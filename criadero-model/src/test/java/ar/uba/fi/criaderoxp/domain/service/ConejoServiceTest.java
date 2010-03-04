package ar.uba.fi.criaderoxp.domain.service;

import java.util.Iterator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ar.uba.fi.criaderoxp.domain.exception.BusinessException;
import ar.uba.fi.criaderoxp.domain.model.Camada;
import ar.uba.fi.criaderoxp.domain.model.Conejo;
import ar.uba.fi.criaderoxp.domain.repository.SexoRepository;
import ar.uba.fi.criaderoxp.domain.util.Context;

/**
 * Conjunto de pruebas sobre {@link ConejoServiceImpl}
 * 
 * @author mmazzei
 * @category Test de unidad
 */
public class ConejoServiceTest {
	private Conejo hembra;
	private Conejo macho;
	private static ConejoService conejoService;

	@BeforeClass
	public static void setUpClass() {
		Context.getInstance().setApplicationContext("testingContext.xml");
		conejoService = Context.getInstance().getBean("conejoService", ConejoService.class);
	}

	@Before
	public void setUp() throws Exception {
		this.hembra = new Conejo();
		hembra.nacer(null);
		hembra.destetar();
		hembra.sexar(Context.getInstance().getBean("sexoRepository", SexoRepository.class).getHembra(), true);

		this.macho = new Conejo();
		macho.nacer(null);
		macho.destetar();
		macho.sexar(Context.getInstance().getBean("sexoRepository", SexoRepository.class).getMacho(), true);
	}

	/**
	 * Verifica que luego de juntar dos conejos, ambos sean detectados como pareja del otro.
	 */
	@Test
	public void conejosJuntadosSonParejaUnoDelOtro() {
		conejoService.crearPareja(hembra, macho);
		Assert.assertEquals(hembra.getPareja(), macho);
		Assert.assertEquals(macho.getPareja(), hembra);
	}

	/**
	 * No se puede obtener una camada de una hembra que no haya sido montada.
	 */
	@Test(expected = BusinessException.class)
	public void noPuedeParirUnaHembraSinSerMontada() {
		conejoService.crearPareja(hembra, macho);
		// conejoService.montar(hembra);
		// hembra.diagnosticar(true);
		conejoService.crearCamada(hembra, 1);
	}

	/**
	 * La cantidad de crías de la camada es la correcta.
	 */
	@Test
	public void unaCamadaTieneTantasCriasComoSePida() {
		conejoService.crearPareja(hembra, macho);
		conejoService.montar(hembra);
		hembra.diagnosticar(true);
		int cantidadCrias = 3;
		Camada camada = conejoService.crearCamada(hembra, cantidadCrias);
		Assert.assertEquals(camada.getCrias().size(), cantidadCrias);
	}

	/**
	 * No puede parirse una camada sin crías.
	 */
	@Test(expected = BusinessException.class)
	public void noDebenObtenerseCamadasVacias() {
		conejoService.crearPareja(hembra, macho);
		conejoService.montar(hembra);
		hembra.diagnosticar(true);
		int cantidadCrias = 0;
		Camada camada = conejoService.crearCamada(hembra, cantidadCrias);
		Assert.assertEquals(camada.getCrias().size(), cantidadCrias);
	}

	/**
	 * El acceso a la madre de un conejo devuelve su madre.
	 */
	@Test
	public void laMadreDeUnConejoSeObtieneCorrectamente() {
		conejoService.crearPareja(hembra, macho);
		conejoService.montar(hembra);
		hembra.diagnosticar(true);
		int cantidadCrias = 1;
		Camada camada = conejoService.crearCamada(hembra, cantidadCrias);

		// Obtengo la camada creada antes
		Conejo cria = camada.getCrias().iterator().next();
		Camada camadaObtenida = cria.getCamada();
		Assert.assertEquals(camadaObtenida.getMadre(), hembra);
	}

	/**
	 * Es posible acceder a los hermanos de un conejo.
	 */
	@Test
	public void puedeAccederseALosHermanosDeUnConejo() {
		conejoService.crearPareja(hembra, macho);
		conejoService.montar(hembra);
		hembra.diagnosticar(true);
		int cantidadCrias = 3;
		Camada camada = conejoService.crearCamada(hembra, cantidadCrias);

		// Verifico que todas las crías sean diferentes y correspondan a la camada.
		Iterator<Conejo> iterator = camada.getCrias().iterator();
		Conejo cria1 = iterator.next();
		Conejo cria2;
		do {
			Assert.assertEquals(camada, cria1.getCamada());
			if (iterator.hasNext()) {
				cria2 = iterator.next();
				Assert.assertFalse(cria1 == cria2);
			}

		} while (iterator.hasNext());
	}
}
