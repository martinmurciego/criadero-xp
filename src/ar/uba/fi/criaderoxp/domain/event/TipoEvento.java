package ar.uba.fi.criaderoxp.domain.event;

/**
 * Representa a cada posible tipo de evento por el que puede pasar un individuo:
 * vacunación, celo, muerte, apareamiento, etc.
 */
public class TipoEvento {
	private String codigo;
	private String descripcion;

	public TipoEvento(String codigo) {
		this.codigo = codigo;
	}

	/** @return El código de identificación. No puede modificarse. */
	public String getCodigo() {
		return codigo;
	}

	/** @return Una descripción del tipo de evento. */
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
