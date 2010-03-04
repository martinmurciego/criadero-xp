package ar.uba.fi.criaderoxp.domain.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import ar.uba.fi.criaderoxp.domain.exception.BusinessException;
import ar.uba.fi.criaderoxp.domain.exception.InvalidStateException;
import ar.uba.fi.criaderoxp.domain.repository.ActivityRepository;
import ar.uba.fi.criaderoxp.domain.repository.EstadoRepository;
import ar.uba.fi.criaderoxp.domain.repository.SexoRepository;
import ar.uba.fi.criaderoxp.domain.util.Context;

/**
 * Organizar conejos es el objetivo principal del sistema; por lo tanto, esta clase será la fuente
 * principal de información. <br />
 * Estos animales pasan por diversos estados a lo largo de su ciclo de vida, el diagrama que
 * describe el flujo puede encontrarse en {@link WorkflowConejos}.
 * 
 * @author mmazzei
 * @category Aggregate Root
 */
@Entity
public class Conejo {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id = null;
	private Date fechaNacimiento;

	// TODO (mmazzei) - Esto era inicializado en nullObject de estado, pero eso requería que
	// el contexto ya hubiera sido cargado, como se ve a continuación:
	// Context.getInstance().getBean("estadoRepository", EstadoRepository.class).getNullObject();
	// Sin embargo, al hacer un "autowired" de las Entities anotadas, Hibernate las instancia para
	// obtener su información por Reflexión (¿?), y este autowired es realizado al configurar el
	// contexto (ver Context.getInstance().setApplicationContext(String)), con lo que da una
	// recursividad indirecta que, evidentemente, explota con un NPE.
	@OneToOne(optional = false)
	private Estado estado = null;

	@OneToOne(optional = false)
	private Sexo sexo;

	@ManyToOne
	private Camada camada;

	@ManyToOne
	private Jaula jaula;

	@OneToOne
	private Conejo pareja;

	@OneToOne(optional = false)
	private Registro registro = new Registro();

	// Solución transitoria para representar estados concurrentes (al mismo
	// tiempo que amamanta, una hembra puede juntarse y quedar preñada).
	private boolean isAmamantando = false;

	// Solución para volver al estado anterior desde cuando un enfermo se cura
	@OneToOne
	private Estado estadoAnterior = null;

	/*-------------------------------------------------------------------------
	 * 							GETTERS y SETTERS
	 ------------------------------------------------------------------------*/
	public Conejo() {
	}

	/** @return La fecha de nacimiento del animal. */
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	/** @return El estado actual del conejo. */
	public Estado getEstado() {
		// Ver comentario en la definición del atributo
		if (estado == null) {
			estado = Context.getInstance().getBean("estadoRepository", EstadoRepository.class).getNullObject();
		}
		return estado;
	}

	/** Este método no debería ser utilizado más que desde {@link Activity} */
	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	/** @return La camada en la que nació el conejo. */
	public Camada getCamada() {
		return this.camada;
	}

	/** @return La jaula en que se encuentra el conejo. */
	public Jaula getJaula() {
		return this.jaula;
	}

	/** @return El sexo del conejo. */
	public Sexo getSexo() {
		return this.sexo;
	}

	/** @return El último conejo con el que se juntó este. */
	public Conejo getPareja() {
		return this.pareja;
	}

	private boolean isMacho() {
		return this.sexo.equals(Context.getInstance().getBean("sexoRepository", SexoRepository.class).getMacho());
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
	public void nacer(Camada camada) {
		Context.getInstance().getBean("activityRepository", ActivityRepository.class).getNacimiento().execute(this);
		this.fechaNacimiento = new Date();
		this.camada = camada;
	}

	/**
	 * Desteta al conejo, dejándolo en engorde.
	 * 
	 * @throws InvalidStateException
	 */
	public void destetar() {
		Context.getInstance().getBean("activityRepository", ActivityRepository.class).getDestete().execute(this);
	}

	/**
	 * Al tener edad suficiente, los conejos son sexados y en este momento puede decidirse cuáles
	 * pasan a ser reproductores.
	 * 
	 * @throws InvalidStateException
	 */

	public void sexar(Sexo sexo, boolean esReproductor) {
		if (esReproductor) {
			Context.getInstance().getBean("activityRepository", ActivityRepository.class).getSexadoReproductor()
					.execute(this);
		} else {
			Context.getInstance().getBean("activityRepository", ActivityRepository.class).getSexadoProductor().execute(
					this);
		}

		this.sexo = sexo;
	}

	/**
	 * Un conejo reproductor puede juntarse con otro de distinto sexo para que se reproduzcan.
	 * 
	 * @param pareja
	 *            Conejo con el que se junta.
	 * @throws InvalidStateException
	 * @throws BusinessException
	 *             En caso de que la pareja sea del mismo sexo.
	 */
	public void juntar(Conejo pareja) {
		Context.getInstance().getBean("activityRepository", ActivityRepository.class).getJunta().execute(this);
		if (this.sexo.equals(pareja.sexo)) {
			throw new BusinessException("No pueden juntarse dos conejos del mismo sexo.");
		}

		this.pareja = pareja;
	}

	/**
	 * Deja al animal en estado "Montado", sólo si es hembra. Si no, "En espera".
	 * 
	 * @throws InvalidStateException
	 */
	public void montura() {
		if (this.isMacho()) {
			Context.getInstance().getBean("activityRepository", ActivityRepository.class).getMonturaMacho().execute(
					this);
		} else {
			Context.getInstance().getBean("activityRepository", ActivityRepository.class).getMonturaHembra().execute(
					this);
		}

		// Se desasigna la pareja del macho ya que luego de la montura se lo
		// cambia de jaula. Pero queda en la hembra para luego poder determinar
		// el padre de la futura camada.
		if (this.isMacho()) {
			this.pareja = null;
		}
	}

	/**
	 * Utilizado para notificar el diagnóstico de la preñez de la hembra luego de ser montada.
	 * 
	 * @throws InvalidStateException
	 */
	public void diagnosticar(boolean isPreniada) {
		if (isPreniada) {
			Context.getInstance().getBean("activityRepository", ActivityRepository.class).getDiagnosticoPreniada()
					.execute(this);
		} else {
			Context.getInstance().getBean("activityRepository", ActivityRepository.class).getDiagnosticoNoPreniada()
					.execute(this);
		}
	}

	/**
	 * Desde aquí se deja a la hembra amamantando una nueva camada.
	 * 
	 * @throws InvalidStateException
	 */
	public void parir() {
		Context.getInstance().getBean("activityRepository", ActivityRepository.class).getParto().execute(this);
		this.isAmamantando = true;
	}

	/**
	 * Deja al animal en estado "En espera".
	 * 
	 * @throws InvalidStateException
	 */
	public void destetarCrias() {
		Context.getInstance().getBean("activityRepository", ActivityRepository.class).getDesteteCrias().execute(this);
		if (!this.isAmamantando()) {
			throw new InvalidStateException();
		}
		this.isAmamantando = false;
	}

	/** @throws InvalidStateException */
	public void mudar(Jaula destino) {
		Context.getInstance().getBean("activityRepository", ActivityRepository.class).getMudanza().execute(this);
		cambiarJaula(destino);
	}

	/**
	 * Al sacrificar un animal, éste queda preprado para que pueda utilizarse su carne y cuero.
	 * 
	 * @throws InvalidStateException
	 */
	public void sacrificar() {
		Context.getInstance().getBean("activityRepository", ActivityRepository.class).getSacrificio().execute(this);
		cambiarJaula(null);
	}

	/** @throws InvalidStateException */
	public void vender() {
		Context.getInstance().getBean("activityRepository", ActivityRepository.class).getVenta().execute(this);
		cambiarJaula(null);
	}

	/** @throws InvalidStateException */
	public void enfermar() {
		this.estadoAnterior = this.estado;
		Context.getInstance().getBean("activityRepository", ActivityRepository.class).getEnfermedad().execute(this);
	}

	/** @throws InvalidStateException */
	public void curar() {
		Context.getInstance().getBean("activityRepository", ActivityRepository.class).getCura().execute(this);
		this.estado = this.estadoAnterior;
	}

	/**
	 * Indica la muerte de un animal. A partir de ese momento no podrá modificarse su estado.
	 * 
	 * @throws InvalidStateException
	 */
	public void morir() {
		Context.getInstance().getBean("activityRepository", ActivityRepository.class).getMuerte().execute(this);
		cambiarJaula(null);
	}

	/*-------------------------------------------------------------------------
	 * 								  HELPERS
	 ------------------------------------------------------------------------*/
	/** Quita al conejo de su jaula actual y lo deja en la de destino. */
	private void cambiarJaula(Jaula destino) {
		// Si actualmente se encuentra en una jaula, lo quito
		if (this.jaula != null) {
			this.jaula.remove(this);
		}
		this.jaula = destino;

		// Si el destino es una jaula, lo agrego (ver #morir)
		if (destino != null) {
			destino.add(this);
		}
	}

	// TODO (mmazzei) - Averiguar si es necesario hacer esto por cada clase :S
	@Override
	public int hashCode() {
		Long id = (this.id != null) ? this.id : Long.MIN_VALUE;
		return id.hashCode() + super.hashCode();
	}

	// TODO (mmazzei) - Averiguar si es necesario hacer esto por cada clase :S
	@Override
	public boolean equals(Object obj) {
		return (obj != null)
				&& ((this == obj) || ((this.id == null) && (((Conejo) obj).id == null)) || (obj.getClass().equals(
						this.getClass()) && (((Conejo) obj).id == this.id)));
	}
}
