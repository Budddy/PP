/**
 * @author Markus.Zisser(1125404)
 * 
 */
public abstract class FilterOperation implements Operation {
	
	/**
	 * Konstruktor der FilterOperation.
	 */
	public FilterOperation() {
	
	}
	
	/**
	 * führt den Blockfilter aus
	 * 
	 * @param img
	 *            Bild auf das der Filter angewendet wird
	 * @return
	 *         Ergebnisbild
	 * 
	 * @throws OperationException
	 *             hier nicht verwendet
	 * 
	 */
	@Override
	public AsciiImage execute(AsciiImage img) throws OperationException {
	
		String charSet = img.getCharset();
		String s = "";
		int[] a = new int[9];
		AsciiImage newImg = new AsciiImage(img);
		for (int y = 0; y < img.getHeight(); y++) {
			for (int x = 0; x < img.getWidth(); x++) {
				for (int y1 = y - 1; y1 <= (y + 1); y1++) {
					for (int x1 = x - 1; x1 <= (x + 1); x1++) {
						if ((y1 < 0) || (x1 < 0) || (y1 == img.getHeight()) || (x1 == img.getWidth())) {
							s = s + charSet.charAt(charSet.length() - 1);
						}
						else s = s + img.getPixel(x1, y1);
					}
				}
				for (int i = 0; i < 9; i++) {
					a[i] = charSet.lastIndexOf(s.charAt(i) + "");
				}
				
				newImg.setPixel(x, y, charSet.charAt(this.filter(a)));
				s = "";
			}
		}
		return newImg;
	}
	
	/**
	 * muss von den abgeleiteten Klassen implementiert werden. Sie führt die eigentliche Logik des Filters durch. Das
	 * übergebene Array umfasst die Helligkeitswerte der Pixel im Block Zeile für Zeile. Diese Methode gibt den
	 * berechneten Helligkeitswert für den neuen Pixel zurück.
	 * 
	 * @param values
	 * 
	 * @return
	 *         Helligkeitswert für den neuen Pixel
	 */
	public abstract int filter(int[] values);
	
}
