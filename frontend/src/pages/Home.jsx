import { useState, useEffect } from 'react'
import { Link } from 'react-router-dom'
import { productAPI } from '../services/api'
import './Home.css'

function Home() {
  const [featuredProducts, setFeaturedProducts] = useState([])
  const [loading, setLoading] = useState(true)

  useEffect(() => {
    const fetchFeatured = async () => {
      try {
        const data = await productAPI.getAll()
        const products = Array.isArray(data) ? data : []
        setFeaturedProducts(products.slice(0, 4))
      } catch (err) {
        console.error('Error fetching featured products:', err)
      } finally {
        setLoading(false)
      }
    }
    fetchFeatured()
  }, [])

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
          <div className="feature-icon">&#128666;</div>
          <h3>Free Shipping</h3>
          <p>On orders over SAR 100</p>
        </div>
        <div className="feature-card">
          <div className="feature-icon">&#128274;</div>
          <h3>Secure Shopping</h3>
          <p>Your data is always protected</p>
        </div>
        <div className="feature-card">
          <div className="feature-icon">&#8617;</div>
          <h3>Easy Returns</h3>
          <p>30-day return guarantee</p>
        </div>
      </section>

      <section className="categories-preview">
        <h2>Shop by Category</h2>
        <div className="categories-grid">
          <Link to="/products?categoryId=1" className="category-card">
            <img src="https://images.pexels.com/photos/1926769/pexels-photo-1926769.jpeg?auto=compress&cs=tinysrgb&w=600" alt="Men" />
            <h3>Men's Fashion</h3>
          </Link>
          <Link to="/products?categoryId=2" className="category-card">
            <img src="https://images.pexels.com/photos/1440680/pexels-photo-1440680.jpeg?auto=compress&cs=tinysrgb&w=600" alt="Women" />
            <h3>Women's Fashion</h3>
          </Link>
          <Link to="/products?categoryId=3" className="category-card">
            <img src="https://images.pexels.com/photos/1267681/pexels-photo-1267681.jpeg?auto=compress&cs=tinysrgb&w=600" alt="Accessories" />
            <h3>Accessories</h3>
          </Link>
        </div>
      </section>

      {!loading && featuredProducts.length > 0 && (
        <section className="featured-products">
          <h2>Featured Products</h2>
          <div className="featured-grid">
            {featuredProducts.map(product => (
              <Link to={`/products/${product.id}`} key={product.id} className="featured-card">
                <img src={product.image} alt={product.name} />
                <h3>{product.name}</h3>
                <p className="featured-price">SAR {product.price.toFixed(2)}</p>
              </Link>
            ))}
          </div>
        </section>
      )}
    </div>
  )
}

export default Home
