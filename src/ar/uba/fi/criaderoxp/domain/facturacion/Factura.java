package ar.uba.fi.criaderoxp.domain.facturacion;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Cada vez que se realiza una venta, el detalle de la misma queda registrado en
 * una factura.
 */
public class Factura {
	private Date fecha;
	private Cliente cliente;
	private Set<Item> items;

	public Factura(Cliente cliente) {
		this.fecha = new Date();
		this.items = new HashSet<Item>();
	}

	public Date getFecha() {
		return fecha;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public Set<Item> getItems() {
		return items;
	}

	/**
	 * Añade un ítem a la factura, sólo si no existe.
	 */
	public void add(Item item) {
		this.items.add(item);
	}

	/**
	 * @return El valor total cobrado por la factura.
	 */
	public Float getMonto() {
		float total = 0;
		Iterator<Item> it = this.items.iterator();
		while (it.hasNext()) {
			total += it.next().getPrecio();
		}
		return total;
	}
}
