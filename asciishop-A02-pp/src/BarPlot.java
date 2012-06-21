import java.util.Scanner;

/**
 * @author Markus.Zisser(1125404)
 * 
 */
public class BarPlot {
	
	/**
	 * Generiert eine Zeile des Balkendiagramms.
	 * 
	 * @param label
	 *            Beschriftung des Balken
	 * 
	 * @param value
	 *            die prozentuelle Länge des Balkens
	 * 
	 * @return
	 *         der Balken
	 */
	static String drawBar(String label, double value) {
	
		int n = (int) Math.round(value * 30);
		
		return (BarPlot.drawLabel(label, 8) + "|" + BarPlot.repeat('#', n) + BarPlot.repeat(' ', 30 - n) + "|");
	}
	
	/**
	 * Generiert eine Zeile des Balkendiagramms. value bezeichnet dabei die absolute LÃ€nge des Balkens.
	 * 
	 * @param label
	 *            Beschriftung des Balken
	 * 
	 * @param value
	 *            die Absolute Länge des Balkens
	 * 
	 * @return
	 *         der Balken
	 */
	static String drawBar(String label, int value) {
	
		return (BarPlot.drawLabel(label, 8) + "|" + BarPlot.repeat('#', value) + BarPlot.repeat(' ', 30 - value) + "|");
	}
	
	/**
	 * Liefert einen String zurück der label beinhaltet aber genau n Zeichen lang ist. Wenn label zu lange ist
	 * wird es abgeschnitten, wenn label zu kurz ist, wird der Rückgabewert mit Leerzeichen aufgefüllt. Beispielsweise
	 * liefert drawLabel("abc",5) den String "abc " zurück.
	 * 
	 * @param label
	 *            Label des Strings
	 * 
	 * @param n
	 *            laenge des Strings
	 * 
	 * @return
	 * 
	 */
	static String drawLabel(String label, int n) {
	
		int laenge = label.length();
		
		// Korrekte Anzahl an Zeichen in label
		if (laenge == n) return label;
		
		// Zu viele Zeichen in label
		if (laenge > n) return label.substring(0, n);
		
		// Zu wenig Zeichen in label
		while (laenge < n) {
			laenge++;
			label += " ";
		}
		
		return label;
	}
	
	/**
	 * liest die Daten und Befehle ein und gibt das Ergebnis aus.
	 * 
	 * @param args
	 * 
	 */
	public static void main(String[] args) {
	
		Scanner sc = new Scanner(System.in);
		String label = new String();
		int ival = 0;
		double dval = 0;
		
		while (sc.hasNext()) {
			
			label = sc.next();
			
			if (sc.hasNextInt()) {
				ival = sc.nextInt();
				
				// Ausgabe des Int Ba lken wenn dieser nicht zu groß
				if (ival <= 30) System.out.println(BarPlot.drawBar(label, ival));
				
				else {
					System.out.println("INPUT ERROR");
					break;
				}
			}
			
			else {
				if (sc.hasNextDouble()) {
					dval = sc.nextDouble();
					
					// Ausgabe des Double Balken wenn dieser nicht zu groß
					if (dval <= 1.0) System.out.println(BarPlot.drawBar(label, dval));
					
					else {
						System.out.println("INPUT ERROR");
						break;
					}
				}
				
				// Weder Int noch Double Wert vorhanden
				else {
					System.out.println("INPUT ERROR");
					break;
				}
			}
			
		}
		
	}
	
	/**
	 * Liefert einen String der Länge n zurück der nur aus dem Zeichen c besteht
	 * 
	 * @param c
	 *            zu wiederholendes Zeichen
	 * 
	 * @param n
	 *            Anzahl der Wiederholungen
	 * 
	 * @return
	 *         n mal das Zeichen c
	 */
	static String repeat(char c, int n) {
	
		String s = new String();
		
		for (int i = 0; i < n; i++) {
			s += c;
		}
		
		return s;
	}
}
