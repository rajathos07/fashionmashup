# Fashion Mashup - Quick Start Guide

Follow these steps in order to get the application running.

## Prerequisites Check
- MySQL 5.7+ or 8.0 installed and running
- Java 21 or higher installed
- Tomcat 10+ configured in Eclipse
- Node.js 18+ installed
- Git (optional)

## Step 1: Initialize Database (5 minutes)

1. Open **MySQL Workbench**
2. Connect to your MySQL instance
3. Create new SQL script: File → New Query Tab
4. Copy and paste the entire contents of **`database_init.sql`**
5. Click Execute (Ctrl+Enter)
6. Verify success: Should see "Rows affected" messages
7. Check database exists:
   ```sql
   SELECT * FROM fashion_mashup.categories;
   -- Should show 3 categories
   ```

**Database Credentials**:
- Username: `root`
- Password: `newpassword123` (update in `DBConnection.java` if different)
- Database: `fashion_mashup`
- Host: `localhost:3306`

## Step 2: Build Backend (5 minutes)

1. In **Eclipse**, right-click project → Maven → Update Project
2. Wait for Maven to download dependencies
3. Right-click project → Run As → Run on Server
4. Select Tomcat 10 server
5. Click Finish
6. Wait for "Server startup" message in Console
7. Verify deployment: Open browser → `http://localhost:8080/fashionmashup/api/categories`
8. Should see JSON response:
   ```json
   [{"id":1,"name":"Men's Fashion",...},...]
   ```

**Expected Console Output**:
```
✅ Database Connected Successfully!
INFO: Server startup in XXX ms
```

## Step 3: Start Frontend Dev Server (3 minutes)

1. Open terminal/PowerShell in project directory
2. Navigate to frontend folder:
   ```bash
   cd frontend
   npm install   # if not done before
   npm run dev
   ```
3. Console will show:
   ```
   Local:   http://localhost:5173/
   ```
4. Open browser → `http://localhost:5173`
5. Application should load

## Step 4: Test Full User Flow (10 minutes)

### Test 1: Browse Products
1. Home page loads with featured products and Pexels images
2. Click category link (e.g., "Men's Fashion")
3. Products page shows filtered products
4. Click product → Product details page loads

### Test 2: Login
1. Click Login button
2. Enter:
   - Email: `test@fashion.com`
   - Password: `password123`
3. Click Submit
4. Should redirect to Home page (logged in state)

### Test 3: Add to Cart
1. Click on any product
2. Select size from dropdown
3. Change quantity if needed
4. Click "Add to Cart"
5. Should see confirmation

### Test 4: View Cart
1. Click Cart button in navbar
2. Should see items added earlier
3. Can update quantity or remove items

### Test 5: Checkout
1. From Cart page, click "Proceed to Checkout"
2. Checkout page loads with order summary
3. Enter shipping address (pre-filled with user data)
4. Click "Place Order"
5. Success page appears with Order ID

### Test 6: View Orders
1. Click "My Orders" in navbar
2. Should see all orders placed
3. Click on order to see details

### Test 7: Logout
1. Click account menu
2. Click Logout
3. Should redirect to Home (logged out)

## Troubleshooting Quick Links

- **API returning 404**: See [TROUBLESHOOTING.md - Issue: API Returns 404 Errors](TROUBLESHOOTING.md#issue-api-returns-404-errors)
- **Database connection failed**: See [TROUBLESHOOTING.md - Issue: Database Connection Failed](TROUBLESHOOTING.md#issue-database-connection-failed)
- **CORS errors**: See [TROUBLESHOOTING.md - Issue: CORS Errors](TROUBLESHOOTING.md#issue-cors-errors-browser-console-shows-cors-blocked)
- **Images not loading**: See [TROUBLESHOOTING.md - Issue: Products Show No Images](TROUBLESHOOTING.md#issue-products-show-no-images-or-default-image)
- **Login not working**: See [TROUBLESHOOTING.md - Issue: Login/Register Not Working](TROUBLESHOOTING.md#issue-loginregister-not-working)

## API Endpoint Testing (Optional)

### Using curl (Windows PowerShell)
```powershell
# Get categories
curl "http://localhost:8080/fashionmashup/api/categories"

# Get all products
curl "http://localhost:8080/fashionmashup/api/products"

# Get product by ID
curl "http://localhost:8080/fashionmashup/api/products/1"

# Filter by category
curl "http://localhost:8080/fashionmashup/api/products?categoryId=1"
```

### Using curl (Mac/Linux)
```bash
# Same commands as above
curl "http://localhost:8080/fashionmashup/api/categories"
```

### Using Postman (Advanced)
1. Import collection from endpoints listed in `SETUP_INSTRUCTIONS.md`
2. Create request with auth cookie from login endpoint
3. Test each endpoint

## Project Structure

```
project/
├── frontend/                 # React app (Vite)
│   ├── src/
│   │   ├── pages/           # Page components
│   │   ├── components/      # Shared components
│   │   ├── services/        # API service layer
│   │   └── main.jsx         # Entry point
│   ├── vite.config.js       # Proxy to backend
│   └── package.json
│
├── src/main/java/           # Backend Java source
│   └── com/fashionmashup/
│       ├── api/             # REST API servlets
│       ├── dao/             # Data access objects
│       ├── model/           # Entity models
│       └── util/            # Utilities
│
├── database_init.sql        # Database schema & seed data
├── pom.xml                  # Maven configuration
└── SETUP_INSTRUCTIONS.md    # Full setup guide
```

## Key Technologies

- **Frontend**: React 18 + Vite + React Router
- **Backend**: Jakarta Servlet 6.0 + MySQL JDBC
- **Database**: MySQL 8.0
- **Server**: Tomcat 10+
- **Build**: Maven
- **Images**: Pexels (online URLs)

## Default Test Account

- **Email**: `test@fashion.com`
- **Password**: `password123`
- **Name**: Test User

## Performance Tips

- Frontend loads in ~2 seconds
- Backend responses should be <200ms
- Database queries are optimized with foreign keys
- Images lazy-load from Pexels CDN

## Common Commands

```bash
# Frontend
cd frontend
npm install         # Install dependencies
npm run dev        # Run dev server (port 5173)
npm run build      # Build for production
npm run build --mode production

# Database (MySQL)
mysql -u root -p   # Connect to MySQL
SHOW DATABASES;    # List databases
USE fashion_mashup; # Select database
SHOW TABLES;       # List tables
SELECT * FROM products; # View all products

# Backend (from Eclipse)
Right-click → Maven → Clean
Right-click → Maven → Update Project
Right-click → Run As → Run on Server
```

## Next Steps

After verifying everything works:
1. Register new user accounts
2. Add own products to database
3. Customize styling in `frontend/src/`
4. Deploy to production (see SETUP_INSTRUCTIONS.md)

---

**Total Setup Time**: ~15-20 minutes

For detailed information, see:
- `SETUP_INSTRUCTIONS.md` - Complete setup guide
- `TROUBLESHOOTING.md` - Problem solving guide
