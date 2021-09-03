<?php
require 'DbConnect.php';

if($_POST["name"]!='' and $_POST["idOrder"]!=''){



$idOrder=$_POST['idOrder'];
$name=$_POST['name'];


$query1="select * from product where name_product like '$name' ";
$result1=mysqli_query($conn,$query1);
$res1=mysqli_fetch_assoc($result1);
$id=$res1['Id_Product'];

$query="DELETE FROM `orderline` WHERE `id_productFk`='$id' AND `id_orderFk`='$idOrder';";
$result=mysqli_query($conn,$query);
mysqli_close($conn);
echo 'Product removed successfully !';
}
else echo 'Missing Information';

?>