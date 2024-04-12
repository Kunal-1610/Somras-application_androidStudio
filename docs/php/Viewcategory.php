
<?php
header('Content-Type: application/json');

    $con = mysqli_connect('localhost','root','','somras');
    if ($con){
        $cat =$_POST['cat'];

        $q ="SELECT * FROM s_product WHERE p_category = '$cat'";
        // echo $q;
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
            echo "Error";
        }

    }
    
    