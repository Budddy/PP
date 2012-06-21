import java.util.Iterator;

/**
 * @author Markus.Zisser(1125404)
 * 
 */
public class SaveOperation implements Operation {
	
	MetricSet<AsciiImage>	saved;
	
	/**
	 * erzeugt eine neue SaveOperation
	 * 
	 * @param saved
	 *            Metric die zur Suche verwendet werden soll
	 */
	public SaveOperation(MetricSet<AsciiImage> saved) {
	
		this.saved = saved;
	}
	
	/**
	 * speichert das spezifizierte Bild
	 * 
	 * @param img
	 *            zu speicherndes Bild
	 * 
	 * @return
	 *         eine Kopie des gespeicherten Bildes
	 * 
	 * @throws OperationException
	 *             nur weil in Operation so spezifiziert
	 */
	@Override
	public AsciiImage execute(AsciiImage img) throws OperationException {
	
		boolean a = false;
		Iterator<AsciiImage> ai = this.saved.iterator();
		while (ai.hasNext()) {
			if (ai.next().equals(img)) {
				a = !a;
			}
		}
		if (!a) {
			this.saved.add(img);
		}
		return new AsciiImage(img);
	}
	
	/**
	 * liefert die Collection mit gespeicherten Bildern.
	 * 
	 * @return
	 *         die Collection
	 */
	public MetricSet<AsciiImage> getSaved() {
	
		return this.saved;
		
	}
}
