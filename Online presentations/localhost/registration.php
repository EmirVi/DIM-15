<!DOCTYPE html>
<html>
<head>
    <?php
    $website_title = "Регистрация";
    require "head.php";
    ?>
</head>
<body>
<?php
    require "dbConfig.php";
    session_start();
    if (isset($_REQUEST['login'])) {
        $name = stripslashes($_REQUEST['name']);
        $name = mysqli_real_escape_string($dbCon, $name);
        $description = stripslashes($_REQUEST['description']);
        $description = mysqli_real_escape_string($dbCon, $description);
        $login = stripslashes($_REQUEST['login']);
        $login = mysqli_real_escape_string($dbCon, $login);
        $password = stripslashes($_REQUEST['password']);
        $password = mysqli_real_escape_string($dbCon, $password);
        $email = stripslashes($_REQUEST['email']);
        $email = mysqli_real_escape_string($dbCon, $email);
        $captcha = stripslashes($_REQUEST['captcha']);
        $query = "INSERT INTO users (name, description, login, password, mail)
                  VALUES ('$name', '$description', '$login', '$password', '$email')";
        $result = mysqli_query($dbCon, $query);
        if ($result) {
            if($_SESSION['captcha'] == $captcha){
				echo "<div class='form'>
                <h3>Регистрация прошла успешно.</h3><br/>
                <p class='link'><a href='login.php'>Войти</a></p>
                </div>";
			}else{
				echo "<div class='form'>
                <h3>Неверная captcha.</h3><br/>
                <p class='link'>Попробуйте <a href='registration.php'>Зарегистрироваться</a> ещё раз.</p>
                </div>";
			}            
        } else {
            echo "<div class='form'>
                  <h3>Обязательные поля отсутствуют.</h3><br/>
                  <p class='link'>Попробуйте <a href='registration.php'>Зарегистрироваться</a> ещё раз.</p>
                  </div>";
        }
    } else {
?>
    <h1>Добро пожаловать на сервис Online презентаций</h1>
    <form class="login-form" method="post">
        <h2 class="login-title">Регистрация</h2>
        <input type="text" class="input-field" name="name" required placeholder="Имя пользователя" autofocus="true" />
        <textarea class="input-field" name="description" required placeholder="О себе"></textarea>
        <input type="text" class="input-field" name="login" required placeholder="Имя пользователя" />
        <input type="text" class="input-field" name="email" required placeholder="Email адрес">
        <input type="password" class="input-field" name="password" required placeholder="Пароль">
        <div style="display: flex; flex-direction: row;">
            <img src="captcha.php" width="150" height="47"/>
            <input type="text" class="input-field" name="captcha" required placeholder="CAPTCHA"/>
        </div>
        <input type="submit" class="login-button" name="submit" value="Зарегистрироваться">
        <p class="link"><a href="login.php">Войти</a></p>
    </form>
<?php
    }
?>
</body>
</html>