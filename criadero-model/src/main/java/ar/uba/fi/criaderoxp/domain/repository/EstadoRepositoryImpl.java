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

import ar.uba.fi.criaderoxp.domain.exception.BusinessException;
import ar.uba.fi.criaderoxp.domain.model.Estado;

@Repository
public class EstadoRepositoryImpl implements EstadoRepository {
	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional(readOnly = true)
	public Estado getAmamantando() {
		throw new BusinessException("Not yet implemented!");
	}

	@Override
	@Transactional(readOnly = true)
	public Estado getEnEspera() {
		throw new BusinessException("Not yet implemented!");
	}

	@Override
	@Transactional(readOnly = true)
	public Estado getEngorde() {
		throw new BusinessException("Not yet implemented!");
	}

	@Override
	@Transactional(readOnly = true)
	public Estado getGazapo() {
		throw new BusinessException("Not yet implemented!");
	}

	@Override
	@Transactional(readOnly = true)
	public Estado getJuntado() {
		throw new BusinessException("Not yet implemented!");
	}

	@Override
	@Transactional(readOnly = true)
	public Estado getNullObject() {
		return findByCodigo("nullObject");
	}

	@Override
	@Transactional(readOnly = true)
	public Estado getPreniado() {
		throw new BusinessException("Not yet implemented!");
	}

	@Override
	@Transactional(readOnly = true)
	public Estado getProductor() {
		throw new BusinessException("Not yet implemented!");
	}

	@Override
	@Transactional(readOnly = true)
	public Estado getSacrificado() {
		throw new BusinessException("Not yet implemented!");
	}

	@Override
	@Transactional(readOnly = true)
	public Estado getVendido() {
		throw new BusinessException("Not yet implemented!");
	}

	@Override
	@Transactional(readOnly = true)
	public List<Estado> findSano() {
		Session session = (Session) em.getDelegate();
		SimpleExpression restriccion = Property.forName("sano").eq(Boolean.TRUE);
		Criteria criteria = session.createCriteria(Estado.class).add(restriccion);
		return criteria.list();
	}

	/** @return El estado con el código indicado. */
	private Estado findByCodigo(String codigo) {
		Session session = (Session) em.getDelegate();
		SimpleExpression restriccion = Property.forName("codigo").eq(codigo);
		Criteria criteria = session.createCriteria(Estado.class).add(restriccion);
		return (Estado) criteria.uniqueResult();
	}
}
