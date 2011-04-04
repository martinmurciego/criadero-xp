package ar.uba.fi.criaderoxp.web;

import org.apache.wicket.Request;
import org.apache.wicket.protocol.http.WebSession;

import ar.uba.fi.criaderoxp.domain.security.Usuario;

public class UserSession extends WebSession {
	private static final long serialVersionUID = -4226500258126106759L;

	private Usuario user;

	public UserSession(Request request) {
		super(request);
	}

	public static UserSession get() {
		return (UserSession) WebSession.get();
	}

	public boolean isAuthenticated() {
		return user != null;
	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}
}
