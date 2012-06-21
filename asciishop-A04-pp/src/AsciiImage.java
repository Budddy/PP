/**
 * @author Markus.Zisser(1125404)
 * 
 */
public class AsciiImage {
	
	private String	image;
	private int		height, width;
	
	/**
	 * initialisiert das neue ASCII-Bild
	 */
	public AsciiImage() {
	
		this.height = this.width = 0;
		this.image = "";
	}
	
	/**
	 * fuegt dem Bild eine Zeile hinzu
	 * 
	 * @param line
	 *            die einzufuegende Zeile
	 * @return
	 *         true - Zeile wurde erfolgreich hinzugefuegt
	 *         fale - beim hinzufuegen ist ein Fehler aufgetreten
	 */
	public boolean addLine(String line) {
	
		if (line.length() == 0) {
			return false;
		}
		if (this.height == 0) {
			this.width = line.length();
		}
		if (line.length() != this.width) {
			return false;
		}
		
		this.image = this.image + line;
		this.height++;
		
		return true;
	}
	
	/**
	 * Ersetzt das Zeichen an der Position (x,y) und alle angrenzenden Positionen mit dem selben Zeichen durch das
	 * Zeichen c.
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
	public void fill(int x, int y, char c) {
	
		Character searchChar = this.getPixel(x, y);
		
		if (searchChar.equals(c)) return;
		
		this.setPixel(x, y, c);
		
		if (((x - 1) >= 0) && searchChar.equals(this.getPixel(x - 1, y))) {
			this.fill(x - 1, y, c);
		}
		
		if (((x + 1) < this.width) && searchChar.equals(this.getPixel(x + 1, y))) {
			this.fill(x + 1, y, c);
		}
		
		if (((y - 1) >= 0) && searchChar.equals(this.getPixel(x, y - 1))) {
			this.fill(x, y - 1, c);
		}
		
		if (((y + 1) < this.height) && searchChar.equals(this.getPixel(x, y + 1))) {
			this.fill(x, y + 1, c);
		}
	}
	
	/**
	 * dreht das Bild vertikal um
	 */
	public void flipV() {
	
		String s = "";
		for (int i = this.height; i > 0; i--) {
			s = s + this.image.substring(this.width * (i - 1), this.width * i);
		}
		this.image = s;
	}
	
	/**
	 * gibt die Hoehe des Bildes zurueck
	 * 
	 * @return
	 *         die Hoehe
	 */
	public int getHeight() {
	
		return this.height;
	}
	
	/**
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
	 */
	private char getPixel(int x, int y) {
	
		return this.image.charAt((y * this.width) + x);
	}
	
	/**
	 * gibt aus wieviele unterschiedliche Zeichen es im Bild gibt
	 * 
	 * @return
	 *         Anzahl der unterschiedlichen Zeichen
	 */
	public int getUniqueChars() {
	
		String s = "";
		
		for (int i = 0; i < this.image.length(); i++) {
			if (!s.contains(this.image.charAt(i) + "")) {
				s = s + this.image.charAt(i);
			}
		}
		
		return s.length();
	}
	
	/**
	 * gibt die Breite des Bildes zurueck
	 * 
	 * @return
	 *         die Breite
	 */
	public int getWidth() {
	
		return this.width;
	}
	
	/**
	 * ueberprueft ob das Bild horizontal symetrisch ist
	 * 
	 * @return
	 *         true - wenn es symetrisch ist
	 *         false - wenn es nicht symetrisch ist
	 */
	public boolean isSymmetricH() {
	
		for (int y = 0; y < (this.height); y++) {
			for (int x = 0; x < (this.width-1)/2; x++) {
				if (this.getPixel(x, y) != this.getPixel(this.width-x-1, y)) {
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * speichert an den übergebenen Koordinaten/Indizes auf das übergebene Zeichen.
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
	 */
	private void setPixel(int x, int y, char c) {
	
		String before = this.image.substring(0, (y * this.width) + x);
		
		String after = "";
		if (((y * this.width) + x + 1) < (this.height * this.width)) {
			after = this.image.substring((y * this.width) + x + 1, this.height * this.width);
		}
		
		this.image = before + c + after;
	}
	
	/**
	 * gibt eine lesbare Darstellung des ASCII-Bildes zurueck.
	 */
	@Override
	public String toString() {
	
		String s = "";
		for (int i = 0; i < this.height; i++) {
			s = s + this.image.substring(this.width * i, this.width * (i + 1)) + '\n';
		}
		
		s = s + this.width + " " + this.height;
		return s;
	}
	
	/**
	 * vertauscht Zeilen und Spalten des Bildes
	 */
	public void transpose() {
	
		int w = this.width;
		int h = this.height;
		String s = "";
		
		for (int i = 0; i < this.width; i++) {
			for (int a = 0; a < this.height; a++) {
				s = s + this.image.charAt((a * this.width) + i);
			}
		}
		
		this.height = w;
		this.width = h;
		this.image = s;
	}
}
