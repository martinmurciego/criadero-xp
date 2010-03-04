package ar.uba.fi.criaderoxp.domain.util;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ar.uba.fi.criaderoxp.domain.security.Usuario;
import ar.uba.fi.criaderoxp.domain.service.UsuarioService;

/**
 * Esta clase es DUMMY hasta que implemente el módulo de seguridad con Acegi + WASP / SWARM o como
 * sea que se llame el componente de wicket para seguridad. <br/>
 * 
 * El getUsuario de aquí pasaría a ser:
 * SecurityContextHolder.getContext().setAuthentication().getPrincipal(); Y esta clase pasaría al
 * olvido.
 * 
 * @see http://wicketstuff.org/confluence/display/STUFFWIKI/Wicket-Security
 * @see http://wicketstuff.org/confluence/display/STUFFWIKI/Getting+started+with+Swarm
 * @see http://cwiki.apache.org/WICKET/spring-security-and-wicket-auth-roles.html
 * @see http 
 *      ://cwiki.apache.org/WICKET/spring-security-and-wicket-auth-roles.html#SpringSecurityandWicket
 *      -auth-roles-ExampleWicket1.2.6
 * 
 * @author mmazzei
 * 
 */
public class Context {
	private BeanFactory beanFactory = null;
	private static Context instance = null;

	private Context() {
	}

	public static synchronized Context getInstance() {
		if (instance == null) {
			instance = new Context();
		}
		return instance;
	}

	/**
	 * Este método cambia el contexto de toda la aplicación, por lo que debe utilizarse sólo durante
	 * la inicialización de la misma.
	 */
	public void setApplicationContext(String contextName) {
		if (contextName != null) {
			beanFactory = new ClassPathXmlApplicationContext(contextName);
		}
	}

	public Usuario getUsuario() {
		UsuarioService service = this.getBean("usuarioService", UsuarioService.class);
		Usuario pepe = service.getUsuario("pepe");
		if (pepe == null) {
			pepe = new Usuario();
			pepe.setUsername("pepe");
			pepe.setPassword("1234");
			service.save(pepe);
		}
		return pepe;
	}

	/**
	 * @return El bean solicitado.
	 * @throws NullPointerException
	 *             en caso de que no se haya configurado el contexto mediante
	 *             {@link #setApplicationContext(String)} previamente.
	 */
	public <T> T getBean(String name, Class<T> clazz) {
		return beanFactory.getBean(name, clazz);
	}
}
