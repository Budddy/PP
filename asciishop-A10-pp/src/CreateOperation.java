/**
 * @author Markus.Zisser(1125404)
 * 
 */
public class CreateOperation implements Operation {
	
	AsciiImage	ai;
	
	/**
	 * erzeugt eine neue CreateOperation, die ein neues Bild mit angegebener Bildg��e und Zeichensatz erzeugt. Alle
	 * Pixel werden mit dem "hellsten" Zeichen, d.h. dem Zeichen mit gr��ten Index in charset initialisiert.
	 * 
	 * @param width
	 *            Breite des Bildes
	 * 
	 * @param height
	 *            Hoehe des Bildes
	 * 
	 * @param charset
	 *            Zeichensatz des Bildes
	 * 
	 */
	public CreateOperation(int width, int height, String charset) {
	
		this.ai = new AsciiImage(width, height, charset);
	}
	
	/**
	 * gibt ein neues AsciiImage zur�ck
	 * 
	 * @param img
	 *            wird hier nicht ben�tigt
	 * 
	 * @return
	 *         das neue AsciiImage
	 * 
	 * @throws OperationException
	 *             wird hier nicht geworfen
	 * 
	 */
	@Override
	public AsciiImage execute(AsciiImage img) throws OperationException {
	
		return this.ai;
	}
	
}
