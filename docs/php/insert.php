<?php
    $con = mysqli_connect('localhost','root','','crud');
    if ($con){
        $data = $_POST['data'];    
        $q ="Insert into emp (e_name) values ('".$data."')";
        if(mysqli_query($con,$q))
        {
            echo "Success";

        }
        else{
            echo "Error";
        }

    }
    else{
        echo "connecction failed";
    }