package ar.uba.fi.criaderoxp.web;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.PropertyModel;

public class LinkExample extends WebPage {
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
