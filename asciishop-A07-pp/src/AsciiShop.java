import java.util.Scanner;

/**
 * @author Markus.Zisser(1125404)
 * 
 */
public class AsciiShop {
	
	/**
	 * liest die Daten und Befehle ein und gibt das Ergebnis aus. Allein diese Methode liest direkt von System.in ein
	 * und gibt direkt auf System.out aus.
	 * 
	 * @param args
	 * 
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
		char c = ' ', cOld = ' ';
		String eof = "";
		String nextLine = "";
		String s = "";
		AsciiStack as = new AsciiStack();
		String charset = "";
		
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
				
				if (sc.hasNext() && !inputMismatchError) {
					charset = sc.next();
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
		
		image = new AsciiImage(width, height, charset);
		
		try {
			// read commands
			while (sc.hasNext() && !inputMismatchError && !unkownCommandError && !oparationFailedError) {
				
				command = sc.next();
				unkownCommandError = true;
				
				// clear command
				if (command.contentEquals("clear")) {
					unkownCommandError = false;
					as.push(new AsciiImage(image));
					image = new ClearOperation().execute(image);
					
				}
				
				// filter command
				if (command.contentEquals("filter")) {
					if (!sc.hasNext() || !sc.next().contentEquals("median")) {
						inputMismatchError = true;
						break;
					}
					unkownCommandError = false;
					as.push(new AsciiImage(image));
					image = new MedianOperation().execute(image);
					
				}
				
				// load command
				if (command.contentEquals("load")) {
					s = "";
					unkownCommandError = false;
					as.push(new AsciiImage(image));
					if (sc.hasNext()) eof = sc.next();
					else {
						inputMismatchError = true;
						break;
					}
					sc.nextLine();
					while (sc.hasNextLine()) {
						nextLine = sc.nextLine();
						if (nextLine.contains(eof)) break;
						s = s + nextLine + "\n";
					}
					
					image = new LoadOperation(s).execute(image);
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
					if (!image.getCharset().contains(c + "")) {
						oparationFailedError = true;
						break;
					}
					image = new ReplaceOperation(cOld, c).execute(image);
					
				}
				
				// undo command
				if (command.contentEquals("undo")) {
					unkownCommandError = false;
					if (as.empty()) System.out.println("STACK EMPTY");
					else {
						image = as.pop();
					}
					
				}
				
			}
		}
		catch (OperationException oe) {
			oparationFailedError = true;
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
