package ar.uba.fi.criaderoxp.domain.service;

import ar.uba.fi.criaderoxp.domain.security.Usuario;
import ar.uba.fi.criaderoxp.domain.service.UsuarioService;

public class UsuarioServiceDummy implements UsuarioService {
	@Override
	public Usuario getUsuario(String username) {
		Usuario usuario = new Usuario();
		usuario.setUsername(username);

		return usuario;
	}

	@Override
	public void save(Usuario usuario) {
		// Nothing to do...
	}

}
