package ar.uba.fi.criaderoxp.domain.model;

import java.util.Date;

import ar.uba.fi.criaderoxp.domain.criadero.Sexo;
import ar.uba.fi.criaderoxp.domain.exception.BusinessException;
import ar.uba.fi.criaderoxp.domain.exception.InvalidStateException;
import ar.uba.fi.criaderoxp.domain.factory.ActivityHelperQUITAR;
import ar.uba.fi.criaderoxp.domain.factory.EstadoHelperQUITAR;

/**
 * Organizar conejos es el objetivo principal del sistema; por lo tanto, esta
 * clase será la fuente principal de información. <br />
 * Estos animales pasan por diversos estados a lo largo de su ciclo de vida, el
 * diagrama que describe el flujo puede encontrarse en {@link WorkflowConejos}.
 * 
 * @author mmazzei
 * @category Aggregate Root
 */
public class Conejo {
	private Date fechaNacimiento;
	private Estado estado = EstadoHelperQUITAR.nullObjectEstado;
	private Sexo sexo;
	private Conejo pareja;

	// Solución transitoria para representar estados concurrentes (al mismo
	// tiempo que amamanta, una hembra puede juntarse y quedar preñada).
	private boolean isAmamantando = false;
	private Estado estadoAnterior = EstadoHelperQUITAR.nullObjectEstado;

	/*-------------------------------------------------------------------------
	 * 							GETTERS y SETTERS
	 ------------------------------------------------------------------------*/
	/** @return El estado actual del conejo. */
	public Estado getEstado() {
		return estado;
	}

	/** Este método no debería ser utilizado más que desde {@link Activity} */
	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	/** @return El último conejo con el que se juntó este. */
	public Conejo getPareja() {
		return this.pareja;
	}

	private boolean isMacho() {
		return this.sexo.equals(Sexo.MACHO);
	}

	/** @return true si la hembra ha parido y aún no ha sido destetada la camada. */
	private boolean isAmamantando() {
		return this.isAmamantando;
	}

	/*-------------------------------------------------------------------------
	 * 								COMPORTAMIENTO
	 ------------------------------------------------------------------------*/
	/**
	 * Indica que el conejo acaba de nacer, con lo que pasa a ser un gazapo.
	 * 
	 * @throws InvalidStateException
	 */
	public void nacer() {
		ActivityHelperQUITAR.nacimiento.execute(this);
		this.fechaNacimiento = new Date();
	}

	/**
	 * Desteta al conejo, dejándolo en engorde.
	 * 
	 * @throws InvalidStateException
	 */
	public void destetar() {
		ActivityHelperQUITAR.destete.execute(this);
	}

	/**
	 * Al tener edad suficiente, los conejos son sexados y en este momento puede
	 * decidirse cuáles pasan a ser reproductores.
	 * 
	 * @throws InvalidStateException
	 */

	public void sexar(Sexo sexo, boolean esReproductor) {
		if (esReproductor) {
			ActivityHelperQUITAR.sexadoReproductor.execute(this);
		} else {
			ActivityHelperQUITAR.sexadoProductor.execute(this);
		}

		this.sexo = sexo;
	}

	/**
	 * Un conejo reproductor puede juntarse con otro de distinto sexo para que
	 * se reproduzcan.
	 * 
	 * @param pareja
	 *            Conejo con el que se junta.
	 * @throws InvalidStateException
	 * @throws BusinessException
	 *             En caso de que la pareja sea del mismo sexo.
	 */
	public void juntar(Conejo pareja) {
		ActivityHelperQUITAR.junta.execute(this);
		if (this.sexo.equals(pareja.sexo)) {
			throw new BusinessException(
					"No pueden juntarse dos conejos del mismo sexo.");
		}

		this.pareja = pareja;
	}

	/**
	 * Deja al animal en estado "Montado", sólo si es hembra. Si no,
	 * "En espera".
	 * 
	 * @throws InvalidStateException
	 */
	public void montura() {
		if (this.isMacho()) {
			ActivityHelperQUITAR.monturaMacho.execute(this);
		} else {
			ActivityHelperQUITAR.monturaHembra.execute(this);
		}

		// Se desasigna la pareja del macho ya que luego de la montura se
		// lo cambia de jaula.
		// Pero queda en la hembra para luego poder determinar el padre de
		// la futura camada.
		if (this.isMacho()) {
			this.pareja = null;
		}
	}

	/**
	 * Utilizado para notificar el diagnóstico de la preñez de la hembra luego
	 * de ser montada.
	 * 
	 * @throws InvalidStateException
	 */
	public void diagnosticar(boolean isPreniada) {
		if (isPreniada) {
			ActivityHelperQUITAR.diagnosticoPreniada.execute(this);
		} else {
			ActivityHelperQUITAR.diagnosticoNoPreniada.execute(this);
		}
	}

	/**
	 * Desde aquí se deja a la hembra amamantando una nueva camada.
	 * 
	 * @throws InvalidStateException
	 */
	public void parir() {
		ActivityHelperQUITAR.parto.execute(this);

		this.isAmamantando = true;
	}

	/**
	 * Deja al animal en estado "En espera".
	 * 
	 * @throws InvalidStateException
	 */
	public void destetarCrias() {
		ActivityHelperQUITAR.desteteCrias.execute(this);
		if (!this.isAmamantando()) {
			throw new InvalidStateException();
		}
		this.isAmamantando = false;
	}

	/** @throws InvalidStateException */
	public void mudar(Jaula destino) {
		ActivityHelperQUITAR.mudanza.execute(this);
		destino.add(this);
	}

	/**
	 * Al sacrificar un animal, éste queda preprado para que pueda utilizarse su
	 * carne y cuero.
	 * 
	 * @throws InvalidStateException
	 */
	public void sacrificar() {
		ActivityHelperQUITAR.sacrificio.execute(this);
	}

	/** @throws InvalidStateException */
	public void vender() {
		ActivityHelperQUITAR.venta.execute(this);
	}

	/** @throws InvalidStateException */
	public void enfermar() {
		this.estadoAnterior = this.estado;
		ActivityHelperQUITAR.enfermedad.execute(this);
	}

	/** @throws InvalidStateException */
	public void curar() {
		ActivityHelperQUITAR.cura.execute(this);
		this.estado = this.estadoAnterior;
	}

	/**
	 * Indica la muerte de un animal. A partir de ese momento no podrá
	 * modificarse su estado.
	 * 
	 * @throws InvalidStateException
	 */
	public void morir() {
		ActivityHelperQUITAR.muerte.execute(this);
	}
}
