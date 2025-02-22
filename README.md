# Currency Converter Application

A lightweight Spring Boot application that provides real-time currency conversion by integrating with an external exchange rate API. This project is built without any JPA entities or repository interfaces – it simply fetches live data from an external API and performs conversions.

## Features

- **GET /rates?base={currency}**  
  Retrieves current exchange rates for the given base currency (defaults to "USD").

- **POST /convert**  
  Converts a specified amount from one currency to another using live exchange rates.

## Technologies

- **Spring Boot** (Web, DevTools, Security)
- **Maven** for dependency management
- **RestTemplate** for external API integration
- **JUnit 5** and **Mockito** for testing

## Getting Started

### Prerequisites

- JDK 17 (or later)
- Maven 3.6+  
- An internet connection to fetch exchange rates from the external API

### Running the Application Locally

1. **Clone the Repository:**

   ```bash
   git clone https://github.com/yourusername/currency-converter.git
   cd currency-converter
2. **Build the Project:**

  ```bash
  mvn clean install
3. **Run the Application:**

  ```bash
  mvn spring-boot:run
The application will start on port 8080.

### Testing with Postman
    **GET Exchange Rates:**
        **URL:**

        ```bash
        http://localhost:8080/rates?base=USD
        **Method: GET**

        **POST Currency Conversion:**
          **URL:**

          ```bash
          http://localhost:8080/convert
          **Method: POST**

      **Headers:**
        **Content-Type: application/json**

      **Body Example:**

        ```json
        {
            "from": "USD",
            "to": "EUR",
            "amount": 100
        }







