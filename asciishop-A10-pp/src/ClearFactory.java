import java.util.Scanner;

/**
 * @author Markus.Zisser(1125404)
 * 
 */
public class ClearFactory implements Factory {
	
	/**
	 * erzeugt eine neue ClearFactory.
	 */
	public ClearFactory() {
	
	}
	
	/**
	 * erzeugt eine neue ClearOperation und gibt diese zur�ck.
	 * 
	 * @param scanner
	 *            input Scanner-wird hier nicht ben�tigt
	 * 
	 * @return
	 *         eine neue ClearOperation
	 * 
	 * @throws FactoryException
	 *             -wird hier nicht geworfen
	 * 
	 */
	@Override
	public Operation create(Scanner scanner) throws FactoryException {
	
		return new ClearOperation();
	}
	
}
