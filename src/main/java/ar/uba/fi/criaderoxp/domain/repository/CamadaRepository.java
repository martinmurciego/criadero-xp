package main.java.ar.uba.fi.criaderoxp.domain.repository;

import main.java.ar.uba.fi.criaderoxp.domain.model.Camada;
import main.java.ar.uba.fi.criaderoxp.domain.model.Conejo;

/**
 * Encargada de proveer acceso a las camadas.
 * 
 * @author mmazzei
 * @category Repository
 */
// TODO (mmazzei) - Cambiar/Eliminar cuando comience con Hibernate en el sistema
public interface CamadaRepository {
	/** @return La camada de la que formó parte el conejo indicado. */
	public Camada findByConejo(Conejo conejo);
}
