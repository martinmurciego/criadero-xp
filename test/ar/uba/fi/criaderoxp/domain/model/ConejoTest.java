package ar.uba.fi.criaderoxp.domain.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ar.uba.fi.criaderoxp.domain.exception.BusinessException;
import ar.uba.fi.criaderoxp.domain.exception.InvalidStateException;

/**
 * Conjunto de pruebas sobre {@link Conejo}.
 * 
 * @author mmazzei
 * @category Test de unidad
 */
public class ConejoTest {
	private Conejo conejo;
	private BeanFactory activities;

	@Before
	public void setUp() throws Exception {
		activities = new ClassPathXmlApplicationContext("activities.xml");
		this.conejo = new Conejo();
		conejo.nacer();
	}

	/**
	 * Verifica que un conejo sólo pueda nacer una vez.
	 */
	@Test(expected = InvalidStateException.class)
	public void soloNaceUnaVez() {
		// Aquí debería fallar
		conejo.nacer();
	}

	/**
	 * Verifica que un conejo quede en estado "Gazapo" luego de nacer.
	 */
	@Test
	public void esGazapoAlNacer() {
		Assert.assertEquals(conejo.getEstado(), activities.getBean("gazapo"));
	}

	/**
	 * Verifica que un gazapo pueda destetarse quedando en engorde.
	 */
	@Test
	public void siEsGazapoPuedeDestetarse() {
		conejo.destetar();
		Assert.assertEquals(conejo.getEstado(), activities.getBean("engorde"));
	}

	/**
	 * Verifica que un conejo en engorde pueda pasar a reproductor al sexarse.
	 */
	@Test
	public void enEngordePuedePasarAReproductorAlSexarse() {
		conejo.destetar();
		conejo.sexar(Sexo.MACHO, true);
		Assert.assertEquals(conejo.getEstado(), activities.getBean("enEspera"));
	}

	/**
	 * Verifica que un conejo en engorde pueda pasar a productor al sexarse.
	 */
	@Test
	public void enEngordePuedePasarAProductorAlSexarse() {
		conejo.destetar();
		conejo.sexar(Sexo.MACHO, false);
		Assert.assertEquals(conejo.getEstado(), activities.getBean("productor"));
	}

	/**
	 * Verifica que un conejo reproductor en espera puede juntarse.
	 */
	@Test
	public void enEsperaPuedeJuntarse() {
		conejo.destetar();
		conejo.sexar(Sexo.MACHO, true);
		conejo.juntar(new Conejo());
		Assert.assertEquals(conejo.getEstado(), activities.getBean("juntado"));
	}

	/**
	 * Verifica que un conejo sólo pueda juntarse con otro de distinto sexo.
	 */
	@Test(expected = BusinessException.class)
	public void noPuedeJuntarseConMismoSexo() {
		conejo.destetar();
		conejo.sexar(Sexo.MACHO, true);

		// Obtengo un conejo del mismo sexo para comprobar
		Conejo conejo2 = new Conejo();
		conejo2.nacer();
		conejo2.destetar();
		conejo2.sexar(Sexo.MACHO, true);

		conejo.juntar(conejo2);
	}

	/**
	 * Verifica que puede obtenerse la pareja de un conejo juntado.
	 */
	@Test
	public void puedeObtenerseLaParejaDeConejoJuntado() {
		conejo.destetar();
		conejo.sexar(Sexo.MACHO, true);

		// Creo un conejo de otro sexo para que sea su pareja
		Conejo conejo2 = new Conejo();
		conejo2.nacer();
		conejo2.destetar();
		conejo2.sexar(Sexo.HEMBRA, true);

		conejo.juntar(conejo2);

		Assert.assertEquals(conejo2, conejo.getPareja());
	}

	/**
	 * Verifica que un conejo productor pueda sacrificarse y ser vendido.
	 */
	@Test
	public void enEngordePuedeSacrificarseYLuegoSerVendido() {
		conejo.destetar();
		conejo.sexar(Sexo.MACHO, false);
		conejo.sacrificar();
		Assert.assertEquals(conejo.getEstado(), activities.getBean("sacrificado"));

		conejo.vender();
		Assert.assertEquals(conejo.getEstado(), activities.getBean("vendido"));
	}

	/**
	 * Verifica que un conejo macho quede en espera luego de la montura.
	 */
	@Test
	public void machoQuedaEnEsperaLuegoDeMontura() {
		conejo.destetar();
		conejo.sexar(Sexo.MACHO, true);
		conejo.juntar(new Conejo());
		conejo.montura();
		Assert.assertEquals(conejo.getEstado(), activities.getBean("enEspera"));
	}

	/**
	 * Verifica que una hembra montada a la que diagnosticó que no quedó
	 * preñada, quede en espera.
	 */
	@Test
	public void hembraMontadaYNoPreniadaQuedaEnEspera() {
		conejo.destetar();
		conejo.sexar(Sexo.HEMBRA, true);
		conejo.juntar(new Conejo());
		conejo.montura();
		conejo.diagnosticar(false);
		Assert.assertEquals(conejo.getEstado(), activities.getBean("enEspera"));
	}

	/**
	 * Verifica que una hembra que estuvo preñada quede amamantando a su cría al
	 * parir.
	 */
	@Test
	public void hembraAmamantaAlParir() {
		conejo.destetar();
		conejo.sexar(Sexo.HEMBRA, true);
		conejo.juntar(new Conejo());
		conejo.montura();
		conejo.diagnosticar(true);
		conejo.parir();
		Assert.assertEquals(conejo.getEstado(), activities.getBean("amamantando"));
	}

	/**
	 * Verifica que una hembra que está amamantando a su cría pueda preñarse
	 * nuevamente.
	 */
	@Test
	public void hembraAmamantandoPuedePreniarse() {
		conejo.destetar();
		conejo.sexar(Sexo.HEMBRA, true);
		conejo.juntar(new Conejo());
		conejo.montura();
		conejo.diagnosticar(true);
		conejo.parir();
		conejo.juntar(new Conejo());
		conejo.montura();
		conejo.diagnosticar(true);
		Assert.assertEquals(conejo.getEstado(), activities.getBean("preniado"));
	}

	/**
	 * Verifica que una hembra a la que se destetan a los hijos pasa a estar en
	 * espera.
	 */
	@Test
	public void hembraDestetadaPasaAEnEspera() {
		conejo.destetar();
		conejo.sexar(Sexo.HEMBRA, true);
		conejo.juntar(new Conejo());
		conejo.montura();
		conejo.diagnosticar(true);
		conejo.parir();
		conejo.destetarCrias();
		Assert.assertEquals(conejo.getEstado(), activities.getBean("enEspera"));
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
}
