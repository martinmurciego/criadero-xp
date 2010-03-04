package ar.uba.fi.criaderoxp.domain.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Lleva el historial de acciones efectuadas sobre un conejo.
 * 
 * @author mmazzei
 * @category Value object
 */
@Entity
public class Registro {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;

	@OneToMany(cascade = CascadeType.ALL)
	private List<Evento> eventos = new ArrayList<Evento>();

	/** Registra la ejecución de una actividad, con un comentario. */
	public void registrar(String descripcion, Activity actividad) {
		Evento evento = new Evento(descripcion, actividad.getTipoEvento());
		this.eventos.add(evento);
	}

	/** Registra la ejecución de un tipo de evento, con un comentario. */
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
