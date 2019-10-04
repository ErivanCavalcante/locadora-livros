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

public class LoginActivity extends BaseActivity implements Callback<List<Usuario>>
{
	//Pega as variaveis GUIS do layout
	@Bind(R.id.edtEmail)
	EditText edtEmail;
	
	@Bind(R.id.edtSenha)
	EditText edtSenha;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.view_login);
		
		ButterKnife.bind(this);
		
		dialogo = new ProgressDialog(this);
		
		setTitle("Login");
	}
	
	@Override
	public void onBackPressed() 
	{
		finish();
	}
	
	public void clickLogin(View v)
	{
		//Testa se os campos d texto estao preenchidos
		if(edtEmail.getText().length() == 0 || edtSenha.getText().length() == 0)
		{
			Toast.makeText(this, "Preencha todos os campos.", Toast.LENGTH_SHORT).show();
			return;
		}
		
		//Procura no banco o login
		procuraBanco();
	}
	
	public void clickNovoLogin(View v)
	{
		vaiPara(NovoLoginActivity.class, null);
	}
	
	
	void procuraBanco()
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
		
		Call<List<Usuario>> call = api.login("\"" + edtEmail.getText().toString() + 
									"\"", "\"" + edtSenha.getText().toString() + "\"");
		
		call.enqueue(this);
		
		mostrarDialogoCarregando();
	}
	
	void logarSistema()
	{
		//Nao esquecer de criar o BaseActivity.usu
		//Se achou 
		vaiPara(ListaActivity.class, null);
			
		//Toast.makeText(this, "Email ou senha incorretos.", Toast.LENGTH_LONG).show();
		//return;
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
			logarSistema();
		}
		else
		{
			Toast.makeText(this, "Usuario nao existe.", Toast.LENGTH_LONG).show();
		}
	}
	
}
