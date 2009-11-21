package main.java.ar.uba.fi.criaderoxp.domain.model;

/**
 * Los {@link Evento} pueden clasificarse según el tipo de acción que los haya
 * generado.<br />
 * Por lo tanto debería haber al menos un {@link TipoEvento} por cada
 * {@link Activity} o interacción posible con un {@link Conejo}.
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

	/** @return Una descripción del sexo. */
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public boolean equals(Object obj) {
		return (obj != null)
				&& ((this == obj) || (obj.getClass().equals(this.getClass()) && (((TipoEvento) obj).getCodigo()
						.equals(this.getCodigo()))));
	}
}
