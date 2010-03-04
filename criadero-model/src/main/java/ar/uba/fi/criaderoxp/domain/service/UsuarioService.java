package ar.uba.fi.criaderoxp.domain.service;

import ar.uba.fi.criaderoxp.domain.security.Usuario;

/**
 * Provee funcionalidad utilizada para garantizar la seguridad de la aplicación.
 * 
 * @author mmazzei
 */
public interface UsuarioService {
	/** @return El usuario cuyo nombre de usuario es el indicado. */
	public Usuario getUsuario(String username);

	/** Guarda un nuevo usuario en la base de datos. */
	public void save(Usuario usuario);
}
