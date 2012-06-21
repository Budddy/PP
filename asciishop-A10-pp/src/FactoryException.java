/**
 * @author Markus.Zisser(1125404)
 * 
 */
@SuppressWarnings("serial")
public class FactoryException extends Exception {
	
	/**
	 * erzeugt eine leere FactoryException
	 */
	public FactoryException() {
	
	}
	
	/**
	 * erzeugt eine FactoryException mit der entsprechenden Fehlerbeschreibung
	 * 
	 * @param message
	 *            die Nachricht der Exception
	 */
	public FactoryException(String message) {
	
		super(message);
	}
}
