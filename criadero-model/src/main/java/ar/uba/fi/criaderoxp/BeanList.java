package ar.uba.fi.criaderoxp;

import java.util.HashSet;
import java.util.Set;

/**
 * Utilizado para acumular conjuntos de beans (y grupos de conjuntos) de modo de poder luego
 * manipularlos en un proceso batch.
 * 
 * @author mmazzei
 */
public class BeanList {
	private Set<Object> beans = new HashSet<Object>();
	private Set<BeanList> beansList = new HashSet<BeanList>();

	/** @return Los beans contenidos por esta lista. */
	public Set<Object> getBeans() {
		return beans;
	}

	public void setBeans(Set<Object> beans) {
		this.beans = beans;
	}

	/** @return Las listas de beans contenidas por esta lista. */
	public Set<BeanList> getBeansList() {
		return beansList;
	}

	public void setBeansList(Set<BeanList> beansList) {
		this.beansList = beansList;
	}
}
