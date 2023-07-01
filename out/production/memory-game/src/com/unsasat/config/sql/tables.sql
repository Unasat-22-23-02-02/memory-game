CREATE DATABASE unasat

CREATE TABLE `unasat`.`users` (
                                  `id` INT NOT NULL AUTO_INCREMENT,
                                  `name` VARCHAR(45) NULL,
                                  `firstname` VARCHAR(45) NULL,
                                  `username` VARCHAR(45) NULL,
                                  `pin` INT NULL,
                                  PRIMARY KEY (`id`),
                                  UNIQUE INDEX `username_UNIQUE` (`username` ASC));

CREATE TABLE `unasat`.`games` (
                                  `id` INT NOT NULL,
                                  `user_id` VARCHAR(45) NULL,
                                  `score` VARCHAR(45) NULL,
                                  `datetime` VARCHAR(45) NULL,
                                  PRIMARY KEY (`id`));