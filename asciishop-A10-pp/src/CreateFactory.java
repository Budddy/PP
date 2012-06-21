import java.util.Scanner;

/**
 * @author Markus.Zisser(1125404)
 * 
 */
public class CreateFactory implements Factory {
	
	/**
	 * erzeugt eine neue CreateFactory.
	 */
	public CreateFactory() {
	
	}
	
	/**
	 * liest mit Hilfe des Scanners Breite und Höhe und einen String ein und gibt eine damit initialisierte neue
	 * CreateOperation zurück. Tritt beim Einlesen ein Fehler (zu wenig Parameter, falsche Parameter), so wird eine
	 * FactoryException geworfen.
	 * 
	 * @param scanner
	 *            input Scanner
	 * 
	 * @return
	 *         die neue CreateOperation
	 * 
	 * @throws FactoryException
	 *             bei zu wenig bzw. falschen Parametern
	 * 
	 */
	@Override
	public Operation create(Scanner scanner) throws FactoryException {
	
		int width = 0;
		int height = 0;
		String charset = null;
		boolean error = false;
		if (scanner.hasNextInt()) {
			
			width = scanner.nextInt();
			
			if (width <= 0) error = true;
			
		}
		else error = true;
		
		if (scanner.hasNextInt() && !error) {
			
			height = scanner.nextInt();
			
			if ((height <= 0) && !error) error = true;
			
		}
		else error = true;
		
		if (scanner.hasNext() && !error) {
			charset = scanner.next();
		}
		else error = true;
		if (error == true) {
			throw new FactoryException();
		}
		
		return new CreateOperation(width, height, charset);
	}
	
}
