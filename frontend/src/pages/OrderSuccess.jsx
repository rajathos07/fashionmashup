import { Link, useLocation } from 'react-router-dom'
import './OrderSuccess.css'

function OrderSuccess() {
  const location = useLocation()
  const orderId = location.state?.orderId || 'N/A'

  return (
    <div className="order-success-page">
      <div className="success-container">
        <div className="success-icon">✓</div>

        <h1>Order Confirmed!</h1>
        <p className="success-message">
          Thank you for your purchase. Your order has been successfully placed.
        </p>

        <div className="order-details">
          <div className="detail-row">
            <span className="detail-label">Order Number:</span>
            <span className="detail-value">#{orderId}</span>
          </div>

          <div className="detail-row">
            <span className="detail-label">Order Date:</span>
            <span className="detail-value">{new Date().toLocaleDateString()}</span>
          </div>

          <div className="detail-row">
            <span className="detail-label">Estimated Delivery:</span>
            <span className="detail-value">3-5 Business Days</span>
          </div>

          <div className="detail-row">
            <span className="detail-label">Status:</span>
            <span className="detail-value status">Processing</span>
          </div>
        </div>

        <div className="next-steps">
          <h2>What's Next?</h2>
          <ol>
            <li>You will receive an order confirmation email shortly</li>
            <li>We will process and prepare your order for shipment</li>
            <li>You'll receive a tracking number once it ships</li>
            <li>Sit back and wait for your new fashion items!</li>
          </ol>
        </div>

        <div className="action-buttons">
          <Link to="/orders" className="btn btn-primary">View Order</Link>
          <Link to="/products" className="btn btn-secondary">Continue Shopping</Link>
        </div>

        <div className="contact-info">
          <p>Need help? <a href="mailto:support@fashionmashup.com">Contact our support team</a></p>
        </div>
      </div>
    </div>
  )
}

export default OrderSuccess
