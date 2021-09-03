<?php

require 'DbConnect.php';
if($_POST["name"]!='' ){
$name=$_POST['name'];

   
    $query1="SELECT `Id_TypeProduct` FROM `type_product` WHERE `Description_TypeProduct`='$name' ";
    $result=mysqli_query($conn,$query1);

    $res=mysqli_fetch_assoc($result);
    $type=$res['Id_TypeProduct'];

    $query2="SELECT * FROM `product` WHERE `Id_Type_Product`='$type' ";
    $result2=mysqli_query($conn,$query2);

    if(mysqli_num_rows($result2)>0){

  
   while($row=mysqli_fetch_assoc($result2))
   {
    $id=$row['Id_Product'];
       
    $query3="SELECT `quantity_Stock` FROM `stock` WHERE `id_Product_Stock`=$id";
    $quantity=mysqli_query($conn,$query3);
    $sum=0;
     while($array=mysqli_fetch_assoc($quantity))
      $sum=$sum+$array['quantity_Stock'];




    $emparray[] =array('name'=> $row['name_product'] , 
    'image' => $row['Description_Product'], 'price'=> $row['Price_Product'],
 'quantity'=> $sum);
}
    

echo json_encode($emparray);
  
}
else

echo "No items";
} 
else echo 'Missing information !';
 ?>