# How to Setup Fashion Mashup in Eclipse - Two Methods

Complete step-by-step instructions for setting up this Maven project in Eclipse.

---

## METHOD 1: Clone from GitHub (Recommended)

Use this if the project is already on GitHub.

### Step 1: Install Git in Eclipse

1. Open Eclipse
2. Go to **Help → Eclipse Marketplace**
3. Search: `EGit`
4. Click **Install**
5. Accept license → Click **Finish**
6. Restart Eclipse when prompted

### Step 2: Clone the Repository

1. Go to **File → Import**
2. Expand **Git** → Select **Projects from Git** → Click **Next**
3. Select **Clone URI** → Click **Next**
4. Enter your GitHub repo details:

```
URI:        https://github.com/yourusername/fashion-mashup.git
Host:       github.com
Path:       /yourusername/fashion-mashup.git
User:       your-github-username
Password:   your-github-password-or-token
```

5. Click **Next**
6. Select branch: **main** (check the checkbox)
7. Click **Next**
8. Set local destination:

```
Windows: C:\Users\YourName\git\fashion-mashup
Mac:     /Users/YourName/git/fashion-mashup
```

9. Click **Next**
10. Wait for clone to finish (progress bar at bottom right)

### Step 3: Import as Maven Project

After cloning completes, Eclipse shows "Select a wizard" dialog:

1. Select **Import existing Eclipse projects** → Click **Next**

   **OR** if that dialog closed:

1. Go to **File → Import**
2. Expand **Maven** → Select **Existing Maven Projects** → Click **Next**
3. Click **Browse**
4. Navigate to: `C:\Users\YourName\git\fashion-mashup`
5. Click **Open**
6. You should see `pom.xml` listed with a checkbox
7. Check the checkbox next to `pom.xml`
8. Click **Finish**
9. **WAIT** - Maven downloads dependencies (5-10 minutes)
   - Watch progress in bottom right corner
   - Console shows "Downloading..." messages
   - Do NOT interrupt

### Step 4: Fix Project Errors (If Any)

If you see red X marks on the project:

1. Right-click project → **Maven → Update Project**
2. Check **Force Update of Snapshots/Releases**
3. Click **OK**
4. Wait for update to complete

If still showing errors:

1. Right-click project → **Properties**
2. Go to **Project Facets**
3. Check **Dynamic Web Module** (version 6.0)
4. Check **Java** (version 21)
5. Click **Apply and Close**

### Step 5: Verify Project Structure

In Project Explorer (left panel), expand the project:

```
fashionmashup
├── src/main/java/com/fashionmashup/
│   ├── controller/       ← Servlet controllers
│   ├── dao/              ← Database interfaces
│   ├── dao/impl/         ← Database implementations
│   ├── model/            ← Entity classes
│   └── util/             ← DB connection utility
├── src/main/webapp/
│   ├── WEB-INF/
│   │   ├── web.xml       ← Servlet mapping
│   │   └── views/        ← JSP files
│   └── assets/           ← CSS, images
├── src/test/java/        ← Test files
├── target/               ← Build output (after build)
├── pom.xml               ← Maven configuration
└── frontend/             ← React frontend (separate)
```

---

## METHOD 2: Create New Maven Project in Eclipse

Use this if you want to start fresh or recreate the project manually.

### Step 1: Create New Maven Project

1. Go to **File → New → Other**
2. Expand **Maven** → Select **Maven Project** → Click **Next**
3. In "Select project location" screen:
   - Check **Use default Workspace location**
   - Or set custom location
4. Click **Next**
5. In "Select an Archetype" screen:
   - Group Id: `org.apache.maven.archetypes`
   - Artifact Id: Select **maven-archetype-webapp**
   - Version: `1.4` (or latest)
6. Click **Next**
7. In "Enter group id" screen, fill in:

```
Group Id:      com.fashionmashup
Artifact Id:   fashionmashup
Version:       0.0.1-SNAPSHOT
Package:       com.fashionmashup
```

8. Click **Finish**
9. Eclipse creates the Maven project structure

### Step 2: Fix Project Structure

The archetype creates a basic structure. You need to add missing folders:

1. Right-click project → **New → Source Folder**
2. Create: `src/main/java`
3. Click **Finish**

4. Right-click `src/main/java` → **New → Package**
5. Name: `com.fashionmashup.controller`
6. Click **Finish**

7. Repeat for each package:
   - `com.fashionmashup.dao`
   - `com.fashionmashup.dao.impl`
   - `com.fashionmashup.model`
   - `com.fashionmashup.util`

### Step 3: Update pom.xml

1. Open `pom.xml` in Eclipse
2. Replace ALL content with:

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="https://maven.apache.org/xsd/maven-4.0.0.xsd">

    <modelVersion>4.0.0</modelVersion>

    <groupId>com.fashionmashup</groupId>
    <artifactId>fashionmashup</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <packaging>war</packaging>

    <name>fashionmashup</name>

    <properties>
        <maven.compiler.source>21</maven.compiler.source>
        <maven.compiler.target>21</maven.compiler.target>
    </properties>

    <dependencies>
        <dependency>
            <groupId>jakarta.servlet</groupId>
            <artifactId>jakarta.servlet-api</artifactId>
            <version>6.0.0</version>
            <scope>provided</scope>
        </dependency>

        <dependency>
            <groupId>com.mysql</groupId>
            <artifactId>mysql-connector-j</artifactId>
            <version>8.3.0</version>
        </dependency>
    </dependencies>

    <build>
        <finalName>fashionmashup</finalName>
    </build>

</project>
```

3. Save: **Ctrl+S**
4. Right-click project → **Maven → Update Project**
5. Click **OK**

### Step 4: Add Java Source Files

Copy each Java file from the GitHub repository into the correct package:

**Controllers** (right-click `com.fashionmashup.controller` → New → Class):
```
AddToCartController.java
CartController.java
CheckoutController.java
HomeController.java
LoginController.java
LogoutController.java
OrdersController.java
PlaceOrderController.java
ProductController.java
ProductDetailsController.java
RegisterController.java
RemoveCartController.java
```

**DAOs** (right-click `com.fashionmashup.dao` → New → Interface):
```
CartDAO.java
CartItemDAO.java
CategoryDAO.java
OrderDAO.java
OrderItemDAO.java
ProductDAO.java
ProductSizeDAO.java
UserDAO.java
```

**DAO Implementations** (right-click `com.fashionmashup.dao.impl` → New → Class):
```
CartDAOImpl.java
CartItemDAOImpl.java
CategoryDAOImpl.java
OrderDAOImpl.java
OrderItemDAOImpl.java
ProductDAOImpl.java
ProductSizeDAOImpl.java
UserDAOImpl.java
```

**Models** (right-click `com.fashionmashup.model` → New → Class):
```
Cart.java
CartItem.java
Category.java
Order.java
OrderItem.java
Product.java
ProductSize.java
User.java
```

**Utilities** (right-click `com.fashionmashup.util` → New → Class):
```
DBConnection.java
TestConnection.java
TestDAO.java
```

### Step 5: Add Web Files

1. Navigate to `src/main/webapp/`
2. Replace `web.xml` in `WEB-INF/` with the project's web.xml
3. Add JSP files in `WEB-INF/views/`
4. Add CSS files in `assets/css/`
5. Add images in `assets/images/`

### Step 6: Build the Project

1. Right-click project → **Run As → Maven clean**
2. Wait for "BUILD SUCCESS"
3. Right-click project → **Run As → Maven install**
4. Wait for "BUILD SUCCESS"
5. Check `target/` folder - should contain `fashionmashup.war`

---

## Connect Tomcat Server in Eclipse

### Step 1: Add Tomcat Runtime

1. Go to **Window → Preferences**
2. Expand **Server** → Click **Runtime Environments**
3. Click **Add**
4. Select **Apache Tomcat v10.1**
5. Click **Next**
6. Click **Browse** → Select your Tomcat installation folder:

```
Windows: C:\Program Files\Apache\Tomcat10
Mac:     /opt/tomcat10 or ~/tomcat10
Linux:   /opt/tomcat10 or ~/tomcat10
```

7. Click **Finish**
8. Make sure it's checked
9. Click **Apply and Close**

### Step 2: Create Server in Eclipse

1. Go to **Window → Show View → Other**
2. Expand **Server** → Select **Servers** → Click **Open**
3. In the Servers tab (bottom), right-click empty area
4. Select **New → Server**
5. Select **Apache Tomcat v10.1**
6. Click **Next**
7. Click **Finish**

### Step 3: Add Project to Server

1. In Servers tab, right-click your Tomcat server
2. Click **Add and Remove**
3. Select `fashionmashup` from left panel
4. Click **Add** → It moves to right panel
5. Click **Finish**

### Step 4: Start the Server

1. In Servers tab, right-click Tomcat server
2. Click **Start**
3. Watch Console tab for: `Server startup in X seconds`
4. If green icon appears, server is running

### Step 5: Access the Application

1. Open browser
2. Go to: `http://localhost:8080/fashionmashup`
3. You should see the application

---

## Setup Database

### Step 1: Create MySQL Database

Open Command Prompt / Terminal:

```bash
mysql -u root -p
```

Enter your MySQL password, then:

```sql
CREATE DATABASE fashionmashup;
SHOW DATABASES;
exit;
```

### Step 2: Update Database Credentials

1. In Eclipse, open: `src/main/java/com/fashionmashup/util/DBConnection.java`
2. Find these lines:

```java
private static final String URL = "jdbc:mysql://localhost:3306/fashionmashup";
private static final String USER = "root";
private static final String PASSWORD = "your_password_here";
```

3. Change `PASSWORD` to your actual MySQL root password
4. Save: **Ctrl+S**

### Step 3: Test Database Connection

1. Right-click `TestConnection.java`
2. **Run As → Java Application**
3. Console should show: `Connection successful!`

---

## Common Problems and Fixes

### Problem: "No Java 21" Error

```
Fix:
1. Window → Preferences → Java → Installed JREs
2. Click Add → Standard VM → Next
3. Browse to your JDK 21 folder
4. Click Finish
5. Check JDK 21 as default
6. Apply and Close
```

### Problem: Maven Dependencies Not Downloading

```
Fix:
1. Right-click project → Maven → Update Project
2. Check "Force Update of Snapshots/Releases"
3. Click OK
4. Wait 5-10 minutes
5. Check internet connection
```

### Problem: Tomcat Won't Start (Port 8080 in Use)

```
Fix Option 1 - Kill process using port:
  Windows: Open CMD as Admin
    netstat -ano | findstr :8080
    taskkill /PID <number> /F

  Mac/Linux: Open Terminal
    lsof -i :8080
    kill -9 <PID>

Fix Option 2 - Change Tomcat port:
  1. Right-click Tomcat in Servers tab
  2. Click Properties
  3. Change HTTP port from 8080 to 8081
  4. Click OK
  5. Access at: http://localhost:8081/fashionmashup
```

### Problem: 404 Not Found in Browser

```
Fix:
1. Check Tomcat is running (green icon in Servers tab)
2. Right-click project → Run As → Run on Server
3. Select Tomcat → Click Finish
4. Wait for deployment
5. Refresh browser (Ctrl+F5)
```

### Problem: "Dynamic Web Module 3.0" Facet Error

```
Fix:
1. Right-click project → Properties
2. Click Project Facets
3. Uncheck Dynamic Web Module
4. Click Apply
5. Check Dynamic Web Module again (version 6.0)
6. Check Java (version 21)
7. Click Apply and Close
8. Right-click project → Maven → Update Project
```

### Problem: "Cannot find class" or Red X on Project

```
Fix:
1. Right-click project → Maven → Update Project
2. Check "Force Update"
3. Click OK
4. Right-click project → Properties → Java Build Path
5. Check Libraries tab shows JDK 21 and Maven Dependencies
6. Click OK
7. Project → Clean
8. Project → Build All
```

---

## Quick Reference: Eclipse Menu Paths

| Task | Menu Path |
|------|-----------|
| Import project | File → Import → Maven → Existing Maven Projects |
| Clone from Git | File → Import → Git → Projects from Git → Clone URI |
| New Maven project | File → New → Other → Maven → Maven Project |
| Update Maven | Right-click project → Maven → Update Project |
| Clean Maven | Right-click project → Run As → Maven clean |
| Build Maven | Right-click project → Run As → Maven install |
| Add Tomcat | Window → Preferences → Server → Runtime Environments |
| Start Tomcat | Servers tab → Right-click Tomcat → Start |
| Stop Tomcat | Servers tab → Right-click Tomcat → Stop |
| Run on Server | Right-click project → Run As → Run on Server |
| Show Console | Window → Show View → Console |
| Show Servers | Window → Show View → Other → Server → Servers |
| Configure JDK | Window → Preferences → Java → Installed JREs |
| Configure Maven | Window → Preferences → Maven → Installations |

---

## Summary: Which Method to Use?

| Scenario | Method |
|----------|--------|
| Project is on GitHub | **Method 1** - Clone from GitHub |
| Starting from scratch | **Method 2** - Create new Maven project |
| Team project with existing code | **Method 1** - Clone from GitHub |
| Learning/experimenting | **Method 2** - Create new Maven project |
| Production setup | **Method 1** - Clone from GitHub |

**Recommended: Method 1 (Clone from GitHub)** - It's faster and ensures you have all files.
