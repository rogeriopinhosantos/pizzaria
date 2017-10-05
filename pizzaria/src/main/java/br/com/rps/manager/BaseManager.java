package br.com.rps.manager;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import br.com.rps.lib.entity.IBaseEntity;
import br.com.rps.lib.filtro.FiltroBase;

public abstract class BaseManager<T extends IBaseEntity> implements Serializable {

	private Class<T> entityClass;
	private Session session;

	@PostConstruct
	public void ManagerBase() {
		entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	private Session getSession() {
//		if (session == null) {
			session = entityManager.unwrap(Session.class);
//		}
		return session;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@PersistenceContext
	EntityManager entityManager;

	public List<T> buscaTodos() {
		Criteria criteria = getSession().createCriteria(entityClass);
		return criteria.list();
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	@SuppressWarnings("unchecked")
	public List<T> localizarPorCriteria(Criterion... criterion) {
		Criteria crit = getSession().createCriteria(entityClass);
		if (criterion != null) {
			for (Criterion c : criterion) {
				crit.add(c);
			}
		}
		return crit.list();
	}
	
	public Criteria retornarCriteria(Criterion... criterion) {
		Criteria crit = getSession().createCriteria(entityClass);
		if (criterion != null && criterion.length > 0) {
			for (Criterion c : criterion) {
				if (c != null) {
					crit.add(c);
				}
			}
		}
		return crit;
	}
	
	public List<T> filtrados(FiltroBase<T> filtro) {
		Criteria criteria = criarCriteriaParaFiltro(filtro);
		
		criteria.setFirstResult(filtro.getPrimeiroRegistro());
		criteria.setMaxResults(filtro.getQuantidadeRegistros());
		
		if (filtro.isAscendente() && filtro.getPropriedadeOrdenacao() != null) {
			criteria.addOrder(Order.asc(filtro.getPropriedadeOrdenacao()));
		} else if (filtro.getPropriedadeOrdenacao() != null) {
			criteria.addOrder(Order.desc(filtro.getPropriedadeOrdenacao()));
		}
		
		return criteria.list();
	}
	
	public int quantidadeFiltrados(FiltroBase<T> filtro) {
		Criteria criteria = criarCriteriaParaFiltro(filtro);
		
		criteria.setProjection(Projections.rowCount());
		
		return ((Number) criteria.uniqueResult()).intValue();
	}
	
	private Criteria criarCriteriaParaFiltro(FiltroBase<T> filtro) {
		Criterion ilike = null;
		if (StringUtils.isNotEmpty(filtro.getDescricao())) {
			ilike = Restrictions.ilike("descricao", filtro.getDescricao(), MatchMode.ANYWHERE);
		}
		return retornarCriteria(ilike);
		
	}

}
