package ar.uba.fi.criaderoxp.domain.criadero;

import java.util.HashMap;
import java.util.Map;

import ar.uba.fi.criaderoxp.domain.event.Agenda;
import ar.uba.fi.criaderoxp.domain.property.Propiedad;

public class Individuo {
	private Raza raza;
	private Map<String, Propiedad> propiedades;
	private Genero genero;

	private Camada camada;
	private Jaula jaula;
	private Agenda agenda;

	private Float peso;

	/*-------------------------------------------------------------------------
	 * 								CONSTRUCTORES
	 -------------------------------------------------------------------------*/
	public Individuo(Raza raza) {
		this.propiedades = new HashMap<String, Propiedad>();
		this.agenda = new Agenda();
	}

	/*-------------------------------------------------------------------------
	 * 								GETTERS Y SETTERS
	 -------------------------------------------------------------------------*/
	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	/** @return La camada a la que pertenece el individuo. */
	public Camada getCamada() {
		return camada;
	}

	public void setCamada(Camada camada) {
		this.camada = camada;
	}

	/** @return La jaula habitada por el individuo. */
	public Jaula getJaula() {
		return jaula;
	}

	public void setJaula(Jaula jaula) {
		this.jaula = jaula;
	}

	public Raza getRaza() {
		return raza;
	}

	/** @return El peso del individuo. */
	public Float getPeso() {
		return peso;
	}

	public void setPeso(Float peso) {
		this.peso = peso;
	}

	/** @return La agenda de eventos asociada. */
	public Agenda getAgenda() {
		return agenda;
	}

	/**
	 * Las propiedades del individuo son todas aquellas de su raza y las
	 * configuradas específicamente para él.
	 * 
	 * @return El valor de la propiedad indicada del individuo (color de pelos,
	 *         peso promedio, etc. ).
	 */
	public Propiedad getPropiedad(String nombre) {
		Propiedad propiedad;
		if (this.propiedades.containsKey(nombre)) {
			propiedad = this.propiedades.get(nombre);
		} else {
			propiedad = this.getRaza().getPropiedad(nombre);
		}

		return propiedad;
	}

	/**
	 * Modifica una propiedad del individuo. Cualquier propiedad que se
	 * configure aquí reemplaza a la existente en la raza.
	 */
	public void setPropiedad(Propiedad propiedad) {
		this.propiedades.put(propiedad.getNombre(), propiedad);
	}

	/*-------------------------------------------------------------------------
	 * 								COMPORTAMIENTO
	 -------------------------------------------------------------------------*/

}
