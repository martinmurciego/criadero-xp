package ar.uba.fi.criaderoxp.domain.exception;

/**
 * Excepción arrojada cada vez que trate de cambiarse el estado de un objeto
 * hacia uno no permitido en base al actual, según su workflow.
 * 
 * @author mmazzei
 */
@SuppressWarnings("serial")
public class InvalidStateException extends BusinessException {
	final static String DEFAULT_MESSAGE = "Estado inválido.";

	public InvalidStateException(String message) {
		super(message);
	}

	/**
	 * Construye una excepción con el mensaje por defecto.
	 * 
	 * @see #DEFAULT_MESSAGE
	 */
	public InvalidStateException() {
		super(DEFAULT_MESSAGE);
	}

}
