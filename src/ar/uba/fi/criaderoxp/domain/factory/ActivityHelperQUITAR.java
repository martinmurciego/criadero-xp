package ar.uba.fi.criaderoxp.domain.factory;

import ar.uba.fi.criaderoxp.domain.model.Activity;
import ar.uba.fi.criaderoxp.domain.model.Estado;

/**
 * Esta clase debería ser reemplazada por un andamiaje en Springs o emprolijada
 * de alguna manera.
 * 
 * @author mmazzei
 * @category Factory
 */
@Deprecated
public class ActivityHelperQUITAR {
	final static public Activity nacimiento;
	final static public Activity destete;
	final static public Activity sexadoProductor;
	final static public Activity sexadoReproductor;
	final static public Activity junta;
	final static public Activity monturaMacho;
	final static public Activity monturaHembra;
	final static public Activity diagnosticoPreniada;
	final static public Activity diagnosticoNoPreniada;
	final static public Activity parto;
	final static public Activity desteteCrias;
	final static public Activity mudanza;
	final static public Activity sacrificio;
	final static public Activity venta;
	final static public Activity enfermedad;
	final static public Activity cura;
	final static public Activity muerte;

	static {
		nacimiento = new Activity("Nacimiento");
		nacimiento.addRequisito(EstadoHelperQUITAR.nullObjectEstado);
		nacimiento.setDestino(EstadoHelperQUITAR.gazapo);

		destete = new Activity("Destete");
		destete.addRequisito(EstadoHelperQUITAR.gazapo);
		destete.setDestino(EstadoHelperQUITAR.engorde);

		sexadoProductor = new Activity("Sexado productor");
		sexadoProductor.addRequisito(EstadoHelperQUITAR.engorde);
		sexadoProductor.setDestino(EstadoHelperQUITAR.productor);

		sexadoReproductor = new Activity("Sexado reproductor");
		sexadoReproductor.addRequisito(EstadoHelperQUITAR.engorde);
		sexadoReproductor.setDestino(EstadoHelperQUITAR.enEspera);

		junta = new Activity("Junta");
		junta.addRequisito(EstadoHelperQUITAR.enEspera);
		junta.addRequisito(EstadoHelperQUITAR.amamantando);
		junta.setDestino(EstadoHelperQUITAR.juntado);

		monturaMacho = new Activity("Montura macho");
		monturaMacho.addRequisito(EstadoHelperQUITAR.juntado);
		monturaMacho.setDestino(EstadoHelperQUITAR.enEspera);

		monturaHembra = new Activity("Montura hembra");
		monturaHembra.addRequisito(EstadoHelperQUITAR.juntado);
		monturaHembra.setDestino(EstadoHelperQUITAR.montado);

		diagnosticoPreniada = new Activity("Diagnostico preniada");
		diagnosticoPreniada.addRequisito(EstadoHelperQUITAR.montado);
		diagnosticoPreniada.setDestino(EstadoHelperQUITAR.preniado);

		diagnosticoNoPreniada = new Activity("Diagnostico no preniada");
		diagnosticoNoPreniada.addRequisito(EstadoHelperQUITAR.montado);
		diagnosticoNoPreniada.setDestino(EstadoHelperQUITAR.enEspera);

		parto = new Activity("Parto");
		parto.addRequisito(EstadoHelperQUITAR.preniado);
		parto.setDestino(EstadoHelperQUITAR.amamantando);

		desteteCrias = new Activity("Destete crias");
		desteteCrias.addRequisito(EstadoHelperQUITAR.amamantando);
		desteteCrias.setDestino(EstadoHelperQUITAR.enEspera);

		mudanza = new Activity("Mudanza");
		mudanza.addRequisito(EstadoHelperQUITAR.gazapo);
		mudanza.addRequisito(EstadoHelperQUITAR.engorde);
		mudanza.addRequisito(EstadoHelperQUITAR.productor);
		mudanza.addRequisito(EstadoHelperQUITAR.preniado);
		mudanza.addRequisito(EstadoHelperQUITAR.enEspera);
		mudanza.addRequisito(EstadoHelperQUITAR.montado);
		mudanza.addRequisito(EstadoHelperQUITAR.juntado);
		mudanza.addRequisito(EstadoHelperQUITAR.amamantando);
		mudanza.addRequisito(EstadoHelperQUITAR.enfermo);

		sacrificio = new Activity("Sacrificio");
		sacrificio.addRequisito(EstadoHelperQUITAR.productor);
		sacrificio.setDestino(EstadoHelperQUITAR.sacrificado);

		venta = new Activity("Venta");
		venta.addRequisito(EstadoHelperQUITAR.gazapo);
		venta.addRequisito(EstadoHelperQUITAR.engorde);
		venta.addRequisito(EstadoHelperQUITAR.productor);
		venta.addRequisito(EstadoHelperQUITAR.preniado);
		venta.addRequisito(EstadoHelperQUITAR.enEspera);
		venta.addRequisito(EstadoHelperQUITAR.montado);
		venta.addRequisito(EstadoHelperQUITAR.juntado);
		venta.addRequisito(EstadoHelperQUITAR.amamantando);
		venta.addRequisito(EstadoHelperQUITAR.sacrificado);
		venta.setDestino(EstadoHelperQUITAR.vendido);

		enfermedad = new Activity("Enfermedad");
		enfermedad.addRequisito(EstadoHelperQUITAR.gazapo);
		enfermedad.addRequisito(EstadoHelperQUITAR.engorde);
		enfermedad.addRequisito(EstadoHelperQUITAR.productor);
		enfermedad.addRequisito(EstadoHelperQUITAR.preniado);
		enfermedad.addRequisito(EstadoHelperQUITAR.enEspera);
		enfermedad.addRequisito(EstadoHelperQUITAR.montado);
		enfermedad.addRequisito(EstadoHelperQUITAR.juntado);
		enfermedad.addRequisito(EstadoHelperQUITAR.amamantando);
		enfermedad.setDestino(EstadoHelperQUITAR.enfermo);

		cura = new Activity("Cura");
		cura.addRequisito(EstadoHelperQUITAR.enfermo);

		muerte = new Activity("Muerte");
		muerte.addRequisito(EstadoHelperQUITAR.gazapo);
		muerte.addRequisito(EstadoHelperQUITAR.engorde);
		muerte.addRequisito(EstadoHelperQUITAR.productor);
		muerte.addRequisito(EstadoHelperQUITAR.preniado);
		muerte.addRequisito(EstadoHelperQUITAR.enEspera);
		muerte.addRequisito(EstadoHelperQUITAR.montado);
		muerte.addRequisito(EstadoHelperQUITAR.juntado);
		muerte.addRequisito(EstadoHelperQUITAR.amamantando);
		muerte.setDestino(EstadoHelperQUITAR.muerto);
	}
}
