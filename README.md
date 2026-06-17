# Automation Engineering Assignment

### Overview

This project is an end-to-end Selenium automation framework developed using Java, Selenium WebDriver, TestNG, and the Page Object Model (POM) design pattern. The framework automates Amazon product search and add-to-cart scenarios and supports execution in both local environments and LambdaTest cloud infrastructure.

**Tech Stack**

- Java 21.0.9
- Selenium WebDriver
- TestNG
- Maven
- Page Object Model (POM)
- WebDriverManager
- Extent Reports
- LambdaTest (Cloud Execution)

**Project Features**

- Driver Factory Pattern 
- Page Object Model (POM)
- Local and Cloud Execution Support
- LambdaTest Integration 
- Extent Reporting
- Maven Build Management

**Test Scenarios**

1. AmazonIphoneTest
2. Navigate to Amazon.
3. Search for "iPhone".
4. Open the first product.
5. Capture and validate the product price.
6. Add the product to the cart.

1. AmazonGalaxyTest
2. Navigate to Amazon.
3. Search for "Galaxy".
4. Open the first product.
5. Capture and validate the product price.
6. Add the product to the cart.

**Prerequisites**

- Java 21.0.9
- Maven 3.9.11
- Git

**Clone the Repository**

```bash
- git clone https://github.com/Sasikumar411/AutomationEngineeringAssignment.git
- cd AutomationEngineeringAssignment
```

**Running Tests Locally**

```bash
- mvn clean test
```

**Running Tests on LambdaTest**

Set the following environment variables:

```bash
- export LT_USERNAME=your_username
- export LT_ACCESS_KEY=your_access_key
```

Execute the tests:

```bash
- mvn clean test -DexecutionType=cloud
```
**Reports**

Extent Reports are generated under:
- reports/

**Author**
- Sasikumar Pichairaj
- QA Automation Engineer




