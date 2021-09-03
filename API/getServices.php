<?php

require 'DbConnect.php';


$query="SELECT * FROM `type_service` ";
$result=mysqli_query($conn,$query);
$emparray=array();

while($row=mysqli_fetch_assoc($result))
{
 $name=$row['description_typeService'];
 $price=$row['price'];
 $image=$row['image'];

    

  $emparray[] =array('name'=> $name , 
 'image' => $image, 'price'=> $price );
}
 

echo json_encode($emparray);





?>