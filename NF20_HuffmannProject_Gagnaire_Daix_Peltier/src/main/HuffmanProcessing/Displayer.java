package main.HuffmanProcessing;

import java.io.PrintStream;

/**
 * Classe permettant de faire diff�rents types d'affichage
 * @version 0.2.0
 */

public class Displayer {
	/**
	 * M�thode affichant une ligne vide dans la console
	
	 * @param stream Flux de sortie � utiliser
	 */
	public void insertBlankLine(PrintStream stream) {
		stream.println("");
	}
	
	/**
	 * M�thode affichant un header avec texte et emphase dans la console
	 * @param stream Flux de sortie � utiliser
	 * @param header String � afficher comme header
	 */
	public void insertHeader(PrintStream stream, String header) {
		stream.println("======= " + header + " =======");
	}
}
