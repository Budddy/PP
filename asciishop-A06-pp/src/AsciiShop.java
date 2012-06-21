import java.util.Scanner;

/**
 * @author Markus.Zisser(1125404)
 * 
 */
public class AsciiShop {
	
	/**
	 * liest die Daten und Befehle ein und gibt das Ergebnis aus
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
	
		AsciiImage image;
		Scanner sc = new Scanner(System.in);
		int height = 0;
		int width = 0;
		String command;
		boolean inputMismatchError = false;
		boolean unkownCommandError = false;
		boolean oparationFailedError = false;
		int x0 = -1, x1 = -1, y0 = -1, y1 = -1;
		char c = ' ', cOld = ' ';
		String eof = "";
		String nextLine = "";
		int i = 0;
		String s = "";
		AsciiStack as = new AsciiStack(3);
		
		// create
		if (sc.hasNext()) {
			
			if (sc.next().contentEquals("create") && !inputMismatchError) {
				
				if (sc.hasNextInt() && !inputMismatchError) {
					
					width = sc.nextInt();
					
					if ((width <= 0) && !inputMismatchError) inputMismatchError = true;
					
				}
				else inputMismatchError = true;
				if (sc.hasNextInt() && !inputMismatchError) {
					
					height = sc.nextInt();
					
					if ((height <= 0) && !inputMismatchError) inputMismatchError = true;
					
				}
				else inputMismatchError = true;
			}
			else inputMismatchError = true;
		}
		else inputMismatchError = true;
		
		if (inputMismatchError) {
			System.out.println("INPUT MISMATCH");
			return;
		}
		sc.nextLine();
		
		image = new AsciiImage(width, height);
		
		// read commands
		while (sc.hasNext() && !inputMismatchError && !unkownCommandError && !oparationFailedError) {
			
			command = sc.next();
			unkownCommandError = true;
			
			// clear command
			if (command.contentEquals("clear")) {
				unkownCommandError = false;
				as.push(new AsciiImage(image));
				image.clear();
				
			}
			
			// line command
			if (command.contentEquals("line")) {
				unkownCommandError = false;
				as.push(new AsciiImage(image));
				
				if (!(sc.hasNextInt() && ((x0 = sc.nextInt()) >= 0) && (x0 <= image.getWidth()))) {
					inputMismatchError = true;
					break;
				}
				
				if (!(sc.hasNextInt() && ((y0 = sc.nextInt()) >= 0) && (y0 <= image.getHeight()))) {
					inputMismatchError = true;
					break;
				}
				
				if (!(sc.hasNextInt() && ((x1 = sc.nextInt()) >= 0) && (x1 <= image.getWidth()))) {
					inputMismatchError = true;
					break;
				}
				
				if (!(sc.hasNextInt() && ((y1 = sc.nextInt()) >= 0) && (y1 <= image.getHeight()))) {
					inputMismatchError = true;
					break;
				}
				
				if (sc.hasNext()) c = sc.next().charAt(0);
				else {
					inputMismatchError = true;
					break;
				}
				
				image.drawLine(x0, y0, x1, y1, c);
				x0 = y0 = x1 = y1 = -1;
			}
			
			// transpose command
			if (command.contentEquals("transpose")) {
				unkownCommandError = false;
				as.push(new AsciiImage(image));
				image.transpose();
			}
			
			// load command
			if (command.contentEquals("load")) {
				i = 0;
				unkownCommandError = false;
				as.push(new AsciiImage(image));
				if (sc.hasNext()) eof = sc.next();
				else {
					inputMismatchError = true;
					break;
				}
				
				while (sc.hasNext()) {
					nextLine = sc.next();
					if (nextLine.contains(eof)) break;
					
					if (i >= image.getHeight()) {
						inputMismatchError = true;
						break;
					}
					if (nextLine.length() != image.getWidth()) {
						inputMismatchError = true;
						break;
					}
					
					for (int a = 0; a < nextLine.length(); a++) {
						image.setPixel(a, i, nextLine.charAt(a));
					}
					i++;
				}
			}
			
			// print command
			if (command.contentEquals("print")) {
				unkownCommandError = false;
				System.out.println(image.toString());
			}
			
			// replace command
			if (command.contentEquals("replace")) {
				unkownCommandError = false;
				as.push(new AsciiImage(image));
				if (sc.hasNext()) {
					s = sc.next();
					cOld = s.charAt(0);
				}
				else {
					inputMismatchError = true;
					break;
				}
				if (sc.hasNext()) {
					s = sc.next();
					c = s.charAt(0);
				}
				else {
					inputMismatchError = true;
					break;
				}
				image.replace(cOld, c);
				
			}
			
			// fill command
			if (command.contentEquals("fill")) {
				unkownCommandError = false;
				as.push(new AsciiImage(image));
				if (sc.hasNextInt()) {
					x0 = sc.nextInt();
				}
				else {
					inputMismatchError = true;
					break;
				}
				
				if (sc.hasNextInt()) {
					y0 = sc.nextInt();
				}
				else {
					inputMismatchError = true;
					break;
				}
				
				if (sc.hasNext()) {
					s = sc.next();
					c = s.charAt(0);
				}
				else {
					inputMismatchError = true;
					break;
				}
				if ((x0 < 0) || (x0 >= image.getWidth()) || (y0 < 0) || (y0 >= image.getHeight())) {
					oparationFailedError = true;
					break;
				}
				image.fill(x0, y0, c);
			}
			
			// centroid command
			if (command.contentEquals("centroid")) {
				unkownCommandError = false;
				if (sc.hasNext()) c = sc.next().charAt(0);
				else {
					inputMismatchError = true;
					break;
				}
				System.out.println(image.getCentroid(c));
			}
			
			// grow command
			if (command.contentEquals("grow")) {
				as.push(new AsciiImage(image));
				unkownCommandError = false;
				if (sc.hasNext()) c = sc.next().charAt(0);
				else {
					inputMismatchError = true;
					break;
				}
				image.growRegion(c);
			}
			
			// undo command
			if (command.contentEquals("undo")) {
				unkownCommandError = false;
				if (as.empty()) System.out.println("STACK EMPTY");
				else {
					image = as.pop();
					System.out.println("STACK USAGE " + as.size() + "/" + as.capacity());
				}
				
			}
			
			// straighten command
			if (command.contentEquals("straighten")) {
				as.push(new AsciiImage(image));
				unkownCommandError = false;
				if (sc.hasNext()) {
					c = sc.next().charAt(0);
					image.straightenRegion(c);
				}
				else {
					inputMismatchError = true;
					break;
				}
				
			}
			
		}
		
		if (oparationFailedError) {
			System.out.println("OPERATION FAILED");
		}
		if (unkownCommandError) {
			System.out.println("UNKNOWN COMMAND");
		}
		if (inputMismatchError) {
			System.out.println("INPUT MISMATCH");
		}
		
	}
	
}
