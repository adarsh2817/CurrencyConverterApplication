# 💱 Currency Converter Application

A lightweight Spring Boot application that provides real-time currency conversion by integrating with an external exchange rate API. This project is built without any JPA entities or repository interfaces – it simply fetches live data from an external API and performs conversions.

---

## 🚀 Features

- **GET /rates?base={currency}**  
  Retrieves current exchange rates for the given base currency (defaults to "USD").

- **POST /convert**  
  Converts a specified amount from one currency to another using live exchange rates.

---

## 🛠 Technologies

- **Spring Boot** (Web, DevTools, Security)
- **Maven** for dependency management
- **RestTemplate** for external API integration
- **JUnit 5** and **Mockito** for testing

---

## 🛠 Getting Started

### Prerequisites

- JDK 17 (or later)
- Maven 3.6+
- An internet connection (to fetch exchange rates from the external API)

### Running the Application Locally

1. **Clone the Repository:**

   ```bash
   git clone https://github.com/yourusername/currency-converter.git
   cd currency-converter
   ```

2. **Build the Project:**

   ```bash
   mvn clean install
   ```

3. **Run the Application:**

   ```bash
   mvn spring-boot:run
   ```

   The application will start on port **8080**.

4. **Test the Endpoints with Postman:**

   - **GET Exchange Rates:**
     - **URL:** `http://localhost:8080/rates?base=USD`
     - **Method:** GET

   - **POST Currency Conversion:**
     - **URL:** `http://localhost:8080/convert`
     - **Method:** POST
     - **Headers:**  
       `Content-Type: application/json`
     - **Body Example:**
       ```json
       {
         "from": "USD",
         "to": "EUR",
         "amount": 100
       }
       ```

---

## 📚 API Documentation

### 1. GET /rates

**Description:**  
Fetches live exchange rates for a specified base currency. If no base is specified, the default is "USD".

**Request:**

- **URL:** `http://localhost:8080/rates?base=USD`
- **Method:** GET

**Successful Response (HTTP 200):**

```json
{
  "rates": {
    "EUR": 0.95,
    "GBP": 0.82,
    "INR": 82.5,
    "...": "..."
  },
  "base": "USD",
  "date": "2024-06-01"
}
```

**Error Response (External API Unavailable):**  
If the external API is down, the service returns HTTP status **503**:

```json
{
  "error": "External API is unavailable: <error details>"
}
```

---

### 2. POST /convert

**Description:**  
Converts a specified amount from one currency to another using live exchange rates.

**Request:**

- **URL:** `http://localhost:8080/convert`
- **Method:** POST
- **Headers:**  
  - `Content-Type: application/json`
- **Request Body Example:**

  ```json
  {
    "from": "USD",
    "to": "EUR",
    "amount": 100
  }
  ```

**Successful Response (HTTP 200):**

```json
{
  "from": "USD",
  "to": "EUR",
  "amount": 100,
  "convertedAmount": 95.0
}
```

**Error Responses:**

- **Invalid Currency Code (HTTP 400):**

  ```json
  {
    "error": "Invalid target currency code: <currency_code>"
  }
  ```

- **External API Unavailable (HTTP 503):**

  ```json
  {
    "error": "External API is unavailable: <error details>"
  }
  ```

---

## 💽 Project Structure

```
currency-converter/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/ty/currencyconverter/
│   │   │       ├── controller/
│   │   │       │   └── CurrencyController.java
│   │   │       ├── dto/
│   │   │       │   ├── ConversionRequest.java
│   │   │       │   └── ConversionResponse.java
│   │   │       └── service/
│   │   │           ├── CurrencyService.java
│   │   │           └── AppConfig.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/
│           └── com/ty/currencyconverter/
│               └── CurrencyConverterApplicationTests.java
├── pom.xml
└── README.md
```

---

