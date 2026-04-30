import axios from 'axios'

const API_BASE_URL = 'http://localhost:8080'

const api = axios.create({
  baseURL: API_BASE_URL,
  withCredentials: true,
  headers: {
    'Content-Type': 'application/json'
  }
})

export const authAPI = {
  login: (email, password) => api.post('/login', { email, password }),
  register: (name, email, password) => api.post('/register', { name, email, password }),
  logout: () => api.post('/logout'),
  getCurrentUser: () => api.get('/user')
}

export const productAPI = {
  getAll: (params) => api.get('/products', { params }),
  getById: (id) => api.get(`/products/${id}`),
  getCategories: () => api.get('/categories')
}

export const cartAPI = {
  getCart: () => api.get('/cart'),
  addToCart: (productId, quantity, size) =>
    api.post('/addToCart', { productId, quantity, size }),
  removeFromCart: (cartItemId) => api.post(`/removeCart`, { cartItemId }),
  updateCartItem: (cartItemId, quantity) =>
    api.put(`/cart/${cartItemId}`, { quantity })
}

export const orderAPI = {
  placeOrder: (orderData) => api.post('/placeOrder', orderData),
  getOrders: () => api.get('/orders'),
  getOrderById: (id) => api.get(`/orders/${id}`)
}

export default api
