package com.tcc.locadoralivros;

import java.util.List;

import com.google.gson.GsonBuilder;
import com.tcc.logica.Livro;
import com.tcc.logica.RestAPI;
import com.tcc.logica.Usuario;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DadosPessoaisActivity extends BaseActivity implements Callback<List<Usuario>>
{
	@Bind(R.id.txtNome)
	TextView txtNome;
	
	@Bind(R.id.txtEmail)
	TextView txtEmail;
	
	@Bind(R.id.txtTelefone)
	TextView txtTelefone;
	
	//Dono do livro
	Usuario u;
	
	//Livro atual
	Livro livro;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_dados_pessoais);
		
		ButterKnife.bind(this);
		
		dialogo = new ProgressDialog(this);
		
		//Recupera o livro atual
		Intent it = getIntent();
		if(it != null)
		{
			Bundle b = it.getExtras();
			livro = (Livro)b.getSerializable("livro");
			
			carregarDadosPessoais();
		}
		else
		{
			
		}
		
	}

	@Override
	public void onBackPressed() 
	{
		vaiPara(ListaActivity.class, null);
	}
	
	void carregarDadosPessoais()
	{
		HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
		interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
		
		OkHttpClient cliente = new OkHttpClient.Builder().addInterceptor(interceptor).build();
		
		Retrofit retro = new Retrofit.Builder()
							 .baseUrl("http://" + BaseActivity.ip + "/LocadoraLivros/")
							 .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create()))
							 .client(cliente)
							 .build();
		
		RestAPI api = retro.create(RestAPI.class);
		
		Call<List<Usuario>> call = api.pegarDadosPessoais(livro.id_dono);
		
		call.enqueue(this);
		
		mostrarDialogoCarregando();
	}
	
	void setaDadosPessoais()
	{
		txtNome.append(u.nome);
		txtEmail.append(u.email);
		txtTelefone.append(u.telefone);
	}

	@Override
	public void onFailure(Call<List<Usuario>> arg0, Throwable arg1) 
	{
		removerDialogoCarregando();
	}

	@Override
	public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> res) 
	{
		removerDialogoCarregando();
		
		int r = res.code();
		
		if(r == 200)
		{
			u = res.body().get(0);
			
			setaDadosPessoais();
		}
	}
}
