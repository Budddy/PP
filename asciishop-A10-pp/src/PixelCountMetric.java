/**
 * @author Markus.Zisser(1125404)
 * 
 */
public class PixelCountMetric implements Metric<AsciiImage> {
	
	/**
	 * liefert den Absolutbetrag der Differenz der Bildgrößen von i1 und i2
	 * 
	 * @param i1
	 *            Bild 1
	 * 
	 * @param i2
	 *            Bild 3
	 * 
	 * @return
	 *         Absolutbetrag der Differenz der Bildgrößen
	 * 
	 */
	@Override
	public int distance(AsciiImage i1, AsciiImage i2) {
	
		return Math.abs((i1.getHeight() * i1.getWidth()) - (i2.getHeight() * i2.getWidth()));
		
	}
}
