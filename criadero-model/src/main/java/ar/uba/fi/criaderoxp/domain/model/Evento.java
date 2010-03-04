package ar.uba.fi.criaderoxp.domain.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import ar.uba.fi.criaderoxp.domain.security.Usuario;
import ar.uba.fi.criaderoxp.domain.util.Context;

/**
 * Los eventos son registros de hechos que le suceden a un conejo en algún momento.
 */
@Entity
public class Evento {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;
	private Date fecha;
	private String descripcion;
	@OneToOne
	private TipoEvento tipo;
	@OneToOne
	private Usuario usuario;

	/** @deprecated Sólo para uso del framework de persistencia. */
	protected Evento() {
	}

	public Evento(String descripcion, TipoEvento tipo) {
		this.descripcion = descripcion;
		this.fecha = new Date();
		this.tipo = tipo;
		this.usuario = Context.getInstance().getUsuario();
	}

	/** @return Una descripción del evento. */
	public String getDescripcion() {
		return descripcion;
	}

	/** @return La clasificación del evento. */
	public TipoEvento getTipo() {
		return tipo;
	}

	/** @return La fecha en la que sucedió el evento. */
	public Date getFecha() {
		return fecha;
	}

	/** @return El usuario que ejecutó la tarea. */
	public Usuario getUsuario() {
		return usuario;
	}
}
