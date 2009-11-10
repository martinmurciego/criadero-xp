package ar.uba.fi.criaderoxp.domain.criadero;

import java.util.Date;

import ar.uba.fi.criaderoxp.domain.event.Agenda;
import ar.uba.fi.criaderoxp.domain.event.TipoEventoHelperQUITAR;
import ar.uba.fi.criaderoxp.domain.exception.BusinessException;
import ar.uba.fi.criaderoxp.domain.factory.EstadoHelperQUITAR;
import ar.uba.fi.criaderoxp.domain.model.Camada;
import ar.uba.fi.criaderoxp.domain.model.Estado;

/**
 * Clase que representa a todos los animales que hayan vivido en el criadero.
 * 
 * @author mmazzei
 */
@Deprecated
public abstract class Animal {
	private Date fechaNacimiento;
	private Date fechaMuerte;
	private Float peso;
	private Sexo sexo;
	private Agenda registro;
	private Animal pareja;
	private Estado estado;
	private Estado estadoAnterior;
	private Enfermedad enfermedad;

	public Animal() {
		this.estado = EstadoHelperQUITAR.nullObjectEstado;
		this.estadoAnterior = EstadoHelperQUITAR.nullObjectEstado;
	}

	/*-------------------------------------------------------------------------
	 * 							GETTERS Y SETTERS
	 ------------------------------------------------------------------------*/
	/** @return La fecha en que nació. */
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	/** @return El peso actual del individuo. */
	public Float getPeso() {
		return peso;
	}

	public void setPeso(Float peso) {
		this.peso = peso;
	}

	/*-------------------------------------------------------------------------
	 * 									LÓGICA
	 ------------------------------------------------------------------------*/
//	/** Deja al animal en estado "Gazapo". */
//	public void nacer(String descripcion) {
//		if (!EstadoHelperQUITAR.gazapo.isValido(this.estado)) {
//			throw new BusinessException("Estado inválido.");
//		}
//
//		this.registro.addEvento(TipoEventoHelperQUITAR.nacimiento, descripcion);
//		this.fechaNacimiento = new Date();
//		this.estado = EstadoHelperQUITAR.gazapo;
//	}
//
//	/**
//	 * Deja al animal en el estado indicado.
//	 * 
//	 * @param estadoCompra
//	 *            El estado en que se adquirió al animal.
//	 */
//	public void comprar(String descripcion, Estado estadoCompra) {
//		if (!estadoCompra.isValido(this.estado)) {
//			throw new BusinessException("Estado inválido.");
//		}
//
//		this.registro.addEvento(TipoEventoHelperQUITAR.compra, descripcion);
//		this.estado = estadoCompra;
//	}
//
//	/** Deja al animal en estado "Engorde". */
//	public void destetar(String descripcion) {
//		if (!EstadoHelperQUITAR.engorde.isValido(this.estado)) {
//			throw new BusinessException("Estado inválido.");
//		}
//
//		this.registro.addEvento(TipoEventoHelperQUITAR.destete, descripcion);
//		this.estado = EstadoHelperQUITAR.engorde;
//	}
//
//	/**
//	 * @param sexo
//	 *            Género detectado del animal.
//	 * @param esReproductor
//	 *            {@link Boolean#TRUE} si debe pasarse al animal a
//	 *            "Reproductor". {@link Boolean#FALSE} en caso de "Productor".
//	 */
//	public void sexar(String descripcion, Sexo sexo, Boolean esReproductor) {
//		Estado nuevoEstado = esReproductor ? EstadoHelperQUITAR.enEspera
//				: EstadoHelperQUITAR.productor;
//		if (!nuevoEstado.isValido(this.estado)) {
//			throw new BusinessException("Estado inválido.");
//		}
//
//		this.registro.addEvento(TipoEventoHelperQUITAR.sexado, descripcion);
//		this.sexo = sexo;
//		this.estado = nuevoEstado;
//	}
//
//	/** Deja al animal y su pareja en estado "Juntado". */
//	public void juntar(String descripcion, Animal pareja) {
//		if (!EstadoHelperQUITAR.juntado.isValido(this.estado)) {
//			throw new BusinessException("Estado inválido.");
//		}
//
//		this.registro.addEvento(TipoEventoHelperQUITAR.junta, descripcion);
//		this.pareja = pareja;
//		this.estado = EstadoHelperQUITAR.juntado;
//
//		// Evalúo la condición para no quedar en una recursión indirecta
//		// infinita con pareja.juntar()
//		if (this.pareja.estado != EstadoHelperQUITAR.juntado) {
//			pareja.juntar(descripcion, this);
//			pareja.estado = EstadoHelperQUITAR.juntado;
//		}
//	}
//
//	/**
//	 * Deja al animal en estado "Montado", sólo si es hembra. Si no,
//	 * "En espera".
//	 */
//	public void montura(String descripcion) {
//		if (!EstadoHelperQUITAR.montado.isValido(this.estado)) {
//			throw new BusinessException("Estado inválido.");
//		}
//
//		this.registro.addEvento(TipoEventoHelperQUITAR.montura, descripcion);
//		this.estado = EstadoHelperQUITAR.montado;
//	}
//
//	/**
//	 * Sólo es válido para hembras.
//	 * 
//	 * @param estaPreniada
//	 *            Si es {@link Boolean#TRUE}, deja al animal en estado
//	 *            "Preñado", si no, "En espera"
//	 */
//	public void diagnosticar(String descripcion, Boolean estaPreniada) {
//		Estado nuevoEstado = estaPreniada ? EstadoHelperQUITAR.preniado
//				: EstadoHelperQUITAR.enEspera;
//		// TODO (mmazzei) - Evaluar que sea macho.
//		if (!nuevoEstado.isValido(this.estado) /* || this.sexo == MACHO */) {
//			throw new BusinessException("Estado inválido.");
//		}
//
//		this.registro
//				.addEvento(TipoEventoHelperQUITAR.diagnostico, descripcion);
//		this.estado = nuevoEstado;
//	}
//
//	/**
//	 * Deja al animal en estado "Amamantando", sólo para hembras.
//	 * 
//	 * @return La camada parida.
//	 */
//	public Camada parir(String descripcion) {
//		if (!EstadoHelperQUITAR.amamantando.isValido(this.estado)) {
//			throw new BusinessException("Estado inválido.");
//		}
//
//		this.registro.addEvento(TipoEventoHelperQUITAR.parto, descripcion);
//		this.estado = EstadoHelperQUITAR.amamantando;
//		// return new Camada(this, this.pareja);
//		return null;
//	}
//
//	/** Deja al animal en estado "En espera". */
//	public void destetarCrias(String descripcion) {
//		if (!EstadoHelperQUITAR.enEspera.isValido(this.estado)) {
//			throw new BusinessException("Estado inválido.");
//		}
//
//		this.registro.addEvento(TipoEventoHelperQUITAR.desteteCrias,
//				descripcion);
//		this.estado = EstadoHelperQUITAR.enEspera;
//	}
//
//	/** Deja al animal en estado "Enfermo". */
//	public void enfermar(String descripcion, Enfermedad enfermedad) {
//		if (!EstadoHelperQUITAR.enfermo.isValido(this.estado)) {
//			throw new BusinessException("Estado inválido.");
//		}
//
//		this.registro.addEvento(TipoEventoHelperQUITAR.enfermedad, descripcion);
//		this.estadoAnterior = this.estado;
//		this.estado = EstadoHelperQUITAR.enfermo;
//		this.enfermedad = enfermedad;
//	}
//
//	/** Deja al animal en el último estado "Sano" en que estuvo. */
//	public void curar(String descripcion) {
//		if (!this.estadoAnterior.isValido(this.estado)) {
//			throw new BusinessException("Estado inválido.");
//		}
//
//		this.registro.addEvento(TipoEventoHelperQUITAR.cura, descripcion);
//		this.estado = this.estadoAnterior;
//		this.estadoAnterior = EstadoHelperQUITAR.nullObjectEstado;
//		this.enfermedad = null;
//	}
//
//	/** Deja al animal en estado "Sacrificado". */
//	public void sacrificar(String descripcion) {
//		if (!EstadoHelperQUITAR.sacrificado.isValido(this.estado)) {
//			throw new BusinessException("Estado inválido.");
//		}
//
//		this.registro.addEvento(TipoEventoHelperQUITAR.sacrificio, descripcion);
//		this.fechaMuerte = new Date();
//		this.estado = EstadoHelperQUITAR.sacrificado;
//	}
//
//	/** Deja al animal en estado "Vendido". */
//	public void vender(String descripcion) {
//		if (!EstadoHelperQUITAR.vendido.isValido(this.estado)) {
//			throw new BusinessException("Estado inválido.");
//		}
//
//		this.registro.addEvento(TipoEventoHelperQUITAR.venta, descripcion);
//		this.estado = EstadoHelperQUITAR.vendido;
//	}
//
//	/** Deja al animal en estado "Muerto". */
//	public void morir(String descripcion) {
//		if (!EstadoHelperQUITAR.muerto.isValido(this.estado)) {
//			throw new BusinessException("Estado inválido.");
//		}
//
//		this.registro.addEvento(TipoEventoHelperQUITAR.muerte, descripcion);
//		this.fechaMuerte = new Date();
//		this.estado = EstadoHelperQUITAR.muerto;
//	}

	/*-------------------------------------------------------------------------
	 * 									HELPERS
	 ------------------------------------------------------------------------*/
}
