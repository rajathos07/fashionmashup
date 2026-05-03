const API_BASE_URL = '/api'

async function request(endpoint, options = {}) {
  const config = {
    credentials: 'include',
    headers: {
      'Content-Type': 'application/json',
      ...options.headers
    },
    ...options
  }

  const response = await fetch(`${API_BASE_URL}${endpoint}`, config)

  if (!response.ok) {
    const data = await response.json().catch(() => ({}))
    throw new Error(data.error || `Request failed with status ${response.status}`)
  }

  return response.json()
}

export const authAPI = {
  login: (email, password) =>
    request('/login', {
      method: 'POST',
      body: JSON.stringify({ email, password })
    }),

  register: (name, email, password) =>
    request('/register', {
      method: 'POST',
      body: JSON.stringify({ name, email, password })
    }),

  logout: () =>
    request('/logout', { method: 'POST' }),

  getCurrentUser: () => request('/user')
}

export const productAPI = {
  getAll: (params) => {
    const query = new URLSearchParams(params).toString()
    return request(`/products${query ? '?' + query : ''}`)
  },

  getById: (id) => request(`/products/${id}`),

  getCategories: () => request('/categories')
}

export const cartAPI = {
  getCart: () => request('/cart'),

  addToCart: (productId, quantity, size) =>
    request('/addToCart', {
      method: 'POST',
      body: JSON.stringify({ productId, quantity, size })
    }),

  removeFromCart: (cartItemId) =>
    request('/removeCart', {
      method: 'POST',
      body: JSON.stringify({ cartItemId })
    }),

  updateCartItem: (cartItemId, quantity) =>
    request(`/cart/${cartItemId}`, {
      method: 'PUT',
      body: JSON.stringify({ quantity })
    })
}

export const orderAPI = {
  placeOrder: (orderData) =>
    request('/placeOrder', {
      method: 'POST',
      body: JSON.stringify(orderData)
    }),

  getOrders: () => request('/orders'),

  getOrderById: (id) => request(`/orders/${id}`)
}

export default request
