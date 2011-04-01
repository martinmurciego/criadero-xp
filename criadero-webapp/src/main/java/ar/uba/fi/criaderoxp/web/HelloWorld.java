package ar.uba.fi.criaderoxp.web;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.security.WaspSession;
import org.apache.wicket.security.components.SecureWebPage;
import org.apache.wicket.security.components.markup.html.links.SecurePageLink;

public class HelloWorld extends SecureWebPage {
	@SuppressWarnings("serial")
	public HelloWorld() {
		add(new Label("message", "¡Hola mundo!"));

		add(new SecurePageLink<String>("link", LinkExample.class) {
			@Override
			public void onClick() {
				setResponsePage(LinkExample.class);
			}
		});
		add(new Link<String>("logout") {
			@Override
			public void onClick() {
				WaspSession waspSession = ((WaspSession) getSession());
				if (waspSession.logoff(new LoginContext())) {
					waspSession.invalidate();
				}
				else error("A problem occured during the logoff process, please try again or contact support");
			}
		});
	}
}
