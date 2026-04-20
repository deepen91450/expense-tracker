# 💰 Expense Tracker (Spring Boot | Deployed)

A full-stack **Expense Tracker Web Application** built using **Spring Boot, MySQL, and Thymeleaf** to help users manage daily expenses efficiently.

This project demonstrates real-world backend development, database integration, and cloud deployment using modern tools and best practices.

---

## 🚀 Live Demo

👉 https://expense-tracker-production-20ea.up.railway.app/

---

## 📌 Key Features

* ➕ Add, update, and delete expenses
* 📋 View all transactions in a structured format
* 📊 Efficient data management using MySQL
* 🌐 Responsive UI using Thymeleaf
* ☁️ Cloud deployment on Railway
* 🔄 Continuous Deployment via GitHub

---

## 🛠️ Tech Stack

| Layer        | Technology                  |
| ------------ | --------------------------- |
| Language     | Java                        |
| Backend      | Spring Boot, Spring MVC     |
| Database     | MySQL                       |
| ORM          | Spring Data JPA (Hibernate) |
| Frontend     | Thymeleaf, HTML, CSS        |
| Build Tool   | Maven                       |
| Version Ctrl | Git, GitHub                 |
| Deployment   | Railway                     |

---

## 🏗️ Architecture

This project follows the **MVC (Model-View-Controller)** architecture:

```
Controller → Handles HTTP requests  
Service → Business logic  
Repository → Database interaction  
Model → Entity classes  
View → Thymeleaf templates  
```

---

## 📂 Project Structure

```
src/main/java/com/example/expensetracker/
├── controller/
├── service/
├── repository/
├── model/
└── ExpenseTrackerApplication.java

src/main/resources/
├── templates/
├── static/
└── application.properties
```

---

## ⚙️ Getting Started

### 1️⃣ Clone Repository

```bash
git clone https://github.com/deepen91450/expense-tracker.git
cd expense-tracker
```

---

### 2️⃣ Configure Database

Update your `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/expense_tracker
spring.datasource.username=your_username
spring.datasource.password=your_password
```

---

### 3️⃣ Run Application

```bash
mvn spring-boot:run
```

---

## 🌐 Deployment

The application is deployed on **Railway** with:

* Cloud-hosted MySQL database
* Environment variable configuration
* Automatic deployment using GitHub (CI/CD)

---

## 🧪 Testing

* Performed manual testing for all CRUD functionalities
* Validated database operations and data consistency
* Tested end-to-end user workflows

---

## 📸 Screenshots


* Home page
  <img width="1366" height="768" alt="Screenshot (557)" src="https://github.com/user-attachments/assets/8ea51017-9232-4b50-837b-5e92a97e811d" />
  
* Login Page
  <img width="1366" height="768" alt="Screenshot (560)" src="https://github.com/user-attachments/assets/755a6caa-72da-49d8-a355-189d95cf43e1" />
  
* Register
  <img width="1366" height="768" alt="Screenshot (558)" src="https://github.com/user-attachments/assets/6e634b63-e3d6-477f-97ba-f906ea074ba1" />
  
* Add expense page
  <img width="1366" height="768" alt="image" src="https://github.com/user-attachments/assets/330bd39e-9580-48b6-aeb1-d31ea9f2b772" />
  
* Expense list
  <img width="1366" height="768" alt="image" src="https://github.com/user-attachments/assets/f305469f-b1ab-4708-8505-c01e28873c12" />
  
* Export CSV
  <img width="1366" height="768" alt="image" src="https://github.com/user-attachments/assets/2464b650-f0e0-4bf4-a2e1-5a619dfaddc3" />
  <img width="1366" height="768" alt="image" src="https://github.com/user-attachments/assets/9c42b37d-ce60-4a44-b52b-5c1acbe54e35" />
  
* Export PDF
  <img width="1366" height="768" alt="image" src="https://github.com/user-attachments/assets/a7ee1e5e-f868-4690-87d7-c187b779229b" />
  
* View Trash
  <img width="1366" height="768" alt="image" src="https://github.com/user-attachments/assets/3421617a-0d3b-470d-9573-6aed2fa8f7ec" />
  
* Manage Categories
  <img width="1366" height="768" alt="image" src="https://github.com/user-attachments/assets/0c451d91-50ce-48ed-883d-25f31b26130c" />
---

## 📚 What I Learned

* Building scalable backend using Spring Boot
* Implementing MVC architecture
* Database integration with JPA/Hibernate
* Cloud deployment using Railway
* CI/CD fundamentals using GitHub

---

## 🤝 Contribution

Contributions are welcome! Feel free to fork and improve.

---

## 👨‍💻 Author

**Deepen Mandve**
🔗 GitHub: https://github.com/deepen91450
