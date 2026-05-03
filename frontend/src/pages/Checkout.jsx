import { useState, useEffect } from 'react'
import { useNavigate } from 'react-router-dom'
import { cartAPI, orderAPI } from '../services/api'
import './Checkout.css'

function Checkout({ user }) {
  const navigate = useNavigate()
  const [loading, setLoading] = useState(false)
  const [cartTotal, setCartTotal] = useState(0)
  const [formData, setFormData] = useState({
    fullName: user?.name || '',
    email: user?.email || '',
    phone: '',
    address: '',
    city: '',
    zipCode: '',
    cardNumber: '',
    cardExpiry: '',
    cardCvv: ''
  })

  useEffect(() => {
    fetchCartTotal()
  }, [])

  const fetchCartTotal = async () => {
    try {
      const data = await cartAPI.getCart()
      const total = (data.items || []).reduce((sum, item) => sum + ((item.product?.price || item.unitPrice) * item.quantity), 0)
      setCartTotal(total * 1.15)
    } catch (err) {
      console.error('Error fetching cart:', err)
    }
  }

  const handleChange = (e) => {
    const { name, value } = e.target
    setFormData(prev => ({ ...prev, [name]: value }))
  }

  const handleSubmit = async (e) => {
    e.preventDefault()
    setLoading(true)

    try {
      const order = await orderAPI.placeOrder({
        shippingAddress: formData.address + ', ' + formData.city + ' ' + formData.zipCode,
        city: formData.city,
        zipCode: formData.zipCode,
        phone: formData.phone,
        paymentMethod: 'credit_card'
      })
      navigate('/order-success', { state: { orderId: order.id } })
    } catch (err) {
      console.error('Error placing order:', err)
      alert('Error placing order: ' + err.message)
    } finally {
      setLoading(false)
    }
  }

  return (
    <div className="checkout-page">
      <h1>Checkout</h1>

      <div className="checkout-container">
        <form onSubmit={handleSubmit} className="checkout-form">
          <section className="form-section">
            <h2>Shipping Information</h2>

            <div className="form-row">
              <div className="form-group">
                <label htmlFor="fullName">Full Name</label>
                <input
                  type="text"
                  id="fullName"
                  name="fullName"
                  value={formData.fullName}
                  onChange={handleChange}
                  required
                />
              </div>

              <div className="form-group">
                <label htmlFor="email">Email</label>
                <input
                  type="email"
                  id="email"
                  name="email"
                  value={formData.email}
                  onChange={handleChange}
                  required
                />
              </div>
            </div>

            <div className="form-row">
              <div className="form-group">
                <label htmlFor="phone">Phone</label>
                <input
                  type="tel"
                  id="phone"
                  name="phone"
                  value={formData.phone}
                  onChange={handleChange}
                  required
                />
              </div>

              <div className="form-group">
                <label htmlFor="address">Address</label>
                <input
                  type="text"
                  id="address"
                  name="address"
                  value={formData.address}
                  onChange={handleChange}
                  required
                />
              </div>
            </div>

            <div className="form-row">
              <div className="form-group">
                <label htmlFor="city">City</label>
                <input
                  type="text"
                  id="city"
                  name="city"
                  value={formData.city}
                  onChange={handleChange}
                  required
                />
              </div>

              <div className="form-group">
                <label htmlFor="zipCode">Zip Code</label>
                <input
                  type="text"
                  id="zipCode"
                  name="zipCode"
                  value={formData.zipCode}
                  onChange={handleChange}
                  required
                />
              </div>
            </div>
          </section>

          <section className="form-section">
            <h2>Payment Information</h2>

            <div className="form-group full">
              <label htmlFor="cardNumber">Card Number</label>
              <input
                type="text"
                id="cardNumber"
                name="cardNumber"
                placeholder="1234 5678 9012 3456"
                value={formData.cardNumber}
                onChange={handleChange}
                required
              />
            </div>

            <div className="form-row">
              <div className="form-group">
                <label htmlFor="cardExpiry">Expiry Date</label>
                <input
                  type="text"
                  id="cardExpiry"
                  name="cardExpiry"
                  placeholder="MM/YY"
                  value={formData.cardExpiry}
                  onChange={handleChange}
                  required
                />
              </div>

              <div className="form-group">
                <label htmlFor="cardCvv">CVV</label>
                <input
                  type="text"
                  id="cardCvv"
                  name="cardCvv"
                  placeholder="123"
                  value={formData.cardCvv}
                  onChange={handleChange}
                  required
                />
              </div>
            </div>
          </section>

          <button type="submit" disabled={loading} className="place-order-btn">
            {loading ? 'Processing...' : 'Place Order'}
          </button>
        </form>

        <div className="order-summary">
          <h2>Order Summary</h2>

          <div className="summary-item">
            <span>Subtotal</span>
            <span>SAR {(cartTotal / 1.15).toFixed(2)}</span>
          </div>

          <div className="summary-item">
            <span>Tax</span>
            <span>SAR {(cartTotal - cartTotal / 1.15).toFixed(2)}</span>
          </div>

          <div className="summary-item">
            <span>Shipping</span>
            <span>Free</span>
          </div>

          <div className="summary-divider"></div>

          <div className="summary-total">
            <span>Total</span>
            <span>SAR {cartTotal.toFixed(2)}</span>
          </div>

          <div className="payment-methods">
            <h3>Payment Methods</h3>
            <label className="payment-option">
              <input type="radio" name="payment" value="credit" defaultChecked />
              <span>Credit Card</span>
            </label>
            <label className="payment-option">
              <input type="radio" name="payment" value="debit" />
              <span>Debit Card</span>
            </label>
            <label className="payment-option">
              <input type="radio" name="payment" value="bank" />
              <span>Bank Transfer</span>
            </label>
          </div>
        </div>
      </div>
    </div>
  )
}

export default Checkout
