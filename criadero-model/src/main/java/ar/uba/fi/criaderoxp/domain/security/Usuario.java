package ar.uba.fi.criaderoxp.domain.security;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Esta clase representa a los usuarios del sistema, que son aquellas personas que pueden iniciar
 * una sesión en el mismo y administrarlo o manipular datos.
 * 
 * @author mmazzei
 */
@Entity
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;
	private String username;
	private String password;
	private boolean isAdmin;

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

	/** @return {@link Boolean#TRUE} si el usuario es administrador. */
	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	@Override
	public boolean equals(Object obj) {
		return (obj != null)
				&& ((this == obj) || (obj.getClass().equals(this.getClass()) && (((Usuario) obj).id == this.id)));
	}
}
