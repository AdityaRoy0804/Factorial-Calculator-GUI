
# 📐 Factorial Calculator – Java Swing GUI + MySQL

A Java-based desktop application that calculates the factorial of a number using both **iterative** and **recursive** methods. It features a user-friendly **Swing GUI** and stores calculation history in a **MySQL database**, which can be viewed in a tabulated format within the application.

---

## 🛠 Features

- ✅ Java Swing-based GUI with clean layout and large fonts
- ✅ Input validation and error handling
- ✅ Iterative and Recursive factorial calculation
- ✅ Result display below input
- ✅ MySQL database integration using JDBC
- ✅ View calculation history 

---

## 🚀 How to Run

### 
#### 1. Clone or Download

git clone https://github.com/AdityaRoy0804/Factorial-Calculator-GUI

cd factorial-gui-java

#### 2. Create MySQL Table
sql

CREATE DATABASE factorial_app;

USE factorial_app;

CREATE TABLE factorial_history (

    id INT AUTO_INCREMENT PRIMARY KEY,
    input INT NOT NULL,
    iterative_result BIGINT,
    recursive_result BIGINT,
    timestamp DATETIME DEFAULT CURRENT_TIMESTAMP
);

#### 3. Configure Database
Edit the DBService.java file and update the following:

private static final String URL = "jdbc:mysql://localhost:3306/factorial_app";

private static final String USER = "your_mysql_username";

private static final String PASSWORD = "your_mysql_password";

#### 4. Compile and Run
Use any IDE (IntelliJ, Eclipse, NetBeans) or compile via terminal:


javac -cp ".;mysql-connector-java.jar" *.java

java -cp ".;mysql-connector-java.jar" FactorialGUI

Make sure mysql-connector-java.jar is in the classpath.

---

## 📁 Project Structure


factorial-gui-java/

│

├── DBService.java         # Manages MySQL connection & history

├── FactorialService.java  # Factorial logic (iterative and recursive)

├── FactorialGUI.java      # Main Swing GUI class

├── README.md              # Project documentation

---

## 📦 Technologies Used
Java 8+

Swing for GUI

JDBC for MySQL connectivity

MySQL for persistent storage

---
## 🙌 Author

### Aditya Roy

LinkedIn : https://www.linkedin.com/in/aditya-kumar-roy-257a1428a/

GitHub : https://github.com/AdityaRoy0804
