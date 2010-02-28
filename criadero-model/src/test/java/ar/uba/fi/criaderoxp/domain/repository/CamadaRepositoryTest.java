package ar.uba.fi.criaderoxp.domain.repository;

import java.util.HashMap;

import org.junit.Test;

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

	@Test
	public void dummyTest() {
		// Este test se agregó para que no se queje Maven de que esta clase no
		// tenga ningún método de tests.
		// Lo que hace es un "mockeado", debería emprolijarla.
	}
}
