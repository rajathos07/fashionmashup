import { useState, useEffect } from 'react'
import { useParams, useNavigate } from 'react-router-dom'
import { productAPI, cartAPI } from '../services/api'
import './ProductDetails.css'

function ProductDetails() {
  const { id } = useParams()
  const navigate = useNavigate()
  const [product, setProduct] = useState(null)
  const [loading, setLoading] = useState(true)
  const [error, setError] = useState(null)
  const [selectedSize, setSelectedSize] = useState('')
  const [quantity, setQuantity] = useState(1)
  const [isAdding, setIsAdding] = useState(false)

  useEffect(() => {
    fetchProduct()
  }, [id])

  const fetchProduct = async () => {
    try {
      const data = await productAPI.getById(id)
      setProduct(data)
    } catch (err) {
      setError('Error loading product')
      console.error(err)
    } finally {
      setLoading(false)
    }
  }

  const handleAddToCart = async () => {
    if (!selectedSize) {
      alert('Please select a size')
      return
    }

    setIsAdding(true)
    try {
      await cartAPI.addToCart(product.id, quantity, selectedSize)
      navigate('/cart')
    } catch (err) {
      if (err.message === 'Not authenticated' || err.message.includes('401')) {
        alert('Please login to add items to cart')
        navigate('/login')
      } else {
        alert('Failed to add to cart: ' + err.message)
      }
    } finally {
      setIsAdding(false)
    }
  }

  if (loading) return <div className="loading-container">Loading...</div>
  if (error) return <div className="error-message">{error}</div>
  if (!product) return <div className="error-message">Product not found</div>

  return (
    <div className="product-details">
      <div className="product-details-container">
        <div className="product-image-section">
          <img
            src={product.image || 'https://images.pexels.com/photos/2769274/pexels-photo-2769274.jpeg?auto=compress&cs=tinysrgb&w=600'}
            alt={product.name}
            className="product-main-image"
          />
        </div>

        <div className="product-info-section">
          <h1>{product.name}</h1>
          <p className="brand">{product.brand || 'Fashion Mashup'}</p>

          <div className="rating">
            <span className="stars">&#9733;&#9733;&#9733;&#9733;&#9733;</span>
            <span className="reviews">(24 reviews)</span>
          </div>

          <div className="price-section">
            <h2 className="price">SAR {product.price.toFixed(2)}</h2>
            <p className="availability">
              {product.stock > 0 ? <span className="in-stock">In Stock</span> : <span className="out-of-stock">Out of Stock</span>}
            </p>
          </div>

          <div className="description">
            <p>{product.description || 'High-quality fashion piece that delivers comfort and style.'}</p>
          </div>

          <div className="size-selection">
            <h3>Select Size</h3>
            <div className="size-grid">
              {product.sizes && product.sizes.length > 0 ? (
                product.sizes.map(sizeObj => (
                  <button
                    key={sizeObj.id}
                    className={`size-option ${selectedSize === sizeObj.size ? 'selected' : ''}`}
                    onClick={() => setSelectedSize(sizeObj.size)}
                  >
                    {sizeObj.size}
                  </button>
                ))
              ) : (
                ['XS', 'S', 'M', 'L', 'XL', 'XXL'].map(size => (
                  <button
                    key={size}
                    className={`size-option ${selectedSize === size ? 'selected' : ''}`}
                    onClick={() => setSelectedSize(size)}
                  >
                    {size}
                  </button>
                ))
              )}
            </div>
          </div>

          <div className="quantity-section">
            <h3>Quantity</h3>
            <div className="quantity-selector">
              <button onClick={() => setQuantity(Math.max(1, quantity - 1))}>-</button>
              <input type="number" value={quantity} readOnly />
              <button onClick={() => setQuantity(quantity + 1)}>+</button>
            </div>
          </div>

          <div className="action-buttons">
            <button
              className="add-to-cart-btn"
              onClick={handleAddToCart}
              disabled={isAdding || product.stock === 0}
            >
              {isAdding ? 'Adding...' : 'Add to Cart'}
            </button>
            <button className="wishlist-add-btn">&#9825; Add to Wishlist</button>
          </div>

          <div className="product-details-info">
            <h3>Product Details</h3>
            <ul>
              <li>Material: 100% Cotton</li>
              <li>Care: Machine wash cold</li>
              <li>Fit: Regular fit</li>
              <li>SKU: {product.id}</li>
            </ul>
          </div>
        </div>
      </div>
    </div>
  )
}

export default ProductDetails
