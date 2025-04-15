# 🎓 Advanced Internet Programming Project  
## 🧑‍💻 Student Management System with Authentication and Admin Dashboard

### 📝 Project Title:
**Secure Student Management System using Python and MySQL with GUI Integration**

---




---

## 🧾 Project Overview

This project presents a **Student Management System** designed with a secure login interface, registration module, and a fully-functional **Admin Dashboard** to manage user data. The system performs complete **CRUD operations**, maintains student academic records, and allows users to update personal details securely.

This application demonstrates the principles of **Advanced Internet Programming** through seamless integration of frontend GUI (Tkinter), backend logic (Python), and a MySQL relational database.

---

## 🎯 Objectives

- To implement secure **user authentication** (Login, Registration, and Forgot Password)
- To design an **interactive admin dashboard** for student data management
- To perform **CRUD operations** on student records using a database
- To manage **subject-wise academic grades**
- To integrate **GUI with backend and database** to reflect Internet programming concepts

---

## 🧩 Functional Modules

### 1. 🔐 User Authentication
- User Login  
- Registration of new users  
- Forgot Password (Reset mechanism)

### 2. 🧑‍💼 Admin Dashboard
- View all registered users with:
  - Name, Age  
  - Father’s and Mother’s Name  
  - Profile Picture  
- Edit User Details  
- View Full Profile  
- Delete Student Record  

### 3. 📚 Academic Grades
- Add/View subject-wise grades  
- Manage grades using `student_grades` table

---

## 🛠️ Technologies Used

| Component        | Technology              |
|------------------|--------------------------|
| Frontend GUI     | Python Tkinter           |
| Backend Logic    | Python                   |
| Database         | MySQL                    |
| DB Connector     | mysql-connector-python   |
| Image Handling   | Pillow (PIL)             |

---

## 🧮 Database Design

### 📁 `user_details` Table

| Field          | Type        | Description             |
|----------------|-------------|--------------------------|
| id             | INT (PK)    | Auto Incremented ID     |
| name           | VARCHAR     | Student Full Name       |
| age            | INT         | Student Age             |
| father_name    | VARCHAR     | Father's Name           |
| mother_name    | VARCHAR     | Mother's Name           |
| profile_pic    | VARCHAR     | Path to profile image   |
| username       | VARCHAR     | Login Username          |
| password       | VARCHAR     | Hashed Password         |

---

### 📘 `student_grades` Table

| Field       | Type        | Description               |
|-------------|-------------|----------------------------|
| id          | INT (PK)    | Grade Entry ID            |
| student_id  | INT (FK)    | Linked to user_details.id |
| subject     | VARCHAR     | Subject Name              |
| grade       | VARCHAR     | Grade (A/B/C/...)         |

---

## ⚙️ How to Run the Project

### 1. 📦 Install Required Packages
```bash
pip npm install express mongoose dotenv bcrypt jsonwebtoken
pip npm init -y
pip node aap.js
