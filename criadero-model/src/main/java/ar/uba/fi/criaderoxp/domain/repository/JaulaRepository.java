package ar.uba.fi.criaderoxp.domain.repository;

import ar.uba.fi.criaderoxp.domain.model.Conejo;
import ar.uba.fi.criaderoxp.domain.model.Jaula;

/**
 * Encargada de proveer acceso a las jaulas.
 * 
 * @author mmazzei
 * @category Repository
 */
// TODO (mmazzei) - Cambiar/Eliminar cuando comience con Hibernate en el sistema
public interface JaulaRepository {
	/** @return La camada de la que formó parte el conejo indicado. */
	public Jaula findByConejo(Conejo conejo);
}
