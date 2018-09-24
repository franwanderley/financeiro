package com.algaworks.financeiro.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;

import org.omnifaces.util.Messages;

import com.algaworks.financeiro.model.Lancamento;
import com.algaworks.financeiro.model.Pessoa;
import com.algaworks.financeiro.model.TipoLancamento;
import com.algaworks.financeiro.repository.DAO;
import com.algaworks.financeiro.service.CadastroLAncamento;
import com.algaworks.financeiro.service.NegocioException;

@ManagedBean
@ViewScoped
public class CadastroLancamentoBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Lancamento lancamento = new Lancamento(); 
	CadastroLAncamento cadastro = new CadastroLAncamento();
	
	@PostConstruct
	public void init() {
		if(lancamento == null)
		lancamento = new Lancamento();
	}

	public Lancamento getLancamento() {
		return lancamento;
	}
	
	public void setLancamento(Lancamento lancamento) {
		this.lancamento = lancamento;
	}
	
	
	public List<Pessoa> getPessoas(){
		return new DAO<>(Pessoa.class).listaTodos();
	}
	
	public void salvar() {
		
		
		try {
			cadastro.salvar(lancamento);
			Messages.addGlobalInfo("Lançamento salvo com sucesso");
			
		} catch (NegocioException e) {
			Messages.addGlobalError(e.getMessage());
		}
		
		lancamento = new Lancamento();
	}
	
	public void dataVencimentoAlterada(AjaxBehaviorEvent event) {
		if(lancamento.getDataPagamento() == null)
			lancamento.setDataPagamento(lancamento.getDataVencimento());
	}
	
	public List<String> pesquisarDescricoes(String descricao){
		return new DAO<>(Lancamento.class).descricoesQueContem(descricao);
	}
	
	public TipoLancamento[] getTiposLancamento() {
		return TipoLancamento.values();
	}
	
	public String Carregar(Lancamento lancamento) {
		this.lancamento = lancamento;
		return "cadastroLancamento";
	}

}
