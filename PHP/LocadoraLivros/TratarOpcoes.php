<?php
require_once 'BancoMysql.php';

/**
 * Trata todas as opcoes vinda da url
 * 
 */
class TratarOpcoes 
{
  /**
   * Conexao com o banco de dados
   */
  private $con;

  public function __construct() 
  {
  	$this->con = new BancoMysql;
  }
  
  /**
   * TODO Auto-generated comment.
   */
  public function TratarOpcao($op) 
  {
  	switch ($op) 
  	{
  		case 1:
  			$this->Login();
  			break;
  		case 2:
  			$this->NovoLogin();
  			break;
  		case 3:
  			$this->ListarLivros();
  			break;
  		case 4:
  			$this->CadastrarLivros();
  			break;
  		case 5:
  			$this->RedisponibilizarLivro();
  			break;
  		case 6:
  			$this->IndisponibilizarLivro();
  			break;
  		case 7:
  			$this->PegarDadosPessoais();
  			break;
  		case 8:
  			$this->ListarLivrosUsuario();
  			break;
  		case 9:
  			$this->PegarDadosLivro();
  			break;
  		case 10:
  			$this->DeletarLivro();
  			break;
  		default:
  			http_response_code( 404 );
  			break;
  	}
  }

  /**
   * TODO Auto-generated comment.
   */
  public function Login() 
  {
  	$email = $_GET[ 'email' ];
  	$senha = $_GET[ 'senha' ];
  	
  	$this->con->Login( $email, $senha );
  }

  /**
   * TODO Auto-generated comment.
   */
  public function NovoLogin() 
  {
  	$this->con->NovoLogin( $_GET[ 'nome' ], $_GET[ 'telefone' ], $_GET[ 'email' ], $_GET[ 'senha' ] );
  }

  /**
   * TODO Auto-generated comment.
   */
  public function ListarLivros() 
  {
  	$this->con->ListarLivros();
  }

  /**
   * TODO Auto-generated comment.
   */
  public function CadastrarLivros() 
  {
  	$this->con->CadastrarLivro( $_GET[ 'id_dono' ], $_GET[ 'nome' ], $_GET[ 'autor' ], $_GET[ 'sinopse' ], $_GET[ 'valor' ], $_GET['nota'] );
  }

  /**
   * TODO Auto-generated comment.
   */
  public function RedisponibilizarLivro() 
  {
  	$this->con->RedisponibilizarLivro(null, $_GET[ 'id_livro' ]);
  }

  /**
   * TODO Auto-generated comment.
   */
  public function IndisponibilizarLivro() 
  {
  	$this->con->IndisponibilizarLivro(null, $_GET[ 'id_livro' ]);
  }

  /**
   * TODO Auto-generated comment.
   */
  public function PegarDadosPessoais() 
  {
  	$this->con->PegarDadosPessoais($_GET[ 'id_dono' ]);
  }

  /**
   * TODO Auto-generated comment.
   */
  public function ListarLivrosUsuario() 
  {
  	$this->con->ListarLivrosUsuario($_GET[ 'id_dono' ]);
  }

  /**
   * TODO Auto-generated comment.
   */
  public function PegarDadosLivro() 
  {
  	$this->con->PegarDadosLivro($_GET[ 'id_livro' ]);
  }
  
  public function DeletarLivro()
  {
  	$this->con->DeletarLivro($_GET[ 'id_livro' ]);
  }
}
