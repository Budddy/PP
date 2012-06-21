import java.util.Scanner;

/**
 * @author Markus.Zisser(1125404)
 * 
 */
public class AsciiShop {
	
	/**
	 * Ersetzt das Zeichen an der Position (x,y) und alle angrenzenden Positionen mit dem selben Zeichen durch das
	 * Zeichen c.
	 * 
	 * @param image
	 *            das Bild
	 * 
	 * @param x
	 *            x Position des Punktes
	 * 
	 * @param y
	 *            y Position des Punktes
	 * 
	 * @param c
	 *            Wert auf den das Zeichen an der Position x y gesetzt werden soll
	 */
	public static void fill(String[] image, int x, int y, char c) {
	
		int laenge = image[0].length();
		int hoee = image.length - 1;
		char zeichen;
		zeichen = image[y].charAt(x);
		if (image[y].charAt(x) == c) return;
		
		if (x == 0) {
			image[y] = c + image[y].substring(1, image[y].length()); // Erstes Zeichen in der Zeile
		}
		
		else {
			if (x == image[y].length()) {
				image[y] = image[y].substring(0, image[y].length()) + c; // Letztes Zeichen in der Zeile
			}
			
			else {
				image[y] = image[y].substring(0, x) + c + image[y].substring(x + 1, image[y].length()); // Das Zeichen
																										// befindet sich
																										// in der Mitte
																										// der Zeile
			}
		}
		
		// Durchlaufen der einzelnen angrenzenden Positionen
		if (x > 0) {
			if ((image[y].charAt(x - 1) == zeichen)) {
				AsciiShop.fill(image, x - 1, y, c);
			}
		}
		if (laenge > (x + 1)) {
			if (image[y].charAt(x + 1) == zeichen) {
				AsciiShop.fill(image, x + 1, y, c);
			}
		}
		if (y > 0) {
			if ((image[y - 1].charAt(x) == zeichen)) {
				AsciiShop.fill(image, x, y - 1, c);
			}
		}
		if (hoee > (y)) {
			if ((image[y + 1].charAt(x) == zeichen)) {
				AsciiShop.fill(image, x, y + 1, c);
			}
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
	
		int laenge = 0;		// Zeilenlänge
		int hoee = 0;		// Hoehe des Ascii-Bildes
		int x = 0, y = 0;	// Koordinaten des zu ändernden Zeichens
		int read;			// Anzahl der zu einlesenden Zeilen
		char c = ' ';		// Zeichen das
		Scanner sc = new Scanner(System.in);
		String com = " ";	// Befehl
		
		// Abfragen der Einzulesenden Zeilen
		if (sc.hasNext()) {
			com = new String(sc.next());
			
			// Abbruch wenn als erster Befehl nicht read eingegeben wird
			if (com.compareTo("read") != 0) {
				System.out.println("INPUT MISMATCH");
				return;
			}
			
			// Anzahl der zu lesenden Zeilen auslesen
			if (sc.hasNextInt()) {
				read = sc.nextInt();
			}
			
			else {
				System.out.println("INPUT MISMATCH");
				return;
			}
		}
		
		else {
			System.out.println("INPUT MISMATCH");
			return;	// Abbruch wenn keine Eingabe vorhanden ist
		}
		
		String[] s = new String[read];	// Ascii-bild
		sc.nextLine();
		// Ermittlung der Länge der ersten Zeile
		laenge = (s[hoee] = new String(sc.nextLine())).length();
		
		while (sc.hasNextLine() && ((read - 1) > hoee)) {
			hoee++;
			s[hoee] = new String(sc.nextLine());
			
			// Ermittlung ob eine Zeile zu kurz oder zu lang ist
			if (s[hoee].length() != laenge) {
				System.out.println("INPUT MISMATCH");
				return;
			}
		}
		
		while (sc.hasNext()) {
			com = new String(sc.next());
			
			// Abbruch wenn als erster Befehl nicht read eingegeben wird
			if (com.compareTo("fill") != 0) {
				System.out.println("INPUT MISMATCH");
				return;
			}
			
			// Anzahl der zu lesenden Zeilen auslesen
			if (sc.hasNextInt()) {
				x = sc.nextInt();
			}
			
			else {
				System.out.println("INPUT MISMATCH");
				return;
			}
			
			if (sc.hasNextInt()) {
				y = sc.nextInt();
			}
			
			else {
				System.out.println("INPUT MISMATCH");
				return;
			}
			
			if (sc.hasNext()) {
				c = sc.next().charAt(0);
			}
			
			else {
				System.out.println("INPUT MISMATCH");
				return;
			}
			if ((y >= s.length) || (x >= s[0].length())) {
				System.out.println("OPERATION FAILED");
				return;
			}
			AsciiShop.fill(s, x, y, c);
		}
		
		for (String element : s) {
			System.out.println(element);
		}
		System.out.println("" + laenge + " " + read);
		
	}
}
