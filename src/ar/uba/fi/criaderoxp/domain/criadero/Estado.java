package ar.uba.fi.criaderoxp.domain.criadero;

import java.util.HashSet;
import java.util.Set;

/**
 * Representa estados que puede tomar un animal durante el transcurso de su
 * vida.
 */
public class Estado {
	private String codigo;
	private String description;
	private Set<Estado> requisitos;

	public Estado(String codigo) {
		this.codigo = codigo;
		this.requisitos = new HashSet<Estado>();
	}

	/** @return El código de identificación. No puede modificarse. */
	public String getCodigo() {
		return codigo;
	}

	/** @return Una descripción del estado. */
	public String getDescription() {
		return description;
	}

	/** @return Los posibles estados desde los que se puede llegar a éste. */
	public Set<Estado> getRequisitos() {
		return this.requisitos;
	}

	/** Añade un nuevo estado a los posibles. */
	public void addRequisito(Estado estado) {
		this.requisitos.add(estado);
	}

	/**
	 * @param estado
	 *            {@link Estado} estado actual.
	 * @return {@link Boolean#TRUE} si es válido llegar del estado actual a
	 *         éste.
	 * @see #getRequisitos()
	 */
	public Boolean isValido(Estado estado) {
		return this.requisitos.contains(estado);
	}

	@Override
	public boolean equals(Object obj) {
		return (obj != null)
				&& ((this == obj) || (obj.getClass().equals(this.getClass()) && (((Estado) obj)
						.getCodigo().equals(this.getCodigo()))));
	}
}
