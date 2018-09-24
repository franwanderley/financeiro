package com.algaworks.financeiro.repository;

import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import com.algaworks.financeiro.util.JpaUtil;

public class DAO<T> extends java.lang.Object {

	private final Class<T> classe;

	public DAO(Class<T> classe) {
		this.classe = classe;
	}

	public List<String> descricoesQueContem(String descricao) {
		EntityManager manager = JpaUtil.getEntityManager();
		TypedQuery<String> query = manager.createQuery(
				"select distinct descricao from Lancamento " + "where upper(descricao) like upper(:descricao)", String.class);
		query.setParameter("descricao", "%" + descricao + "%");
		return query.getResultList();//Serve para o componente autocomplete do primefaces
	}

	public boolean adiciona(T t) {
		EntityManager manager = JpaUtil.getEntityManager();
		// limpa a transição
		manager.clear();

		// consegue a entity manager
		EntityManager em = JpaUtil.getEntityManager();

		// abre transacao
		em.getTransaction().begin();

		try {

			// persiste o objeto
			em.persist(t);

			// commita a transacao
			em.getTransaction().commit();

		} catch (Exception e) {
			em.getTransaction().rollback();

			FacesContext.getCurrentInstance().addMessage("Lancamento", new FacesMessage(e.getMessage()));
			// fecha a entity manager
			em.close();
			return false;
		}

		// fecha a entity manager
		em.close();
		return true;
	}

	public void remove(T t) {
		EntityManager em = JpaUtil.getEntityManager();
		em.getTransaction().begin();
		try {
			em.remove(em.merge(t));
			em.getTransaction().commit();

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("Lancamento", new FacesMessage(e.getMessage()));
		}

		em.close();
	}
	
	public T guarda(T t) {
		EntityManager em = JpaUtil.getEntityManager();
		return em.merge(t);
	}

	public void atualiza(T t) {
		EntityManager em = JpaUtil.getEntityManager();
		em.getTransaction().begin();
		try {

			em.merge(t);// Mescle o estado da entidade dado o atual contexto de persistência.

			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
		}
		em.close();

	}

	public List<T> listaTodos() {
		EntityManager em = JpaUtil.getEntityManager();
		CriteriaQuery<T> query = em.getCriteriaBuilder().createQuery(classe);
		query.select(query.from(classe));

		List<T> lista = em.createQuery(query).getResultList();

		em.close();
		return lista;
	}

	public T buscaPorId(Long id) {
		EntityManager em = JpaUtil.getEntityManager();
		T instancia = em.find(classe, id);
		em.close();
		return instancia;
	}

	public int contaTodos() {
		EntityManager em = JpaUtil.getEntityManager();
		long result = (Long) em.createQuery("select count(n) from login n").getSingleResult();
		em.close();

		return (int) result;
	}

	public List<T> listaTodosPaginada(int firstResult, int maxResults) {
		EntityManager em = JpaUtil.getEntityManager();
		CriteriaQuery<T> query = em.getCriteriaBuilder().createQuery(classe);
		query.select(query.from(classe));

		List<T> lista = em.createQuery(query).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();

		em.close();
		return lista;
	}

}
