<?php
    $con = mysqli_connect('localhost','root','','crud');
    if ($con){
        $data = $_POST['data'];    
        $q ="Delete from emp where id=".$data;
        mysqli_query($con,$q);
        if(mysqli_affected_rows($con)>0)
        {
            echo "Success";

        }
        else
        {
            echo "Error";
        }

    }
    else{
        echo "connecction failed";
    }