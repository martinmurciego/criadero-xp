package ar.uba.fi.criaderoxp.domain.criadero;

/**
 * Representa una posible enfermedad que puede atacar a los animales.
 */
public class Enfermedad {
	private String codigo;
	private String descripcion;

	public Enfermedad(String codigo) {
		this.codigo = codigo;
	}

	/** @return El código de identificación. No puede modificarse. */
	public String getCodigo() {
		return codigo;
	}

	/** @return Una descripción de la enfermedad. */
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
