package ar.uba.fi.criaderoxp.domain.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.SimpleExpression;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ar.uba.fi.criaderoxp.domain.model.Activity;

@Repository
public class ActivityRepositoryImpl implements ActivityRepository {
	@PersistenceContext
	private EntityManager em;

	@Transactional(readOnly = true)
	public Activity getCompra() {
		return findByCodigo("compra");
	}

	@Override
	public Activity getCura() {
		return findByCodigo("cura");
	}

	@Override
	public Activity getDestete() {
		return findByCodigo("destete");
	}

	@Override
	public Activity getDesteteCrias() {
		return findByCodigo("desteteCrias");
	}

	@Override
	public Activity getDiagnosticoNoPreniada() {
		return findByCodigo("diagnosticoNoPreniada");
	}

	@Override
	public Activity getDiagnosticoPreniada() {
		return findByCodigo("diagnosticoPreniada");
	}

	@Override
	public Activity getEnfermedad() {
		return findByCodigo("enfermedad");
	}

	@Override
	public Activity getJunta() {
		return findByCodigo("junta");
	}

	@Override
	public Activity getMonturaHembra() {
		return findByCodigo("monturaHembra");
	}

	@Override
	public Activity getMonturaMacho() {
		return findByCodigo("monturaMacho");
	}

	@Override
	public Activity getMudanza() {
		return findByCodigo("mudanza");
	}

	@Override
	public Activity getMuerte() {
		return findByCodigo("muerte");
	}

	@Override
	public Activity getNacimiento() {
		return findByCodigo("nacimiento");
	}

	@Override
	public Activity getParto() {
		return findByCodigo("parto");
	}

	@Override
	public Activity getSacrificio() {
		return findByCodigo("sacrificio");
	}

	@Override
	public Activity getSexadoProductor() {
		return findByCodigo("sexadoProductor");
	}

	@Override
	public Activity getSexadoReproductor() {
		return findByCodigo("sexadoReproductor");
	}

	@Override
	public Activity getVenta() {
		return findByCodigo("venta");
	}

	/** @return La actividad con el código indicado. */
	private Activity findByCodigo(String codigo) {
		Session session = (Session) em.getDelegate();
		SimpleExpression restriccion = Property.forName("codigo").eq(codigo);
		Criteria criteria = session.createCriteria(Activity.class).add(restriccion);
		return (Activity) criteria.uniqueResult();
	}
}
