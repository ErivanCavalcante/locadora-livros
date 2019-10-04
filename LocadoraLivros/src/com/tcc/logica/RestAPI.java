package com.tcc.logica;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RestAPI 
{
	@GET("RestAPI.php?op=1")
	Call<List<Usuario>> login(@Query("email") String email, @Query("senha") String senha);
	
	@GET("RestAPI.php?op=2")
	Call<List<Usuario>> novoLogin(@Query("nome") String nome, @Query("telefone") String telefone, 
									@Query("email") String email, @Query("senha") String senha);
	
	@GET("RestAPI.php?op=3")
	Call<List<Livro>> listarLivros();
	
	@GET("RestAPI.php?op=4")
	Call<Void> cadastrarLivros(@Query("id_dono") Integer id, @Query("nome") String nome, @Query("autor") String autor, 
			@Query("sinopse") String sinopse, @Query("valor") String valor, 
			@Query("nota") String nota);
	
	@GET("RestAPI.php?op=5")
	Call<Void> redisponibilizarLivro(@Query("id_livro") Integer id);
	
	@GET("RestAPI.php?op=6")
	Call<Void> indisponibilizarLivro(@Query("id_livro") Integer id);
	
	@GET("RestAPI.php?op=7")
	Call<List<Usuario>> pegarDadosPessoais(@Query("id_dono") Integer id);
	
	@GET("RestAPI.php?op=8")
	Call<List<Livro>> listarLivrosUsuario(@Query("id_dono") Integer id);
	
	@GET("RestAPI.php?op=9")
	Call<List<Livro>> pegarDadosLivro(@Query("id_livro") Integer id);
	
	@GET("RestAPI.php?op=10")
	Call<Void> deletarLivro(@Query("id_livro") Integer id);
}
