package ar.uba.fi.criaderoxp.web;

import java.util.Date;
import java.util.List;

import org.apache.wicket.extensions.yui.calendar.DateField;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.validation.validator.DateValidator;

import ar.uba.fi.criaderoxp.domain.model.Conejo;
import ar.uba.fi.criaderoxp.domain.model.Estado;
import ar.uba.fi.criaderoxp.domain.model.Jaula;
import ar.uba.fi.criaderoxp.domain.model.Sexo;
import ar.uba.fi.criaderoxp.domain.repository.EstadoRepository;
import ar.uba.fi.criaderoxp.domain.repository.JaulaRepository;
import ar.uba.fi.criaderoxp.domain.repository.SexoRepository;
import ar.uba.fi.criaderoxp.domain.service.ConejoService;
import ar.uba.fi.criaderoxp.domain.util.Context;

public class AltaConejo extends WebPage {
	public AltaConejo() {
		Form<Conejo> form = new AltaConejoForm<Conejo>("form", new CompoundPropertyModel<Conejo>(new Conejo()));
		add(form);
	}

	private class AltaConejoForm<T extends Conejo> extends Form<T> {
		private static final long serialVersionUID = -3165081361025435965L;

		public AltaConejoForm(String id, IModel<T> model) {
			super(id, model);
			this.add(new FeedbackPanel("feedback"));

			// TODO (mmazzei) - Mejorar y lograr que valide
			this.add(new DateField("fechaNacimiento").add(DateValidator.minimum(new Date())));

			List<Sexo> sexos = Context.getInstance().getBean("sexoRepository", SexoRepository.class).findAll();
			this.add(new DropDownChoice<Sexo>("sexo", Model.ofList(sexos)).setRequired(true));

			List<Jaula> jaulas = Context.getInstance().getBean("jaulaRepository", JaulaRepository.class).findAll();
			this.add(new DropDownChoice<Jaula>("jaula", Model.ofList(jaulas)).setRequired(true));

			List<Estado> estados = Context.getInstance().getBean("estadoRepository", EstadoRepository.class).findSano();
			this.add(new DropDownChoice<Estado>("estado", Model.ofList(estados)).setRequired(true));
		}

		protected void onSubmit() {
			T conejo = getModelObject();
			// Esta chanchada es porque utilicé al conejo para traer el valor de los parámetros,
			// pero debería tener el estado nullValue al momento de crearlo...
			Estado estadoCompra = conejo.getEstado();
			conejo.setEstado(Context.getInstance().getBean("estadoRepository", EstadoRepository.class).getNullObject());

			Context.getInstance().getBean("conejoService", ConejoService.class).comprar(conejo, estadoCompra);
		};
	}
}
