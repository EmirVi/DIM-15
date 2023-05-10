<?php
$dbHost     = "localhost";
$dbUsername = "root";
$dbPassword = "";
$dbName     = "online presentations";

// Установка подключния к БД
$dbCon = new mysqli($dbHost, $dbUsername, $dbPassword, $dbName);

// Проверка подключения
if ($dbCon->connect_error) {
    die("Connection failed: " . $dbCon->connect_error);
}
?>