package ar.uba.fi.criaderoxp.domain.criadero;

import java.util.HashSet;
import java.util.Set;

/**
 * Conjunto de crías nacidas en un mismo parto.
 */
public class Camada {
	private Set<Animal> crias;
	private Animal madre;
	private Animal padre;

	public Camada(Animal madre, Animal padre) {
		this.madre = madre;
		this.padre = padre;
		this.crias = new HashSet<Animal>();
	}

	public Animal getMadre() {
		return madre;
	}

	public Animal getPadre() {
		return padre;
	}

	public Set<Animal> getCrias() {
		return crias;
	}

	/** Añade una cría a la camada (sólo si no existe). */
	public void add(Animal cria) {
		this.crias.add(cria);
	}
}
