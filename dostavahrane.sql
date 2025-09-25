/*M!999999\- enable the sandbox mode */ 
-- MariaDB dump 10.19-12.0.2-MariaDB, for Linux (x86_64)
--
-- Host: localhost    Database: dostavahrane
-- ------------------------------------------------------
-- Server version	12.0.2-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*M!100616 SET @OLD_NOTE_VERBOSITY=@@NOTE_VERBOSITY, NOTE_VERBOSITY=0 */;

--
-- Table structure for table `jela`
--

DROP TABLE IF EXISTS `jela`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `jela` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cena` decimal(10,2) NOT NULL,
  `naziv` varchar(255) NOT NULL,
  `opis` text DEFAULT NULL,
  `restoran_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5xq8gxr8qytpkvy3jy9gdmoed` (`restoran_id`),
  CONSTRAINT `FK5xq8gxr8qytpkvy3jy9gdmoed` FOREIGN KEY (`restoran_id`) REFERENCES `restorani` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jela`
--

LOCK TABLES `jela` WRITE;
/*!40000 ALTER TABLE `jela` DISABLE KEYS */;
set autocommit=0;
INSERT INTO `jela` VALUES
(1,990.00,'Capricciosa','Pizza sa šunkom, pečurkama, sirom i pelatom',1),
(2,850.00,'Kung Pao piletina','Piletina sa kikirikijem i povrćem u kineskom sosu',2),
(3,700.00,'Pasulj sa kobasicom','Tradicionalni srpski pasulj sa dimljenom kobasicom',3);
/*!40000 ALTER TABLE `jela` ENABLE KEYS */;
UNLOCK TABLES;
commit;

--
-- Table structure for table `korisnici`
--

DROP TABLE IF EXISTS `korisnici`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `korisnici` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `adresa` varchar(255) NOT NULL,
  `ime` varchar(255) NOT NULL,
  `kontakt` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `prezime` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_fnhlgpdv9nwggcetnb4hadcdi` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `korisnici`
--

LOCK TABLES `korisnici` WRITE;
/*!40000 ALTER TABLE `korisnici` DISABLE KEYS */;
set autocommit=0;
INSERT INTO `korisnici` VALUES
(1,'Dunavska 45','Marko','0611234567','sifra123','Markovic','marko123'),
(2,'Jevrejska 10','Ivana','0627654321','tajna123','Ivic','ivana456');
/*!40000 ALTER TABLE `korisnici` ENABLE KEYS */;
UNLOCK TABLES;
commit;

--
-- Table structure for table `porudzbine`
--

DROP TABLE IF EXISTS `porudzbine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `porudzbine` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `status` varchar(255) NOT NULL,
  `ukupan_iznos` decimal(10,2) NOT NULL,
  `vreme_kreiranja` datetime(6) NOT NULL,
  `korisnik_id` bigint(20) NOT NULL,
  `restoran_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKqlg9tjd3ilfperaao9ewuujf1` (`korisnik_id`),
  KEY `FK768rdsftv19v3d4o9n6hymkxq` (`restoran_id`),
  CONSTRAINT `FK768rdsftv19v3d4o9n6hymkxq` FOREIGN KEY (`restoran_id`) REFERENCES `restorani` (`id`),
  CONSTRAINT `FKqlg9tjd3ilfperaao9ewuujf1` FOREIGN KEY (`korisnik_id`) REFERENCES `korisnici` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `porudzbine`
--

LOCK TABLES `porudzbine` WRITE;
/*!40000 ALTER TABLE `porudzbine` DISABLE KEYS */;
set autocommit=0;
INSERT INTO `porudzbine` VALUES
(1,'Kreirana',990.00,'2025-09-25 08:35:00.000000',1,1),
(2,'U toku',850.00,'2025-09-25 09:00:00.000000',2,2);
/*!40000 ALTER TABLE `porudzbine` ENABLE KEYS */;
UNLOCK TABLES;
commit;

--
-- Table structure for table `restorani`
--

DROP TABLE IF EXISTS `restorani`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `restorani` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `adresa` varchar(255) NOT NULL,
  `naziv` varchar(255) NOT NULL,
  `tip_kuhinje` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `restorani`
--

LOCK TABLES `restorani` WRITE;
/*!40000 ALTER TABLE `restorani` DISABLE KEYS */;
set autocommit=0;
INSERT INTO `restorani` VALUES
(1,'Bulevar Oslobodjenja 1','Restoran Bella','Italijanska'),
(2,'Kralja Petra 12','Kineski Zmaj','Kineska'),
(3,'Trg Republike 5','Domaća Kafana','Srpska');
/*!40000 ALTER TABLE `restorani` ENABLE KEYS */;
UNLOCK TABLES;
commit;

--
-- Table structure for table `stavke_porudzbine`
--

DROP TABLE IF EXISTS `stavke_porudzbine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `stavke_porudzbine` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `kolicina` int(11) NOT NULL,
  `jelo_id` bigint(20) NOT NULL,
  `porudzbina_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKp7b01ctusnqhsdr2e60t84af3` (`jelo_id`),
  KEY `FK1wt6l6ntox4u6ix9pgyxrslsk` (`porudzbina_id`),
  CONSTRAINT `FK1wt6l6ntox4u6ix9pgyxrslsk` FOREIGN KEY (`porudzbina_id`) REFERENCES `porudzbine` (`id`),
  CONSTRAINT `FKp7b01ctusnqhsdr2e60t84af3` FOREIGN KEY (`jelo_id`) REFERENCES `jela` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stavke_porudzbine`
--

LOCK TABLES `stavke_porudzbine` WRITE;
/*!40000 ALTER TABLE `stavke_porudzbine` DISABLE KEYS */;
set autocommit=0;
INSERT INTO `stavke_porudzbine` VALUES
(1,1,1,1),
(2,2,2,2);
/*!40000 ALTER TABLE `stavke_porudzbine` ENABLE KEYS */;
UNLOCK TABLES;
commit;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*M!100616 SET NOTE_VERBOSITY=@OLD_NOTE_VERBOSITY */;

-- Dump completed on 2025-09-25  9:13:58
