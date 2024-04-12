
<?php
header('Content-Type: application/json');


    $con = mysqli_connect('localhost','root','','somras');
    if ($con){
        $id = $_POST['id'];

        $q ="SELECT s_orderdetail.p_id ,s_orderdetail.o_qty , s_orderdetail.o_amt , s_product.p_name , s_product.p_img , s_product.p_category FROM s_orderdetail,s_product WHERE s_orderdetail.o_id=$id AND s_product.p_id=s_orderdetail.p_id";

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
    else{
        echo "connecction failed";
    }
    