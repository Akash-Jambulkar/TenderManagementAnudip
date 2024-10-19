CREATE DATABASE TenderManagement;
USE TenderManagement;

-- Create the Admin table
CREATE TABLE Admin (
    admin_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL
);

-- Create the Vendor table
CREATE TABLE Vendor (
    vendor_id INT AUTO_INCREMENT PRIMARY KEY,
    vendor_name VARCHAR(100),
    contact_info VARCHAR(100),
    username VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL
);

-- Create the Tender table
CREATE TABLE Tender (
    tender_id INT AUTO_INCREMENT PRIMARY KEY,
    description TEXT,
    budget DECIMAL(10, 2),
    deadline DATE,
    status VARCHAR(20),
    assigned_vendor_id INT,
    FOREIGN KEY (assigned_vendor_id) REFERENCES Vendor(vendor_id)
);

-- Create the Bid table
CREATE TABLE Bid (
    bid_id INT AUTO_INCREMENT PRIMARY KEY,
    tender_id INT,
    vendor_id INT,
    bid_amount DECIMAL(10, 2),
    status VARCHAR(20),
    FOREIGN KEY (tender_id) REFERENCES Tender(tender_id),
    FOREIGN KEY (vendor_id) REFERENCES Vendor(vendor_id)
);

SHOW TABLES;

INSERT INTO Admin (username, password) 
VALUES ('admin1', 'password123'),
       ('admin2', 'password456');

INSERT INTO Vendor (vendor_name, contact_info, username, password) 
VALUES ('Vendor One', 'vendor1@example.com', 'vendor1', 'vendorpass1'),
       ('Vendor Two', 'vendor2@example.com', 'vendor2', 'vendorpass2');

INSERT INTO Tender (description, budget, deadline, status, assigned_vendor_id) 
VALUES ('Construction of office building', 500000.00, '2024-12-31', 'Open', NULL),
       ('IT infrastructure setup', 150000.00, '2024-11-15', 'Open', NULL);

INSERT INTO Bid (tender_id, vendor_id, bid_amount, status) 
VALUES (1, 1, 450000.00, 'Pending'),
       (2, 2, 140000.00, 'Pending');

SELECT * FROM Admin;
SELECT * FROM Vendor;
SELECT * FROM Tender;
SELECT * FROM Bid;

CREATE TABLE Bids (
    bid_id INT AUTO_INCREMENT PRIMARY KEY,
    tender_id INT NOT NULL,
    bid_amount DECIMAL(10, 2) NOT NULL,
    status VARCHAR(50) NOT NULL DEFAULT 'Pending',
    vendor_id INT NOT NULL,
    FOREIGN KEY (tender_id) REFERENCES Tenders(tender_id), -- Assuming you have a Tenders table
    FOREIGN KEY (vendor_id) REFERENCES Vendor(vendor_id)  -- Assuming you have a Vendor table
);

