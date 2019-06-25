DROP TABLE IF EXISTS user;

CREATE TABLE user (
    id         INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    username   VARCHAR(50) NOT NULL,
    password   VARCHAR(50) NOT NULL
);

DROP TABLE IF EXISTS account;

CREATE TABLE account (
    customer_id   INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    customer_name VARCHAR(128) NOT NULL,
    position      NUMERIC(20, 2)
);