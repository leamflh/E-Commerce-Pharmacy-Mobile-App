<?php

include('DbConnect.php');

function checkOrderline($var1,$var2){
  
    $query="SELECT count(*) as nbr FROM `orderline` WHERE `id_ProductFk`='$var1' AND `id_orderFk`='$var2' ";
    $result=mysqli_query($conn,$query);
    mysqli_close($conn);
    return $result;
 }
 function updateOrderline($var1,$var2,$var3,$var4){
    
    $query="UPDATE `orderline` SET `qty_orderline`='$var1',`subtotal`='$var2' WHERE `id_productFk`='$var3' AND `id_orderFk`='$var4'";
    $result=mysqli_query($conn,$query);
    mysqli_close($conn);
    return $result;
 }
 function addOrderline($var1,$var2,$var3,$var4){
   
    $query="INSERT INTO `orderline` (`qty_orderline`, `subtotal`, `id_productFk`, `id_orderFk`) VALUES ('$var1', '$var2', '$var3', '$var4')";
    $result=mysqli_query($conn,$query);
    mysqli_close($conn);
     return $result;

  }


?>