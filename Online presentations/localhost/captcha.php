<?php
	session_start();
 
	$random = rand(1, 9).rand(1, 9).rand(1, 9).rand(1, 9);
 
	$_SESSION['captcha'] = $random;
 
	$captcha = imagecreatefromjpeg("img/captcha.jpg");
	$color = imagecolorallocate($captcha, rand(0, 255), rand(0, 255), rand(0, 255));
	$font = realpath('code.otf');
	imagettftext($captcha, 35, 0, rand(30, 150), rand(35, 60), $color, $font, $random );
	imagepng($captcha);
	imagedestroy($captcha);
?>