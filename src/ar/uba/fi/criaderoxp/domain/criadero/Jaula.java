package ar.uba.fi.criaderoxp.domain.criadero;

import java.util.HashSet;
import java.util.Set;

import ar.uba.fi.criaderoxp.domain.exception.BusinessException;

/**
 * Todos los individuos vivos del criadero habitan una jaula. Cada jaula puede
 * tener ninguno, uno o varios individuos.
 */
public class Jaula {
	private String codigo;
	private Integer capacidad;
	private Set<Individuo> individuos;

	public Jaula(String codigo) {
		this.codigo = codigo;
		this.individuos = new HashSet<Individuo>();
	}

	/** @return El código de identificación. No puede modificarse. */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * @return La cantidad de indiviudos que pueden habitar simultáneamente la
	 *         jaula.
	 */
	public Integer getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(Integer capacidad) {
		this.capacidad = capacidad;
	}

	/** @return Los individuos que habitan la jaula. */
	public Set<Individuo> getIndividuos() {
		return individuos;
	}

	/**
	 * Añade un individuo a la jaula, sólo si no la habita aún.
	 * 
	 * @throws BusinessException
	 *             Si la jaula estaba llena.
	 */
	public void add(Individuo individuo) {
		if (this.individuos.size() >= this.capacidad) {
			throw new BusinessException("La jaula está llena.");
		}
		this.individuos.add(individuo);
	}

}
