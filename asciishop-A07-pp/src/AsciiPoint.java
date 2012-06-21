/**
 * @author Markus.Zisser(1125404)
 * 
 */
public final class AsciiPoint {
	
	final int	x;
	final int	y;
	
	/**
	 * erzeugt einen Punkt mit den angegebenen Koordinaten.
	 * 
	 * @param x
	 * @param y
	 */
	public AsciiPoint(int x, int y) {
	
		this.x = x;
		this.y = y;
	}
	
	/**
	 * gibt die x-Koordinate des Punktes zurück.
	 * 
	 * @return
	 *         die x-Koordinate
	 */
	public int getX() {
	
		return this.x;
	}
	
	/**
	 * gibt die y-Koordinate des Punktes zurück.
	 * 
	 * @return
	 *         die y-Koordinate
	 */
	public int getY() {
	
		return this.y;
	}
	
	/**
	 * gibt eine lesbare Darstellung des Punktes in der Form (x,y) zurück.
	 * 
	 */
	@Override
	public String toString() {
	
		return "(" + this.x + "," + this.y + ")";
	}
	
}
