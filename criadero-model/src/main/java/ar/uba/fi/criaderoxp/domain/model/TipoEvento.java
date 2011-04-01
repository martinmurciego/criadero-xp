package ar.uba.fi.criaderoxp.domain.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Los {@link Evento} pueden clasificarse según el tipo de acción que los haya generado.<br />
 * Por lo tanto debería haber al menos un {@link TipoEvento} por cada {@link Activity} o interacción
 * posible con un {@link Conejo}.
 */
@Entity
public class TipoEvento implements Serializable {
	private static final long serialVersionUID = -8241678581452209859L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;

	@Column(unique = true)
	private String codigo;

	private String descripcion;

	/** @deprecated Sólo para uso del framework de persistencia. */
	protected TipoEvento() {
	}

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
