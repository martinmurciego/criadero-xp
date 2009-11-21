package main.java.ar.uba.fi.criaderoxp.domain.model;

/**
 * Representa estados que puede tomar un animal durante el transcurso de su
 * vida.
 */
public class Estado {
	private String codigo;
	private String descripcion;

	public Estado(String codigo) {
		this.codigo = codigo;
	}

	/** @return El código de identificación. No puede modificarse. */
	public String getCodigo() {
		return codigo;
	}

	/** @return Una descripción del estado. */
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public boolean equals(Object obj) {
		return (obj != null)
				&& ((this == obj) || (obj.getClass().equals(this.getClass()) && (((Estado) obj)
						.getCodigo().equals(this.getCodigo()))));
	}
}
