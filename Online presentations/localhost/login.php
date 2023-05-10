<!DOCTYPE html>
<html>
<head>
    <?php
    $website_title = "Авторизация";
    require "head.php";
    ?>
</head>
<body>
    <?php
    require "dbConfig.php";
    session_start();
    if (isset($_POST['login'])) {
        $login = stripslashes($_REQUEST['login']);
        $login = mysqli_real_escape_string($dbCon, $login);
        $password = stripslashes($_REQUEST['password']);
        $password = mysqli_real_escape_string($dbCon, $password);
        $captcha = stripslashes($_REQUEST['captcha']);
        $query = "SELECT id FROM users WHERE login='$login'
                  AND password='$password'";
        $result = mysqli_query($dbCon, $query);
        $rows = mysqli_num_rows($result);
        if ($rows > 0) {
            if($_SESSION['captcha'] == $captcha){
				$_SESSION['login'] = $login;
                
                $row = mysqli_fetch_assoc($result);
                $user_id =  $row['id'];
                $_SESSION["user_id"] = $user_id;
                
                header("Location: dashboard.php");
			}else{
				echo "<div class='form'>
                <h3>Неверная captcha.</h3><br/>
                <p class='link'>Попробуйте <a href='login.php'>Войти</a> ещё раз.</p>
                </div>";
			}
        } else {
            echo "<div class='form'>
                <h3>Неверный логин или пароль.</h3><br/>
                <p class='link'>Попробуйте <a href='login.php'>Войти</a> ещё раз.</p>
                </div>";
        }
    } else {
    ?>
        <h1>Добро пожаловать на сервис Online презентаций</h1>
        <form class="login-form" method="post" name="login">
            <h2 class="login-title">Авторизация</h2>
            <input type="text" class="input-field" name="login" required placeholder="Логин" autofocus="true"/>
            <input type="password" class="input-field" name="password" required placeholder="Пароль"/>
            <div style="display: flex; flex-direction: row;">
                <img src="captcha.php" width="150" height="47"/>
                <input type="text" class="input-field" name="captcha" required placeholder="CAPTCHA"/>
            </div>
            <input type="submit" class="login-button" value="Войти" name="submit"/>
            <p class="link"><a href="registration.php">Зарегистрироваться</a></p>
        </form>
    <?php
    }
    ?>
</body>
</html>