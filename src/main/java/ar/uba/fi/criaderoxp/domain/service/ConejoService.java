package main.java.ar.uba.fi.criaderoxp.domain.service;

import main.java.ar.uba.fi.criaderoxp.domain.model.Camada;
import main.java.ar.uba.fi.criaderoxp.domain.model.Conejo;
import main.java.ar.uba.fi.criaderoxp.domain.model.Jaula;

/**
 * Provee funcionalidad que involucra a varios conejos.
 * 
 * @author mmazzei
 */
public interface ConejoService {
	/**
	 * Junta dos conejos para que formen una pareja.
	 */
	void crearPareja(Conejo hembra, Conejo macho);

	/**
	 * La montura es la acción por la cual una pareja de conejos procrea,
	 * quedando el macho preparado para otra pareja y la hembra en espera del
	 * diagnóstico.
	 * 
	 * @param hembra
	 *            Coneja que fue montada.
	 */
	public void montar(Conejo hembra);

	/**
	 * Provoca el parto de una coneja y crea una camada de gazapos cuyos padres
	 * son esta hembra y su pareja.
	 * 
	 * @param madre
	 *            Debe ser una hembra, será la madre de la camada.
	 * @param tamanio
	 *            Cantidad de crías de la camada.
	 * @return Una nueva camada con la cantidad de crías indicada.
	 */
	public Camada crearCamada(Conejo madre, int tamanio);

	/** @return La camada en que nació el conejo. */
	public Camada getCamada(Conejo conejo);

	/** @return La jaula en que se encuentra el conejo. */
	public Jaula getJaula(Conejo conejo);
}
