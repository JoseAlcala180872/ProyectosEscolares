CREATE DATABASE  IF NOT EXISTS `tienda` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `tienda`;
-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: tienda
-- ------------------------------------------------------
-- Server version	8.0.34

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
-- Table structure for table `categorias`
--

DROP TABLE IF EXISTS `categorias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categorias` (
  `ID_Categoria` int NOT NULL,
  `Descripcion` varchar(255) DEFAULT NULL,
  `Nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID_Categoria`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categorias`
--

LOCK TABLES `categorias` WRITE;
/*!40000 ALTER TABLE `categorias` DISABLE KEYS */;
INSERT INTO `categorias` VALUES (1,'Productos relacionados con el equipo Mercedes','Mercedes'),(2,'Productos relacionados con el equipo Red Bull Racing','Red Bull Racing'),(3,'Productos relacionados con el equipo Ferrari','Ferrari'),(4,'Productos relacionados con el equipo McLaren','McLaren'),(5,'Productos relacionados con el equipo Racing Point','Racing Point'),(6,'Productos relacionados con la marca Sparco','Sparco'),(7,'Mochilas de equipos de Fórmula 1','Mochilas'),(8,'Paraguas oficiales de la Fórmula 1','Paraguas');
/*!40000 ALTER TABLE `categorias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clientes`
--

DROP TABLE IF EXISTS `clientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clientes` (
  `ID_Cliente` int NOT NULL AUTO_INCREMENT,
  `Nombre_ApellidoP_ApellidoM` varchar(255) DEFAULT NULL,
  `CorreoElectronico` varchar(255) DEFAULT NULL,
  `Contraseña` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID_Cliente`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientes`
--

LOCK TABLES `clientes` WRITE;
/*!40000 ALTER TABLE `clientes` DISABLE KEYS */;
INSERT INTO `clientes` VALUES (1,'carlos','carlos@gmail.com','1324'),(3,'yalam','yalam@gmail.com','1234'),(5,'peña','peña@gmail.com','1234'),(6,'carolina','carolina@gmail.com','elkiler123');
/*!40000 ALTER TABLE `clientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `compras`
--

DROP TABLE IF EXISTS `compras`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `compras` (
  `CompraID` int NOT NULL AUTO_INCREMENT,
  `Fecha_HORA` datetime DEFAULT NULL,
  `ID_Cliente` int DEFAULT NULL,
  `ID_Carrito` int NOT NULL,
  `montoTotal` decimal(10,0) DEFAULT NULL,
  PRIMARY KEY (`CompraID`),
  KEY `ID_Cliente` (`ID_Cliente`),
  KEY `idCarrito_idx` (`ID_Carrito`),
  CONSTRAINT `compras_ibfk_1` FOREIGN KEY (`ID_Cliente`) REFERENCES `clientes` (`ID_Cliente`),
  CONSTRAINT `idCarrito` FOREIGN KEY (`ID_Carrito`) REFERENCES `tienecarrito` (`ID_Carrito`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `compras`
--

LOCK TABLES `compras` WRITE;
/*!40000 ALTER TABLE `compras` DISABLE KEYS */;
/*!40000 ALTER TABLE `compras` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productos`
--

DROP TABLE IF EXISTS `productos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `productos` (
  `ID_Producto` int NOT NULL AUTO_INCREMENT,
  `Precio` decimal(10,2) DEFAULT NULL,
  `Descripcion` varchar(255) DEFAULT NULL,
  `NombreProducto` varchar(255) DEFAULT NULL,
  `ID_Categoria` int DEFAULT NULL,
  PRIMARY KEY (`ID_Producto`),
  KEY `ID_Categoria` (`ID_Categoria`),
  CONSTRAINT `productos_ibfk_1` FOREIGN KEY (`ID_Categoria`) REFERENCES `categorias` (`ID_Categoria`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productos`
--

LOCK TABLES `productos` WRITE;
/*!40000 ALTER TABLE `productos` DISABLE KEYS */;
INSERT INTO `productos` VALUES (1,39.99,'Camiseta oficial del equipo Mercedes','Camiseta Mercedes',1),(2,89.99,'Gorra Red Bull Racing con logotipo','Gorra Red Bull Racing',2),(3,249.99,'Miniatura del coche Ferrari SF21','Miniatura Ferrari SF21',3),(4,149.99,'Llavero oficial del equipo McLaren','Llavero McLaren',4),(5,69.99,'Polo Racing Point F1 Team','Polo Racing Point',1),(6,29.99,'Bufanda oficial de la Fórmula 1','Bufanda Fórmula 1',5),(7,199.99,'Casco de seguridad Sparco','Casco Sparco',6),(8,49.99,'Mochila Red Bull Racing','Mochila Red Bull Racing',7),(9,169.99,'Paraguas oficial de la Fórmula 1','Paraguas Fórmula 1',8),(10,199.99,'Chaqueta Mercedes-AMG Petronas','Chaqueta Mercedes',1);
/*!40000 ALTER TABLE `productos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tienecarrito`
--

DROP TABLE IF EXISTS `tienecarrito`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tienecarrito` (
  `ID_Carrito` int NOT NULL AUTO_INCREMENT,
  `ID_Producto` int DEFAULT NULL,
  `cantidadProductos` int NOT NULL,
  `importe` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`ID_Carrito`),
  KEY `ID_Producto` (`ID_Producto`),
  CONSTRAINT `tienecarrito_ibfk_2` FOREIGN KEY (`ID_Producto`) REFERENCES `productos` (`ID_Producto`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tienecarrito`
--

LOCK TABLES `tienecarrito` WRITE;
/*!40000 ALTER TABLE `tienecarrito` DISABLE KEYS */;
INSERT INTO `tienecarrito` VALUES (1,2,3,NULL),(2,2,3,NULL),(3,2,2,180.00),(4,3,2,500.00),(5,3,2,500.00),(6,2,3,269.97);
/*!40000 ALTER TABLE `tienecarrito` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-09-24  2:51:48
