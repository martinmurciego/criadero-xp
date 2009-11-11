package ar.uba.fi.criaderoxp.domain.service;

import java.util.HashSet;
import java.util.Iterator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ar.uba.fi.criaderoxp.domain.exception.BusinessException;
import ar.uba.fi.criaderoxp.domain.model.Camada;
import ar.uba.fi.criaderoxp.domain.model.Conejo;
import ar.uba.fi.criaderoxp.domain.model.ConejoTest;
import ar.uba.fi.criaderoxp.domain.model.Sexo;
import ar.uba.fi.criaderoxp.domain.repository.CamadaRepositoryTest;

/**
 * Conjunto de pruebas sobre {@link ConejoServiceImpl}
 * 
 * @author mmazzei
 * @category Test de unidad
 */
public class ConejoServiceTest {
	private Conejo hembra;
	private Conejo macho;
	private ConejoService conejoService;
	private CamadaRepositoryTest camadaRepository;

	@Before
	public void setUp() throws Exception {
		this.hembra = new Conejo();
		hembra.nacer();
		hembra.destetar();
		hembra.sexar(Sexo.HEMBRA, true);

		this.macho = new Conejo();
		macho.nacer();
		macho.destetar();
		macho.sexar(Sexo.MACHO, true);

		// TODO (mmazzei) - Obtener del contexto con Spring.
		this.conejoService = new ConejoServiceImpl();
		this.camadaRepository = new CamadaRepositoryTest();
		((ConejoServiceImpl) conejoService)
				.setCamadaRepository(camadaRepository);
	}

	/**
	 * Verifica que luego de juntar dos conejos, ambos sean detectados como
	 * pareja del otro.
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

		// Guardo los datos en el repository falso que utiliza el servicio
		for (Conejo conejo : camada.getCrias()) {
			this.camadaRepository.addTestData(conejo, camada);
		}

		// Obtengo la camada creada antes
		Conejo cria = camada.getCrias().iterator().next();
		Camada camadaObtenida = conejoService.getCamada(cria);
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

		// Aquí guardo los hermanos de la camada para luego comparar.
		HashSet<Conejo> criasAux = new HashSet<Conejo>();

		// Guardo los datos en el repository falso que utiliza el servicio
		for (Conejo conejo : camada.getCrias()) {
			this.camadaRepository.addTestData(conejo, camada);
			criasAux.add(conejo);
		}

		// Obtengo la camada creada antes y verifico que todas las crías sean
		// diferentes y se encuentren entre las que realmente la componían
		// (guardadas en criasAux).
		Camada camadaObtenida = conejoService.getCamada(criasAux.iterator()
				.next());
		Iterator<Conejo> iterator = camadaObtenida.getCrias().iterator();
		Conejo cria1 = iterator.next();
		Conejo cria2;
		do {
			Assert.assertTrue(criasAux.contains(cria1));
			if (iterator.hasNext()) {
				cria2 = iterator.next();
				Assert.assertFalse(cria1 == cria2);
				Assert.assertTrue(criasAux.contains(cria2));
			}

		} while (iterator.hasNext());
	}
}
