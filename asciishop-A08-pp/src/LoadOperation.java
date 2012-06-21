import java.util.Scanner;

/**
 * @author Markus.Zisser(1125404)
 * 
 */
public class LoadOperation implements Operation {
	
	String	data;
	
	/**
	 * erzeugt eine neue LoadOperation mit den entsprechenden Bilddaten
	 * 
	 * @param data
	 *            Bilddaten
	 */
	public LoadOperation(String data) {
	
		this.data = data;
	}
	
	/**
	 * gibt ein neues AsciiImage zurück, das von Größe und Zeichensatz dem übergebenen AsciiImage entspricht und in das
	 * die Daten geladen wurden
	 * 
	 * @param img
	 *            Image in das die Daten geladen werden
	 * 
	 * @return
	 *         Ergebnisbild
	 * 
	 * @throws OperationException
	 *             Wenn beim Laden ein Fehler auftritt (zu wenige oder zu viele Daten bzw. ungültige Zeichen)
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
