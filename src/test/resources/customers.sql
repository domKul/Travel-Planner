CREATE TABLE SimpleCustomerQueryDto (
                          customerId BIGINT AUTO_INCREMENT PRIMARY KEY,
                          firstName VARCHAR(255) NOT NULL,
                          lastName VARCHAR(255) NOT NULL,
                          birthdate DATE NOT NULL,
                          country VARCHAR(255) NOT NULL,
                          city VARCHAR(255) NOT NULL,
                          streetName VARCHAR(255) NOT NULL,
                          postalCode VARCHAR(10) NOT NULL,
                          email VARCHAR(255) UNIQUE NOT NULL,
                          phoneNumber INT NOT NULL,
                          CONSTRAINT email_format CHECK (email LIKE '%@%.%')
);


INSERT INTO SimpleCustomerQueryDto (firstName, lastName, birthdate, country, city, streetName, postalCode, email, phoneNumber)
VALUES
    ('John', 'Doe', '1990-01-01', 'USA', 'New York', '123 Main Street', '10001', 'john.doe@example.com', 123456789),
    ('Jane', 'Smith', '1995-05-15', 'Canada', 'Toronto', '456 Elm Street', 'M5V 2H1', 'jane.smith@example.com', 987654321);
