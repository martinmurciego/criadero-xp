package ar.uba.fi.criaderoxp.domain.repository;

import ar.uba.fi.criaderoxp.domain.model.Activity;
import ar.uba.fi.criaderoxp.domain.util.Context;

/**
 * Permite acceder a las WKI de actividades sin necesidad de una DB (requiere incluir activities.xml
 * en el entorno de pruebas).
 * 
 * @author mmazzei
 */
public class ActivityRepositoryDummy implements ActivityRepository {
	@Override
	public Activity getCompra() {
		return Context.getInstance().getBean("compra", Activity.class);
	}

	@Override
	public Activity getCura() {
		return Context.getInstance().getBean("cura", Activity.class);
	}

	@Override
	public Activity getDestete() {
		return Context.getInstance().getBean("destete", Activity.class);
	}

	@Override
	public Activity getDesteteCrias() {
		return Context.getInstance().getBean("desteteCrias", Activity.class);
	}

	@Override
	public Activity getDiagnosticoNoPreniada() {
		return Context.getInstance().getBean("diagnosticoNoPreniada", Activity.class);
	}

	@Override
	public Activity getDiagnosticoPreniada() {
		return Context.getInstance().getBean("diagnosticoPreniada", Activity.class);
	}

	@Override
	public Activity getEnfermedad() {
		return Context.getInstance().getBean("enfermedad", Activity.class);
	}

	@Override
	public Activity getJunta() {
		return Context.getInstance().getBean("junta", Activity.class);
	}

	@Override
	public Activity getMonturaHembra() {
		return Context.getInstance().getBean("monturaHembra", Activity.class);
	}

	@Override
	public Activity getMonturaMacho() {
		return Context.getInstance().getBean("monturaMacho", Activity.class);
	}

	@Override
	public Activity getMudanza() {
		return Context.getInstance().getBean("mudanza", Activity.class);
	}

	@Override
	public Activity getMuerte() {
		return Context.getInstance().getBean("muerte", Activity.class);
	}

	@Override
	public Activity getNacimiento() {
		return Context.getInstance().getBean("nacimiento", Activity.class);
	}

	@Override
	public Activity getParto() {
		return Context.getInstance().getBean("parto", Activity.class);
	}

	@Override
	public Activity getSacrificio() {
		return Context.getInstance().getBean("sacrificio", Activity.class);
	}

	@Override
	public Activity getSexadoProductor() {
		return Context.getInstance().getBean("sexadoProductor", Activity.class);
	}

	@Override
	public Activity getSexadoReproductor() {
		return Context.getInstance().getBean("sexadoReproductor", Activity.class);
	}

	@Override
	public Activity getVenta() {
		return Context.getInstance().getBean("venta", Activity.class);
	}
}
