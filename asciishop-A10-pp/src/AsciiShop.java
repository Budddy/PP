import java.util.HashMap;
import java.util.Iterator;
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
	
		MetricSet<AsciiImage> imagelist = new MetricSet<AsciiImage>();
		Scanner sc = new Scanner(System.in);
		String command;
		AsciiStack as = new AsciiStack();
		HashMap<String, Factory> hm = new HashMap<String, Factory>();
		
		// Fehler
		boolean inputMismatchError = false;
		boolean unkownCommandError = false;
		boolean oparationFailedError = false;
		
		// erzeugen der Factorys
		hm.put("clear", new ClearFactory());
		hm.put("binary", new BinaryFactory());
		hm.put("filter", new FilterFactory());
		hm.put("load", new LoadFactory());
		hm.put("replace", new ReplaceFactory());
		hm.put("save", new SaveFactory(imagelist));
		hm.put("search", new SearchFactory(imagelist));
		hm.put("create", new CreateFactory());
		
		// create
		AsciiImage image = null;
		String s1 = sc.next();
		if (!s1.contentEquals("create")) {
			System.out.println("INPUT MISMATCH");
			return;
		}
		
		try {
			image = hm.get(s1).create(sc).execute(image);
		}
		catch (OperationException e) {
			System.out.println("OPERATION FAILED");
			return;
		}
		catch (FactoryException e) {
			System.out.println("INPUT MISMATCH");
			return;
		}
		
		sc.nextLine();
		
		// /////////////////
		// read commands //
		// /////////////////
		try {
			while (sc.hasNext() && !inputMismatchError && !unkownCommandError && !oparationFailedError) {
				
				command = sc.next();
				
				if (command.contentEquals("print")) {
					System.out.println(image.toString());
				}
				else
					if (command.contentEquals("printsaved")) {
						Iterator<AsciiImage> ait = imagelist.iterator();
						if (!ait.hasNext()) {
							System.out.println("NO SAVED IMAGES");
						}
						
						while (ait.hasNext()) {
							System.out.println(ait.next());
						}
						
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
