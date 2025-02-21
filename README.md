# CurrencyConverterApplication

This Spring Boot application provides real-time currency conversion by integrating with an external exchange rate API. The application is built without any JPA entities or repository interfaces; it simply fetches live data from the external API and performs conversions.

## Features

- **GET /api/rates?base={currency}:** Returns the current exchange rates for the given base currency (default: USD).
- **POST /api/convert:** Converts a specified amount from one currency to another.  
  **Request Body Example:**
  ```json
  {
    "from": "USD",
    "to": "EUR",
    "amount": 100
  }

 **Response Example:**
 ```json
{
  "from": "USD",
  "to": "EUR",
  "amount": 100,
  "convertedAmount": 95.0
}



