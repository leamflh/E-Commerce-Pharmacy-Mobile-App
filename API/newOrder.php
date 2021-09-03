<?php
require 'DbConnect.php';


if($_POST["client"]!='' and $_POST["date"]!='' ){

$email=$_POST["client"];
$input=$_POST["date"];



$mysql_query="select * from client where email_client like '$email' ";
$result=mysqli_query($conn,$mysql_query);
$res=mysqli_fetch_assoc($result);
$id=$res['id_client'];



$query="INSERT INTO `client_order` (`id_order`, `date_order`, `idClient_order`)
 VALUES (NULL, '$input', '$id');";
$result2=mysqli_query($conn,$query);
$idOrder=mysqli_insert_id($conn);
    mysqli_close($conn);
    echo $idOrder;

}
else echo 'Missing infromation !';

?>