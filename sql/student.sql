-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 16, 2023 at 04:44 PM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `student`
--

-- --------------------------------------------------------

--
-- Table structure for table `score_calculus`
--

CREATE TABLE `score_calculus` (
  `student_id` varchar(13) NOT NULL,
  `afl_1` float(5,2) DEFAULT NULL,
  `afl_2` float(5,2) DEFAULT NULL,
  `afl_3` float(5,2) DEFAULT NULL,
  `afl_4` float(5,2) DEFAULT NULL,
  `alp` float(5,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `score_calculus`
--

INSERT INTO `score_calculus` (`student_id`, `afl_1`, `afl_2`, `afl_3`, `afl_4`, `alp`) VALUES
('0806022210008', 0.00, 0.00, 0.00, 0.00, 0.00);

-- --------------------------------------------------------

--
-- Table structure for table `score_database`
--

CREATE TABLE `score_database` (
  `student_id` varchar(13) NOT NULL,
  `afl_1` float(5,2) DEFAULT NULL,
  `afl_2` float(5,2) DEFAULT NULL,
  `afl_3` float(5,2) DEFAULT NULL,
  `afl_4` float(5,2) DEFAULT NULL,
  `alp` float(5,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `score_database`
--

INSERT INTO `score_database` (`student_id`, `afl_1`, `afl_2`, `afl_3`, `afl_4`, `alp`) VALUES
('0806022210008', 0.00, 0.00, 0.00, 0.00, 0.00);

-- --------------------------------------------------------

--
-- Table structure for table `score_oop`
--

CREATE TABLE `score_oop` (
  `student_id` varchar(13) NOT NULL,
  `afl_1` float(5,2) DEFAULT NULL,
  `afl_2` float(5,2) DEFAULT NULL,
  `afl_3` float(5,2) DEFAULT NULL,
  `afl_4` float(5,2) DEFAULT NULL,
  `alp` float(5,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `score_oop`
--

INSERT INTO `score_oop` (`student_id`, `afl_1`, `afl_2`, `afl_3`, `afl_4`, `alp`) VALUES
('0806022210008', 0.00, 0.00, 0.00, 0.00, 0.00);

-- --------------------------------------------------------

--
-- Table structure for table `score_web`
--

CREATE TABLE `score_web` (
  `student_id` varchar(13) NOT NULL,
  `afl_1` float(5,2) DEFAULT NULL,
  `afl_2` float(5,2) DEFAULT NULL,
  `afl_3` float(5,2) DEFAULT NULL,
  `afl_4` float(5,2) DEFAULT NULL,
  `alp` float(5,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `score_web`
--

INSERT INTO `score_web` (`student_id`, `afl_1`, `afl_2`, `afl_3`, `afl_4`, `alp`) VALUES
('0806022210008', 0.00, 0.00, 0.00, 0.00, 0.00);

-- --------------------------------------------------------

--
-- Table structure for table `student_data`
--

CREATE TABLE `student_data` (
  `student_id` varchar(13) NOT NULL,
  `name` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `major` varchar(100) NOT NULL,
  `semester` int(1) NOT NULL,
  `ip` float(3,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `student_data`
--

INSERT INTO `student_data` (`student_id`, `name`, `email`, `major`, `semester`, `ip`) VALUES
('0806022210008', 'Henry Bintang Setiawan', 'hbintang@student.ciputra.ac.id', 'Informatics', 2, 4.00);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `score_calculus`
--
ALTER TABLE `score_calculus`
  ADD KEY `student_id` (`student_id`);

--
-- Indexes for table `score_database`
--
ALTER TABLE `score_database`
  ADD KEY `student_id` (`student_id`);

--
-- Indexes for table `score_oop`
--
ALTER TABLE `score_oop`
  ADD KEY `student_id` (`student_id`);

--
-- Indexes for table `score_web`
--
ALTER TABLE `score_web`
  ADD KEY `student_id` (`student_id`);

--
-- Indexes for table `student_data`
--
ALTER TABLE `student_data`
  ADD PRIMARY KEY (`student_id`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `score_calculus`
--
ALTER TABLE `score_calculus`
  ADD CONSTRAINT `score_calculus_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `student_data` (`student_id`);

--
-- Constraints for table `score_database`
--
ALTER TABLE `score_database`
  ADD CONSTRAINT `student_id` FOREIGN KEY (`student_id`) REFERENCES `student_data` (`student_id`);

--
-- Constraints for table `score_oop`
--
ALTER TABLE `score_oop`
  ADD CONSTRAINT `score_oop_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `student_data` (`student_id`);

--
-- Constraints for table `score_web`
--
ALTER TABLE `score_web`
  ADD CONSTRAINT `score_web_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `student_data` (`student_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
