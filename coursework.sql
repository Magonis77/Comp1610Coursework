-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 26, 2022 at 07:09 PM
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
  `User_ID` int(11) NOT NULL,
  `Start Date` date NOT NULL,
  `End Date` date NOT NULL,
  `Lenght` int(55) NOT NULL,
  `Overall Length` int(55) NOT NULL,
  `Request Made Date` date NOT NULL,
  `Status` varchar(255) NOT NULL,
  `Peak Time` varchar(255) NOT NULL,
  `Date Decision Made` date DEFAULT NULL,
  `constraints` varchar(255) NOT NULL,
  `contraintbreak` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `holidays`
--

INSERT INTO `holidays` (`ID`, `User_ID`, `Start Date`, `End Date`, `Lenght`, `Overall Length`, `Request Made Date`, `Status`, `Peak Time`, `Date Decision Made`, `constraints`, `contraintbreak`) VALUES
(67, 21, '2022-04-29', '2022-04-30', 1, 0, '2022-04-21', 'Rejected', 'A - Yes', '2022-04-26', '', 0),
(68, 20, '2022-05-10', '2022-05-20', 10, 0, '2022-04-21', 'Rejected', 'B - No', '2022-04-26', '', 0),
(93, 1, '2022-04-25', '2022-04-28', 3, 3, '2022-04-22', 'Accepted', 'B - No', '2022-04-26', '', 0),
(103, 1, '2022-12-14', '2022-12-21', 7, 7, '2022-04-23', 'Accepted', 'B - No', '2022-04-26', '', 0),
(104, 52, '2022-12-16', '2022-12-20', 4, 0, '2022-04-23', 'Outstanding', 'A - Yes', NULL, 'Head of the department is on leave', 1),
(107, 1, '2022-04-27', '2022-05-04', 7, 3, '2022-04-26', 'Outstanding', 'B - No', NULL, 'Head of the department is on leave', 1),
(114, 20, '2022-12-25', '2023-01-02', 8, 0, '2022-04-26', 'Outstanding', 'A - Yes Christmas time', NULL, '', 0),
(115, 55, '2022-05-01', '2022-05-31', 30, 0, '2022-04-26', 'Outstanding', 'B - No', NULL, 'Head of the department is on leave', 1),
(116, 55, '2023-04-10', '2023-05-24', 44, 0, '2022-04-26', 'Outstanding', 'B - No', NULL, 'Requesting more days than entiteled', 1),
(117, 56, '2022-12-24', '2023-01-02', 9, 9, '2022-04-26', 'Accepted', 'A - Yes Christmas time', '2022-04-26', '', 0),
(118, 57, '2022-10-25', '2022-10-28', 3, 3, '2022-04-26', 'Accepted', 'B - No', '2022-04-26', '', 0),
(120, 1, '2022-10-11', '2022-10-14', 3, 10, '2022-04-26', 'Rejected', 'B - No', '2022-04-26', '', 0),
(121, 56, '2022-11-14', '2022-11-16', 2, 9, '2022-04-26', 'Rejected', 'B - No', '2022-04-26', '', 0),
(122, 58, '2022-10-17', '2022-10-21', 4, 4, '2022-04-26', 'Accepted', 'B - No', '2022-04-26', '', 0),
(124, 1, '2022-05-17', '2022-05-19', 2, 10, '2022-04-26', 'Outstanding', 'B - No', NULL, 'Depudy Head of the department is on leave', 1),
(125, 1, '2022-11-22', '2022-11-24', 2, 10, '2022-04-26', 'Rejected', 'B - No', '2022-04-26', '', 0),
(126, 21, '2022-11-28', '2022-11-29', 1, 1, '2022-04-26', 'Accepted', 'B - No', '2022-04-26', '', 0);

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
  `Department_ID` int(11) NOT NULL,
  `Status` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`ID`, `Username`, `Password`, `firstname`, `lastname`, `Gender`, `JoinDate`, `Role_ID`, `Department_ID`, `Status`) VALUES
(1, 'Dan', '123', 'Daniels', 'Magonis', 'Male', '2021-12-07', 1, 6, 'Working'),
(20, 'Anna', '123', 'Annaa', 'Sokolova', 'Male', '2022-03-16', 2, 6, 'Working'),
(21, 'Eric123', '12345', 'Eric', 'Shokov', 'Male', '2022-03-07', 4, 6, 'Working'),
(49, 'Arnold11', '123', 'Arnold', 'Weitman', 'Male', '2022-03-29', 4, 5, 'Working'),
(52, 'AndisL', '123', 'Andis', 'Laipins', 'Male', '2022-04-23', 1, 1, 'Working'),
(55, 'Sophie', '123', 'Sophie', 'Sandra', 'Female', '2022-04-23', 3, 6, 'Working'),
(56, 'Sandris12', '123', 'Sandris', 'Andolis', 'Male', '2022-04-23', 3, 6, 'Working'),
(57, 'Sandriss', '123', 'Sandris', 'Antolijs', 'Male', '2022-04-23', 4, 6, 'Working'),
(58, 'Sanatolijs', '123', 'Sanatolijs', 'Liskins', 'Male', '2022-04-23', 5, 6, 'Working'),
(59, 'Anatolijs', '123', 'Anatolijs', 'Antons', 'Male', '2022-04-23', 4, 6, 'Working');

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
  ADD PRIMARY KEY (`ID`),
  ADD KEY `User_ID` (`User_ID`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `Role_ID` (`Role_ID`,`Department_ID`),
  ADD KEY `Department_ID` (`Department_ID`);

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
  MODIFY `ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=127;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=70;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `holidays`
--
ALTER TABLE `holidays`
  ADD CONSTRAINT `holidays_ibfk_1` FOREIGN KEY (`User_ID`) REFERENCES `user` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `user_ibfk_1` FOREIGN KEY (`Role_ID`) REFERENCES `drole` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `user_ibfk_2` FOREIGN KEY (`Department_ID`) REFERENCES `department` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
