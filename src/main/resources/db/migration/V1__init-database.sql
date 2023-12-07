drop table if exists book;
drop table if exists users;

create table book (
    id VARCHAR(36) NOT NULL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    author VARCHAR(50) NOT NULL,
    category VARCHAR(50) NOT NULL,
    tag VARCHAR(50) NOT NULL,
    isbn VARCHAR(16) NOT NULL UNIQUE,
    available BOOLEAN NOT NULL
) engine=InnoDB;

create table users (
    id VARCHAR(36) NOT NULL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    surname VARCHAR(50) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL
) engine=InnoDB;