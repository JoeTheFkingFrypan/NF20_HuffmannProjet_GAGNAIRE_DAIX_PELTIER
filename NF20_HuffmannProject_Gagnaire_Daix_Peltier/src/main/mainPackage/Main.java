package main.mainPackage;

import java.io.PrintStream;

import main.HuffmanProcessing.Displayer;
import main.HuffmanProcessing.HuffmanBean;
import main.HuffmanProcessing.HuffmanEncoder;
import main.fileProcessing.FileReader;

/**
 * Classe contenant le point d'entr�e du programme
 * @version 0.2.0
 */

@SuppressWarnings("unused") //temporaire
public class Main {
	public static void main (String[] args) {
		FileReader reader = new FileReader();
		Displayer display = new Displayer();
		PrintStream output = System.out;
		
		String url1 = "src/main/ressources/file.txt";
		String url2 = "src/main/ressources/ex1.txt";
		String url3 = "src/main/ressources/ex2.txt";
		
		encodeFileMeasuringPerformance(reader,url3);
		//encodeFileDisplayingDebugInfo(reader, display, output, url3);
	}

	/**
	 * M�thode priv�e permettant d'encoder un fichier (avec affichage des performances) --temps d'encodage sans affichage
	 * @param url Chemin vers le fichier � analyser et encoder
	 * @param reader Objet permettant de lire le fichier donn� en URL et d'en extraire chaque lettre avec sa fr�quence
	 */
	private static void encodeFileMeasuringPerformance(FileReader reader, String url) {
		long startTime = startTimer();
		HuffmanBean res = reader.proccessOctetFrequencyFrom(url);
		HuffmanEncoder encoder = new HuffmanEncoder();
		encoder.encode(res);
		displayMeasureOfElapsedTimeInSeconds(startTime);
	}
	
	/**
	 * M�thode priv�e permettant d'encoder un fichier avec affichage des diff�rentes �tapes (sans prendre en compte les performances) 
	 * @param url Chemin vers le fichier � analyser et encoder
	 * @param reader Objet permettant de lire le fichier donn� en URL et d'en extraire chaque lettre avec sa fr�quence
	 * @param display Objet fournissant des m�thodes d'affichage basique (affichage de titres avec emphases, sauts de lignes, etc)
	 * @param output Flux de sortie sur lequel afficher toutes les informations
	 */
	private static void encodeFileDisplayingDebugInfo(FileReader reader, Displayer display, PrintStream output, String url) {
		display.insertHeader(output,"Affichage du contenu du fichier");
		reader.displayTest(output,url);
		display.insertBlankLine(output);
		
		display.insertHeader(output,"Affichage des �l�ments avant encodage");
		HuffmanBean res = reader.processLetterFrequencyFrom(url);
		res.displayTree(output);
		display.insertBlankLine(output);
		
		display.insertHeader(output,"Encodage des �l�ments");
		HuffmanEncoder encoder = new HuffmanEncoder();
		encoder.encodeWithDebugInfo(res);
		
		display.insertBlankLine(output);
		display.insertHeader(output,"Poids de chacune des lettres");
		res.displayNodeWeight(output);
	}
	
	/**
	 * M�thode permettant de r�cup�rer l'heure actuelle en nanosecondes
	 * @return Heure en nanosecondes
	 */
	private static long startTimer() {
		return System.nanoTime();
	}
	
	/**
	 * M�thode permettant de d'afficher le temps �coul� par rapport au temps initial (en secondes)
	 * @param startTime Heure de r�f�rence (d�part de la mesure)
	 * @return Temps �coul� en secondes
	 */
	private static void displayMeasureOfElapsedTimeInSeconds(long startTime) {
		PrintStream output = System.out;
		double elapsedTime = (System.nanoTime() - startTime)/1000000000.0;
		output.println("Duration = " + elapsedTime + "secondes");
	}
}
