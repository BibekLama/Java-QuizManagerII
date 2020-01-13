-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 13, 2020 at 12:10 PM
-- Server version: 10.1.36-MariaDB
-- PHP Version: 7.2.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `quiz_manager_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `admins`
--

CREATE TABLE `admins` (
  `id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admins`
--

INSERT INTO `admins` (`id`, `username`, `password`) VALUES
(1, 'admin', '5f4dcc3b5aa765d61d8327deb882cf99');

-- --------------------------------------------------------

--
-- Table structure for table `mcq_answers`
--

CREATE TABLE `mcq_answers` (
  `id` int(11) NOT NULL,
  `quest_id` int(11) NOT NULL,
  `answer` varchar(255) NOT NULL,
  `isCorrect` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `mcq_answers`
--

INSERT INTO `mcq_answers` (`id`, `quest_id`, `answer`, `isCorrect`) VALUES
(1, 12, 'vbvb', 0),
(2, 12, 'hjhjh', 0),
(3, 12, 'tytyt', 1),
(4, 12, 'jkjl', 0),
(5, 13, 'fdg', 0),
(6, 13, 'bnvb', 0),
(7, 13, 'asda', 1),
(8, 13, 'xcvcx', 0),
(9, 14, 'rskdfhs', 0),
(10, 14, 'cv,mbncv', 1),
(11, 14, 'ortiuyor', 0),
(12, 14, 'djgjdkfh', 0),
(13, 15, '', 1),
(14, 15, '', 0),
(15, 15, '', 0),
(16, 15, '', 0),
(17, 16, 'nm', 0),
(18, 16, 'vb', 0),
(19, 16, 'hj', 0),
(20, 16, 'jk', 1),
(21, 17, 'ksjhks', 1),
(22, 17, 'qkwjehqwk', 0),
(23, 17, 'kajsdhask', 0),
(24, 17, 'kjasdhkas', 0),
(25, 18, 'sdkfsdlkj', 0),
(26, 18, 'lasdkjasl', 1),
(27, 18, 'alskdj', 0),
(28, 18, 'alsdkjsal', 0),
(29, 19, 'salskdj', 1),
(30, 19, 'lskdjasl', 0),
(31, 19, 'alskdjl', 0),
(32, 19, 'alsdkjal', 0),
(33, 20, 'dadasd', 0),
(34, 20, 'dasdas', 0),
(35, 20, 'qdd', 1),
(36, 20, 'eqweqwe', 0),
(37, 21, 'sldfkjsdlfsj', 0),
(38, 21, 'laskdjals', 0),
(39, 21, 'lksdjasldal', 0),
(40, 21, 'lqkjeqwlejq', 1);

-- --------------------------------------------------------

--
-- Table structure for table `questions`
--

CREATE TABLE `questions` (
  `id` int(11) NOT NULL,
  `quiz_id` int(11) NOT NULL,
  `question` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `questions`
--

INSERT INTO `questions` (`id`, `quiz_id`, `question`) VALUES
(12, 11, 'sdasda'),
(13, 6, 'hello?'),
(14, 11, 'Hello World?'),
(15, 11, 'sdfs'),
(16, 11, 'dlfdksjdf'),
(17, 12, 'lkrjwelrej?'),
(18, 13, 'slkdjaslkdja?'),
(19, 13, 'sljfsldfjsd?'),
(20, 14, 'what is your name?'),
(21, 14, 'asdjalsdjaldjasdjalsdjalsd ?');

-- --------------------------------------------------------

--
-- Table structure for table `quizes`
--

CREATE TABLE `quizes` (
  `id` int(11) NOT NULL,
  `title` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `quizes`
--

INSERT INTO `quizes` (`id`, `title`) VALUES
(1, 'Quiz One'),
(2, 'Java Quiz'),
(3, 'French Language Quiz'),
(4, 'CPP Quiz'),
(5, 'Digital Marketing Quiz'),
(6, 'test'),
(8, 'ddfgd'),
(9, 'latest'),
(11, 'Algorithm'),
(12, 'agdfgjdfl'),
(13, 'slkfjsldfj'),
(14, 'new quiz');

-- --------------------------------------------------------

--
-- Table structure for table `students`
--

CREATE TABLE `students` (
  `id` int(11) NOT NULL,
  `fullname` varchar(50) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admins`
--
ALTER TABLE `admins`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Indexes for table `mcq_answers`
--
ALTER TABLE `mcq_answers`
  ADD PRIMARY KEY (`id`),
  ADD KEY `quest_mcq_answer` (`quest_id`);

--
-- Indexes for table `questions`
--
ALTER TABLE `questions`
  ADD PRIMARY KEY (`id`),
  ADD KEY `quiz_question` (`quiz_id`);

--
-- Indexes for table `quizes`
--
ALTER TABLE `quizes`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `students`
--
ALTER TABLE `students`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admins`
--
ALTER TABLE `admins`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `mcq_answers`
--
ALTER TABLE `mcq_answers`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=41;

--
-- AUTO_INCREMENT for table `questions`
--
ALTER TABLE `questions`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT for table `quizes`
--
ALTER TABLE `quizes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `students`
--
ALTER TABLE `students`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `mcq_answers`
--
ALTER TABLE `mcq_answers`
  ADD CONSTRAINT `quest_mcq_answer` FOREIGN KEY (`quest_id`) REFERENCES `questions` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `questions`
--
ALTER TABLE `questions`
  ADD CONSTRAINT `quiz_question` FOREIGN KEY (`quiz_id`) REFERENCES `quizes` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
