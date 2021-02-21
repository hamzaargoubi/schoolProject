-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Mer 06 Mai 2020 à 03:23
-- Version du serveur :  5.6.17-log
-- Version de PHP :  5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données :  `mawhebty`
--

-- --------------------------------------------------------

--
-- Structure de la table `categoris`
--

CREATE TABLE IF NOT EXISTS `categoris` (
  `id_cat` int(11) NOT NULL AUTO_INCREMENT,
  `nom_cat` int(11) NOT NULL,
  PRIMARY KEY (`id_cat`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `commentaires`
--

CREATE TABLE IF NOT EXISTS `commentaires` (
  `id_comm` int(11) NOT NULL AUTO_INCREMENT,
  `descr` text NOT NULL,
  `id_vid` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  PRIMARY KEY (`id_comm`),
  KEY `id_vid` (`id_vid`),
  KEY `id_user` (`id_user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `competitions`
--

CREATE TABLE IF NOT EXISTS `competitions` (
  `id_comp` int(11) NOT NULL AUTO_INCREMENT,
  `nom_comp` int(11) NOT NULL,
  `id_cat` int(11) NOT NULL,
  `date_deb` int(11) NOT NULL,
  `date_fin` int(11) NOT NULL,
  PRIMARY KEY (`id_comp`),
  KEY `competitions_ibfk_1` (`id_cat`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `participants`
--

CREATE TABLE IF NOT EXISTS `participants` (
  `id_user` int(11) NOT NULL,
  `id_comp` int(11) NOT NULL,
  KEY `id_user` (`id_user`),
  KEY `id_comp` (`id_comp`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `personnes`
--

CREATE TABLE IF NOT EXISTS `personnes` (
  `id_user` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) NOT NULL,
  `prenom` varchar(50) NOT NULL,
  `date_de_nes` date NOT NULL,
  `adress` varchar(80) NOT NULL,
  `profil` varchar(10) NOT NULL,
  `photo` varchar(80) NOT NULL,
  `login` varchar(50) NOT NULL,
  `pwd` varchar(50) NOT NULL,
  `list_video` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  PRIMARY KEY (`id_user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `reacts`
--

CREATE TABLE IF NOT EXISTS `reacts` (
  `id_react` int(11) NOT NULL AUTO_INCREMENT,
  `type_react` varchar(10) NOT NULL,
  `id_vid` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  PRIMARY KEY (`id_react`),
  UNIQUE KEY `id_user` (`id_user`),
  KEY `id_vid` (`id_vid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `videos`
--

CREATE TABLE IF NOT EXISTS `videos` (
  `id_vid` int(11) NOT NULL AUTO_INCREMENT,
  `nom_vid` varchar(55) NOT NULL,
  `path_vid` varchar(55) NOT NULL,
  `desc_vid` text NOT NULL,
  `id_comp` int(11) NOT NULL,
  PRIMARY KEY (`id_vid`),
  KEY `id-comp` (`id_comp`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 AUTO_INCREMENT=1 ;

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `commentaires`
--
ALTER TABLE `commentaires`
  ADD CONSTRAINT `commentaires_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `personnes` (`id_user`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `commentaires_ibfk_2` FOREIGN KEY (`id_vid`) REFERENCES `videos` (`id_vid`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `competitions`
--
ALTER TABLE `competitions`
  ADD CONSTRAINT `competitions_ibfk_1` FOREIGN KEY (`id_cat`) REFERENCES `categoris` (`id_cat`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `participants`
--
ALTER TABLE `participants`
  ADD CONSTRAINT `participants_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `personnes` (`id_user`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `participants_ibfk_2` FOREIGN KEY (`id_comp`) REFERENCES `competitions` (`id_comp`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `personnes`
--
ALTER TABLE `personnes`
  ADD CONSTRAINT `personnes_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `participants` (`id_user`) ON DELETE CASCADE;

--
-- Contraintes pour la table `reacts`
--
ALTER TABLE `reacts`
  ADD CONSTRAINT `reacts_ibfk_1` FOREIGN KEY (`id_vid`) REFERENCES `videos` (`id_vid`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `reacts_ibfk_2` FOREIGN KEY (`id_user`) REFERENCES `personnes` (`id_user`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `videos`
--
ALTER TABLE `videos`
  ADD CONSTRAINT `videos_ibfk_1` FOREIGN KEY (`id_comp`) REFERENCES `competitions` (`id_comp`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
