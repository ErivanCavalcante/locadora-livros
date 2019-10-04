package com.tcc.locadoralivros;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.GsonBuilder;
import com.tcc.logica.Livro;
import com.tcc.logica.RestAPI;

import android.app.ProgressDialog;
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

public class ListaActivity extends BaseActivity implements Callback<List<Livro>>
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
		/*
		//Pega o id do usuario logado
		Intent it = getIntent();
		if(it != null)
		{
			Bundle b = it.getExtras();
			
			usu = (Usuario)b.getSerializable("usu");
			
			//Testa pra ter certeza
			if(usu == null)
			{
				Toast.makeText(this, "Problemas com o usuario Serializado.", Toast.LENGTH_SHORT).show();
				return;
			}
		}
		*/
		
		//Seta o adaptador da lista
		adp = new ListaAdaptador(this, R.layout.view_item_lista, listaLivros);
		
		listLivros.setAdapter(adp);
		
		//carrega a lista de livros
		carregarLista();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_lista, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) 
	{
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.menu_atualizar) 
		{
			carregarLista();
			return true;
		}
		else if (id == R.id.menu_meusLivros) 
		{
			vaiPara(ListaLivrosUsuActivity.class, null);
			return true;
		}
		
		return super.onOptionsItemSelected(item);
	}
	
	@OnItemClick(R.id.listLivros)
	public void clickItemLista(int pos)
	{
		Bundle b = new Bundle();
		b.putSerializable("livro", listaLivros.get(pos));
		
		vaiPara(AlugarActivity.class, b);
	}
	
	void carregarLista()
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
		
		Call<List<Livro>> call = api.listarLivros();
		
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
		}
		
	}
}
