package ar.uba.fi.criaderoxp.domain.facturacion;

import ar.uba.fi.criaderoxp.domain.criadero.Individuo;

/**
 * Representa un elemento de la factura. Para simplificar, consideraré que lo
 * único que vende un criadero son animales.
 */
public class Item {
	private Float precio;
	private Individuo individuo;

	public Item(Individuo individuo, Float precio) {
		this.precio = precio;
		this.individuo = individuo;
	}

	/** @return El precio al que se vendió el individuo. */
	public Float getPrecio() {
		return precio;
	}

	/** @return El animal vendido. */
	public Individuo getIndividuo() {
		return individuo;
	}

}
