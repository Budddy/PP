/**
 * @author Markus.Zisser(1125404)
 * 
 */
@SuppressWarnings("serial")
public class OperationException extends Exception {
	
	/**
	 * erzeugt eine leere OperationException
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
