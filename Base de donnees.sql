-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 01, 2022 at 08:58 AM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 7.4.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `parc1`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `ida` int(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`ida`, `email`, `password`) VALUES
(1, 'admin@gmail.com', 'admin'),
(2, 'admin@gmail.com', 'admin');

-- --------------------------------------------------------

--
-- Table structure for table `article`
--

CREATE TABLE `article` (
  `id_article` int(20) NOT NULL,
  `quantite` int(40) NOT NULL,
  `id_commerce` int(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `article`
--

INSERT INTO `article` (`id_article`, `quantite`, `id_commerce`) VALUES
(295628, 720, 34547756),
(1234542, 332, 12312424),
(3454456, 764, 1312323),
(5213456, 234, 7654321);

-- --------------------------------------------------------

--
-- Table structure for table `attraction`
--

CREATE TABLE `attraction` (
  `id` int(255) NOT NULL,
  `txt_duree` float NOT NULL,
  `txt_nom` varchar(40) NOT NULL,
  `txt_prix` int(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `attraction`
--

INSERT INTO `attraction` (`id`, `txt_duree`, `txt_nom`, `txt_prix`) VALUES
(1321323, 30, 'Terrains de tennis', 300),
(2323553, 45, 'Pataugeoire ', 230);

-- --------------------------------------------------------

--
-- Table structure for table `billet`
--

CREATE TABLE `billet` (
  `id_billet` int(255) NOT NULL,
  `prix` int(255) NOT NULL,
  `date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `client`
--

CREATE TABLE `client` (
  `id_client` int(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `commandearticle`
--

CREATE TABLE `commandearticle` (
  `nom` varchar(40) NOT NULL,
  `prix` int(40) NOT NULL,
  `disponible` enum('oui','non','','') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `commandearticle`
--

INSERT INTO `commandearticle` (`nom`, `prix`, `disponible`) VALUES
('Jakob', 22, 'oui'),
('Fifa', 55, 'oui'),
('Giga', 77, 'non'),
('Tiwan', 80, 'oui');

-- --------------------------------------------------------

--
-- Table structure for table `commerce`
--

CREATE TABLE `commerce` (
  `id_commerce` int(40) NOT NULL,
  `nom` varchar(40) NOT NULL,
  `prix` int(20) NOT NULL,
  `categorie` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `commerce`
--

INSERT INTO `commerce` (`id_commerce`, `nom`, `prix`, `categorie`) VALUES
(1312323, 'Giga', 55, 'Jeux'),
(7654321, 'joker', 76, 'Livre'),
(12312424, 'Fifa', 123, 'Jeux'),
(34547756, 'Kiosk', 345, 'aliment');

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE `employee` (
  `ide` int(255) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `prenom` varchar(255) NOT NULL,
  `date_naiss` date NOT NULL,
  `salaire` int(255) NOT NULL,
  `nmr_matricule` int(255) NOT NULL,
  `role` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `facture`
--

CREATE TABLE `facture` (
  `nom` varchar(45) NOT NULL,
  `prix` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `facture`
--

INSERT INTO `facture` (`nom`, `prix`) VALUES
('Terrains de tennis', 300),
('Giga', 77);

-- --------------------------------------------------------

--
-- Table structure for table `parc`
--

CREATE TABLE `parc` (
  `id_parc` int(255) NOT NULL,
  `nom_parc` varchar(255) NOT NULL,
  `adresse` varchar(1024) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `repas`
--

CREATE TABLE `repas` (
  `id` int(255) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `quantite` int(40) NOT NULL,
  `prix` int(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `username` varchar(40) NOT NULL,
  `password` varchar(40) NOT NULL,
  `level_user` varchar(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`username`, `password`, `level_user`) VALUES
('Rachid', '123bslm', 'Utilisateur'),
('rachi', '123456', 'Administrateur'),
('rachid22', '1234', 'Utilisateur'),
('user', '123', 'Utilisateur');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `article`
--
ALTER TABLE `article`
  ADD PRIMARY KEY (`id_article`),
  ADD KEY `id_fk_constraint` (`id_commerce`);

--
-- Indexes for table `attraction`
--
ALTER TABLE `attraction`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `commerce`
--
ALTER TABLE `commerce`
  ADD PRIMARY KEY (`id_commerce`);

--
-- Indexes for table `repas`
--
ALTER TABLE `repas`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD UNIQUE KEY `unique` (`username`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `article`
--
ALTER TABLE `article`
  ADD CONSTRAINT `id_fk_constraint` FOREIGN KEY (`id_commerce`) REFERENCES `commerce` (`id_commerce`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
