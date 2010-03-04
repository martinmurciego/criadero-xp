package ar.uba.fi.criaderoxp.domain.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.SimpleExpression;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.uba.fi.criaderoxp.domain.security.Usuario;

@Service
public class UsuarioServiceImpl implements UsuarioService {
	@PersistenceContext
	private EntityManager em;

	@Transactional
	@Override
	public Usuario getUsuario(String username) {
		// Query query = em.createQuery("prueba");//find(Usuario.class, avisoId);
		// get the native hibernate session
		Session session = (Session) em.getDelegate();
		SimpleExpression restriccion = Property.forName("username").eq(username);
		Criteria criteria = session.createCriteria(Usuario.class).add(restriccion);
		return (Usuario) criteria.uniqueResult();
	}

	@Transactional
	@Override
	public void save(Usuario usuario) {
		em.persist(usuario);
	}

}
