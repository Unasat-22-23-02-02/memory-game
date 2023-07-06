CREATE DATABASE unasat

CREATE TABLE `unasat`.`users` (
                                  `id` INT NOT NULL AUTO_INCREMENT,
                                  `name` VARCHAR(45) NOT NULL,
                                  `firstname` VARCHAR(45) NOT NULL,
                                  `username` VARCHAR(45) NOT NULL,
                                  `pin` INT NOT NULL,
                                  PRIMARY KEY (`id`),
                                  UNIQUE INDEX `username_UNIQUE` (`username` ASC));

CREATE TABLE `unasat`.`games` (
                                  `id` INT NOT NULL AUTO_INCREMENT,
                                  `user_id` VARCHAR(45) NOT NULL,
                                  `score` VARCHAR(45) NOT NULL,
                                  `datetime` VARCHAR(45) NOT NULL,
                                  PRIMARY KEY (`id`)),