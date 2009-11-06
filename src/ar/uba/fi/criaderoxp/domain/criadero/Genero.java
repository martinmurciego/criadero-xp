package ar.uba.fi.criaderoxp.domain.criadero;

/**
 * Indica si un individuo es femenino o masculino.
 */
public class Genero {
	private String codigo;
	private String descripcion;

	public Genero(String codigo) {
		this.codigo = codigo;
	}

	/** @return El código de identificación. No puede modificarse. */
	public String getCodigo() {
		return codigo;
	}

	/** @return Una descripción del género. */
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
