package ar.uba.fi.criaderoxp.domain.repository;

import java.util.List;

import ar.uba.fi.criaderoxp.domain.model.Estado;

/**
 * Pemite acceder a las WKI de Estado
 * 
 * @author mmazzei
 * @category Repository
 */
// TODO (mmazzei) - Ordenar, documentar y completar
public interface EstadoRepository {

	public Estado getNullObject();

	public Estado getEngorde();

	public Estado getGazapo();

	public Estado getEnEspera();

	public Estado getProductor();

	public Estado getJuntado();

	public Estado getSacrificado();

	public Estado getVendido();

	public Estado getAmamantando();

	public Estado getPreniado();

	/** @return Todos los estados que representan a un conejo sano. */
	public List<Estado> findSano();
}