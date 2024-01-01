# Price Challenge

This web project is developed using Java with Spring Boot. It enables to get a price given certains conditions.

## Some assumptions
It was decided to use a date format compatible with the LocalDateTime class. This is the ISO format '2020-12-31T23:59:59'.

## Technologies Used

- Java 11
- Spring Boot
- Embedded H2 database
- Lombok
- Maven
- It was started using Api First design methodology with Openapi 3.0

## Installation

To run this project locally, follow these steps:

1. Clone this repository to your local machine.
    ```bash
    git clone git@github.com:hnfiorini/price-challenge.git
    ```
2. Make sure you have Java and Maven installed on your system.
3. Open a terminal in the project's root directory.
4. Run the following command to compile the project:

    ```bash
    mvn clean install
    ```
## Execution

Once you've done the installation:

1. Run the application using the following command:

    ```bash
    mvn spring-boot:run
    ```

2. Open a web browser and go to  
`http://localhost:8080/prices?dateToBeApplied=2020-06-14T10:00:00&productId=35455&brandId=1`  
to see the application in action.
or you can use this curl command
    ```bash
    curl -X GET "http://localhost:8080/prices?dateToBeApplied=2020-06-14T10:00:00&productId=35455&brandId=1"
    ```

## Test
You can run the test with this command
```bash
mvn test
```

## Swagger

You can view the documentation of this application api in
http://localhost:8080/swagger-ui/index.html#/

## License

Nicolas Fiorini
**Free Software, Hell Yeah!**
