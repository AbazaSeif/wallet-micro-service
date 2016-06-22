-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema core_wallet
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema core_wallet
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `core_wallet` DEFAULT CHARACTER SET utf8 ;
USE `core_wallet` ;

-- -----------------------------------------------------
-- Table `core_wallet`.`currency`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `core_wallet`.`currency` (
  `id` VARCHAR(3) NOT NULL,
  `creation_date_time` TIMESTAMP NOT NULL,
  PRIMARY KEY (`id`))
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `core_wallet`.`wallet`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `core_wallet`.`wallet` (
  `id` BIGINT NOT NULL,
  `balance` DECIMAL(20,2) NOT NULL,
  `creation_date_time` TIMESTAMP NOT NULL,
  `fk_country` VARCHAR(2) NOT NULL,
  `fk_currency` VARCHAR(3) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_wallet_currency1_idx` (`fk_currency` ASC),
  CONSTRAINT `fk_wallet_currency1`
  FOREIGN KEY (`fk_currency`)
  REFERENCES `core_wallet`.`currency` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `core_wallet`.`wallet_transaction_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `core_wallet`.`wallet_transaction_type` (
  `id` INT NOT NULL,
  `name` VARCHAR(100) NOT NULL COMMENT 'Withdraw,\ndirect debit',
  PRIMARY KEY (`id`))
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `core_wallet`.`wallet_transaction`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `core_wallet`.`wallet_transaction` (
  `id` BIGINT NOT NULL,
  `fk_wallet_source` BIGINT NOT NULL,
  `fk_wallet_dest` BIGINT NOT NULL,
  `amount` DECIMAL(20,2) NOT NULL,
  `reference` LONGTEXT NOT NULL,
  `description` LONGTEXT NULL,
  `creation_date_time` TIMESTAMP NOT NULL,
  `fk_transaction_type` INT NOT NULL,
  `additional_information` JSON NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_wallet_transaction_wallet_idx` (`fk_wallet_source` ASC),
  INDEX `fk_wallet_transaction_wallet1_idx` (`fk_wallet_dest` ASC),
  INDEX `fk_wallet_transaction_wallet_transaction_type1_idx` (`fk_transaction_type` ASC),
  CONSTRAINT `fk_wallet_transaction_wallet`
  FOREIGN KEY (`fk_wallet_source`)
  REFERENCES `core_wallet`.`wallet` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_wallet_transaction_wallet1`
  FOREIGN KEY (`fk_wallet_dest`)
  REFERENCES `core_wallet`.`wallet` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_wallet_transaction_wallet_transaction_type1`
  FOREIGN KEY (`fk_transaction_type`)
  REFERENCES `core_wallet`.`wallet_transaction_type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
