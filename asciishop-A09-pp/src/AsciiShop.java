import java.util.HashMap;
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
		AsciiStack as = new AsciiStack();
		String charset = "";
		HashMap<String, Factory> hm = new HashMap<String, Factory>();
		hm.put("clear", new ClearFactory());
		hm.put("binary", new BinaryFactory());
		hm.put("filter", new FilterFactory());
		hm.put("load", new LoadFactory());
		hm.put("replace", new ReplaceFactory());
		
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
		
		// read commands
		try {
			while (sc.hasNext() && !inputMismatchError && !unkownCommandError && !oparationFailedError) {
				
				command = sc.next();
				
				if (command.contentEquals("print")) {
					System.out.println(image.toString());
				}
				else
					if (command.contentEquals("undo")) {
						if (as.empty()) System.out.println("STACK EMPTY");
						
						else {
							image = as.pop();
						}
						
					}
					else
						if (hm.get(command) != null) {
							as.push(new AsciiImage(image));
							image = hm.get(command).create(sc).execute(image);
						}
						else unkownCommandError = true;
				
			}
		}
		catch (OperationException oe) {
			oparationFailedError = true;
		}
		catch (FactoryException fe) {
			inputMismatchError = true;
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
