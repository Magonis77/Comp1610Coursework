-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 01, 2022 at 01:22 PM
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
-- Database: `coursework`
--

-- --------------------------------------------------------

--
-- Table structure for table `department`
--

CREATE TABLE `department` (
  `ID` int(10) NOT NULL,
  `Department` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `department`
--

INSERT INTO `department` (`ID`, `Department`) VALUES
(1, 'Engineering\r\n'),
(2, 'Plumbing'),
(3, 'Roofing\r\n'),
(4, 'Carpentry'),
(5, 'Bricklaying\r\n'),
(6, 'Office');

-- --------------------------------------------------------

--
-- Table structure for table `drole`
--

CREATE TABLE `drole` (
  `ID` int(10) NOT NULL,
  `Role` varchar(55) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `drole`
--

INSERT INTO `drole` (`ID`, `Role`) VALUES
(1, 'Head'),
(2, 'Deputy Head'),
(3, 'Manager'),
(4, 'Apprentice'),
(5, 'Junior member'),
(6, 'Senior member');

-- --------------------------------------------------------

--
-- Table structure for table `holidays`
--

CREATE TABLE `holidays` (
  `ID` int(10) NOT NULL,
  `Start Date` date NOT NULL,
  `End Date` date NOT NULL,
  `Lenght` int(55) NOT NULL,
  `Status` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `holidays`
--

INSERT INTO `holidays` (`ID`, `Start Date`, `End Date`, `Lenght`, `Status`) VALUES
(1, '2022-03-21', '2022-03-24', 4, 'Accepted'),
(2, '2022-03-28', '2022-03-31', 4, 'Rejected');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `ID` int(10) NOT NULL,
  `Username` varchar(255) NOT NULL,
  `Password` varchar(255) NOT NULL,
  `firstname` varchar(255) NOT NULL,
  `lastname` varchar(255) NOT NULL,
  `Gender` varchar(255) NOT NULL,
  `JoinDate` date NOT NULL,
  `Role_ID` int(11) NOT NULL,
  `Department_ID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`ID`, `Username`, `Password`, `firstname`, `lastname`, `Gender`, `JoinDate`, `Role_ID`, `Department_ID`) VALUES
(1, 'Dan', '123', 'Daniels', 'Magiii', 'Male', '2021-12-07', 1, 1),
(20, 'Anna', '123', 'Annaa', 'Sokolova', 'Male', '2022-03-16', 1, 1),
(21, 'Eric123', '12345', 'Eric', 'Shokov', 'Male', '2022-03-07', 1, 1),
(49, 'Arnold11', '123', 'Arnold', 'Weitman', 'Male', '2022-03-29', 4, 5);

-- --------------------------------------------------------

--
-- Table structure for table `user_holidays`
--

CREATE TABLE `user_holidays` (
  `Users_ID` int(10) NOT NULL,
  `Holidays_ID` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user_holidays`
--

INSERT INTO `user_holidays` (`Users_ID`, `Holidays_ID`) VALUES
(1, 1),
(20, 2);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `department`
--
ALTER TABLE `department`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `drole`
--
ALTER TABLE `drole`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `holidays`
--
ALTER TABLE `holidays`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `Role_ID` (`Role_ID`,`Department_ID`),
  ADD KEY `Department_ID` (`Department_ID`);

--
-- Indexes for table `user_holidays`
--
ALTER TABLE `user_holidays`
  ADD KEY `UserID` (`Users_ID`,`Holidays_ID`),
  ADD KEY `HolidayID` (`Holidays_ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `department`
--
ALTER TABLE `department`
  MODIFY `ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `drole`
--
ALTER TABLE `drole`
  MODIFY `ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `holidays`
--
ALTER TABLE `holidays`
  MODIFY `ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=50;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `user_ibfk_1` FOREIGN KEY (`Role_ID`) REFERENCES `drole` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `user_ibfk_2` FOREIGN KEY (`Department_ID`) REFERENCES `department` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `user_holidays`
--
ALTER TABLE `user_holidays`
  ADD CONSTRAINT `user_holidays_ibfk_1` FOREIGN KEY (`Users_ID`) REFERENCES `user` (`ID`) ON DELETE CASCADE ON UPDATE NO ACTION,
  ADD CONSTRAINT `user_holidays_ibfk_2` FOREIGN KEY (`Holidays_ID`) REFERENCES `holidays` (`ID`) ON DELETE CASCADE ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
