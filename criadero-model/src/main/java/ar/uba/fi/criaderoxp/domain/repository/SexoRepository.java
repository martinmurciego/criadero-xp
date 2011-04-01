package ar.uba.fi.criaderoxp.domain.repository;

import java.util.List;

import ar.uba.fi.criaderoxp.domain.model.Sexo;

/**
 * Pemite acceder a las WKI de Sexo
 * 
 * @author mmazzei
 * @category Repository
 */
public interface SexoRepository {
	public List<Sexo> findAll();

	public Sexo getMacho();

	public Sexo getHembra();
}