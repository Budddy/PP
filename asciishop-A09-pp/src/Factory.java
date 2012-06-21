import java.util.Scanner;

/**
 * @author Markus.Zisser(1125404)
 * 
 */
public interface Factory {
	
	/**
	 * erzeugt ein neues Objekt vom Typ Operation. Welche konkrete Operation erzeugt wird, hängt von der
	 * implementierenden Factory ab. Bei Bedarf liest diese Methode vom übergebenen Scanner Parameter ein.
	 * 
	 * @param scanner
	 *            input Scanner
	 * 
	 * @return
	 *         die entsprechende Operation
	 * 
	 * @throws FactoryException
	 *             wenn Parameter fehlen oder vom falschen Typ sind
	 * 
	 */
	public Operation create(Scanner scanner) throws FactoryException;
}
