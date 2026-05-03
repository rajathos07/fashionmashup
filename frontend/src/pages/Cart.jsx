import { useState, useEffect } from 'react'
import { Link, useNavigate } from 'react-router-dom'
import { cartAPI } from '../services/api'
import './Cart.css'

function Cart() {
  const navigate = useNavigate()
  const [cartItems, setCartItems] = useState([])
  const [loading, setLoading] = useState(true)
  const [error, setError] = useState(null)

  useEffect(() => {
    fetchCart()
  }, [])

  const fetchCart = async () => {
    try {
      const data = await cartAPI.getCart()
      setCartItems(data.items || [])
    } catch (err) {
      if (err.message.includes('401') || err.message === 'Not authenticated') {
        setError('Please login to view your cart')
      } else {
        setError('Error loading cart')
      }
      console.error(err)
    } finally {
      setLoading(false)
    }
  }

  const handleRemoveItem = async (cartItemId) => {
    try {
      await cartAPI.removeFromCart(cartItemId)
      fetchCart()
    } catch (err) {
      console.error('Error removing item:', err)
    }
  }

  const handleQuantityChange = async (cartItemId, newQuantity) => {
    if (newQuantity < 1) return
    try {
      await cartAPI.updateCartItem(cartItemId, newQuantity)
      fetchCart()
    } catch (err) {
      console.error('Error updating quantity:', err)
    }
  }

  const subtotal = cartItems.reduce((sum, item) => sum + ((item.product?.price || item.unitPrice) * item.quantity), 0)
  const tax = subtotal * 0.15
  const total = subtotal + tax

  if (loading) return <div className="loading-container">Loading cart...</div>

  return (
    <div className="cart-page">
      <h1>Shopping Cart</h1>

      <div className="cart-container">
        <div className="cart-items-section">
          {cartItems.length > 0 ? (
            <>
              <div className="cart-items-header">
                <span>Product</span>
                <span>Price</span>
                <span>Quantity</span>
                <span>Total</span>
                <span></span>
              </div>

              <div className="cart-items-list">
                {cartItems.map(item => (
                  <div key={item.id} className="cart-item">
                    <div className="item-product">
                      <img
                        src={item.product?.image || 'https://images.pexels.com/photos/2769274/pexels-photo-2769274.jpeg?auto=compress&cs=tinysrgb&w=600'}
                        alt={item.product?.name}
                      />
                      <div className="item-details">
                        <h3>{item.product?.name}</h3>
                        <p className="item-size">Size: {item.size}</p>
                      </div>
                    </div>

                    <div className="item-price">
                      SAR {(item.product?.price || item.unitPrice).toFixed(2)}
                    </div>

                    <div className="item-quantity">
                      <button onClick={() => handleQuantityChange(item.id, item.quantity - 1)}>-</button>
                      <input type="number" value={item.quantity} readOnly />
                      <button onClick={() => handleQuantityChange(item.id, item.quantity + 1)}>+</button>
                    </div>

                    <div className="item-total">
                      SAR {((item.product?.price || item.unitPrice) * item.quantity).toFixed(2)}
                    </div>

                    <button
                      className="remove-btn"
                      onClick={() => handleRemoveItem(item.id)}
                    >
                      x
                    </button>
                  </div>
                ))}
              </div>

              <div className="continue-shopping">
                <Link to="/products" className="continue-btn">Continue Shopping</Link>
              </div>
            </>
          ) : (
            <div className="empty-cart">
              <p>{error || 'Your cart is empty'}</p>
              <Link to="/products" className="continue-btn">Start Shopping</Link>
            </div>
          )}
        </div>

        {cartItems.length > 0 && (
          <div className="cart-summary">
            <h2>Order Summary</h2>

            <div className="summary-row">
              <span>Subtotal</span>
              <span>SAR {subtotal.toFixed(2)}</span>
            </div>

            <div className="summary-row">
              <span>Tax (15%)</span>
              <span>SAR {tax.toFixed(2)}</span>
            </div>

            <div className="summary-row">
              <span>Shipping</span>
              <span>Free</span>
            </div>

            <div className="summary-divider"></div>

            <div className="summary-total">
              <span>Total</span>
              <span>SAR {total.toFixed(2)}</span>
            </div>

            <button className="checkout-btn" onClick={() => navigate('/checkout')}>
              Proceed to Checkout
            </button>

            <button className="continue-shopping-btn" onClick={() => navigate('/products')}>
              Continue Shopping
            </button>
          </div>
        )}
      </div>
    </div>
  )
}

export default Cart
