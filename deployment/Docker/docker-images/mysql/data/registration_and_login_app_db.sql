

DROP DATABASE IF EXISTS registration_and_login_app_db;
CREATE DATABASE IF NOT EXISTS registration_and_login_app_db DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

USE registration_and_login_app_db;

CREATE TABLE IF NOT EXISTS user(
id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
username VARCHAR(30) UNIQUE NOT NULL,
email VARCHAR(30) UNIQUE NOT NULL,
`password_hash` VARCHAR(60) NOT NULL,
`can_access_all_data` BOOL NOT NULL DEFAULT FALSE,
PRIMARY KEY (id)
)ENGINE=INNODB;


INSERT INTO user(username, email, `password_hash`, `can_access_all_data`)
VALUES ('admin','admin@me.de','taALWdDATA3hBJ6PQzVoOXtWl9ptksVTwNPS7bp+EF7AP6jmSrm+HQ==', TRUE); 

INSERT INTO user(username, email, `password_hash`) 
VALUES ('tchuensu237','rodrigue@me.de','Z+go39FhIZWgg04F6+wggFaGK42Cs3qct02yfZplXvTFFKdDv/7lGQ=='),
       ('müller49','müller@me.de','iyAD2kmS3sfQKEPBstK+XbXxtlCjkFhqe1XejppF94/IEOTve6SyQg==');

--  username=admin pwd=@dmin ; username=tchuensu237 pwd=123456 ; username=müller49 pwd=#Sicherheit


CREATE USER 'spring-server'@'localhost' IDENTIFIED BY '@Spring';
GRANT ALL PRIVILEGES ON registration_and_login_app_db.* TO 'spring-server'@'localhost' ;