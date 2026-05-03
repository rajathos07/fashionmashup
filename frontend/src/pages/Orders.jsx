import { useState, useEffect } from 'react'
import { orderAPI } from '../services/api'
import './Orders.css'

function Orders() {
  const [orders, setOrders] = useState([])
  const [loading, setLoading] = useState(true)
  const [error, setError] = useState(null)

  useEffect(() => {
    fetchOrders()
  }, [])

  const fetchOrders = async () => {
    try {
      const data = await orderAPI.getOrders()
      setOrders(Array.isArray(data) ? data : [])
    } catch (err) {
      if (err.message.includes('401') || err.message === 'Not authenticated') {
        setError('Please login to view your orders')
      } else {
        setError('Error loading orders')
      }
      console.error(err)
    } finally {
      setLoading(false)
    }
  }

  if (loading) return <div className="loading-container">Loading orders...</div>

  return (
    <div className="orders-page">
      <h1>My Orders</h1>

      {error && <div className="error-message">{error}</div>}

      <div className="orders-container">
        {orders.length > 0 ? (
          <div className="orders-list">
            {orders.map(order => (
              <div key={order.id} className="order-card">
                <div className="order-header">
                  <div>
                    <h3>Order #{order.id}</h3>
                    <p className="order-date">{new Date(order.createdAt).toLocaleDateString()}</p>
                  </div>
                  <span className={`order-status status-${order.status?.toLowerCase() || 'pending'}`}>
                    {order.status || 'Pending'}
                  </span>
                </div>

                <div className="order-items">
                  {order.items && order.items.map(item => (
                    <div key={item.id} className="order-item">
                      <img
                        src={item.product?.image || 'https://images.pexels.com/photos/2769274/pexels-photo-2769274.jpeg?auto=compress&cs=tinysrgb&w=600'}
                        alt={item.product?.name || item.name}
                      />
                      <div className="item-info">
                        <p className="item-name">{item.product?.name || item.name}</p>
                        <p className="item-size">Size: {item.size}</p>
                      </div>
                      <div className="item-qty">
                        <p>Qty: {item.quantity}</p>
                        <p className="item-price">SAR {(item.product?.price || item.unitPrice).toFixed(2)}</p>
                      </div>
                    </div>
                  ))}
                </div>

                <div className="order-footer">
                  <div className="order-total">
                    <span>Total Amount:</span>
                    <span>SAR {order.totalAmount?.toFixed(2)}</span>
                  </div>
                  <button className="view-details-btn">View Details</button>
                </div>
              </div>
            ))}
          </div>
        ) : (
          <div className="empty-orders">
            <p>You haven't placed any orders yet</p>
          </div>
        )}
      </div>
    </div>
  )
}

export default Orders
