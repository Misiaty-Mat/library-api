DROP DATABASE IF EXISTS librarydb;
DROP USER IF EXISTS `rest-library`@`%`;
CREATE DATABASE IF NOT EXISTS librarydb CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE USER IF NOT EXISTS `rest-library`@`%` IDENTIFIED WITH mysql_native_password BY 'password';
GRANT SELECT, INSERT, UPDATE, DELETE, CREATE, DROP, REFERENCES, INDEX, ALTER, EXECUTE, CREATE VIEW, SHOW VIEW,
CREATE ROUTINE, ALTER ROUTINE, EVENT, TRIGGER ON `librarydb`.* TO `rest-library`@`%`;