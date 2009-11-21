package main.java.ar.uba.fi.criaderoxp.domain.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Lleva el historial de acciones efectuadas sobre un conejo.
 * 
 * @author mmazzei
 * @category Value object
 */
public class Registro {
	private List<Evento> eventos = new ArrayList<Evento>();

	public void registrar(String descripcion, Activity actividad) {
		Evento evento = new Evento(descripcion, actividad.getTipoEvento());
		this.eventos.add(evento);
	}

	public void registrar(String descripcion, TipoEvento tipoEvento) {
		Evento evento = new Evento(descripcion, tipoEvento);
		this.eventos.add(evento);
	}

	/** @return Los eventos que se han registrado. */
	public List<Evento> getEventos() {
		return eventos;
	}

	/** @return El último evento registrado. */
	public Evento getUltimoEvento() {
		return this.eventos.get(this.eventos.size() - 1);
	}
}
