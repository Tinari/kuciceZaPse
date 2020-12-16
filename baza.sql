/*
SQLyog Trial v13.1.7 (64 bit)
MySQL - 10.4.14-MariaDB : Database - baza
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`baza` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `baza`;

/*Table structure for table `kucicazapse` */

DROP TABLE IF EXISTS `kucicazapse`;

CREATE TABLE `kucicazapse` (
  `kucicaZaPseId` int(11) NOT NULL,
  `nazivKuciceZaPse` varchar(50) DEFAULT NULL,
  `tipId` int(11) DEFAULT NULL,
  PRIMARY KEY (`kucicaZaPseId`),
  KEY `tipId` (`tipId`),
  CONSTRAINT `kucicazapse_ibfk_2` FOREIGN KEY (`tipId`) REFERENCES `tipkucicezapse` (`tipId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `kucicazapse` */

insert  into `kucicazapse`(`kucicaZaPseId`,`nazivKuciceZaPse`,`tipId`) values 
(1,'Xo Xo',1),
(2,'Zig Zag',3),
(3,'Paw Paw',2);

/*Table structure for table `materijal` */

DROP TABLE IF EXISTS `materijal`;

CREATE TABLE `materijal` (
  `materijalId` int(11) NOT NULL,
  `nazivMaterijala` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`materijalId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `materijal` */

insert  into `materijal`(`materijalId`,`nazivMaterijala`) values 
(1,'drvo'),
(2,'plastika'),
(3,'cigla');

/*Table structure for table `mesto` */

DROP TABLE IF EXISTS `mesto`;

CREATE TABLE `mesto` (
  `idMesto` int(11) NOT NULL,
  `nazivMesta` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`idMesto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `mesto` */

insert  into `mesto`(`idMesto`,`nazivMesta`) values 
(1,'Beograd'),
(2,'NYC');

/*Table structure for table `nacinizrade` */

DROP TABLE IF EXISTS `nacinizrade`;

CREATE TABLE `nacinizrade` (
  `nacinId` int(11) NOT NULL,
  `opis` varchar(50) DEFAULT NULL,
  `datum` date DEFAULT NULL,
  `jmbg` varchar(50) DEFAULT NULL,
  `kucicaZaPseId` int(11) DEFAULT NULL,
  PRIMARY KEY (`nacinId`),
  KEY `jmbg` (`jmbg`),
  KEY `kucicaZaPseId` (`kucicaZaPseId`),
  CONSTRAINT `nacinizrade_ibfk_1` FOREIGN KEY (`jmbg`) REFERENCES `radnik` (`jmbg`),
  CONSTRAINT `nacinizrade_ibfk_2` FOREIGN KEY (`kucicaZaPseId`) REFERENCES `kucicazapse` (`kucicaZaPseId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `nacinizrade` */

/*Table structure for table `radnik` */

DROP TABLE IF EXISTS `radnik`;

CREATE TABLE `radnik` (
  `jmbg` varchar(50) NOT NULL,
  `ime` varchar(50) DEFAULT NULL,
  `prezime` varchar(50) DEFAULT NULL,
  `pol` varchar(10) DEFAULT NULL,
  `datumZaposlenja` date DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  `idMesto` int(11) DEFAULT NULL,
  PRIMARY KEY (`jmbg`),
  KEY `idMesto` (`idMesto`),
  CONSTRAINT `radnik_ibfk_1` FOREIGN KEY (`idMesto`) REFERENCES `mesto` (`idMesto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `radnik` */

insert  into `radnik`(`jmbg`,`ime`,`prezime`,`pol`,`datumZaposlenja`,`status`,`idMesto`) values 
('',NULL,NULL,NULL,NULL,NULL,NULL),
('0304990715715','Filip','Markovic','Muski','2020-11-06','Stalni radnik',2),
('2109995745045','Jovana','Jovcic','Zenski','2020-10-24','Stalna radnica',1),
('2403995715715','Andjela','Topalovic','Zenski','2020-10-15','Radnik na praksi',2);

/*Table structure for table `stavkaizrade` */

DROP TABLE IF EXISTS `stavkaizrade`;

CREATE TABLE `stavkaizrade` (
  `rbStavke` int(11) NOT NULL,
  `nacinId` int(11) NOT NULL,
  `materijalId` int(11) DEFAULT NULL,
  `kolicina` double DEFAULT NULL,
  `jedinicaMere` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`rbStavke`,`nacinId`),
  KEY `materijalId` (`materijalId`),
  KEY `nacinId` (`nacinId`),
  CONSTRAINT `stavkaizrade_ibfk_1` FOREIGN KEY (`materijalId`) REFERENCES `materijal` (`materijalId`),
  CONSTRAINT `stavkaizrade_ibfk_2` FOREIGN KEY (`nacinId`) REFERENCES `nacinizrade` (`nacinId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `stavkaizrade` */

/*Table structure for table `tipkucicezapse` */

DROP TABLE IF EXISTS `tipkucicezapse`;

CREATE TABLE `tipkucicezapse` (
  `tipId` int(11) NOT NULL,
  `nazivTipa` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`tipId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `tipkucicezapse` */

insert  into `tipkucicezapse`(`tipId`,`nazivTipa`) values 
(0,NULL),
(1,'mala'),
(2,'srednja'),
(3,'velika');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
