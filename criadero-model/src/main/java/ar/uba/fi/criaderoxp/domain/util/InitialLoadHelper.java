package ar.uba.fi.criaderoxp.domain.util;

import ar.uba.fi.criaderoxp.BeanList;

/**
 * Provee funcionalidad útil para la carga inicial de datos del sistema.
 * 
 * @author mmazzei
 */
public interface InitialLoadHelper {
	/**
	 * Este método se ejecuta recursivamente sobre cada {@link BeanList} contenido por el beanList
	 * persistiendo todos sus beans.
	 * 
	 * @param beanList
	 *            Conjunto de beans a almacenar.
	 */
	public void persistAllBeans(BeanList beanList);
}
