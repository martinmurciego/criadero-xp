package ar.uba.fi.criaderoxp.domain.repository;

import ar.uba.fi.criaderoxp.domain.model.Activity;

/**
 * Pemite acceder a las WKI de Activity
 * 
 * @author mmazzei
 * @category Repository
 */
public interface ActivityRepository {
	public Activity getNacimiento();

	public Activity getCompra();

	public Activity getMuerte();

	public Activity getCura();

	public Activity getEnfermedad();

	public Activity getVenta();

	public Activity getSacrificio();

	public Activity getMudanza();

	public Activity getDesteteCrias();

	public Activity getParto();

	public Activity getDiagnosticoNoPreniada();

	public Activity getDiagnosticoPreniada();

	public Activity getMonturaHembra();

	public Activity getMonturaMacho();

	public Activity getJunta();

	public Activity getSexadoReproductor();

	public Activity getSexadoProductor();

	public Activity getDestete();
}
