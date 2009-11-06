package ar.uba.fi.criaderoxp.domain.property;

/**
 * Algunos objetos tienen varias propiedades que se asignan dinámicamente, según
 * el contexto o su estado. Esta clase es utilizada para almacenar esta
 * información.
 */
public interface Propiedad {
	/**
	 * @return El nombre del dato representado.
	 */
	public String getNombre();

	/**
	 * @return El valor de la propiedad.
	 */
	public Object getValor();
}
