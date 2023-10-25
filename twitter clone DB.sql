-- --------------------------------------------------------
-- Host:                         178.128.24.68
-- Server version:               10.3.36-MariaDB - MariaDB Server
-- Server OS:                    Linux
-- HeidiSQL Version:             12.1.0.6537
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- Dumping structure for table sec_slt_kss_mw_dev.systemuser
CREATE TABLE IF NOT EXISTS `systemuser` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `USERID` varchar(128) NOT NULL,
  `PASSWORD` varchar(1024) DEFAULT NULL,
  `FIRSTNAME` varchar(64) DEFAULT NULL,
  `LASTNAME` varchar(64) DEFAULT NULL,
  `JWT` varchar(512) DEFAULT NULL,
  `PROFILE_PIC` text DEFAULT NULL,
  `BIO` text DEFAULT NULL,
  `FOLLOWERS` int(11) NOT NULL DEFAULT 0,
  `TWEETS` int(11) NOT NULL DEFAULT 0,
  `STATUS` int(11) NOT NULL,
  `CREATEDDATETIME` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `LASTUPDATEDDATETIME` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `CREATEDUSER` varchar(64) NOT NULL,
  `LASTUPDATEDUSER` varchar(64) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

-- Data exporting was unselected.

-- Dumping structure for table sec_slt_kss_mw_dev.tweet
CREATE TABLE IF NOT EXISTS `tweet` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `USER_ID` int(11) NOT NULL DEFAULT 0,
  `DESCRIPTION` varchar(300) NOT NULL DEFAULT '0',
  `CREATEDDATETIME` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- Data exporting was unselected.

-- Dumping structure for table sec_slt_kss_mw_dev.tweet_comment
CREATE TABLE IF NOT EXISTS `tweet_comment` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `TWEET_ID` int(11) NOT NULL DEFAULT 0,
  `USER_ID` int(11) NOT NULL DEFAULT 0,
  `DESCRIPTION` varchar(50) NOT NULL DEFAULT '0',
  `CREATEDDATETIME` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- Data exporting was unselected.

-- Dumping structure for table sec_slt_kss_mw_dev.tweet_like
CREATE TABLE IF NOT EXISTS `tweet_like` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `TWEET_ID` int(11) NOT NULL,
  `USER_ID` int(11) NOT NULL,
  `CREATEDDATETIME` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- Data exporting was unselected.

-- Dumping structure for table sec_slt_kss_mw_dev.tweet_share
CREATE TABLE IF NOT EXISTS `tweet_share` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `USER_ID` int(11) NOT NULL DEFAULT 0,
  `TWEET_ID` int(11) NOT NULL DEFAULT 0,
  `CREATEDDATETIME` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- Data exporting was unselected.

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
