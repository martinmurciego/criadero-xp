package ar.uba.fi.criaderoxp.domain.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Indica si un individuo es femenino o masculino.
 */
@Entity
public class Sexo implements Serializable {
	private static final long serialVersionUID = -204433916944399448L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;

	@Column(unique = true)
	private String codigo;
	private String descripcion;

	/** @deprecated Sólo para uso del framework de persistencia. */
	protected Sexo() {
	}

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
	public int hashCode() {
		Long id = this.id;
		return id.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return (obj != null)
				&& ((this == obj) || (obj.getClass().equals(this.getClass()) && (((Sexo) obj).codigo == this.codigo)));
	}

	public String toString() {
		return descripcion;
	}
}
