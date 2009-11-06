package ar.uba.fi.criaderoxp.domain.criadero;

import java.util.HashMap;
import java.util.Map;

import ar.uba.fi.criaderoxp.domain.property.Propiedad;

/**
 * En biología, raza se refiere a los grupos en que se subdividen algunas
 * especies biológicas, a partir de una serie de características que se
 * transmiten por herencia genética.
 */
public class Raza {
	private String codigo;
	private String descripcion;
	private Especie especie;
	private Map<String, Propiedad> propiedades;

	public Raza(String codigo, Especie especie) {
		this.codigo = codigo;
		this.especie = especie;
		this.propiedades = new HashMap<String, Propiedad>();
	}

	/** @return El código de identificación. No puede modificarse. */
	public String getCodigo() {
		return codigo;
	}

	/** @return Una descripción de la raza. */
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/** @return La especie a la que pertenece. */
	public Especie getEspecie() {
		return especie;
	}

	/**
	 * @return El valor de la propiedad indicada de la raza (color de pelos,
	 *         peso promedio, tamaño de camadas, etc. ).
	 */
	public Propiedad getPropiedad(String nombre) {
		return this.propiedades.get(nombre);
	}

	/** Modifica una propiedad de la raza. */
	public void setPropiedad(Propiedad propiedad) {
		this.propiedades.put(propiedad.getNombre(), propiedad);
	}
}
