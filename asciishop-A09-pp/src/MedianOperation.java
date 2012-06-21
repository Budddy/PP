import java.util.Arrays;

/**
 * @author Markus.Zisser(1125404)
 * 
 */
public class MedianOperation extends FilterOperation {
	
	/**
	 * erzeugt eine neue MedianOperation.
	 */
	public MedianOperation() {
	
	}
	
	/**
	 * führt mit dem übergebenen Block den Medianfilter aus
	 * 
	 * @param values
	 *            Der Block von dem der Median zu berechnen ist
	 * @return
	 *         der Median
	 */
	@Override
	public int filter(int[] values) {
	
		Arrays.sort(values);
		return values[4];
	}
	
}
