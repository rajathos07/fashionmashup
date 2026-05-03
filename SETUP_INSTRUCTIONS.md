# Fashion Mashup - Complete Setup Instructions

## Project Architecture
- **Frontend**: React application (Vite) on port 5173
- **Backend**: Java Servlet REST API on Tomcat at `http://localhost:8080/fashionmashup`
- **Database**: MySQL with schema defined in `database_init.sql`

## Required Prerequisites
1. **MySQL Server** - installed and running on localhost:3306
2. **Java 21** - for backend compilation
3. **Tomcat 10+** - for backend deployment
4. **Eclipse IDE** - or Maven command line
5. **Node.js + npm** - for frontend

## Step-by-Step Setup

### Step 1: Set Up MySQL Database
1. Open MySQL Workbench
2. Connect to your MySQL instance
3. Create a new SQL script and paste the contents of `database_init.sql`
4. Execute the script to create:
   - Database: `fashion_mashup`
   - Tables: categories, products, product_sizes, users, cart, cart_items, orders, order_items
   - Seed data with 15 products and online Pexels image URLs

**Database Credentials** (used in backend):
- Host: `localhost:3306`
- Database: `fashion_mashup`
- Username: `root`
- Password: `newpassword123`

### Step 2: Build Backend Application
The backend uses Maven. In Eclipse:
1. Right-click project → Maven → Update Project
2. Right-click project → Run As → Maven Build
3. Configure build goal: `clean package`
4. This creates `target/fashionmashup.war`

### Step 3: Deploy to Tomcat
1. In Eclipse, ensure Tomcat 10 is configured (Window → Preferences → Server Runtime Environments)
2. Right-click project → Run As → Run on Server
3. Select Tomcat 10 server
4. Choose to deploy on Tomcat

Verify deployment:
- Open browser: `http://localhost:8080/fashionmashup/api/categories`
- Should return JSON: `[{"id":1,"name":"Men's Fashion",...}]`

### Step 4: Run Frontend Development Server
From `/frontend` directory:
```bash
npm install   # if not already done
npm run dev   # runs on http://localhost:5173
```

### Step 5: Access Application
Open browser: `http://localhost:5173`

## API Endpoints (Backend)
All endpoints require session authentication except login/register:

- `GET /api/categories` - List all categories
- `GET /api/products` - List all products
- `GET /api/products?categoryId=1` - Filter by category
- `GET /api/products/{id}` - Product details
- `POST /api/login` - Login user
- `POST /api/register` - Register new user
- `GET /api/logout` - Logout user
- `GET /api/user` - Current user info
- `GET /api/cart/*` - Get cart items
- `POST /api/addToCart` - Add item to cart
- `PUT /api/cart/{cartItemId}` - Update cart item quantity
- `POST /api/removeCart` - Remove item from cart
- `POST /api/placeOrder` - Create order
- `GET /api/orders` - List user orders

## Debugging

### If API returns 404:
1. Verify Tomcat is running: Check Eclipse console
2. Verify application deployed: Check `http://localhost:8080/manager` (if Tomcat manager is available)
3. Check backend logs in Eclipse console
4. Verify CORS headers are set correctly

### If database connection fails:
1. Verify MySQL is running: `mysql -u root -p -h localhost`
2. Verify database exists: `SHOW DATABASES;`
3. Check DBConnection.java credentials match your MySQL setup
4. Check MySQL connector JAR is in dependencies (pom.xml)

### If frontend requests fail:
1. Verify proxy in `frontend/vite.config.js` points to correct backend URL
2. Check browser DevTools Network tab for actual request URL
3. Verify CORS headers in CorsFilter.java allow localhost origins

## Database Schema
See `database_init.sql` for complete schema with:
- Categories (men's, women's, accessories)
- 15 Products with Pexels image URLs
- Product sizes and stock quantities
- Test user: `test@fashion.com` / `password123`

## Notes
- All product images use online Pexels URLs (no local file uploads needed)
- Database includes test data for immediate testing
- CORS is configured to allow localhost development
- Session-based authentication (not token-based)
