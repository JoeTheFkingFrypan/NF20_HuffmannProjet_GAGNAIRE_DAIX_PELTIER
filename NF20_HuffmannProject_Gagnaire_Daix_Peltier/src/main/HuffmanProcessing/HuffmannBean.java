package main.HuffmanProcessing;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * 
 * @author JoeTheFuckingFrypan
 * @version 0.1
 */
public class HuffmannBean {
	private SortedSet<Node> tree;
	
	public HuffmannBean(Map<String, Integer> originalValues) {
		this.tree = new TreeSet<Node>();
		for(Entry<String, Integer> entry : originalValues.entrySet()) {
			tree.add(new Node(entry.getKey(),entry.getValue()));
		}
	}
	
	/**
	 * M�thode permettant de v�rifier si l'algorithme est termin�
	 * @return true s'il reste plus de 2 noeuds ou false, s'il n'en reste plus qu'un
	 */
	public boolean stillHasNodesToProcess() {
		if(tree.size() >= 2) {
			return true;
		}
		return false;
	}
	
	/**
	 * M�thode permettant de progression d'une �tape dans la r�alisation du codage de huffmann
	 * Principe de fonctionnement :
	 * - R�cupere la lettre ayant la fr�quence la plus basse
	 * - V�rifie s'il existe s'il existe une lettre ayant une fr�quence plus �lev�e (lettre suivante dans la collection)
	 * - S'il en existe une alors, les deux sont mix�es en une (en faisant la somme de leurs fr�quences respectives)
	 */
	public void processingOneMoreStep() {
		Iterator<Node> it = tree.iterator();
		Node lowestEntry;

		lowestEntry = it.next(); //R�cup�ration de la lettre ayant la plus petite fr�quence

		if(it.hasNext()) {
			mergeLowestTwoValues(it,lowestEntry); //addition des fr�quences s'il reste encore une lettre
		}
	}

	/**
	 * M�thode priv�e permettant de mixer deux lettres en une (en additionnant leurs fr�quences respectives)
	 * @param it Iterateur permettant de parcourir la collection
	 * @param lowestEntry Entry r�cup�r�e par la m�thode pr�c�dente (correspond � la lettre ayant la fr�quence minimale)
	 */
	private void mergeLowestTwoValues(Iterator<Node> it, Node lowestEntry) {
		Node nextLowestEntry;
		String letterOne, letterTwo;
		Integer frequencyOne, frequencyTwo;

		letterOne = lowestEntry.getLetter();
		frequencyOne = lowestEntry.getFrequency();

		if(it.hasNext()) {
			nextLowestEntry = it.next();
			letterTwo = nextLowestEntry.getLetter();
			frequencyTwo = nextLowestEntry.getFrequency();

			String mergedLetters = letterOne + "." + letterTwo;
			Integer mergedFrequency = frequencyOne + frequencyTwo;

			insertNewNodeWithLeaves(mergedLetters, mergedFrequency, lowestEntry, nextLowestEntry);
			System.out.println("");
			System.out.println("[MERGING] " + letterOne + "(" + frequencyOne + ")" + " AND " + letterTwo + "(" + frequencyTwo + ")" + " => " + mergedLetters + "(" + mergedFrequency + ")"); 
		}
	}

	/**
	 * M�thode permettant d'inserer un nouveau noeud dans l'arbre (en prenant ajoutant les deux pr�c�dents comme fils)
	 * @param mergedLetters String contenant la (ou les) lettre(s)
	 * @param mergedFrequency Integer contenant la fr�quence (ou somme de fr�quences) associ�e(s)
	 * @param lowestEntry Noeud correpondant � la fr�quence la plus faible
	 * @param nextLowestEntry Noeud correspondant � la fr�quence (suivante) la plus faible
	 */
	private void insertNewNodeWithLeaves(String mergedLetters, Integer mergedFrequency, Node lowestEntry, Node nextLowestEntry) {
		tree.add(new Node(mergedLetters,mergedFrequency,lowestEntry,nextLowestEntry));
		tree.remove(lowestEntry);
		tree.remove(nextLowestEntry);
	}

	/**
	 * M�thode permettant d'afficher le contenu de l'arbre
	 */
	public void displayContent() {
		for(Node node : tree) {
			System.out.println("");
			node.displayNodeInfoWithDepth(0);
		}
	}
}
