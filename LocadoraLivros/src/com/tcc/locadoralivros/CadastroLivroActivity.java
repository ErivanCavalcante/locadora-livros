package com.tcc.locadoralivros;

import com.google.gson.GsonBuilder;
import com.tcc.logica.RestAPI;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
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

public class CadastroLivroActivity extends BaseActivity implements Callback<Void>
{
	@Bind(R.id.edtTitulo)
	EditText edtTitulo;
	
	@Bind(R.id.edtAutor)
	EditText edtAutor;
	
	@Bind(R.id.edtSinopse)
	EditText edtSinopse;
	
	@Bind(R.id.edtValor)
	EditText edtValor;
	
	@Bind(R.id.barNota)
	RatingBar barNota;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_cadastro_livro);
		
		ButterKnife.bind(this);
		
		dialogo = new ProgressDialog(this);
	}
	
	@Override
	public void onBackPressed() 
	{
		vaiPara(ListaLivrosUsuActivity.class, null);
	}
	
	public void clickCadastrar(View v)
	{
		if(edtTitulo.getText().length() == 0 || edtAutor.getText().length() == 0 ||
			edtSinopse.getText().length() == 0 || edtValor.getText().length() == 0) 
		{
			Toast.makeText(this, "Preencha todos os campos.", Toast.LENGTH_SHORT).show();
			return;
		}
		
		cadastrarLivro();
	}
	
	void cadastrarLivro()
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
		
		Call<Void> call = api.cadastrarLivros(BaseActivity.usu.id, "\"" + edtTitulo.getText().toString() + "\"", 
													"\"" + edtAutor.getText().toString() + "\"", "\"" + edtSinopse.getText().toString() + "\"",
													"\"" + edtValor.getText().toString() + "\"",
													"\"" + barNota.getRating() + "\"");
		
		call.enqueue(this);
		
		mostrarDialogoCarregando();
	}

	@Override
	public void onFailure(Call<Void> arg0, Throwable t) 
	{
		removerDialogoCarregando();
		
		Toast.makeText(this, t.getMessage(), Toast.LENGTH_LONG).show();
		Log.d("Debug", t.getMessage());
		t.printStackTrace();
	}

	@Override
	public void onResponse(Call<Void> call, Response<Void> res) 
	{
		removerDialogoCarregando();
		
		int r = res.code();
		
		if(r == 200)
		{
			Toast.makeText(this, "Livro cadastrado com sucesso.", Toast.LENGTH_SHORT).show();
		}
		else
		{
			Toast.makeText(this, "Erro ao cadastrar o livro.", Toast.LENGTH_SHORT).show();
		}
		
		vaiPara(ListaLivrosUsuActivity.class, null);
	}
}
