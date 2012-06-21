import java.util.Scanner;

/**
 * @author Markus.Zisser(1125404)
 * 
 */
public class LoadFactory implements Factory {
	
	/**
	 * erzeugt eine neue LoadFactory.
	 */
	public LoadFactory() {
	
	}
	
	/**
	 * liest den eof-String ein und übergibt in einem String alle Zeilen bis zum abschließenden eof-String durch
	 * Zeilenumbrüche getrennt an den Konstruktor der LoadOperation. Tritt beim Einlesen ein Fehler auf (eof fehlt), so
	 * wird eine FactoryException geworfen.
	 * 
	 * @param scanner
	 *            input Scanner
	 * 
	 * @return
	 *         Eine neue LoadOperation die mit den Werten initialisiert wird.
	 * 
	 * @throws FactoryException
	 *             Wenn beim einlesen ein Fehler auftritt
	 */
	@Override
	public Operation create(Scanner scanner) throws FactoryException {
	
		String s = "";
		String eof = "";
		String nextLine;
		
		if (scanner.hasNext()) eof = scanner.next();
		else {
			throw new FactoryException();
		}
		
		scanner.nextLine();
		
		while (scanner.hasNextLine()) {
			nextLine = scanner.nextLine();
			if (nextLine.contains(eof)) break;
			s = s + nextLine + "\n";
		}
		
		return new LoadOperation(s);
	}
	
}
