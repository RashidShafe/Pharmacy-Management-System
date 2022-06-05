-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Sep 15, 2021 at 05:12 PM
-- Server version: 10.4.17-MariaDB
-- PHP Version: 8.0.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `pm`
--

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `phone no` varchar(11) NOT NULL,
  `name` varchar(40) NOT NULL,
  `address` varchar(50) NOT NULL,
  `due` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`phone no`, `name`, `address`, `due`) VALUES
('01732421139', 'Shafe', 'NEUB,Sylhet, BD', 50.5),
('01811111111', 'Marwan', '56 Mitali R/A, ', 100),
('01622222222', 'Maidul', '13/A ABC Road,Sylhet', 951),
('01312345678', 'Rashid', '13 VIP palace,Ambarkhana', 753),
('01398765432', 'Vikki', 'NEUB,Sylhet', 2486),
('01515915915', 'Haque', '13 VIP palace,Ambarkhana', 682.5),
('00000', 'test2', 'test2', 897),
('012369854', 'Haza', 'Sylhet', 654),
('7410', 'Vanish dash', 'abc', 897);

-- --------------------------------------------------------

--
-- Table structure for table `medicine`
--

CREATE TABLE `medicine` (
  `name` varchar(40) NOT NULL,
  `manufacturer` varchar(40) NOT NULL,
  `type` varchar(10) NOT NULL,
  `unit price` float NOT NULL,
  `expire date` varchar(20) DEFAULT NULL,
  `stock` int(11) NOT NULL,
  `position` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `medicine`
--

INSERT INTO `medicine` (`name`, `manufacturer`, `type`, `unit price`, `expire date`, `stock`, `position`) VALUES
('Paracetamol 500mg', 'Albion Laboratories Ltd.', 'Tablet', 1, '2025-03-24', 10, 'shelf-1 row-2 right side'),
('Invega Sustenna 75mg', 'Unimed Ltd.', 'Injection', 9093.18, '2022-02-07', 2, 'Refrigerator'),
('Sergel 20mg', 'Helthcare Ltd.', 'Capsule', 7, '2022-07-07', 3, 'shelf-6 row-2 left side'),
('1', '1', 'Tablet', 1, '2022-02-02', 20, 'S3'),
('xyz', 'xy', 'Drops', 11, '2022-20-25', 41, 'asdfsg');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `phone` varchar(20) NOT NULL,
  `name` varchar(40) NOT NULL,
  `address` varchar(50) NOT NULL,
  `user name` varchar(20) NOT NULL,
  `password` varchar(15) NOT NULL,
  `value` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`phone`, `name`, `address`, `user name`, `password`, `value`) VALUES
('01732421139', 'Shafe', 'NEUB,Sylhet,BD', 'admin', 'admin', 1),
('01777777777', 'user', 'Sylhet', 'user', 'user', 0),
('1', 'admin', 'xyz', '1', '1', 1),
('0', 'emplye', 'abcd', '0', '0', 0);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
