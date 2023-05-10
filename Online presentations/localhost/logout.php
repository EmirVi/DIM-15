<?php
    session_start();
    // Закрытие сессии
    if(session_destroy()) {
        // Перенаправление на страницу авторизации
        header("Location: login.php");
    }
?>