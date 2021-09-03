<?php
require 'conn2.php';


$query="SELECT * FROM reminder";
$result=mysqli_query($conn,$query);
$emparray = array();
while($row =mysqli_fetch_assoc($result))
{
   $emparray[] =array('medicine_name'=> $row['medecine_name'],
                     'number_days' => $row['number_of_days'],
                     'number_times' => $row['number_of_times'] );
}
echo json_encode($emparray);
?>
