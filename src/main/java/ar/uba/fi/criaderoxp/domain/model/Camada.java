package main.java.ar.uba.fi.criaderoxp.domain.model;

import java.util.HashSet;
import java.util.Set;


/**
 * Conjunto de crías nacidas en un mismo parto.<br />
 * Esta clase es la encargada de mantener la relación padre/madre - hijos y la
 * existente entre hermanos.
 * 
 * @author mmazzei
 * @category Value object
 */
public class Camada {
	private Set<Conejo> crias;
	private Conejo madre;
	private Conejo padre;

	public Camada(Conejo madre, Conejo conejo) {
		this.madre = madre;
		this.padre = conejo;
		this.crias = new HashSet<Conejo>();
	}

	public Conejo getMadre() {
		return madre;
	}

	public Conejo getPadre() {
		return padre;
	}

	public Set<Conejo> getCrias() {
		return crias;
	}

	/** Añade una cría a la camada (sólo si no existe). */
	public void add(Conejo cria) {
		this.crias.add(cria);
	}
}
