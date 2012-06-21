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
	 * liest den n�chsten String ein und gibt, falls dieser �median� ist, eine neue MedianOperation zur�ck. Tritt beim
	 * Einlesen des Strings ein Fehler auf, oder ist der String nicht �median�, so wird eine FactoryException geworfen.
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
	
		String s = "";
		if (!scanner.hasNext()) {
			throw new FactoryException();
		}
		s = scanner.next();
		if (!s.contentEquals("median") && !s.contentEquals("average")) {
			throw new FactoryException();
		}
		if (s.contentEquals("average")) return new AverageOperation();
		if (s.contentEquals("median")) return new MedianOperation();
		return null;
	}
	
}
