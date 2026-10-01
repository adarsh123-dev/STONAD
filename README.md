# 🎨 STONAD – Art Showcase Platform

**STONAD** is a full-stack web-based art showcase platform designed for artists and art enthusiasts. Users can create an account, showcase artwork, explore different creations, like artworks, and interact through comments.

The project is built using **Java Servlets, JSP, JDBC, MySQL, HTML, CSS, and JavaScript** and is deployed on **Railway**.

---

## 🌐 Live Demo

🚀 **[Visit STONAD](https://stonad-production.up.railway.app/)**

---

## ✨ Features

### 👤 User Authentication

* User registration and login
* Session-based authentication
* User information stored in MySQL

### 🖼️ Artwork Showcase

* Display artwork in a modern gallery
* Artwork title and description
* Browse artworks uploaded by users
* Artist/user information

### ❤️ Like System

* Users can like artworks
* Prevents duplicate likes
* Like data stored in MySQL
* Real-time interaction with artwork

### 💬 Comment System

* Users can comment on artworks
* Comments are linked to users and artworks
* Displays commenter information
* Comments stored securely in MySQL

### 🎨 Modern UI

* Clean and minimal art-focused design
* Responsive artwork cards
* Interactive buttons
* User-friendly navigation

### ☁️ Deployment

* Hosted on Railway
* MySQL database integration
* Environment-based database configuration

---

## 🛠️ Tech Stack

| Technology        | Usage                       |
| ----------------- | --------------------------- |
| **Java**          | Backend development         |
| **Servlets**      | Request & response handling |
| **JSP**           | Dynamic web pages           |
| **JDBC**          | Database connectivity       |
| **MySQL**         | Database                    |
| **HTML5**         | Page structure              |
| **CSS3**          | UI & responsive design      |
| **JavaScript**    | Frontend interactions       |
| **Apache Tomcat** | Application server          |
| **Railway**       | Deployment                  |

---

## 🏗️ Architecture

```text
                    STONAD
                       │
                       ▼
              ┌─────────────────┐
              │    Frontend     │
              │ JSP / HTML /    │
              │ CSS / JavaScript│
              └────────┬────────┘
                       │
                       ▼
              ┌─────────────────┐
              │ Java Servlets   │
              │   Controller    │
              └────────┬────────┘
                       │
                       ▼
              ┌─────────────────┐
              │    DAO Layer    │
              │ Database Logic  │
              └────────┬────────┘
                       │
                       ▼
              ┌─────────────────┐
              │      JDBC       │
              │   Connection    │
              └────────┬────────┘
                       │
                       ▼
              ┌─────────────────┐
              │     MySQL       │
              │    Database     │
              └─────────────────┘
```

---

## 🗄️ Database

STONAD uses MySQL to manage application data.

Main tables include:

```text
users
   │
   ├── artworks
   │      │
   │      ├── artwork_likes
   │      │
   │      └── artwork_comments
   │
   └── user interactions
```

### Main Tables

* `users` – Stores user information
* `artworks` – Stores artwork details
* `artwork_likes` – Stores artwork likes
* `artwork_comments` – Stores user comments

The likes table uses a unique combination of `artwork_id` and `user_id` to prevent duplicate likes.

---

## 🔄 Application Flow

### Login / Registration

```text
User
 ↓
JSP / HTML Form
 ↓
Servlet
 ↓
DAO
 ↓
JDBC
 ↓
MySQL
```

### Artwork

```text
User
 ↓
Artwork Page
 ↓
Artwork Servlet
 ↓
Artwork DAO
 ↓
MySQL
 ↓
Display Artwork
```

### Like

```text
User clicks ❤️
       ↓
Like Servlet
       ↓
Like DAO
       ↓
artwork_likes
       ↓
MySQL
```

### Comment

```text
User writes comment
       ↓
Comment Servlet
       ↓
Comment DAO
       ↓
artwork_comments
       ↓
MySQL
```

---

## 🚀 Run Locally

### 1. Clone the repository

```bash
git clone https://github.com/adarsh123-dev/STONAD.git
cd STONAD
```

### 2. Create the MySQL database

```sql
CREATE DATABASE stonad;
```

Create the required tables inside the database.

### 3. Configure Database

For local development:

```text
Host: localhost
Port: 3306
Database: stonad
Username: root
Password: your_password
```

For production, use environment variables instead of hardcoding credentials.

### 4. Run with Tomcat

Deploy the project on **Apache Tomcat 10.x** and start the server.

Open:

```text
http://localhost:8080/STONAD/
```

---

## ☁️ Deployment

The application is deployed using **Railway**.

The database connection can be configured using environment variables such as:

```text
MYSQLHOST
MYSQLPORT
MYSQLDATABASE
MYSQLUSER
MYSQLPASSWORD
```

This keeps database credentials separate from the source code.

---

## 📚 What I Learned

While developing STONAD, I worked with:

* Java Servlet architecture
* JSP and dynamic web pages
* JDBC and MySQL
* DAO and Model architecture
* CRUD operations
* HTTP request/response handling
* Session management
* Relational database design
* Foreign keys
* Like and comment functionality
* Frontend/backend integration
* Railway deployment
* Environment variables

---

## 🔮 Future Improvements

* 🤖 AI-powered artwork search
* 🔍 Advanced artwork search and filtering
* 👤 Artist profile pages
* 🏷️ Artwork categories and tags
* 🔔 User notifications
* 📊 Artist dashboard
* ❤️ Personalized artwork recommendations
* ☁️ Cloud image storage
* 🔐 Improved authentication and password hashing

---

## 👨‍💻 Developer

### Adarsh Kumar

**B.Tech Computer Science Engineering**

Interested in **Java Backend Development, Spring Boot, SQL, and Software Development**.

🔗 **GitHub:** [@adarsh123-dev](https://github.com/adarsh123-dev)

🌐 **Live Project:** [STONAD](https://stonad-production.up.railway.app/)

---

## ⭐ Support

If you find this project interesting, consider giving the repository a ⭐ **Star**.

**Built with Java, JDBC, MySQL & creativity. 🎨**

