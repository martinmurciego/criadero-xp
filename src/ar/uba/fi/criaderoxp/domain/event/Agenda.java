package ar.uba.fi.criaderoxp.domain.event;

import java.util.Collection;

/**
 * La agenda es utilizada para llevar registros de eventos.
 */
public class Agenda {
	private Collection<Evento> eventos;

	/** Añade un evento a la agenda. */
	public void addEvento(TipoEvento tipo, String descripcion) {
		Evento evento = new Evento(tipo);
		evento.setDescripcion(descripcion);
		this.eventos.add(evento);
	}
}
