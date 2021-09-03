<?php 
 
    require 'DbConnect.php';
if($_POST["idOrder"]!=''){
$idOrder=intval($_POST["idOrder"]);

$mysql_query="DELETE FROM `client_order` WHERE `client_order`.`id_order` = $idOrder";
$result=mysqli_query($conn,$mysql_query);
mysqli_close($conn);
echo 'Logout  !';
}
else echo 'Missing infromation';
?>