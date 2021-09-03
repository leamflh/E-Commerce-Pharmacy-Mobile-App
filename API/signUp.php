<?php 
 
require 'DbConnect.php';

if($_POST["name"]!='' and $_POST["address"]!='' and $_POST["email"]!='' and $_POST["password"]!='' and $_POST["phone"]!=''){

$name=$_POST["name"];
$password=$_POST["password"];
$email=$_POST["email"];
$phone=$_POST["phone"];
$address=$_POST["address"];


$mysql_query="select * from client where email_client like '$email' ";
$result=mysqli_query($conn,$mysql_query);

if(mysqli_num_rows($result)>0){
    echo 'User already register .';
}
else{
   
		$mail=preg_match("/^[a-zA-Z]{1}[a-zA-Z0-9_\.]{3,30}@[a-zA-Z]{3,20}\.[a-zA-Z]{2,10}$/",$email);
		if(!$mail)   
			echo 'Email format is wrong. Please try again.';

			$phoneTest=preg_match("/[0-9]{8}/",$phone);
		if(!$phoneTest)   
			echo 'Phone number format is wrong. Please try again.';

			
			$uppercase = preg_match('@[A-Z]@', $password);
			$lowercase = preg_match('@[a-z]@', $password);
			$number    = preg_match('@[0-9]@',$password);
			if(!$uppercase || !$lowercase || !$number)
				echo 'Uppercase letter and a Number are required in your password.';
              
 if($mail && $uppercase && $lowercase && $number && $phone  ){
       $hashedPass=password_hash($password,PASSWORD_BCRYPT);
		$phoneToInt=intval($phone);

        $query="INSERT INTO `client` (`name_client`, `email_client`, `password`, `phone_client`, `address_client`, `id_client`)
        VALUES ('$name', '$email', '$hashedPass', '$phoneToInt', '$address', NULL);";
        $result=mysqli_query($conn,$query);
      
     mysqli_close($conn);
     echo 'Done';
   
                        }
}
}
else echo 'Missing informations';





?>