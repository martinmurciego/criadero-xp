package ar.uba.fi.criaderoxp.web;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.StatelessForm;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.security.authentication.LoginException;

public class SigninPage extends WebPage {
	@SuppressWarnings("serial")
	private static class SignInForm extends StatelessForm {
		private String wiaPassword;
		private String wiaUsername;

		public SignInForm(String id) {
			super(id);
			setModel(new CompoundPropertyModel(this));
			add(new TextField<String>("wiaUsername"));
			add(new PasswordTextField("wiaPassword"));
			add(new Button("submit"));
		}

		@Override
		public final void onSubmit() {
			if (signIn(wiaUsername, wiaPassword)) {
				if (!continueToOriginalDestination()) {
					setResponsePage(getApplication().getHomePage());
				}
			}
			else {
				error("Unknown username/ password");
			}
		}

		private boolean signIn(String username, String password) {
			try {
				CriaderoSession.get().login(new LoginContext(username, password));
				return true;
			}
			catch (LoginException e) {
				e.printStackTrace();
				return false;
			}
		}

	}

	public SigninPage() {
		add(new SignInForm("signinForm"));
	}
}
