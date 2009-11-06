package ar.uba.fi.criaderoxp.domain.facturacion;

/**
 * Representa un tipo de documento de identificación de una persona.
 */
public class TipoDocumento {
	private String codigo;
	private String descripcion;

	public TipoDocumento(String codigo) {
		this.codigo = codigo;
	}

	/** @return El código de identificación. No puede modificarse. */
	public String getCodigo() {
		return codigo;
	}

	/** @return Una descripción de la especie. */
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
