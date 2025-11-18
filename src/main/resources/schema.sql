-----------------------------------------
-- USERS TABLE
-----------------------------------------
CREATE TABLE users (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL
);

-----------------------------------------
-- PRODUCTS TABLE
-----------------------------------------
CREATE TABLE products (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    price DECIMAL(10,2) NOT NULL
);

-----------------------------------------
-- ORDERS TABLE
-----------------------------------------
CREATE TABLE orders (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT NOT NULL,
    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

-----------------------------------------
-- ORDER_PRODUCTS TABLE (Many-to-Many)
-----------------------------------------
CREATE TABLE order_products (
    order_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    PRIMARY KEY (order_id, product_id),
    FOREIGN KEY (order_id) REFERENCES orders(id),
    FOREIGN KEY (product_id) REFERENCES products(id)
);