package ar.uba.fi.criaderoxp.domain.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ar.uba.fi.criaderoxp.domain.exception.BusinessException;
import ar.uba.fi.criaderoxp.domain.exception.InvalidStateException;
import ar.uba.fi.criaderoxp.domain.repository.SexoRepository;
import ar.uba.fi.criaderoxp.domain.security.Usuario;
import ar.uba.fi.criaderoxp.domain.util.Context;

/**
 * Conjunto de pruebas sobre {@link Conejo}.
 * 
 * @author mmazzei
 * @category Test de unidad
 */
public class ConejoTest {
	private Conejo conejo;
	private Usuario usuario;

	@BeforeClass
	public static void setUpClass() {
		Context.getInstance().setApplicationContext("testingContext.xml");
	}

	@Before
	public void setUp() throws Exception {
		this.conejo = new Conejo();
		conejo.nacer(null);
	}

	/**
	 * Verifica que un conejo sólo pueda nacer una vez.
	 */
	@Test(expected = InvalidStateException.class)
	public void soloNaceUnaVez() {
		// Aquí debería fallar
		conejo.nacer(null);
	}

	/**
	 * Verifica que un conejo quede en estado "Gazapo" luego de nacer.
	 */
	@Test
	public void esGazapoAlNacer() {
		Assert.assertEquals(conejo.getEstado(), Context.getInstance().getBean("gazapo", Estado.class));
	}

	/**
	 * Verifica que un gazapo pueda destetarse quedando en engorde.
	 */
	@Test
	public void siEsGazapoPuedeDestetarse() {
		conejo.destetar();
		Assert.assertEquals(conejo.getEstado(), Context.getInstance().getBean("engorde", Estado.class));
	}

	/**
	 * Verifica que un conejo en engorde pueda pasar a reproductor al sexarse.
	 */
	@Test
	public void enEngordePuedePasarAReproductorAlSexarse() {
		conejo.destetar();
		conejo.sexar(Context.getInstance().getBean("sexoRepository", SexoRepository.class).getMacho(), true);
		Assert.assertEquals(conejo.getEstado(), Context.getInstance().getBean("enEspera", Estado.class));
	}

	/**
	 * Verifica que un conejo en engorde pueda pasar a productor al sexarse.
	 */
	@Test
	public void enEngordePuedePasarAProductorAlSexarse() {
		conejo.destetar();
		conejo.sexar(Context.getInstance().getBean("sexoRepository", SexoRepository.class).getMacho(), false);
		Assert.assertEquals(conejo.getEstado(), Context.getInstance().getBean("productor", Estado.class));
	}

	/**
	 * Verifica que un conejo reproductor en espera puede juntarse.
	 */
	@Test
	public void enEsperaPuedeJuntarse() {
		conejo.destetar();
		conejo.sexar(Context.getInstance().getBean("sexoRepository", SexoRepository.class).getMacho(), true);
		conejo.juntar(new Conejo());
		Assert.assertEquals(conejo.getEstado(), Context.getInstance().getBean("juntado", Estado.class));
	}

	/**
	 * Verifica que un conejo sólo pueda juntarse con otro de distinto sexo.
	 */
	@Test(expected = BusinessException.class)
	public void noPuedeJuntarseConMismoSexo() {
		conejo.destetar();
		conejo.sexar(Context.getInstance().getBean("sexoRepository", SexoRepository.class).getMacho(), true);

		// Obtengo un conejo del mismo sexo para comprobar
		Conejo conejo2 = new Conejo();
		conejo2.nacer(null);
		conejo2.destetar();
		conejo2.sexar(Context.getInstance().getBean("sexoRepository", SexoRepository.class).getMacho(), true);

		conejo.juntar(conejo2);
	}

	/**
	 * Verifica que puede obtenerse la pareja de un conejo juntado.
	 */
	@Test
	public void puedeObtenerseLaParejaDeConejoJuntado() {
		conejo.destetar();
		conejo.sexar(Context.getInstance().getBean("sexoRepository", SexoRepository.class).getMacho(), true);

		// Creo un conejo de otro sexo para que sea su pareja
		Conejo conejo2 = new Conejo();
		conejo2.nacer(null);
		conejo2.destetar();
		conejo2.sexar(Context.getInstance().getBean("sexoRepository", SexoRepository.class).getHembra(), true);

		conejo.juntar(conejo2);

		Assert.assertEquals(conejo2, conejo.getPareja());
	}

	/**
	 * Verifica que un conejo productor pueda sacrificarse y ser vendido.
	 */
	@Test
	public void enEngordePuedeSacrificarseYLuegoSerVendido() {
		conejo.destetar();
		conejo.sexar(Context.getInstance().getBean("sexoRepository", SexoRepository.class).getMacho(), false);
		conejo.sacrificar();
		Assert.assertEquals(conejo.getEstado(), Context.getInstance().getBean("sacrificado", Estado.class));

		conejo.vender();
		Assert.assertEquals(conejo.getEstado(), Context.getInstance().getBean("vendido", Estado.class));
	}

	/**
	 * Verifica que un conejo macho quede en espera luego de la montura.
	 */
	@Test
	public void machoQuedaEnEsperaLuegoDeMontura() {
		conejo.destetar();
		conejo.sexar(Context.getInstance().getBean("sexoRepository", SexoRepository.class).getMacho(), true);
		conejo.juntar(new Conejo());
		conejo.montura();
		Assert.assertEquals(conejo.getEstado(), Context.getInstance().getBean("enEspera", Estado.class));
	}

	/**
	 * Verifica que una hembra montada a la que diagnosticó que no quedó preñada, quede en espera.
	 */
	@Test
	public void hembraMontadaYNoPreniadaQuedaEnEspera() {
		conejo.destetar();
		conejo.sexar(Context.getInstance().getBean("sexoRepository", SexoRepository.class).getHembra(), true);
		conejo.juntar(new Conejo());
		conejo.montura();
		conejo.diagnosticar(false);
		Assert.assertEquals(conejo.getEstado(), Context.getInstance().getBean("enEspera", Estado.class));
	}

	/**
	 * Verifica que una hembra que estuvo preñada quede amamantando a su cría al parir.
	 */
	@Test
	public void hembraAmamantaAlParir() {
		conejo.destetar();
		conejo.sexar(Context.getInstance().getBean("sexoRepository", SexoRepository.class).getHembra(), true);
		conejo.juntar(new Conejo());
		conejo.montura();
		conejo.diagnosticar(true);
		conejo.parir();
		Assert.assertEquals(conejo.getEstado(), Context.getInstance().getBean("amamantando", Estado.class));
	}

	/**
	 * Verifica que una hembra que está amamantando a su cría pueda preñarse nuevamente.
	 */
	@Test
	public void hembraAmamantandoPuedePreniarse() {
		conejo.destetar();
		conejo.sexar(Context.getInstance().getBean("sexoRepository", SexoRepository.class).getHembra(), true);
		conejo.juntar(new Conejo());
		conejo.montura();
		conejo.diagnosticar(true);
		conejo.parir();
		conejo.juntar(new Conejo());
		conejo.montura();
		conejo.diagnosticar(true);
		Assert.assertEquals(conejo.getEstado(), Context.getInstance().getBean("preniado", Estado.class));
	}

	/**
	 * Verifica que una hembra a la que se destetan a los hijos pasa a estar en espera.
	 */
	@Test
	public void hembraDestetadaPasaAEnEspera() {
		conejo.destetar();
		conejo.sexar(Context.getInstance().getBean("sexoRepository", SexoRepository.class).getHembra(), true);
		conejo.juntar(new Conejo());
		conejo.montura();
		conejo.diagnosticar(true);
		conejo.parir();
		conejo.destetarCrias();
		Assert.assertEquals(conejo.getEstado(), Context.getInstance().getBean("enEspera", Estado.class));
	}

	/**
	 * Verifica que un conejo enfermo vuelva a su estado anterior al curarse.
	 */
	@Test
	public void enfermoVuelveAlEstadoAnteriorAlCurar() {
		conejo.destetar();
		Estado estadoAnterior = conejo.getEstado();
		conejo.enfermar();
		conejo.curar();
		Assert.assertEquals(conejo.getEstado(), estadoAnterior);
	}

	/**
	 * Verifica que un conejo muerto no pueda cambiar de estados.
	 */
	@Test(expected = InvalidStateException.class)
	public void muertoNoPuedeCambiarDeEstado() {
		conejo.morir();
		conejo.destetar();
	}

	/**
	 * Verifica que se registran tantas actividades como haya ejecutado en el conejo.
	 */
	@Test
	public void seRegistranTodasLasActividadesEjecutadas() {
		int cantInicialEventos = conejo.getRegistro().getEventos().size();
		conejo.destetar();
		conejo.enfermar();
		conejo.curar();
		int cantFinalEventos = conejo.getRegistro().getEventos().size();

		// Compruebo que se hayan registrado tres eventos
		Assert.assertEquals(3, cantFinalEventos - cantInicialEventos);
	}

	/**
	 * Verifica que se registran los eventos adecuados para la actividad que se ejecuta.
	 */
	@Test
	public void seRegistranLosEventosAdecuadosParaLaActividad() {
		TipoEvento tipoCorrecto = Context.getInstance().getBean("muerte", Activity.class).getTipoEvento();
		conejo.destetar();
		conejo.enfermar();
		conejo.morir();
		TipoEvento tipoRegistrado = conejo.getRegistro().getUltimoEvento().getTipo();

		usuario = new Usuario();
		usuario.setUsername("pepe");

		// Compruebo que se hayan registrado tres eventos
		Assert.assertEquals(tipoCorrecto, tipoRegistrado);
	}

}
