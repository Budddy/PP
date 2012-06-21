/**
 * @author Markus.Zisser(1125404)
 * 
 */
public class UniqueCharsMetric implements Metric<AsciiImage> {
	
	/**
	 * liefert den Absolutbetrag der Differenz der Anzahl unterschiedlicher Zeichen in einem Bild.
	 * 
	 * @param o1
	 *            Bild1
	 * 
	 * @param o2
	 *            Bild2
	 * 
	 * @return
	 *         Absolutbetrag der Differenz
	 * 
	 */
	@Override
	public int distance(AsciiImage o1, AsciiImage o2) {
	
		return Math.abs(o1.getUniqueChars() - o2.getUniqueChars());
	}
	
}
