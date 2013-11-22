package main.HuffmanProcessing;

import java.io.PrintStream;

/**
 * Classe correspondant � un noeud dans l'arbre de Huffman (chaque noeud peut contenir une lettre, une fr�quence et un poids, ou simplement 1 � 2 sous-noeuds)
 * @version 0.2.0
 */

public class Node implements Comparable<Node>{
	private Node leftNode;
	private Node rightNode;
	private Integer frequency;
	private final String letter;
	private final Boolean isFinalLeaf;
	private String weight;

	/**
	 * Constructeur d'un noeud correspondant � une lettre (avec fr�quence, avec lettre, et sans sous-noeuds)
	 * @param letter
	 * @param frequency
	 */
	public Node(String letter, Integer frequency) {
		this.letter = letter;
		this.frequency = frequency;
		this.leftNode = null;
		this.rightNode = null;
		this.isFinalLeaf = true;

	}

	/**
	 * Constructeur d'un noeud p�re correspondant � une lettre (avec fr�quence, avec lettre, et sans sous-noeuds)
	 * @param letter
	 * @param frequency
	 * @param leftNode
	 * @param rigthNode
	 */
	public Node(String letter, Integer frequency, Node leftNode, Node rigthNode) {
		this.letter = letter;
		this.frequency = frequency;
		this.leftNode = leftNode;
		this.rightNode = rigthNode;
		this.isFinalLeaf = false;
	}

	/**
	 * M�thode permettant d'incr�menter la fr�quence de la lettre associ�e
	 */
	public void increaseFrequency() {
		++this.frequency;
	}

	/**
	 * M�thode permettant d'assigner � chaque lettre son poids selon l'encodage de Huffman
	 * @param weight String contenant le poids � assigner
	 */
	public void assignWeightToLetter(String weight) {
		if(isFinalLeaf) {
			this.weight = weight;
		}
		if(hasLeftChild()) {
			leftNode.assignWeightToLetter(weight+"0");
		}
		if(hasRightChild()) {
			rightNode.assignWeightToLetter(weight+"1");
		}
	}

	/**
	 * M�thode permettant d'afficher les donn�es d'un noeud, et ceux des sous-noeuds associ�s (s'ils existent)
	 * @param stream Flux de sortie sur lequel afficher le message
	 * @param depth Num�ro correspondant � la profondeur dans l'arbre (0 �tant la racine)
	 */
	public void displayNodeInfoWithDepth(PrintStream stream, int depth) {
		stream.print("[" + getLetter() + "] : " + getFrequency());
		if(hasLeftChild()) {
			stream.println();
			for(int i=0; i<=depth; i++) {
				stream.print("--");
			}
			stream.print("[LEFT] ");
			this.leftNode.displayNodeInfoWithDepth(stream,depth+1);
		}
		if(hasRightChild()) {
			stream.println();
			for(int i=0; i<=depth; i++) {
				stream.print("--");
			}
			stream.print("[RIGHT] ");
			this.rightNode.displayNodeInfoWithDepth(stream,depth+1);
		}
	}

	/**
	 * M�hode permettant d'afficher le poids de chaque noeud
	 * @param stream
	 */
	public void displayWeight(PrintStream stream) {
		if(isFinalLeaf) {
			stream.println("Letter [" + getLetter() + "] weights : " + getWeight());
		}
		if(hasLeftChild()) {
			this.leftNode.displayWeight(stream);
		}
		if(hasRightChild()) {
			this.rightNode.displayWeight(stream);
		}
	}

	/**
	 * M�thode permettant de r�cuperer la lettre associ�e au noeud
	 * @return La lettre associ�e au noeud
	 */
	public String getLetter() {
		return letter;
	}

	/**
	 * M�thode permettant de r�cuperer la fr�quence de la lettre associ�e au noeud
	 * @return La fr�quence de la lettre associ�e au noeud
	 */
	public Integer getFrequency() {
		return frequency;
	}

	/**
	 * M�thode permettant de r�cuperer l'encodage de Huffman pour cette lettre
	 * @return String contenant l'encodage de Huffmann associ�
	 */
	public String getWeight() {
		return this.weight;
	}
	
	/**
	 * M�thode permettant de savoir si le noeud a un fils � gauche
	 * @return true si le noeud a un fils � gauche, false sinon
	 */
	public Boolean hasLeftChild() {
		return this.leftNode != null;
	}

	/**
	 * M�thode permettant de savoir si le noeud a un fils � droite
	 * @return true si le noeud a un fils � droite, false sinon
	 */
	public Boolean hasRightChild() {
		return this.rightNode != null;
	}

	/**
	 * M�thode red�finissant la fa�on dont sont compar�s les noeuds
	 * Trie d'abord sur la fr�quence, et en cas d'�galit� trie sur la lettre
	 */
	@Override
	public int compareTo(Node other) {
		if(this.getFrequency().equals(other.getFrequency())) {
			return this.getLetter().compareTo(other.getLetter());
		} else {
			return this.getFrequency().compareTo(other.getFrequency());
		}
	}
}

