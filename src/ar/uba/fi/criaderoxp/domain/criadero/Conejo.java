package ar.uba.fi.criaderoxp.domain.criadero;

import java.util.Date;

import ar.uba.fi.criaderoxp.domain.exception.BusinessException;

/**
 * Organizar conejos es el objetivo principal del sistema; por lo tanto, esta
 * clase será la fuente principal de información. <br />
 * Estos animales pasan por diversos estados a lo largo de su ciclo de vida, el
 * diagrama que describe el flujo puede encontrarse en {@link WorkflowConejos}.
 * 
 * @author mmazzei
 */
public class Conejo {
	private Date fechaNacimiento;
	private Estado estado = EstadoHelperQUITAR.nullObjectEstado;
	private Sexo sexo;
	private Conejo pareja;

	// Solución transitoria para representar estados concurrentes (al mismo
	// tiempo que amamanta, una hembra puede juntarse y quedar preñada).
	private boolean isAmamantando;
	private Estado estadoAnterior;

	/*-------------------------------------------------------------------------
	 * 							GETTERS y SETTERS
	 ------------------------------------------------------------------------*/
	public Estado getEstado() {
		return estado;
	}

	private boolean isMacho() {
		return this.sexo.equals(Sexo.MACHO);
	}

	private boolean isAmamantando() {
		return this.isAmamantando;
	}

	/*-------------------------------------------------------------------------
	 * 								COMPORTAMIENTO
	 ------------------------------------------------------------------------*/
	/** Indica que el conejo acaba de nacer, con lo que pasa a ser un gazapo. */
	public void nacer() {
		if (!EstadoHelperQUITAR.gazapo.isValido(this.estado)) {
			throw new BusinessException("Estado inválido.");
		}

		this.fechaNacimiento = new Date();
		this.estado = EstadoHelperQUITAR.gazapo;
	}

	/** Desteta al conejo, dejándolo en engorde. */
	public void destetar() {
		if (!EstadoHelperQUITAR.engorde.isValido(this.estado)) {
			throw new BusinessException("Estado inválido.");
		}

		this.estado = EstadoHelperQUITAR.engorde;
	}

	/**
	 * Al tener edad suficiente, los conejos son sexados y en este momento puede
	 * decidirse cuáles pasan a ser reproductores.
	 */
	public void sexar(Sexo sexo, boolean esReproductor) {
		Estado nuevoEstado = esReproductor ? EstadoHelperQUITAR.enEspera
				: EstadoHelperQUITAR.productor;
		if (!nuevoEstado.isValido(this.estado)) {
			throw new BusinessException("Estado inválido.");
		}

		this.sexo = sexo;
		this.estado = nuevoEstado;
	}

	/**
	 * Un conejo reproductor puede juntarse con otro de distinto sexo para que
	 * se reproduzcan.
	 * 
	 * @param pareja
	 *            Conejo con el que se junta.
	 */
	public void juntar(Conejo pareja) {
		if (!EstadoHelperQUITAR.juntado.isValido(this.estado)) {
			throw new BusinessException("Estado inválido.");
		}
		if (this.sexo.equals(pareja.sexo)) {
			throw new BusinessException(
					"No pueden juntarse dos conejos del mismo sexo.");
		}

		this.pareja = pareja;
		this.estado = EstadoHelperQUITAR.juntado;
	}

	/**
	 * Deja al animal en estado "Montado", sólo si es hembra. Si no,
	 * "En espera".
	 */
	public void montura() {
		Estado nuevoEstado = this.isMacho() ? EstadoHelperQUITAR.enEspera
				: EstadoHelperQUITAR.montado;
		if (!nuevoEstado.isValido(this.estado)) {
			throw new BusinessException("Estado inválido.");
		}

		// Se desasigna la pareja ya que luego de la montura se cambian de jaula
		this.pareja = null;
		this.estado = nuevoEstado;
	}

	/**
	 * Utilizado para notificar el diagnóstico de la preñez de la hembra luego
	 * de ser montada.
	 */
	public void diagnosticar(boolean isPreniada) {
		Estado nuevoEstado = isPreniada ? EstadoHelperQUITAR.preniado
				: EstadoHelperQUITAR.enEspera;
		if (!nuevoEstado.isValido(this.estado)) {
			throw new BusinessException("Estado inválido.");
		}

		this.estado = nuevoEstado;
	}

	/**
	 * Desde aquí se deja a la hembra amamantando una nueva camada.
	 */
	public void parir() {
		if (!EstadoHelperQUITAR.amamantando.isValido(this.estado)) {
			throw new BusinessException("Estado inválido.");
		}

		this.isAmamantando = true;
		this.estado = EstadoHelperQUITAR.amamantando;
	}

	/** Deja al animal en estado "En espera". */
	public void destetarCrias() {
		if (!EstadoHelperQUITAR.enEspera.isValido(this.estado)
				|| (!this.isAmamantando())) {
			throw new BusinessException("Estado inválido.");
		}

		this.estado = EstadoHelperQUITAR.enEspera;
	}

	/**
	 * Al sacrificar un animal, éste queda preprado para que pueda utilizarse su
	 * carne y cuero.
	 */
	public void sacrificar() {
		if (!EstadoHelperQUITAR.sacrificado.isValido(this.estado)) {
			throw new BusinessException("Estado inválido.");
		}

		this.estado = EstadoHelperQUITAR.sacrificado;
	}

	public void vender() {
		if (!EstadoHelperQUITAR.vendido.isValido(this.estado)) {
			throw new BusinessException("Estado inválido.");
		}

		this.estado = EstadoHelperQUITAR.vendido;
	}

	public void enfermar() {
		if (!EstadoHelperQUITAR.enfermo.isValido(this.estado)) {
			throw new BusinessException("Estado inválido.");
		}

		this.estadoAnterior = this.estado;
		this.estado = EstadoHelperQUITAR.enfermo;
	}

	public void curar() {
		if (!this.estadoAnterior.isValido(this.estado)) {
			throw new BusinessException("Estado inválido.");
		}

		this.estado = this.estadoAnterior;
	}

	/**
	 * Indica la muerte de un animal. A partir de ese momento no podrá
	 * modificarse su estado.
	 */
	public void morir() {
		if (!EstadoHelperQUITAR.muerto.isValido(this.estado)) {
			throw new BusinessException("Estado inválido.");
		}

		this.estado = EstadoHelperQUITAR.muerto;
	}

}
