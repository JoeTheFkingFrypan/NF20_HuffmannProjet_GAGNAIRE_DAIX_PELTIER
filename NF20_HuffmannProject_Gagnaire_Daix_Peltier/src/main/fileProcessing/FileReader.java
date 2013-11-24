package main.fileProcessing;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Map;
import java.util.TreeMap;

import main.HuffmanProcessing.HuffmanBean;

/**
 * Classe permettant de lire un fichier et de retourner le nombre d'occurences de chaque lettre, ordonn� selon ce nombre (ordre croissant)
 * @version 0.3.0
 */

public class FileReader {
	private String url;

	public FileReader() {
	}

	/**
	 * M�thode permettant de r�cuperer les fr�quences de chaque caract�re � partir de l'URL d'un fichier
	 * @param url URL du fichier d�sir�
	 * @return HuffmannBean : Ensemble tri� de noeuds associant : une String correspondant � une lettre, et un Integer (sa fr�quence)

	 */
	public HuffmanBean processLetterFrequencyFrom(String url) {
		this.url = url;
		BufferedReader reader = openFileAt(url);
		if(false){//Methode romain, tr�s bien �crite mais pas tr�s rapide :
			Map<String,Integer> frequencyByLetter = processEntireFileCountingFrequency(reader);
			return new HuffmanBean(frequencyByLetter);
		}
		else
		{//M�thode Daix, pas tr�s bien �crite, mais tr�s rapide :
			String lineToProcess;
			int tab[] = new int[65536];
			for(int i = 0; i < 65536; i++)
				tab[i] = 0;
			
			try {
				while((lineToProcess=reader.readLine())!=null) {
					lineToProcess = normalizeLine(lineToProcess);// <= Je l'ai gard�, mais je trouve �a inutile !
					//Je t'en parle en cours pourquoi... Mais c'est toujours l'histoire des octets.
					//Et m�me pire, si tu dis que A = a, Ok, tu gagne en place, mais tu corrompts le fichier.
					//Tu perds des informations, tu gagne en compression, mais avec pertes.
					//Moi je serais pour, ne plus travailler avec des caract�res mais des octets.
					//Moins de gal�re, le fichier reste le m�me, et on peut traiter des fichiers txt comme des mp3
					//Sans probl�me !
					for(char letter: lineToProcess.toCharArray()) {
						tab[(int)letter]++;
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return new HuffmanBean(tab);
		}
	}

	/**
	 * M�thode permettant de r�cup�rer un flux lisible � partir de l'URL d'un fichier (gestion des erreurs)
	 * @param url String contenant l'URL du fichier souhait�
	 * @return BufferedReader : Flux lisible ligne � ligne
	 * @throws FileReaderException : Exception indiquant avec un message qu'une erreur s'est produite lors de l'ouverture du fichier

	 */
	private BufferedReader openFileAt(String url) throws FileReaderException {
		try {
			InputStream ips = new FileInputStream(url);
			InputStreamReader ipsr = new InputStreamReader(ips);
			return new BufferedReader(ipsr);
		} catch (FileNotFoundException e) {
			throw new FileReaderException("Error while loading file at '" + this.url + "'", e);
		}
	}

	/**
	 * M�thode permettant de r�cuperer les fr�quences de chaque caract�re � partir du flux de donn�es
	 * @param reader BufferedReader correspondant au flux de donn�es provenant du fichier pr�c�dement ouvert
	 * @return Une MAP<String,Integer>, chaque String correspond � une lettre, et chaque Integer associ� � sa fr�quence
	 * @throws FileReaderException Exception avec message indiquant qu'une erreur s'est produite lors de la lecture du fichier

	 */
	private Map<String,Integer> processEntireFileCountingFrequency(BufferedReader reader) throws FileReaderException {
		Map<String,Integer> frequencyByLetter = new TreeMap<String,Integer>();
		try {
			String lineToProcess;
			while((lineToProcess=reader.readLine())!=null) {
				lineToProcess = normalizeLine(lineToProcess);
				processAllLettersFromLine(lineToProcess,frequencyByLetter);
			}
		} catch (IOException e) {
			throw new FileReaderException("Error while reading file at '" + this.url + "'", e);
		}
		return frequencyByLetter;
	}

	/**
	 * M�thode permettant de normaliser la String re�ue (enl�ve tous les espaces et met toutes les lettres en minuscule)
	 * @param lineToProcess String d'origine contenant �ventuellement des caract�res � normaliser
	 * @return renvoie une String sans espaces et sans majuscules

	 */
	private String normalizeLine(String lineToProcess) {
		//lineToProcess = lineToProcess.replaceAll("\\s+", ""); 
		return lineToProcess.toLowerCase();
	}


	/**
	 * M�thode priv�e permettant de r�cuperer la fr�quence de toutes les lettres d'une ligne
	 * @param lineToProcess String contenant la ligne � analyser
	 * @param frequencyByLetter Map<String,Integer> � remplir ou dont les fr�quences sont � incr�menter
	 */
	private void processAllLettersFromLine(String lineToProcess, Map<String, Integer> frequencyByLetter) {	
		for(char letter: lineToProcess.toCharArray()) {
			String expectedKey = String.valueOf(letter);
			if(frequencyByLetter.containsKey(expectedKey)) {
				Integer updatedValue = frequencyByLetter.get(expectedKey) + 1;
				frequencyByLetter.put(expectedKey, updatedValue);
			} else {
				frequencyByLetter.put(expectedKey, 1);
			}
		}
	}

	/**
	 * M�thode de debug pour avoir un rendu visuel sur le contenu du fichier lu
	 * /!\ M�thode qui sera donc supprim�e /!\
	 * @param url : String contenant l'url (chemin relatif) du fichier � ouvrir
	 * @param stream Flux de sortie sur lequel afficher les informations 
	 */
	public void displayTest(PrintStream stream, String url) {
		try {
			BufferedReader reader = openFileAt(url);
			String ligne;
			while((ligne=reader.readLine())!=null) {
				stream.println(ligne);
			}
		} catch (IOException e) {
			throw new FileReaderException("Error while reading file at '" + url + "'", e);
		}
	}
}
