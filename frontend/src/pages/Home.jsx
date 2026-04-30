import { Link } from 'react-router-dom'
import './Home.css'

function Home() {
  return (
    <div className="home">
      <section className="hero">
        <div className="hero-content">
          <h1>Simple<br />is More</h1>
          <p>Discover our collection of timeless fashion pieces</p>
          <Link to="/products" className="cta-button">
            Shop Now
          </Link>
        </div>
        <div className="hero-image">
          <img src="https://images.pexels.com/photos/1055691/pexels-photo-1055691.jpeg?auto=compress&cs=tinysrgb&w=600" alt="Hero" />
        </div>
      </section>

      <section className="features">
        <div className="feature-card">
          <div className="feature-icon">🚚</div>
          <h3>Free Shipping</h3>
          <p>On orders over SAR 100</p>
        </div>
        <div className="feature-card">
          <div className="feature-icon">🔒</div>
          <h3>Secure Shopping</h3>
          <p>Your data is always protected</p>
        </div>
        <div className="feature-card">
          <div className="feature-icon">↩️</div>
          <h3>Easy Returns</h3>
          <p>30-day return guarantee</p>
        </div>
      </section>

      <section className="categories-preview">
        <h2>Shop by Category</h2>
        <div className="categories-grid">
          <Link to="/products" className="category-card">
            <img src="https://images.pexels.com/photos/1926769/pexels-photo-1926769.jpeg?auto=compress&cs=tinysrgb&w=600" alt="Men" />
            <h3>Men's Fashion</h3>
          </Link>
          <Link to="/products" className="category-card">
            <img src="https://images.pexels.com/photos/1440680/pexels-photo-1440680.jpeg?auto=compress&cs=tinysrgb&w=600" alt="Women" />
            <h3>Women's Fashion</h3>
          </Link>
          <Link to="/products" className="category-card">
            <img src="https://images.pexels.com/photos/1267681/pexels-photo-1267681.jpeg?auto=compress&cs=tinysrgb&w=600" alt="Accessories" />
            <h3>Accessories</h3>
          </Link>
        </div>
      </section>
    </div>
  )
}

export default Home
