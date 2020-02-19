<?php

$Name = $_GET["name"];

$fileName = "arduinoData.txt";


if (file_exists($fileName)) 
{
	echo "The file exists!";
} 
else 
{
	echo "Nope";
}

if (is_writable($fileName)) 
{
	echo "The file is writable\n!";
} 
else 
{
	echo "nope";
}


//$myfile = fopen($fileName, "w") or die("unable to open file!");
//fwrite("Work\n");


//fwrite($myfile, $Name);
//fclose($myfile);

//print("<h2>Hello PHP world!</h2>");
//printf($_POST);
//$json_string = json_encode($_POST);

//file_put_contents($dir.'/test.txt', $Name);
//print($Name);

//print($json_string);
//print_r($_REQUEST);
file_put_contents("arduinoData.txt", $Name);


?>