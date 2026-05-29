# 📬 Mail Server Web Application

A sophisticated full-stack mail management system built with **Spring Boot** and **Vue.js**. This project demonstrates a robust backend architecture utilizing modern design patterns and a responsive, intuitive frontend.

---

## 🚀 Key Features

### 📧 Email Management
- **Compose & Send**: Send emails to multiple recipients with ease.
- **Drafts Support**: Save your progress and resume later.
- **Attachments**: Support for sending and viewing file attachments.
- **Folders**: Organize your mail with **Inbox, Sent, Drafts, and Trash**.
- **Bulk Operations**: Select multiple emails for bulk deletion or moving between folders.
- **Trash Recovery**: Accidentally deleted an email? Retrieve it easily from the Trash.
- **Priority Inbox**: Mark important emails to keep them at the top.

### 👤 User & Contact Management
- **Secure Authentication**: Robust Sign-up and Login system.
- **Contact Book**: Full CRUD operations (Add, Edit, Delete) for your personal contacts.
- **Search & Filter**: Quickly find specific contacts or emails using advanced search criteria.

### 🛠 Technical Excellence
- **Advanced Pagination**: Smooth data loading for thousands of emails and contacts.
- **Filtering & Sorting**: Sort by date, sender, importance, or subject. Filter emails based on multiple attributes.

---

## 🏗 Architectural Design Patterns

This project highlights the implementation of several Gang of Four (GoF) design patterns to ensure scalability and maintainability:

1.  **Prototype**: Used for duplicating emails efficiently when sending to multiple recipients or moving across folders.
2.  **Adapter**: Bridges the gap between user requests (page numbers) and backend logic (data indices) for seamless pagination.
3.  **Iterator**: Provides a clean way to traverse through collections of users, emails, and folders.
4.  **Proxy**: Handles CRUD operations and access control for emails and user profiles.
5.  **Filter/Criteria**: Allows dynamic filtering of emails based on subject, sender, importance, and more.
6.  **Facade**: Simplifies complex backend operations into a unified interface for the frontend to consume.

---

## 💻 Tech Stack

### Frontend
- **Framework**: Vue.js 2
- **UI Library**: Vuetify (Material Design)
- **State Management**: Vuex
- **Routing**: Vue Router
- **HTTP Client**: Axios

### Backend
- **Framework**: Spring Boot 2.4.1
- **Language**: Java 11
- **Build Tool**: Maven
- **Persistence**: File-based storage (Structured JSON/File system)

---

## ⚙️ Installation & Setup

### Prerequisites
- **Java 11** or higher.
- **Node.js & npm**.
- **Maven**.

### Running the Backend
1. Navigate to the `Back-end/eg.edu.alexu.csd.oop.email.cs231.thisisit.endone` directory.
2. Run the following command:
   ```bash
   mvn spring-boot:run
   ```
   *The server will start on `http://localhost:8080`.*

### Running the Frontend
1. Navigate to the `Front-end` directory.
2. Install dependencies:
   ```bash
   npm install
   ```
3. Run the development server:
   ```bash
   npm run serve
   ```
   *The application will be available at `http://localhost:8081`.*

---

---
