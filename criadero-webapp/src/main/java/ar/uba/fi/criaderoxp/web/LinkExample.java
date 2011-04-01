package ar.uba.fi.criaderoxp.web;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.security.components.SecureWebPage;

public class LinkExample extends SecureWebPage {
	private int linkCounter = 0;

	public LinkExample() {
		add(new Link("myLink") {
			public void onClick() {
				linkCounter++;
			}
		});
		add(new Label("message", new PropertyModel(this, "linkCounter")));
	}
}
