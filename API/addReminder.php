<?php
require 'conn2.php';

if($_POST["medname"]!='' and $_POST["nb_days"]!='' and $_POST["nb_times"]!=''){
$medname=$_POST["medname"];
$nb_days=$_POST["nb_days"];
$nb_times=$_POST["nb_times"];
$query="INSERT INTO reminder (id,medecine_name,number_of_days,number_of_times) VALUES (NULL,'$medname','$nb_days','$nb_times')";
$result=mysqli_query($conn,$query);
if($result){
    echo "Done";
}
else echo "Something went wrong";
mysqli_close($conn);
}
else 'Missing Infromation';
?>