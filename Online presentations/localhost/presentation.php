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

<?php
    require 'dbConfig.php';
    // Изменение уже существующей презентации
    if(isset($_GET["id"])){
        $id = $_GET["id"];
        $query = "SELECT * FROM presentations WHERE id = $id";
        $result = mysqli_query($dbCon, $query);
        $rows = mysqli_num_rows($result);
        if ($rows > 0) {
            $row = mysqli_fetch_assoc($result);
            $imageURL = $row["image"];
            $title = $row["title"];
            $description = $row["description"];

            if(isset($_POST["submit"])){
                $title = stripslashes($_REQUEST['title']);
                $title = mysqli_real_escape_string($dbCon, $title);
                $description = stripslashes($_REQUEST['description']);
                $description = mysqli_real_escape_string($dbCon, $description);
        
                // Путь к загружаемому файлу
                $targetDir = "uploads/".$_SESSION['login']."/";
                if (!file_exists($targetDir)) mkdir($targetDir, 0777, true);  // Создаем директорию если её изначально не было
                $fileName = basename($_FILES["file"]["name"]);
                $targetFilePath = $targetDir . $fileName;
                $fileType = pathinfo($targetFilePath,PATHINFO_EXTENSION);

                if(!empty($_FILES["file"]["name"])){
                    // Загрузка файла изображения на сервер
                    if(move_uploaded_file($_FILES["file"]["tmp_name"], $targetFilePath)){
                        // Загрузка имя файла изображения в базу данных
                        $query = "UPDATE presentations
                                  SET title = '$title', description = '$description', image = '$targetFilePath'
                                  WHERE id = '$id'";
                        $result = mysqli_query($dbCon, $query);
                        if($result){
                            echo "<div class='form'>
                                  <h3>Презентация успешно изменена.</h3><br/>
                                  <p class='link'><a href='dashboard.php'>Вернуться к списку</a></p>
                                  </div>";
                            }else{
                                echo "<div class='form'>
                                      <h3>Извините, при добавлении презентации в базу данных произошла ошибка.</h3><br/>
                                      <p class='link'>Попробуйте <a href='presentation.php'>создать презентацию</a> ещё раз.</p>
                                      </div>";
                        } 
                    }else{
                        echo "<div class='form'>
                              <h3>Извините, при загрузке файла на сервер произошла ошибка.</h3><br/>
                              <p class='link'>Попробуйте <a href='presentation.php'>создать презентацию</a> ещё раз.</p>
                              </div>";
                    }
                }
                else{
                    echo "<div class='form'>
                         <h3>Пожалуйста выберите титульное изображение для загрузки.</h3><br/>
                         <p class='link'>Попробуйте <a href='presentation.php'>создать презентацию</a> ещё раз.</p>
                         </div>";
                }
            }else{
                ?>
                <form class="form" method="post" enctype="multipart/form-data">
                    <h3>Изменение презентации: <?php echo $title; ?></h3>
                    <input type="text" class="input-field" name="title" value="<?php echo $title; ?>" required placeholder="Название" autofocus="true"/>
                    <textarea class="input-field" name="description" required placeholder="Описание"><?php echo $description; ?></textarea>
                    <label>Выберите титульное изображение:</label>
                    <input type="file" name="file" accept="image/*" onchange="imagePreview(this);">	
                    <div id="preview" src="#" style="margin: 25px auto; text-align: center;"></div>
                    <script>
                    var img = document.createElement("img");
                    img.src = "<?php echo $imageURL; ?>";
                    img.width = '300';
                    img.style.height = 'auto';
                    document.getElementById("preview").appendChild(img);
                    </script>
                    <div style="display: flex; flex-direction: row;">
                        <input type="submit" class="button" style="margin: auto 0;" name="submit" value="Изменить презентацию" />
                        <input type="button" class="logout-button" style="margin-left: auto;" onclick="window.location.href='dashboard.php';" value="Отмена" />       
                    </div>
                </form>
                <?php
            }
        } else {
            echo "<div class='form'>
                <h3>Извините, произошла ошибка.</h3><br/>
                <p class='link'>Попробуйте <a href='dashboard.php'>Вернуться к списку презентаций</a> ещё раз.</p>
                </div>";
        }
    }
    // Была нажата кнопка Добавить презентацию
    else{
        if(isset($_POST["submit"])){
            $title = stripslashes($_REQUEST['title']);
            $title = mysqli_real_escape_string($dbCon, $title);
            $user = $_SESSION["user_id"];
            $description = stripslashes($_REQUEST['description']);
            $description = mysqli_real_escape_string($dbCon, $description);
    
            // Путь к загружаемому файлу
            $targetDir = "uploads/".$_SESSION['login']."/";
            if (!file_exists($targetDir)) mkdir($targetDir, 0777, true);  // Создаем директорию если её изначально не было
            $fileName = basename($_FILES["file"]["name"]);
            $targetFilePath = $targetDir . $fileName;
            $fileType = pathinfo($targetFilePath,PATHINFO_EXTENSION);
    
            if(!empty($_FILES["file"]["name"])){
                // Загрузка файла изображения на сервер
                if(move_uploaded_file($_FILES["file"]["tmp_name"], $targetFilePath)){
                    // Загрузка имя файла изображения в базу данных
                    $query = "INSERT INTO presentations (title, user, description, image)
                              VALUES ('$title', '$user', '$description', '$targetFilePath')";
                    $result = mysqli_query($dbCon, $query);
                    if($result){
                        echo "<div class='form'>
                              <h3>Презентация успешно создана.</h3><br/>
                              <p class='link'><a href='dashboard.php'>Вернуться к списку</a></p>
                              </div>";
                        }else{
                            echo "<div class='form'>
                                  <h3>Извините, при добавлении презентации в базу данных произошла ошибка.</h3><br/>
                                  <p class='link'>Попробуйте <a href='presentation.php'>создать презентацию</a> ещё раз.</p>
                                  </div>";
                    } 
                }else{
                    echo "<div class='form'>
                          <h3>Извините, при загрузке файла на сервер произошла ошибка.</h3><br/>
                          <p class='link'>Попробуйте <a href='presentation.php'>создать презентацию</a> ещё раз.</p>
                          </div>";
                }
            }
            else{
                echo "<div class='form'>
                     <h3>Пожалуйста выберите титульное изображение для загрузки.</h3><br/>
                     <p class='link'>Попробуйте <a href='presentation.php'>создать презентацию</a> ещё раз.</p>
                     </div>";
            }
        } else {
        ?>    
            <form class="form" method="post" enctype="multipart/form-data">
                <h3>Создание новой презентации</h3>
                <input type="text" class="input-field" name="title" required placeholder="Название" autofocus="true"/>
                <textarea class="input-field" name="description" required placeholder="Описание"></textarea>
                <label>Выберите титульное изображение:</label>
                <input type="file" name="file" accept="image/*" onchange="imagePreview(this);">	
                <div id="preview" src="#" style="margin: 25px auto; text-align: center;"></div>
                <div style="display: flex; flex-direction: row;">
                    <input type="submit" class="button" style="margin: auto 0;" name="submit" value="Создать презентацию" />
                    <input type="button" class="logout-button" style="margin-left: auto;" onclick="window.location.href='dashboard.php';" value="Отмена" />       
                </div>
            </form>       
        <?php
        }
    }
?> 
</body>
</html>

<script src="https://snipp.ru/cdn/jquery/2.1.1/jquery.min.js"></script>
<script type="text/javascript">
    function imagePreview(fileInput) {
        if (fileInput.files && fileInput.files[0]) {
            var fileReader = new FileReader();
            fileReader.onload = function (event) {
                $('#preview').html('<img src="'+event.target.result+'" width="300" height="auto"/>');
            };
            fileReader.readAsDataURL(fileInput.files[0]);
        }
    }
</script>