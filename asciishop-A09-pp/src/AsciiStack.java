/**
 * @author Markus.Zisser(1125404)
 * 
 */
public class AsciiStack {
	
	/**
	 * @author Markus.Zisser(1125404)
	 * 
	 */
	public class AsciiStackNode {
		
		private AsciiStackNode	next;
		private AsciiImage		ai;
		
		/**
		 * inizialisiert den Listenknoten.
		 * 
		 * @param image
		 *            das einzuf�gende Bild
		 * @param next
		 *            Referenz auf den n�chsten Knoten
		 */
		public AsciiStackNode(AsciiImage image, AsciiStackNode next) {
		
			this.ai = new AsciiImage(image);
			this.next = next;
		}
		
		/**
		 * liefert das vom Knoten referenzierte AsciiImage zur�ck.
		 * 
		 * @return
		 *         das vom Knoten referenzierte AsciiImage
		 */
		public AsciiImage getImage() {
		
			return this.ai;
		}
		
		/**
		 * liefert eine Referenz auf den n�chsten Knoten zur�ck.
		 * 
		 * @return
		 *         eine Referenz auf den n�chsten Knoten
		 */
		public AsciiStackNode getNext() {
		
			return this.next;
		}
		
		/**
		 * liefert die Anzahl der Knoten in der von diesem Knoten referenzierten Restliste plus eins (fÌr diesen
		 * Knoten).
		 * 
		 * @return
		 *         die Anzahl der Knoten in der von diesem Knoten referenzierten Restliste
		 */
		public int size() {
		
			if (this.next == null) return 1;
			return this.next.size() + 1;
		}
		
	}
	
	private AsciiStackNode	head;
	
	/**
	 * erzeugt einen leeren Stack.
	 */
	public AsciiStack() {
	
		this.head = null;
	}
	
	/**
	 * 
	 * �berpr�ft, ob zumindest ein Element am Stack liegt.
	 * 
	 * @return
	 *         true - wenn der Stack leer ist
	 *         false - wenn zumindest ein Element auf dem Stack liegt
	 */
	public boolean empty() {
	
		return this.head == null;
		
	}
	
	/**
	 * 
	 * gibt das oberste Element am Stack zur�ck ohne es zu entfernen. Liegt nichts am Stack, so wird null zur�ckgegeben.
	 * 
	 * @return
	 *         das oberste Element am Stack
	 */
	public AsciiImage peek() {
	
		return this.head.getImage();
		
	}
	
	/**
	 * 
	 * gibt das oberste Element am Stack zur�ck und entfernt dieses. Liegt kein Element am Stack, so wird null
	 * zur�ckgegeben.
	 * 
	 * @return
	 *         das oberste Element am Stack
	 */
	public AsciiImage pop() {
	
		if (this.head == null) return null;
		AsciiStackNode an = this.head;
		this.head = this.head.next;
		return an.getImage();
		
	}
	
	/**
	 * 
	 * legt ein AsciiImage oben auf den Stack.
	 * 
	 * @param img
	 */
	public void push(AsciiImage img) {
	
		if (this.head == null) this.head = new AsciiStackNode(img, null);
		else {
			this.head = new AsciiStackNode(img, this.head);
		}
	}
	
	/**
	 * 
	 * gibt die Anzahl der im Stack belegten Pl�tze zur�ck.
	 * 
	 * @return
	 *         Anzahl der im Stack belegten Pl�tze
	 */
	public int size() {
	
		return this.head.size();
		
	}
	
}
