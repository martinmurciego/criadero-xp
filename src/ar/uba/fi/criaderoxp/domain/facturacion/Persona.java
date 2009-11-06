package ar.uba.fi.criaderoxp.domain.facturacion;

/**
 * Representación de cualquier persona que participe de alguna actividad en el
 * criadero.
 */
public abstract class Persona {
	private String documento;
	private String apellido;
	private String nombre;
	private TipoDocumento tipoDocumento;

	public Persona(TipoDocumento tipoDocumento, String documento) {
		this.tipoDocumento = tipoDocumento;
		this.documento = documento;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDocumento() {
		return documento;
	}

	public TipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}

}
