package ar.uba.fi.criaderoxp.web;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.StatelessForm;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;

import ar.uba.fi.criaderoxp.domain.security.Usuario;

public class SigninPage extends WebPage {
	private static class SigninForm extends StatelessForm {
		private String username;
		private String password;

		public SigninForm(String id) {
			super(id);
			setModel(new CompoundPropertyModel<SigninForm>(this));
			add(new TextField("username"));
			add(new PasswordTextField("password"));
		}

		@Override
		public final void onSubmit() {
			if (signIn(username, password)) {
				if (!continueToOriginalDestination()) {
					setResponsePage(getApplication().getHomePage());
				}
			} else {
				error("Unknown username/ password");
			}
		}

		private boolean signIn(String username, String password) {
			if (username != null && password != null) {
				// Usuario user = DataBase.getInstance().findUser(username);
				// if (user != null) {
				// if (user.getWiaPassword().equals(password)) {
				// WiaSession.get().setUser(user);
				// return true;
				// }
				// }
			}
			return false;
		}
	}
}
