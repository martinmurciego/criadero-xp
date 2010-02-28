package ar.uba.fi.criaderoxp.domain.model;

import java.util.Date;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ar.uba.fi.criaderoxp.domain.exception.BusinessException;
import ar.uba.fi.criaderoxp.domain.exception.InvalidStateException;

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
	BeanFactory activities = new ClassPathXmlApplicationContext("applicationContext.xml");
	private Date fechaNacimiento;
	private Estado estado = activities.getBean("nullObjectEstado", Estado.class);
	private Sexo sexo;
	private Conejo pareja;
	private Registro registro = new Registro();

	// Solución transitoria para representar estados concurrentes (al mismo
	// tiempo que amamanta, una hembra puede juntarse y quedar preñada).
	private boolean isAmamantando = false;

	// Solución para volver al estado anterior desde cuando un enfermo se cura
	private Estado estadoAnterior = activities.getBean("nullObjectEstado", Estado.class);

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

	/** @return El registro de toda la actividad del conejo. */
	public Registro getRegistro() {
		return this.registro;
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
		activities.getBean("nacimiento", Activity.class).execute(this);
		this.fechaNacimiento = new Date();
	}

	/**
	 * Desteta al conejo, dejándolo en engorde.
	 * 
	 * @throws InvalidStateException
	 */
	public void destetar() {
		activities.getBean("destete", Activity.class).execute(this);
	}

	/**
	 * Al tener edad suficiente, los conejos son sexados y en este momento puede
	 * decidirse cuáles pasan a ser reproductores.
	 * 
	 * @throws InvalidStateException
	 */

	public void sexar(Sexo sexo, boolean esReproductor) {
		if (esReproductor) {
			activities.getBean("sexadoReproductor", Activity.class).execute(this);
		} else {
			activities.getBean("sexadoProductor", Activity.class).execute(this);
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
		activities.getBean("junta", Activity.class).execute(this);
		if (this.sexo.equals(pareja.sexo)) {
			throw new BusinessException("No pueden juntarse dos conejos del mismo sexo.");
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
			activities.getBean("monturaMacho", Activity.class).execute(this);
		} else {
			activities.getBean("monturaHembra", Activity.class).execute(this);
		}

		// Se desasigna la pareja del macho ya que luego de la montura se lo
		// cambia de jaula. Pero queda en la hembra para luego poder determinar
		// el padre de la futura camada.
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
			activities.getBean("diagnosticoPreniada", Activity.class).execute(this);
		} else {
			activities.getBean("diagnosticoNoPreniada", Activity.class).execute(this);
		}
	}

	/**
	 * Desde aquí se deja a la hembra amamantando una nueva camada.
	 * 
	 * @throws InvalidStateException
	 */
	public void parir() {
		activities.getBean("parto", Activity.class).execute(this);
		this.isAmamantando = true;
	}

	/**
	 * Deja al animal en estado "En espera".
	 * 
	 * @throws InvalidStateException
	 */
	public void destetarCrias() {
		activities.getBean("desteteCrias", Activity.class).execute(this);
		if (!this.isAmamantando()) {
			throw new InvalidStateException();
		}
		this.isAmamantando = false;
	}

	/** @throws InvalidStateException */
	public void mudar(Jaula destino) {
		activities.getBean("mudanza", Activity.class).execute(this);
		destino.add(this);
	}

	/**
	 * Al sacrificar un animal, éste queda preprado para que pueda utilizarse su
	 * carne y cuero.
	 * 
	 * @throws InvalidStateException
	 */
	public void sacrificar() {
		activities.getBean("sacrificio", Activity.class).execute(this);
	}

	/** @throws InvalidStateException */
	public void vender() {
		activities.getBean("venta", Activity.class).execute(this);
	}

	/** @throws InvalidStateException */
	public void enfermar() {
		this.estadoAnterior = this.estado;
		activities.getBean("enfermedad", Activity.class).execute(this);
	}

	/** @throws InvalidStateException */
	public void curar() {
		activities.getBean("cura", Activity.class).execute(this);
		this.estado = this.estadoAnterior;
	}

	/**
	 * Indica la muerte de un animal. A partir de ese momento no podrá
	 * modificarse su estado.
	 * 
	 * @throws InvalidStateException
	 */
	public void morir() {
		activities.getBean("muerte", Activity.class).execute(this);
	}
}
