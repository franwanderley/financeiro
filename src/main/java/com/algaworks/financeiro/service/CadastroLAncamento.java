package com.algaworks.financeiro.service;

import java.io.Serializable;
import java.util.Date;

import javax.transaction.Transactional;

import com.algaworks.financeiro.model.Lancamento;
import com.algaworks.financeiro.repository.DAO;

public class CadastroLAncamento implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public void salvar(Lancamento lancamento) throws NegocioException {
		if(lancamento.getDataPagamento() != null && lancamento.getDataPagamento().after(new Date())) {
			throw new NegocioException("Data de pagamento não pode ser uma data futura!");
		}
		if(lancamento.getId() == null)
			new DAO<>(Lancamento.class).adiciona(lancamento);
		else
			new DAO<>(Lancamento.class).atualiza(lancamento);
	}

	@Transactional
	public void remover(Lancamento lancamento) throws NegocioException{
		lancamento = new DAO<>(Lancamento.class).buscaPorId(lancamento.getId());
		
		if(lancamento.getDataPagamento() != null)
			throw new NegocioException("Não é possivel excluir um lançamento pago!");
		
		new DAO<>(Lancamento.class).remove(lancamento);
	}
}
