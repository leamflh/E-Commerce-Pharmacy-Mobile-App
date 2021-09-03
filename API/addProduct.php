<?php
require 'DbConnect.php';

if($_POST["nameProduct"]!='' and $_POST["quantity"]!='' and $_POST["idOrder"]!=''){
$name=$_POST['nameProduct'];
$quantity=intval($_POST['quantity']);
$idOrder=$_POST['idOrder'];


$query1="select * from product where name_product like '$name' ";
$result1=mysqli_query($conn,$query1);
$res1=mysqli_fetch_assoc($result1);
$id=$res1['Id_Product'];
$price=$res1['Price_Product'];


$subtotal=$quantity*$price;
$sub=intval($subtotal);



$query2="SELECT count(*) as nbr FROM `orderline` WHERE `id_ProductFk`='$id' AND `id_orderFk`='$idOrder' ";
$result2=mysqli_query($conn,$query2);
$res2=mysqli_fetch_assoc($result2); // ha trj3le array fi b albo bas element wahad , li fio filed esmo nbr;



if($res2['nbr']!=1){ 



$query3="INSERT INTO `orderline` (`qty_orderline`, `subtotal`, `id_productFk`, `id_orderFk`)
 VALUES ('$quantity', '$sub', '$id', '$idOrder')";
$result3=mysqli_query($conn,$query3);
echo 'Added successfully to cart :'.$name.' '. $quantity.'/pcs';

}
else{

    $query4="UPDATE `orderline` SET `qty_orderline`='$quantity',`subtotal`='$sub' WHERE `id_productFk`='$id' AND `id_orderFk`='$idOrder'";
    $result4=mysqli_query($conn,$query4);
    mysqli_close($conn);
    echo 'Cart successfully updated !'.$name.' '.  $quantity.'/pcs';
   
}
}
else echo 'Missing Information';

?>