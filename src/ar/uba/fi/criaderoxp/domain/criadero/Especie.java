package ar.uba.fi.criaderoxp.domain.criadero;

/**
 * En biología se denomina especie a cada uno de los grupos en que se dividen
 * los géneros, es decir, la limitación de lo genérico en un ámbito
 * morfológicamente concreto. En biología, una especie es la unidad básica de la
 * clasificación biológica.
 */
public class Especie {
	private String codigo;
	private String descripcion;

	public Especie(String codigo) {
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
