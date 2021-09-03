<?php
require 'DbConnect.php';

if($_POST["idBill"]!='' and $_POST["total"]!='' and $_POST["date"]!='' and $_POST["idOrder"]!=''){



$idBill=intval($_POST['idBill']);
$price=intval($_POST['total']);
$date=$_POST['date'];
$idOrder=intval($_POST['idOrder']);


    $query="INSERT INTO `bill` (`id_bill`, `price_bill`, `date_bill`, `serviceId_bill`, `orderId_bill`)
     VALUES ('$idBill', '$price', '$date', NULL,'$idOrder')";
    $result=mysqli_query($conn,$query);
   
    
 
    

  
    $query1="SELECT * FROM `orderline` WHERE `id_orderFk`=$idOrder";
    $result1=mysqli_query($conn,$query1);
  





    while($ligne=mysqli_fetch_assoc($result1)){
        $var=$ligne['id_productFk'];
        $query2="SELECT * FROM `stock` WHERE `id_Product_Stock`='$var' ORDER BY `expiryDate_Stock` DESC";
	    $res2=mysqli_query($conn,$query2); //kamen lzm naamoul test eza raddit true or false
       $res=mysqli_fetch_assoc($res2); // we only want the first row
       $newqty=$res['quantity_Stock']-$ligne['qty_orderline'];
        $var2=$res['Id_Stock'];
       $query3="UPDATE `stock` SET `quantity_Stock` = '$newqty' WHERE `stock`.`Id_Stock` = '$var2';";
        $fnsh=mysqli_query($conn,$query3); //kamen lzm naamoul test eza raddit true or false
    	mysqli_close($conn);
	
    
    } 

    echo "Order Successfully submit";
}
else echo 'Missing information !';

?>