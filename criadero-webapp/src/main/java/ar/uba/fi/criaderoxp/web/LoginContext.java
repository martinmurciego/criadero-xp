package ar.uba.fi.criaderoxp.web;

import org.apache.wicket.security.authentication.LoginException;
import org.apache.wicket.security.hive.authentication.DefaultSubject;
import org.apache.wicket.security.hive.authentication.Subject;
import org.apache.wicket.security.hive.authentication.UsernamePasswordContext;
import org.apache.wicket.security.hive.authorization.Principal;
import org.apache.wicket.security.hive.authorization.SimplePrincipal;

import ar.uba.fi.criaderoxp.domain.security.Rol;
import ar.uba.fi.criaderoxp.domain.security.Usuario;
import ar.uba.fi.criaderoxp.domain.service.UsuarioService;
import ar.uba.fi.criaderoxp.domain.util.Context;

public class LoginContext extends UsernamePasswordContext {
	public LoginContext(String username, String password) {
		super(username, password);
	}

	/**
	 * Constructor for loging off.
	 */
	public LoginContext() {
		super();
	}

	@Override
	protected Subject getSubject(String username, String password) throws LoginException {
		if (username == null || password == null) throw new LoginException("No se indicó usuario/password");

		Usuario usuario = Context.getInstance().getBean("usuarioService", UsuarioService.class).getUsuario(username);
		if (usuario == null || !usuario.getPassword().equals(password)) throw new LoginException("Usuario/password incorrectos");

		DefaultSubject user = new DefaultSubject();
		for (Rol rol : usuario.getRoles()) {
			Principal principal = new SimplePrincipal(rol.getName());
			user.addPrincipal(principal);
		}

		return user;
	}

}
