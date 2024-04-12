<?php
    $con = mysqli_connect('localhost','root','','somras');
    if ($con)
	{
        $cid =$_POST['id'];
        $caddr =$_POST['addr'];
		 $totamt =$_POST['totamt'];
		 // jino,jrate,jqty,jamt


// Get the current date in the default format (Y-m-d)
		$odate = date("d-m-y");


		  
        $q ="INSERT into s_order values (null,'$cid','$odate','$caddr','$totamt','false')";

		// echo $q;
		
        if(mysqli_query($con,$q))
        {
			 $id = mysqli_insert_id($con);
			 //explode(" ", "Geeks for Geeks")
            //echo "Success Order no :".$id; 
			$ino =explode(",",$_POST['jino']);
			$ino=str_replace("["," ",$ino);
			$ino=str_replace("]"," ",$ino);
			
			$irate =explode(",",$_POST['jrate']);
			$irate=str_replace("["," ",$irate);
			$irate=str_replace("]"," ",$irate);
			
			$iqty=explode(",",$_POST['jqty']);
			$iqty=str_replace("["," ",$iqty);
			$iqty=str_replace("]"," ",$iqty);
			
			$iamt=explode(",",$_POST['jamt']);
			$iamt=str_replace("["," ",$iamt);
			$iamt=str_replace("]"," ",$iamt);
			for($i=0;$i<count($ino);$i++)
			{
			// Sanitize and insert data into the database
			$sql="INSERT INTO s_orderdetail values(null,$id,$ino[$i],$iqty[$i],$irate[$i],$iamt[$i])";
			mysqli_query($con,$sql);
            }
             echo "Success".$id;
        }
        else{
            echo "Error";
        }

    }
    else{
        echo "connetion failed";
    }



?>