package ar.uba.fi.criaderoxp.domain.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.SimpleExpression;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ar.uba.fi.criaderoxp.domain.model.Sexo;

@Repository
public class SexoRepositoryImpl implements SexoRepository {
	@PersistenceContext
	private EntityManager em;

	@Transactional(readOnly = true)
	public List<Sexo> findAll() {
		Session session = (Session) em.getDelegate();
		Criteria criteria = session.createCriteria(Sexo.class);
		return criteria.list();
	}

	@Transactional(readOnly = true)
	public Sexo getMacho() {
		Session session = (Session) em.getDelegate();
		SimpleExpression restriccion = Property.forName("codigo").eq("macho");
		Criteria criteria = session.createCriteria(Sexo.class).add(restriccion);
		return (Sexo) criteria.uniqueResult();
	}

	@Transactional(readOnly = true)
	public Sexo getHembra() {
		Session session = (Session) em.getDelegate();
		SimpleExpression restriccion = Property.forName("codigo").eq("hembra");
		Criteria criteria = session.createCriteria(Sexo.class).add(restriccion);
		return (Sexo) criteria.uniqueResult();
	}
}
