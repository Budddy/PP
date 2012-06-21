import java.util.Arrays;

/**
 * @author Markus.Zisser(1125404)
 * 
 */
public class MedianOperation implements Operation {
	
	/**
	 * erzeugt eine neue MedianOperation.
	 */
	public MedianOperation() {
	
	}
	
	/**
	 * führt auf einer Kopie des Bildes den Medianfilter aus. Dabei werden immer 3 mal 3 Größe Blöcke des Bildes
	 * betrachtet, die Pixel nach ihrem `Helligkeitswert' sortiert und dann der Median (also das in der sortierten Liste
	 * in der Mitte stehende Zeichen) als neues Pixel im Mittelpunkt des Blocks gesetzt.
	 * 
	 * @param img
	 *            The AsciiImage to use as basis for executing the Operation, it will remain unchanged
	 * @return A new AsciiImage reflecting the result of the executed Operation
	 * @throws OperationException
	 *             Thrown if a problem occurs, while executing the Operation
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
				Arrays.sort(a);
				newImg.setPixel(x, y, charSet.charAt(a[4]));
				s = "";
			}
		}
		return newImg;
	}
	
}
