package ar.uba.fi.criaderoxp.domain.service;

import ar.uba.fi.criaderoxp.domain.exception.BusinessException;
import ar.uba.fi.criaderoxp.domain.model.Camada;
import ar.uba.fi.criaderoxp.domain.model.Conejo;
import ar.uba.fi.criaderoxp.domain.model.Jaula;
import ar.uba.fi.criaderoxp.domain.repository.CamadaRepository;
import ar.uba.fi.criaderoxp.domain.repository.JaulaRepository;

public class ConejoServiceImpl implements ConejoService {
	private CamadaRepository camadaRepository;
	private JaulaRepository jaulaRepository;

	public void setCamadaRepository(CamadaRepository repository) {
		this.camadaRepository = repository;
	}

	@Override
	public void crearPareja(Conejo hembra, Conejo macho) {
		hembra.juntar(macho);
		macho.juntar(hembra);
	}

	@Override
	public void montar(Conejo hembra) {
		hembra.montura();
		hembra.getPareja().montura();
	}

	@Override
	public Camada crearCamada(Conejo madre, int tamanio) {
		if (tamanio == 0) {
			throw new BusinessException("La camada debe tener al menos una cría.");
		}
		madre.parir();
		Camada camada = new Camada(madre, madre.getPareja());
		for (int i = 0; i < tamanio; i++) {
			Conejo cria = new Conejo();
			cria.nacer();
			camada.add(cria);
		}

		return camada;
	}

	@Override
	public Camada getCamada(Conejo conejo) {
		// TODO (mmazzei) - Cambiar cuando comience con Hibernate en el sistema
		return this.camadaRepository.findByConejo(conejo);
	}

	@Override
	public Jaula getJaula(Conejo conejo) {
		// TODO (mmazzei) - Cambiar cuando comience con Hibernate en el sistema
		return this.jaulaRepository.findByConejo(conejo);
	}
}
