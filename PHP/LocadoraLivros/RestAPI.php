<?php
	require_once 'TratarOpcoes.php';
	
	$tratar = new TratarOpcoes;
	
	//Testa se a url eh bem formada
	if (isset($_GET['op']) == false) 
	{
		http_response_code(400);
		die("Url mal formada");
	}
	
	$op = filter_var($_GET['op'], FILTER_SANITIZE_NUMBER_INT);
	
	$tratar->TratarOpcao($op);
?>