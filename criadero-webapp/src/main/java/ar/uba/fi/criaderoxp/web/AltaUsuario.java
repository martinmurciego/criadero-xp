package ar.uba.fi.criaderoxp.web;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;

import ar.uba.fi.criaderoxp.domain.security.Usuario;
import ar.uba.fi.criaderoxp.domain.service.UsuarioService;
import ar.uba.fi.criaderoxp.domain.util.Context;

public class AltaUsuario extends WebPage {
	private Usuario usuario = new Usuario();

	public AltaUsuario() {
		Form<Usuario> form = new AltaUsuarioForm<Usuario>("form", new CompoundPropertyModel<Usuario>(usuario));
		add(form);
	}

	private class AltaUsuarioForm<T> extends Form<T> {
		private static final long serialVersionUID = -1231999824338116257L;

		public AltaUsuarioForm(String id, IModel<T> model) {
			super(id, model);

			this.add(new RequiredTextField<String>("username"));
			this.add(new PasswordTextField("password"));
			this.add(new CheckBox("isAdmin"));
		}

		protected void onSubmit() {
			Context.getInstance().getBean("usuarioService", UsuarioService.class).save(usuario);
		};
	}
}
