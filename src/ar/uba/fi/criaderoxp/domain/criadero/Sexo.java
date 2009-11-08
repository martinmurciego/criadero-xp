package ar.uba.fi.criaderoxp.domain.criadero;

/**
 * Indica si un individuo es femenino o masculino.
 */
public class Sexo {
	// TODO (mmazzei) - Ver qué hacer con esto...
	public static final Sexo MACHO = new Sexo("macho");
	public static final Sexo HEMBRA = new Sexo("hembra");

	private String codigo;
	private String descripcion;

	public Sexo(String codigo) {
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
				&& ((this == obj) || (obj.getClass().equals(this.getClass()) && (((Sexo) obj)
						.getCodigo().equals(this.getCodigo()))));
	}
}
