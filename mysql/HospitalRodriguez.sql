-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.1.16-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win32
-- HeidiSQL Version:             9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for consultorio
CREATE DATABASE IF NOT EXISTS `consultorio` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `consultorio`;

-- Dumping structure for table consultorio.apointment
CREATE TABLE IF NOT EXISTS `apointment` (
  `idapointment` int(11) NOT NULL AUTO_INCREMENT,
  `date` varchar(45) NOT NULL,
  `time` varchar(45) NOT NULL,
  `Medication` varchar(45) NOT NULL,
  `Username_idUsername` int(11) NOT NULL,
  `Patient_idPatient` int(11) NOT NULL,
  PRIMARY KEY (`idapointment`,`Username_idUsername`,`Patient_idPatient`),
  KEY `fk_Apointment_Username_idx` (`Username_idUsername`),
  KEY `fk_Apointment_Patient1_idx` (`Patient_idPatient`),
  CONSTRAINT `fk_Apointment_Patient1` FOREIGN KEY (`Patient_idPatient`) REFERENCES `patient` (`idPatient`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Apointment_Username` FOREIGN KEY (`Username_idUsername`) REFERENCES `username` (`idUsername`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- Dumping data for table consultorio.apointment: ~4 rows (approximately)
/*!40000 ALTER TABLE `apointment` DISABLE KEYS */;
REPLACE INTO `apointment` (`idapointment`, `date`, `time`, `Medication`, `Username_idUsername`, `Patient_idPatient`) VALUES
	(1, '23/01/2016', '10:30', '', 1, 1),
	(2, '12/01/2016', '10:50', 'x34', 4, 2),
	(9, '23/12/2016', '09:30', 'HH5', 1, 1),
	(20, '20/12/2016', '08:30', 'gg', 1, 4);
/*!40000 ALTER TABLE `apointment` ENABLE KEYS */;

-- Dumping structure for table consultorio.billing
CREATE TABLE IF NOT EXISTS `billing` (
  `idBilling` int(11) NOT NULL AUTO_INCREMENT,
  `charge` varchar(45) NOT NULL,
  `Status` varchar(45) NOT NULL,
  `Total` varchar(45) NOT NULL,
  `Patient_idPatient` int(11) NOT NULL,
  `Username_idUsername` int(11) NOT NULL,
  PRIMARY KEY (`idBilling`,`Patient_idPatient`,`Username_idUsername`),
  KEY `fk_billing Department_Patient1_idx` (`Patient_idPatient`),
  KEY `fk_billing Department_Username1_idx` (`Username_idUsername`),
  CONSTRAINT `fk_billing Department_Patient1` FOREIGN KEY (`Patient_idPatient`) REFERENCES `patient` (`idPatient`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_billing Department_Username1` FOREIGN KEY (`Username_idUsername`) REFERENCES `username` (`idUsername`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- Dumping data for table consultorio.billing: ~4 rows (approximately)
/*!40000 ALTER TABLE `billing` DISABLE KEYS */;
REPLACE INTO `billing` (`idBilling`, `charge`, `Status`, `Total`, `Patient_idPatient`, `Username_idUsername`) VALUES
	(1, '50', 'paid', '50', 2, 1),
	(2, '30', 'unpaid', '30', 1, 4),
	(3, '30', 'unpaid', '30', 4, 4),
	(4, '50', 'unpaid', '50', 1, 4);
/*!40000 ALTER TABLE `billing` ENABLE KEYS */;

-- Dumping structure for table consultorio.messages
CREATE TABLE IF NOT EXISTS `messages` (
  `idMessages` int(11) NOT NULL AUTO_INCREMENT,
  `Message` varchar(250) NOT NULL,
  `status` varchar(45) NOT NULL,
  `Read_at` varchar(45) NOT NULL,
  `date` varchar(45) NOT NULL,
  `time` varchar(45) NOT NULL,
  `Username_idUsername` int(11) NOT NULL,
  `Patient_idPatient` int(11) NOT NULL,
  PRIMARY KEY (`idMessages`,`Username_idUsername`,`Patient_idPatient`),
  KEY `fk_Messages_Username1_idx` (`Username_idUsername`),
  KEY `fk_Messages_Patient1_idx` (`Patient_idPatient`),
  CONSTRAINT `fk_Messages_Patient1` FOREIGN KEY (`Patient_idPatient`) REFERENCES `patient` (`idPatient`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Messages_Username1` FOREIGN KEY (`Username_idUsername`) REFERENCES `username` (`idUsername`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- Dumping data for table consultorio.messages: ~1 rows (approximately)
/*!40000 ALTER TABLE `messages` DISABLE KEYS */;
REPLACE INTO `messages` (`idMessages`, `Message`, `status`, `Read_at`, `date`, `time`, `Username_idUsername`, `Patient_idPatient`) VALUES
	(10, 'late', 'read', '18:29 29/12/16', '16/12/16', '1212', 4, 1),
	(14, 'I am going to be late ', 'read', '23:04 29/12/16', '25/12/16', '12:30', 1, 2);
/*!40000 ALTER TABLE `messages` ENABLE KEYS */;

-- Dumping structure for table consultorio.patient
CREATE TABLE IF NOT EXISTS `patient` (
  `idPatient` int(11) NOT NULL AUTO_INCREMENT,
  `name_P` varchar(45) NOT NULL,
  `Lastname_P` varchar(45) NOT NULL,
  `Phone` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `birthyear` int(11) NOT NULL,
  PRIMARY KEY (`idPatient`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- Dumping data for table consultorio.patient: ~5 rows (approximately)
/*!40000 ALTER TABLE `patient` DISABLE KEYS */;
REPLACE INTO `patient` (`idPatient`, `name_P`, `Lastname_P`, `Phone`, `email`, `birthyear`) VALUES
	(1, 'Jorge', 'Prado', '484961659', 'Jorge@live.com', 1925),
	(2, 'Magdalena', 'Rios', '494949', 'mag@live.com', 1990),
	(4, 'Michael', 'Jackson', '04949849', 'michael@live.com', 1945),
	(5, 'Michael', 'Perez', '84984984', 'mi@live.com', 1945),
	(6, 'Ernesto', 'Prado', '9132191', 'netho@live.com', 1990);
/*!40000 ALTER TABLE `patient` ENABLE KEYS */;

-- Dumping structure for table consultorio.username
CREATE TABLE IF NOT EXISTS `username` (
  `idUsername` int(11) NOT NULL AUTO_INCREMENT,
  `Username` varchar(45) NOT NULL,
  `Userlastname` varchar(45) NOT NULL,
  `Department` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  PRIMARY KEY (`idUsername`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- Dumping data for table consultorio.username: ~4 rows (approximately)
/*!40000 ALTER TABLE `username` DISABLE KEYS */;
REPLACE INTO `username` (`idUsername`, `Username`, `Userlastname`, `Department`, `password`) VALUES
	(1, 'Ernesto', 'Prado', 'doctor', 'pass123'),
	(2, 'Berenice', 'Prado', 'reception', 'password'),
	(3, 'Fernanda', 'Ruvalcaba', 'billing', 'pass'),
	(4, 'Jhony', 'rubert', 'doctor', 'pass342');
/*!40000 ALTER TABLE `username` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
