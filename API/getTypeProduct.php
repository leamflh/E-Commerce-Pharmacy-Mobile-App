<?php

require 'DbConnect.php';


   $query="SELECT * FROM `type_product` ";
   $result=mysqli_query($conn,$query);
   mysqli_close($conn);

   $emparray = array();
   while($row =mysqli_fetch_assoc($result))
   {
       $emparray[] =array('category'=> $row['Description_TypeProduct'] , 'image' => $row['image_typeProduct']);
   }

   echo json_encode($emparray);
  
 

 ?>