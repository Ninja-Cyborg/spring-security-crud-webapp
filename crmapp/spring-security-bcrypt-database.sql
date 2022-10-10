DROP DATABASE  IF EXISTS `web_client_app_bcrypt`;

CREATE DATABASE  IF NOT EXISTS `web_client_app_bcrypt`;
USE `web_client_app_bcrypt`;

-- Bcrypt password stariting from 2a works with spring not 2y

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `password` char(68) NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- NOTE: The passwords are encrypted using BCrypt
-- A generation tool is avail at: https://bcrypt.online/
-- Default passwords here are: user123
-- user meryl has all rights to test the system

INSERT INTO `users` 
VALUES 
('Meryl','{bcrypt}$2a$10$04q9ukJY.U0yk2tIipwSIuRplL6xfARtqJaYi/iZDfeRuyj9BV/ly',1),
('Mervin','{bcrypt}$2a$10$04q9ukJY.U0yk2tIipwSIuRplL6xfARtqJaYi/iZDfeRuyj9BV/ly',1),
('Rochette','{bcrypt}$2a$10$04q9ukJY.U0yk2tIipwSIuRplL6xfARtqJaYi/iZDfeRuyj9BV/ly',1),
('Anabel','{bcrypt}$2a$10$04q9ukJY.U0yk2tIipwSIuRplL6xfARtqJaYi/iZDfeRuyj9BV/ly',1),
('Domeniga','{bcrypt}$2a$10$04q9ukJY.U0yk2tIipwSIuRplL6xfARtqJaYi/iZDfeRuyj9BV/ly',1),
('Cordell','{bcrypt}$2a$10$04q9ukJY.U0yk2tIipwSIuRplL6xfARtqJaYi/iZDfeRuyj9BV/ly',1),
('Miguelita','{bcrypt}$2a$10$04q9ukJY.U0yk2tIipwSIuRplL6xfARtqJaYi/iZDfeRuyj9BV/ly',1),
('Hilary','{bcrypt}$2a$10$04q9ukJY.U0yk2tIipwSIuRplL6xfARtqJaYi/iZDfeRuyj9BV/ly',1),
 ('Hedda', '{bcrypt}$2a$10$04q9ukJY.U0yk2tIipwSIuRplL6xfARtqJaYi/iZDfeRuyj9BV/ly',1);

DROP TABLE IF EXISTS `authorities`;
CREATE TABLE `authorities` (
  `username` varchar(50) NOT NULL,
  `authority` varchar(50) NOT NULL,
  UNIQUE KEY `authorities_idx_1` (`username`,`authority`),
  CONSTRAINT `authorities_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `authorities` 
VALUES 
	('Meryl','ROLE_EMPLOYEE'),
    ('Meryl','ROLE_MANAGER'),
    ('Meryl','ROLE_ADMIN'),
	('Mervin','ROLE_EMPLOYEE'),
	('Rochette','ROLE_EMPLOYEE'),
	('Anabel','ROLE_EMPLOYEE'),
    ('Anabel','ROLE_ADMIN'),
	('Domeniga', 'ROLE_EMPLOYEE'),
	('Cordell', 'ROLE_EMPLOYEE'),
	('Miguelita', 'ROLE_EMPLOYEE'),
	('Hilary', 'ROLE_EMPLOYEE'),
	 ('Hedda', 'ROLE_EMPLOYEE');

