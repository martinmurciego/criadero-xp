package ar.uba.fi.criaderoxp.domain.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 * Conjunto de crías nacidas en un mismo parto.<br />
 * Esta clase es la encargada de mantener la relación padre/madre - hijos y la existente entre
 * hermanos.
 * 
 * @author mmazzei
 * @category Value object
 */
@Entity
public class Camada {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;

	@OneToMany(cascade = CascadeType.ALL)
	private Set<Conejo> crias;

	@OneToOne(optional = false)
	private Conejo madre;

	@OneToOne(optional = false)
	private Conejo padre;

	/** @deprecated Sólo para utilizar por fwk de persistencia. */
	public Camada() {
	}

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
