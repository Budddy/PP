import java.util.Scanner;

/**
 * @author Markus.Zisser(1125404)
 * 
 */
public class BinaryFactory implements Factory {
	
	/**
	 * erzeugt eine neue BinaryFactory.
	 */
	public BinaryFactory() {
	
	}
	
	/**
	 * liest mit dem Scanner das Schwellwert Zeichen ein, erzeugt damit eine neue BinaryOperation und gibt diese zurück.
	 * Tritt beim Einlesen des Zeichens ein Fehler auf, so wird eine FactoryException geworfen.
	 * 
	 * @param scanner
	 *            The Scanner to use for reading parameters
	 * @return A new ReplaceOperation that is initialized with the read parameters
	 * @throws FactoryException
	 *             Thrown if there are too few parameters or parameters with a wrong type
	 */
	@Override
	public Operation create(Scanner scanner) throws FactoryException {
	
		char thereshold;
		if (scanner.hasNext()) {
			thereshold = scanner.next().charAt(0);
		}
		else {
			throw new FactoryException();
		}
		return new BinaryOperation(thereshold);
	}
	
}
