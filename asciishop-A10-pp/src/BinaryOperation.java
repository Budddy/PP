/**
 * @author Markus.Zisser(1125404)
 * 
 */
public class BinaryOperation implements Operation {
	
	char	threshold;
	
	/**
	 * erzeugt eine neue BinaryOperation mit dem entsprechenden Schwellwert.
	 * 
	 * @param threshold
	 *            Schwellwert f�r die Umwandlung in das Bin�rbild
	 */
	public BinaryOperation(char threshold) {
	
		this.threshold = threshold;
	}
	
	/**
	 * gibt ein neues AsciiImage zur�ck, das das Bin�rbild des �bergebenen AsciiImage ist
	 * 
	 * @param img
	 *            AsciiImage das umgewandelt werden soll
	 * 
	 * @return
	 *         das Bin�rbild
	 * 
	 * @throws OperationException
	 *             wenn der Schwellwert im Zeichensatz nicht vorkommt
	 */
	
	@Override
	public AsciiImage execute(AsciiImage img) throws OperationException {
	
		AsciiImage ai = new AsciiImage(img);
		String charSet = img.getCharset();
		int schwelle = charSet.lastIndexOf(this.threshold + "");
		
		if (!charSet.contains(this.threshold + "")) throw new OperationException();
		
		for (int y = 0; y < img.getHeight(); y++) {
			for (int x = 0; x < img.getWidth(); x++) {
				if (charSet.lastIndexOf(img.getPixel(x, y) + "") < schwelle) ai.setPixel(x, y, charSet.charAt(0));
				else ai.setPixel(x, y, charSet.charAt(charSet.length() - 1));
			}
		}
		return ai;
	}
}
