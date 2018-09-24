package com.algaworks.financeiro.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import org.omnifaces.util.Messages;

import com.algaworks.financeiro.model.Lancamento;
import com.algaworks.financeiro.repository.DAO;
import com.algaworks.financeiro.service.CadastroLAncamento;
import com.algaworks.financeiro.service.NegocioException;

@ManagedBean
@ViewScoped
public class consultaLancamentoBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private List<Lancamento> lancamentos;

	public List<Lancamento> getLancamentos() {
		return lancamentos;
	}

	public void consultar() {
		lancamentos = new DAO<>(Lancamento.class).listaTodos();
	}
	
	public void remover(Lancamento lancamento) {
		CadastroLAncamento cadastro = new CadastroLAncamento();
		
		try {
			cadastro.remover(lancamento);
			this.consultar();
			Messages.addGlobalInfo("Lancamento excluido com sucesso!");
		}catch(NegocioException e) {
			Messages.addGlobalError(e.getMessage());
		}
		lancamento = new Lancamento();
	}

}
