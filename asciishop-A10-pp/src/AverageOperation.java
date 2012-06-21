/**
 * @author Markus.Zisser(1125404)
 * 
 */
public class AverageOperation extends FilterOperation {
	
	/**
	 * erzeugt eine neue AverageOperation.
	 */
	public AverageOperation() {
	
	}
	
	/**
	 * f�hrt mit dem �bergebenen Block den Mittelwertfilter aus.
	 * 
	 * @param values
	 *            Block auf den der Filter angewendet werden soll
	 * @return
	 *         der Mittelwert
	 */
	@Override
	public int filter(int[] values) {
	
		float sum = 0;
		for (int value : values) {
			sum += value;
		}
		sum /= values.length;
		return Math.round(sum);
		
	}
}
