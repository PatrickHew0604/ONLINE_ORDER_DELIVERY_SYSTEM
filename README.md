# APU Convenience Store Management System

Welcome to the APU Convenience Store Management System!  
This is a Java EE web application designed for managing a convenience store, including customer, staff, product, and order management, as well as generating various business reports.

---

## 📁 Project Structure

```
EPDAAssignment-ejb/      # EJB module: business logic, models, facades
EPDAAssignment-war/      # WAR module: web layer, servlets, JSPs, controllers
build/                   # Build outputs (JAR, WAR, etc.)
src/                     # Root source directory
nbproject/               # NetBeans project files
```

---

## 🚀 Features

- **User Authentication**: Secure login and registration for customers, staff, and delivery staff.
- **Customer Management**: Add, update, and delete customer profiles.
- **Staff Management**: Manage staff accounts and roles.
- **Product Management**: Add, update, and delete products.
- **Order Management**: Place, assign, and track orders.
- **Reporting**: Generate reports on gender distribution, age, customer spending, product sales, and monthly earnings.
- **Responsive UI**: Modern, user-friendly interface using JSP and CSS.

---

## 🛠️ Technologies Used

- **Java EE (Servlets, JSP, EJB)**
- **JPA (Java Persistence API)**
- **JSTL (JavaServer Pages Standard Tag Library)**
- **Chart.js** for data visualization in reports
- **NetBeans/Ant** for project management and builds

---

## ⚙️ How to Build & Run

1. **Requirements**
   - JDK 8+
   - GlassFish or compatible Java EE server
   - NetBeans IDE (recommended)

2. **Build Steps**
   - Open the project in NetBeans.
   - Clean and build the project (`Build > Clean and Build Project`).
   - Deploy the `EPDAAssignment-war` module to your application server.

3. **Access the Application**
   - Open your browser and navigate to:  
     `http://localhost:8080/EPDAAssignment-war/`

---

## 📊 Reports

- **Gender Report**: `/GenderReport`
- **Age Report**: `/AgeReport`
- **Customer Spending Report**: `/CustomerSpendingReport`
- **Product Sold Report**: `/ProductSoldReport`
- **Monthly Earnings Report**: `/MonthlyEarningsReport`

Accessible from the [generateReport.jsp](EPDAAssignment-war/web/generateReport.jsp) page.

---

## 👤 Main Modules

- **EJB Models**:  
  - [`models.MyUser`](EPDAAssignment-ejb/src/java/models/MyUser.java)  
  - [`models.MyOrder`](EPDAAssignment-ejb/src/java/models/MyOrder.java)  
  - [`models.Feedback`](EPDAAssignment-ejb/src/java/models/Feedback.java)  
  - [`models.Receipt`](EPDAAssignment-ejb/src/java/models/Receipt.java)

- **Controllers**:  
  - [`controller.Register`](EPDAAssignment-war/src/java/controller/Register.java)  
  - [`controller.managingstaff.AddNewStaff`](EPDAAssignment-war/src/java/controller/managingstaff/AddNewStaff.java)  
  - [`controller.managingstaff.DeleteCustomer`](EPDAAssignment-war/src/java/controller/managingstaff/DeleteCustomer.java)  
  - [`controller.managingstaff.GenderReport`](EPDAAssignment-war/src/java/controller/managingstaff/GenderReport.java)  
  - [`controller.customer.CustomerMainPage`](EPDAAssignment-war/src/java/controller/customer/CustomerMainPage.java)

- **JSP Pages**:  
  - [login.jsp](EPDAAssignment-war/web/login.jsp)
  - [register.jsp](EPDAAssignment-war/web/register.jsp)
  - [managingstaffmainpage.jsp](EPDAAssignment-war/web/managingstaffmainpage.jsp)
  - [managecustomer.jsp](EPDAAssignment-war/web/managecustomer.jsp)
  - [managestaff.jsp](EPDAAssignment-war/web/managestaff.jsp)
  - [generateReport.jsp](EPDAAssignment-war/web/generateReport.jsp)
  - [genderReport.jsp](EPDAAssignment-war/web/genderReport.jsp)
  - [monthlyEarningsReport.jsp](EPDAAssignment-war/web/monthlyEarningsReport.jsp)
  - [customerSpendingReport.jsp](EPDAAssignment-war/web/customerSpendingReport.jsp)
  - [productSoldReport.jsp](EPDAAssignment-war/web/productSoldReport.jsp)

---

## 📄 License

This project is for academic use at Asia Pacific University of Technology And Innovation (APU).

---

## 🙏 Acknowledgements

- Developed by Patrick
- Asia Pacific University of Technology And Innovation (APU)

---

Enjoy managing your convenience store with ease!