<?php
    $con = mysqli_connect('localhost','root','','somras');
    if ($con){
 $user = $_POST['username'];
$addr = $_POST['addr'];
$city = $_POST['city'];
$pass = $_POST['pass'];
$email = $_POST['email'];
$phone = $_POST['phone'];


       
        
        $q ="Insert into s_customer (c_name , c_addr , c_city , c_pass , c_email , c_mob) values('" .$user."','".$addr."','".$city."','".$pass."','".$email."','".$phone."') ";
        // ('John', 'Doe','xyz','231132','john@example.com','78945')
       if(mysqli_query($con,$q))
        {
            echo "Success";

        }
        else{
           echo "Error";
        }

   }
    else{
        echo "connetion failed";
    }