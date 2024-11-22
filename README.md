# Java JPA Project - Agriculture Resource Management 🌟

A Java Spring Boot project for managing agricultural resources. This project demonstrates CRUD operations for managing employees, suppliers (local and international), and farms, all integrated into a user-friendly dashboard using Vaadin.

## Features 📋

### Core Functionalities ✅

- **Employee Management** 👨‍💼
  - Add, update, delete, and view employee records
  - Manage details like name, position, and salary

- **Supplier Management** 🛒
  - Handle both local and international suppliers
  - Maintain comprehensive contact details

- **Farm Management** 🌾
  - Create and edit farm profiles
  - Assign employees as farm managers
  - Track farm details and status

- **Preloaded Data**
  - Includes sample data for testing and demonstration
  - Pre-configured employees, suppliers, and farms

- **Dashboard Overview** 🖥️
  - Intuitive dashboard displaying key system resources
  - Real-time resource management statistics

## Technologies Used 🛠️

### Backend
- Java (Spring Boot, JPA, Hibernate) ☕
- MySQL database (or any other relational database)
- Maven for dependency management

### Frontend
- Vaadin for building interactive user interfaces 🎨

## Project Structure 🏗️

### Backend Components

#### Entities
- `Employee` - Employee details (name, position, salary)
- `Farm` - Farm information (location, type, manager)
- `Supplier` - Parent entity
  - `LocalSupplier` - Local supplier subclass
  - `InternationalSupplier` - International supplier subclass

#### Repositories
- `EmployeeRepository`
- `FarmRepository`
- `SupplierRepository`

#### Services
- `EmployeeService`
- `FarmService`
- `SupplierService`

#### Data Initialization
- Sample data loaded via `CommandLineRunner` in `AgcApplication`

### Frontend Components

#### Views
- `EmployeeView` - Employee management interface
- `FarmView` - Farm management and manager assignment
- `SupplierView` - Supplier management interface

#### Layouts
- `MainLayout` - Main navigation structure

## Installation and Setup 💻

1. **Clone the Repository**
   ```bash
   git clone https://github.com/your-username/agriculture-management.git
   ```

2. **Configure Database**
   
   Update `application.properties`:
   ```properties
   # Database connection details
   spring.datasource.url=jdbc:mysql://localhost:3306/agriculture_db
   spring.datasource.username=your_username
   spring.datasource.password=your_password

   # Hibernate settings
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
   ```

3. **Build the Project**
   ```bash
   mvn clean install
   ```

4. **Run the Application**
   ```bash
   mvn spring-boot:run
   ```

5. **Access the Application**
   
   Open your browser and navigate to: `http://localhost:8080`

## Sample Data ✨

### Employees 👨‍💼
- **Employee 1**
  - Name: "Angajat 1"
  - Position: Tractor Operator

- **Employee 2**
  - Name: "Angajat 2"
  - Position: Manager

### Suppliers 🛒
- **Local Supplier**
  - Name: "AGRICOVER"
  - Contact: 0745215376

- **International Supplier**
  - Name: "INGRASAMINTE"
  - Contact: 0743623434

### Farms 🌾
- Name: "Farma 1"
- Location: Iasi
- Manager: "Angajat 2"

## Future Improvements 🎯

1. **Authentication** 🔒
   - Implement secure login functionality
   - Role-based access control

2. **Reporting** 📊
   - Generate detailed resource reports
   - Export capabilities for farms, employees, and suppliers

3. **Advanced Filtering** 🔍
   - Dynamic search functionality
   - Complex filtering for large datasets

4. **Enhanced UI** 🎨
   - Improved user interface design
   - Better user experience and accessibility
