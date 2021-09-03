<?php

require 'DbConnect.php';

if($_POST["idOrder"]!=''){
$idOrder=$_POST['idOrder'];


// bdna njib l oderlines taba3 l order
    $query="SELECT * FROM `orderline` WHERE `id_orderFk`=$idOrder";
    $result=mysqli_query($conn,$query);
    $sum=0;

    
    if(mysqli_num_rows($result)>0){

  
        while($row=mysqli_fetch_assoc($result))
        {
         $id=$row['id_productFk'];
         $quantity=$row['qty_orderline'];
         $price=$row['subtotal'];
         $sum=$sum+$price;
         
         $query2="SELECT * FROM `product` WHERE `Id_Product`='$id' ";
         $result2=mysqli_query($conn,$query2);
      
          while($array=mysqli_fetch_assoc($result2)){
                $name=$array['name_product'];
                $image=$array['Description_Product'];
               
          
           
     
     
     
     
         $emparray[] =array('name'=> $name, 
         'image' => $image, 'price'=> intval($price),
      'quantity'=> $quantity, 'total' => intval($sum));
     }}
         
     
     echo json_encode($emparray);
       
     }
     else
     
     echo "No items".$idOrder;
    }
    else echo 'Missing infromation !';

?>