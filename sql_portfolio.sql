CREATE DATABASE portfolio;
USE portfolio;
CREATE TABLE User (
    user_id BIGINT PRIMARY KEY auto_increment,
    username VARCHAR(255),
    password VARCHAR(255),
    email VARCHAR(255),
    country VARCHAR(255)
);

-- Cash table
CREATE TABLE Cash (
    cash_id BIGINT PRIMARY KEY auto_increment,
    user_id BIGINT,
    amount DECIMAL(10, 2)
);

-- Stocks table
CREATE TABLE Stocks (
    stock_id BIGINT PRIMARY KEY auto_increment,
    stock_name VARCHAR(255)
);

-- Stock_price table
CREATE TABLE Stock_detail (
    stock_detail_id BIGINT PRIMARY KEY auto_increment,
    stock_id BIGINT,
    stock_price DECIMAL(10, 2),
    time DATETIME
);

-- Bonds table
CREATE TABLE Bonds (
    bond_id BIGINT PRIMARY KEY auto_increment,
    bond_name VARCHAR(255)
);

-- Bonds_price table
CREATE TABLE Bond_detail (
    bond_detail_id BIGINT PRIMARY KEY auto_increment,
    bond_id BIGINT,
    bond_price DECIMAL(10, 2),
    time DATETIME
);

-- Transaction_record table
CREATE TABLE Transaction_record (
    tr_id BIGINT PRIMARY KEY auto_increment,
    user_id BIGINT,
    transaction_type INT,
    number_of_transaction INT,
    production_id BIGINT,
    production_type INT,
    production_detail_id BIGINT,
    production_price DECIMAL(10, 2),
    cost DECIMAL(10, 2),
    remain_cash DECIMAL(10, 2),
    time DATETIME
);

-- Hold_assets table
CREATE TABLE Hold_assets (
    hold_assets_id BIGINT PRIMARY KEY auto_increment,
    user_id BIGINT,
    production_id BIGINT,
    production_type INT,
    production_amount BIGINT
);