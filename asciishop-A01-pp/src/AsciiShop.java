import java.util.Scanner;

/**
 * @author Markus.Zisser(1125404)
 * 
 */
public class AsciiShop {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
	
		int laenge = 0;
		int hoee = 0;
		Scanner sc = new Scanner(System.in);
		boolean fehler = false;
		
		if (sc.hasNextLine()) {
			// Ermittlung der Länge der ersten Zeile
			laenge = sc.nextLine().length();
			hoee += 1;
			
			while (sc.hasNextLine()) {
				hoee++;
				
				// Ermittlung ob eine Zeile zu kurz oder zu lang ist
				if (laenge != sc.nextLine().length()) {
					System.out.println("INPUT MISMATCH");
					fehler = true;
					break;
				}
				
			}
			
			// Ausgabe der Laenge und Hoee wenn kein Fehler aufgetreten ist
			if (!fehler) System.out.println("" + laenge + " " + hoee);
		}
		
		// Wenn keine Zeile im Asciibild enthalten ist.
		else {
			System.out.println("INPUT MISMATCH");
		}
		
	}
}
