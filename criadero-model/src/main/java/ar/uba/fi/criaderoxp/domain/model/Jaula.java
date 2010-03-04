package ar.uba.fi.criaderoxp.domain.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import ar.uba.fi.criaderoxp.domain.exception.BusinessException;

/**
 * Todos los individuos vivos del criadero habitan una jaula. Cada jaula puede tener ninguno, uno o
 * varios individuos.
 */
@Entity
public class Jaula {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;
	private String codigo;
	private Integer capacidad;

	@OneToMany
	private Set<Conejo> conejos;

	public Jaula(String codigo) {
		this.codigo = codigo;
		this.conejos = new HashSet<Conejo>();
	}

	/** @return El código de identificación. No puede modificarse. */
	public String getCodigo() {
		return codigo;
	}

	/** @return La cantidad de conejos que entran en la jaula. */
	public Integer getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(Integer capacidad) {
		this.capacidad = capacidad;
	}

	/** @return Los conejos que habitan la jaula. */
	public Set<Conejo> getConejos() {
		return this.conejos;
	}

	/**
	 * Añade un conejo a la jaula, sólo si no la habita aún.
	 * 
	 * @throws BusinessException
	 *             Si la jaula estaba llena.
	 */
	public void add(Conejo conejo) {
		if (this.conejos.size() >= this.capacidad) {
			throw new BusinessException("La jaula está llena.");
		}
		this.conejos.add(conejo);
	}

	/** Quita al conejo de la jaula. */
	public void remove(Conejo conejo) {
		this.conejos.remove(conejo);
	}
}
