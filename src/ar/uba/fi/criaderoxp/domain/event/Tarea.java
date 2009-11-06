package ar.uba.fi.criaderoxp.domain.event;

/**
 * Una tarea es una actividad planificada para el futuro.
 * 
 * @deprecated Ver si es útil para algo.
 */
public class Tarea extends Evento {
	private Boolean hecho;

	public Tarea(TipoEvento tipo) {
		super(tipo);
	}
}
