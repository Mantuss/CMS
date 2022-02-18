-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 14, 2022 at 10:50 AM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 8.0.13

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `course_management`
--

-- --------------------------------------------------------

--
-- Table structure for table `assigned_modules`
--

CREATE TABLE `assigned_modules` (
  `assigned_id` int(11) NOT NULL,
  `module_name` varchar(200) NOT NULL,
  `instruct_username` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `courses`
--

CREATE TABLE `courses` (
  `course_id` int(11) NOT NULL,
  `course_name` varchar(200) NOT NULL,
  `course_full` varchar(120) NOT NULL,
  `addedDate` varchar(2000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `courses`
--

INSERT INTO `courses` (`course_id`, `course_name`, `course_full`, `addedDate`) VALUES
(24, 'BBA', 'Bachelor of Business Administration', '2022/01/21'),
(28, 'BIT', 'Bachelors in Information Technology', '2022/9/2');

-- --------------------------------------------------------

--
-- Table structure for table `logs`
--

CREATE TABLE `logs` (
  `user_id` int(10) UNSIGNED NOT NULL,
  `username` varchar(10) NOT NULL,
  `password` varchar(10) NOT NULL,
  `indicator` varchar(10) NOT NULL,
  `recovery_code` varchar(10) NOT NULL,
  `status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `logs`
--

INSERT INTO `logs` (`user_id`, `username`, `password`, `indicator`, `recovery_code`, `status`) VALUES
(2, 'admin', 'admin', 'admin', 'admin', 1),
(36, 'Raymon', '73-55-608', 'Student', 'UAJTRZ', 1),
(38, 'Bishal', 'bishal123', 'Teacher', 'EQCHDS', 1);

-- --------------------------------------------------------

--
-- Table structure for table `modules`
--

CREATE TABLE `modules` (
  `course_id` int(10) UNSIGNED NOT NULL,
  `course_modules` varchar(500) NOT NULL,
  `module_code` varchar(50) NOT NULL,
  `level` varchar(100) NOT NULL,
  `course_names` varchar(120) NOT NULL,
  `semester` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `modules`
--

INSERT INTO `modules` (`course_id`, `course_modules`, `module_code`, `level`, `course_names`, `semester`) VALUES
(1, 'Academic Skills and Team B.L', '4CI018', '4', 'BIT', '1'),
(2, 'Introductory Programming and P.S', '4CS001', '4', 'BIT', '1'),
(3, 'Fundamentals of Computing', '4CS015', '4', 'BIT', '1'),
(4, 'Database Management System', '4CS019', '4', 'BIT', '1'),
(5, 'Embedded System Programming', '4CS016', '4', 'BIT', '2'),
(6, 'Internet Software Architecture', '4CS017', '4', 'BIT', '2'),
(7, 'Computational Mathematics', '4MM013', '4', 'BIT', '2'),
(8, 'Statistics & Probability', '4CS020', '4', 'BIT', '2'),
(9, 'Concepts and Technologies of AI', '5CS037', '5', 'BIT', '3'),
(10, 'Object-Oriented Design & Program', '5CS019', '5', 'BIT', '3'),
(11, 'Numerical Methods and Concurrency', '5CS021', '5', 'BIT', '3'),
(12, 'Advance Database Management', '5CS023', '5', 'BIT', '3'),
(13, 'Distributed and Cloud S.P', '5CS022', '5', 'BIT', '4'),
(14, 'Collaborative Development', '5CS024', '5', 'BIT', '4'),
(15, 'Human Computer Interaction', '5CS020', '5', 'BIT', '4'),
(16, 'Data Structure & Algo', '5CS023', '5', 'BIT', '4'),
(17, 'Complex System', '6CS014', '6', 'BIT', '5'),
(18, 'High Performance Computing', '6CS005', '6', 'BIT', '5'),
(19, 'Project and Professionalism', '6CS007', '6', 'BIT', '5'),
(20, 'Web Framework', '6CS008', '6', 'BIT', '5'),
(21, 'Artificial Intelligence and M.L ', '6CS012', '6', 'BIT', '6'),
(22, 'Big Data', '6CS030', '6', 'BIT', '6'),
(23, 'Complex Mathematics', '6CS069', '6', 'BIT', '6'),
(24, 'Full Stack Development', '6CS041', '6', 'BIT', '6');

-- --------------------------------------------------------

--
-- Table structure for table `report`
--

CREATE TABLE `report` (
  `module_id` int(10) UNSIGNED NOT NULL,
  `student_name` varchar(150) NOT NULL,
  `module_name` varchar(150) NOT NULL,
  `marks` int(100) NOT NULL,
  `semester` int(11) NOT NULL,
  `level` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `report`
--

INSERT INTO `report` (`module_id`, `student_name`, `module_name`, `marks`, `semester`, `level`) VALUES
(1, 'Raymon Bikram Basnyat', 'Introductory Programming and P.S', 0, 1, 4),
(2, 'Raymon Bikram Basnyat', 'Fundamentals of Computing', 0, 1, 4),
(3, 'Raymon Bikram Basnyat', 'Database Management System', 0, 1, 4),
(4, 'Raymon Bikram Basnyat', 'Academic Skills and Team B.L', 0, 1, 4);

-- --------------------------------------------------------

--
-- Table structure for table `session`
--

CREATE TABLE `session` (
  `user_id` int(11) NOT NULL,
  `username` varchar(120) NOT NULL,
  `indicator` varchar(120) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `session`
--

INSERT INTO `session` (`user_id`, `username`, `indicator`) VALUES
(249, 'Raymon', 'Student');

-- --------------------------------------------------------

--
-- Table structure for table `user_info`
--

CREATE TABLE `user_info` (
  `fullname` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `phone` varchar(100) NOT NULL,
  `indicator` varchar(100) NOT NULL,
  `user_id` int(11) NOT NULL,
  `username` varchar(100) NOT NULL,
  `enrolled_modules` varchar(10000) NOT NULL,
  `level` varchar(120) NOT NULL,
  `assigned` varchar(1500) NOT NULL,
  `college_id` varchar(50) NOT NULL,
  `enrolled_courses` varchar(1000) NOT NULL,
  `semester` int(11) NOT NULL,
  `completed_sem` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user_info`
--

INSERT INTO `user_info` (`fullname`, `email`, `phone`, `indicator`, `user_id`, `username`, `enrolled_modules`, `level`, `assigned`, `college_id`, `enrolled_courses`, `semester`, `completed_sem`) VALUES
('Admin', 'null', '0', 'admin', 1, 'Admin', 'null', '0', 'null', 'null', 'BIT', 0, 0),
('Raymon Bikram Basnyat', 'basnetraymonn@gmail.com', '9803822743', 'Student', 67, 'Raymon', ',4CS001,4CS015,4CS019,4CI018', '4', 'null', '2059640', 'BIT', 1, 0),
('Bishal Khadka', 'bishal@gmail.com', '9803465676', 'Teacher', 69, 'Bishal', 'null', 'null', '', 'null', 'null', 0, 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `assigned_modules`
--
ALTER TABLE `assigned_modules`
  ADD PRIMARY KEY (`assigned_id`);

--
-- Indexes for table `courses`
--
ALTER TABLE `courses`
  ADD PRIMARY KEY (`course_id`);

--
-- Indexes for table `logs`
--
ALTER TABLE `logs`
  ADD PRIMARY KEY (`user_id`);

--
-- Indexes for table `modules`
--
ALTER TABLE `modules`
  ADD PRIMARY KEY (`course_id`);

--
-- Indexes for table `report`
--
ALTER TABLE `report`
  ADD PRIMARY KEY (`module_id`);

--
-- Indexes for table `session`
--
ALTER TABLE `session`
  ADD PRIMARY KEY (`user_id`);

--
-- Indexes for table `user_info`
--
ALTER TABLE `user_info`
  ADD PRIMARY KEY (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `assigned_modules`
--
ALTER TABLE `assigned_modules`
  MODIFY `assigned_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `courses`
--
ALTER TABLE `courses`
  MODIFY `course_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;

--
-- AUTO_INCREMENT for table `logs`
--
ALTER TABLE `logs`
  MODIFY `user_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=39;

--
-- AUTO_INCREMENT for table `modules`
--
ALTER TABLE `modules`
  MODIFY `course_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT for table `report`
--
ALTER TABLE `report`
  MODIFY `module_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `session`
--
ALTER TABLE `session`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=252;

--
-- AUTO_INCREMENT for table `user_info`
--
ALTER TABLE `user_info`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=70;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
