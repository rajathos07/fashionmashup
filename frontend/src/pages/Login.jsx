import { useState } from 'react'
import { useNavigate, Link } from 'react-router-dom'
import { authAPI, cartAPI } from '../services/api'
import './Auth.css'

function Login({ setUser }) {
  const navigate = useNavigate()
  const [email, setEmail] = useState('')
  const [password, setPassword] = useState('')
  const [loading, setLoading] = useState(false)
  const [error, setError] = useState(null)

  const handleSubmit = async (e) => {
    e.preventDefault()
    setError(null)
    setLoading(true)

    try {
      const userData = await authAPI.login(email, password)
      setUser(userData)
      
      const localCart = JSON.parse(localStorage.getItem('localCart') || '[]')
      let syncedCart = false
      if (localCart.length > 0) {
        try {
          // Sync all local cart items to the backend
          for (const item of localCart) {
            await cartAPI.addToCart(item.productId, item.quantity, item.size)
          }
          localStorage.removeItem('localCart')
          syncedCart = true
        } catch (e) {
          console.error('Failed to sync local cart:', e)
        }
      }
      
      const pendingItem = localStorage.getItem('pendingCartItem')
      if (pendingItem) {
        try {
          const { productId, quantity, size } = JSON.parse(pendingItem)
          await cartAPI.addToCart(productId, quantity, size)
          localStorage.removeItem('pendingCartItem')
          syncedCart = true
        } catch (e) {
          console.error('Failed to add pending item:', e)
        }
      }
      
      if (syncedCart) {
        navigate('/cart')
      } else {
        navigate('/')
      }
    } catch (err) {
      setError(err.message || 'Login failed')
    } finally {
      setLoading(false)
    }
  }

  return (
    <div className="auth-container">
      <div className="auth-card">
        <h1>Login</h1>
        <p className="auth-subtitle">Welcome back to Fashion Mashup</p>

        {error && <div className="error-alert">{error}</div>}

        <form onSubmit={handleSubmit} className="auth-form">
          <div className="form-group">
            <label htmlFor="email">Email Address</label>
            <input
              type="email"
              id="email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              required
              placeholder="you@example.com"
            />
          </div>

          <div className="form-group">
            <label htmlFor="password">Password</label>
            <input
              type="password"
              id="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              required
              placeholder="--------"
            />
          </div>

          <button type="submit" disabled={loading} className="auth-button">
            {loading ? 'Logging in...' : 'Login'}
          </button>
        </form>

        <p className="auth-footer">
          Don't have an account? <Link to="/register">Register here</Link>
        </p>

        <div className="auth-divider">or</div>

        <button className="social-button">Continue with Google</button>
      </div>
    </div>
  )
}

export default Login
