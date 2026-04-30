# Eclipse Setup Guide - Fashion Mashup Project

Complete step-by-step guide to clone, setup, and run Fashion Mashup in Eclipse with Maven and Tomcat.

## Table of Contents
1. [Prerequisites](#prerequisites)
2. [Step 1: Install Required Software](#step-1-install-required-software)
3. [Step 2: Configure Eclipse](#step-2-configure-eclipse)
4. [Step 3: Clone Project from GitHub](#step-3-clone-project-from-github)
5. [Step 4: Import as Maven Project](#step-4-import-as-maven-project)
6. [Step 5: Configure Tomcat Server](#step-5-configure-tomcat-server)
7. [Step 6: Setup Database](#step-6-setup-database)
8. [Step 7: Run the Project](#step-7-run-the-project)
9. [Step 8: Verify Installation](#step-8-verify-installation)
10. [Troubleshooting](#troubleshooting)

---

## Prerequisites

Before starting, ensure you have:
- **Windows 10/11 or Mac/Linux**
- **Administrator access** to your computer
- **Internet connection** (for downloading dependencies)
- **At least 4GB RAM** and **5GB free disk space**

---

## Step 1: Install Required Software

### 1.1 Install Java JDK 21

**For Windows:**
1. Download from: https://www.oracle.com/java/technologies/downloads/#java21
2. Select "Windows x64 Installer"
3. Run the installer
4. Follow the installation wizard (next → next → finish)
5. Default location: `C:\Program Files\Java\jdk-21`

**For Mac:**
1. Download from: https://www.oracle.com/java/technologies/downloads/#java21
2. Select "macOS x64 DMG Installer"
3. Open the DMG file and follow installation wizard

**For Linux:**
```bash
sudo apt update
sudo apt install openjdk-21-jdk
```

**Verify Installation:**
```bash
java -version
javac -version
```

Should show: `java version "21.x.x"`

### 1.2 Install Maven

**For Windows:**
1. Download from: https://maven.apache.org/download.cgi
2. Download "Binary zip archive"
3. Extract to: `C:\Program Files\Apache\maven` (create folder if needed)
4. Add to System Environment Variables:
   - Open "Environment Variables"
   - Click "New" under System variables
   - Variable name: `MAVEN_HOME`
   - Variable value: `C:\Program Files\Apache\maven`
5. Edit "Path" variable:
   - Add: `C:\Program Files\Apache\maven\bin`
6. Restart your computer

**For Mac/Linux:**
```bash
# Using Homebrew (Mac)
brew install maven

# Using apt (Linux)
sudo apt install maven
```

**Verify Installation:**
```bash
mvn -version
```

Should show Maven version and Java version.

### 1.3 Install Eclipse IDE

1. Download from: https://www.eclipse.org/downloads/
2. Select "Eclipse IDE for Enterprise Java and Web Developers"
3. Run the installer
4. Choose installation location
5. Finish installation

**Or Download Pre-packaged:**
- Download `eclipse-jee-2024-03-R-win32-x86_64.zip` (Windows)
- Extract anywhere on your computer
- Run `eclipse.exe`

### 1.4 Install Tomcat 10

1. Download from: https://tomcat.apache.org/download-10.cgi
2. Download "Binary Distributions → zip" (Windows) or "tar.gz" (Mac/Linux)
3. Extract to: `C:\Program Files\Apache\Tomcat10` (Windows) or `/opt/tomcat10` (Mac/Linux)
4. Note the installation path (you'll need it in Eclipse)

**For Windows:**
```
C:\Program Files\Apache\Tomcat10\
```

**For Mac/Linux:**
```
/opt/tomcat10/
or
~/tomcat10/
```

### 1.5 Install MySQL Server

1. Download from: https://dev.mysql.com/downloads/mysql/
2. Choose your operating system
3. Download MySQL Community Server
4. Run installer and follow wizard
5. Set root password (remember it!)
6. Default port: 3306

**Verify Installation:**
```bash
mysql --version
mysql -u root -p
# Enter your password
# Type: exit
```

---

## Step 2: Configure Eclipse

### 2.1 First Launch & Workspace Setup

1. Launch Eclipse
2. Select workspace location: `C:\eclipse-workspace` (or your preferred location)
3. Click "Launch"
4. Wait for Eclipse to fully load

### 2.2 Configure JDK in Eclipse

1. Go to: **Window → Preferences** (Mac: **Eclipse → Preferences**)
2. Expand **Java** in left panel
3. Click **Installed JREs**
4. Click **Add...**
5. Select **Standard VM**
6. Click **Next**
7. Set JRE home:
   - **Windows:** `C:\Program Files\Java\jdk-21`
   - **Mac:** `/Library/Java/JavaVirtualMachines/jdk-21.jdk/Contents/Home`
   - **Linux:** `/usr/lib/jvm/java-21-openjdk-amd64`
8. Click **Finish**
9. Make sure the new JDK 21 is selected (checkbox)
10. Click **Apply and Close**

### 2.3 Configure Maven in Eclipse

1. Go to: **Window → Preferences**
2. Expand **Maven** in left panel
3. Click **Installations**
4. Click **Add...**
5. Set:
   - **Installation name:** Apache Maven 3.9.x
   - **Installation home:** `C:\Program Files\Apache\maven`
6. Click **Finish**
7. Make sure it's selected
8. Click **Apply and Close**

### 2.4 Install Git Plugin (if not already installed)

1. Go to: **Help → Eclipse Marketplace**
2. Search for: **EGit**
3. Click **Install**
4. Follow the wizard
5. Restart Eclipse when prompted

---

## Step 3: Clone Project from GitHub

### 3.1 Using Eclipse GUI

1. Go to: **File → Import**
2. Expand **Git** folder
3. Select **Projects from Git**
4. Click **Next**
5. Select **Clone URI**
6. Click **Next**
7. In "URI" field, paste:
   ```
   https://github.com/yourusername/fashion-mashup.git
   ```
   (Replace with your actual GitHub repository URL)

8. Click **Next**
9. Select branch: **main** (or **master**)
10. Click **Next**
11. Set local destination:
    - **Windows:** `C:\Users\YourUsername\Documents\fashion-mashup`
    - **Mac/Linux:** `/Users/YourUsername/Documents/fashion-mashup`
12. Click **Finish**
13. Wait for clone to complete

### 3.2 Alternative: Using Command Line

**For Windows (Git Bash):**
```bash
cd C:\Users\YourUsername\Documents
git clone https://github.com/yourusername/fashion-mashup.git
cd fashion-mashup
```

**For Mac/Linux:**
```bash
cd ~/Documents
git clone https://github.com/yourusername/fashion-mashup.git
cd fashion-mashup
```

---

## Step 4: Import as Maven Project

### 4.1 Import Maven Project

1. Go to: **File → Import**
2. Expand **Maven** folder
3. Select **Existing Maven Projects**
4. Click **Next**
5. Click **Browse** button
6. Navigate to your cloned project folder
   - Windows: `C:\Users\YourUsername\Documents\fashion-mashup`
   - Mac/Linux: `~/Documents/fashion-mashup`
7. Click **Open** (or **Select Folder** on Mac)
8. Eclipse should find the pom.xml file
9. Check the checkbox next to `pom.xml`
10. Click **Finish**
11. **Wait for Maven to download dependencies** (may take 5-10 minutes)
    - You'll see progress in the bottom right corner
    - Don't interrupt this process

### 4.2 Verify Maven Project Import

Once import completes:
1. In **Project Explorer** (left panel), expand your project
2. You should see:
   ```
   fashionmashup/
   ├── src/
   │   ├── main/
   │   │   ├── java/
   │   │   └── webapp/
   │   └── test/
   ├── target/
   ├── pom.xml
   └── (other folders)
   ```

3. Right-click project → **Properties**
4. Go to **Project Facets**
5. Make sure "Dynamic Web Module" is checked
6. Click **Convert to faceted form...** if needed
7. Click **OK**

### 4.3 Update Maven Project (if needed)

1. Right-click project
2. Go to **Maven → Update Project** (or press **Alt+F5**)
3. Check **Force Update of Snapshots/Releases**
4. Click **OK**
5. Wait for update to complete

---

## Step 5: Configure Tomcat Server

### 5.1 Add Tomcat to Eclipse

1. Go to: **Window → Preferences**
2. Expand **Server** in left panel
3. Click **Runtime Environments**
4. Click **Add...**
5. Select **Apache Tomcat v10.1**
6. Click **Next**
7. Click **Browse** button
8. Navigate to your Tomcat installation:
   - **Windows:** `C:\Program Files\Apache\Tomcat10`
   - **Mac/Linux:** `/opt/tomcat10/` or `~/tomcat10/`
9. Click **Finish**
10. Make sure Apache Tomcat v10.1 is **checked**
11. Click **Apply and Close**

### 5.2 Create Tomcat Server in Eclipse

1. Go to: **Window → Show View → Other**
2. Expand **Server** folder
3. Select **Servers**
4. Click **Open**
5. You'll see a "Servers" tab at the bottom of Eclipse
6. Right-click in Servers view
7. Select **New → Server**
8. Select **Apache Tomcat v10.1**
9. Click **Next**
10. Server name: `Tomcat v10.1 Server` (or custom name)
11. Server runtime: Select the Tomcat runtime you created
12. Click **Next**
13. Click **Finish**

### 5.3 Configure Tomcat Server Settings

1. In Servers view, right-click your Tomcat server
2. Click **Properties**
3. Make sure following are set correctly:
   - **Server name:** Tomcat v10.1 Server
   - **Hostname:** localhost
   - **HTTP port:** 8080 (default)
   - **Control port:** 8005
4. Click **OK**

### 5.4 Add Project to Tomcat

1. In Servers view, right-click Tomcat server
2. Click **Add and Remove...**
3. In left panel: Select "fashionmashup" project
4. Click **Add →** button
5. It should move to right panel
6. Click **Finish**

---

## Step 6: Setup Database

### 6.1 Create MySQL Database

1. Open MySQL command prompt:
   - **Windows:** `cmd` or `PowerShell`
   - **Mac/Linux:** Terminal

2. Connect to MySQL:
   ```bash
   mysql -u root -p
   # Enter your root password
   ```

3. Create database:
   ```sql
   CREATE DATABASE fashionmashup;
   USE fashionmashup;
   ```

4. Create tables (tables will be auto-created by Java DAOs):
   ```sql
   -- Tables are created by the application via DAOs
   -- Just ensure database exists
   SHOW DATABASES;
   # You should see "fashionmashup" in the list
   
   exit;
   ```

### 6.2 Update Database Credentials in Eclipse

1. In Eclipse, navigate to:
   ```
   src → main → java → com → fashionmashup → util
   ```

2. Open **DBConnection.java**

3. Update these lines with your credentials:
   ```java
   private static final String URL = "jdbc:mysql://localhost:3306/fashionmashup";
   private static final String USER = "root";
   private static final String PASSWORD = "your_mysql_password";
   ```

4. Replace `your_mysql_password` with your actual MySQL root password

5. Save file: **Ctrl+S** (Windows/Linux) or **Cmd+S** (Mac)

---

## Step 7: Run the Project

### 7.1 Start Tomcat Server

1. Go to **Servers** view (bottom of Eclipse)
2. Right-click your Tomcat server
3. Click **Start** (icon with green play button)
4. Wait for server to start (you should see "Server Tomcat v10.1 Server has started" in console)

### 7.2 Deploy Application to Tomcat

The application should auto-deploy when Tomcat starts (since you added it in Step 5.4).

To manually deploy:
1. Right-click **fashionmashup** project
2. Go to **Run As → Run on Server**
3. Select your Tomcat server
4. Click **Finish**
5. Application will deploy

### 7.3 Build the Project (Optional)

If you need to rebuild:
1. Right-click project
2. Go to **Maven → Clean**
3. Wait for build to complete
4. Right-click project
5. Go to **Maven → Install**
6. Wait for build to complete

---

## Step 8: Verify Installation

### 8.1 Check Server Console

In **Console** tab (bottom of Eclipse), you should see:
```
Server Tomcat v10.1 Server has started and is ready to receive requests.
```

If you see errors, check [Troubleshooting](#troubleshooting) section.

### 8.2 Access Application in Browser

1. Open your web browser
2. Go to: `http://localhost:8080/fashionmashup`
3. You should see the login page

**If you see a 404 error:**
- Check console for errors
- Make sure MySQL database exists
- Verify DBConnection.java has correct credentials

### 8.3 Test Database Connection

1. In Eclipse, open **TestConnection.java**:
   ```
   src → main → java → com → fashionmashup → util
   ```

2. Right-click **TestConnection.java**
3. Go to **Run As → Java Application**
4. In Console, you should see:
   ```
   Connection successful!
   ```
   or error message if connection fails

---

## Step 9: Setup Frontend (Optional)

### 9.1 Install Node.js (if not installed)

1. Download from: https://nodejs.org/
2. Download LTS version
3. Run installer
4. Accept defaults and finish

### 9.2 Setup React Frontend

1. In command prompt/terminal, navigate to frontend:
   ```bash
   cd C:\path\to\fashion-mashup\frontend
   # or Mac/Linux
   cd ~/Documents/fashion-mashup/frontend
   ```

2. Install dependencies:
   ```bash
   npm install
   ```

3. Start development server:
   ```bash
   npm run dev
   ```

4. Access at: `http://localhost:5173`

---

## Troubleshooting

### Issue 1: Maven Dependencies Not Downloading

**Problem:** Project shows errors, missing dependencies

**Solution:**
1. Right-click project
2. Go to **Maven → Update Project** (Alt+F5)
3. Check "Force Update of Snapshots/Releases"
4. Click OK
5. Wait for download to complete (5-10 minutes)

### Issue 2: Tomcat Server Won't Start

**Problem:** Server fails to start, red X on server icon

**Solution:**
1. Check if port 8080 is in use:
   - **Windows:** `netstat -ano | findstr :8080`
   - **Mac/Linux:** `lsof -i :8080`
2. If in use, kill the process or change Tomcat port
3. Clean Tomcat work directory:
   - Right-click Tomcat → **Clean**
   - Right-click Tomcat → **Start**

### Issue 3: Database Connection Error

**Problem:** "Communications link failure" or "Access denied"

**Solution:**
1. Verify MySQL is running:
   ```bash
   mysql -u root -p
   # Type: exit
   ```
2. Check database exists:
   ```bash
   mysql -u root -p
   SHOW DATABASES;
   exit;
   ```
3. Update credentials in **DBConnection.java**:
   - Check username is "root"
   - Check password is correct
   - Check URL is "jdbc:mysql://localhost:3306/fashionmashup"
4. Restart Tomcat

### Issue 4: 404 Error When Accessing Application

**Problem:** "The requested resource is not available"

**Solution:**
1. Check Tomcat is running (green icon in Servers view)
2. Check application deployed:
   - Right-click Tomcat → **Show In → Windows Explorer** (Windows)
   - Navigate to: `webapps → fashionmashup`
   - Should contain files
3. If not deployed:
   - Right-click project → **Run As → Run on Server**
   - Select Tomcat and click Finish
4. Check console for errors

### Issue 5: "Cannot find main class" Error

**Problem:** Java runtime not configured correctly

**Solution:**
1. Go to **Window → Preferences**
2. Expand **Java → Installed JREs**
3. Verify JDK 21 is there and checked
4. If not, add it again (see Step 2.2)
5. Rebuild project

### Issue 6: Port 8080 Already in Use

**Problem:** "Address already in use"

**Solution:**
1. Change Tomcat port:
   - In Servers view, right-click Tomcat
   - Click **Properties**
   - Change HTTP port to 8081 (or any available port)
   - Click OK
   - Restart server
   - Access at: `http://localhost:8081/fashionmashup`

Or find and kill process using port:
- **Windows:**
  ```bash
  netstat -ano | findstr :8080
  taskkill /PID <PID> /F
  ```
- **Mac/Linux:**
  ```bash
  lsof -i :8080
  kill -9 <PID>
  ```

### Issue 7: Maven Build Errors

**Problem:** "BUILD FAILURE" in console

**Solution:**
1. Clean and rebuild:
   ```bash
   # Right-click project → Maven → Clean
   # Right-click project → Maven → Update Project
   ```
2. Check Java version:
   ```bash
   java -version
   # Should be 21.x.x
   ```
3. Check pom.xml for syntax errors
4. If still failing, run Maven from command line:
   ```bash
   cd C:\path\to\fashionmashup
   mvn clean install
   ```

### Issue 8: Application Loads But Shows Blank Page

**Problem:** Page loads but no content displayed

**Solution:**
1. Open browser DevTools (F12)
2. Check Console for JavaScript errors
3. Verify database tables exist:
   ```bash
   mysql -u root -p
   USE fashionmashup;
   SHOW TABLES;
   ```
4. Check Tomcat console in Eclipse for Java errors

---

## Project Structure in Eclipse

Once everything is setup, your project in Eclipse should look like:

```
fashionmashup
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com/fashionmashup
│   │   │       ├── controller/
│   │   │       ├── dao/
│   │   │       │   ├── (interfaces)
│   │   │       │   └── impl/
│   │   │       ├── model/
│   │   │       └── util/
│   │   └── webapp
│   │       ├── WEB-INF/
│   │       ├── assets/
│   │       └── index.html
│   └── test
├── target/
├── pom.xml
├── .classpath
├── .project
├── .gitignore
└── (other config files)
```

---

## Complete Workflow

### Daily Development Workflow

1. **Start Development:**
   - Open Eclipse
   - Start Tomcat server (Servers view → right-click → Start)
   - Make your code changes

2. **Deploy Changes:**
   - Save files (Ctrl+S)
   - Project auto-rebuilds
   - Tomcat auto-redeploys
   - Refresh browser to see changes

3. **Access Application:**
   - Backend: `http://localhost:8080/fashionmashup/products`
   - Frontend (if running): `http://localhost:5173`

4. **Stop Development:**
   - Right-click Tomcat → Stop
   - Close Eclipse

### Testing Workflow

1. **Test Login:**
   - Access `http://localhost:8080/fashionmashup/login`
   - Enter test credentials
   - Should redirect to home page

2. **Test Products:**
   - Access `http://localhost:8080/fashionmashup/products`
   - Should display product list

3. **Test Database:**
   - Open **TestConnection.java** (in util package)
   - Right-click → Run As → Java Application
   - Should say "Connection successful!" in console

---

## Quick Reference

| Task | Steps |
|------|-------|
| **Start Tomcat** | Servers view → Right-click → Start |
| **Stop Tomcat** | Servers view → Right-click → Stop |
| **Clean Project** | Right-click project → Maven → Clean |
| **Rebuild Project** | Right-click project → Maven → Install |
| **Update Maven** | Right-click project → Maven → Update Project |
| **View Console** | Window → Show View → Console |
| **View Servers** | Window → Show View → Servers |
| **Access App** | http://localhost:8080/fashionmashup |
| **Access DB** | mysql -u root -p |
| **View Logs** | Eclipse Console tab |

---

## Next Steps

After successful setup:

1. **Review the code:**
   - Explore controller classes
   - Check DAO implementations
   - Understand model structures

2. **Add new features:**
   - Create new controllers
   - Add database tables
   - Implement business logic

3. **Test thoroughly:**
   - Test all endpoints
   - Verify database operations
   - Check error handling

4. **Deploy to production:**
   - Build WAR file
   - Deploy to production Tomcat
   - Configure production database

---

## Support & Resources

- **Eclipse Documentation:** https://help.eclipse.org/
- **Maven Documentation:** https://maven.apache.org/guides/
- **Tomcat Documentation:** https://tomcat.apache.org/
- **Java Servlet Guide:** https://docs.oracle.com/javaee/

---

**Congratulations! Your Fashion Mashup project is now setup in Eclipse with Maven and Tomcat!**

For any issues, check the Troubleshooting section or refer to the project documentation.

