package ar.uba.fi.criaderoxp.domain.security;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Agrupa permisos que pueden ser asignados a algún usuario.
 * 
 * @author mmazzei
 */
@Entity
public class Rol implements Serializable {
	private static final long serialVersionUID = -2314112926138942278L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;
	private String name;

	@OneToMany(cascade = CascadeType.PERSIST)
	private Set<Permiso> permisos;

	public Rol(String name) {
		this.name = name;
		this.permisos = new HashSet<Permiso>();
	}

	/** @deprecated Sólo para utilizar por fwk de persistencia. */
	public Rol() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Permiso> getPermisos() {
		return permisos;
	}

	public void setPermisos(Set<Permiso> permisos) {
		this.permisos = permisos;
	}
}
