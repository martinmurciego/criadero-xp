package ar.uba.fi.criaderoxp.domain.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import ar.uba.fi.criaderoxp.domain.exception.BusinessException;
import ar.uba.fi.criaderoxp.domain.model.Estado;
import ar.uba.fi.criaderoxp.domain.util.Context;

/**
 * Permite acceder a las WKI de actividades sin necesidad de una DB (requiere incluir activities.xml
 * en el entorno de pruebas).
 * 
 * @author mmazzei
 */
@Repository
public class EstadoRepositoryDummy implements EstadoRepository {
	@Override
	public Estado getNullObject() {
		return Context.getInstance().getBean("nullObjectEstado", Estado.class);
	}

	@Override
	public Estado getEngorde() {
		return Context.getInstance().getBean("engorde", Estado.class);
	}

	@Override
	public Estado getGazapo() {
		return Context.getInstance().getBean("gazapo", Estado.class);
	}

	@Override
	public Estado getEnEspera() {
		return Context.getInstance().getBean("enEspera", Estado.class);
	}

	@Override
	public Estado getJuntado() {
		return Context.getInstance().getBean("juntado", Estado.class);
	}

	@Override
	public Estado getProductor() {
		return Context.getInstance().getBean("productor", Estado.class);
	}

	@Override
	public Estado getSacrificado() {
		return Context.getInstance().getBean("sacrificado", Estado.class);
	}

	@Override
	public Estado getVendido() {
		return Context.getInstance().getBean("vendido", Estado.class);
	}

	@Override
	public Estado getAmamantando() {
		return Context.getInstance().getBean("amamantando", Estado.class);
	}

	@Override
	public Estado getPreniado() {
		return Context.getInstance().getBean("preniado", Estado.class);
	}

	@Override
	public List<Estado> findSano() {
		throw new BusinessException("Not yet implemented!");
	}
}
