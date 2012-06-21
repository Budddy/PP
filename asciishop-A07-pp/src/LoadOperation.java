import java.util.Scanner;

/**
 * @author Markus.Zisser(1125404)
 * 
 */
public class LoadOperation implements Operation {
	
	String	data;
	
	/**
	 * erzeugt eine neue LoadOperation mit den entsprechenden Bilddaten. Diese Bilddaten liegen als String vor, wobei
	 * die Bildzeilen durch Zeilenumbrüche (‘\n’) getrennt sind.
	 * 
	 * @param data
	 */
	public LoadOperation(String data) {
	
		this.data = data;
	}
	
	/**
	 * gibt ein neues AsciiImage zurück, das von Größe und Zeichensatz dem übergebenen AsciiImage entspricht und in das
	 * die Daten geladen wurden. Tritt beim Laden ein Fehler auf (zu wenige oder zu viele Daten bzw. ungültige Zeichen),
	 * so wird eine OperationException mit einer entsprechenden Fehlermeldung geworfen.
	 * 
	 * @param img
	 *            The AsciiImage to use as basis for executing the Operation, it will remain unchanged
	 * @return A new AsciiImage reflecting the result of the executed Operation
	 * @throws OperationException
	 *             Thrown if a problem occurs, while executing the Operation
	 */
	@Override
	public AsciiImage execute(AsciiImage img) throws OperationException {
	
		Scanner sc = new Scanner(this.data);
		String s;
		int y = 0;
		AsciiImage newImg = new AsciiImage(img);
		
		while (sc.hasNextLine()) {
			s = sc.nextLine();
			
			/*
			 * if (s.length() < newImg.getWidth()) throw new OperationException("Zu wenig Daten"); if (s.length() >
			 * newImg.getWidth()) throw new OperationException("Zu viele Daten");
			 */
			
			for (int i = 0; i < newImg.getWidth(); i++) {
				if (!newImg.getCharset().contains(s.charAt(i) + "")) throw new OperationException("ungültige Zeichen");
				newImg.setPixel(i, y, s.charAt(i));
			}
			y++;
		}
		
		if (y < newImg.getHeight()) throw new OperationException("Zu viele Daten");
		if (y > newImg.getHeight()) throw new OperationException("Zu wenig Daten");
		
		return newImg;
	}
}
