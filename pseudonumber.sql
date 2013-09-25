-- phpMyAdmin SQL Dump
-- version 3.4.11.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Sep 22, 2013 at 02:45 AM
-- Server version: 5.5.23
-- PHP Version: 5.3.17

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `ktmlabs_building_pseudonumber`
--

-- --------------------------------------------------------

--
-- Table structure for table `pseudonumber`
--

CREATE TABLE IF NOT EXISTS `pseudonumber` (
  `OSMID` bigint(20) NOT NULL,
  `District` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `VDC` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `Ward` int(11) NOT NULL,
  `bid` int(11) NOT NULL,
  `combined` varchar(50) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
