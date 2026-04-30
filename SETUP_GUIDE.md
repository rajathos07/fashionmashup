# Fashion Mashup - Complete Setup Guide

This guide covers the setup and running of the Fashion Mashup e-commerce application, which consists of a Java/Maven backend and a React/Vite frontend.

## Project Architecture

```
Backend (Java/Tomcat)
├── Controllers - Handle HTTP requests
├── DAOs - Database access layer
├── Models - Business entities
└── Utils - Database connection

Frontend (React/Vite)
├── Pages - Full page components
├── Components - Reusable UI components
├── Services - API integration
└── Styles - CSS styling
```

## Prerequisites

- **Java JDK 21 or higher**
- **Apache Maven 3.8 or higher**
- **Node.js 16 or higher**
- **npm 8 or higher**
- **Apache Tomcat 10 or higher**
- **MySQL 8 or higher**

## Backend Setup (Java)

### 1. Database Setup

Create a MySQL database for Fashion Mashup:

```sql
CREATE DATABASE fashionmashup;
USE fashionmashup;

-- Tables will be created by the Java application via DAOs
```

Update database connection in `src/main/java/com/fashionmashup/util/DBConnection.java`:

```java
private static final String URL = "jdbc:mysql://localhost:3306/fashionmashup";
private static final String USER = "root";
private static final String PASSWORD = "your_password";
```

### 2. Build the Backend

Navigate to the project root:

```bash
# Compile and package the Maven project
mvn clean package
```

This will create a WAR file in the `target/` directory: `target/fashionmashup.war`

### 3. Deploy to Tomcat

1. Copy the WAR file to Tomcat's webapps directory:
```bash
cp target/fashionmashup.war /path/to/tomcat/webapps/
```

2. Start Tomcat:
```bash
# On Linux/Mac
/path/to/tomcat/bin/startup.sh

# On Windows
C:\path\to\tomcat\bin\startup.bat
```

3. The application will be available at: `http://localhost:8080/fashionmashup`

### Backend API Endpoints

The Java backend provides the following REST-like endpoints:

- **Authentication**
  - `POST /fashionmashup/login` - User login
  - `POST /fashionmashup/register` - User registration
  - `POST /fashionmashup/logout` - User logout

- **Products**
  - `GET /fashionmashup/products` - List all products
  - `GET /fashionmashup/products/{id}` - Get product details

- **Categories**
  - `GET /fashionmashup/categories` - List all categories

- **Cart**
  - `GET /fashionmashup/cart` - Get user's cart
  - `POST /fashionmashup/addToCart` - Add item to cart
  - `POST /fashionmashup/removeCart` - Remove item from cart
  - `PUT /fashionmashup/cart/{id}` - Update cart item quantity

- **Orders**
  - `POST /fashionmashup/placeOrder` - Place an order
  - `GET /fashionmashup/orders` - Get user's orders

## Frontend Setup (React)

### 1. Install Dependencies

Navigate to the frontend directory:

```bash
cd frontend
npm install
```

### 2. Configure Backend URL

The frontend is pre-configured to connect to the Java backend at `http://localhost:8080/fashionmashup`.

If you need to change this, update `frontend/vite.config.js`:

```javascript
server: {
  proxy: {
    '/api': {
      target: 'http://localhost:8080/fashionmashup',
      changeOrigin: true,
      pathRewrite: { '^/api': '' }
    }
  }
}
```

### 3. Run Development Server

```bash
npm run dev
```

The application will be available at: `http://localhost:5173`

### 4. Build for Production

```bash
npm run build
```

Production files will be created in the `dist/` directory.

## Running the Full Application

### Option 1: Development Mode

**Terminal 1 - Start Java Backend:**
```bash
# Ensure Tomcat is running and deployed
# Access backend at http://localhost:8080/fashionmashup
```

**Terminal 2 - Start React Frontend:**
```bash
cd frontend
npm run dev
# Access frontend at http://localhost:5173
```

### Option 2: Production Mode

1. Build and deploy Java backend to Tomcat
2. Build React frontend for production:
```bash
cd frontend
npm run build
```
3. Serve the `dist/` folder using a web server (Nginx, Apache, etc.)

## Testing the Application

### 1. Register a New User
- Navigate to `http://localhost:5173/register`
- Fill in name, email, and password
- Click "Register"

### 2. Login
- Navigate to `http://localhost:5173/login`
- Use your registered email and password
- Click "Login"

### 3. Browse Products
- After login, go to "Shop"
- Use filters to find products
- Click on a product to see details

### 4. Add to Cart
- Click "Add to Cart" on product details
- Select size and quantity
- Confirm addition

### 5. Checkout
- Navigate to cart
- Review items and total
- Proceed to checkout
- Fill in shipping and payment details
- Place order

### 6. View Orders
- After successful order, click "View Orders"
- See order history and status

## Project Structure

```
project/
├── src/
│   └── main/
│       ├── java/com/fashionmashup/
│       │   ├── controller/    (Request handlers)
│       │   ├── dao/           (Database access)
│       │   ├── model/         (Business entities)
│       │   └── util/          (Utilities)
│       └── webapp/
│           └── WEB-INF/       (Configuration)
├── frontend/                   (React application)
│   ├── src/
│   │   ├── components/
│   │   ├── pages/
│   │   ├── services/
│   │   └── App.jsx
│   ├── package.json
│   └── vite.config.js
├── pom.xml                     (Maven configuration)
└── README.md

```

## Troubleshooting

### Frontend can't connect to backend
- Ensure Tomcat is running: `http://localhost:8080/fashionmashup`
- Check proxy configuration in `vite.config.js`
- Check browser console for CORS errors
- Ensure backend URL is correct

### Database connection errors
- Check MySQL is running
- Verify database credentials in `DBConnection.java`
- Ensure database `fashionmashup` exists
- Check database tables are created

### Port already in use
- Frontend (5173): `lsof -i :5173` then kill the process
- Tomcat (8080): Change Tomcat port in `conf/server.xml`

### Build errors
- Clear Maven cache: `mvn clean`
- Rebuild: `mvn package`
- Check Java version: `java -version`

## Key Features

- **User Authentication**: Secure login/registration
- **Product Catalog**: Browse products with filters
- **Shopping Cart**: Add/remove items, manage quantities
- **Checkout Flow**: Complete order with shipping and payment details
- **Order Management**: View order history and status
- **Responsive Design**: Works on all devices
- **Modern UI**: Clean, professional design inspired by Stella e-commerce

## Important Notes

- The application uses session-based authentication
- All sensitive operations require user login
- Cart data is stored in session
- Database connection pooling is recommended for production
- HTTPS should be enabled for production deployment

## Support

For issues or questions, refer to:
- Backend: Check server logs in Tomcat `logs/` directory
- Frontend: Check browser console (F12)
- Database: Check MySQL logs

## Next Steps

1. Deploy backend to production Tomcat server
2. Build frontend for production deployment
3. Configure HTTPS/SSL certificates
4. Setup database backups
5. Monitor application performance
