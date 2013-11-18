package main.HuffmanProcessing;

import java.util.Comparator;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Classe permettant de r�cup�rer un ensemble tri� selon les valeurs � partir d'une MAP
 * @author JoeTheFuckingFrypan
 * @version 0.1
 */
public class Sorter {
	
	/**
	 * M�thode permettant de r�cup�rer un ensemble tri� selon les valeurs � partir d'une MAP<String,Integer> --MAP<K,V> en g�n�ral
	 * @param map Collection ordonn�e selon les cl�s, dont on souhaite un ordonnancement selon les valeurs
	 * @return Collection ordonn�e selon les valeurs
	 */
	public static <K,V extends Comparable<? super V>> SortedSet<Map.Entry<K,V>> entriesSortedByValues(Map<K,V> map) {
	    SortedSet<Map.Entry<K,V>> sortedEntries = new TreeSet<Map.Entry<K,V>>(
	        new Comparator<Map.Entry<K,V>>() {
	            @Override 
	            public int compare(Map.Entry<K,V> e1, Map.Entry<K,V> e2) {
	                if (e1.getValue().equals(e2.getValue())) {
	                	return 1; //permet d'outrepasser les r�gles du compareTo pour permettre d'avoir des fr�quences identiques
	                } else {
	                	return e1.getValue().compareTo(e2.getValue());
	                }
	            }
	        }
	    );
	    sortedEntries.addAll(map.entrySet());
	    return sortedEntries;
	}
}
