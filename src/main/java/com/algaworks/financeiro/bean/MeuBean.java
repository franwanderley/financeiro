package com.algaworks.financeiro.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class MeuBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String nome;
	private int qtdcaractere;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	public int getQtdcaractere() {
		return qtdcaractere;
	}

	public void setQtdcaractere(int qtdcaractere) {
		this.qtdcaractere = qtdcaractere;
	}

	public void transformar() {
		nome = nome.toUpperCase();
		qtdcaractere = nome.length();
	}
}
