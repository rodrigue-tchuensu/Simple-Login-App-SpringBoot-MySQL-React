

DROP DATABASE IF EXISTS registration_and_login_app_db;
CREATE DATABASE IF NOT EXISTS registration_and_login_app_db DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

USE registration_and_login_app_db;

CREATE TABLE IF NOT EXISTS user(
id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
username VARCHAR(30) UNIQUE NOT NULL,
email VARCHAR(30) UNIQUE NOT NULL,
`password_hash` VARCHAR(60) NOT NULL,
PRIMARY KEY (id)
)ENGINE=INNODB;


INSERT INTO user(username, email, `password_hash`) 
VALUES ('admin','admin@me.de','$2a$10$nvv0blzq/VthCg/ltidWDuBzvncmif4msiFsh..faCitHMOePZp.i'),
       ('tchuensu237','rodrigue@me.de','$2a$10$QCimFgSFCvI/g5tdvUaZJuYwe2IcLPyGmrlINYDSCCIH7nbR3Zcya'),
       ('müller49','müller@me.de','$2a$10$FcbsyJ.UrCAEVJppvhYCKenFXRPeOZ/rLTULIt2ZOTZ234P4nWK8K');
	   
	   
--  username=admin pwd=@dmin ; username=tchuensu237 pwd=123456 ; username=müller49 pwd=#Sicherheit


CREATE USER 'spring-server'@'localhost' IDENTIFIED BY '@Spring';
GRANT ALL PRIVILEGES ON registration_and_login_app_db.* TO 'spring-server'@'localhost' ;
