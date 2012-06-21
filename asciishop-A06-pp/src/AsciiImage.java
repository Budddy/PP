import java.util.ArrayList;

/**
 * @author Markus.Zisser(1125404)
 * 
 */
public class AsciiImage {
	
	private char[][]	image;
	private int			width;
	private int			height;
	
	/**
	 * Kopierkonstruktor
	 * 
	 * @param img
	 *            zu kopierendes Bild
	 */
	
	public AsciiImage(AsciiImage img) {
	
		this.width = img.getWidth();
		this.height = img.getHeight();
		this.image = new char[this.height][this.width];
		
		for (int i = 0; i < this.height; i++) {
			for (int a = 0; a < this.width; a++) {
				this.image[i][a] = img.getPixel(a, i);
			}
			
		}
	}
	
	/**
	 * initialisiert das neue ASCII-Bild
	 * 
	 * @param width
	 *            Breite
	 * 
	 * @param height
	 *            Hoehe
	 */
	public AsciiImage(int width, int height) {
	
		this.width = width;
		this.height = height;
		this.image = new char[height][width];
		
		for (int i = 0; i < height; i++) {
			for (int a = 0; a < width; a++) {
				this.image[i][a] = '.';
			}
			
		}
	}
	
	/**
	 * setzt alle Pixel des Bildes auf das Zeichen ‘.’
	 */
	public void clear() {
	
		for (int i = 0; i < this.height; i++) {
			for (int a = 0; a < this.width; a++) {
				this.image[i][a] = '.';
			}
			
		}
	}
	
	/**
	 * zeichnet eine Linie zwischen den Koordinaten (x0,y0) und (x1,y1).
	 * 
	 * @param x0
	 * 
	 * @param y0
	 * 
	 * @param x1
	 * 
	 * @param y1
	 * 
	 * @param c
	 *            Zeichen, das für die Linie verwendet werden soll
	 */
	public void drawLine(int x0, int y0, int x1, int y1, char c) {
	
		double dx = x1 - x0;
		double dy = y1 - y0;
		boolean swaped = false;
		
		if (Math.abs(dy) > Math.abs(dx)) {
			swaped = true;
			
			int tmpInt;
			tmpInt = x0;
			x0 = y0;
			y0 = tmpInt;
			
			tmpInt = x1;
			x1 = y1;
			y1 = tmpInt;
			
			double tmpDouble;
			tmpDouble = dx;
			dx = dy;
			dy = tmpDouble;
		}
		
		if (x1 < x0) {
			int tmpInt;
			tmpInt = x0;
			x0 = x1;
			x1 = tmpInt;
			
			tmpInt = y0;
			y0 = y1;
			y1 = tmpInt;
		}
		
		double steigung = dy / dx;
		
		for (double x = x0, y = y0; x <= x1; x++, y += steigung) {
			if (swaped) {
				this.setPixel((int) Math.round(y), (int) Math.round(x), c);
			}
			else {
				this.setPixel((int) Math.round(x), (int) Math.round(y), c);
			}
		}
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
		this.setPixel(x, y, c);
		
		if (searchChar.equals(c)) return;
		
		// search left
		if (((x - 1) >= 0) && searchChar.equals(this.getPixel(x - 1, y))) {
			this.fill(x - 1, y, c);
		}
		
		// search right
		if (((x + 1) < this.width) && searchChar.equals(this.getPixel(x + 1, y))) {
			this.fill(x + 1, y, c);
		}
		
		// search above
		if (((y - 1) >= 0) && searchChar.equals(this.getPixel(x, y - 1))) {
			this.fill(x, y - 1, c);
		}
		
		// search below
		if (((y + 1) < this.height) && searchChar.equals(this.getPixel(x, y + 1))) {
			this.fill(x, y + 1, c);
		}
	}
	
	/**
	 * bestimmt den Schwerpunkt aller Pixel mit dem Zeichen c und gibt diesen als AsciiPoint zurück. Kommt das Zeichen
	 * nicht vor, so wird null zurückgegeben.
	 * 
	 * @param c
	 *            Zeichen von dem der Schwerpunkt bestimmt werden soll
	 * 
	 * @return
	 *         der Schwerpunkt
	 */
	public AsciiPoint getCentroid(char c) {
	
		float fx = 0, fy = 0;
		int anz = 0, x, y;
		ArrayList<AsciiPoint> al = this.getPointList(c);
		if (al.isEmpty()) return null;
		for (anz = 0; anz < al.size(); anz++) {
			fx = fx + al.get(anz).getX();
			fy = fy + al.get(anz).getY();
		}
		x = Math.round(fx / anz);
		y = Math.round(fy / anz);
		return new AsciiPoint(x, y);
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
	 * gibt, an der durch p spezifizierten Stelle, zurück.
	 * 
	 * @param p
	 *            Position des Zeichens
	 * 
	 * @return
	 *         das Zeichen
	 */
	public char getPixel(AsciiPoint p) {
	
		return this.getPixel(p.getX(), p.getY());
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
	public char getPixel(int x, int y) {
	
		return this.image[y][x];
	}
	
	/**
	 * gibt eine ArrayList aller Pixel eines bestimmten Zeichens zurück.
	 * 
	 * @param c
	 *            das zu suchende Zeichen
	 * 
	 * @return
	 *         Liste mit den Positionen des Zeichens
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
	 * gibt die Breite des Bildes zurueck
	 * 
	 * @return
	 *         die Breite
	 */
	public int getWidth() {
	
		return this.width;
	}
	
	/**
	 * vergrößert die die Flächen aller Pixel des Zeichens c, in dem es an diese Pixel angrenzende Hintergrundpixel
	 * (also Pixel mit dem Zeichen ‘.’) auf das Zeichen c setzt.
	 * 
	 * @param c
	 *            Das Zeichen, das wachsen soll
	 */
	public void growRegion(char c) {
	
		ArrayList<AsciiPoint> al = this.getPointList(c);
		int x, y;
		for (int i = 0; i < al.size(); i++) {
			x = al.get(i).getX();
			y = al.get(i).getY();
			if (((y + 1) < this.getHeight()) && (this.getPixel(x, y + 1) == '.')) this.setPixel(x, y + 1, c);
			if (((y - 1) >= 0) && (this.getPixel(x, y - 1) == '.')) this.setPixel(x, y - 1, c);
			if (((x + 1) < this.getWidth()) && (this.getPixel(x + 1, y) == '.')) this.setPixel(x + 1, y, c);
			if (((x - 1) >= 0) && (this.getPixel(x - 1, y) == '.')) this.setPixel(x - 1, y, c);
		}
	}
	
	/**
	 * ersetzt alle Vorkommen eines bestimmten Zeichens oldChar im Bild durch ein anderes Zeichen newChar.
	 * 
	 * @param oldChar
	 *            zu ersetzendes Zeichen
	 * @param newChar
	 *            neues Zeichen
	 */
	public void replace(char oldChar, char newChar) {
	
		for (int i = 0; i < this.height; i++) {
			for (int a = 0; a < this.width; a++) {
				if (this.image[i][a] == oldChar) {
					this.image[i][a] = newChar;
				}
			}
		}
	}
	
	/**
	 * speichert, das übergebene Zeichen an der durch den Punkt p spezifizierten Stelle.
	 * 
	 * @param p
	 *            Punkt an dem das Zeichen gesetzt werden soll
	 * 
	 * @param c
	 *            das zu setzende Zeichen
	 */
	public void setPixel(AsciiPoint p, char c) {
	
		this.setPixel(p.getX(), p.getY(), c);
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
	public void setPixel(int x, int y, char c) {
	
		this.image[y][x] = c;
	}
	
	/**
	 * glaettet das bild durch Entfernen dünner Linien bzw. einzelner Störpixel
	 * 
	 * @param c
	 *            Zeichen auf das die Glaettung angewendet werden soll
	 */
	public void straightenRegion(char c) {
	
		// AsciiImage ai=new AsciiImage(this);
		ArrayList<AsciiPoint> al = this.getPointList(c);
		int x, y, a;
		
		for (int i = 0; i < al.size(); i++) {
			a = 0;
			x = al.get(i).getX();
			y = al.get(i).getY();
			if (((y + 1) < this.getHeight()) && (this.getPixel(x, y + 1) == c)) a++;
			if (((y - 1) >= 0) && (this.getPixel(x, y - 1) == c)) a++;
			if (((x + 1) < this.getWidth()) && (this.getPixel(x + 1, y) == c)) a++;
			if (((x - 1) >= 0) && (this.getPixel(x - 1, y) == c)) a++;
			
			if (a <= 1) this.setPixel(al.get(i), '.');
			
		}
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
	
	/**
	 * vertauscht Zeilen und Spalten des Bildes
	 */
	public void transpose() {
	
		char[][] tmpImage = new char[this.width][this.height];
		
		for (int i = 0; i < this.width; i++) {
			for (int a = 0; a < this.height; a++) {
				tmpImage[i][a] = this.image[a][i];
			}
		}
		
		int tmpInt = this.height;
		this.height = this.width;
		this.width = tmpInt;
		this.image = tmpImage;
	}
}
