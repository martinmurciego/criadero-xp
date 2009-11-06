package ar.uba.fi.criaderoxp.domain.event;

import java.util.Date;

import ar.uba.fi.criaderoxp.domain.security.Usuario;

/**
 * Los eventos son registros de hechos que le suceden a un individuo en algún
 * momento.
 */
public class Evento {
	private Date fecha;
	private TipoEvento tipo;
	private String descripcion;
	private Usuario usuario;

	/**
	 * Luego de construirse el evento, es recomendable añadir una descripción e
	 * indicar el usuario para el que sucedió.
	 */
	public Evento(TipoEvento tipo) {
		this.tipo = tipo;
		this.fecha = new Date();
	}

	/** @return Una descripción del evento. */
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/** @return El usuario que registró el evento. */
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	/** @return La fecha en la que sucedió el evento. */
	public Date getFecha() {
		return fecha;
	}

	/** @return El tipo de evento. */
	public TipoEvento getTipo() {
		return tipo;
	}
}
