-- Insert sample users
INSERT INTO users (name, email, password) VALUES
('Alice Johnson', 'alice@example.com', '$2a$12$uwwjnGfG79NUTQXCIItSFOQyEq3VN17rTcnODZiRYF2TEgAp.FiFu'),
('Bob Smith', 'bob@example.com', '$2a$10$abc123xyz456abc123xyz456abc123xyz456abc123xyz456abc123'),
('Charlie Brown', 'charlie@example.com', '$2a$10$def789ghi012def789ghi012def789ghi012def789ghi012def789');
-- Insert sample products
INSERT INTO products (name, price) VALUES
('Laptop', 75000.00),
('Smartphone', 35000.00),
('Headphones', 2500.00),
('Keyboard', 1500.00),
('Monitor', 12000.00);

-- Insert sample orders
INSERT INTO orders (user_id, order_date) VALUES
(1, CURRENT_TIMESTAMP),
(2, CURRENT_TIMESTAMP),
(3, CURRENT_TIMESTAMP);

-- Insert sample order-product relationships
INSERT INTO order_products (order_id, product_id) VALUES
(1, 1), -- Alice bought Laptop
(1, 3), -- Alice bought Headphones
(2, 2), -- Bob bought Smartphone
(2, 4), -- Bob bought Keyboard
(3, 5); -- Charlie bought Monitor