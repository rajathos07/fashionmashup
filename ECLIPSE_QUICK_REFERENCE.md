# Eclipse Setup - Quick Reference Guide

Visual quick-start guide for setting up Fashion Mashup in Eclipse.

## Installation Order

```
1. Java JDK 21
   ↓
2. Maven 3.9+
   ↓
3. Eclipse IDE (JEE Edition)
   ↓
4. Tomcat 10
   ↓
5. MySQL 8+
   ↓
6. Configure Eclipse (JDK, Maven, Git, Tomcat)
   ↓
7. Clone Project from GitHub
   ↓
8. Import as Maven Project
   ↓
9. Setup Database
   ↓
10. Run on Tomcat
```

---

## Download Links

### Java JDK 21
```
https://www.oracle.com/java/technologies/downloads/#java21
Download: JDK 21 (select your OS)
Installation: Run installer → Next → Next → Finish
```

### Maven 3.9.x
```
https://maven.apache.org/download.cgi
Download: Binary zip archive
Extract to: C:\Program Files\Apache\maven (Windows)
           /opt/maven (Mac/Linux)
Add to PATH in Environment Variables
```

### Eclipse IDE
```
https://www.eclipse.org/downloads/
Download: Eclipse IDE for Enterprise Java and Web Developers
Run installer or extract and launch eclipse.exe
```

### Tomcat 10
```
https://tomcat.apache.org/download-10.cgi
Download: Binary zip or tar.gz
Extract to: C:\Program Files\Apache\Tomcat10 (Windows)
           /opt/tomcat10 (Mac/Linux)
```

### MySQL Server
```
https://dev.mysql.com/downloads/mysql/
Download: MySQL Community Server
Run installer
Remember root password (you'll need it!)
Default port: 3306
```

---

## Eclipse Configuration Checklist

```
Window → Preferences
├── Java
│   └── Installed JREs
│       ├── Add → Standard VM
│       ├── JRE home: C:\Program Files\Java\jdk-21
│       └── ✓ Check JDK 21
├── Maven
│   └── Installations
│       ├── Add → C:\Program Files\Apache\maven
│       └── ✓ Select Apache Maven 3.9.x
└── Server
    └── Runtime Environments
        ├── Add → Apache Tomcat v10.1
        ├── Tomcat home: C:\Program Files\Apache\Tomcat10
        └── ✓ Check Apache Tomcat v10.1
```

---

## GitHub Clone Steps

**Method 1: Using Eclipse Git**
```
File → Import
└── Git → Projects from Git
    └── Clone URI
        ├── URI: https://github.com/yourusername/fashion-mashup.git
        ├── Branch: main
        └── Local Destination: C:\Users\...\fashion-mashup
```

**Method 2: Command Line**
```bash
cd C:\Users\YourUsername\Documents
git clone https://github.com/yourusername/fashion-mashup.git
cd fashion-mashup
```

---

## Maven Import Steps

```
File → Import
└── Maven → Existing Maven Projects
    ├── Root Directory: C:\...\fashion-mashup
    ├── Select: pom.xml
    └── Finish (wait 5-10 minutes for dependencies)
```

---

## Tomcat Configuration Steps

```
Window → Preferences
└── Server → Runtime Environments
    ├── Add New
    ├── Apache Tomcat v10.1
    ├── Tomcat home: C:\Program Files\Apache\Tomcat10
    └── Finish

Then Add Project:
├── Servers view (bottom)
├── Right-click Tomcat
├── Add and Remove...
├── Select fashionmashup → Add →
└── Finish
```

---

## Database Setup Steps

```
MySQL Command Line:

1. Connect:
   mysql -u root -p
   (Enter password)

2. Create Database:
   CREATE DATABASE fashionmashup;
   USE fashionmashup;

3. Exit:
   exit;

4. Update DBConnection.java:
   - Open in Eclipse
   - src → main → java → com → fashionmashup → util
   - Edit: URL, USER, PASSWORD
   - Save: Ctrl+S
```

---

## Running the Application

### Start Tomcat
```
Servers view (bottom of Eclipse)
└── Right-click "Tomcat v10.1 Server"
    └── Start (green play button)
    
Wait for: "Server ... has started and is ready to receive requests"
```

### Deploy Application
```
Option 1 - Auto Deploy:
  └── Application auto-deploys when added to Tomcat

Option 2 - Manual Deploy:
  ├── Right-click fashionmashup project
  ├── Run As → Run on Server
  ├── Select Tomcat v10.1
  └── Finish
```

### Access Application
```
Backend: http://localhost:8080/fashionmashup
Frontend: http://localhost:5173 (if running npm dev)
```

---

## Project Structure in Eclipse

```
fashionmashup
├── src/main/java/com/fashionmashup/
│   ├── controller/        ← Request handlers
│   │   ├── LoginController
│   │   ├── ProductController
│   │   ├── CartController
│   │   ├── CheckoutController
│   │   └── ... (more)
│   ├── dao/               ← Database access (interfaces)
│   │   ├── UserDAO
│   │   ├── ProductDAO
│   │   ├── CartDAO
│   │   └── ... (more)
│   ├── dao/impl/          ← DAO implementations
│   │   ├── UserDAOImpl
│   │   ├── ProductDAOImpl
│   │   └── ... (more)
│   ├── model/             ← Entity classes
│   │   ├── User
│   │   ├── Product
│   │   ├── Cart
│   │   ├── Order
│   │   └── ... (more)
│   └── util/              ← Utilities
│       └── DBConnection
├── src/main/webapp/
│   ├── WEB-INF/
│   │   ├── web.xml        ← Servlet config
│   │   └── views/         ← JSP files
│   └── assets/            ← CSS, images
├── src/test/              ← Test files
├── target/                ← Build output
├── pom.xml                ← Maven config
└── ... (other files)
```

---

## Common Operations

### Clean Build
```
Right-click project
└── Maven → Clean
    (Wait for "BUILD SUCCESS")
```

### Update Project
```
Right-click project
└── Maven → Update Project (Alt+F5)
    ├── ☑ Force Update of Snapshots/Releases
    └── OK
```

### Rebuild Project
```
Right-click project
└── Maven → Install
    (Wait for "BUILD SUCCESS")
```

### Restart Tomcat
```
Servers view
├── Right-click Tomcat
├── Stop (wait for stop)
└── Start
```

### View Console
```
Window → Show View → Console
(If not visible, go to Show View again and search "Console")
```

### View Project Problems
```
Window → Show View → Problems
(Shows compile errors, warnings)
```

---

## Keyboard Shortcuts

| Shortcut | Action |
|----------|--------|
| Ctrl+S | Save file |
| Ctrl+Shift+S | Save all |
| Ctrl+B | Build project |
| Alt+F5 | Update Maven project |
| F11 | Debug |
| Ctrl+F11 | Run |
| Ctrl+H | Search and Replace |
| Ctrl+Shift+F | Format code |
| Ctrl+/ | Comment/uncomment |
| Ctrl+1 | Quick fix |

---

## Port Configuration

### Default Ports
```
Tomcat HTTP:    8080
Tomcat Control: 8005
MySQL:          3306
Frontend (npm):  5173
```

### Change Tomcat Port (if 8080 in use)

```
Servers view
├── Right-click Tomcat
├── Properties
├── HTTP port: 8081 (or other)
└── OK
```

Then access: `http://localhost:8081/fashionmashup`

---

## Troubleshooting Quick Fixes

### Tomcat Won't Start
```
1. Servers → Right-click Tomcat → Clean
2. Servers → Right-click Tomcat → Start
3. If still fails, change port (see above)
4. Check console for errors
```

### Maven Dependencies Not Found
```
1. Right-click project → Maven → Update Project
2. Check "Force Update of Snapshots/Releases"
3. Click OK
4. Wait 5-10 minutes
```

### Database Connection Error
```
1. Verify MySQL is running:
   mysql -u root -p
   exit;
   
2. Check database exists:
   mysql -u root -p
   SHOW DATABASES;
   exit;
   
3. Update DBConnection.java:
   - Check URL: jdbc:mysql://localhost:3306/fashionmashup
   - Check USER: root
   - Check PASSWORD: (your actual password)
   
4. Restart Tomcat
```

### 404 Error
```
1. Check Tomcat is running (green icon)
2. Check URL: http://localhost:8080/fashionmashup
3. Check application deployed in Tomcat
4. Check console for errors
5. Restart Tomcat
```

### "Cannot resolve symbol" Errors
```
1. Right-click project → Maven → Update Project
2. Project → Clean
3. Project → Build All
4. If persistent, restart Eclipse
```

---

## Verification Checklist

After setup, verify everything works:

```
☐ Java JDK 21 installed
  └─ Command: java -version (shows 21.x.x)

☐ Maven installed
  └─ Command: mvn -version (shows version and Java 21)

☐ Eclipse installed and configured
  └─ Window → Preferences shows Java and Maven configured

☐ Tomcat added to Eclipse
  └─ Servers view shows "Tomcat v10.1 Server"

☐ Project imported as Maven project
  └─ Project Explorer shows "fashionmashup" with Maven icon

☐ Project builds successfully
  └─ Console shows "BUILD SUCCESS"

☐ MySQL running and database created
  └─ mysql -u root -p command works

☐ DBConnection.java has correct credentials
  └─ File src/main/java/.../util/DBConnection.java updated

☐ Tomcat can start
  └─ Servers view: Right-click Tomcat → Start
  └─ Console shows "... has started and is ready to receive requests"

☐ Application accessible
  └─ Browser: http://localhost:8080/fashionmashup
  └─ Shows login page or home page

☐ Database connection works
  └─ TestConnection.java shows "Connection successful!"
```

---

## Next Steps After Setup

1. **Explore the Code:**
   - Open LoginController.java
   - Open ProductDAO.java
   - Understand the MVC pattern

2. **Test the Application:**
   - Access http://localhost:8080/fashionmashup
   - Try login page
   - Try products page

3. **Make a Code Change:**
   - Edit a controller or view
   - Save file
   - Refresh browser to see changes

4. **Debug:**
   - Set breakpoint by clicking line number
   - Access application
   - Debug perspective shows variable values

5. **Git Workflow:**
   - Make changes
   - Right-click project → Team → Commit
   - Enter commit message
   - Push to GitHub

---

## File Locations Reference

| Item | Windows Path | Mac/Linux Path |
|------|-------------|----------------|
| Java JDK | C:\Program Files\Java\jdk-21 | /Library/Java/JavaVirtualMachines/jdk-21.jdk |
| Maven | C:\Program Files\Apache\maven | /opt/maven or /usr/local/maven |
| Eclipse | C:\Eclipse (or custom) | ~/eclipse or /Applications/Eclipse |
| Tomcat | C:\Program Files\Apache\Tomcat10 | /opt/tomcat10 or ~/tomcat10 |
| MySQL | C:\Program Files\MySQL | /usr/local/mysql or /opt/mysql |
| Project | C:\Users\...\Documents\fashion-mashup | ~/Documents/fashion-mashup |
| Workspace | C:\eclipse-workspace | ~/eclipse-workspace |

---

## Environment Variables (Windows)

Set these for command-line access:

```
JAVA_HOME = C:\Program Files\Java\jdk-21
MAVEN_HOME = C:\Program Files\Apache\maven
CATALINA_HOME = C:\Program Files\Apache\Tomcat10
MYSQL_HOME = C:\Program Files\MySQL\MySQL Server 8.0

PATH = (add following)
  %JAVA_HOME%\bin
  %MAVEN_HOME%\bin
  %MYSQL_HOME%\bin
```

Test in Command Prompt:
```
java -version
mvn -version
mysql --version
```

All should show version numbers.

---

## Common Error Messages & Solutions

| Error | Cause | Solution |
|-------|-------|----------|
| Port 8080 in use | Another app using port | Change Tomcat port to 8081 |
| Connection refused | MySQL not running | Start MySQL service |
| BUILD FAILURE | Missing dependencies | Maven → Update Project |
| 404 Not Found | App not deployed | Run As → Run on Server |
| Cannot find JDK | Java not configured | Add JDK in Preferences |
| [ERROR] Unknown | Maven cache corrupt | Maven → Update Project |

---

**You're all set! Happy coding!**

If you have any issues, refer to the full [ECLIPSE_SETUP_GUIDE.md](ECLIPSE_SETUP_GUIDE.md) for detailed troubleshooting.

