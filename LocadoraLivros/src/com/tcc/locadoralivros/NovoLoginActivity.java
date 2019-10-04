package com.tcc.locadoralivros;

import java.util.List;

import com.google.gson.GsonBuilder;
import com.tcc.logica.RestAPI;
import com.tcc.logica.Usuario;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NovoLoginActivity extends BaseActivity implements Callback<List<Usuario>>
{
	//Pega as variaveis do layout
	@Bind(R.id.edtNome)
	EditText edtNome;
	
	@Bind(R.id.edtEmail)
	EditText edtEmail;
	
	@Bind(R.id.edtSenha)
	EditText edtSenha;
	
	@Bind(R.id.edtTelefone)
	EditText edtTelefone;
	
	@Bind(R.id.edtNovSenha)
	EditText edtNovSenha;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_novo_login);
		
		ButterKnife.bind(this);
		
		dialogo = new ProgressDialog(this);
	}
	
	@Override
	public void onBackPressed() 
	{
		vaiPara(LoginActivity.class, null);
	}
	
	public void clickOK(View v)
	{
		//Testa se os campos tem alguma coisa
		if(edtEmail.getText().length() == 0 || edtSenha.getText().length() == 0 ||
			edtNome.getText().length() == 0 || edtTelefone.getText().length() == 0 ||
			 edtNovSenha.getText().length() == 0)
		{
			Toast.makeText(this, "Preencha todos os campos.", Toast.LENGTH_SHORT).show();
			return;
		}
			
		//Testa as senha
		if(edtSenha.getText().toString().equals(edtNovSenha.getText().toString()) == false)
		{
			Toast.makeText(this, "As senhas nao conferem.", Toast.LENGTH_SHORT).show();
			return;
		}
		
		//Cria o login e retorna o id do novo usuario
		adicionarBanco();
	}
	
	void adicionarBanco()
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
		
		Call<List<Usuario>> call = api.novoLogin("\"" + edtNome.getText().toString() + "\"", 
												"\"" + edtTelefone.getText().toString() + "\"", "\"" + edtEmail.getText().toString() + 
												"\"", "\"" + edtSenha.getText().toString() + "\"");
		
		call.enqueue(this);
		
		mostrarDialogoCarregando();
	}
	
	void acessarSistema()
	{
		//Salva o id do usuario registrado e manda pra proxima tela
		vaiPara(ListaActivity.class, null);
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
			BaseActivity.usu = res.body().get(0);
			acessarSistema();
		}
		else
		{
			Toast.makeText(this, "Usuario nao pode ser criado.", Toast.LENGTH_LONG).show();
		}
	}
}
