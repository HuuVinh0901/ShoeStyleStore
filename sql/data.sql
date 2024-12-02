-- Thêm dữ liệu cho bảng Role
use ShoeStore
INSERT INTO Role (name, description)
VALUES
('Admin', 'Quản trị viên với toàn quyền quản lý'),
('Customer', 'Khách hàng sử dụng dịch vụ và mua sản phẩm');



-- Thêm dữ liệu vào bảng Users
INSERT INTO Users (CI, email, name, password, phoneNumber, status, userName, roleID)
VALUES
-- Admins
('1234567890', 'admin1@example.com', 'Admin User 1', '$2a$10$piYOHuFhF7WWTyziAev08.RtlRcnZuruhfrTrgYWO6phJ4l1XvSBm', '0334567890', 'Active', 'admin1', 1),
('0987654321', 'admin2@example.com', 'Admin User 2', 'securepassword2', '0987654321', 'Active', 'admin2', 1),
('1122334455', 'admin3@example.com', 'Admin User 3', 'securepassword3', '0322334455', 'Active', 'admin3', 1),

-- Customers
('2233445566', 'customer1@example.com', 'John Doe', '$2a$10$piYOHuFhF7WWTyziAev08.RtlRcnZuruhfrTrgYWO6phJ4l1XvSBm', '0333445566', 'Active', 'johndoe1', 2),
('3344556677', 'customer2@example.com', 'Jane Smith', 'password456', '0944556677', 'Active', 'janesmith2', 2),
('4455667788', 'customer3@example.com', 'Alice Johnson', 'password789', '0355667788', 'Active', 'alicejohnson3', 2),
('5566778899', 'customer4@example.com', 'Bob Brown', 'password101', '0366778899', 'Active', 'bobbrown4', 2),
('6677889900', 'customer5@example.com', 'Charlie Davis', 'password202', '0377889900', 'Active', 'charliedavis5', 2),
('7788990011', 'customer6@example.com', 'Diana Prince', 'password303', '0388990011', 'Active', 'dianaprince6', 2),
('8899001122', 'customer7@example.com', 'Ethan Hunt', 'password404', '0399001122', 'Active', 'ethanhunt7', 2),
('9900112233', 'customer8@example.com', 'Fiona Gallagher', 'password505', '0300112233', 'Active', 'fionagallagher8', 2),
('1011121314', 'customer9@example.com', 'George Clooney', 'password606', '0311121314', 'Active', 'georgeclooney9', 2),
('1112131415', 'customer10@example.com', 'Hannah Montana', 'password707', '0312131415', 'Active', 'hannahmontana10', 2),
('1213141516', 'customer11@example.com', 'Isaac Newton', 'password808', '0313141516', 'Active', 'isaacnewton11', 2),
('1314151617', 'customer12@example.com', 'Jack Sparrow', 'password909', '0314151617', 'Active', 'jacksparrow12', 2),
('1415161718', 'customer13@example.com', 'Kara Danvers', 'password010', '0315161718', 'Active', 'karadanvers13', 2),
('1516171819', 'customer14@example.com', 'Liam Hemsworth', 'password111', '0316171819', 'Active', 'liamhemsworth14', 2),
('1617181920', 'customer15@example.com', 'Mia Wallace', 'password222', '0317181920', 'Active', 'miawallace15', 2),
('1718192021', 'customer16@example.com', 'Nina Dobrev', 'password333', '0318192021', 'Active', 'ninadobrev16', 2),
('1819202122', 'customer17@example.com', 'Oscar Isaac', 'password444', '0319202122', 'Active', 'oscarisaac17', 2),
('1920212223', 'customer18@example.com', 'Peter Parker', 'password555', '0320212223', 'Active', 'peterparker18', 2),
('2021222324', 'customer19@example.com', 'Quinn Fabray', 'password666', '0321222324', 'Active', 'quinnfabray19', 2),
('2122232425', 'customer20@example.com', 'Rachel Green', '$2a$10$piYOHuFhF7WWTyziAev08.RtlRcnZuruhfrTrgYWO6phJ4l1XvSBm', '2122232425', 'Active', 'rachelgreen20', 2);


INSERT INTO Address (city, district, street, ward, userID)
VALUES 
('Hanoi', 'Ba Dinh', '123 Le Loi', 'Ward 1', 1),
('Hanoi', 'Cau Giay', '456 Hoang Hoa Tham', 'Ward 2', 2),
('Hanoi', 'Dong Da', '789 Nguyen Chi Thanh', 'Ward 3', 3),
('Ho Chi Minh', 'District 1', '12 Hai Ba Trung', 'Ward 4', 4),
('Ho Chi Minh', 'District 2', '34 Nguyen Hue', 'Ward 5', 5),
('Da Nang', 'Hai Chau', '56 Tran Phu', 'Ward 6', 6),
('Da Nang', 'Thanh Khe', '78 Dien Bien Phu', 'Ward 7', 7),
('Can Tho', 'Ninh Kieu', '90 Vo Van Kiet', 'Ward 8', 8),
('Hai Phong', 'Le Chan', '11 Nguyen Trai', 'Ward 9', 9),
('Hai Phong', 'Ngo Quyen', '22 Hai Ba Trung', 'Ward 10', 10),
('Hue', 'Phu Nhuan', '33 Nguyen Cong Tru', 'Ward 11', 11),
('Hue', 'Thuan Thanh', '44 Le Loi', 'Ward 12', 12),
('Nha Trang', 'Van Thanh', '55 Tran Phu', 'Ward 13', 13),
('Nha Trang', 'Phuoc Long', '66 Nguyen Thien Thuat', 'Ward 14', 14),
('Vung Tau', 'Ward 1', '77 Tran Hung Dao', 'Ward 15', 15),
('Vung Tau', 'Ward 2', '88 Nguyen Trai', 'Ward 16', 16),
('Da Lat', 'Ward 5', '99 Le Hong Phong', 'Ward 17', 17),
('Da Lat', 'Ward 6', '10 Nguyen Van Cu', 'Ward 18', 18),
('Quy Nhon', 'An Nhon', '20 Tran Hung Dao', 'Ward 19', 19),
('Quy Nhon', 'Quy Nhon', '30 Vo Thi Sau', 'Ward 20', 20);


-- Thêm dữ liệu cho bảng Brand
INSERT INTO Brand (name)
VALUES
('Nike'),
('Adidas'),
('Puma'),
('Reebok'),
('Under Armour');

-- Thêm dữ liệu cho bảng Category
INSERT INTO Category (description, name)
VALUES
('Shoes for running and sports', 'Running'),
('Shoes for casual wear', 'Lifestyle'),
('Shoes for basketball players', 'Basketball'),
('Shoes for football players', 'Football'),
('Shoes for gym and training', 'Training'),
('High-quality tennis shoes', 'Tennis'),
('Shoes for hiking and trekking', 'Hiking'),
('Specialized soccer shoes', 'Soccer'),
('Shoes for kids and youth', 'Kids'),
('Luxury brand shoes', 'Luxury');

-- Thêm dữ liệu cho bảng Supplier
INSERT INTO Supplier (address,supplierName)
VALUES
('123 Main St, New York, NY', 'Global Sports Inc.'),
('456 Elm St, San Francisco, CA', 'Elite Shoes LLC'),
('789 Maple Ave, Chicago, IL', 'Active Wear Co.'),
('321 Oak St, Seattle, WA', 'Fit and Style Ltd.'),
('654 Pine St, Boston, MA', 'Performance Gear Corp.'),
('987 Cedar Rd, Austin, TX', 'Trendsetters Supply'),
('159 Spruce St, Miami, FL', 'Urban Outfits Inc.'),
('753 Birch St, Denver, CO', 'Trail Blazers Ltd.'),
('951 Aspen Dr, Atlanta, GA','Southern Comfort Gear'),
('852 Redwood Ave, Portland, OR','Pacific Edge Shoes');
INSERT INTO Supplier_PhoneNumber (supplierID, phoneNumbers) 
VALUES 
    (1, '0911111111'), 
    (1, '0911222222'), 
    (2, '0913333333'), 
    (3, '0914444444'), 
    (4, '0915555555'),
    (5, '0916666666'),
	(5, '0911111156'), 
    (6, '0911222999'), 
    (6, '0913333458'), 
    (7, '0914444454'), 
    (8, '0915554564'),
    (9, '0916666846');

INSERT INTO Product (description, price, productName, status, brandID, categoryID, promotionID, supplierID,createDate)
VALUES
('High-performance running shoes.', 90000, 'Nike Air Max 270', 'Available', 1, 1, NULL, 1,GETDATE()),
('Lightweight basketball sneakers.', 120000, 'Adidas Harden Vol. 5', 'Available', 2, 2, NULL, 2,GETDATE()),
('Stylish hiking boots.', 150000, 'Timberland Premium', 'Available', 3, 3, NULL, 3,GETDATE()),
('Everyday wear sneakers.', 80000, 'Converse Chuck Taylor', 'Available', 4, 4, NULL, 4,GETDATE()),
('Elegant leather loafers.', 100000, 'Clarks Originals', 'Available', 5, 5, NULL, 5,GETDATE()),
('Performance tennis shoes.', 110000, 'Asics Gel-Resolution', 'Available', 1, 6, NULL, 6,GETDATE()),
('Classic skate shoes.', 75000, 'Vans Old Skool', 'Available', 2, 7, NULL, 7,GETDATE()),
('Premium long-distance runners.', 95000, 'Brooks Ghost 14', 'Available', 3, 8, NULL, 8,GETDATE()),
('Breathable sports sandals.', 50000, 'Teva Hurricane XLT2', 'Available', 4, 9, NULL, 9,GETDATE()),
('Luxury high heels.', 200000, 'Jimmy Choo Romy 100', 'Available', 5, 10, NULL, 10,GETDATE()),
('Comfort-focused sneakers.', 85000, 'New Balance 574', 'Available', 1, 1, NULL, 1,GETDATE()),
('High-performance trainers.', 120000, 'Under Armour HOVR', 'Available', 2, 2, NULL, 2,GETDATE()),
('Waterproof outdoor boots.', 130000, 'Columbia Bugaboot', 'Available', 3, 3, NULL, 3,GETDATE()),
('Athletic running shoes.', 100000, 'Saucony Endorphin', 'Available', 4, 4, NULL, 4,GETDATE()),
('Modern casual loafers.', 90000, 'Sperry Top-Sider', 'Available', 5, 5, NULL, 5,GETDATE()),
('Lightweight running shoes.', 75000, 'Reebok Floatride', 'Available', 1, 6, NULL, 6,GETDATE()),
('Classic canvas shoes.', 55000, 'Keds Champion', 'Available', 2, 7, NULL, 7,GETDATE()),
('Trail running shoes.', 110000, 'Salomon Speedcross', 'Available', 3, 8, NULL, 8,GETDATE()),
('Minimalist sports sandals.', 60000, 'Xero Shoes Z-Trail', 'Available', 4, 9, NULL, 9,GETDATE()),
('Luxury dress shoes.', 250000, 'Gucci Ace Sneakers', 'Available', 5, 10, NULL, 10,GETDATE()),
('Casual slip-on sneakers.', 70000, 'Skechers Go Walk', 'Available', 1, 1, NULL, 1,GETDATE()),
('Basketball performance shoes.', 140000, 'Puma Clyde All-Pro', 'Available', 2, 2, NULL, 2,GETDATE()),
('Hiking boots with ankle support.', 160000, 'North Face Vectiv', 'Available', 3, 3, NULL, 3,GETDATE()),
('Canvas skate shoes.', 75000, 'DC Shoes Trase', 'Available', 4, 4, NULL, 4,GETDATE()),
('Formal leather oxford shoes.', 130000, 'Allen Edmonds Park Ave', 'Available', 5, 5, NULL, 5,GETDATE()),
('Cushioned tennis shoes.', 115000, 'Fila Axilus 2 Energized', 'Available', 1, 6, NULL, 6,GETDATE()),
('Classic low-top sneakers.', 90000, 'Lacoste Carnaby Evo', 'Available', 2, 7, NULL, 7,GETDATE()),
('Trail running shoes for grip.', 125000, 'Hoka Speedgoat', 'Available', 3, 8, NULL, 8,GETDATE()),
('Open-toe sports sandals.', 45000, 'Merrell Hydro MOC', 'Available', 4, 9, NULL, 9,GETDATE()),
('Elegant party heels.', 220000, 'Manolo Blahnik BB', 'Available', 5, 10, NULL, 10,GETDATE()),
('Everyday cushioned sneakers.', 100000, 'On Cloud 5', 'Available', 1, 1, NULL, 1,GETDATE()),
('Durable basketball sneakers.', 150000, 'Jordan Retro 1', 'Available', 2, 2, NULL, 2,GETDATE()),
('Warm insulated boots.', 180000, 'Sorel Caribou', 'Available', 3, 3, NULL, 3,GETDATE()),
('Stylish slip-ons.', 70000, 'Toms Alpargata', 'Available', 4, 4, NULL, 4,GETDATE()),
('Premium leather loafers.', 190000, 'Tods Gommino', 'Available', 5, 5, NULL, 5,GETDATE()),
('High-traction trail runners.', 130000, 'Arcteryx Norvan', 'Available', 1, 6, NULL, 6,GETDATE()),
('Skateboarding classics.', 75000, 'Globe Sabre', 'Available', 2, 7, NULL, 7,GETDATE()),
('All-terrain shoes.', 140000, 'Altra Lone Peak', 'Available', 3, 8, NULL, 8,GETDATE()),
('Quick-dry sandals.', 50000, 'Chaco Z/Cloud', 'Available', 4, 9, NULL, 9,GETDATE()),
('Luxury red sole heels.', 800000, 'Christian Louboutin So Kate', 'Available', 5, 10, NULL, 10,GETDATE()),
('Lightweight walking shoes.', 60000, 'Skechers Flex Appeal', 'Available', 1, 1, NULL, 1,GETDATE()),
('Responsive basketball shoes.', 130000, 'Nike Zoom Freak', 'Available', 2, 2, NULL, 2,GETDATE()),
('Rugged hiking boots.', 170000, 'Scarpa Zodiac Plus', 'Available', 3, 3, NULL, 3,GETDATE()),
('Casual street sneakers.', 95000, 'Adidas Superstar', 'Available', 4, 4, NULL, 4,GETDATE()),
('Italian leather dress shoes.', 250000, 'Santoni Double Monk', 'Available', 5, 5, NULL, 5,GETDATE()),
('Lightweight walking shoes.', 60000, 'Skechers Flex Appeal', 'Available', 1, 1, NULL, 1,GETDATE()),
('Responsive basketball shoes.', 130000, 'Nike Zoom Freak', 'Available', 2, 2, NULL, 2,GETDATE()),
('Rugged hiking boots.', 170000, 'Scarpa Zodiac Plus', 'Available', 3, 3, NULL, 3,GETDATE()),
('Casual street sneakers.', 95000, 'Adidas Superstar', 'Available', 4, 4, NULL, 4,GETDATE()),
('Italian leather dress shoes.', 250000, 'Santoni Double Monk', 'Available', 5, 5, NULL, 5,GETDATE());

INSERT INTO ProductDetail (color, size, stockQuantity, productID)
VALUES
('RED', 'SIZE_38', 50, 1),
('BLUE', 'SIZE_39', 30, 2),
('GREEN', 'SIZE_40', 20, 3),
('YELLOW', 'SIZE_41', 40, 4),
('BLACK', 'SIZE_42', 35, 5),
('WHITE', 'SIZE_43', 25, 6),
('PINK', 'SIZE_44', 60, 7),
('RED', 'SIZE_38', 45, 8),
('BLUE', 'SIZE_39', 50, 9),
('GREEN', 'SIZE_40', 20, 10),
('YELLOW', 'SIZE_41', 30, 11),
('BLACK', 'SIZE_42', 50, 12),
('WHITE', 'SIZE_43', 20, 13),
('PINK', 'SIZE_44', 40, 14),
('RED', 'SIZE_38', 35, 15),
('BLUE', 'SIZE_39', 25, 16),
('GREEN', 'SIZE_40', 30, 17),
('YELLOW', 'SIZE_41', 50, 18),
('BLACK', 'SIZE_42', 20, 19),
('WHITE', 'SIZE_43', 60, 20),
('PINK', 'SIZE_44', 40, 21),
('RED', 'SIZE_38', 50, 22),
('BLUE', 'SIZE_39', 45, 23),
('GREEN', 'SIZE_40', 30, 24),
('YELLOW', 'SIZE_41', 55, 25),
('BLACK', 'SIZE_42', 65, 26),
('WHITE', 'SIZE_43', 35, 27),
('PINK', 'SIZE_44', 45, 28),
('RED', 'SIZE_38', 40, 29),
('BLUE', 'SIZE_39', 25, 30),
('GREEN', 'SIZE_40', 50, 31),
('YELLOW', 'SIZE_41', 45, 32),
('BLACK', 'SIZE_42', 30, 33),
('WHITE', 'SIZE_43', 50, 34),
('PINK', 'SIZE_44', 40, 35),
('RED', 'SIZE_38', 35, 36),
('BLUE', 'SIZE_39', 30, 37),
('GREEN', 'SIZE_40', 50, 38),
('YELLOW', 'SIZE_41', 45, 39),
('BLACK', 'SIZE_42', 20, 40),
('WHITE', 'SIZE_43', 55, 41),
('PINK', 'SIZE_44', 60, 42),
('RED', 'SIZE_38', 25, 43),
('BLUE', 'SIZE_39', 40, 44),
('GREEN', 'SIZE_40', 45, 45),
('YELLOW', 'SIZE_41', 30, 46),
('BLACK', 'SIZE_42', 20, 47),
('WHITE', 'SIZE_43', 35, 48),
('PINK', 'SIZE_44', 50, 49),
('RED', 'SIZE_38', 60, 50);

INSERT INTO Product_ImageURL (productID, imageURL)
VALUES
(1, 'product1_1.png'),
(1, 'product1_2.png'),
(1, 'product1_3.png'),
(1, 'product1_4.png'),
(1, 'product1_5.png'),
(2, 'product2_1.png'),
(2, 'product2_2.png'),
(2, 'product2_3.png'),
(2, 'product2_4.png'),
(2, 'product2_5.png'),
(3, 'product3_1.png'),
(3, 'product3_2.png'),
(3, 'product3_3.png'),
(3, 'product3_4.png'),
(3, 'product3_5.png'),
(4, 'product4_1.png'),
(4, 'product4_2.png'),
(4, 'product4_3.png'),
(4, 'product4_4.png'),
(4, 'product4_5.png'),
(5, 'product5_1.png'),
(5, 'product5_2.png'),
(5, 'product5_3.png'),
(5, 'product5_4.png'),
(5, 'product5_5.png'),
(6, 'product6_1.png'),
(6, 'product6_2.png'),
(6, 'product6_3.png'),
(6, 'product6_4.png'),
(6, 'product6_5.png'),
(7, 'product7_1.png'),
(7, 'product7_2.png'),
(7, 'product7_3.png'),
(7, 'product7_4.png'),
(7, 'product7_5.png'),
(8, 'product8_1.png'),
(8, 'product8_2.png'),
(8, 'product8_3.png'),
(8, 'product8_4.png'),
(8, 'product8_5.png'),
(9, 'product9_1.png'),
(9, 'product9_2.png'),
(9, 'product9_3.png'),
(9, 'product9_4.png'),
(9, 'product9_5.png'),
(10, 'product10_1.png'),
(10, 'product10_2.png'),
(10, 'product10_3.png'),
(10, 'product10_4.png'),
(10, 'product10_5.png'),
(11, 'product11_1.png'),
(11, 'product11_2.png'),
(11, 'product11_3.png'),
(11, 'product11_4.png'),
(11, 'product11_5.png'),
(12, 'product12_1.png'),
(12, 'product12_2.png'),
(12, 'product12_3.png'),
(12, 'product12_4.png'),
(12, 'product12_5.png'),
(13, 'product13_1.png'),
(13, 'product13_2.png'),
(13, 'product13_3.png'),
(13, 'product13_4.png'),
(13, 'product13_5.png'),
(14, 'product14_1.png'),
(14, 'product14_2.png'),
(14, 'product14_3.png'),
(14, 'product14_4.png'),
(14, 'product14_5.png'),
(15, 'product15_1.png'),
(15, 'product15_2.png'),
(15, 'product15_3.png'),
(15, 'product15_4.png'),
(15, 'product15_5.png'),
(16, 'product16_1.png'),
(16, 'product16_2.png'),
(16, 'product16_3.png'),
(16, 'product16_4.png'),
(16, 'product16_5.png'),
(17, 'product17_1.png'),
(17, 'product17_2.png'),
(17, 'product17_3.png'),
(17, 'product17_4.png'),
(17, 'product17_5.png'),
(18, 'product18_1.png'),
(18, 'product18_2.png'),
(18, 'product18_3.png'),
(18, 'product18_4.png'),
(18, 'product18_5.png'),
(19, 'product19_1.png'),
(19, 'product19_2.png'),
(19, 'product19_3.png'),
(19, 'product19_4.png'),
(19, 'product19_5.png'),
(20, 'product20_1.png'),
(20, 'product20_2.png'),
(20, 'product20_3.png'),
(20, 'product20_4.png'),
(20, 'product20_5.png'),
(21, 'product21_1.png'),
(21, 'product21_2.png'),
(21, 'product21_3.png'),
(21, 'product21_4.png'),
(21, 'product21_5.png'),
(22, 'product22_1.png'),
(22, 'product22_2.png'),
(22, 'product22_3.png'),
(22, 'product22_4.png'),
(22, 'product22_5.png'),
(23, 'product23_1.png'),
(23, 'product23_2.png'),
(23, 'product23_3.png'),
(23, 'product23_4.png'),
(23, 'product23_5.png'),
(24, 'product24_1.png'),
(24, 'product24_2.png'),
(24, 'product24_3.png'),
(24, 'product24_4.png'),
(24, 'product24_5.png'),
(25, 'product25_1.png'),
(25, 'product25_2.png'),
(25, 'product25_3.png'),
(25, 'product25_4.png'),
(25, 'product25_5.png'),
(26, 'product26_1.png'),
(26, 'product26_2.png'),
(26, 'product26_3.png'),
(26, 'product26_4.png'),
(26, 'product26_5.png'),
(27, 'product27_1.png'),
(27, 'product27_2.png'),
(27, 'product27_3.png'),
(27, 'product27_4.png'),
(27, 'product27_5.png'),
(28, 'product28_1.png'),
(28, 'product28_2.png'),
(28, 'product28_3.png'),
(28, 'product28_4.png'),
(28, 'product28_5.png'),
(29, 'product29_1.png'),
(29, 'product29_2.png'),
(29, 'product29_3.png'),
(29, 'product29_4.png'),
(29, 'product29_5.png'),
(30, 'product30_1.png'),
(30, 'product30_2.png'),
(30, 'product30_3.png'),
(30, 'product30_4.png'),
(30, 'product30_5.png'),
(31, 'product31_1.png'),
(31, 'product31_2.png'),
(31, 'product31_3.png'),
(31, 'product31_4.png'),
(31, 'product31_5.png'),
(32, 'product32_1.png'),
(32, 'product32_2.png'),
(32, 'product32_3.png'),
(32, 'product32_4.png'),
(32, 'product32_5.png'),
(33, 'product33_1.png'),
(33, 'product33_2.png'),
(33, 'product33_3.png'),
(33, 'product33_4.png'),
(33, 'product33_5.png'),
(34, 'product34_1.png'),
(34, 'product34_2.png'),
(34, 'product34_3.png'),
(34, 'product34_4.png'),
(34, 'product34_5.png'),
(35, 'product35_1.png'),
(35, 'product35_2.png'),
(35, 'product35_3.png'),
(35, 'product35_4.png'),
(35, 'product35_5.png'),
(36, 'product36_1.png'),
(36, 'product36_2.png'),
(36, 'product36_3.png'),
(36, 'product36_4.png'),
(36, 'product36_5.png'),
(37, 'product37_1.png'),
(37, 'product37_2.png'),
(37, 'product37_3.png'),
(37, 'product37_4.png'),
(37, 'product37_5.png'),
(38, 'product38_1.png'),
(38, 'product38_2.png'),
(38, 'product38_3.png'),
(38, 'product38_4.png'),
(38, 'product38_5.png'),
(39, 'product39_1.png'),
(39, 'product39_2.png'),
(39, 'product39_3.png'),
(39, 'product39_4.png'),
(39, 'product39_5.png'),
(40, 'product40_1.png'),
(40, 'product40_2.png'),
(40, 'product40_3.png'),
(40, 'product40_4.png'),
(40, 'product40_5.png'),
(41, 'product41_1.png'),
(41, 'product41_2.png'),
(41, 'product41_3.png'),
(41, 'product41_4.png'),
(41, 'product41_5.png'),
(42, 'product42_1.png'),
(42, 'product42_2.png'),
(42, 'product42_3.png'),
(42, 'product42_4.png'),
(42, 'product42_5.png'),
(43, 'product43_1.png'),
(43, 'product43_2.png'),
(43, 'product43_3.png'),
(43, 'product43_4.png'),
(43, 'product43_5.png'),
(44, 'product44_1.png'),
(44, 'product44_2.png'),
(44, 'product44_3.png'),
(44, 'product44_4.png'),
(44, 'product44_5.png'),
(45, 'product45_1.png'),
(45, 'product45_2.png'),
(45, 'product45_3.png'),
(45, 'product45_4.png'),
(45, 'product45_5.png'),
(46, 'product46_1.png'),
(46, 'product46_2.png'),
(46, 'product46_3.png'),
(46, 'product46_4.png'),
(46, 'product46_5.png'),
(47, 'product47_1.png'),
(47, 'product47_2.png'),
(47, 'product47_3.png'),
(47, 'product47_4.png'),
(47, 'product47_5.png'),
(48, 'product48_1.png'),
(48, 'product48_2.png'),
(48, 'product48_3.png'),
(48, 'product48_4.png'),
(48, 'product48_5.png'),
(49, 'product49_1.png'),
(49, 'product49_2.png'),
(49, 'product49_3.png'),
(49, 'product49_4.png'),
(49, 'product49_5.png'),
(50, 'product50_1.png'),
(50, 'product50_2.png'),
(50, 'product50_3.png'),
(50, 'product50_4.png'),
(50, 'product50_5.png');




INSERT INTO Promotion (description, endDate, name, promotionType, promotionValue, startDate)
VALUES
('Black Friday Sale', '2024-11-30', 'Black Friday', 'Percentage', '20%', '2024-11-01'),
('Christmas Sale', '2024-12-25', 'Christmas', 'Percentage', '25%', '2024-12-01'),
('New Year Sale', '2025-01-15', 'New Year', 'Flat', '$50', '2025-01-01'),
('Summer Sale', '2024-06-30', 'Summer', 'Percentage', '15%', '2024-06-01'),
('Back to School', '2024-09-15', 'School', 'Flat', '$30', '2024-08-15'),
('Mid Year Sale', '2024-07-15', 'Mid Year', 'Percentage', '10%', '2024-07-01'),
('Easter Discount', '2024-04-15', 'Easter', 'Flat', '$20', '2024-04-01'),
('Valentine Special', '2024-02-14', 'Valentine', 'Percentage', '15%', '2024-02-01'),
('Halloween Sale', '2024-10-31', 'Halloween', 'Flat', '$40', '2024-10-01'),
('Lunar New Year', '2024-02-15', 'Lunar New Year', 'Percentage', '20%', '2024-01-25');


INSERT INTO Voucher (description, discountType, discountValue, endDate, name, startDate, status, minValueOrder)
VALUES
('Welcome Voucher', 'Percentage', 10, '2024-12-31', 'WELCOME10', '2024-12-10', 'Upcoming', 300000),
('Holiday Discount', 'Flat', 20000, '2024-12-25', 'HOLIDAY20', '2024-12-01', 'Active', 200000),
('Year-End Sale', 'Percentage', 15, '2024-12-31', 'YEAR15', '2024-12-15', 'Ended', 250000);


INSERT INTO Cart (createAt, userID)
VALUES 
('2024-11-01 08:30:00', 1),
('2024-11-02 09:45:00', 2),
('2024-11-03 10:15:00', 3),
('2024-11-04 11:20:00', 4),
('2024-11-05 12:30:00', 5),
('2024-11-06 13:45:00', 6),
('2024-11-07 14:50:00', 7),
('2024-11-08 15:10:00', 8),
('2024-11-09 16:30:00', 9),
('2024-11-10 17:00:00', 10),
('2024-11-11 18:15:00', 11),
('2024-11-12 19:20:00', 12),
('2024-11-13 20:25:00', 13),
('2024-11-14 21:30:00', 14),
('2024-11-15 22:45:00', 15),
('2024-11-16 23:50:00', 16),
('2024-11-17 08:00:00', 17),
('2024-11-18 09:10:00', 18),
('2024-11-19 10:20:00', 19),
('2024-11-20 11:30:00', 20);

INSERT INTO CartItem (quantity, subTotal, cartID, productDetailID)
VALUES
(2, 180.00, 1, 1), -- Nike Air Max 270, $90.00 * 2
(1, 120.00, 2, 2), -- Adidas Harden Vol. 5, $120.00 * 1
(3, 450.00, 3, 3), -- Timberland Premium, $150.00 * 3
(4, 320.00, 4, 4), -- Converse Chuck Taylor, $80.00 * 4
(2, 200.00, 5, 5), -- Clarks Originals, $100.00 * 2
(1, 110.00, 6, 6), -- Asics Gel-Resolution, $110.00 * 1
(5, 375.00, 7, 7), -- Vans Old Skool, $75.00 * 5
(3, 285.00, 8, 8), -- Brooks Ghost 14, $95.00 * 3
(1, 50.00, 9, 9), -- Teva Hurricane XLT2, $50.00 * 1
(2, 400.00, 10, 10), -- Jimmy Choo Romy 100, $200.00 * 2
(4, 340.00, 11, 11), -- New Balance 574, $85.00 * 4
(1, 120.00, 12, 12), -- Under Armour HOVR, $120.00 * 1
(2, 260.00, 13, 13), -- Columbia Bugaboot, $130.00 * 2
(5, 500.00, 14, 14), -- Saucony Endorphin, $100.00 * 5
(3, 270.00, 15, 15), -- Sperry Top-Sider, $90.00 * 3
(1, 75.00, 16, 16), -- Reebok Floatride, $75.00 * 1
(2, 110.00, 17, 17), -- Keds Champion, $55.00 * 2
(3, 330.00, 18, 18), -- Salomon Speedcross, $110.00 * 3
(1, 60.00, 19, 19), -- Xero Shoes Z-Trail, $60.00 * 1
(4, 1000.00, 20, 20), -- Gucci Ace Sneakers, $250.00 * 4
(2, 140.00, 1, 21), -- Skechers Go Walk, $70.00 * 2
(3, 420.00, 2, 22), -- Puma Clyde All-Pro, $140.00 * 3
(5, 800.00, 3, 23), -- North Face Vectiv, $160.00 * 5
(1, 75.00, 4, 24), -- DC Shoes Trase, $75.00 * 1
(2, 260.00, 5, 25), -- Allen Edmonds Park Ave, $130.00 * 2
(4, 460.00, 6, 26), -- Fila Axilus 2 Energized, $115.00 * 4
(1, 90.00, 7, 27), -- Lacoste Carnaby Evo, $90.00 * 1
(3, 375.00, 8, 28), -- Hoka Speedgoat, $125.00 * 3
(2, 90.00, 9, 29), -- Merrell Hydro MOC, $45.00 * 2
(1, 220.00, 10, 30), -- Manolo Blahnik BB, $220.00 * 1
(2, 200.00, 11, 31), -- On Cloud 5, $100.00 * 2
(5, 750.00, 12, 32), -- Jordan Retro 1, $150.00 * 5
(1, 180.00, 13, 33), -- Sorel Caribou, $180.00 * 1
(3, 210.00, 14, 34), -- Toms Alpargata, $70.00 * 3
(4, 760.00, 15, 35), -- Tods Gommino, $190.00 * 4
(2, 260.00, 16, 36), -- Arcteryx Norvan, $130.00 * 2
(3, 225.00, 17, 37), -- Globe Sabre, $75.00 * 3
(5, 700.00, 18, 38), -- Altra Lone Peak, $140.00 * 5
(2, 100.00, 19, 39), -- Chaco Z/Cloud, $50.00 * 2
(4, 3200.00, 20, 40), -- Christian Louboutin So Kate, $800.00 * 4
(1, 60.00, 1, 41), -- Skechers Flex Appeal, $60.00 * 1
(3, 390.00, 2, 42), -- Nike Zoom Freak, $130.00 * 3
(5, 850.00, 3, 43), -- Scarpa Zodiac Plus, $170.00 * 5
(2, 190.00, 4, 44), -- Adidas Superstar, $95.00 * 2
(4, 1000.00, 5, 45), -- Santoni Double Monk, $250.00 * 4
(1, 90.00, 6, 46), -- Nike Air Max 270, $90.00 * 1
(3, 330.00, 7, 47), -- Adidas Harden Vol. 5, $110.00 * 3
(2, 300.00, 8, 48), -- Timberland Premium, $150.00 * 2
(4, 640.00, 9, 49), -- Converse Chuck Taylor, $80.00 * 4
(5, 500.00, 10, 50); -- Clarks Originals, $100.00 * 5



INSERT INTO Orders (feeShip, orderDate, shippingAddress, status, userID, voucherID, total)
VALUES
    (30000, '2024-11-01', '123 Le Loi, Hanoi', 'Shipped', 5, 1, 372000),
    (30000, '2024-11-02', '456 Hoang Hoa Tham, Hanoi', 'Processing', 4, 2, 880000),
    (30000, '2024-11-03', '789 Nguyen Chi Thanh, Hanoi', 'Delivered', 6, 3, 450000),
    (30000, '2024-11-04', '12 Hai Ba Trung, Ho Chi Minh', 'Shipped', 4, 1, 613000),
    (30000, '2024-11-05', '34 Nguyen Hue, Ho Chi Minh', 'Processing', 5, NULL, 900000),
    (30000, '2024-11-06', '56 Tran Phu, Da Nang', 'Shipped', 6, 2, 380000),
    (30000, '2024-11-07', '78 Dien Bien Phu, Da Nang', 'Delivered', 7, NULL, 630000),
    (30000, '2024-11-08', '90 Vo Van Kiet, Can Tho', 'Processing', 8, 2, 995000),
    (30000, '2024-11-09', '11 Nguyen Trai, Hai Phong', 'Shipped', 6, NULL, 120000),
    (30000, '2024-11-10', '22 Hai Ba Trung, Hai Phong', 'Delivered', 10, 1, 3270000),
    (30000, '2024-11-11', '33 Nguyen Cong Tru, Hue', 'Processing', 11, NULL, 430000),
    (30000, '2024-11-12', '44 Le Loi, Hue', 'Shipped', 12, 2, 520000),
    (30000, '2024-11-13', '55 Tran Phu, Nha Trang', 'Processing', 13, NULL, 1140000),
    (30000, '2024-11-14', '66 Nguyen Thien Thuat, Nha Trang', 'Delivered', 14, 3, 450000),
    (30000, '2024-11-15', '77 Tran Hung Dao, Vung Tau', 'Processing', 15, NULL, 1300000),
    (30000, '2024-11-16', '88 Nguyen Trai, Vung Tau', 'Shipped', 16, 1, 97500),
    (30000, '2024-11-17', '99 Le Hong Phong, Da Lat', 'Delivered', 17, NULL, 140000),
    (30000, '2024-11-18', '10 Nguyen Van Cu, Da Lat', 'Processing', 18, NULL, 360000),
    (30000, '2024-11-19', '20 Tran Hung Dao, Quy Nhon', 'Shipped', 19, 1, 84000),
    (30000, '2024-11-20', '30 Vo Thi Sau, Quy Nhon', 'Delivered', 20, NULL, 1030000),
    (30000, '2024-11-21', '123 Le Loi, Hanoi', 'Shipped', 5, 2, 150000),
    (30000, '2024-11-22', '456 Hoang Hoa Tham, Hanoi', 'Processing', 10, 3, 450000),
    (30000, '2024-11-23', '789 Nguyen Chi Thanh, Hanoi', 'Delivered', 8, NULL, 830000),
    (30000, '2024-11-24', '12 Hai Ba Trung, Ho Chi Minh', 'Processing', 4, 1, 37500),
    (30000, '2024-11-25', '34 Nguyen Hue, Ho Chi Minh', 'Delivered', 5, 2, 490000),
    (30000, '2024-11-26', '56 Tran Phu, Da Nang', 'Shipped', 6, NULL, 35000),
    (30000, '2024-11-27', '78 Dien Bien Phu, Da Nang', 'Processing', 7, 3, 375000),
    (30000, '2024-11-28', '90 Vo Van Kiet, Can Tho', 'Delivered', 8, 1, 367500),
    (30000, '2024-11-30', '11 Nguyen Trai, Hai Phong', 'Shipped', 9, 1, 111000),
    (0, '2024-12-01', '22 Hai Ba Trung, Hai Phong', 'Processing', 10, NULL, 220000);




INSERT INTO OrderDetail (price, quantity, productDetailID, orderID)
VALUES
(90000, 2, 1, 1), -- Nike Air Max 270
(120000, 1, 2, 2), -- Adidas Harden Vol. 5
(150000, 3, 3, 3), -- Timberland Premium
(80000, 4, 4, 4), -- Converse Chuck Taylor
(100000, 2, 5, 5), -- Clarks Originals
(110000, 1, 6, 6), -- Asics Gel-Resolution
(75000, 5, 7, 7), -- Vans Old Skool
(95000, 3, 8, 8), -- Brooks Ghost 14
(50000, 1, 9, 9), -- Teva Hurricane XLT2
(200000, 2, 10, 10), -- Jimmy Choo Romy 100
(85000, 4, 11, 11), -- New Balance 574
(120000, 1, 12, 12), -- Under Armour HOVR
(130000, 2, 13, 13), -- Columbia Bugaboot
(100000, 5, 14, 14), -- Saucony Endorphin
(90000, 3, 15, 15), -- Sperry Top-Sider
(75000, 1, 16, 16), -- Reebok Floatride
(55000, 2, 17, 17), -- Keds Champion
(110000, 3, 18, 18), -- Salomon Speedcross
(60000, 1, 19, 19), -- Xero Shoes Z-Trail
(250000, 4, 20, 20), -- Gucci Ace Sneakers
(70000, 2, 21, 21), -- Skechers Go Walk
(140000, 3, 22, 22), -- Puma Clyde All-Pro
(160000, 5, 23, 23), -- North Face Vectiv
(75000, 1, 24, 24), -- DC Shoes Trase
(130000, 2, 25, 25), -- Allen Edmonds Park Ave
(115000, 4, 26, 26), -- Fila Axilus 2 Energized
(90000, 1, 27, 27), -- Lacoste Carnaby Evo
(125000, 3, 28, 28), -- Hoka Speedgoat
(45000, 2, 29, 29), -- Merrell Hydro MOC
(220000, 1, 30, 30), -- Manolo Blahnik BB
(100000, 2, 31, 1), -- On Cloud 5
(150000, 5, 32, 2), -- Jordan Retro 1
(180000, 1, 33, 3), -- Sorel Caribou
(70000, 3, 34, 4), -- Toms Alpargata
(190000, 4, 35, 5), -- Tods Gommino
(130000, 2, 36, 6), -- Arcteryx Norvan
(75000, 3, 37, 7), -- Globe Sabre
(140000, 5, 38, 8), -- Altra Lone Peak
(50000, 2, 39, 9), -- Chaco Z/Cloud
(800000, 4, 40, 10), -- Christian Louboutin So Kate
(60000, 1, 41, 11), -- Skechers Flex Appeal
(130000, 3, 42, 12), -- Nike Zoom Freak
(170000, 5, 43, 13), -- Scarpa Zodiac Plus
(95000, 2, 44, 14), -- Adidas Superstar
(250000, 4, 45, 15); -- Santoni Double Monk

INSERT INTO Payment (paymentDate, status, orderID)
VALUES
('2024-11-01', 'Completed', 1),
('2024-11-02', 'Pending', 2),
('2024-11-03', 'Failed', 3),
('2024-11-04', 'Completed', 4),
('2024-11-05', 'Completed', 5),
('2024-11-06', 'Pending', 6),
('2024-11-07', 'Completed', 7),
('2024-11-08', 'Pending', 8),
('2024-11-09', 'Failed', 9),
('2024-11-10', 'Completed', 10),
('2024-11-11', 'Pending', 11),
('2024-11-12', 'Completed', 12),
('2024-11-13', 'Completed', 13),
('2024-11-14', 'Pending', 14),
('2024-11-15', 'Failed', 15),
('2024-11-16', 'Completed', 16),
('2024-11-17', 'Pending', 17),
('2024-11-18', 'Completed', 18),
('2024-11-19', 'Failed', 19),
('2024-11-20', 'Completed', 20),
('2024-11-21', 'Pending', 21),
('2024-11-22', 'Completed', 22),
('2024-11-23', 'Failed', 23),
('2024-11-24', 'Completed', 24),
('2024-11-25', 'Completed', 25),
('2024-11-26', 'Pending', 26),
('2024-11-27', 'Completed', 27),
('2024-11-28', 'Completed', 28),
('2024-11-29', 'Pending', 29),
('2024-11-30', 'Failed', 30);


INSERT INTO Receipt (receiptDate, total, paymentID)
VALUES
('2024-11-01', 180.00, 1),
('2024-11-02', 120.00, 2),
('2024-11-03', 450.00, 3),
('2024-11-04', 320.00, 4),
('2024-11-05', 200.00, 5),
('2024-11-06', 110.00, 6),
('2024-11-07', 375.00, 7),
('2024-11-08', 285.00, 8),
('2024-11-09', 50.00, 9),
('2024-11-10', 400.00, 10),
('2024-11-11', 340.00, 11),
('2024-11-12', 120.00, 12),
('2024-11-13', 260.00, 13),
('2024-11-14', 500.00, 14),
('2024-11-15', 270.00, 15),
('2024-11-16', 75.00, 16),
('2024-11-17', 110.00, 17),
('2024-11-18', 330.00, 18),
('2024-11-19', 60.00, 19),
('2024-11-20', 1000.00, 20),
('2024-11-21', 140.00, 21),
('2024-11-22', 420.00, 22),
('2024-11-23', 800.00, 23),
('2024-11-24', 320.00, 24),
('2024-11-25', 260.00, 25),
('2024-11-26', 460.00, 26),
('2024-11-27', 390.00, 27),
('2024-11-28', 300.00, 28),
('2024-11-29', 250.00, 29),
('2024-11-30', 520.00, 30);


INSERT INTO Review (contents, createDate, start, productID, userID)
VALUES
('Great quality!', '2024-11-01', 5, 1, 1),
('Comfortable to wear.', '2024-11-02', 4, 2, 2),
('Stylish and durable.', '2024-11-03', 5, 3, 3),
('Affordable price.', '2024-11-04', 4, 4, 4),
('Highly recommend!', '2024-11-05', 5, 5, 5),
('Not true to size.', '2024-11-06', 3, 6, 6),
('Great for sports.', '2024-11-07', 5, 7, 7),
('Very comfortable.', '2024-11-08', 4, 8, 8),
('Looks amazing.', '2024-11-09', 5, 9, 9),
('Best shoes ever!', '2024-11-10', 5, 10, 10),
('Fast shipping.', '2024-11-11', 4, 11, 11),
('Perfect fit.', '2024-11-12', 5, 12, 12),
('Good value.', '2024-11-13', 4, 13, 13),
('Highly functional.', '2024-11-14', 5, 14, 14),
('Not very durable.', '2024-11-15', 3, 15, 15),
('Looks premium.', '2024-11-16', 4, 16, 16),
('Works as expected.', '2024-11-17', 5, 17, 17),
('Could be better.', '2024-11-18', 3, 18, 18),
('Excellent material.', '2024-11-19', 5, 19, 19),
('Highly recommend!', '2024-11-20', 5, 20, 20);

<<<<<<< HEAD

=======
>>>>>>> 11d64cb4fdee43e78e7288a8d24d9c52ee2e789c

