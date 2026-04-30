# Eclipse Setup - Step-by-Step Visual Guide

Ultra-detailed, numbered steps with exact screenshots-like descriptions.

---

## PHASE 1: INSTALL REQUIRED SOFTWARE

### STEP 1: Install Java JDK 21

**Windows:**
1. Open browser → Go to https://www.oracle.com/java/technologies/downloads/#java21
2. Click **"Windows x64 Installer"** (blue button)
3. Accept license agreement
4. Click **"Download"**
5. Run the downloaded file `jdk-21_windows-x64_bin.exe`
6. Click **"Next >"**
7. Accept default location: `C:\Program Files\Java\jdk-21`
8. Click **"Next >"**
9. Installation begins...
10. Click **"Finish"** when complete

**Mac:**
1. Open browser → Go to https://www.oracle.com/java/technologies/downloads/#java21
2. Click **"macOS x64 DMG Installer"**
3. Open the downloaded DMG file
4. Drag Java icon to Applications folder
5. Complete installation

**Linux (Ubuntu):**
```bash
sudo apt update
sudo apt install openjdk-21-jdk
```

**Verify Installation:**
- Open Command Prompt (Windows) or Terminal (Mac/Linux)
- Type: `java -version`
- You should see: `java version "21.x.x" 2024-xx-xx LTS`

---

### STEP 2: Install Maven 3.9.x

**Windows:**
1. Open browser → Go to https://maven.apache.org/download.cgi
2. Download **"Binary zip archive"** (apache-maven-3.9.x-bin.zip)
3. Create folder: `C:\Program Files\Apache\`
4. Extract zip file into: `C:\Program Files\Apache\maven`
   - (Folder structure: C:\Program Files\Apache\maven\bin, C:\Program Files\Apache\maven\lib, etc.)
5. Set Environment Variables:
   - Press **Windows Key + Pause**
   - Click **"Advanced system settings"** (left side)
   - Click **"Environment Variables..."** button
   - Under "System variables", click **"New..."**
   - Variable name: `MAVEN_HOME`
   - Variable value: `C:\Program Files\Apache\maven`
   - Click **"OK"**
   - Click **"New..."** again
   - Edit "Path" variable (select it and click "Edit...")
   - Click **"New"**
   - Add: `C:\Program Files\Apache\maven\bin`
   - Click **"OK"** on all dialogs
   - **Restart your computer**

**Mac/Linux:**
```bash
# Using Homebrew (Mac)
brew install maven

# Using apt (Linux)
sudo apt install maven
```

**Verify Installation:**
- Open Command Prompt/Terminal
- Type: `mvn -version`
- You should see Maven version and Java 21 version

---

### STEP 3: Install Eclipse IDE for Enterprise Java

**All Platforms:**
1. Open browser → Go to https://www.eclipse.org/downloads/
2. Click **"Eclipse IDE for Enterprise Java and Web Developers"**
3. Download for your OS (Windows/Mac/Linux)
4. Extract or run the installer
5. Choose installation location (default is fine)
6. Launch Eclipse
   - Windows: Run `eclipse.exe`
   - Mac: Open Eclipse application
   - Linux: Run `eclipse` command

7. **First time setup:**
   - Select Workspace Location:
     - Windows: `C:\eclipse-workspace`
     - Mac/Linux: `~/eclipse-workspace`
   - Click **"Launch"**
   - Wait for Eclipse to fully load (may take 2-3 minutes)

---

### STEP 4: Install Apache Tomcat 10

**Windows:**
1. Open browser → Go to https://tomcat.apache.org/download-10.cgi
2. Under "Binary Distributions", click **"zip"** (apache-tomcat-10.1.x.zip)
3. Create folder: `C:\Program Files\Apache\`
4. Extract zip file into: `C:\Program Files\Apache\Tomcat10`
   - Final path should be: `C:\Program Files\Apache\Tomcat10\bin`, etc.

**Mac/Linux:**
```bash
# Create directory
mkdir -p ~/tomcat10

# Download (replace version as needed)
wget https://archive.apache.org/dist/tomcat/tomcat-10/v10.1.x/bin/apache-tomcat-10.1.x.tar.gz

# Extract
tar xzf apache-tomcat-10.1.x.tar.gz -C ~/tomcat10

# Make startup script executable (Mac/Linux)
chmod +x ~/tomcat10/bin/catalina.sh
```

**Remember this path for later!** You'll need it in Eclipse.

---

### STEP 5: Install MySQL Community Server

**Windows:**
1. Open browser → Go to https://dev.mysql.com/downloads/mysql/
2. Select your Windows version (8.0 Community Server)
3. Click **"Download"** on ZIP Archive (or MSI Installer)
4. Run the installer
5. Click **"Next >"** through the wizard
6. When asked for password, enter a password you'll remember (e.g., "admin123")
7. Complete installation

**Mac:**
```bash
brew install mysql
mysql_secure_installation
# Set root password
```

**Linux:**
```bash
sudo apt update
sudo apt install mysql-server
sudo mysql_secure_installation
```

**Verify Installation:**
- Open Command Prompt/Terminal
- Type: `mysql --version`
- You should see: `mysql Ver X.X.XX`

---

## PHASE 2: CONFIGURE ECLIPSE

### STEP 6: Configure Java JDK in Eclipse

**In Eclipse:**
1. Go to **Window** → **Preferences** (Mac: **Eclipse** → **Preferences**)
2. In left panel, expand **Java**
3. Click **Installed JREs**
4. Click **Add...** button
5. Select **Standard VM** → Click **Next**
6. Click **Browse...** button
7. Navigate to your Java JDK installation:
   - Windows: `C:\Program Files\Java\jdk-21`
   - Mac: `/Library/Java/JavaVirtualMachines/jdk-21.jdk/Contents/Home`
   - Linux: `/usr/lib/jvm/java-21-openjdk-amd64`
8. Click **Finish**
9. Check the box next to your JDK 21
10. Click **Apply and Close**

---

### STEP 7: Configure Maven in Eclipse

**In Eclipse:**
1. Go to **Window** → **Preferences**
2. In left panel, expand **Maven**
3. Click **Installations**
4. Click **Add...** button
5. Click **Browse...** button
6. Navigate to Maven installation:
   - Windows: `C:\Program Files\Apache\maven`
   - Mac/Linux: `/opt/maven` or `~/maven`
7. Click **Finish**
8. Make sure it's selected (radio button)
9. Click **Apply and Close**

---

### STEP 8: Install Git Plugin (EGit)

**In Eclipse:**
1. Go to **Help** → **Eclipse Marketplace**
2. In search box, type: `EGit`
3. Click **Install** on "EGit"
4. Accept license agreement
5. Click **Finish**
6. Click **Restart Now** when prompted
7. Eclipse will restart

---

## PHASE 3: CLONE PROJECT FROM GITHUB

### STEP 9: Clone Project Using Eclipse

**In Eclipse:**
1. Go to **File** → **Import**
2. In dialog, expand **Git** folder
3. Select **Projects from Git** → Click **Next >**
4. Select **Clone URI** → Click **Next >**
5. In URI field, paste your GitHub repository URL:
   ```
   https://github.com/yourusername/fashion-mashup.git
   ```
   (Get actual URL from your GitHub repo)
6. Click **Next >**
7. Make sure **main** (or **master**) branch is selected
8. Click **Next >**
9. Set destination folder:
   - Windows: `C:\Users\YourUsername\Documents\fashion-mashup`
   - Mac/Linux: `~/Documents/fashion-mashup`
10. Click **Finish**
11. Wait for clone to complete (shows progress bar)

**Alternative - Using Git Command Line:**
```bash
# Windows (Git Bash)
cd C:\Users\YourUsername\Documents
git clone https://github.com/yourusername/fashion-mashup.git
cd fashion-mashup

# Mac/Linux
cd ~/Documents
git clone https://github.com/yourusername/fashion-mashup.git
cd fashion-mashup
```

---

## PHASE 4: IMPORT AS MAVEN PROJECT

### STEP 10: Import Maven Project

**In Eclipse:**
1. Go to **File** → **Import**
2. Expand **Maven** folder
3. Select **Existing Maven Projects** → Click **Next >**
4. Click **Browse...** button
5. Navigate to your cloned project folder:
   - Windows: `C:\Users\YourUsername\Documents\fashion-mashup`
   - Mac/Linux: `~/Documents/fashion-mashup`
6. Click **OK** (or **Select Folder** on Mac)
7. Eclipse should show:
   - Folder path field filled
   - Below showing: `✓ C:\...\fashion-mashup\pom.xml`
8. Make sure pom.xml is **checked**
9. Click **Finish**
10. **WAIT** - This is important!
    - Console shows: `Downloading ...` and progress
    - Eclipse downloads all Maven dependencies (5-10 minutes)
    - DO NOT interrupt this process
    - When done, console shows "BUILD SUCCESS"

---

### STEP 11: Verify Maven Project Import

**In Eclipse:**
1. Look at **Project Explorer** (left panel)
2. Expand your project → You should see:
   ```
   fashionmashup/
   ├── src/
   │   ├── main/
   │   │   ├── java/        ← Java source code
   │   │   └── webapp/      ← Web files
   │   └── test/
   ├── target/              ← Compiled output
   ├── pom.xml              ← Maven config
   └── (other files)
   ```
3. If you see red X marks:
   - Right-click project → **Maven** → **Update Project**
   - Check "Force Update of Snapshots/Releases"
   - Click **OK**
   - Wait for download

---

## PHASE 5: CONFIGURE TOMCAT SERVER IN ECLIPSE

### STEP 12: Add Tomcat Runtime to Eclipse

**In Eclipse:**
1. Go to **Window** → **Preferences**
2. In left panel, expand **Server**
3. Click **Runtime Environments**
4. Click **Add...** button
5. Select **Apache Tomcat v10.1** → Click **Next >**
6. Click **Browse...** button
7. Navigate to Tomcat installation:
   - Windows: `C:\Program Files\Apache\Tomcat10`
   - Mac/Linux: `~/tomcat10` or `/opt/tomcat10`
8. Click **Finish**
9. Make sure **Apache Tomcat v10.1** is **checked**
10. Click **Apply and Close**

---

### STEP 13: Create Tomcat Server in Eclipse

**In Eclipse:**
1. Go to **Window** → **Show View** → **Other...**
2. Expand **Server** folder
3. Select **Servers** → Click **Open**
4. You should see "Servers" tab at bottom of Eclipse
5. In empty Servers area, right-click
6. Select **New** → **Server**
7. Select **Apache Tomcat v10.1** → Click **Next >**
8. Server name: `Tomcat v10.1 Server` (default is fine)
9. Server runtime: Select your Tomcat runtime from dropdown
10. Click **Next >**
11. Click **Finish**
12. You should see in Servers view: `Tomcat v10.1 Server` (stopped)

---

### STEP 14: Add Project to Tomcat Server

**In Eclipse:**
1. In **Servers** view (bottom), right-click **Tomcat v10.1 Server**
2. Select **Add and Remove...**
3. In left panel, you should see: `fashionmashup`
4. Select `fashionmashup` and click **Add →** button
5. It should move to right panel (Available)
6. Click **Finish**
7. Now your project is associated with Tomcat

---

## PHASE 6: SETUP DATABASE

### STEP 15: Create MySQL Database

**Open Command Prompt/Terminal:**

Windows:
```bash
# Open Command Prompt (Windows Key + R, type "cmd", press Enter)
mysql -u root -p
# When prompted, enter your MySQL root password
```

Mac/Linux:
```bash
mysql -u root -p
# When prompted, enter your MySQL root password
```

**In MySQL Command Line:**
```sql
CREATE DATABASE fashionmashup;
USE fashionmashup;
SHOW TABLES;
# (Should be empty - tables created by Java application)
exit;
```

**Result:**
- Database `fashionmashup` created
- Ready for Java application to create tables

---

### STEP 16: Update Database Credentials in Eclipse

**In Eclipse:**
1. In **Project Explorer**, navigate to:
   ```
   fashionmashup → src → main → java → com → fashionmashup → util
   ```
2. Double-click **DBConnection.java**
3. Find these lines (around line 5-10):
   ```java
   private static final String URL = "jdbc:mysql://localhost:3306/fashionmashup";
   private static final String USER = "root";
   private static final String PASSWORD = "your_password";
   ```
4. Update `PASSWORD` with your actual MySQL password:
   ```java
   private static final String PASSWORD = "admin123";  // if your password is "admin123"
   ```
5. Save file: **Ctrl+S** (Windows/Linux) or **Cmd+S** (Mac)

---

## PHASE 7: RUN THE PROJECT

### STEP 17: Start Tomcat Server

**In Eclipse:**
1. Look at **Servers** view (bottom right)
2. Right-click **Tomcat v10.1 Server**
3. Click **Start** (green play button icon)
4. Watch the **Console** tab (bottom left)
5. Wait for message:
   ```
   Server Tomcat v10.1 Server has started and is ready to receive requests.
   ```
6. If no errors, server is running!

**If Tomcat won't start:**
- Check [Troubleshooting](#troubleshooting-quick-fixes) below

---

### STEP 18: Deploy Application to Tomcat

**In Eclipse:**
1. Right-click **fashionmashup** project (in Project Explorer)
2. Go to **Run As** → **Run on Server**
3. Select **Tomcat v10.1 Server** (should already be selected)
4. Click **Finish**
5. Eclipse will:
   - Build the project
   - Deploy WAR to Tomcat
   - Show "Server startup in X seconds"
6. A browser window should open showing your application

---

## PHASE 8: VERIFY INSTALLATION

### STEP 19: Test Application in Browser

**Open Web Browser:**
1. Go to: `http://localhost:8080/fashionmashup`
2. You should see the login page
3. If you see 404 error:
   - Check Tomcat is running (green icon in Servers view)
   - Check console for errors
   - See [Troubleshooting](#troubleshooting-quick-fixes)

---

### STEP 20: Test Database Connection

**In Eclipse:**
1. In Project Explorer, navigate to:
   ```
   fashionmashup → src → main → java → com → fashionmashup → util
   ```
2. Right-click **TestConnection.java**
3. Go to **Run As** → **Java Application**
4. In **Console** tab, you should see:
   ```
   Connection successful!
   ```
   or
   ```
   Error: Database connection failed
   ```

**If "Connection successful!":**
- ✓ Database setup is correct
- ✓ MySQL is running
- ✓ Credentials are correct

**If "Connection failed":**
- Check MySQL is running
- Check password in DBConnection.java
- See [Troubleshooting](#troubleshooting-quick-fixes)

---

## PHASE 9: SETUP FRONTEND (OPTIONAL)

### STEP 21: Install Node.js

1. Download from: https://nodejs.org/
2. Download LTS version
3. Run installer
4. Follow wizard (accept defaults)
5. Restart computer

**Verify:**
```bash
node --version
npm --version
```

---

### STEP 22: Setup React Frontend

**Open Command Prompt/Terminal:**
```bash
# Windows (PowerShell or Git Bash)
cd C:\Users\YourUsername\Documents\fashion-mashup\frontend

# Mac/Linux
cd ~/Documents/fashion-mashup/frontend
```

**Install dependencies:**
```bash
npm install
# Wait for installation to complete
```

**Start development server:**
```bash
npm run dev
# You should see: "Local: http://localhost:5173/"
```

**Access in browser:**
- Go to: `http://localhost:5173`
- You should see the home page

---

## Troubleshooting Quick Fixes

### Problem 1: Tomcat Won't Start

**Error in Console:**
```
[SEVERE] Cannot start Tomcat
```

**Solutions:**
1. Right-click Tomcat → **Clean**
2. Right-click Tomcat → **Start** again
3. If still fails, port 8080 might be in use:
   - Right-click Tomcat → **Properties**
   - Change HTTP port from 8080 to 8081
   - Click **OK**
   - Start Tomcat again
   - Access at: `http://localhost:8081/fashionmashup`

---

### Problem 2: 404 Error When Accessing Application

**Error in Browser:**
```
The requested resource is not available
```

**Solutions:**
1. Check URL: should be `http://localhost:8080/fashionmashup`
2. Check Tomcat is running (green icon in Servers view)
3. Check application deployed:
   - Right-click project → **Run As** → **Run on Server**
   - Select Tomcat and click **Finish**
4. Refresh browser: **Ctrl+F5** (hard refresh)

---

### Problem 3: Maven Dependencies Not Downloading

**Error in Console:**
```
[ERROR] Failed to execute goal
[ERROR] Missing artifact
```

**Solutions:**
1. Right-click project → **Maven** → **Update Project** (Alt+F5)
2. Check "Force Update of Snapshots/Releases"
3. Click **OK**
4. Wait 5-10 minutes for download
5. If still fails:
   - Delete: project's `.m2` folder
   - Try update again

---

### Problem 4: Database Connection Error

**Error in Console:**
```
Communications link failure
Access denied for user 'root'@'localhost'
```

**Solutions:**
1. Verify MySQL is running:
   ```bash
   mysql -u root -p
   # If this works, database is fine
   exit;
   ```
2. Check password in DBConnection.java:
   - Should match your MySQL root password
3. Verify database exists:
   ```bash
   mysql -u root -p
   SHOW DATABASES;
   exit;
   ```
4. Restart Tomcat

---

### Problem 5: Java Version Mismatch

**Error in Console:**
```
[ERROR] java.lang.UnsupportedClassVersionError
[ERROR] major.minor version 21
```

**Solutions:**
1. Verify Java version: `java -version`
2. Should show: `21.x.x`
3. In Eclipse:
   - Right-click project → **Properties**
   - Look for "Java Compiler" or "Project Facets"
   - Set Compiler compliance to 21
   - Click **OK**
4. Clean and rebuild project

---

### Problem 6: Cannot Find Main Class

**Error in Console:**
```
Error: Could not find or load main class
```

**Solutions:**
1. Go to **Window** → **Preferences**
2. Expand **Java** → **Installed JREs**
3. Make sure JDK 21 is selected (not JRE)
4. Click **Apply and Close**
5. Clean and rebuild project

---

## Quick Access Paths

### Important Folders
```
Windows:
C:\Program Files\Java\jdk-21              (Java)
C:\Program Files\Apache\maven             (Maven)
C:\Program Files\Apache\Tomcat10          (Tomcat)
C:\Users\YourUsername\eclipse-workspace   (Eclipse workspace)
C:\Users\YourUsername\Documents\fashion-mashup  (Project)

Mac/Linux:
/Library/Java/JavaVirtualMachines/jdk-21.jdk  (Java - Mac)
/usr/lib/jvm/java-21-openjdk-amd64            (Java - Linux)
/opt/maven or ~/maven                         (Maven)
/opt/tomcat10 or ~/tomcat10                    (Tomcat)
~/eclipse-workspace                           (Eclipse workspace)
~/Documents/fashion-mashup                     (Project)
```

---

## Testing the Complete Setup

After all steps, test this sequence:

1. **Start Tomcat:**
   - Servers view → Right-click Tomcat → Start
   - Wait for "ready to receive requests"

2. **Access Backend:**
   - Browser → http://localhost:8080/fashionmashup
   - Should show login page (white page with form)

3. **Test Database:**
   - Eclipse → TestConnection.java → Right-click → Run As → Java Application
   - Console should show "Connection successful!"

4. **Start Frontend (optional):**
   - Terminal → `cd frontend`
   - `npm run dev`
   - Browser → http://localhost:5173
   - Should show home page

**All tests pass?** ✓ Setup complete!

---

**Congratulations! Your Fashion Mashup project is fully setup in Eclipse with Maven and Tomcat!**

For more detailed help, see:
- [ECLIPSE_SETUP_GUIDE.md](ECLIPSE_SETUP_GUIDE.md) - Full detailed guide
- [ECLIPSE_QUICK_REFERENCE.md](ECLIPSE_QUICK_REFERENCE.md) - Quick reference
- [SETUP_GUIDE.md](SETUP_GUIDE.md) - General setup guide

