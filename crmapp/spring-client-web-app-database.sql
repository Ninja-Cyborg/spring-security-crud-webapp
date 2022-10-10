CREATE DATABASE  IF NOT EXISTS `web_client_app` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `web_client_app`;

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

DROP TABLE IF EXISTS `client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `client` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

LOCK TABLES `client` WRITE;
/*!40000 ALTER TABLE `client` DISABLE KEYS */;

INSERT INTO `client` VALUES 
	(1,'David','Adams','david@luv2code.com'),
	(2,'John','Doe','john@luv2code.com'),
	(3,'Ajay','Rao','ajay@luv2code.com'),
	(4,'Mary','Public','mary@luv2code.com'),
	(5,'Maxwell','Dixon','max@luv2code.com');

insert into client (id, first_name, last_name, email) values (1, 'Meryl', 'Verring', 'mverring0@youku.com');
insert into client (id, first_name, last_name, email) values (2, 'Mervin', 'Brompton', 'mbrompton1@gizmodo.com');
insert into client (id, first_name, last_name, email) values (3, 'Rochette', 'Stelfox', 'rstelfox2@salon.com');
insert into client (id, first_name, last_name, email) values (4, 'Anabel', 'Kinze', 'akinze3@angelfire.com');
insert into client (id, first_name, last_name, email) values (5, 'Domeniga', 'Dalziel', 'ddalziel4@npr.org');
insert into client (id, first_name, last_name, email) values (6, 'Cordell', 'Gravatt', 'cgravatt5@google.es');
insert into client (id, first_name, last_name, email) values (7, 'Miguelita', 'Coskerry', 'mcoskerry6@quantcast.com');
insert into client (id, first_name, last_name, email) values (8, 'Hilary', 'Chinn', 'hchinn7@nydailynews.com');
insert into client (id, first_name, last_name, email) values (9, 'Hedda', 'Warsop', 'hwarsop8@cnbc.com');
insert into client (id, first_name, last_name, email) values (10, 'Shaylyn', 'Proudman', 'sproudman9@washingtonpost.com');

/*!40000 ALTER TABLE `client` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

