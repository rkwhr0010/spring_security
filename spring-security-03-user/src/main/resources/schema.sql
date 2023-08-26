--CREATE DATABASE spring default CHARACTER SET UTF8; 

--DROP TABLE `spring`.`users`;
--DROP TABLE `spring`.`authorities`;

CREATE TABLE IF NOT EXISTS `spring`.`users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NULL,
  `password` VARCHAR(45) NULL,
  `enabled` INT NOT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE IF NOT EXISTS `spring`.`authorities` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NULL,
  `authority` VARCHAR(45) NULL,
  PRIMARY KEY (`id`));
