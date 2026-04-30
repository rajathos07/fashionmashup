# Quick Start Guide - Fashion Mashup

Get the Fashion Mashup application running in 5 minutes.

## Prerequisites

- Java JDK 21+
- Maven 3.8+
- Node.js 16+
- MySQL 8+
- Tomcat 10+

## Step 1: Setup Database

```bash
mysql -u root -p
```

```sql
CREATE DATABASE fashionmashup;
```

Update credentials in `src/main/java/com/fashionmashup/util/DBConnection.java`

## Step 2: Build Backend

```bash
# From project root
mvn clean package
```

Copy the WAR file to Tomcat:
```bash
cp target/fashionmashup.war /path/to/tomcat/webapps/
```

Start Tomcat:
```bash
/path/to/tomcat/bin/startup.sh
```

## Step 3: Start Frontend

```bash
cd frontend
npm install
npm run dev
```

## Step 4: Access Application

- Frontend: http://localhost:5173
- Backend API: http://localhost:8080/fashionmashup

## Test Flow

1. **Register** at `/register`
2. **Login** with your credentials
3. **Browse Products** at `/products`
4. **Add Items** to cart
5. **Checkout** and place order
6. **View Orders** in order history

## File Structure

```
project/
├── src/                    # Java backend source
├── frontend/              # React frontend
│   ├── src/
│   ├── package.json
│   └── vite.config.js
├── pom.xml               # Maven config
├── SETUP_GUIDE.md        # Detailed setup
└── QUICK_START.md        # This file
```

## Frontend Pages

| Route | Purpose |
|-------|---------|
| `/` | Home page |
| `/products` | Product listing |
| `/products/{id}` | Product details |
| `/login` | Login page |
| `/register` | Registration page |
| `/cart` | Shopping cart |
| `/checkout` | Checkout form |
| `/orders` | Order history |
| `/order-success` | Order confirmation |

## Backend Endpoints

| Method | Endpoint | Purpose |
|--------|----------|---------|
| POST | `/login` | User login |
| POST | `/register` | User registration |
| GET | `/products` | List products |
| GET | `/products/{id}` | Get product details |
| POST | `/addToCart` | Add to cart |
| GET | `/cart` | View cart |
| POST | `/placeOrder` | Place order |
| GET | `/orders` | View orders |

## Common Issues

**Port 5173 in use:**
```bash
lsof -i :5173
kill -9 <PID>
```

**Backend connection error:**
- Check Tomcat is running
- Verify proxy in `frontend/vite.config.js`

**Database error:**
- Verify MySQL is running
- Check credentials in `DBConnection.java`
- Ensure database exists

## Next Steps

See `SETUP_GUIDE.md` for detailed configuration and deployment options.

---

**Everything ready?** Open http://localhost:5173 in your browser!
