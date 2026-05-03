import { Link, useNavigate } from 'react-router-dom'
import { useState } from 'react'
import { authAPI } from '../services/api'
import './Navbar.css'

function Navbar({ user, setUser }) {
  const navigate = useNavigate()
  const [mobileMenuOpen, setMobileMenuOpen] = useState(false)

  const handleLogout = async () => {
    try {
      await authAPI.logout()
      setUser(null)
      navigate('/')
    } catch (error) {
      console.error('Logout failed:', error)
      setUser(null)
      navigate('/')
    }
  }

  return (
    <nav className="navbar">
      <div className="navbar-container">
        <Link to="/" className="navbar-logo">
          <span className="logo-text">stella</span>
        </Link>

        <div className={`navbar-menu ${mobileMenuOpen ? 'active' : ''}`}>
          <Link to="/" className="nav-link" onClick={() => setMobileMenuOpen(false)}>
            Home
          </Link>
          <Link to="/products" className="nav-link" onClick={() => setMobileMenuOpen(false)}>
            Shop
          </Link>

          <div className="nav-actions">
            <Link to="/cart" className="nav-link cart-link" onClick={() => setMobileMenuOpen(false)}>
              <span className="cart-icon">&#128722;</span>
              Cart
            </Link>

            {user ? (
              <>
                <Link to="/orders" className="nav-link" onClick={() => setMobileMenuOpen(false)}>
                  Orders
                </Link>
                <button className="nav-link logout-btn" onClick={() => {
                  handleLogout()
                  setMobileMenuOpen(false)
                }}>
                  Logout
                </button>
                <span className="user-name">{user.name}</span>
              </>
            ) : (
              <>
                <Link to="/login" className="nav-link" onClick={() => setMobileMenuOpen(false)}>
                  Login
                </Link>
                <Link to="/register" className="nav-link register-link" onClick={() => setMobileMenuOpen(false)}>
                  Register
                </Link>
              </>
            )}
          </div>
        </div>

        <button className="mobile-toggle" onClick={() => setMobileMenuOpen(!mobileMenuOpen)}>
          &#9776;
        </button>
      </div>
    </nav>
  )
}

export default Navbar
