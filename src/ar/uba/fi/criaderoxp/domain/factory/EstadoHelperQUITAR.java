package ar.uba.fi.criaderoxp.domain.factory;

import ar.uba.fi.criaderoxp.domain.model.Estado;

/**
 * Esta clase debería ser reemplazada por un andamiaje en Springs o emprolijada
 * de alguna manera.
 * 
 * @author mmazzei
 * @category Factory
 */
@Deprecated
public class EstadoHelperQUITAR {
	final static public Estado gazapo = new Estado("Gazapo");
	final static public Estado engorde = new Estado("Engorde");
	final static public Estado productor = new Estado("Productor");
	final static public Estado enEspera = new Estado("En espera");
	final static public Estado juntado = new Estado("Juntado");
	final static public Estado montado = new Estado("Montado");
	final static public Estado preniado = new Estado("Preniado");
	final static public Estado amamantando = new Estado("Amamantando");
	final static public Estado sacrificado = new Estado("Sacrificado");
	final static public Estado vendido = new Estado("Vendido");
	final static public Estado enfermo = new Estado("Enfermo");
	final static public Estado muerto = new Estado("Muerto");
	final static public Estado nullObjectEstado = new Estado("Null object");
}
