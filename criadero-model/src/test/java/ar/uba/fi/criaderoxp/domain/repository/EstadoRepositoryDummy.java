package ar.uba.fi.criaderoxp.domain.repository;

import org.springframework.stereotype.Repository;

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
}
