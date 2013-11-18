package main.HuffmanProcessing;

import java.util.Map;
import java.util.Map.Entry;

/**
 * Classe permettant de faire diff�rents types d'affichage
 * @author JoeTheFuckingFrypan
 * @version 0.0.1
 */

public class Displayer {
	/**
	 * M�thode permettant obtenir un rendu visuel de la fr�quence de chaque lettre composant le fichier analys�
	 * @param url : String contenant l'url (chemin relatif) du fichier � ouvrir
	 * @author JoeTheFuckingFrypan
	 */
	public void displayFrequencyResultsFrom(Map<String,Integer> results) {
		insertBlankLine();
		insertHeader("Displaying frenquency from file");
		for(Entry<String, Integer> entry : Sorter.entriesSortedByValues(results)) {
			System.out.println("[" + entry.getKey() + "] has a frequency of : " + entry.getValue());
		}
		insertBlankLine();
	}
	
	/**
	 * M�thode permettant obtenir un rendu visuel de la fr�quence de chaque lettre composant le fichier analys� sous la forme d'une liste
	 * @param url : String contenant l'url (chemin relatif) du fichier � ouvrir
	 * @author JoeTheFuckingFrypan
	 */
	public void displayFrequencyResultsAsListFrom(Map<String,Integer> results) {
		insertBlankLine();
		insertHeader("Displaying frenquency from file");
		System.out.println(Sorter.entriesSortedByValues(results));
		insertBlankLine();
	}	
	
	public void displayTree(HuffmannBean bean) {
		insertBlankLine();
		insertHeader("Displaying frenquency from current tree");
		bean.displayContent();
		insertBlankLine();
	}
	
	
	/**
	 * M�thode priv�e affichant une ligne vide dans la console
	 * @author JoeTheFuckingFrypan
	 */
	private void insertBlankLine() {
		System.out.println("");
	}
	
	/**
	 * M�thode priv�e affichant un header avec texte et emphase dans la console
	 * @param header String � afficher comme header
	 */
	private void insertHeader(String header) {
		System.out.println("=======" + header + "=======");
	}
}
