package com.tcc.locadoralivros;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.GsonBuilder;
import com.tcc.logica.Livro;
import com.tcc.logica.RestAPI;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListaLivrosUsuActivity extends BaseActivity implements Callback<List<Livro>>
{
	@Bind(R.id.listLivros)
	ListView listLivros;
	
	@Bind(R.id.txtMsg)
	TextView txtMsg;
	
	//Lista com tds os livros carregados
	ArrayList<Livro> listaLivros = new ArrayList<Livro>();
		
	ListaAdaptador adp;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_lista);
		
		ButterKnife.bind(this);
		
		dialogo = new ProgressDialog(this);
		
		//Seta o adaptador da lista
		adp = new ListaAdaptador(this, R.layout.view_item_lista, listaLivros);
		
		listLivros.setAdapter(adp);
		
		carregarLista();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.lista_livros_usu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) 
	{
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.menu_cadastrarLivro) 
		{
			vaiPara(CadastroLivroActivity.class, null);
			return true;
		}
		if (id == R.id.menu_atualizar) 
		{
			carregarLista();
			return true;
		}
		
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public void onBackPressed() 
	{
		vaiPara(ListaActivity.class, null);
	}
	
	@OnItemClick(R.id.listLivros)
	public void itemSelecionado(final int pos)
	{
		AlertDialog dialog = new AlertDialog.Builder(this)
							 .setTitle("O que deseja fazer?")
							 .setCancelable(true)
							 .setMessage("Escolha uma opcao")
							 .setPositiveButton("Disponivel", new OnClickListener() 
							 {
								@Override
								public void onClick(DialogInterface dialog, int which) 
								{
									RestAPI api = criaRestApi();
									Call<Void> call = api.redisponibilizarLivro(listaLivros.get(pos).id);
									
									call.enqueue(new Callback<Void>() 
									{
										@Override
										public void onResponse(Call<Void> call, Response<Void> res) 
										{
											int r = res.code();
											
											if (r == 200) 
											{
												carregarLista();
											}
										}
										
										@Override
										public void onFailure(Call<Void> call, Throwable arg1) 
										{
										}
									});								
								}
							})
							.setNegativeButton("Deletar", new OnClickListener() 
							{	
								@Override
								public void onClick(DialogInterface dialog, int which) 
								{
									RestAPI api = criaRestApi();
									Call<Void> call = api.deletarLivro(listaLivros.get(pos).id);
									
									call.enqueue(new Callback<Void>() 
									{
										@Override
										public void onResponse(Call<Void> call, Response<Void> res) 
										{
											int r = res.code();
											
											if (r == 200) 
											{
												carregarLista();
											}
										}
										
										@Override
										public void onFailure(Call<Void> call, Throwable arg1) 
										{
										}
									});		
								}
							})
							.setNeutralButton("Indisponivel", new OnClickListener() 
							{
								@Override
								public void onClick(DialogInterface dialog, int which) 
								{
									RestAPI api = criaRestApi();
									Call<Void> call = api.indisponibilizarLivro(listaLivros.get(pos).id);
									
									call.enqueue(new Callback<Void>() 
									{
										@Override
										public void onResponse(Call<Void> call, Response<Void> res) 
										{
											int r = res.code();
											
											if (r == 200) 
											{
												carregarLista();
											}
										}
										
										@Override
										public void onFailure(Call<Void> call, Throwable arg1) 
										{
										}
									});		
								}
							}).create();
		
		dialog.show();
	}
	
	RestAPI criaRestApi()
	{
		HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
		interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
		
		OkHttpClient cliente = new OkHttpClient.Builder().addInterceptor(interceptor).build();
		
		Retrofit retro = new Retrofit.Builder()
							 .baseUrl("http://" + BaseActivity.ip + "/LocadoraLivros/")
							 .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create()))
							 .client(cliente)
							 .build();
		
		return retro.create(RestAPI.class);
	}
	
	void carregarLista()
	{
		RestAPI api = criaRestApi();
		
		Call<List<Livro>> call = api.listarLivrosUsuario(BaseActivity.usu.id);
		
		call.enqueue(this);
		
		mostrarDialogoCarregando();
	}

	@Override
	public void onFailure(Call<List<Livro>> arg0, Throwable arg1) 
	{
		removerDialogoCarregando();
	}

	@Override
	public void onResponse(Call<List<Livro>> call, Response<List<Livro>> res) 
	{
		removerDialogoCarregando();
		
		int r = res.code();
		
		if(r == 200)
		{
			listaLivros.clear();
			listaLivros.addAll(res.body());
			
			if(listaLivros.size() > 0)
				txtMsg.setVisibility(View.INVISIBLE);
			else
				txtMsg.setVisibility(View.VISIBLE);
			
			adp.notifyDataSetChanged();
			listLivros.invalidate();
		}
		
	}
}
