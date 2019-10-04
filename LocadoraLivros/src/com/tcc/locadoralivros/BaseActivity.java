package com.tcc.locadoralivros;

import com.tcc.logica.Usuario;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

public class BaseActivity extends Activity
{
	//Usuario atualmente logado no sistema
	public static Usuario usu = null;
	
	public static String ip = "indieappsbr.16mb.com";
	
	//Dialogo do progresso
	protected ProgressDialog dialogo;
	
	void vaiPara(Class<?> para, Bundle data)
	{
		Intent it = new Intent(this, para);
		
		if(data != null)
			it.putExtras(data);
		
		startActivity(it);
		finish();
	}
	
	protected void mostrarDialogoCarregando()
	{
		dialogo.setCancelable(false);
		dialogo.setMessage("Aguarde...");
		dialogo.setIndeterminate(true);
		dialogo.show();
	}
	
	protected void removerDialogoCarregando()
	{
		dialogo.dismiss();
	}
}
