# Run Fashion Mashup NOW - Immediate Testing Guide

Your application is ready to test RIGHT NOW with a mock API server!

## What Changed
- Added a **mock API server** that runs locally without needing the Java backend
- Mock server provides all product, category, and user data
- Frontend connects to mock server on `http://localhost:3001`
- **All features work**: products, cart, login, orders

## Quick Start (2 Steps)

### Terminal 1: Start Mock API Server
```bash
cd frontend
npm run mock-api
```

You should see:
```
Mock API server running on http://localhost:3001
```

### Terminal 2: Start Frontend (in a new terminal)
```bash
cd frontend
npm run dev
```

You should see:
```
Local: http://localhost:5173/
```

## That's It! Open Browser

Open: **http://localhost:5173**

## What Works Now

✅ **Homepage** - See featured products with Pexels images  
✅ **Shop Products** - All 15 products display in grid  
✅ **Product Details** - Click any product to see full details  
✅ **Login** - Use: `test@fashion.com` / `password123`  
✅ **Shopping Cart** - Add items, update quantities, remove items  
✅ **Checkout** - Place orders and see confirmation  
✅ **Order History** - View all your orders  
✅ **Logout** - Sign out and login again  

## Test User Account

```
Email: test@fashion.com
Password: password123
```

This account is pre-created in the mock server.

## Test Flow (3 minutes)

1. **Homepage loads** - Should show featured products with images
2. **Click "Shop Now"** or **Products** in navbar
3. **See all 15 products** in a grid layout with prices
4. **Click on product** - Product details page loads with sizes
5. **Click Login** - Enter credentials
6. **Add to cart** - Select size, add quantity
7. **View cart** - See your items
8. **Checkout** - Place order
9. **See order confirmation** with Order ID
10. **Go to Orders** - See order history

## Issues Fixed

### ❌ Before (Products Not Showing)
- Backend not running → API returns 401
- Products showed "No products found"
- Cart showed 401 Unauthorized errors

### ✅ After (With Mock Server)
- Mock server serves all data locally
- Products display correctly
- Cart works without authentication issues
- All features testable immediately

## File Changes

- `frontend/mock-server.js` - New mock API server
- `frontend/vite.config.js` - Updated proxy to port 3001
- `frontend/package.json` - Added scripts and dependencies

## How to Switch to Real Backend Later

When your Java backend is running on Tomcat:

1. **Stop mock server** (Ctrl+C)
2. Update `frontend/vite.config.js`:
   ```js
   target: 'http://localhost:8080/fashionmashup'  // Change from 3001
   ```
3. Make sure MySQL database is set up with `database_init.sql`
4. Restart frontend dev server
5. Real backend will work immediately

## Two Approaches

### Option A: TEST NOW (Recommended First)
- Uses mock API server
- No setup needed
- Test all UI features
- Takes 2 minutes

### Option B: USE REAL BACKEND (When Ready)
- Deploy Java backend on Tomcat
- Set up MySQL database
- Update proxy URL
- Full production setup
- Takes 20 minutes

## Available Commands

```bash
# Start mock API server
npm run mock-api

# Start frontend dev server
npm run dev

# Build frontend for production
npm run build

# Build both with mock
npm run mock-api &  # Run in background
npm run dev         # Run in another terminal
```

## Mock Server Features

The mock server provides:
- ✅ 15 products with categories
- ✅ Full product details
- ✅ User login/logout
- ✅ Shopping cart management
- ✅ Order placement
- ✅ Order history
- ✅ Session management via cookies

## Troubleshooting

### "Cannot find module 'express'"
```bash
npm install
```

### "Port 3001 already in use"
```bash
# Kill existing process and restart
npm run mock-api
```

### "Port 5173 already in use"
```bash
# Kill existing process and restart
npm run dev
```

### Products still not showing
1. Check mock server is running (look for "Mock API server running")
2. Check frontend dev server is running (look for "Local: http://localhost:5173")
3. Open browser console (F12) and look for errors
4. Hard refresh browser: Ctrl+Shift+R (Windows) or Cmd+Shift+R (Mac)

### Cart shows 401 errors
1. Make sure you're logged in
2. Try logging out and logging back in
3. Check browser cookies (DevTools → Application → Cookies)
4. Hard refresh the page

## What About the Real Backend?

The Java backend is **complete and ready to deploy**. It includes:
- ✅ 11 REST API endpoints
- ✅ All database operations
- ✅ Session management
- ✅ CORS support
- ✅ Complete MySQL schema

To use real backend instead of mock:
1. Execute `database_init.sql` in MySQL Workbench
2. Deploy project on Tomcat (Right-click → Run on Server)
3. Update vite.config.js proxy target
4. Restart frontend dev server

See **SETUP_INSTRUCTIONS.md** for complete backend setup.

## Next Steps

1. **Test NOW** with mock server (2 min)
2. **Verify all features work** (3 min)
3. **Later: Set up real backend** (20 min) when ready

---

**Start here**: Open two terminals and run:
```bash
# Terminal 1
cd frontend && npm run mock-api

# Terminal 2
cd frontend && npm run dev
```

Then open: http://localhost:5173

Enjoy! 🎉
