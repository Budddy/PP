import java.util.Scanner;

/**
 * @author Markus.Zisser(1125404)
 * 
 */
public class AsciiShop {
	
	/**
	 * liest die Daten und Befehle ein und gibt das Ergebnis aus.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
	
		AsciiImage image = new AsciiImage();
		Scanner sc = new Scanner(System.in);
		int hight = 0;
		String command = "";
		int x = 0, y = 0;
		char f = ' ';
		
		if (sc.hasNext()) {
			if (sc.next().contentEquals("read")) {
				if (sc.hasNextInt()) {
					hight = sc.nextInt();
				}
				else {
					System.out.println("INPUT MISMATCH");
					return;
				}
			}
			else {
				System.out.println("INPUT MISMATCH");
				return;
			}
		}
		else {
			System.out.println("INPUT MISMATCH");
			return;
		}
		
		sc.nextLine();
		
		for (int i = 0; sc.hasNextLine() && (hight > i); i++) {
			if (!image.addLine(sc.nextLine())) {
				System.out.println("INPUT MISMATCH");
				return;
			}
		}
		
		while (sc.hasNext()) {
			command = sc.next();
			if (command.contentEquals("uniqueChars")) {
				System.out.println(image.getUniqueChars() + "");
			}
			else {
				if (command.contentEquals("flip-v")) {
					image.flipV();
				}
				else {
					if (command.contentEquals("transpose")) {
						image.transpose();
					}
					else {
						if (command.contentEquals("fill")) {
							if (sc.hasNextInt()) {
								x = sc.nextInt();
							}
							else {
								System.out.println("OPERATION FAILED");
								return;
							}
							
							if (sc.hasNextInt()) {
								y = sc.nextInt();
							}
							else {
								System.out.println("OPERATION FAILED");
								return;
							}
							
							if (sc.hasNext()) {
								f = sc.next().charAt(0);
							}
							else {
								System.out.println("OPERATION FAILED");
								return;
							}
							
							if ((image.getHeight() < (y + 1)) || (image.getWidth() < (x + 1))) {
								System.out.println("OPERATION FAILED");
								return;
							}
							
							image.fill(x + 1, y + 1, f);
						}
						else {
							if (command.contentEquals("symmetric-h")) {
								System.out.println(image.isSymmetricH() + "");
							}
							else {
								System.out.println("INPUT MISMATCH");
								return;
							}
						}
					}
				}
			}
		}
		
		System.out.println(image.toString());
	}
}
