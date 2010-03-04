package ar.uba.fi.criaderoxp.domain.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import ar.uba.fi.criaderoxp.domain.exception.InvalidStateException;

/**
 * Representa una actividad, con sus precondiciones y acciones a ejecutar sobre un conejo.<br />
 * El objetivo de las actividades es realizar una transición entre dos estados.
 * 
 * @author mmazzei
 * @category Strategy
 */
@Entity
public class Activity {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;

	@Column(unique = true)
	private String codigo;
	private String descripcion;

	@ManyToMany
	private Set<Estado> requisitos;

	@OneToOne
	private Estado destino;

	@OneToOne
	private TipoEvento tipoEvento;

	/** @deprecated Sólo para uso del framework de persistencia. */
	protected Activity() {
	}

	public Activity(String codigo) {
		this.codigo = codigo;
		this.requisitos = new HashSet<Estado>();
	}

	/** @return El código de identificación. No puede modificarse. */
	public String getCodigo() {
		return codigo;
	}

	/** @return Una descripción de la actividad. */
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/** @return El tipo de evento que debe registrarse al ejecutar la actividad. */
	public TipoEvento getTipoEvento() {
		return tipoEvento;
	}

	public void setTipoEvento(TipoEvento tipoEvento) {
		this.tipoEvento = tipoEvento;
	}

	/** Configura el estado al que deve realizarse la transición. */
	public void setDestino(Estado destino) {
		this.destino = destino;
	}

	/** @return Los posibles estados desde los que se puede ejecutar. */
	public Set<Estado> getRequisitos() {
		return this.requisitos;
	}

	public void setRequisitos(Set<Estado> requisitos) {
		this.requisitos = requisitos;
	}

	/**
	 * Ejecuta la actividad, sobre el conejo, dejándolo en el estado destino (si es que hay uno), o
	 * en el mismo (si no hay estado destino).
	 */
	public void execute(Conejo conejo) {
		if (!this.isValido(conejo.getEstado())) {
			throw new InvalidStateException();
		}

		if (this.destino != null) {
			conejo.setEstado(this.destino);
		}
		conejo.getRegistro().registrar("", this.getTipoEvento());
	}

	/**
	 * @param estado
	 *            {@link Estado} estado actual.
	 * @return {@link Boolean#TRUE} si se puede ejecutar la actividad desde el estado actual.
	 * @see #getRequisitos()
	 */
	private Boolean isValido(Estado estado) {
		return this.requisitos.contains(estado);
	}

	@Override
	public boolean equals(Object obj) {
		return (obj != null)
				&& ((this == obj) || (obj.getClass().equals(this.getClass()) && (((Activity) obj).codigo == this.codigo)));
	}
}
