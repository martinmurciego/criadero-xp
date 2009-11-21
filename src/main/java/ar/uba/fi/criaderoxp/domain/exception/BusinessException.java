package main.java.ar.uba.fi.criaderoxp.domain.exception;

/**
 * Identifica a todas las excepciones que son arrojadas durante el normal
 * funcionamiento del sistema en validaciones de negocio.
 */
@SuppressWarnings("serial")
public class BusinessException extends RuntimeException {
	/**
	 * @param message
	 *            Mensaje de error.
	 */
	public BusinessException(String message) {
		super(message);
	}

}
