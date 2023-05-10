<?php
include "auth_session.php";
?>
<!DOCTYPE html>
<html>
<head>
    <?php
    $website_title = "Online презентации";
    require "head.php";
    ?>
</head>

<body>
    <div class="form">
        <h3>Добро пожаловать, <?php echo $_SESSION['login']; ?>!</h3>

        <div style="display: flex; flex-direction: row;">
            <input type="submit" class="button" style="margin: auto 0;" onclick="window.location.href='presentation.php';" value="Добавить презентацию" />
            <input type="submit" class="logout-button" style="margin-left: auto;" onclick="window.location.href='logout.php';" value="Выход из системы" />       
        </div>

        <div class="grid-container">
        <?php
            require 'dbConfig.php';
            $query = $dbCon->query("SELECT * FROM presentations ORDER BY id DESC");
            if($query->num_rows > 0){
                while($row = $query->fetch_assoc()){                    
                    $imageURL = $row["image"];
                    $title = $row["title"];
                    $description = $row["description"];
                    $id = $row["id"];
                    ?>
                        <div type="submit" class="card" style="cursor: pointer;"
                             onclick="window.location.href='presentation.php?id=<?php echo $id; ?>';">                                           
                            <img src="<?php echo $imageURL; ?>" width="250" height="auto"/>
                            <div class="container">
                                <h4><b><?php echo $title; ?></b></h4>
                                <p><?php echo $description; ?></p>
                            </div>
                        </div>                                                        
                <?php }
            } else { ?>
            </div>
                <h3>Здесь пока ничего нет, добавьте новую презентацию!</h3>
            <?php } ?>
        
        
    </div>
</body>
</html>