package ar.uba.fi.criaderoxp.domain.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ar.uba.fi.criaderoxp.domain.model.Jaula;

@Repository
public class JaulaRepositoryImpl implements JaulaRepository {
	@PersistenceContext
	private EntityManager em;

	@Transactional(readOnly = true)
	public List<Jaula> findAll() {
		Session session = (Session) em.getDelegate();
		Criteria criteria = session.createCriteria(Jaula.class);
		return criteria.list();
	}
}
