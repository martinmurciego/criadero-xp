package ar.uba.fi.criaderoxp.domain.util;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.uba.fi.criaderoxp.BeanList;

@Service
public class InitialLoadHelperImpl implements InitialLoadHelper {
	@PersistenceContext
	private EntityManager em;

	@Transactional
	public void persistAllBeans(BeanList beanList) {
		for (BeanList bean : beanList.getBeansList()) {
			persistAllBeans(bean);
		}
		for (Object bean : beanList.getBeans()) {
			em.persist(bean);
		}
	}
}
