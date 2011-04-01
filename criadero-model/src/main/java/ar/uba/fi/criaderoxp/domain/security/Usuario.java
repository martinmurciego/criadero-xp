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
 * Esta clase representa a los usuarios del sistema, que son aquellas personas que pueden iniciar
 * una sesión en el mismo y administrarlo o manipular datos.<br />
 * Un usuario puede cumplir diferentes roles en el sistema, con lo que tiene permiso para ejecutar
 * diferentes acciones dentro del mismo.<br />
 * TODO (mmazzei) - Queda por definir la manera en que se asocian roles y permisos así como permisos
 * y acciones. Si es WASP, sólo sé configurar por ahora roles de usuarios.
 * 
 * @author mmazzei
 * @category Aggregate Root
 */
@Entity
public class Usuario implements Serializable {
	private static final long serialVersionUID = -2314112926138942278L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;
	private String username;
	private String password;

	@OneToMany(cascade = CascadeType.PERSIST)
	private Set<Rol> roles;

	public Usuario(String username, String password) {
		this.username = username;
		this.password = password;
		this.roles = new HashSet<Rol>();
	}

	/** @deprecated Sólo para utilizar por fwk de persistencia. */
	public Usuario() {}

	/** @return El nombre con el que el usuario ingresa al sistema. */
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	/** @return La contraseña del usuario. */
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/** @return Los roles que tiene el usuario en el sistema. */
	public Set<Rol> getRoles() {
		return roles;
	}

	public void setRoles(Set<Rol> roles) {
		this.roles = roles;
	}
}
