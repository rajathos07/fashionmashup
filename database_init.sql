-- ============================================================
-- Fashion Mashup Database Initialization Script
-- Run this script ONCE to set up the database with seed data
-- All product images use online URLs from Pexels
-- ============================================================

-- Create database
CREATE DATABASE IF NOT EXISTS fashion_mashup;
USE fashion_mashup;

-- ============================================================
-- TABLES
-- ============================================================

CREATE TABLE IF NOT EXISTS categories (
    category_id INT PRIMARY KEY AUTO_INCREMENT,
    category_name VARCHAR(100) NOT NULL,
    description TEXT,
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS products (
    product_id INT PRIMARY KEY AUTO_INCREMENT,
    category_id INT NOT NULL,
    product_name VARCHAR(200) NOT NULL,
    description TEXT,
    price DOUBLE NOT NULL DEFAULT 0,
    discount_percent DOUBLE DEFAULT 0,
    image_url VARCHAR(500) DEFAULT '',
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (category_id) REFERENCES categories(category_id)
);

CREATE TABLE IF NOT EXISTS product_sizes (
    product_size_id INT PRIMARY KEY AUTO_INCREMENT,
    product_id INT NOT NULL,
    size_label VARCHAR(10) NOT NULL,
    stock_quantity INT DEFAULT 0,
    sku_code VARCHAR(50) DEFAULT '',
    is_available BOOLEAN DEFAULT TRUE,
    FOREIGN KEY (product_id) REFERENCES products(product_id)
);

CREATE TABLE IF NOT EXISTS users (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    full_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    phone VARCHAR(20) DEFAULT '',
    password VARCHAR(255) NOT NULL,
    gender VARCHAR(10) DEFAULT '',
    address TEXT DEFAULT '',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS cart (
    cart_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL UNIQUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE IF NOT EXISTS cart_items (
    cart_item_id INT PRIMARY KEY AUTO_INCREMENT,
    cart_id INT NOT NULL,
    product_id INT NOT NULL,
    size_label VARCHAR(10) DEFAULT '',
    quantity INT DEFAULT 1,
    unit_price DOUBLE NOT NULL DEFAULT 0,
    added_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (cart_id) REFERENCES cart(cart_id),
    FOREIGN KEY (product_id) REFERENCES products(product_id)
);

CREATE TABLE IF NOT EXISTS orders (
    order_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    total_amount DOUBLE NOT NULL DEFAULT 0,
    payment_method VARCHAR(50) DEFAULT '',
    order_status VARCHAR(50) DEFAULT 'Pending',
    delivery_address TEXT DEFAULT '',
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE IF NOT EXISTS order_items (
    order_item_id INT PRIMARY KEY AUTO_INCREMENT,
    order_id INT NOT NULL,
    product_id INT NOT NULL,
    product_name VARCHAR(200) DEFAULT '',
    quantity INT DEFAULT 1,
    unit_price DOUBLE DEFAULT 0,
    subtotal DOUBLE DEFAULT 0,
    size_label VARCHAR(10) DEFAULT '',
    FOREIGN KEY (order_id) REFERENCES orders(order_id),
    FOREIGN KEY (product_id) REFERENCES products(product_id)
);

-- ============================================================
-- SEED DATA: Categories
-- ============================================================

INSERT IGNORE INTO categories (category_id, category_name, description, is_active) VALUES
(1, "Men's Fashion", 'Stylish clothing and accessories for men', TRUE),
(2, "Women's Fashion", 'Trendy clothing and accessories for women', TRUE),
(3, 'Accessories', 'Bags, watches, jewelry and more', TRUE);

-- ============================================================
-- SEED DATA: Products with Online Image URLs
-- ============================================================

INSERT IGNORE INTO products (product_id, category_id, product_name, description, price, discount_percent, image_url, is_active) VALUES
-- Men's Fashion
(1,  1, 'Classic White Shirt',       'Premium cotton white shirt with a slim fit design. Perfect for formal and casual occasions.',          149.99, 10, 'https://images.pexels.com/photos/298863/pexels-photo-298863.jpeg?auto=compress&cs=tinysrgb&w=600', TRUE),
(2,  1, 'Navy Blue Blazer',          'Tailored navy blazer with a modern cut. Ideal for business meetings and evening events.',              349.99, 15, 'https://images.pexels.com/photos/1040945/pexels-photo-1040945.jpeg?auto=compress&cs=tinysrgb&w=600', TRUE),
(3,  1, 'Slim Fit Chinos',           'Comfortable stretch chinos in khaki. Versatile enough for office or weekend wear.',                   129.99,  0, 'https://images.pexels.com/photos/6764190/pexels-photo-6764190.jpeg?auto=compress&cs=tinysrgb&w=600', TRUE),
(4,  1, 'Denim Jacket',              'Classic denim jacket with a vintage wash. Layer it over a tee for an effortless look.',               199.99, 20, 'https://images.pexels.com/photos/5405092/pexels-photo-5405092.jpeg?auto=compress&cs=tinysrgb&w=600', TRUE),
(5,  1, 'Polo T-Shirt',             'Premium pique polo shirt in black. A wardrobe essential for smart-casual dressing.',                79.99,   0, 'https://images.pexels.com/photos/5698851/pexels-photo-5698851.jpeg?auto=compress&cs=tinysrgb&w=600', TRUE),

-- Women's Fashion
(6,  2, 'Floral Summer Dress',       'Lightweight floral midi dress perfect for summer days. Features a flattering A-line silhouette.',     189.99, 10, 'https://images.pexels.com/photos/985635/pexels-photo-985635.jpeg?auto=compress&cs=tinysrgb&w=600', TRUE),
(7,  2, 'Silk Blouse',              'Elegant silk blouse in champagne. Pairs beautifully with skirts or tailored trousers.',               219.99,  0, 'https://images.pexels.com/photos/7691105/pexels-photo-7691105.jpeg?auto=compress&cs=tinysrgb&w=600', TRUE),
(8,  2, 'High-Waist Trousers',       'Tailored high-waist trousers in charcoal. A modern classic for the professional wardrobe.',           169.99,  5, 'https://images.pexels.com/photos/7691048/pexels-photo-7691048.jpeg?auto=compress&cs=tinysrgb&w=600', TRUE),
(9,  2, 'Cashmere Cardigan',         'Soft cashmere cardigan in blush pink. Luxuriously warm for cooler evenings.',                         279.99,  0, 'https://images.pexels.com/photos/6764195/pexels-photo-6764195.jpeg?auto=compress&cs=tinysrgb&w=600', TRUE),
(10, 2, 'Wrap Midi Skirt',           'Satin wrap midi skirt in emerald green. Elegant and versatile for day-to-night dressing.',            139.99, 15, 'https://images.pexels.com/photos/6030968/pexels-photo-6030968.jpeg?auto=compress&cs=tinysrgb&w=600', TRUE),

-- Accessories
(11, 3, 'Leather Crossbody Bag',     'Genuine leather crossbody bag in tan. Compact yet spacious for daily essentials.',                    249.99,  0, 'https://images.pexels.com/photos/1152077/pexels-photo-1152077.jpeg?auto=compress&cs=tinysrgb&w=600', TRUE),
(12, 3, 'Minimalist Watch',          'Sleek minimalist watch with a stainless steel mesh band. Swiss movement, Japanese precision.',        399.99, 10, 'https://images.pexels.com/photos/190819/pexels-photo-190819.jpeg?auto=compress&cs=tinysrgb&w=600', TRUE),
(13, 3, 'Aviator Sunglasses',        'Classic aviator sunglasses with polarized lenses. UV400 protection for all-day comfort.',              159.99,  0, 'https://images.pexels.com/photos/7018401/pexels-photo-7018401.jpeg?auto=compress&cs=tinysrgb&w=600', TRUE),
(14, 3, 'Wool Scarf',               'Luxurious merino wool scarf in burgundy. Soft, warm, and endlessly stylish.',                        89.99,   5, 'https://images.pexels.com/photos/6712412/pexels-photo-6712412.jpeg?auto=compress&cs=tinysrgb&w=600', TRUE),
(15, 3, 'Canvas Tote Bag',           'Durable canvas tote bag in olive. Eco-friendly and perfect for everyday use.',                        69.99,   0, 'https://images.pexels.com/photos/2905238/pexels-photo-2905238.jpeg?auto=compress&cs=tinysrgb&w=600', TRUE);

-- ============================================================
-- SEED DATA: Product Sizes
-- ============================================================

-- Product 1: Classic White Shirt
INSERT IGNORE INTO product_sizes (product_id, size_label, stock_quantity, sku_code, is_available) VALUES
(1, 'S',  15, 'WS-S',  TRUE),
(1, 'M',  25, 'WS-M',  TRUE),
(1, 'L',  20, 'WS-L',  TRUE),
(1, 'XL', 10, 'WS-XL', TRUE);

-- Product 2: Navy Blue Blazer
INSERT IGNORE INTO product_sizes (product_id, size_label, stock_quantity, sku_code, is_available) VALUES
(2, 'S',  8,  'NB-S',  TRUE),
(2, 'M',  12, 'NB-M',  TRUE),
(2, 'L',  10, 'NB-L',  TRUE),
(2, 'XL', 5,  'NB-XL', TRUE);

-- Product 3: Slim Fit Chinos
INSERT IGNORE INTO product_sizes (product_id, size_label, stock_quantity, sku_code, is_available) VALUES
(3, 'S',  20, 'FC-S',  TRUE),
(3, 'M',  30, 'FC-M',  TRUE),
(3, 'L',  25, 'FC-L',  TRUE),
(3, 'XL', 15, 'FC-XL', TRUE),
(3, 'XXL', 10, 'FC-XXL', TRUE);

-- Product 4: Denim Jacket
INSERT IGNORE INTO product_sizes (product_id, size_label, stock_quantity, sku_code, is_available) VALUES
(4, 'S',  10, 'DJ-S',  TRUE),
(4, 'M',  18, 'DJ-M',  TRUE),
(4, 'L',  12, 'DJ-L',  TRUE),
(4, 'XL', 6,  'DJ-XL', TRUE);

-- Product 5: Polo T-Shirt
INSERT IGNORE INTO product_sizes (product_id, size_label, stock_quantity, sku_code, is_available) VALUES
(5, 'XS', 10, 'PT-XS', TRUE),
(5, 'S',  20, 'PT-S',  TRUE),
(5, 'M',  35, 'PT-M',  TRUE),
(5, 'L',  25, 'PT-L',  TRUE),
(5, 'XL', 15, 'PT-XL', TRUE),
(5, 'XXL', 10, 'PT-XXL', TRUE);

-- Product 6: Floral Summer Dress
INSERT IGNORE INTO product_sizes (product_id, size_label, stock_quantity, sku_code, is_available) VALUES
(6, 'XS', 8,  'FD-XS', TRUE),
(6, 'S',  15, 'FD-S',  TRUE),
(6, 'M',  20, 'FD-M',  TRUE),
(6, 'L',  12, 'FD-L',  TRUE),
(6, 'XL', 5,  'FD-XL', TRUE);

-- Product 7: Silk Blouse
INSERT IGNORE INTO product_sizes (product_id, size_label, stock_quantity, sku_code, is_available) VALUES
(7, 'XS', 6,  'SB-XS', TRUE),
(7, 'S',  12, 'SB-S',  TRUE),
(7, 'M',  18, 'SB-M',  TRUE),
(7, 'L',  10, 'SB-L',  TRUE);

-- Product 8: High-Waist Trousers
INSERT IGNORE INTO product_sizes (product_id, size_label, stock_quantity, sku_code, is_available) VALUES
(8, 'S',  14, 'HT-S',  TRUE),
(8, 'M',  22, 'HT-M',  TRUE),
(8, 'L',  16, 'HT-L',  TRUE),
(8, 'XL', 8,  'HT-XL', TRUE);

-- Product 9: Cashmere Cardigan
INSERT IGNORE INTO product_sizes (product_id, size_label, stock_quantity, sku_code, is_available) VALUES
(9, 'S',  10, 'CC-S',  TRUE),
(9, 'M',  15, 'CC-M',  TRUE),
(9, 'L',  12, 'CC-L',  TRUE),
(9, 'XL', 5,  'CC-XL', TRUE);

-- Product 10: Wrap Midi Skirt
INSERT IGNORE INTO product_sizes (product_id, size_label, stock_quantity, sku_code, is_available) VALUES
(10, 'XS', 8,  'MS-XS', TRUE),
(10, 'S',  14, 'MS-S',  TRUE),
(10, 'M',  20, 'MS-M',  TRUE),
(10, 'L',  10, 'MS-L',  TRUE),
(10, 'XL', 4,  'MS-XL', TRUE);

-- Product 11: Leather Crossbody Bag (one size)
INSERT IGNORE INTO product_sizes (product_id, size_label, stock_quantity, sku_code, is_available) VALUES
(11, 'One Size', 25, 'CB-OS', TRUE);

-- Product 12: Minimalist Watch (one size)
INSERT IGNORE INTO product_sizes (product_id, size_label, stock_quantity, sku_code, is_available) VALUES
(12, 'One Size', 18, 'MW-OS', TRUE);

-- Product 13: Aviator Sunglasses (one size)
INSERT IGNORE INTO product_sizes (product_id, size_label, stock_quantity, sku_code, is_available) VALUES
(13, 'One Size', 30, 'AS-OS', TRUE);

-- Product 14: Wool Scarf (one size)
INSERT IGNORE INTO product_sizes (product_id, size_label, stock_quantity, sku_code, is_available) VALUES
(14, 'One Size', 22, 'WS-OS', TRUE);

-- Product 15: Canvas Tote Bag (one size)
INSERT IGNORE INTO product_sizes (product_id, size_label, stock_quantity, sku_code, is_available) VALUES
(15, 'One Size', 35, 'TB-OS', TRUE);

-- ============================================================
-- SEED DATA: Test User
-- ============================================================

INSERT IGNORE INTO users (user_id, full_name, email, phone, password, gender, address) VALUES
(1, 'Test User', 'test@fashion.com', '0512345678', 'password123', 'Male', '123 Fashion Street, Riyadh 12345');

-- ============================================================
-- DONE
-- ============================================================
