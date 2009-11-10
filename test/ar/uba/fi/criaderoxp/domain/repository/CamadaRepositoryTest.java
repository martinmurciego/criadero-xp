package ar.uba.fi.criaderoxp.domain.repository;

import java.util.HashMap;

import ar.uba.fi.criaderoxp.domain.model.Camada;
import ar.uba.fi.criaderoxp.domain.model.Conejo;

public class CamadaRepositoryTest implements CamadaRepository {
	private HashMap<Conejo, Camada> datosPrueba = new HashMap<Conejo, Camada>();

	/**
	 * Utilizado para generar una tabla en memoria de la cual se obtienen los
	 * datos de pruebas.
	 */
	public void addTestData(Conejo conejo, Camada camada) {
		this.datosPrueba.put(conejo, camada);
	}

	@Override
	public Camada findByConejo(Conejo conejo) {
		return this.datosPrueba.get(conejo);
	}

}
