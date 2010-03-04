package ar.uba.fi.criaderoxp.domain.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.uba.fi.criaderoxp.domain.exception.BusinessException;
import ar.uba.fi.criaderoxp.domain.model.Camada;
import ar.uba.fi.criaderoxp.domain.model.Conejo;

@Service
public class ConejoServiceImpl implements ConejoService {
	@PersistenceContext
	private EntityManager em;

	@Transactional
	@Override
	public void crearPareja(Conejo hembra, Conejo macho) {
		hembra.juntar(macho);
		macho.juntar(hembra);
	}

	@Transactional
	@Override
	public void montar(Conejo hembra) {
		hembra.montura();
		hembra.getPareja().montura();
	}

	@Transactional
	@Override
	public Camada crearCamada(Conejo madre, int tamanio) {
		if (tamanio == 0) {
			throw new BusinessException("La camada debe tener al menos una cría.");
		}
		madre.parir();
		Camada camada = new Camada(madre, madre.getPareja());
		for (int i = 0; i < tamanio; i++) {
			Conejo cria = new Conejo();
			cria.nacer(camada);
			camada.add(cria);
		}

		return camada;
	}
}
