-- phpMyAdmin SQL Dump
-- version 4.8.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 25, 2024 at 05:25 PM
-- Server version: 10.1.34-MariaDB
-- PHP Version: 5.6.37

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `somras`
--

-- --------------------------------------------------------

--
-- Table structure for table `s_customer`
--

CREATE TABLE `s_customer` (
  `c_id` int(5) NOT NULL,
  `c_name` varchar(30) NOT NULL,
  `c_addr` varchar(300) NOT NULL,
  `c_city` varchar(50) NOT NULL,
  `c_pass` varchar(20) NOT NULL,
  `c_email` varchar(50) NOT NULL,
  `c_mob` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `s_customer`
--

INSERT INTO `s_customer` (`c_id`, `c_name`, `c_addr`, `c_city`, `c_pass`, `c_email`, `c_mob`) VALUES
(1, 'kunal', 'laxminagar garbi chowk', 'porbandar', 'kk1234', 'katirakunal16@gmail.com', '9664614937'),
(4, 'lathu', 'gidc', 'pbr', 'lathu', 'abhi@gmail.com', '8238047216'),
(5, 'kashyap', 'rokadiya', 'pbr', 'kash', 'kashyapdinesh2383@gmail.com', '9913907777'),
(6, 'mehul', 'wagheswari', 'pbr', 'mehul', 'mehulgohil848@gmail.com', '8488875366');

-- --------------------------------------------------------

--
-- Table structure for table `s_order`
--

CREATE TABLE `s_order` (
  `o_id` int(5) NOT NULL,
  `c_id` int(5) NOT NULL,
  `o_date` varchar(20) NOT NULL,
  `o_add` varchar(300) NOT NULL,
  `o_grandtotal` varchar(15) NOT NULL,
  `o_sellstat` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `s_order`
--

INSERT INTO `s_order` (`o_id`, `c_id`, `o_date`, `o_add`, `o_grandtotal`, `o_sellstat`) VALUES
(1, 1, '03-02-24242424', 'abhishek', '3000', 'false'),
(2, 1, '03-02-24242424', 'kashyap', '3000', 'false'),
(3, 1, '03-02-24', 'mehul', '3000', 'false'),
(4, 1, '03-02-24', 'sknsakjksajf', '2020', 'false'),
(5, 1, '03-02-24', 'sknsakjksajf', '2020', 'false'),
(6, 1, '03-02-24', 'laxminagar garbi chowk', '600', 'false'),
(7, 1, '03-02-24', 'laxminagar garbi chowk', '8900', 'false'),
(8, 1, '03-02-24', 'laxminagar garbi chowk', '5000', 'false'),
(9, 1, '03-02-24', 'laxminagar garbi chowk', '5000', 'false'),
(10, 1, '03-02-24', 'laxminagar garbi chowk', '11000', 'false'),
(11, 1, '05-02-24', 'laxminagar garbi chowk', '10000', 'false'),
(12, 1, '05-02-24', 'GIDC SEETAL PARK 2', '450', 'false'),
(13, 1, '06-02-24', 'laxminagar garbi chowk', '6000', 'false'),
(14, 1, '06-02-24', 'laxminagar garbi chowk', '6000', 'false'),
(15, 1, '06-02-24', 'laxminagar garbi chowk', '10000', 'false'),
(16, 5, '06-02-24', 'rokadiya', '15000', 'false'),
(17, 1, '08-02-24', 'laxminagar garbi chowk', '6000', 'false'),
(18, 5, '08-02-24', 'Gidc', '9000', 'false'),
(19, 1, '08-02-24', 'laxminagar garbi chowk', '9000', 'false'),
(20, 1, '08-02-24', 'laxminagar garbi chowk', '3000', 'false'),
(21, 1, '08-02-24', 'laxminagar garbi chowk', '5000', 'false'),
(22, 1, '08-02-24', 'laxminagar garbi chowk', '5000', 'false'),
(23, 1, '08-02-24', 'laxminagar garbi chowk', '5000', 'false'),
(24, 1, '08-02-24', 'laxminagar garbi chowk', '5000', 'false'),
(25, 1, '08-02-24', 'laxminagar garbi chowk', '10000', 'false'),
(26, 1, '08-02-24', 'laxminagar garbi chowk', '5000', 'false'),
(27, 6, '08-02-24', 'wagheswari', '12000', 'false'),
(28, 1, '20-02-24', 'laxminagar garbi chowk', '10000', 'false'),
(29, 1, '20-02-24', 'laxminagar garbi chowk', '450', 'false'),
(30, 1, '20-02-24', 'laxminagar garbi chowk', '10000', 'false'),
(31, 1, '20-02-24', 'laxminagar garbi chowk', '10000', 'false'),
(32, 1, '20-02-24', 'laxminagar garbi chowk', '5000', 'false'),
(33, 1, '20-02-24', 'laxminagar garbi chowk', '300', 'false'),
(34, 1, '20-02-24', 'laxminagar garbi chowk', '3000', 'false'),
(35, 1, '20-02-24', 'laxminagar garbi chowk', '4750', 'false'),
(36, 1, '25-02-24', 'laxminagar garbi chowk', '6000', 'false');

-- --------------------------------------------------------

--
-- Table structure for table `s_orderdetail`
--

CREATE TABLE `s_orderdetail` (
  `tid` int(5) NOT NULL,
  `o_id` int(5) NOT NULL,
  `p_id` int(5) NOT NULL,
  `o_qty` int(5) NOT NULL,
  `o_rate` int(10) NOT NULL,
  `o_amt` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `s_orderdetail`
--

INSERT INTO `s_orderdetail` (`tid`, `o_id`, `p_id`, `o_qty`, `o_rate`, `o_amt`) VALUES
(1, 1, 1, 1, 3000, 3000),
(2, 2, 1, 1, 3000, 3000),
(3, 3, 1, 1, 3000, 3000),
(4, 6, 4, 4, 150, 600),
(5, 7, 1, 1, 3000, 3000),
(6, 7, 2, 1, 5000, 5000),
(7, 7, 4, 6, 150, 900),
(8, 8, 2, 1, 5000, 5000),
(9, 9, 1, 1, 3000, 3000),
(10, 9, 2, 1, 5000, 5000),
(11, 10, 1, 2, 3000, 6000),
(12, 10, 2, 1, 5000, 5000),
(13, 11, 2, 2, 5000, 10000),
(14, 12, 4, 3, 150, 450),
(15, 13, 1, 2, 3000, 6000),
(16, 14, 1, 2, 3000, 6000),
(17, 15, 2, 2, 5000, 10000),
(18, 16, 2, 3, 5000, 15000),
(19, 17, 1, 2, 3000, 6000),
(20, 18, 1, 3, 3000, 9000),
(21, 19, 1, 3, 3000, 9000),
(22, 20, 1, 1, 3000, 3000),
(23, 21, 2, 1, 5000, 5000),
(24, 22, 2, 1, 5000, 5000),
(25, 23, 2, 1, 5000, 5000),
(26, 24, 2, 1, 5000, 5000),
(27, 25, 2, 2, 5000, 10000),
(28, 26, 2, 1, 5000, 5000),
(29, 27, 1, 2, 3000, 6000),
(30, 27, 1, 2, 3000, 6000),
(31, 28, 2, 2, 5000, 10000),
(32, 29, 4, 3, 150, 450),
(33, 30, 2, 2, 5000, 10000),
(34, 31, 2, 2, 5000, 10000),
(35, 32, 2, 1, 5000, 5000),
(36, 33, 4, 2, 150, 300),
(37, 34, 1, 1, 3000, 3000),
(38, 35, 4, 5, 150, 750),
(39, 35, 3, 1, 4000, 4000),
(40, 36, 1, 1, 3000, 3000),
(41, 36, 1, 1, 3000, 3000);

-- --------------------------------------------------------

--
-- Table structure for table `s_product`
--

CREATE TABLE `s_product` (
  `p_id` int(5) NOT NULL,
  `p_name` varchar(30) NOT NULL,
  `p_rate` varchar(10) NOT NULL,
  `p_unit` varchar(10) NOT NULL,
  `p_img` varchar(30) NOT NULL,
  `p_category` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `s_product`
--

INSERT INTO `s_product` (`p_id`, `p_name`, `p_rate`, `p_unit`, `p_img`, `p_category`) VALUES
(1, 'Dewars', '3000', 'bottle', 'item1', 'whisky'),
(2, 'Chivas', '5000', 'bottle', 'item2', 'whisky'),
(3, 'Jack denials', '4000', 'bottle', 'item3', 'whisky'),
(4, 'Budweiser', '150', 'tin', 'item4', 'beer');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `s_customer`
--
ALTER TABLE `s_customer`
  ADD PRIMARY KEY (`c_id`);

--
-- Indexes for table `s_order`
--
ALTER TABLE `s_order`
  ADD PRIMARY KEY (`o_id`);

--
-- Indexes for table `s_orderdetail`
--
ALTER TABLE `s_orderdetail`
  ADD PRIMARY KEY (`tid`);

--
-- Indexes for table `s_product`
--
ALTER TABLE `s_product`
  ADD PRIMARY KEY (`p_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `s_customer`
--
ALTER TABLE `s_customer`
  MODIFY `c_id` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `s_order`
--
ALTER TABLE `s_order`
  MODIFY `o_id` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=37;

--
-- AUTO_INCREMENT for table `s_orderdetail`
--
ALTER TABLE `s_orderdetail`
  MODIFY `tid` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=42;

--
-- AUTO_INCREMENT for table `s_product`
--
ALTER TABLE `s_product`
  MODIFY `p_id` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
