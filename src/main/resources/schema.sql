CREATE TABLE `animal_tabla` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) DEFAULT NULL,
  `especie` varchar(100) DEFAULT NULL,
  `edad` int DEFAULT NULL,
  `habitat` varchar(100) DEFAULT NULL,
  `creado` date DEFAULT NULL,
  `modificado` date DEFAULT current_timestamp(),
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
);