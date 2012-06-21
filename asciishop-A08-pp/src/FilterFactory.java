import java.util.Scanner;

/**
 * @author Markus.Zisser(1125404)
 * 
 */
public class FilterFactory implements Factory {
	
	/**
	 * erzeugt eine neue FilterFactory.
	 */
	public FilterFactory() {
	
	}
	
	/**
	 * liest den nächsten String ein und gibt, falls dieser ‘median’ ist, eine neue MedianOperation zurück. Tritt beim
	 * Einlesen des Strings ein Fehler auf, oder ist der String nicht ‘median’, so wird eine FactoryException geworfen.
	 * 
	 * @param scanner
	 *            The Scanner to use for reading parameters
	 * 
	 * @return
	 *         A new ReplaceOperation that is initialized with the read parameters
	 * 
	 * @throws FactoryException
	 *             Thrown if there are too few parameters or parameters with a wrong type
	 * 
	 */
	@Override
	public Operation create(Scanner scanner) throws FactoryException {
	
		if (!scanner.hasNext() || !scanner.next().contentEquals("median")) {
			throw new FactoryException();
		}
		return new MedianOperation();
	}
	
}
