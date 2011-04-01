package ar.uba.fi.criaderoxp.domain.service;

import ar.uba.fi.criaderoxp.domain.model.Camada;
import ar.uba.fi.criaderoxp.domain.model.Conejo;
import ar.uba.fi.criaderoxp.domain.model.Estado;

/**
 * Provee funcionalidad que involucra a varios conejos.
 * 
 * @author mmazzei
 */
public interface ConejoService {

	/**
	 * Utilizado para comprar un conejo.
	 * 
	 * @param conejo
	 *            Conejo adquirido.
	 * @param estado
	 *            Estado en el que se lo adquirió.
	 */
	public void comprar(Conejo conejo, Estado estado);

	/**
	 * Junta dos conejos para que formen una pareja.
	 */
	void crearPareja(Conejo hembra, Conejo macho);

	/**
	 * La montura es la acción por la cual una pareja de conejos procrea, quedando el macho
	 * preparado para otra pareja y la hembra en espera del diagnóstico.
	 * 
	 * @param hembra
	 *            Coneja que fue montada.
	 */
	public void montar(Conejo hembra);

	/**
	 * Provoca el parto de una coneja y crea una camada de gazapos cuyos padres son esta hembra y su
	 * pareja.
	 * 
	 * @param madre
	 *            Debe ser una hembra, será la madre de la camada.
	 * @param tamanio
	 *            Cantidad de crías de la camada.
	 * @return Una nueva camada con la cantidad de crías indicada.
	 */
	public Camada crearCamada(Conejo madre, int tamanio);
}
