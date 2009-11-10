package ar.uba.fi.criaderoxp.domain.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Representa estados que puede tomar un animal durante el transcurso de su
 * vida.
 */
public class Estado {
	private String codigo;
	private String description;

	public Estado(String codigo) {
		this.codigo = codigo;
	}

	/** @return El código de identificación. No puede modificarse. */
	public String getCodigo() {
		return codigo;
	}

	/** @return Una descripción del estado. */
	public String getDescription() {
		return description;
	}

	@Override
	public boolean equals(Object obj) {
		return (obj != null)
				&& ((this == obj) || (obj.getClass().equals(this.getClass()) && (((Estado) obj)
						.getCodigo().equals(this.getCodigo()))));
	}
}
