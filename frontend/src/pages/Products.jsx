import { useState, useEffect } from 'react'
import { Link, useSearchParams } from 'react-router-dom'
import { productAPI } from '../services/api'
import './Products.css'

function Products() {
  const [searchParams] = useSearchParams()
  const [products, setProducts] = useState([])
  const [categories, setCategories] = useState([])
  const [filteredProducts, setFilteredProducts] = useState([])
  const [loading, setLoading] = useState(true)
  const [error, setError] = useState(null)

  const [filters, setFilters] = useState({
    category: searchParams.get('categoryId') || '',
    priceRange: [0, 500],
    size: '',
    brand: ''
  })

  const [sortBy, setSortBy] = useState('popular')

  useEffect(() => {
    fetchProducts()
    fetchCategories()
  }, [])

  useEffect(() => {
    applyFilters()
  }, [filters, products, sortBy])

  const fetchProducts = async () => {
    try {
      const data = await productAPI.getAll()
      setProducts(Array.isArray(data) ? data : [])
    } catch (err) {
      setError('Error fetching products')
      console.error(err)
    } finally {
      setLoading(false)
    }
  }

  const fetchCategories = async () => {
    try {
      const data = await productAPI.getCategories()
      setCategories(Array.isArray(data) ? data : [])
    } catch (err) {
      console.error('Error fetching categories:', err)
    }
  }

  const applyFilters = () => {
    let filtered = [...products]

    if (filters.category) {
      filtered = filtered.filter(p => p.categoryId === parseInt(filters.category))
    }

    filtered = filtered.filter(p => p.price >= filters.priceRange[0] && p.price <= filters.priceRange[1])

    if (filters.size) {
      filtered = filtered.filter(p => p.sizes && p.sizes.some(s => s.size === filters.size))
    }

    if (filters.brand) {
      filtered = filtered.filter(p => p.brand && p.brand.toLowerCase().includes(filters.brand.toLowerCase()))
    }

    if (sortBy === 'price-asc') {
      filtered.sort((a, b) => a.price - b.price)
    } else if (sortBy === 'price-desc') {
      filtered.sort((a, b) => b.price - a.price)
    } else if (sortBy === 'newest') {
      filtered.sort((a, b) => new Date(b.createdAt) - new Date(a.createdAt))
    }

    setFilteredProducts(filtered)
  }

  const handlePriceChange = (e) => {
    const value = parseInt(e.target.value)
    setFilters({
      ...filters,
      priceRange: [filters.priceRange[0], value]
    })
  }

  if (loading) return <div className="loading-container">Loading products...</div>
  if (error) return <div className="error-message">{error}</div>

  return (
    <div className="products-page">
      <div className="products-header">
        <h1>Shop Products</h1>
        <p>{filteredProducts.length} results</p>
      </div>

      <div className="products-container">
        <aside className="filters-sidebar">
          <div className="filter-group">
            <h3>Filter</h3>
            <a href="#" className="advanced-filter">Advanced</a>
          </div>

          <div className="filter-group">
            <h4>Category</h4>
            <select
              value={filters.category}
              onChange={(e) => setFilters({ ...filters, category: e.target.value })}
              className="filter-select"
            >
              <option value="">All Categories</option>
              {categories.map(cat => (
                <option key={cat.id} value={cat.id}>{cat.name}</option>
              ))}
            </select>
          </div>

          <div className="filter-group">
            <h4>Price Range</h4>
            <div className="price-range">
              <input type="range" min="0" max="500" value={filters.priceRange[1]} onChange={handlePriceChange} className="range-slider" />
              <div className="price-display">
                <span>0 SAR</span>
                <span>{filters.priceRange[1]} SAR</span>
              </div>
            </div>
          </div>

          <div className="filter-group">
            <h4>Size</h4>
            <div className="size-options">
              {['XS', 'S', 'M', 'L', 'XL', 'XXL'].map(size => (
                <button
                  key={size}
                  className={`size-btn ${filters.size === size ? 'active' : ''}`}
                  onClick={() => setFilters({ ...filters, size: filters.size === size ? '' : size })}
                >
                  {size}
                </button>
              ))}
            </div>
          </div>

          <div className="filter-group">
            <h4>Brand</h4>
            <input
              type="text"
              placeholder="Search brand..."
              value={filters.brand}
              onChange={(e) => setFilters({ ...filters, brand: e.target.value })}
              className="filter-input"
            />
          </div>
        </aside>

        <section className="products-main">
          <div className="products-controls">
            <div className="view-options">
              <button className="view-btn active">...</button>
              <button className="view-btn">...</button>
            </div>
            <div className="sort-options">
              <label>Sort by:</label>
              <select value={sortBy} onChange={(e) => setSortBy(e.target.value)} className="sort-select">
                <option value="popular">Popular</option>
                <option value="newest">Newest</option>
                <option value="price-asc">Price: Low to High</option>
                <option value="price-desc">Price: High to Low</option>
              </select>
            </div>
          </div>

          <div className="products-grid">
            {filteredProducts.length > 0 ? (
              filteredProducts.map(product => (
                <Link to={`/products/${product.id}`} key={product.id} className="product-card">
                  <div className="product-image">
                    <img
                      src={product.image || 'https://images.pexels.com/photos/2769274/pexels-photo-2769274.jpeg?auto=compress&cs=tinysrgb&w=600'}
                      alt={product.name}
                    />
                    {product.isNew && <span className="badge">New Arrival</span>}
                  </div>
                  <div className="product-info">
                    <h3>{product.name}</h3>
                    <p className="brand">{product.brand || 'Fashion Mashup'}</p>
                    <p className="price">SAR {product.price.toFixed(2)}</p>
                    <button className="wishlist-btn">&#9825;</button>
                  </div>
                </Link>
              ))
            ) : (
              <p className="no-products">No products found matching your filters</p>
            )}
          </div>
        </section>
      </div>
    </div>
  )
}

export default Products
