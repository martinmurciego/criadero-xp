package ar.uba.fi.criaderoxp.domain.criadero;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ar.uba.fi.criaderoxp.domain.exception.BusinessException;

/**
 * Conjunto de pruebas sobre la clase Conejo.
 * 
 * @author mmazzei
 */
public class ConejoTest {
	private Conejo conejo;

	@Before
	public void setUp() throws Exception {
		this.conejo = new Conejo();
		conejo.nacer();
	}

	/**
	 * Verifica que un conejo sólo pueda nacer una vez.
	 */
	@Test(expected = BusinessException.class)
	public void soloNaceUnaVez() {
		// Aquí debería fallar
		conejo.nacer();
	}

	/**
	 * Verifica que un conejo quede en estado "Gazapo" luego de nacer.
	 */
	@Test
	public void esGazapoAlNacer() {
		Assert.assertEquals(conejo.getEstado(), EstadoHelperQUITAR.gazapo);
	}

	/**
	 * Verifica que un gazapo pueda destetarse quedando en engorde.
	 */
	@Test
	public void siEsGazapoPuedeDestetarse() {
		conejo.destetar();
		Assert.assertEquals(conejo.getEstado(), EstadoHelperQUITAR.engorde);
	}

	/**
	 * Verifica que un conejo en engorde pueda pasar a reproductor al sexarse.
	 */
	@Test
	public void enEngordePuedePasarAReproductorAlSexarse() {
		conejo.destetar();
		conejo.sexar(Sexo.MACHO, true);
		Assert.assertEquals(conejo.getEstado(), EstadoHelperQUITAR.enEspera);
	}

	/**
	 * Verifica que un conejo en engorde pueda pasar a productor al sexarse.
	 */
	@Test
	public void enEngordePuedePasarAProductorAlSexarse() {
		conejo.destetar();
		conejo.sexar(Sexo.MACHO, false);
		Assert.assertEquals(conejo.getEstado(), EstadoHelperQUITAR.productor);
	}

	/**
	 * Verifica que un conejo reproductor en espera puede juntarse.
	 */
	@Test
	public void enEsperaPuedeJuntarse() {
		conejo.destetar();
		conejo.sexar(Sexo.MACHO, true);
		conejo.juntar(new Conejo());
		Assert.assertEquals(conejo.getEstado(), EstadoHelperQUITAR.juntado);
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
	 * Verifica que un conejo productor pueda sacrificarse y ser vendido.
	 */
	@Test
	public void enEngordePuedeSacrificarseYLuegoSerVendido() {
		conejo.destetar();
		conejo.sexar(Sexo.MACHO, false);
		conejo.sacrificar();
		Assert.assertEquals(conejo.getEstado(), EstadoHelperQUITAR.sacrificado);

		conejo.vender();
		Assert.assertEquals(conejo.getEstado(), EstadoHelperQUITAR.vendido);
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
		Assert.assertEquals(conejo.getEstado(), EstadoHelperQUITAR.enEspera);
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
		Assert.assertEquals(conejo.getEstado(), EstadoHelperQUITAR.enEspera);
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
		Assert.assertEquals(conejo.getEstado(), EstadoHelperQUITAR.amamantando);
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
		Assert.assertEquals(conejo.getEstado(), EstadoHelperQUITAR.preniado);
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
		Assert.assertEquals(conejo.getEstado(), EstadoHelperQUITAR.enEspera);
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
	@Test(expected = BusinessException.class)
	public void muertoNoPuedeCambiarDeEstado() {
		conejo.morir();
		conejo.destetar();
	}
}
