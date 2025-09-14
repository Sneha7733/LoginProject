# 🚀 Selenium Login Automation Project

## 📌 Project Description
This project automates the testing of a login form on the demo website:  
👉 [The Internet - Login Page](https://the-internet.herokuapp.com/login)

We implement test cases using **Selenium WebDriver (Java)** and **TestNG**.  
Additionally, a **data-driven test** is included that reads test data from a CSV file.

---

## ✅ Test Cases Implemented

### 1. **Valid Login**
- Username: `tomsmith`
- Password: `SuperSecretPassword!`
- ✅ Expected: Redirect to `/secure` page.

### 2. **Invalid Login**
- Username: `wrongusername`
- Password: `wrongpassword`
- ❌ Expected: Error message `"Your username is invalid!"`.

### 3. **Empty Fields**
- Username: *(blank)*
- Password: *(blank)*
- ⚠️ Expected (as per assignment): `"This field is required"`.

### 4. **Bonus: Data-Driven Test (CSV)**
- Reads multiple login attempts from a CSV file (`testdata.csv`).
- Executes tests automatically for **valid**, **invalid**, and **empty fields** cases.

---

## 🛠️ Tools & Technologies
- **Java 11+**
- **Maven**
- **Selenium WebDriver**
- **TestNG**
- **WebDriverManager** (auto-manages ChromeDriver)
- **IntelliJ IDEA / Eclipse** (recommended IDEs)

---

## 📂 Project Structure
selenium-login-project/
│
├── pom.xml
├── README.md
│
├── src/main/java/com/selenium/login/
│ ├── LoginPage.java
│ └── testdata.csv <-- CSV for data-driven test
│
└── src/test/java/tests/
├── LoginTest.java
└── LoginDataDrivenTest.java


---

## ⚙️ Setup Instructions

1. **Clone or Create Project**
   ```bash
   git clone <repo-url>
   cd SeleniumLoginProject

2. **Open in IntelliJ IDEA**

- **File → Open → select pom.xml**
- **Mark src/main/java as Sources Root**
- **Mark src/test/java as Test Sources Root**

3. **Maven Dependencies**

- **IntelliJ will auto-import dependencies from pom.xml.**
- **Or run:**
  mvn clean install

## ▶️ Running Tests

**Run from IntelliJ**

- **Right-click LoginTest.java → Run 'LoginTest'**
- **Right-click LoginDataDrivenTest.java → Run 'LoginDataDrivenTest'**
