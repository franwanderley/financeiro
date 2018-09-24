package com.algaworks.funcionario.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.algaworks.financeiro.model.Lancamento;
import com.algaworks.financeiro.repository.DAO;

@FacesConverter(forClass = Lancamento.class)
public class LancamentoConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Lancamento retorno = null;
		if (value != null && !"".equals(value)) {
			retorno =new DAO<>(Lancamento.class).buscaPorId(new Long(value));
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Lancamento lancamento = ((Lancamento) value);
			return lancamento.getId() == null ? null : lancamento.getId().toString();
		}
		return null;
	}
}