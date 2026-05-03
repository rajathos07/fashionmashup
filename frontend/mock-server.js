import express from 'express';
import cors from 'cors';

const app = express();
app.use(cors());
app.use(express.json());

// Mock data
const products = [
  { id: 1, name: 'Classic White Shirt', categoryId: 1, price: 149.99, discountPercent: 10, image: 'https://images.pexels.com/photos/298863/pexels-photo-298863.jpeg?auto=compress&cs=tinysrgb&w=600', stock: 70, sizes: [{ id: 1, size: 'S', stock: 15, available: true }, { id: 2, size: 'M', stock: 25, available: true }, { id: 3, size: 'L', stock: 20, available: true }, { id: 4, size: 'XL', stock: 10, available: true }], description: 'Premium cotton white shirt' },
  { id: 2, name: 'Navy Blue Blazer', categoryId: 1, price: 349.99, discountPercent: 15, image: 'https://images.pexels.com/photos/1040945/pexels-photo-1040945.jpeg?auto=compress&cs=tinysrgb&w=600', stock: 35, sizes: [{ id: 5, size: 'S', stock: 8, available: true }, { id: 6, size: 'M', stock: 12, available: true }, { id: 7, size: 'L', stock: 10, available: true }, { id: 8, size: 'XL', stock: 5, available: true }], description: 'Tailored navy blazer' },
  { id: 3, name: 'Slim Fit Chinos', categoryId: 1, price: 129.99, discountPercent: 0, image: 'https://images.pexels.com/photos/6764190/pexels-photo-6764190.jpeg?auto=compress&cs=tinysrgb&w=600', stock: 100, sizes: [{ id: 9, size: 'S', stock: 20, available: true }, { id: 10, size: 'M', stock: 30, available: true }, { id: 11, size: 'L', stock: 25, available: true }, { id: 12, size: 'XL', stock: 15, available: true }, { id: 13, size: 'XXL', stock: 10, available: true }], description: 'Comfortable stretch chinos' },
  { id: 4, name: 'Denim Jacket', categoryId: 1, price: 199.99, discountPercent: 20, image: 'https://images.pexels.com/photos/5405092/pexels-photo-5405092.jpeg?auto=compress&cs=tinysrgb&w=600', stock: 46, sizes: [{ id: 14, size: 'S', stock: 10, available: true }, { id: 15, size: 'M', stock: 18, available: true }, { id: 16, size: 'L', stock: 12, available: true }, { id: 17, size: 'XL', stock: 6, available: true }], description: 'Classic denim jacket' },
  { id: 5, name: 'Polo T-Shirt', categoryId: 1, price: 79.99, discountPercent: 0, image: 'https://images.pexels.com/photos/5698851/pexels-photo-5698851.jpeg?auto=compress&cs=tinysrgb&w=600', stock: 115, sizes: [{ id: 18, size: 'XS', stock: 10, available: true }, { id: 19, size: 'S', stock: 20, available: true }, { id: 20, size: 'M', stock: 35, available: true }, { id: 21, size: 'L', stock: 25, available: true }, { id: 22, size: 'XL', stock: 15, available: true }, { id: 23, size: 'XXL', stock: 10, available: true }], description: 'Premium pique polo shirt' },
  { id: 6, name: 'Floral Summer Dress', categoryId: 2, price: 189.99, discountPercent: 10, image: 'https://images.pexels.com/photos/985635/pexels-photo-985635.jpeg?auto=compress&cs=tinysrgb&w=600', stock: 60, sizes: [{ id: 24, size: 'XS', stock: 8, available: true }, { id: 25, size: 'S', stock: 15, available: true }, { id: 26, size: 'M', stock: 20, available: true }, { id: 27, size: 'L', stock: 12, available: true }, { id: 28, size: 'XL', stock: 5, available: true }], description: 'Lightweight floral midi dress' },
  { id: 7, name: 'Silk Blouse', categoryId: 2, price: 219.99, discountPercent: 0, image: 'https://images.pexels.com/photos/7691105/pexels-photo-7691105.jpeg?auto=compress&cs=tinysrgb&w=600', stock: 46, sizes: [{ id: 29, size: 'XS', stock: 6, available: true }, { id: 30, size: 'S', stock: 12, available: true }, { id: 31, size: 'M', stock: 18, available: true }, { id: 32, size: 'L', stock: 10, available: true }], description: 'Elegant silk blouse' },
  { id: 8, name: 'High-Waist Trousers', categoryId: 2, price: 169.99, discountPercent: 5, image: 'https://images.pexels.com/photos/7691048/pexels-photo-7691048.jpeg?auto=compress&cs=tinysrgb&w=600', stock: 60, sizes: [{ id: 33, size: 'S', stock: 14, available: true }, { id: 34, size: 'M', stock: 22, available: true }, { id: 35, size: 'L', stock: 16, available: true }, { id: 36, size: 'XL', stock: 8, available: true }], description: 'Tailored high-waist trousers' },
  { id: 9, name: 'Cashmere Cardigan', categoryId: 2, price: 279.99, discountPercent: 0, image: 'https://images.pexels.com/photos/6764195/pexels-photo-6764195.jpeg?auto=compress&cs=tinysrgb&w=600', stock: 42, sizes: [{ id: 37, size: 'S', stock: 10, available: true }, { id: 38, size: 'M', stock: 15, available: true }, { id: 39, size: 'L', stock: 12, available: true }, { id: 40, size: 'XL', stock: 5, available: true }], description: 'Soft cashmere cardigan' },
  { id: 10, name: 'Wrap Midi Skirt', categoryId: 2, price: 139.99, discountPercent: 15, image: 'https://images.pexels.com/photos/6030968/pexels-photo-6030968.jpeg?auto=compress&cs=tinysrgb&w=600', stock: 56, sizes: [{ id: 41, size: 'XS', stock: 8, available: true }, { id: 42, size: 'S', stock: 14, available: true }, { id: 43, size: 'M', stock: 20, available: true }, { id: 44, size: 'L', stock: 10, available: true }, { id: 45, size: 'XL', stock: 4, available: true }], description: 'Satin wrap midi skirt' },
  { id: 11, name: 'Leather Crossbody Bag', categoryId: 3, price: 249.99, discountPercent: 0, image: 'https://images.pexels.com/photos/1152077/pexels-photo-1152077.jpeg?auto=compress&cs=tinysrgb&w=600', stock: 25, sizes: [{ id: 46, size: 'One Size', stock: 25, available: true }], description: 'Genuine leather crossbody bag' },
  { id: 12, name: 'Minimalist Watch', categoryId: 3, price: 399.99, discountPercent: 10, image: 'https://images.pexels.com/photos/190819/pexels-photo-190819.jpeg?auto=compress&cs=tinysrgb&w=600', stock: 18, sizes: [{ id: 47, size: 'One Size', stock: 18, available: true }], description: 'Sleek minimalist watch' },
  { id: 13, name: 'Aviator Sunglasses', categoryId: 3, price: 159.99, discountPercent: 0, image: 'https://images.pexels.com/photos/7018401/pexels-photo-7018401.jpeg?auto=compress&cs=tinysrgb&w=600', stock: 30, sizes: [{ id: 48, size: 'One Size', stock: 30, available: true }], description: 'Classic aviator sunglasses' },
  { id: 14, name: 'Wool Scarf', categoryId: 3, price: 89.99, discountPercent: 5, image: 'https://images.pexels.com/photos/6712412/pexels-photo-6712412.jpeg?auto=compress&cs=tinysrgb&w=600', stock: 22, sizes: [{ id: 49, size: 'One Size', stock: 22, available: true }], description: 'Luxurious merino wool scarf' },
  { id: 15, name: 'Canvas Tote Bag', categoryId: 3, price: 69.99, discountPercent: 0, image: 'https://images.pexels.com/photos/2905238/pexels-photo-2905238.jpeg?auto=compress&cs=tinysrgb&w=600', stock: 35, sizes: [{ id: 50, size: 'One Size', stock: 35, available: true }], description: 'Durable canvas tote bag' }
];

const categories = [
  { id: 1, name: "Men's Fashion", description: 'Stylish clothing for men' },
  { id: 2, name: "Women's Fashion", description: 'Trendy clothing for women' },
  { id: 3, name: 'Accessories', description: 'Bags, watches, jewelry and more' }
];

let users = [{ id: 1, email: 'test@fashion.com', password: 'password123', name: 'Test User' }];
let carts = { 1: { items: [] } };
let orders = {};

// Session store
const sessions = new Map();

function generateSessionId() {
  return Math.random().toString(36).substring(2, 15);
}

// Routes
app.get('/api/categories', (req, res) => {
  res.json(categories.map(c => ({ id: c.id, name: c.name, description: c.description, isActive: true })));
});

app.get('/api/products', (req, res) => {
  const categoryId = req.query.categoryId;
  let result = products;
  if (categoryId) {
    result = products.filter(p => p.categoryId === parseInt(categoryId));
  }
  res.json(result.map(p => ({
    id: p.id, name: p.name, price: p.price, image: p.image, stock: p.stock,
    categoryId: p.categoryId, discountPercent: p.discountPercent, description: p.description,
    sizes: p.sizes.map(s => ({ id: s.id, size: s.size, stock: s.stock, available: s.available })),
    isActive: true
  })));
});

app.get('/api/products/:id', (req, res) => {
  const product = products.find(p => p.id === parseInt(req.params.id));
  if (!product) return res.status(404).json({ error: 'Product not found' });
  res.json({
    id: product.id, name: product.name, price: product.price, image: product.image, stock: product.stock,
    categoryId: product.categoryId, discountPercent: product.discountPercent, description: product.description,
    sizes: product.sizes.map(s => ({ id: s.id, size: s.size, stock: s.stock, available: s.available })),
    isActive: true
  });
});

app.post('/api/login', (req, res) => {
  const { email, password } = req.body;
  const user = users.find(u => u.email === email && u.password === password);
  if (!user) return res.status(401).json({ error: 'Invalid credentials' });

  const sessionId = generateSessionId();
  sessions.set(sessionId, { userId: user.id, email: user.email });
  res.cookie('SESSIONID', sessionId, { httpOnly: true, sameSite: 'lax' });
  res.json({ id: user.id, name: user.name, email: user.email });
});

app.post('/api/register', (req, res) => {
  const { name, email, password } = req.body;
  if (users.find(u => u.email === email)) return res.status(400).json({ error: 'Email exists' });

  const id = Math.max(...users.map(u => u.id)) + 1;
  users.push({ id, name, email, password });
  carts[id] = { items: [] };

  const sessionId = generateSessionId();
  sessions.set(sessionId, { userId: id, email });
  res.cookie('SESSIONID', sessionId, { httpOnly: true, sameSite: 'lax' });
  res.json({ id, name, email });
});

app.post('/api/logout', (req, res) => {
  const sessionId = req.cookies.SESSIONID;
  if (sessionId) sessions.delete(sessionId);
  res.clearCookie('SESSIONID');
  res.json({ message: 'Logged out' });
});

app.get('/api/user', (req, res) => {
  const sessionId = req.cookies.SESSIONID;
  const session = sessions.get(sessionId);
  if (!session) return res.status(401).json({ error: 'Not authenticated' });

  const user = users.find(u => u.id === session.userId);
  if (!user) return res.status(401).json({ error: 'User not found' });
  res.json({ id: user.id, name: user.name, email: user.email });
});

app.get('/api/cart/*', (req, res) => {
  const sessionId = req.cookies.SESSIONID;
  const session = sessions.get(sessionId);
  if (!session) return res.status(401).json({ error: 'Not authenticated' });

  const userId = session.userId;
  const cart = carts[userId] || { items: [] };
  res.json({
    items: cart.items.map(item => ({
      id: item.id, productId: item.productId, quantity: item.quantity, size: item.size,
      unitPrice: item.unitPrice, product: {
        id: item.productId, name: item.productName, price: item.unitPrice,
        image: item.productImage
      }
    }))
  });
});

app.post('/api/addToCart', (req, res) => {
  const sessionId = req.cookies.SESSIONID;
  const session = sessions.get(sessionId);
  if (!session) return res.status(401).json({ error: 'Not authenticated' });

  const { productId, quantity, size } = req.body;
  const product = products.find(p => p.id === productId);
  if (!product) return res.status(404).json({ error: 'Product not found' });

  const userId = session.userId;
  if (!carts[userId]) carts[userId] = { items: [] };

  const itemId = Math.random().toString(36).substring(2, 15);
  carts[userId].items.push({
    id: itemId, productId, quantity, size, unitPrice: product.price,
    productName: product.name, productImage: product.image
  });

  res.json({ message: 'Item added to cart' });
});

app.put('/api/cart/:id', (req, res) => {
  const sessionId = req.cookies.SESSIONID;
  const session = sessions.get(sessionId);
  if (!session) return res.status(401).json({ error: 'Not authenticated' });

  const { quantity } = req.body;
  const userId = session.userId;
  const cart = carts[userId];
  if (!cart) return res.status(400).json({ error: 'Cart not found' });

  const item = cart.items.find(i => i.id === req.params.id);
  if (!item) return res.status(404).json({ error: 'Item not found' });

  item.quantity = quantity;
  res.json({ message: 'Cart updated' });
});

app.post('/api/removeCart', (req, res) => {
  const sessionId = req.cookies.SESSIONID;
  const session = sessions.get(sessionId);
  if (!session) return res.status(401).json({ error: 'Not authenticated' });

  const { cartItemId } = req.body;
  const userId = session.userId;
  const cart = carts[userId];
  if (!cart) return res.status(400).json({ error: 'Cart not found' });

  cart.items = cart.items.filter(i => i.id !== cartItemId);
  res.json({ message: 'Item removed' });
});

app.post('/api/placeOrder', (req, res) => {
  const sessionId = req.cookies.SESSIONID;
  const session = sessions.get(sessionId);
  if (!session) return res.status(401).json({ error: 'Not authenticated' });

  const userId = session.userId;
  const cart = carts[userId];
  if (!cart || cart.items.length === 0) return res.status(400).json({ error: 'Cart empty' });

  const orderId = Math.random().toString(36).substring(2, 15);
  const totalAmount = cart.items.reduce((sum, item) => sum + (item.unitPrice * item.quantity), 0);

  orders[orderId] = {
    id: orderId, userId, totalAmount, items: [...cart.items],
    status: 'Pending', createdAt: new Date()
  };

  carts[userId].items = [];
  res.json({ id: orderId, message: 'Order placed' });
});

app.get('/api/orders', (req, res) => {
  const sessionId = req.cookies.SESSIONID;
  const session = sessions.get(sessionId);
  if (!session) return res.status(401).json({ error: 'Not authenticated' });

  const userId = session.userId;
  const userOrders = Object.values(orders).filter(o => o.userId === userId);
  res.json(userOrders.map(o => ({
    id: o.id, totalAmount: o.totalAmount, status: o.status,
    createdAt: o.createdAt, items: o.items.map(i => ({
      id: i.id, productId: i.productId, name: i.productName,
      quantity: i.quantity, unitPrice: i.unitPrice, image: i.productImage
    }))
  })));
});

const PORT = 3001;
app.listen(PORT, () => {
  console.log(`Mock API server running on http://localhost:${PORT}`);
});
