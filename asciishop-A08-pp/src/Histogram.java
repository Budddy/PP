/**
 * @author Markus.Zisser(1125404)
 * 
 */
public class Histogram {
	
	/**
	 * erzeugt das Charset für das Histogramm
	 * 
	 * @param cs
	 *            das alte Charset
	 * @return
	 *         das neue Charset
	 */
	public static String createCharset(String cs) {
	
		if (!cs.contains(".")) {
			cs += ".";
		}
		
		if (!cs.contains("#")) {
			cs += ".";
		}
		
		for (Integer i = 0; i < 10; i++) {
			if (!cs.contains(i.toString())) {
				cs += (i.toString());
			}
		}
		return cs;
	}
	
	/**
	 * erzeugt das histogramm
	 * 
	 * @param max
	 *            Anzahl des am haefigst vorkommenden Zeichen im Bild
	 * @param img
	 *            Bild für das das Histogram erstellt werden soll
	 * @param hist
	 *            das Histogram
	 * @param anz
	 *            Anzahl der einzelnen vorkommenden Zeichen
	 */
	public static void createHist(double max, AsciiImage img, AsciiImage hist, int[] anz) {
	
		Integer a = 0;
		double incr = max / 15;
		double help = max;
		for (int i = 0; i < 15; i += 1) {
			a = (int) Math.round(((((max * 1.0) / (img.getHeight() * img.getWidth())) * 100) / 15.0) * (15 - i));
			if ((i % 2) == 0) {
				
				if (a < 10) {
					hist.setPixel(2, i, a.toString().charAt(0));
				}
				else
					if ((a >= 10) && (a < 100)) {
						hist.setPixel(2, i, a.toString().charAt(1));
						hist.setPixel(1, i, a.toString().charAt(0));
					}
					else {
						hist.setPixel(2, i, a.toString().charAt(2));
						hist.setPixel(1, i, a.toString().charAt(1));
						hist.setPixel(0, i, a.toString().charAt(0));
					}
			}
			for (int b = 0; b < img.getCharset().length(); b++) {
				if (anz[b] > (help - incr)) hist.setPixel(b + 3, i, '#');
			}
			help -= incr;
		}
	}
	
	/**
	 * ermittelt wie oft jedes Zeichen in img vorkommt
	 * 
	 * @param anz
	 *            darin wird die Anzahl der Zeichen gespeichert
	 * @param img
	 *            das AsiiImage für das die Anzahl zu ermitteln ist
	 */
	public static void getAnz(int[] anz, AsciiImage img) {
	
		String cs = img.getCharset();
		for (int i = 0; i < cs.length(); i++) {
			anz[i] = 0;
		}
		for (int i = 0; i < cs.length(); i++) {
			for (int a = 0; a < img.getHeight(); a++) {
				for (int b = 0; b < img.getWidth(); b++) {
					if (img.getPixel(b, a) == cs.charAt(i)) {
						anz[i]++;
					}
				}
			}
		}
	}
	
	/**
	 * 
	 * Erzeugt ein Histogramm des Bildes
	 * 
	 * @param img
	 *            Bild für das das Histogramm erstellt werden soll
	 * @return
	 *         das Histogramm
	 */
	
	public static AsciiImage getHistogram(AsciiImage img) {
	
		String cs = img.getCharset();
		int[] anz = new int[cs.length()];
		AsciiImage ret = new AsciiImage(cs.length() + 3, 16, Histogram.createCharset(cs));
		
		Histogram.initHist(ret);
		
		Histogram.getAnz(anz, img);
		
		// erste Reihe-Beschriftung
		for (int i = 3; i < (cs.length() + 3); i++)
			ret.setPixel(i, 15, cs.charAt(i - 3));
		
		// Maximales Vorkommen eines Zeichens ermitteln
		double max = 0;
		for (int element : anz) {
			if (max < element) max = element;
		}
		
		// Histogramm erstellen
		Histogram.createHist(max, img, ret, anz);
		return ret;
		
	}
	
	/**
	 * initialisiert das Histogramm
	 * 
	 * @param ai
	 *            das Histogramm
	 */
	public static void initHist(final AsciiImage ai) {
	
		for (int y = 0; y < ai.getHeight(); y++) {
			for (int x = 0; x < ai.getWidth(); x++) {
				ai.setPixel(x, y, '.');
			}
		}
	}
}
