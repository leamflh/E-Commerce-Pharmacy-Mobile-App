<?php 
 
    require 'DbConnect.php';
if($_POST["user_name"]!='' and $_POST["password"]!=''){
$user_name=$_POST["user_name"];
$user_pass=$_POST["password"];



$query="SELECT `password` FROM `client` WHERE `email_client`='".$user_name."' ";
$result=mysqli_query($conn,$query);
$passwordHash=mysqli_fetch_assoc($result);
   


$mysql_query="select * from client where email_client like '$user_name' ";
$result2=mysqli_query($conn,$mysql_query);
 
if(mysqli_num_rows($result2)==1 && password_verify($user_pass,$passwordHash["password"]))
echo 'Login success';

else
echo 'Login not success .';
}
else echo 'Missing informations';
mysqli_close($conn);
?>
