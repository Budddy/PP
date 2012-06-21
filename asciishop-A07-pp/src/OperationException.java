/**
 * @author Markus.Zisser(1125404)
 * 
 */
public class OperationException extends Exception {
	
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	
	/**
	 * erzeugt eine leere OperationException.
	 */
	public OperationException() {
	
		super();
	}
	
	/**
	 * erzeugt eine OperationException mit der entsprechenden Fehlerbeschreibung
	 * 
	 * @param message
	 *            Text der Exception
	 */
	public OperationException(String message) {
	
		super(message);
	}
}
