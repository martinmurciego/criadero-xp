package ar.uba.fi.criaderoxp.domain.criadero;

public class EstadoHelperQUITAR {
	final static Estado gazapo;
	final static Estado engorde;
	final static Estado productor;
	final static Estado enEspera;
	final static Estado juntado;
	final static Estado montado;
	final static Estado preniado;
	final static Estado amamantando;
	final static Estado sacrificado;
	final static Estado vendido;
	final static Estado enfermo;
	final static Estado muerto;
	final static Estado nullObjectEstado;

	static {
		nullObjectEstado = new Estado("Null object");

		gazapo = new Estado("Gazapo");
		gazapo.addRequisito(nullObjectEstado);

		engorde = new Estado("Engorde");
		engorde.addRequisito(nullObjectEstado);
		engorde.addRequisito(gazapo);

		productor = new Estado("Productor");
		productor.addRequisito(nullObjectEstado);
		productor.addRequisito(engorde);

		enEspera = new Estado("En espera");
		enEspera.addRequisito(nullObjectEstado);
		enEspera.addRequisito(engorde);

		juntado = new Estado("Juntado");
		juntado.addRequisito(enEspera);
		enEspera.addRequisito(juntado);

		montado = new Estado("Montado");
		montado.addRequisito(nullObjectEstado);
		montado.addRequisito(juntado);
		enEspera.addRequisito(montado);

		preniado = new Estado("Preniado");
		preniado.addRequisito(nullObjectEstado);
		preniado.addRequisito(montado);

		amamantando = new Estado("Amamantando");
		amamantando.addRequisito(preniado);
		enEspera.addRequisito(amamantando);

		sacrificado = new Estado("Sacrificado");
		sacrificado.addRequisito(productor);

		vendido = new Estado("Vendido");
		vendido.addRequisito(sacrificado);

		enfermo = new Estado("Enfermo");
		enfermo.addRequisito(gazapo);
		enfermo.addRequisito(engorde);
		enfermo.addRequisito(productor);
		enfermo.addRequisito(enEspera);
		enfermo.addRequisito(juntado);
		enfermo.addRequisito(montado);
		enfermo.addRequisito(preniado);
		enfermo.addRequisito(amamantando);

		muerto = new Estado("Muerto");
		muerto.addRequisito(gazapo);
		muerto.addRequisito(enfermo);
		muerto.addRequisito(engorde);
		muerto.addRequisito(productor);
		muerto.addRequisito(enEspera);
		muerto.addRequisito(juntado);
		muerto.addRequisito(montado);
		muerto.addRequisito(preniado);
		muerto.addRequisito(amamantando);
	}
}
