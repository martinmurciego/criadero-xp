package ar.uba.fi.criaderoxp.web;

import org.apache.wicket.Page;
import org.apache.wicket.Request;
import org.apache.wicket.Response;
import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebApplication;

import ar.uba.fi.criaderoxp.domain.util.Context;

public class CriaderoWebApplication extends WebApplication {

	@Override
	protected void init() {
		Context.getInstance().setApplicationContext("applicationContext.xml");
	}

	@Override
	public Class<? extends Page> getHomePage() {
		// TODO (mmazzei) - return Index.class
		return AltaConejo.class;
	}

	@Override
	public Session newSession(Request request, Response response) {
		return new UserSession(request);
	}
}
