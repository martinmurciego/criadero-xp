package ar.uba.fi.criaderoxp.domain.repository;

import java.util.List;

import ar.uba.fi.criaderoxp.domain.model.Jaula;

/**
 * Pemite acceder a las Jaulas para mostrar opciones en la UI.
 * 
 * @author mmazzei
 * @category Repository
 */
public interface JaulaRepository {
	public List<Jaula> findAll();
}