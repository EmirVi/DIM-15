-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: May 10, 2023 at 04:42 AM
-- Server version: 8.0.30
-- PHP Version: 8.0.22

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `online presentations`
--

-- --------------------------------------------------------

--
-- Table structure for table `presentations`
--

CREATE TABLE `presentations` (
  `id` int NOT NULL,
  `title` varchar(255) COLLATE utf8mb4_ru_0900_ai_ci NOT NULL,
  `user` int NOT NULL,
  `description` text COLLATE utf8mb4_ru_0900_ai_ci,
  `image` varchar(255) COLLATE utf8mb4_ru_0900_ai_ci DEFAULT NULL,
  `video` varchar(255) COLLATE utf8mb4_ru_0900_ai_ci DEFAULT NULL,
  `published` tinyint(1) DEFAULT '0',
  `evaluation` int DEFAULT NULL,
  `meta_key` varchar(255) COLLATE utf8mb4_ru_0900_ai_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_ru_0900_ai_ci;

--
-- Dumping data for table `presentations`
--

INSERT INTO `presentations` (`id`, `title`, `user`, `description`, `image`, `video`, `published`, `evaluation`, `meta_key`) VALUES
(1, 'Тестовая презентация ', 1, 'С использованием адаптивной верстки создать web страницы ввода презентации. Страницы создания новой и редактирования презентации должны быть идентичными, только при открытии презентации для редактирования поля ввода должны быть уже заполнены данными из таблицы presentations и при сохранении внесенных изменений используется команда update, а при создании новой презентации поля ввода должны быть пустыми и при сохранении внесенных изменений используется команда insert. На странице презентации должен отображаться список слайдов этой презентации, так же находиться кнопка добавить слайд, при нажатии на которую должен осуществляться переход на страницу, содержащую поля ввода информации для создания нового слайда.', 'uploads/Kira/Screenshot_20221203_121525.png', NULL, 0, NULL, NULL),
(2, 'Еще одна презентация ', 1, 'For the single-table syntax, the UPDATE statement updates columns of existing rows in the named table with new values. The SET clause indicates which columns to modify and the values they should be given. Each value can be given as an expression, or the keyword DEFAULT to set a column explicitly to its default value. The WHERE clause, if given, specifies the conditions that identify which rows to update. With no WHERE clause, all rows are updated. If the ORDER BY clause is specified, the rows are updated in the order that is specified. The LIMIT clause places a limit on the number of rows that can be updated.', 'uploads/EmirVi/Screenshot_20221027_041038.png', NULL, 0, NULL, NULL),
(3, 'Лабораторная 1', 2, 'Создать реляционную базу данных, состоящую из 3-х таблиц users, presentations, pages.\r\n\r\nТаблица users должна содержать поля: id, name, description, login, password, mail.\r\n\r\nТаблица presentation должна содержать поля: id, title, user, description, image, video, published, evaluation, meta_key.\r\n\r\nТаблица page должна содержать поля: id, nomber, title, content, image, video, voice, meta_key.\r\n\r\nРисунки, видео и звук должны загружаться на сервер, а в базе сохраняется лишь ссылка на файлы.\r\n\r\nФорма отчета по первой лабораторной работе собеседование + sql файл.', 'uploads/EmirVi/Screenshot 2023-03-22 033158.png', NULL, 0, NULL, NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `presentations`
--
ALTER TABLE `presentations`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user` (`user`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `presentations`
--
ALTER TABLE `presentations`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `presentations`
--
ALTER TABLE `presentations`
  ADD CONSTRAINT `presentations_ibfk_1` FOREIGN KEY (`user`) REFERENCES `users` (`id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
