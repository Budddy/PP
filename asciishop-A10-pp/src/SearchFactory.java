import java.util.Scanner;

/**
 * @author Markus.Zisser(1125404)
 * 
 */
public class SearchFactory implements Factory {
	
	MetricSet<AsciiImage>	saved;
	
	/**
	 * erzeugt eine neue SearchFactory.
	 * 
	 * @param saved
	 *            MetricSet in dem sich die gespeicherten Bilder Befinden
	 */
	public SearchFactory(MetricSet<AsciiImage> saved) {
	
		this.saved = saved;
	}
	
	/**
	 * Erzeugt eine neue SearchOperation.
	 * 
	 * @param scanner
	 *            input Scanner
	 * 
	 * @return
	 *         eine neue SearchOparation
	 * 
	 * @throws FactoryException
	 *             wenn die metric nicht bekannt oder keine angegeben wurde
	 * 
	 */
	@Override
	public Operation create(Scanner scanner) throws FactoryException {
	
		String m = null;
		if (scanner.hasNext()) {
			m = scanner.next();
			if (m.contentEquals("pixelcount")) {
				return new SearchOperation(this.saved, new PixelCountMetric());
			}
			else
				if (m.contentEquals("uniquechars")) {
					return new SearchOperation(this.saved, new UniqueCharsMetric());
				}
				else {
					throw new FactoryException();
				}
		}
		throw new FactoryException();
	}
	
}
