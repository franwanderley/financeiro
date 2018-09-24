package com.algaworks.financeiro.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.html.HtmlCommandButton;
import javax.faces.component.html.HtmlInputText;

@ManagedBean
@ViewScoped
public class NomeBean {
	private String nome;
	private List<String> nomes = new ArrayList<>();
	
	private HtmlInputText inputNome;
	private HtmlCommandButton botaoadicionar;
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public List<String> getNomes() {
		return nomes;
	}
	
	public HtmlInputText getInputNome() {
		return inputNome;
	}
	public void setInputNome(HtmlInputText inputNome) {
		this.inputNome = inputNome;
	}
	public HtmlCommandButton getBotaoadicionar() {
		return botaoadicionar;
	}
	public void setBotaoadicionar(HtmlCommandButton botaoadicionar) {
		this.botaoadicionar = botaoadicionar;
	}
	
	public String adicionar() {
		this.nomes.add(nome);
		if(nomes.size() > 3) {
			this.inputNome.setDisabled(true);
			this.botaoadicionar.setDisabled(true);
			this.botaoadicionar.setValue("Muitos nomes adicionados...");
			return "ola?faces-redirect = true";
		}
		return null;
	}
}
