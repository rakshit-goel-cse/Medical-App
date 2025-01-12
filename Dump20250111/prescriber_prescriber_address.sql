-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: 127.0.10.11    Database: prescriber
-- ------------------------------------------------------
-- Server version	8.0.35

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `prescriber_address`
--

DROP TABLE IF EXISTS `prescriber_address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prescriber_address` (
  `id` int NOT NULL AUTO_INCREMENT,
  `add_line1` varchar(20) NOT NULL,
  `add_line2` varchar(10) DEFAULT NULL,
  `city` varchar(10) NOT NULL,
  `state` varchar(10) NOT NULL,
  `zip_code` int NOT NULL,
  `is_active` enum('Y','N') NOT NULL DEFAULT 'N',
  `prescriber_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_address_id` (`prescriber_id`),
  CONSTRAINT `fk_address_id` FOREIGN KEY (`prescriber_id`) REFERENCES `prescriber` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prescriber_address`
--

LOCK TABLES `prescriber_address` WRITE;
/*!40000 ALTER TABLE `prescriber_address` DISABLE KEYS */;
INSERT INTO `prescriber_address` VALUES (1,'pres1Add1','pres1Add2','MyC1','CA',21,'Y',15),(2,'pres1Add3','pres1Add1','MyC2','YA',22,'Y',15),(3,'pres1Add1','pres1Add2','MyC1','CA',21,'Y',16),(4,'pres1Add3','pres1Add1','MyC2','YA',22,'Y',16),(5,'pres1Add1','pres1Add2','MyC1','CA',21,'Y',17),(6,'pres1Add3','pres1Add1','MyC2','YA',22,'Y',17),(7,'pres1Add1','pres1Add2','MyC1','CA',21,'Y',18),(8,'pres1Add3','pres1Add1','MyC2','YA',22,'Y',18),(9,'pres1Add1','pres1Add2','MyC1','CA',21,'Y',19),(10,'pres1Add3','pres1Add1','MyC2','YA',22,'Y',19);
/*!40000 ALTER TABLE `prescriber_address` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-01-11  9:42:05
