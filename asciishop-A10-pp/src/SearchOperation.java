import java.util.Iterator;

/**
 * @author Markus.Zisser(1125404)
 * 
 */
public class SearchOperation implements Operation {
	
	MetricSet<AsciiImage>	saved;
	Metric<AsciiImage>		m;
	
	/**
	 * initialisiert diese SearchOperation mit einer angegebenen Metrik
	 * 
	 * @param saved
	 *            Metric Set in dem sich die gespeicherten Bilder befinden
	 * 
	 * @param m
	 *            Metric
	 */
	public SearchOperation(MetricSet<AsciiImage> saved, Metric<AsciiImage> m) {
	
		this.saved = saved;
		this.m = m;
	}
	
	/**
	 * liefert ein Bild mit minimaler Distanz zum spezifizierten Bild.
	 * 
	 * @param img
	 *            das Bild zu dem eine minimale Distanz gesucht wird
	 * 
	 * @return
	 *         Bild mit minimaler distanz
	 * 
	 * @throws OperationException
	 *             wenn saved leer
	 */
	@Override
	public AsciiImage execute(AsciiImage img) throws OperationException {
	
		if (this.saved == null) throw new OperationException();
		MetricSet<AsciiImage> ms = this.saved.search(img, this.m);
		if (ms == null) throw new OperationException();
		// AsciiImage image = ((AsciiImage) ms.toArray()[0]);
		Iterator<AsciiImage> ai = ms.iterator();
		return new AsciiImage(ai.next());
	}
	
}
