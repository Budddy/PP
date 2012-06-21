import java.util.Scanner;

/**
 * @author Markus.Zisser(1125404)
 * 
 */
public class SaveFactory implements Factory {
	
	MetricSet<AsciiImage>	saved;
	
	/**
	 * erzeugt eine neue SaveFactory
	 * 
	 * @param saved
	 *            Metric set in das das zu speichernde bild eingefügt werden soll
	 */
	public SaveFactory(MetricSet<AsciiImage> saved) {
	
		this.saved = saved;
	}
	
	/**
	 * Erzeugt eine neue SaveOperation. .
	 * 
	 * @param scanner
	 *            input Scanner
	 * 
	 * @return
	 *         die neue SaveOperation
	 * 
	 * @throws FactoryException
	 *             eigentlich nicht
	 * 
	 */
	@Override
	public Operation create(Scanner scanner) throws FactoryException {
	
		return new SaveOperation(this.saved);
	}
	
}
