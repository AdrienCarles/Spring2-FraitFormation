-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : lun. 29 jan. 2024 à 15:52
-- Version du serveur : 10.4.32-MariaDB
-- Version de PHP : 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `spring2_declarationfrais`
--

-- --------------------------------------------------------

--
-- Structure de la table `declaration`
--

CREATE TABLE `declaration` (
  `id` bigint(20) NOT NULL,
  `coordonneesbancaires` varchar(255) NOT NULL,
  `date` varchar(255) NOT NULL,
  `intitule` varchar(255) NOT NULL,
  `lieu` varchar(255) NOT NULL,
  `lieu_depart` varchar(255) NOT NULL,
  `montant_hebergement` varchar(255) NOT NULL,
  `montant_transport` varchar(255) NOT NULL,
  `nom_fichier_hebergement` varchar(255) NOT NULL,
  `nom_fichier_transport` varchar(255) NOT NULL,
  `ref_dossier` varchar(255) NOT NULL,
  `statut` varchar(255) NOT NULL,
  `type_transport` varchar(255) NOT NULL,
  `type_hebergement` varchar(255) NOT NULL,
  `user` varchar(255) NOT NULL,
  `old_statut` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `declaration`
--

INSERT INTO `declaration` (`id`, `coordonneesbancaires`, `date`, `intitule`, `lieu`, `lieu_depart`, `montant_hebergement`, `montant_transport`, `nom_fichier_hebergement`, `nom_fichier_transport`, `ref_dossier`, `statut`, `type_transport`, `type_hebergement`, `user`, `old_statut`) VALUES
(1, 'FR09HB09900990902', '2024-01-08', 'Séminaire éducatif', 'Piscine Alex Jany', 'Votre domicile', '', '30', '', 'essence.pdf', 'DEC7307319', 'Valide', 'Grande Lignes: Train, Avion, Ferry', 'Derogation de mon CE', 's.esposito@mail.com', 'Valide'),
(2, 'FR09HB09900990905', '2023-12-21 14:50:49', 'Formation gestion d\'equipe', 'Montpellier', 'Toulouse', '43', '90', 'hotel.pdf', 'billets-train.pdf', 'DEC8729001', 'Invalide', 'Grande Lignes: Train, Avion, Ferry', 'Hebergement payant', 'p.sgurio@mail.com', NULL),
(3, 'FR09HB22900990902', '2023-12-15 08:00:00', 'Formation Pédagogique', 'Paris', 'Toulouse', '50', '30', 'hebergement1.pdf', 'transport1.pdf', 'DEC123456', 'Invalide', 'Mon propre véhicule', 'Hebergement payant', 's.esposito@mail.com', NULL),
(4, 'FR09HB09911190902', '2023-12-16 09:00:00', 'Séminaire Éducatif', 'Lyon', 'Toulouse', '40', '20', 'hebergement2.pdf', 'transport2.pdf', 'DEC987654', 'En attente', 'Grande Lignes: Train, Avion, Ferry', 'Hebergement gratuit', 'p.sgurio@mail.com', NULL),
(5, 'FR09HB09900990911', '2023-12-17 10:00:00', 'Stage Pédagogique', 'Marseille', 'Toulouse', '60', '40', 'hebergement3.pdf', 'transport3.pdf', 'DEC567890', 'Invalide', 'Mon propre véhicule', 'Hebergement payant', 's.dubois@mail.com', NULL),
(6, 'FR09HB09360990902', '2023-12-18 11:00:00', 'Atelier Éducatif', 'Bordeaux', 'Toulouse', '35', '25', 'hebergement4.pdf', 'transport4.pdf', 'DEC246810', 'En attente', 'Grande Lignes: Train, Avion, Ferry', 'Dérogation de mon CE', 'p.sgurio@mail.com', NULL),
(7, 'FR09HB09900990888', '2023-12-19 12:00:00', 'Conférence Pédagogique', 'Nantes', 'Toulouse', '55', '35', 'hebergement5.pdf', 'transport5.pdf', 'DEC135791', 'Valide', 'Mon propre véhicule', 'Hebergement payant', 's.esposito@mail.com', NULL),
(8, 'FR09HB09900912345', '2023-12-13 17:00:50', 'Journée découverte', 'Limayrac', 'Domicile', '100', '60', 'hebergement.pdf', 'transport.pdf', 'DEC8292827', 'Valide', 'Mon propre véhicule', 'Hebergement payant', 's.esposito@mail.com', NULL),
(77, 'FR09HB09900990902', '2024-01-24', 'Formation', 'Pau', 'Votre domicile', '60', '40', 'fcature_hebrgement.png', 'transport.jpg', 'DEC9555755', 'en attente', 'Grande Lignes: Train, Avion, Ferry', 'Hebergement payant', 'j.jean@mal.com', 'en attente'),
(78, 'FR09HB09900990902', '2024-01-10', 'Formation', 'Complexe Paul Sabatier', 'Votre domicile', '', '10.01', '', 'transport.jpg', 'DEC8913422', 'Valide', 'Mon propre vehicule', 'Derogation de mon CE', 's.esposito@mail.com', 'Valide');

-- --------------------------------------------------------

--
-- Structure de la table `restauration`
--

CREATE TABLE `restauration` (
  `id` bigint(20) NOT NULL,
  `montant_resto` varchar(255) NOT NULL,
  `nom_fichier_resto` varchar(255) NOT NULL,
  `repas` varchar(255) NOT NULL,
  `declaration_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `restauration`
--

INSERT INTO `restauration` (`id`, `montant_resto`, `nom_fichier_resto`, `repas`, `declaration_id`) VALUES
(1, '10', 'resto.pdf', 'dejeuner', 1),
(2, '15', 'diner.pdf', 'diner', 1),
(3, '20', 'ticket-caisse-restaurant.pdf', 'Diner', 2),
(4, '10', 'resto1.pdf', 'déjeuner', 1),
(6, '20', 'resto3.pdf', 'déjeuner', 2),
(7, '25', 'resto4.pdf', 'dîner', 2),
(8, '30', 'resto5.pdf', 'déjeuner', 3),
(9, '35', 'resto6.pdf', 'dîner', 3),
(10, '40', 'resto7.pdf', 'déjeuner', 4),
(11, '45', 'resto8.pdf', 'dîner', 4),
(12, '50', 'resto9.pdf', 'déjeuner', 5),
(13, '55', 'resto10.pdf', 'dîner', 5),
(102, '10', 'petit_dej.jpg', 'Petit-dejeuner', 77),
(103, '20', 'dejeuner.jpg', 'Déjeuner', 77),
(104, '20', 'diner.jpg', 'Diner', 77);

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `email` varchar(50) NOT NULL,
  `first_name` varchar(20) NOT NULL,
  `last_name` varchar(20) NOT NULL,
  `password` varchar(100) NOT NULL,
  `role` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`id`, `email`, `first_name`, `last_name`, `password`, `role`) VALUES
(1, 'admin@admin', 'admin', 'admin', '$2a$10$1WfjU9.fgTIpM18D0m1RteVCOiHsZjySbuQyp5P/0Fce8D49SYc0C', 1),
(2, 's.esposito@mail.com', 'Solene', 'Esposito', '$2a$10$PPN5E9ioaNFwFmwtlcvigOCcFFlWkBJLXmU/FO5mWXztH3DVeckwW', 0),
(3, 's.dubois@mail.com', 'Sieg', 'Dubois', '$2a$10$L6ZaRaaaCW7ksfvWQdkh5ewgyN9n3INEdENysb63YLhHRVpR0N9hW', 0),
(4, 'p.sgurio@mail.com', 'Pierre', 'Sgurio', '$2a$10$DX97Lckhupa10n11ZG9Juuwt0JGe.rlnPgpzq8aGcvnL2tjDhNm0q', 0),
(9, 'd.gloudi@mail.com', 'Dominique', 'Gloudi', '$2a$10$04NZVHBrTIo.WTnIu7ER7OBwwNlNj.u0PsvNKGgfJr/wT0Os/H/1C', 0),
(10, 'p.felgrat@mail.com', 'Patricia', 'Felgrat', '$2a$10$v/Xe5AWi8h4plJezf7Sjf.TgVYjwBqeajRV5seQZHrHxwCG7la6Hm', 0),
(13, 'j.jean@mal.com', 'julie', 'jean', '$2a$10$3vpU1XnpJdQ1WFtBXKqOSO2s5bKtYzw9UAM8wLK9gnmAN8oV8DNA.', 0);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `declaration`
--
ALTER TABLE `declaration`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `restauration`
--
ALTER TABLE `restauration`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKtrv8sl43wa3oe6gmqwwejx611` (`declaration_id`);

--
-- Index pour la table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_ob8kqyqqgmefl0aco34akdtpe` (`email`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `declaration`
--
ALTER TABLE `declaration`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=79;

--
-- AUTO_INCREMENT pour la table `restauration`
--
ALTER TABLE `restauration`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=105;

--
-- AUTO_INCREMENT pour la table `user`
--
ALTER TABLE `user`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `restauration`
--
ALTER TABLE `restauration`
  ADD CONSTRAINT `FKtrv8sl43wa3oe6gmqwwejx611` FOREIGN KEY (`declaration_id`) REFERENCES `declaration` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
