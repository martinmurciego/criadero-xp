package ar.uba.fi.criaderoxp.web;

import org.apache.wicket.Request;
import org.apache.wicket.security.WaspApplication;
import org.apache.wicket.security.WaspSession;

public class CriaderoSession extends WaspSession {
	private static final long serialVersionUID = -4226500258126106759L;

	public CriaderoSession(WaspApplication application, Request request) {
		super(application, request);
	}

	public static CriaderoSession get() {
		return (CriaderoSession) WaspSession.get();
	}
}
