/**
 * @author Markus.Zisser(1125404)
 * 
 */
public class ClearOperation implements Operation {
	
	/**
	 * erzeugt eine neue ClearOperation.
	 */
	public ClearOperation() {
	
	}
	
	/**
	 * gibt ein neues AsciiImage zurück, das dem übergebenen AsciiImage entspricht, wobei alle Zeichen auf das hellste
	 * Zeichen, sprich dem letzten Zeichen im Zeichensatz des Bildes, gesetzt sind.
	 * 
	 * @param img
	 *            das übergebene AsciiImage auf das die Operation angewandt wird
	 * 
	 * @return
	 *         das Ergebnisbild
	 * 
	 * @throws OperationException
	 *             -wird hier nicht geworfen
	 * 
	 */
	@Override
	public AsciiImage execute(AsciiImage img) throws OperationException {
	
		return new AsciiImage(img.getWidth(), img.getHeight(), img.getCharset());
	}
	
}
