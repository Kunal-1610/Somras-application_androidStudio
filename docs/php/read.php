<?php

header('Content-Type: application/json');
$data=array();
$con=mysqli_connect('localhost','root','','somras');
if($con){
    $q="select * from s_product";
    $rs=mysqli_query($con,$q);
    if(mysqli_num_rows($rs)!=0)
    {
        $i=0;
        while($rec=mysqli_fetch_assoc($rs))
        {
            $data[$i]=$rec;
            
            $i++;
        }

        echo json_encode($data);
        
        
    }
    else{
        echo json_encode("error");  

    }
    
}