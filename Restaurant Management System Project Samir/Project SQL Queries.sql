CREATE DATABASE restaurant_management;

-- Use the newly created database
USE restaurant_management;

-- Create Menu Items table
CREATE TABLE menu_items (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    category VARCHAR(50) NOT NULL,
    price DECIMAL(10, 2) NOT NULL
);

-- Create Customers table
CREATE TABLE customers (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    phone VARCHAR(15),
    email VARCHAR(100),
    address VARCHAR(255)
);

-- Create Orders table
CREATE TABLE orders (
    id INT AUTO_INCREMENT PRIMARY KEY,
    customer_id INT,
    total_cost DECIMAL(10, 2),
    order_status VARCHAR(50),
    payment_status VARCHAR(50),
    FOREIGN KEY (customer_id) REFERENCES customers(id)
);

-- Create Reservations table
CREATE TABLE reservations (
    id INT AUTO_INCREMENT PRIMARY KEY,
    customer_id INT,
    table_type VARCHAR(50),
    reservation_time DATETIME,
    FOREIGN KEY (customer_id) REFERENCES customers(id)
);



INSERT INTO menu_items VALUES (1, "Pizza", "Main Course", 200);
INSERT INTO menu_items VALUES (2, "Burger", "Main Course", 150);
INSERT INTO menu_items VALUES (3, "Pasta", "Main Course", 180);
INSERT INTO menu_items VALUES (4, "Salad", "Appetizer", 100);
INSERT INTO menu_items VALUES (5, "Soup", "Appetizer", 80);
INSERT INTO menu_items VALUES (6, "Grilled Chicken", "Main Course", 220);
INSERT INTO menu_items VALUES (7, "Vegetarian Burger", "Main Course", 160);
INSERT INTO menu_items VALUES (8, "Sushi", "Main Course", 250);
INSERT INTO menu_items VALUES (9, "Cheeseburger", "Main Course", 170);
INSERT INTO menu_items VALUES (10, "Tacos", "Main Course", 130);
INSERT INTO menu_items VALUES (11, "Spaghetti Bolognese", "Main Course", 200);
INSERT INTO menu_items VALUES (12, "Caesar Salad", "Appetizer", 120);
INSERT INTO menu_items VALUES (13, "Spring Rolls", "Appetizer", 90);
INSERT INTO menu_items VALUES (14, "Fish & Chips", "Main Course", 210);
INSERT INTO menu_items VALUES (15, "Falafel", "Appetizer", 70);
INSERT INTO menu_items VALUES (16, "Grilled Salmon", "Main Course", 250);
INSERT INTO menu_items VALUES (17, "Steak", "Main Course", 300);
INSERT INTO menu_items VALUES (18, "Mushroom Risotto", "Main Course", 230);
INSERT INTO menu_items VALUES (19, "Eggplant Parmesan", "Main Course", 200);
INSERT INTO menu_items VALUES (20, "Chicken Nuggets", "Appetizer", 110);
INSERT INTO menu_items VALUES (21, "Lamb Chops", "Main Course", 280);
INSERT INTO menu_items VALUES (22, "Vegetable Stir Fry", "Main Course", 170);
INSERT INTO menu_items VALUES (23, "Fried Rice", "Side Dish", 100);
INSERT INTO menu_items VALUES (24, "Garlic Bread", "Side Dish", 60);
INSERT INTO menu_items VALUES (25, "Onion Rings", "Side Dish", 80);
INSERT INTO menu_items VALUES (26, "Mozzarella Sticks", "Appetizer", 120);
INSERT INTO menu_items VALUES (27, "Prawn Cocktail", "Appetizer", 150);
INSERT INTO menu_items VALUES (28, "Beef Burritos", "Main Course", 190);
INSERT INTO menu_items VALUES (29, "Chicken Wings", "Appetizer", 140);
INSERT INTO menu_items VALUES (30, "Vegetable Pizza", "Main Course", 210);
INSERT INTO menu_items VALUES (31, "Chicken Alfredo", "Main Course", 240);
INSERT INTO menu_items VALUES (32, "Beef Steak", "Main Course", 300);
INSERT INTO menu_items VALUES (33, "Peking Duck", "Main Course", 350);
INSERT INTO menu_items VALUES (34, "Lobster Tail", "Main Course", 400);
INSERT INTO menu_items VALUES (35, "Clams", "Appetizer", 180);
INSERT INTO menu_items VALUES (36, "Chicken Caesar Wrap", "Main Course", 200);
INSERT INTO menu_items VALUES (37, "Roast Beef Sandwich", "Main Course", 150);
INSERT INTO menu_items VALUES (38, "Quinoa Salad", "Side Dish", 130);
INSERT INTO menu_items VALUES (39, "Rice Pudding", "Dessert", 100);
INSERT INTO menu_items VALUES (40, "Chocolate Cake", "Dessert", 120);
INSERT INTO menu_items VALUES (41, "Fruit Tart", "Dessert", 130);
INSERT INTO menu_items VALUES (42, "Apple Pie", "Dessert", 110);
INSERT INTO menu_items VALUES (43, "Ice Cream Sundae", "Dessert", 140);
INSERT INTO menu_items VALUES (44, "Lemon Sorbet", "Dessert", 100);
INSERT INTO menu_items VALUES (45, "Panna Cotta", "Dessert", 150);
INSERT INTO menu_items VALUES (46, "Cheesecake", "Dessert", 180);
INSERT INTO menu_items VALUES (47, "Brownies", "Dessert", 90);
INSERT INTO menu_items VALUES (48, "Tiramisu", "Dessert", 160);
INSERT INTO menu_items VALUES (49, "Creme Brulee", "Dessert", 200);
INSERT INTO menu_items VALUES (50, "Gelato", "Dessert", 120);
INSERT INTO menu_items VALUES (51, "Coffee", "Beverage", 50);
INSERT INTO menu_items VALUES (52, "Tea", "Beverage", 40);
INSERT INTO menu_items VALUES (53, "Lemonade", "Beverage", 60);
INSERT INTO menu_items VALUES (54, "Orange Juice", "Beverage", 70);
INSERT INTO menu_items VALUES (55, "Coca-Cola", "Beverage", 30);
INSERT INTO menu_items VALUES (56, "Pepsi", "Beverage", 30);
INSERT INTO menu_items VALUES (57, "Mineral Water", "Beverage", 20);
INSERT INTO menu_items VALUES (58, "Iced Coffee", "Beverage", 90);
INSERT INTO menu_items VALUES (59, "Milkshake", "Beverage", 110);
INSERT INTO menu_items VALUES (60, "Smoothie", "Beverage", 120);

-- Insert 60 entries into the customers table
INSERT INTO customers (name, phone, email, address) VALUES
('John Doe', '123-456-7890', 'johndoe@example.com', '123 Elm St, Springfield'),
('Jane Smith', '234-567-8901', 'janesmith@example.com', '456 Oak St, Springfield'),
('Alice Johnson', '345-678-9012', 'alicej@example.com', '789 Pine St, Springfield'),
('Bob Brown', '456-789-0123', 'bobbrown@example.com', '101 Maple St, Springfield'),
('Charlie Davis', '567-890-1234', 'charlied@example.com', '202 Birch St, Springfield'),
('Debbie White', '678-901-2345', 'debbiew@example.com', '303 Cedar St, Springfield'),
('Evan Green', '789-012-3456', 'evangreen@example.com', '404 Walnut St, Springfield'),
('Fiona Blue', '890-123-4567', 'fionablue@example.com', '505 Redwood St, Springfield'),
('George Harris', '901-234-5678', 'georgeh@example.com', '606 Cherry St, Springfield'),
('Hannah Allen', '012-345-6789', 'hannahallen@example.com', '707 Cedar Ave, Springfield'),
('Ivy Martin', '123-456-7891', 'ivymartin@example.com', '808 Birch Ave, Springfield'),
('Jack Nelson', '234-567-8902', 'jackn@example.com', '909 Pine Ave, Springfield'),
('Kathy Adams', '345-678-9013', 'kathyadams@example.com', '101 Oak Ave, Springfield'),
('Liam Carter', '456-789-0124', 'liamc@example.com', '202 Elm Ave, Springfield'),
('Mona Scott', '567-890-1235', 'monascott@example.com', '303 Walnut Ave, Springfield'),
('Nathaniel King', '678-901-2346', 'nathanking@example.com', '404 Redwood Ave, Springfield'),
('Olivia Lee', '789-012-3457', 'olivialee@example.com', '505 Cherry Ave, Springfield'),
('Paul Wright', '890-123-4568', 'paulwright@example.com', '606 Cedar St, Springfield'),
('Quinn Young', '901-234-5679', 'quinnyoung@example.com', '707 Oak St, Springfield'),
('Rachel Perez', '012-345-6790', 'rachelperez@example.com', '808 Pine St, Springfield'),
('Sam Taylor', '123-456-7892', 'samtaylor@example.com', '909 Cedar Ave, Springfield'),
('Tina Lewis', '234-567-8903', 'tinalewis@example.com', '101 Redwood St, Springfield'),
('Ursula Hall', '345-678-9014', 'ursulahall@example.com', '202 Cherry Ave, Springfield'),
('Victor Wilson', '456-789-0125', 'victorw@example.com', '303 Birch St, Springfield'),
('Wendy Moore', '567-890-1236', 'wendymoore@example.com', '404 Pine Ave, Springfield'),
('Xander King', '678-901-2347', 'xanderk@example.com', '505 Oak St, Springfield'),
('Yvonne Harris', '789-012-3458', 'yvonneharris@example.com', '606 Cedar Ave, Springfield'),
('Zachary Nelson', '890-123-4569', 'zacharynelson@example.com', '707 Redwood Ave, Springfield'),
('Aaron Scott', '901-234-5680', 'aaronscott@example.com', '808 Walnut Ave, Springfield'),
('Bella Green', '012-345-6791', 'bellagreen@example.com', '909 Redwood St, Springfield'),
('Cody Blue', '123-456-7893', 'codyblue@example.com', '101 Cedar Ave, Springfield'),
('Diana Young', '234-567-8904', 'dianayoung@example.com', '202 Walnut Ave, Springfield'),
('Eli Fisher', '345-678-9015', 'elifisher@example.com', '303 Oak Ave, Springfield'),
('Freda Carter', '456-789-0126', 'fredacarter@example.com', '404 Birch St, Springfield'),
('Gavin Perry', '567-890-1237', 'gavinperry@example.com', '505 Pine St, Springfield'),
('Holly Adams', '678-901-2348', 'hollyadams@example.com', '606 Redwood St, Springfield'),
('Ian Marshall', '789-012-3459', 'ianmarshall@example.com', '707 Cherry St, Springfield'),
('Julie Ward', '890-123-4570', 'julieward@example.com', '808 Birch Ave, Springfield'),
('Kevin Murphy', '901-234-5681', 'kevinmurphy@example.com', '909 Cedar Ave, Springfield'),
('Lily Lee', '012-345-6792', 'lilylee@example.com', '101 Oak St, Springfield'),
('Mason Scott', '123-456-7894', 'masonscott@example.com', '202 Pine Ave, Springfield'),
('Nina Kim', '234-567-8905', 'ninakim@example.com', '303 Redwood Ave, Springfield'),
('Oscar Miller', '345-678-9016', 'oscarmiller@example.com', '404 Cedar St, Springfield'),
('Penny White', '456-789-0127', 'pennywhite@example.com', '505 Walnut Ave, Springfield'),
('Quincy Hall', '567-890-1238', 'quincyhall@example.com', '606 Pine St, Springfield'),
('Riley Phillips', '678-901-2349', 'rileyphillips@example.com', '707 Redwood Ave, Springfield'),
('Sophia Collins', '789-012-3460', 'sophiacollins@example.com', '808 Oak Ave, Springfield'),
('Toby Williams', '890-123-4571', 'tobywilliams@example.com', '909 Birch Ave, Springfield'),
('Uma Patel', '901-234-5682', 'umapatel@example.com', '101 Redwood St, Springfield'),
('Vera Ross', '012-345-6793', 'veraross@example.com', '202 Pine St, Springfield'),
('Willow Lee', '123-456-7895', 'willowlee@example.com', '303 Walnut Ave, Springfield'),
('Xena Torres', '234-567-8906', 'xenatorres@example.com', '404 Oak St, Springfield'),
('Yara Thompson', '345-678-9017', 'yarathompson@example.com', '505 Cedar Ave, Springfield'),
('Zane White', '456-789-0128', 'zanewhite@example.com', '606 Pine Ave, Springfield'),
('Amos Ford', '567-890-1239', 'amosford@example.com', '707 Walnut St, Springfield'),
('Brenda Grant', '678-901-2350', 'brendagrant@example.com', '808 Redwood Ave, Springfield'),
('Charlie Perry', '789-012-3461', 'charlieperry@example.com', '909 Oak Ave, Springfield'),
('Dylan Bailey', '890-123-4572', 'dylanbailey@example.com', '101 Birch Ave, Springfield'),
('Eva Young', '901-234-5683', 'evayoung@example.com', '202 Cedar Ave, Springfield'),
('Franklin Morris', '012-345-6794', 'franklinmorris@example.com', '303 Redwood St, Springfield'),
('Grace Kelly', '123-456-7896', 'gracekelly@example.com', '404 Pine Ave, Springfield'),
('Henry Daniels', '234-567-8907', 'henrydaniels@example.com', '505 Walnut St, Springfield');

-- Insert 60 entries into the orders table
INSERT INTO orders (customer_id, total_cost, order_status, payment_status) VALUES
(1, 100.00, 'Completed', 'Paid'),
(2, 250.50, 'Completed', 'Paid'),
(3, 75.30, 'Completed', 'Unpaid'),
(4, 120.00, 'Pending', 'Unpaid'),
(5, 90.00, 'Completed', 'Paid'),
(6, 250.00, 'Completed', 'Paid'),
(7, 175.50, 'Completed', 'Paid'),
(8, 220.00, 'Pending', 'Unpaid'),
(9, 300.00, 'Completed', 'Paid'),
(10, 50.75, 'Completed', 'Paid'),
(11, 400.00, 'Completed', 'Paid'),
(12, 60.00, 'Pending', 'Unpaid'),
(13, 180.00, 'Completed', 'Paid'),
(14, 99.99, 'Completed', 'Unpaid'),
(15, 200.00, 'Pending', 'Paid'),
(16, 130.50, 'Completed', 'Paid'),
(17, 85.00, 'Completed', 'Paid'),
(18, 110.00, 'Completed', 'Unpaid'),
(19, 175.00, 'Completed', 'Paid'),
(20, 250.00, 'Pending', 'Unpaid'),
(21, 145.00, 'Completed', 'Paid'),
(22, 300.00, 'Completed', 'Paid'),
(23, 195.00, 'Completed', 'Paid'),
(24, 165.00, 'Pending', 'Paid'),
(25, 130.50, 'Completed', 'Unpaid'),
(26, 75.00, 'Completed', 'Paid'),
(27, 220.00, 'Completed', 'Paid'),
(28, 80.00, 'Pending', 'Paid'),
(29, 160.00, 'Completed', 'Paid'),
(30, 230.00, 'Completed', 'Paid'),
(31, 100.00, 'Completed', 'Paid'),
(32, 140.50, 'Completed', 'Paid'),
(33, 250.00, 'Pending', 'Unpaid'),
(34, 95.50, 'Completed', 'Paid'),
(35, 210.00, 'Completed', 'Paid'),
(36, 120.00, 'Completed', 'Paid'),
(37, 130.50, 'Pending', 'Paid'),
(38, 160.00, 'Completed', 'Paid'),
(39, 180.00, 'Completed', 'Unpaid'),
(40, 190.00, 'Completed', 'Paid'),
(41, 240.00, 'Completed', 'Paid'),
(42, 70.00, 'Completed', 'Paid'),
(43, 200.00, 'Pending', 'Paid'),
(44, 50.00, 'Completed', 'Paid'),
(45, 170.00, 'Completed', 'Paid'),
(46, 75.00, 'Completed', 'Paid'),
(47, 220.00, 'Pending', 'Unpaid'),
(48, 250.00, 'Completed', 'Paid'),
(49, 300.00, 'Completed', 'Paid'),
(50, 100.00, 'Pending', 'Unpaid'),
(51, 150.00, 'Completed', 'Paid'),
(52, 200.00, 'Completed', 'Paid'),
(53, 125.00, 'Completed', 'Paid'),
(54, 95.00, 'Completed', 'Paid'),
(55, 185.00, 'Completed', 'Paid'),
(56, 110.00, 'Pending', 'Paid'),
(57, 220.00, 'Completed', 'Paid'),
(58, 300.00, 'Completed', 'Paid'),
(59, 180.00, 'Completed', 'Paid'),
(60, 160.00, 'Completed', 'Paid');

-- Insert 60 entries into the reservations table
INSERT INTO reservations (customer_id, table_type, reservation_time) VALUES
(1, 'VIP', '2024-12-21 19:00:00'),
(2, 'Regular', '2024-12-22 20:00:00'),
(3, 'Outdoor', '2024-12-23 18:30:00'),
(4, 'VIP', '2024-12-24 21:00:00'),
(5, 'Regular', '2024-12-25 17:00:00'),
(6, 'Outdoor', '2024-12-26 18:00:00'),
(7, 'VIP', '2024-12-27 19:30:00'),
(8, 'Regular', '2024-12-28 20:15:00'),
(9, 'Outdoor', '2024-12-29 17:45:00'),
(10, 'VIP', '2024-12-30 21:00:00'),
(11, 'Regular', '2024-12-31 20:00:00'),
(12, 'Outdoor', '2025-01-01 19:30:00'),
(13, 'VIP', '2025-01-02 21:00:00'),
(14, 'Regular', '2025-01-03 18:15:00'),
(15, 'Outdoor', '2025-01-04 17:30:00'),
(16, 'VIP', '2025-01-05 20:00:00'),
(17, 'Regular', '2025-01-06 18:45:00'),
(18, 'Outdoor', '2025-01-07 19:00:00'),
(19, 'VIP', '2025-01-08 20:30:00'),
(20, 'Regular', '2025-01-09 17:15:00'),
(21, 'Outdoor', '2025-01-10 19:30:00'),
(22, 'VIP', '2025-01-11 19:00:00'),
(23, 'Outdoor', '2025-01-12 17:45:00'),
(24, 'Regular', '2025-01-13 19:00:00'),
(25, 'VIP', '2025-01-14 18:00:00'),
(26, 'Outdoor', '2025-01-15 19:30:00'),
(27, 'Regular', '2025-01-16 20:15:00'),
(28, 'VIP', '2025-01-17 21:00:00'),
(29, 'Outdoor', '2025-01-18 17:45:00'),
(30, 'Regular', '2025-01-19 19:00:00'),
(31, 'VIP', '2025-01-20 20:30:00'),
(32, 'Outdoor', '2025-01-21 19:15:00'),
(33, 'Regular', '2025-01-22 18:45:00'),
(34, 'VIP', '2025-01-23 20:00:00'),
(35, 'Outdoor', '2025-01-24 21:00:00'),
(36, 'Regular', '2025-01-25 19:30:00'),
(37, 'VIP', '2025-01-26 18:30:00'),
(38, 'Outdoor', '2025-01-27 20:15:00'),
(39, 'Regular', '2025-01-28 18:00:00'),
(40, 'VIP', '2025-01-29 19:30:00'),
(41, 'Outdoor', '2025-01-30 20:45:00'),
(42, 'Regular', '2025-02-01 17:15:00'),
(43, 'VIP', '2025-02-02 21:00:00'),
(44, 'Outdoor', '2025-02-03 19:00:00'),
(45, 'Regular', '2025-02-04 20:30:00'),
(46, 'VIP', '2025-02-05 18:00:00'),
(47, 'Outdoor', '2025-02-06 19:45:00'),
(48, 'Regular', '2025-02-07 18:30:00'),
(49, 'VIP', '2025-02-08 20:00:00'),
(50, 'Outdoor', '2025-02-09 19:15:00'),
(51, 'Regular', '2025-02-10 20:30:00'),
(52, 'VIP', '2025-02-11 19:45:00'),
(53, 'Outdoor', '2025-02-12 21:00:00'),
(54, 'Regular', '2025-02-13 18:00:00'),
(55, 'VIP', '2025-02-14 20:15:00'),
(56, 'Outdoor', '2025-02-15 19:30:00'),
(57, 'Regular', '2025-02-16 17:45:00'),
(58, 'VIP', '2025-02-17 21:00:00'),
(59, 'Outdoor', '2025-02-18 18:15:00'),
(60, 'Regular', '2025-02-19 19:30:00');

select * from menu_items;
select * from orders;
select * from customers;
select * from reservations;




