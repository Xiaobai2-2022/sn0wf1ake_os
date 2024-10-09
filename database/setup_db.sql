-- setup_db.sql

-- create database if does not exist
CREATE DATABASE IF NOT EXISTS sfdevdb;
USE sfdevdb;

-- create table to store command discriptions
DROP TABLE IF EXISTS command_lists;
CREATE TABLE IF NOT EXISTS command_lists (
    id INT AUTO_INCREMENT PRIMARY KEY,
    command VARCHAR(50) NOT NULL UNIQUE,
    short_description TINYTEXT NOT NULL,
    long_description TEXT
);
