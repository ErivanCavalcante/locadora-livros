<?php
//Defines usados na classe
define( 'HOST', 'mysql.hostinger.com.br' );
define( 'USER', 'u392918790_admin' );
define( 'PASS', 'dream007' );
define( 'DB', 'u392918790_loca' );

/**
 * TODO Auto-generated comment.
 */
class BancoMysql 
{
  /**
   * TODO Auto-generated comment.
   */
  private $con;

  public function __construct()
  {
  	$this->IniciarConexao();
  }
  
  public function __destruct()
  {
  	$this->FinalizarConexao();
  }
  /**
   * TODO Auto-generated comment.
   */
  public function Login($email, $senha) 
  {
  	//Procura na tablea login
  	$sql = "SELECT id, nome, telefone, email FROM Usuarios WHERE email = $email AND senha = $senha";
  		
  	$res = mysqli_query( $this->con, $sql ) or die( "Erro ao tentar o query " . mysqli_error( $this->con ) );
  		
  	if( mysqli_num_rows( $res ) == 0 )
  	{
  		http_response_code( 404 );
  		return ;
  	}
  		
  	$this->EviarResposta($res);
  }

  /**
   * TODO Auto-generated comment.
   */
  public function NovoLogin($nome, $telefone, $email, $senha) 
  {
  	//Procura na tablea login
  	$sql = "SELECT id FROM Usuarios WHERE email = $email";
  	
  	$res = mysqli_query( $this->con, $sql );
  	
  	if(mysqli_num_rows($res) > 0)
  	{
  		http_response_code(404);
  		die("O usuario ja existe");
  	}
  	
  	$sql = "INSERT INTO Usuarios ( nome, telefone, email, senha ) VALUES ( $nome, $telefone, $email, $senha )";
  		
  	$res = mysqli_query( $this->con, $sql ) or die( "Erro ao tentar o query " . mysqli_error( $this->con ) );
  	
  	if($res == false)
  	{
  		http_response_code(404);
  		die("Erro ao criar o novo login");
  	}
  	
  	$this->Login($email, $senha);
  }

  /**
   * TODO Auto-generated comment.
   */
  public function ListarLivros() 
  {
  	//Procura na tablea roupas
  	$sql = "SELECT id, nome, autor, valor, disponivel FROM Livros WHERE disponivel = true";
  	
  	$res = mysqli_query( $this->con, $sql ) or die( "Erro ao tentar o query " . mysqli_error( $this->con ) );
  	
  	$this->EviarResposta($res);
  }

  /**
   * TODO Auto-generated comment.
   */
  public function CadastrarLivro($id_dono, $nome, $autor, $sinopse, $valor, $nota) 
  {
  	//Procura na tablea login
  	$sql = "INSERT INTO Livros ( nome, autor, sinopse, id_dono, valor, nota, disponivel ) VALUES ( $nome, $autor, $sinopse, $id_dono, $valor, $nota, true )";
  	
  	$res = mysqli_query( $this->con, $sql ) or die( "Erro ao tentar o query " . mysqli_error( $this->con ) );
  		
  	http_response_code(200);
  }

  /**
   * TODO Auto-generated comment.
   */
  public function RedisponibilizarLivro($id_dono, $id_livro) 
  {
  	//Procura na tablea login
  	$sql = "UPDATE Livros SET disponivel = true WHERE id = $id_livro";
  	
  	$res = mysqli_query( $this->con, $sql ) or die( "Erro ao tentar o query " . mysqli_error( $this->con ) );
  	
  	if($res == false)
  	{
  		http_response_code( 404 );
  		return ;
  	}
  	
  	http_response_code(200);
  }

  /**
   * TODO Auto-generated comment.
   */
  public function IndisponibilizarLivro($id_dono, $id_livro) 
  {
  	//Procura na tablea login
  	$sql = "UPDATE Livros SET disponivel = false WHERE id = $id_livro";
  	 
  	$res = mysqli_query( $this->con, $sql ) or die( "Erro ao tentar o query " . mysqli_error( $this->con ) );
  	 
  	if($res == false)
  	{
  		http_response_code( 404 );
  		return ;
  	}
  	 
  	http_response_code(200);
  }

  /**
   * TODO Auto-generated comment.
   */
  public function PegarDadosPessoais($id_dono) 
  {
  	//Procura na tablea login
  	$sql = "SELECT * FROM Usuarios WHERE id = $id_dono";
  	 
  	$res = mysqli_query( $this->con, $sql ) or die( "Erro ao tentar o query " . mysqli_error( $this->con ) );
  	
  	$this->EviarResposta($res);
  }

  /**
   * TODO Auto-generated comment.
   */
  public function ListarLivrosUsuario($id_dono) 
  {
  	//Procura na tablea login
  	$sql = "SELECT * FROM Livros WHERE id_dono = $id_dono";
  	
  	$res = mysqli_query( $this->con, $sql ) or die( "Erro ao tentar o query " . mysqli_error( $this->con ) );
  	 
  	$this->EviarResposta($res);
  }

  /**
   * TODO Auto-generated comment.
   */
  public function PegarDadosLivro($id_livro) 
  {
  	//Procura na tablea login
  	$sql = "SELECT * FROM Livros WHERE id = $id_livro";
  	 
  	$res = mysqli_query( $this->con, $sql ) or die( "Erro ao tentar o query " . mysqli_error( $this->con ) );
  	
  	$this->EviarResposta($res);
  }

  public function DeletarLivro($id_livro)
  {
  	$sql = "DELETE FROM Livros WHERE id = $id_livro";
  	
  	$res = mysqli_query( $this->con, $sql );
  	
  	if($res == false)
  	{
  		http_response_code(404);
  		return;
  	}
  	
  	http_response_code(200);
  }
  
  /**
   * TODO Auto-generated comment.
   */
  public function IniciarConexao() 
  {
  	//Abre uma conexao
  	$this->con = mysqli_connect( HOST, USER, PASS, DB ) or die( "Erro ao criar a conexao " . mysqli_error( $this->con ) );
  }

  /**
   * TODO Auto-generated comment.
   */
  public function FinalizarConexao() 
  {
  	if( $this->con != NULL )
  		mysqli_close( $this->con );
  }

  /**
   * TODO Auto-generated comment.
   */
  public function EviarResposta($resultado) 
  {
  	$arraySql = array();
  		
  	while( $row = mysqli_fetch_assoc( $resultado ) )
  	{
  		$arraySql[] = $row;
  	}
  	
  	$str = json_encode( $arraySql );
  	
  	print $str;
  }
}
