package ar.uba.fi.criaderoxp.domain.model;

import java.util.Date;

/**
 * Los eventos son registros de hechos que le suceden a un conejo en algún
 * momento.
 */
public class Evento {
	private Date fecha;
	private String descripcion;
	private TipoEvento tipo;

	public Evento(String descripcion, TipoEvento tipo) {
		this.descripcion = descripcion;
		this.fecha = new Date();
		this.tipo = tipo;
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
}
