# Fashion Mashup - React Frontend

A modern, responsive e-commerce frontend for Fashion Mashup built with React and Vite, featuring a Stella-inspired design.

## Features

- Modern, clean UI matching the Stella e-commerce template
- Product browsing with advanced filtering (brand, price, size, color)
- Shopping cart management
- User authentication (login/register)
- Secure checkout flow
- Order management and history
- Fully responsive design for mobile, tablet, and desktop
- Smooth animations and transitions

## Technology Stack

- **React 18** - UI framework
- **Vite** - Build tool and dev server
- **React Router** - Client-side routing
- **Axios** - HTTP client for API calls
- **CSS** - Styling with CSS modules

## Project Structure

```
frontend/
├── src/
│   ├── components/
│   │   ├── Navbar.jsx
│   │   ├── Navbar.css
│   │   ├── Footer.jsx
│   │   └── Footer.css
│   ├── pages/
│   │   ├── Home.jsx
│   │   ├── Home.css
│   │   ├── Products.jsx
│   │   ├── Products.css
│   │   ├── ProductDetails.jsx
│   │   ├── ProductDetails.css
│   │   ├── Cart.jsx
│   │   ├── Cart.css
│   │   ├── Checkout.jsx
│   │   ├── Checkout.css
│   │   ├── Login.jsx
│   │   ├── Register.jsx
│   │   ├── Auth.css
│   │   ├── Orders.jsx
│   │   ├── Orders.css
│   │   ├── OrderSuccess.jsx
│   │   └── OrderSuccess.css
│   ├── services/
│   │   └── api.js
│   ├── App.jsx
│   ├── App.css
│   ├── main.jsx
│   └── index.css
├── index.html
├── vite.config.js
├── package.json
└── README.md
```

## Installation

1. Navigate to the frontend directory:
```bash
cd frontend
```

2. Install dependencies:
```bash
npm install
```

## Development

Run the development server:
```bash
npm run dev
```

The application will be available at `http://localhost:5173`

## Build

Build for production:
```bash
npm run build
```

## Configuration

The frontend is configured to proxy API calls to the Java backend running on `http://localhost:8080`.

Update the `vite.config.js` file if your backend is running on a different port:

```javascript
server: {
  proxy: {
    '/api': {
      target: 'http://localhost:8080', // Change this if needed
      changeOrigin: true,
      pathRewrite: { '^/api': '' }
    }
  }
}
```

## API Integration

All API calls to the Java backend are handled through the `src/services/api.js` service:

- **Authentication**: Login, Register, Logout
- **Products**: List products, Get product details
- **Cart**: Add to cart, Remove from cart, View cart
- **Orders**: Place order, View orders
- **Categories**: Get product categories

## Design Highlights

- Clean, minimalist design inspired by Stella e-commerce
- Consistent color scheme with cyan accent color
- Smooth hover effects and transitions
- Responsive grid layouts
- Mobile-first approach

## Browser Support

- Chrome (latest)
- Firefox (latest)
- Safari (latest)
- Edge (latest)

## Contributing

When adding new features or pages:
1. Create a new component/page in the appropriate directory
2. Create corresponding CSS file with the same name
3. Update routing in `App.jsx`
4. Ensure responsive design on all breakpoints

## License

This project is part of Fashion Mashup e-commerce platform.
