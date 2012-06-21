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
	 *            The AsciiImage to use as basis for executing the Operation, it will remain unchanged
	 * @return A new AsciiImage reflecting the result of the executed Operation
	 * @throws OperationException
	 *             Thrown if a problem occurs, while executing the Operation
	 */
	@Override
	public AsciiImage execute(AsciiImage img) throws OperationException {
	
		return new AsciiImage(img.getWidth(), img.getHeight(), img.getCharset());
	}
	
}
