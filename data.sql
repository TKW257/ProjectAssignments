-- 1. Tạo database
USE [master];
GO

IF DB_ID('Assignment') IS NOT NULL
    DROP DATABASE Assignment;
GO

CREATE DATABASE Assignment;
GO

USE Assignment;
GO

-- 2. Tạo bảng người dùng
CREATE TABLE [tblUsers] (
    user_id INT PRIMARY KEY IDENTITY(1,1),
    name NVARCHAR(100) NOT NULL,
    email NVARCHAR(100) UNIQUE NOT NULL,
    password NVARCHAR(255) NOT NULL,
    address NVARCHAR(MAX),
    phone_number NVARCHAR(20),
    created_at DATETIME DEFAULT GETDATE(),
    role VARCHAR(20) NOT NULL CHECK (role IN ('Admin', 'User'))
);

-- 3. Tạo bảng danh mục sản phẩm
CREATE TABLE [tblCategories] (
    category_id INT PRIMARY KEY IDENTITY(1,1),
    category_name NVARCHAR(100) NOT NULL,
    description NVARCHAR(MAX)
);

-- 4. Tạo bảng sản phẩm
CREATE TABLE [tblProducts] (
    product_id INT PRIMARY KEY IDENTITY(1,1),
    name NVARCHAR(100) NOT NULL,
    description NVARCHAR(MAX),
    price DECIMAL(10, 2) NOT NULL,
    stock_quantity INT DEFAULT 0,
    category_id INT,
    image_url NVARCHAR(255),
    FOREIGN KEY (category_id) REFERENCES [tblCategories](category_id)
);

-- 5. Tạo bảng đơn hàng
CREATE TABLE [tblOrders] (
    order_id INT PRIMARY KEY IDENTITY(1,1),
    user_id INT NOT NULL,
    order_date DATETIME DEFAULT GETDATE(),
    status NVARCHAR(50) DEFAULT 'pending',
    total_amount DECIMAL(10, 2),
    FOREIGN KEY (user_id) REFERENCES [tblUsers](user_id)
);

-- 6. Tạo bảng chi tiết đơn hàng
CREATE TABLE [tblOrderDetails] (
    order_detail_id INT PRIMARY KEY IDENTITY(1,1),
    order_id INT NOT NULL,
    product_id INT NOT NULL,
    quantity INT NOT NULL,
    price_at_purchase DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (order_id) REFERENCES [tblOrders](order_id),
    FOREIGN KEY (product_id) REFERENCES [tblProducts](product_id)
);

-- 7. Tạo bảng thanh toán
CREATE TABLE [tblPayments] (
    payment_id INT PRIMARY KEY IDENTITY(1,1),
    order_id INT NOT NULL,
    payment_date DATETIME DEFAULT GETDATE(),
    payment_method NVARCHAR(50),
    amount DECIMAL(10, 2) NOT NULL,
    payment_status NVARCHAR(50) DEFAULT 'unpaid',
    FOREIGN KEY (order_id) REFERENCES [tblOrders](order_id)
);




-- 1. Người dùng
INSERT INTO [tblUsers] (name, email, password, address, phone_number, role)
VALUES 
(N'Admin User', 'admin@example.com', 'admin123', N'123 Admin St', '0900000001', 'Admin'),
(N'John Doe', 'john@example.com', 'user123', N'456 Main St', '0900000002', 'User'),
(N'Jane Smith', 'jane@example.com', 'user456', N'789 Side Rd', '0900000003', 'User');

-- 2. Danh mục sản phẩm
INSERT INTO [tblCategories] (category_name, description)
VALUES
(N'T-Shirts', N'All types of cotton and casual t-shirts'),
(N'Jeans', N'Various styles of jeans'),
(N'Shoes', N'Sports and casual shoes'),
(N'Accessories', N'Wallets, belts, and other accessories'),
(N'Electronics', N'Watches, gadgets, and more');

-- 3. Sản phẩm
INSERT INTO [tblProducts] (name, description, price, stock_quantity, category_id, image_url)
VALUES 
(N'Classic White T-Shirt', N'Comfortable cotton white t-shirt', 15.99, 100, 1, 'assets/img/gallery/popular1.png'),
(N'Blue Denim Jeans', N'Stylish slim-fit jeans', 39.99, 50, 2, 'assets/img/gallery/popular2.png'),
(N'Sports Running Shoes', N'Lightweight and durable', 59.90, 80, 3, 'assets/img/gallery/popular3.png'),
(N'Black Leather Wallet', N'Genuine leather men''s wallet', 25.00, 120, 4, 'assets/img/gallery/popular4.png'),
(N'Smart Watch Pro', N'Track your fitness and messages', 129.99, 30, 5, 'assets/img/gallery/popular5.png');



