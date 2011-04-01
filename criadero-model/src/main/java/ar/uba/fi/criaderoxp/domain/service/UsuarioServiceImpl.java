package ar.uba.fi.criaderoxp.domain.service;

import java.util.HashMap;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.uba.fi.criaderoxp.domain.security.Rol;
import ar.uba.fi.criaderoxp.domain.security.Usuario;

@Service
public class UsuarioServiceImpl implements UsuarioService {
	@PersistenceContext
	private EntityManager em;

	private static final HashMap<String, Usuario> tablaUsuarios;
	static {
		tablaUsuarios = new HashMap<String, Usuario>();
		Rol admin = new Rol("admin");
		Rol jefe = new Rol("jefe");
		Rol basic = new Rol("basic");

		Usuario root = new Usuario("admin", "admin");
		root.getRoles().add(admin);
		tablaUsuarios.put(root.getUsername(), root);

		Usuario jboss = new Usuario("jboss", "jboss");
		jboss.getRoles().add(jefe);
		jboss.getRoles().add(basic);
		tablaUsuarios.put(jboss.getUsername(), jboss);

		Usuario pepe = new Usuario("pepe", "pepe");
		pepe.getRoles().add(basic);
		tablaUsuarios.put(pepe.getUsername(), pepe);
	}

	@Transactional
	@Override
	public Usuario getUsuario(String username) {
		return tablaUsuarios.get(username);
		// Session session = (Session) em.getDelegate();
		// SimpleExpression restriccion = Property.forName("username").eq(username);
		// Criteria criteria = session.createCriteria(Usuario.class).add(restriccion);
		// return (Usuario) criteria.uniqueResult();
	}

	@Transactional
	@Override
	public void save(Usuario usuario) {
		em.persist(usuario);
	}

}
