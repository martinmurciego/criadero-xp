package ar.uba.fi.criaderoxp.domain.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Representa estados que puede tomar un animal durante el transcurso de su vida.
 */
@Entity
public class Estado implements Serializable {
	private static final long serialVersionUID = -7117718214861856749L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;

	@Column(unique = true)
	private String codigo;

	private String descripcion;

	@Column(nullable = false)
	private boolean sano;

	/** @deprecated Sólo para uso del framework de persistencia. */
	protected Estado() {
	}

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

	/** @return Si el estado representa a un conejo sano. */
	public boolean isSano() {
		return sano;
	}

	public void setSano(boolean sano) {
		this.sano = sano;
	}

	@Override
	public int hashCode() {
		Long id = this.id;
		return id.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return (obj != null)
				&& ((this == obj) || (obj.getClass().equals(this.getClass()) && (((Estado) obj).codigo
						.equals(this.codigo))));
	}

	public String toString() {
		return descripcion;
	}
}
