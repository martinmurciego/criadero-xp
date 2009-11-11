package ar.uba.fi.criaderoxp.domain.model;

import java.util.Date;

/**
 * Los eventos son registros de hechos que le suceden a un conejo en algún
 * momento.
 */
public class Evento {
	private Date fecha;
	private String descripcion;

	public Evento(String descripcion) {
		this.descripcion = descripcion;
		this.fecha = new Date();
	}

	/** @return Una descripción del evento. */
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/** @return La fecha en la que sucedió el evento. */
	public Date getFecha() {
		return fecha;
	}
}
