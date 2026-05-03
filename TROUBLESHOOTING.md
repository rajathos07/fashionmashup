# Fashion Mashup - Troubleshooting Guide

## Common Issues and Solutions

### Issue: API Returns 404 Errors
**Symptoms**: Browser shows 404 for all API calls to `http://localhost:8080/fashionmashup/api/*`

**Solutions (in order)**:
1. **Verify Tomcat is running**
   - Check Eclipse console for startup messages
   - Look for: "INFO: Server startup in XXX ms"
   - If not running: Right-click project → Run As → Run on Server

2. **Verify application is deployed**
   - Check Tomcat deployment directory: Check for `fashionmashup` folder in Tomcat webapps
   - Restart Tomcat if needed: Right-click server in Servers view → Restart

3. **Check backend compilation**
   - Verify no compilation errors in Eclipse Problems view
   - Maven should have compiled all .java files to .class files in target/classes
   - If errors exist, fix them and rebuild

4. **Check servlet mappings**
   - All API servlets use `@WebServlet("/api/...")` annotation
   - Example endpoints that should work:
     - `http://localhost:8080/fashionmashup/api/categories` → returns JSON array
     - `http://localhost:8080/fashionmashup/api/products` → returns JSON array
   
5. **Check context path**
   - Application deploys to `/fashionmashup` context
   - Frontend proxy in `vite.config.js` correctly targets: `http://localhost:8080/fashionmashup`
   - Verify proxy URL doesn't have typos

---

### Issue: Database Connection Failed
**Symptoms**: 
- Console shows: "❌ Connection Failed!"
- All API endpoints return 500 "Server error"
- No data retrieved from backend

**Solutions**:
1. **Verify MySQL is running**
   ```bash
   mysql -u root -p -h localhost
   # Should connect successfully
   ```

2. **Verify database exists**
   ```sql
   SHOW DATABASES;
   -- Should show 'fashion_mashup'
   
   USE fashion_mashup;
   SHOW TABLES;
   -- Should show 8 tables: categories, products, product_sizes, users, cart, cart_items, orders, order_items
   ```

3. **Check database credentials in DBConnection.java**
   ```java
   private static final String URL = "jdbc:mysql://localhost:3306/fashion_mashup?useSSL=false&serverTimezone=UTC";
   private static final String USERNAME = "root";
   private static final String PASSWORD = "newpassword123";
   ```
   - Verify these match your MySQL credentials
   - If password is different, update it and rebuild

4. **Verify MySQL Connector JAR**
   - Check pom.xml includes: `mysql-connector-j:8.3.0`
   - Rebuild project to ensure JAR is downloaded

5. **Test database manually**
   - Run in MySQL Workbench:
   ```sql
   USE fashion_mashup;
   SELECT * FROM products LIMIT 1;
   -- Should return 1 product with image URL from Pexels
   ```

---

### Issue: CORS Errors (Browser Console Shows CORS Blocked)
**Symptoms**:
- Browser console: "Access to XMLHttpRequest at 'http://localhost:8080/fashionmashup/api/products' from origin 'http://localhost:5173' has been blocked by CORS policy"
- Network tab shows preflight OPTIONS request failing

**Solutions**:
1. **Verify CorsFilter is deployed**
   - Class: `CorsFilter.java` at `/api/*`
   - Should handle OPTIONS preflight requests

2. **Check CORS headers**
   - In browser DevTools → Network tab
   - Select a failing request → Response Headers should include:
     ```
     Access-Control-Allow-Origin: http://localhost:5173
     Access-Control-Allow-Methods: GET, POST, PUT, DELETE, OPTIONS
     Access-Control-Allow-Headers: Content-Type, Authorization, X-Client-Info, Apikey
     Access-Control-Allow-Credentials: true
     ```

3. **Verify proxy configuration**
   - File: `frontend/vite.config.js`
   - Should have:
   ```js
   proxy: {
     '/api': {
       target: 'http://localhost:8080/fashionmashup',
       changeOrigin: true
     }
   }
   ```

4. **Clear browser cache**
   - CORS preflight responses are cached
   - Clear cache: DevTools → Application → Clear site data

---

### Issue: Frontend Shows Blank Page or Loading Forever
**Symptoms**:
- Browser loads page but shows no content
- Console shows network errors or infinite loading state

**Solutions**:
1. **Verify frontend dev server is running**
   ```bash
   cd frontend
   npm run dev
   # Should show: "Local: http://localhost:5173/"
   ```

2. **Check frontend build**
   ```bash
   cd frontend
   npm run build
   # Should complete with no errors
   ```

3. **Check React errors in console**
   - Open browser DevTools → Console tab
   - Look for React-specific errors
   - Common issues:
     - Missing API endpoint responses
     - JSON parsing errors
     - Session/auth issues

4. **Verify API endpoints respond**
   - Open new browser tab and test directly:
   - `http://localhost:8080/fashionmashup/api/categories`
   - Should return JSON array with at least 3 categories

---

### Issue: Products Show No Images or Default Image
**Symptoms**:
- All products display default placeholder image
- Pexels images don't load

**Solutions**:
1. **Verify database has image URLs**
   ```sql
   USE fashion_mashup;
   SELECT product_id, product_name, image_url FROM products LIMIT 1;
   -- Image URL should start with: https://images.pexels.com/
   ```

2. **Check image URLs are valid**
   - Copy URL from database
   - Paste in browser - should load image
   - If 404, URL is broken, run database_init.sql again

3. **Clear browser cache**
   - Stale images might be cached
   - Hard refresh: Ctrl+Shift+R (Windows) or Cmd+Shift+R (Mac)

4. **Check ProductApiServlet getDefaultImage()**
   - If DB image URL is empty or null, default image is returned
   - Default images are also from Pexels (should load)

---

### Issue: Login/Register Not Working
**Symptoms**:
- Form submits but nothing happens
- No error message displayed
- Session not created

**Solutions**:
1. **Test with curl** (command line)
   ```bash
   curl -X POST http://localhost:8080/fashionmashup/api/login \
     -H "Content-Type: application/json" \
     -d '{"email":"test@fashion.com","password":"password123"}'
   # Should return: {"id":1,"name":"Test User","email":"test@fashion.com"}
   ```

2. **Verify test user exists in database**
   ```sql
   USE fashion_mashup;
   SELECT * FROM users WHERE email = 'test@fashion.com';
   -- Should return 1 row with password: password123
   ```

3. **Check session handling**
   - Tomcat creates JSESSIONID cookie after login
   - Verify cookie appears in browser DevTools → Application → Cookies
   - Cookie domain should be `localhost`

4. **Verify JSON request format**
   - Frontend sends: `{"email":"...","password":"..."}`
   - JsonBodyParser correctly parses this

---

### Issue: Adding Items to Cart Fails
**Symptoms**:
- Click "Add to Cart" button
- Nothing happens or error message appears

**Solutions**:
1. **Verify user is logged in**
   - Must have active session (from login)
   - API checks: `session.getAttribute("user")`

2. **Test add to cart with curl**
   ```bash
   # First login to get session
   curl -X POST http://localhost:8080/fashionmashup/api/login \
     -H "Content-Type: application/json" \
     -c cookies.txt \
     -d '{"email":"test@fashion.com","password":"password123"}'
   
   # Then add to cart (with session cookie)
   curl -X POST http://localhost:8080/fashionmashup/api/addToCart \
     -H "Content-Type: application/json" \
     -b cookies.txt \
     -d '{"productId":1,"quantity":1,"size":"M"}'
   # Should return: {"message":"Item added to cart"}
   ```

3. **Verify product exists**
   ```sql
   USE fashion_mashup;
   SELECT * FROM products WHERE product_id = 1;
   ```

4. **Check cart was created**
   ```sql
   SELECT * FROM cart WHERE user_id = 1;
   SELECT * FROM cart_items;
   ```

---

### Issue: Placing Order Fails or Data Lost
**Symptoms**:
- Order creation returns error
- Cart items disappear but order not created
- Order shows but items missing

**Solutions**:
1. **Verify cart has items**
   - Call GET `/api/cart` before placing order
   - Should return items array

2. **Check order creation response**
   - Should return: `{"id":ORDER_ID,"message":"Order placed successfully"}`
   - If error, check console for details

3. **Verify order in database**
   ```sql
   USE fashion_mashup;
   SELECT * FROM orders WHERE user_id = 1 ORDER BY order_id DESC LIMIT 1;
   SELECT * FROM order_items WHERE order_id = (SELECT MAX(order_id) FROM orders);
   ```

---

## Emergency Fixes

### Reset Database
```sql
-- Run this to reset to clean state:
DROP DATABASE IF EXISTS fashion_mashup;
-- Then re-run database_init.sql
```

### Clear Tomcat Cache
1. Stop Tomcat
2. Delete: `{TOMCAT_HOME}/work/Catalina/localhost/fashionmashup`
3. Delete: `{TOMCAT_HOME}/webapps/fashionmashup` (if exists)
4. Restart Tomcat - application will redeploy

### Rebuild Everything
1. In Eclipse: Right-click project → Clean
2. Right-click project → Maven → Update Project
3. Right-click project → Run As → Maven Build... → clean package
4. Restart Tomcat

---

## Testing Checklist

- [ ] MySQL running and connected
- [ ] Database `fashion_mashup` exists with all tables
- [ ] Tomcat running and application deployed
- [ ] Browser can access: `http://localhost:8080/fashionmashup/api/categories`
- [ ] Frontend dev server running on `http://localhost:5173`
- [ ] Frontend loads without errors
- [ ] Can navigate between pages
- [ ] Can login with `test@fashion.com / password123`
- [ ] Products load with Pexels images
- [ ] Can add items to cart
- [ ] Can view cart
- [ ] Can place order
- [ ] Can view order history

---

## Debug Logging

To add debug logging to trace issues:

1. **In backend servlet**, add console output:
   ```java
   System.out.println("DEBUG: Incoming request to " + request.getPathInfo());
   System.out.println("DEBUG: Request body: " + body);
   System.out.println("DEBUG: Parsed params: " + params);
   ```

2. **Check Eclipse Console** while making API calls

3. **Check MySQL slow query log** if database queries are slow:
   ```sql
   SET GLOBAL slow_query_log = 'ON';
   SET GLOBAL long_query_time = 0;
   -- Then check: /var/log/mysql/slow.log (Linux) or MySQL data directory (Windows)
   ```
