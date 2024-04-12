<?php
echo "<h1>pooja</h1><img src='http://192.168.100.243/bca6/images/a1.jpg' width=100 height=100>";
// header('Content-Type: application/json');

$data=array();
$con=mysqli_connect('localhost','root','','crud');
if($con)
{
    
        $id=$_GET['id'];
        $q="select * from emp where id=".$id;
        // echo "qquery=".$q;
    $rs=mysqli_query($con,$q);

    if(mysqli_num_rows($rs)!=0)
    {
        $i=0;
        while($rec=mysqli_fetch_assoc($rs))
        {
            $data[$i]=$rec;
            
            $i++;
        }

        echo json_encode(array("data1"=>$data));
        
        
    }
    else{
        echo ("");  

    }
    
    }

?>
    