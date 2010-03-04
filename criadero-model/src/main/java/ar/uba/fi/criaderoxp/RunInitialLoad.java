package ar.uba.fi.criaderoxp;

import ar.uba.fi.criaderoxp.domain.util.Context;
import ar.uba.fi.criaderoxp.domain.util.InitialLoadHelper;

/**
 * Ejecuta la carga inicial de los datos requeridos por el sistema, tal como se definen en los
 * archivos de configuración.
 * 
 * @author mmazzei
 */
public class RunInitialLoad {
	private InitialLoadHelper helper;

	public static void main(String[] args) {
		RunInitialLoad program = new RunInitialLoad();
		program.run();
		System.exit(0);
	}

	private void run() {
		Context.getInstance().setApplicationContext("initialLoad.xml");
		this.helper = Context.getInstance().getBean("initialLoadHelper", InitialLoadHelper.class);
		BeanList initialLoadBeanList = Context.getInstance().getBean("initialLoad", BeanList.class);
		helper.persistAllBeans(initialLoadBeanList);
	}
}