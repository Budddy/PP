import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;

/**
 * @author Markus.Zisser(1125404)
 * 
 * @param <E>
 */
@SuppressWarnings("serial")
public class MetricSet<E> extends LinkedHashSet<E> {
	
	/**
	 * initialisiert ein leeres MetricSet
	 */
	public MetricSet() {
	
	}
	
	/**
	 * initialisiert das MetricSet mit den Elementen aus c.
	 * 
	 * @param c
	 *            Elemente für die Initialisierung von c
	 */
	public MetricSet(Collection<? extends E> c) {
	
		this.addAll(c);
	}
	
	/**
	 * liefert ein neues MetricSet zurück, in dem nur die Elemente enthalten sind, die die minimale Distanz zum
	 * spezifizierten Element e haben
	 * 
	 * @param e
	 *            Element zu dem die minimale Distanz berechnet wird
	 * 
	 * @param m
	 *            Metrik zur Distanzmaß
	 * 
	 * @return
	 *         MetricSet mit Elementen mit minimaler Distanz
	 */
	public MetricSet<E> search(E e, Metric<? super E> m) {
	
		if (e == null) return null;
		Iterator<E> ai = this.iterator();
		if (!ai.hasNext()) return null;
		E image = ai.next();
		E next = null;
		MetricSet<E> ms = new MetricSet<E>();
		ms.add(image);
		
		while (ai.hasNext()) {
			next = ai.next();
			
			if (m.distance(e, next) < m.distance(e, image)) {
				image = next;
				ms = new MetricSet<E>();
				ms.add(image);
			}
			
			else
				if (m.distance(e, next) == m.distance(e, image)) {
					ms.add(next);
				}
		}
		
		return ms;
		
	}
}
