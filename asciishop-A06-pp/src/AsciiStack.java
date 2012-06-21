/**
 * @author Markus.Zisser(1125404)
 * 
 */
public class AsciiStack {
	
	private AsciiImage[]	aImg;
	private int				increment;
	private int				elementCount;
	
	/**
	 * erzeugt einen Stack, der initial increment Elemente speichern kann.
	 * 
	 * @param increment
	 */
	public AsciiStack(int increment) {
	
		this.aImg = new AsciiImage[increment];
		this.increment = increment;
		this.elementCount = 0;
	}
	
	/**
	 * gibt die Anzahl der Stack bereit stehenden Plätze zurück (sprich wie groß das zu Grunde liegende Array ist)
	 * 
	 * @return
	 *         die Anzahl der Stack bereit stehenden Plätze
	 */
	
	public int capacity() {
	
		return this.aImg.length;
	}
	
	/**
	 * überprüft, ob zumindest ein Element am Stack liegt.
	 * 
	 * @return
	 *         true - wenn der Stack leer ist
	 *         false - wenn zumindest ein Element auf dem Stack liegt
	 */
	public boolean empty() {
	
		return this.elementCount == 0;
		
	}
	
	/**
	 * 
	 * gibt das oberste Element am Stack zurück ohne es zu entfernen. Liegt nichts am Stack, so wird null zurückgegeben.
	 * 
	 * @return
	 *         das oberste Element am Stack
	 */
	public AsciiImage peek() {
	
		if (this.elementCount == 0) return null;
		return this.aImg[this.elementCount - 1];
		
	}
	
	/**
	 * 
	 * gibt das oberste Element am Stack zurück und entfernt dieses. Liegt kein Element am Stack, so wird null
	 * zurückgegeben.
	 * 
	 * @return
	 *         das oberste Element am Stack
	 */
	public AsciiImage pop() {
	
		if (this.elementCount == 0) return null;
		
		AsciiImage aitmp;
		aitmp = this.aImg[this.elementCount - 1];
		this.aImg[this.elementCount - 1] = null;
		this.elementCount--;
		
		if ((this.aImg.length - this.elementCount) > this.increment) {
			AsciiImage[] tempImg = new AsciiImage[this.aImg.length - this.increment];
			for (int i = 0; i < tempImg.length; i++) {
				tempImg[i] = this.aImg[i];
			}
			this.aImg = tempImg;
		}
		
		return aitmp;
		
	}
	
	/**
	 * 
	 * legt ein AsciiImage oben auf den Stack.
	 * 
	 * @param img
	 */
	public void push(AsciiImage img) {
	
		if (this.aImg.length < (this.elementCount + 1)) {
			AsciiImage[] tempImg = new AsciiImage[this.aImg.length + this.increment];
			for (int i = 0; i < this.aImg.length; i++) {
				tempImg[i] = this.aImg[i];
			}
			this.aImg = tempImg;
		}
		
		this.aImg[this.elementCount] = img;
		this.elementCount++;
	}
	
	/**
	 * 
	 * gibt die Anzahl der im Stack belegten Plätze zurück.
	 * 
	 * @return
	 *         Anzahl der im Stack belegten Plätze
	 */
	public int size() {
	
		return this.elementCount;
		
	}
	
}
