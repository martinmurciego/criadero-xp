package ar.uba.fi.criaderoxp.domain.repository;

import java.util.ArrayList;
import java.util.List;

import ar.uba.fi.criaderoxp.domain.model.Sexo;

/**
 * Implementación del repositorio de sexos que no necesita acceder a la base de datos.
 * 
 * @author mmazzei
 */
public class SexoRepositoryDummy implements SexoRepository {
	private Sexo hembra = new Sexo("hembra");
	private Sexo macho = new Sexo("macho");

	public List<Sexo> findAll() {
		ArrayList<Sexo> list = new ArrayList<Sexo>(2);
		list.add(macho);
		list.add(hembra);
		return list;
	}

	@Override
	public Sexo getHembra() {
		return hembra;
	}

	@Override
	public Sexo getMacho() {
		return macho;
	}

}
