-- --------------------------------------------------------
-- Host:                         10.135.15.81
-- Server version:               5.5.39-log - MySQL Community Server (GPL)
-- Server OS:                    Win32
-- HeidiSQL Version:             8.3.0.4694
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping database structure for springsecurity
CREATE DATABASE IF NOT EXISTS `springsecurity` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `springsecurity`;


-- Dumping structure for table springsecurity.app_user
CREATE TABLE IF NOT EXISTS `app_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sso_id` varchar(30) NOT NULL,
  `password` varchar(100) NOT NULL,
  `first_name` varchar(30) NOT NULL,
  `last_name` varchar(30) NOT NULL,
  `email` varchar(30) NOT NULL,
  `sign_in_provider` varchar(20) DEFAULT NULL,
  `ENABLED` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `LASTPASSWORDRESETDATE` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`),
  UNIQUE KEY `sso_id` (`sso_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- Dumping data for table springsecurity.app_user: ~1 rows (approximately)
/*!40000 ALTER TABLE `app_user` DISABLE KEYS */;
INSERT INTO `app_user` (`id`, `sso_id`, `password`, `first_name`, `last_name`, `email`, `sign_in_provider`, `ENABLED`, `LASTPASSWORDRESETDATE`) VALUES
	(1, 'sam', '$2a$10$4eqIF5s/ewJwHK1p8lqlFOEm2QIA0S8g6./Lok.pQxqcxaBZYChRm', 'Sam', 'Smith', 'samy@xyz.com', NULL, 1, '0000-00-00 00:00:00');
/*!40000 ALTER TABLE `app_user` ENABLE KEYS */;


-- Dumping structure for table springsecurity.app_user_user_profile
CREATE TABLE IF NOT EXISTS `app_user_user_profile` (
  `user_id` bigint(20) NOT NULL,
  `user_profile_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`,`user_profile_id`),
  KEY `FK_USER_PROFILE` (`user_profile_id`),
  CONSTRAINT `FK_APP_USER` FOREIGN KEY (`user_id`) REFERENCES `app_user` (`id`),
  CONSTRAINT `FK_USER_PROFILE` FOREIGN KEY (`user_profile_id`) REFERENCES `user_profile` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table springsecurity.app_user_user_profile: ~1 rows (approximately)
/*!40000 ALTER TABLE `app_user_user_profile` DISABLE KEYS */;
INSERT INTO `app_user_user_profile` (`user_id`, `user_profile_id`) VALUES
	(1, 2);
/*!40000 ALTER TABLE `app_user_user_profile` ENABLE KEYS */;


-- Dumping structure for table springsecurity.persistent_logins
CREATE TABLE IF NOT EXISTS `persistent_logins` (
  `username` varchar(64) NOT NULL,
  `series` varchar(64) NOT NULL,
  `token` varchar(64) NOT NULL,
  `last_used` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`series`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table springsecurity.persistent_logins: ~0 rows (approximately)
/*!40000 ALTER TABLE `persistent_logins` DISABLE KEYS */;
/*!40000 ALTER TABLE `persistent_logins` ENABLE KEYS */;


-- Dumping structure for table springsecurity.user_profile
CREATE TABLE IF NOT EXISTS `user_profile` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` varchar(30) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `type` (`type`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- Dumping data for table springsecurity.user_profile: ~3 rows (approximately)
/*!40000 ALTER TABLE `user_profile` DISABLE KEYS */;
INSERT INTO `user_profile` (`id`, `type`) VALUES
	(2, 'ADMIN'),
	(3, 'DBA'),
	(1, 'USER');
/*!40000 ALTER TABLE `user_profile` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
