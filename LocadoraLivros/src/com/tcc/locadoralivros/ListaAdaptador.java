package com.tcc.locadoralivros;

import java.util.ArrayList;

import com.tcc.logica.Livro;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

public class ListaAdaptador extends ListViewAdapter<Livro>
{

	public ListaAdaptador(Context ct, int idResource, ArrayList<Livro> objLista) 
	{
		super(ct, idResource, objLista);
	}

	@Override
	public void onLayoutInflate(View container, int pos) 
	{
		Livro r = lista.get(pos);
		
		TextView nome = (TextView)container.findViewById(R.id.txtNome);
		TextView autor = (TextView)container.findViewById(R.id.txtAutor);
		TextView valor = (TextView)container.findViewById(R.id.txtValor);
		
		nome.setText(r.nome);
		autor.setText(r.autor);
		valor.setText("R$ " + r.valor);
		
		if(r.disponivel != 1)
		{
			container.setBackgroundColor(Color.GRAY);
		}
		else
		{
			container.setBackgroundColor(Color.WHITE);
		}
	}

}
