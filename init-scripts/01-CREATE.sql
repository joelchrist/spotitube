-- MySQL Script generated by MySQL Workbench
-- Thu Nov  2 21:31:19 2017
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema spotitube
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema spotitube
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `spotitube` DEFAULT CHARACTER SET utf8 ;
USE `spotitube` ;

-- -----------------------------------------------------
-- Table `spotitube`.`Tracks`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `spotitube`.`Tracks` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(255) NOT NULL,
  `performer` VARCHAR(45) NOT NULL,
  `duration` INT NOT NULL,
  `album` VARCHAR(255) NULL,
  `play_count` INT NULL,
  `publication_date` DATETIME NULL,
  `description` TEXT NULL,
  `offline_available` TINYINT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `spotitube`.`Users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `spotitube`.`Users` (
  `user` VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`user`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `spotitube`.`Playlists`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `spotitube`.`Playlists` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `owner` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_owner_idx` (`owner` ASC),
  CONSTRAINT `FK_owner`
    FOREIGN KEY (`owner`)
    REFERENCES `spotitube`.`Users` (`user`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `spotitube`.`TrackPlaylist`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `spotitube`.`PlaylistTrack` (
  `track_id` INT NOT NULL,
  `playlist_id` INT NOT NULL,
  PRIMARY KEY (`playlist_id`, `track_id`),
  INDEX `FK_Track_idx` (`track_id` ASC),
  CONSTRAINT `FK_Track`
    FOREIGN KEY (`track_id`)
    REFERENCES `spotitube`.`Tracks` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_Playlist`
    FOREIGN KEY (`playlist_id`)
    REFERENCES `spotitube`.`Playlists` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `spotitube`.`AuthenticationTokens`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `spotitube`.`AuthenticationTokens` (
  `user` VARCHAR(255) NOT NULL,
  `token` VARCHAR(14) NOT NULL,
  `expiry_date` DATETIME NOT NULL,
  PRIMARY KEY (`user`),
  CONSTRAINT `FK_user`
    FOREIGN KEY (`user`)
    REFERENCES `spotitube`.`Users` (`user`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
