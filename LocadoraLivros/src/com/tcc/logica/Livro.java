package com.tcc.logica;

import java.io.Serializable;

public class Livro implements Serializable
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//Foto
	public int id;
	
	public String autor;
	
	public String nome;
	
	public String sinopse;
	
	public int nota;
	
	public float valor;
	
	//id do dono do livro
	public int id_dono;
	
	public int disponivel;
	
	//public String dataDevolucao;
}
