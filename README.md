# 🎓 Student Management System with Authentication and Admin Dashboard

## 📝 Project Title
**Secure Student Management System using JSP, Servlets, and MySQL with Web Interface**

---

## 📌 Overview

This project is a **Student Management System** built using **Java (JSP/Servlets)** for backend logic, **HTML/CSS** for the frontend interface, and **MySQL** (managed via **XAMPP**) for the database. The system includes:

- Secure Login & Registration  
- Forgot Password functionality  
- Admin Dashboard to manage student records and grades  
- Full **CRUD operations** on student data  
- Academic grade management

---

## 🎯 Objectives

- Implement secure user **authentication system** (Login, Registration, Forgot Password)
- Provide an **Admin Panel** to manage student details and academic grades
- Enable full **CRUD** (Create, Read, Update, Delete) functionalities
- Showcase integration of **JSP/Servlets with MySQL**
- Demonstrate concepts of **Advanced Internet Programming**

---

## 🧩 Key Features

### 🔐 User Module
- User Login  
- New User Registration  
- Forgot Password (Reset mechanism)

### 🧑‍💼 Admin Module
- View Registered Students (Name, Age, Father & Mother Name, Profile Image)
- View Full Student Profile
- Edit Student Information
- Delete Student Records

### 📚 Academic Module
- Add Subject-wise Grades  
- View or Edit Grades  
- Maintain academic performance table

---

## 💻 Technologies Used

| Component        | Technology                |
|------------------|----------------------------|
| Frontend         | HTML5, CSS3               |
| Backend          | JSP & Servlets (Java)     |
| IDE              | NetBeans IDE              |
| Web Server       | Apache Tomcat (via NetBeans) |
| Database         | MySQL (via XAMPP)         |
| JDBC Driver      | MySQL Connector/J         |

---

## 🗃️ Database Design

### 📁 `user_details` Table

| Field          | Type        | Description             |
|----------------|-------------|--------------------------|
| id             | INT (PK)    | Auto-increment ID       |
| name           | VARCHAR     | Full Name               |
| age            | INT         | Age                     |
| father_name    | VARCHAR     | Father's Name           |
| mother_name    | VARCHAR     | Mother's Name           |
| profile_pic    | VARCHAR     | Image file path         |
| username       | VARCHAR     | Login Username          |
| password       | VARCHAR     | Hashed Password         |

### 📘 `student_grades` Table

| Field       | Type        | Description                   |
|-------------|-------------|-------------------------------|
| id          | INT (PK)    | Grade ID                      |
| student_id  | INT (FK)    | Foreign Key to user_details   |
| subject     | VARCHAR     | Subject Name                  |
| grade       | VARCHAR     | Grade (A, B, C, etc.)         |

---

## ⚙️ Setup Instructions

### ✅ Prerequisites
- **NetBeans IDE** with **Apache Tomcat** server
- **XAMPP** (Apache + MySQL)
- **MySQL Connector/J** (JDBC driver)

---

## 🏗️ Steps to Run the Project

1. **Install XAMPP**
   - Download and install XAMPP from [XAMPP website](https://www.apachefriends.org/index.html).
   - Start **Apache** and **MySQL** from the XAMPP Control Panel.

2. **Create Database in MySQL**
   - Open `phpMyAdmin` from XAMPP (typically accessible at `http://localhost/phpmyadmin/`).
   - Create a new database called `student_db`.
   - Run the following SQL queries to create the necessary tables:

``sql
-- Create user_details table
CREATE TABLE user_details (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    age INT,
    father_name VARCHAR(100),
    mother_name VARCHAR(100),
    profile_pic VARCHAR(255),
    username VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

-- Create student_grades table
CREATE TABLE student_grades (
    id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT,
    subject VARCHAR(100),
    grade VARCHAR(10),
    FOREIGN KEY (student_id) REFERENCES user_details(id)
);
## Setup Project in NetBeans

Open NetBeans IDE.

Create a new Dynamic Web Project.

Set Apache Tomcat as the server for the project.

Import the project folder into NetBeans or create a new one with the same structure.

## Configure Database Connection

Inside your project, locate the database connection code (usually in the Servlet classes).

Use the following connection string to connect to the MySQL database:

java
Copy
Edit
```pip
String url = "jdbc:mysql://localhost:3306/student_db";
pip String user = "root";   // Default MySQL username
String password = "";   // Default MySQL password (empty in XAMPP)
```
##Build and Deploy

Clean and build the project in NetBeans.

Deploy the project to Apache Tomcat.

Open the web application in your browser at:

arduino
Copy
Edit
``` http://localhost:8080/StudentManagementSystem/
```
## 📁 Folder Structure
bash
Copy
Edit
/StudentManagementSystem
│
├── /Web Pages
│   ├── index.jsp
│   ├── login.jsp
│   ├── register.jsp
│   ├── forgotPassword.jsp
│   ├── dashboard.jsp
│   ├── profile.jsp
│   └── ...
│
├── /src
│   ├── LoginServlet.java
│   ├── RegisterServlet.java
│   ├── AdminDashboardServlet.java
│   ├── UpdateStudentServlet.java
│   └── ...
│
├── /WEB-INF
│   └── web.xml
│
└── /images
    └── Profile pictures folder
## 🔐 Security Highlights
Password hashing for user credentials (e.g., MD5 or SHA)

Session handling to prevent unauthorized access

Input validation to avoid SQL injection and errors

## 🚀 Future Enhancements
Add role-based login (Student/Admin)

Integrate email notifications for password recovery

Add pagination/search filters for admin records

Enable PDF/Excel export of student reports

Use Bootstrap or Tailwind CSS for better UI/UX

## 📚 References
JSP and Servlets – GeeksForGeeks

Apache NetBeans IDE

XAMPP Download

MySQL Connector/J
