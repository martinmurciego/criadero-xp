package ar.uba.fi.criaderoxp.web;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;

public class HelloWorld extends WebPage {
	public HelloWorld() {
		add(new Label("message", "¡Hola mundo!"));
		add(new Link("link") {
			@Override
			public void onClick() {
				setResponsePage(LinkExample.class);
			}
		});
	}
}
