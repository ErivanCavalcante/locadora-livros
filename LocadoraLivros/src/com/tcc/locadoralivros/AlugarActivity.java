package com.tcc.locadoralivros;

import java.util.List;

import com.google.gson.GsonBuilder;
import com.tcc.logica.Livro;
import com.tcc.logica.RestAPI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
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

public class AlugarActivity extends BaseActivity implements Callback<List<Livro>>
{
	@Bind(R.id.imgLivro)
	ImageView imgLivro;
	
	@Bind(R.id.txtLivro)
	TextView txtLivro;
	
	@Bind(R.id.txtAutor)
	TextView txtAutor;
	
	@Bind(R.id.txtSinopse)
	TextView txtSinopse;
	
	//Livro q esta sendo tratado
	Livro livro;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_alugar);
		
		ButterKnife.bind(this);
		
		//Pega o id do usuario logado
		Intent it = getIntent();
		if(it != null)
		{
			Bundle b = it.getExtras();
			
			//O sistema deletou ele
			if(BaseActivity.usu == null)
			{
				vaiPara(LoginActivity.class, null);
				return;
			}
			
			livro = (Livro)b.getSerializable("livro");
			
			pegarDadosLivro();
		}
	}
	
	@Override
	public void onBackPressed() 
	{
		vaiPara(ListaActivity.class, null);
	}
	
	public void clickAlugar(View v)
	{
		Bundle b = new Bundle();
		b.putSerializable("livro", livro);
		vaiPara(DadosPessoaisActivity.class, b);
	}
	
	void pegarDadosLivro()
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
		
		Call<List<Livro>> call = api.pegarDadosLivro(livro.id);
		
		call.enqueue(this);
	}

	@Override
	public void onFailure(Call<List<Livro>> arg0, Throwable arg1) 
	{
		
	}

	@Override
	public void onResponse(Call<List<Livro>> call, Response<List<Livro>> res) 
	{
		int r = res.code();
		
		if(r == 200)
		{
			livro = res.body().get(0);
			
			txtAutor.setText(livro.autor);
			txtLivro.setText(livro.nome);
			txtSinopse.setText(livro.sinopse);
		}
	}
}
