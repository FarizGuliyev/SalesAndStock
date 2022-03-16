-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 16, 2022 at 08:14 PM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 8.1.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `salesandstock`
--

-- --------------------------------------------------------

--
-- Table structure for table `accounts`
--

CREATE TABLE `accounts` (
  `Id` int(11) NOT NULL,
  `PermitId` int(11) DEFAULT NULL,
  `WorkerId` int(11) DEFAULT NULL,
  `Passvord` varchar(50) COLLATE utf8_turkish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

--
-- Dumping data for table `accounts`
--

INSERT INTO `accounts` (`Id`, `PermitId`, `WorkerId`, `Passvord`) VALUES
(1, 1, 1, '1234'),
(2, 3, 4, '12345'),
(3, 3, 3, '1234'),
(4, 4, 2, '1234');

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `Id` int(11) NOT NULL,
  `Name` varchar(50) COLLATE utf8_turkish_ci DEFAULT NULL,
  `ParentId` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`Id`, `Name`, `ParentId`) VALUES
(1, 'Aksesuar', 0),
(2, 'Geyimlər', 0),
(3, 'Elektronika', 0),
(4, 'Telefonlar', 0),
(5, 'Kompüterlər', 0),
(6, 'Planşetlər', 0),
(7, 'Kişi Geyimləri', 2),
(8, 'Qadın Geyimləri', 2),
(9, 'Məişət Avadanlıqları', 3),
(10, 'İphone', 4),
(11, 'Samsung Planset', 6);

-- --------------------------------------------------------

--
-- Table structure for table `city`
--

CREATE TABLE `city` (
  `Id` int(11) NOT NULL,
  `Name` varchar(255) COLLATE utf8_turkish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

--
-- Dumping data for table `city`
--

INSERT INTO `city` (`Id`, `Name`) VALUES
(1, 'Bakı'),
(2, 'Sumqayıt'),
(3, 'Gəncə'),
(4, 'Yevlax'),
(5, 'Mingəçevir'),
(6, 'Quba'),
(7, 'Şəki'),
(8, 'Lənkəran');

-- --------------------------------------------------------

--
-- Table structure for table `costumer`
--

CREATE TABLE `costumer` (
  `Id` int(11) NOT NULL,
  `NameSurname` varchar(50) COLLATE utf8_turkish_ci DEFAULT NULL,
  `Telephone` varchar(50) COLLATE utf8_turkish_ci DEFAULT NULL,
  `Adress` varchar(100) COLLATE utf8_turkish_ci DEFAULT NULL,
  `CityId` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

--
-- Dumping data for table `costumer`
--

INSERT INTO `costumer` (`Id`, `NameSurname`, `Telephone`, `Adress`, `CityId`) VALUES
(1, 'İntiqam Muxtarov', '0700000000', 'Xəzər ray, Binə qəs', 1),
(2, 'Əfqan Məlikzadə', '709999999', 'Quba şəh. Nügədi', 6),
(3, 'Test Testov', '0706666666', 'x ray z qes', 5);

-- --------------------------------------------------------

--
-- Table structure for table `permit`
--

CREATE TABLE `permit` (
  `Id` int(11) NOT NULL,
  `Name` varchar(50) COLLATE utf8_turkish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

--
-- Dumping data for table `permit`
--

INSERT INTO `permit` (`Id`, `Name`) VALUES
(1, 'CEO'),
(2, 'Aparıcı Direktor'),
(3, 'Şöbə müdiri'),
(4, 'Satıcı oğlan'),
(5, 'Kassir');

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `Id` int(11) NOT NULL,
  `Name` varchar(50) COLLATE utf8_turkish_ci DEFAULT NULL,
  `CategoryId` int(11) DEFAULT NULL,
  `DateDay` date DEFAULT NULL,
  `Price` decimal(10,0) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`Id`, `Name`, `CategoryId`, `DateDay`, `Price`) VALUES
(1, 'Qolbaq', 1, '2022-03-10', '30'),
(2, 'Sep', 1, '2022-03-10', '40'),
(3, 'Ayaqqabı', 2, '2022-03-11', '56'),
(4, 'T-shirt', 2, '2022-03-11', '25'),
(5, 'Krasovka', 2, '2022-03-11', '39'),
(6, 'Paltaryuyan', 3, '2022-03-12', '39'),
(7, 'İPhone', 4, '2022-03-13', '2000'),
(8, 'Macbook Air', 5, '2022-03-13', '3500'),
(9, 'İpad', 6, '2022-03-14', '3000'),
(10, 'Samsung Note11', 11, '2022-03-16', '1800');

-- --------------------------------------------------------

--
-- Table structure for table `sales`
--

CREATE TABLE `sales` (
  `Id` int(11) NOT NULL,
  `ProductId` int(11) DEFAULT NULL,
  `CostumerId` int(11) DEFAULT NULL,
  `DateDay` date DEFAULT NULL,
  `Amount` int(11) DEFAULT NULL,
  `WorkerId` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

--
-- Dumping data for table `sales`
--

INSERT INTO `sales` (`Id`, `ProductId`, `CostumerId`, `DateDay`, `Amount`, `WorkerId`) VALUES
(1, 1, 1, '2022-03-14', 10, 1),
(2, 1, 2, '2022-03-16', 50, 1);

-- --------------------------------------------------------

--
-- Table structure for table `stock`
--

CREATE TABLE `stock` (
  `Id` int(11) NOT NULL,
  `ProductId` int(11) DEFAULT NULL,
  `WorkerId` int(11) DEFAULT NULL,
  `DateDay` date DEFAULT NULL,
  `Amount` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

--
-- Dumping data for table `stock`
--

INSERT INTO `stock` (`Id`, `ProductId`, `WorkerId`, `DateDay`, `Amount`) VALUES
(1, 1, 1, '2022-03-14', -10),
(2, 1, 1, '2022-03-13', 80),
(3, 9, 1, '2022-03-14', 50),
(4, 1, 1, '2022-03-10', 100),
(5, 1, 1, '2022-03-16', -50);

-- --------------------------------------------------------

--
-- Table structure for table `worker`
--

CREATE TABLE `worker` (
  `Id` int(11) NOT NULL,
  `NameSurname` varchar(50) COLLATE utf8_turkish_ci DEFAULT NULL,
  `Email` varchar(50) COLLATE utf8_turkish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

--
-- Dumping data for table `worker`
--

INSERT INTO `worker` (`Id`, `NameSurname`, `Email`) VALUES
(1, 'Fariz Quliyev', 'fariz.quliyevvv@gmail.com'),
(2, 'Nərgiz İsmayılova', 'mvmvmm@mail.ru'),
(3, 'Rasim İsazadə', 'rasim55@gmail.com'),
(4, 'Əli Əliyev', 'reümm@gmail.ru'),
(5, 'Emil Hümbətov', 'emilhumbetov@gmail.com');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `accounts`
--
ALTER TABLE `accounts`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `city`
--
ALTER TABLE `city`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `costumer`
--
ALTER TABLE `costumer`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `permit`
--
ALTER TABLE `permit`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `sales`
--
ALTER TABLE `sales`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `stock`
--
ALTER TABLE `stock`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `worker`
--
ALTER TABLE `worker`
  ADD PRIMARY KEY (`Id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `accounts`
--
ALTER TABLE `accounts`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `city`
--
ALTER TABLE `city`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `costumer`
--
ALTER TABLE `costumer`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `permit`
--
ALTER TABLE `permit`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `sales`
--
ALTER TABLE `sales`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `stock`
--
ALTER TABLE `stock`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `worker`
--
ALTER TABLE `worker`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
