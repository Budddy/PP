import java.util.ArrayList;

/**
 * @author Markus.Zisser(1125404)
 * 
 */
public class AsciiImage {
	
	private char[][]	image;
	private int			width;
	private int			height;
	private String		charset;
	
	/**
	 * ist ein Kopierkonstruktor. Er erzeugt ein neues AsciiImage mit dem gleichen Inhalt, wie das übergebene Bild.
	 * 
	 * @param img
	 *            das zu kopierende Bild
	 */
	public AsciiImage(AsciiImage img) {
	
		this.width = img.getWidth();
		this.height = img.getHeight();
		this.image = new char[this.height][this.width];
		this.charset = img.charset;
		
		for (int i = 0; i < this.height; i++) {
			for (int a = 0; a < this.width; a++) {
				this.image[i][a] = img.getPixel(a, i);
			}
			
		}
	}
	
	/**
	 * erzeugt ein ASCII-Bild der spezifizierten Größe und mit dem angegebenen Zeichensatz. Anfangs sind alle Pixel auf
	 * den hellsten Wert des Zeichensatzes (also dem letzten Zeichen des Strings) gesetzt.
	 * 
	 * @param width
	 *            breite des Bildes
	 * 
	 * @param height
	 *            Hoehe des Bildes
	 * 
	 * @param charset
	 *            Zeichensatz des Bildes
	 * 
	 * @throws IllegalArgumentException
	 *             wird geworfen wenn Breite oder Höhe <=0 sind oder wenn der Zeichensatz keine Zeichen beinhaltet
	 */
	public AsciiImage(int width, int height, String charset) {
	
		if ((width <= 0) || (height <= 0) || (charset.length() <= 0)) {
			throw new IllegalArgumentException();
		}
		for (int i = 0; i < charset.length(); i++) {
			if (charset.subSequence(i + 1, charset.length()).toString().contains("" + charset.charAt(i))) {
				throw new IllegalArgumentException();
			}
		}
		
		this.width = width;
		this.height = height;
		this.image = new char[height][width];
		this.charset = charset;
		
		for (int i = 0; i < height; i++) {
			for (int a = 0; a < width; a++) {
				this.image[i][a] = this.charset.charAt(this.charset.length() - 1);
			}
			
		}
	}
	
	/**
	 * gibt den Zeichensatz des Bildes als String zurück.
	 * 
	 * @return
	 *         Der Zeichensatz des Bildes
	 */
	public String getCharset() {
	
		return this.charset;
	}
	
	/**
	 * gibt die Hoehe des Bildes zurueck.
	 * 
	 * @return
	 *         die Hoehe des Bildes
	 */
	public int getHeight() {
	
		return this.height;
	}
	
	/**
	 * gibt das Zeichen an der durch p spezifizierten Stelle zurück.
	 * 
	 * @param p
	 *            Punkt an dem das Pixel ist
	 * 
	 * @return
	 *         Das Zeichen an dem Punkt
	 * 
	 * @throws IndexOutOfBoundsException
	 *             wenn der Punkt ungültig sind
	 */
	public char getPixel(AsciiPoint p) {
	
		if ((p.getX() > this.width) || (p.getY() > this.height)) {
			throw new IndexOutOfBoundsException();
		}
		return this.getPixel(p.getX(), p.getY());
	}
	
	/**
	 * 
	 * gibt das an den übergebenen Koordinaten/Indizes gespeicherte Zeichen zurück.
	 * 
	 * @param x
	 *            x Position
	 * 
	 * @param y
	 *            y Position
	 * 
	 * @return
	 *         das Zeichen an der x/y Position
	 * 
	 * @throws IndexOutOfBoundsException
	 *             wenn die Indizes ungültig sind
	 */
	public char getPixel(int x, int y) {
	
		if ((x > this.width) || (y > this.height)) {
			throw new IndexOutOfBoundsException();
		}
		return this.image[y][x];
	}
	
	/**
	 * gibt eine ArrayList aller Pixel eines bestimmten Zeichens zurück. In dieser ArrayList sind Objekte vom Typ
	 * AsciiPoint, sollte es keine Punkte mit dem angegebenen Zeichen geben, so wird eine leere Liste zurückgegeben
	 * werden.
	 * 
	 * @param c
	 *            zu suchendes Zeichen
	 * 
	 * @return
	 *         Liste mit Positionen des Zeichens im Bild
	 */
	public ArrayList<AsciiPoint> getPointList(char c) {
	
		ArrayList<AsciiPoint> al = new ArrayList<AsciiPoint>();
		for (int i = 0; i < this.image.length; i++) {
			for (int a = 0; a < this.image[i].length; a++) {
				if (this.image[i][a] == c) {
					al.add(new AsciiPoint(a, i));
				}
			}
		}
		return al;
	}
	
	/**
	 * gibt die Breite des Bildes zurueck.
	 * 
	 * @return
	 *         die Breite des Bildes
	 */
	public int getWidth() {
	
		return this.width;
	}
	
	/**
	 * setzt das übergebene Zeichen an der durch p spezifizierten Stelle auf das Zeichen c
	 * 
	 * @param p
	 *            Punkt an dem das Pixel ist
	 * @param c
	 *            Pixel das auf der Position gesetzt werden soll
	 * 
	 * @throws IndexOutOfBoundsException
	 *             falls das Zeichen c nicht dem Zeichensatz des Bildes entspricht
	 *             oder der Punkt nicht innerhalb des Bildes liegt
	 */
	public void setPixel(AsciiPoint p, char c) {
	
		if ((p.getX() > this.width) || (p.getY() > this.height) || !this.charset.contains(c + "")) {
			throw new IndexOutOfBoundsException();
		}
		this.setPixel(p.getX(), p.getY(), c);
	}
	
	/**
	 * speichert an den übergebenen Koordinaten/Indizes das übergebene Zeichen. Überprüfen Sie an dieser Stelle, ob die
	 * Indizes gültig sind und werfen Sie andernfalls eine IndexOutOfBoundsException.
	 * 
	 * @param x
	 *            x Position
	 * 
	 * @param y
	 *            y Position
	 * 
	 * @param c
	 *            new character
	 * 
	 * @throws IndexOutOfBoundsException
	 *             wenn die Indizes ungültig sind oder das Zeichen nicht im Zeichensatz enthalten ist
	 */
	public void setPixel(int x, int y, char c) {
	
		if ((x > this.width) || (y > this.height) || !this.charset.contains(c + "")) {
			throw new IndexOutOfBoundsException();
		}
		this.image[y][x] = c;
	}
	
	/**
	 * gibt eine lesbare Darstellung des ASCII-Bildes zurueck.
	 */
	@Override
	public String toString() {
	
		String s = "";
		for (int i = 0; i < this.height; i++) {
			s = s + String.valueOf(this.image[i]) + '\n';
		}
		
		return s;
	}
}
