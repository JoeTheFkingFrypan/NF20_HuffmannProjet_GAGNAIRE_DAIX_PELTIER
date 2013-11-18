package main.HuffmanProcessing;

public class Node implements Comparable<Node>{
	private String letter;
	private Integer frequency;
	private Node leftNode;
	private Node rightNode;
	
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
	}
	
	/**
	 * M�thode permettant d'afficher les donn�es d'un noeud, et ceux des sous-noeuds associ�s (s'ils existent)
	 * @param depth Num�ro correspondant � la profondeur dans l'arbre (0 �tant la racine)
	 */
	public void displayNodeInfoWithDepth(int depth) {
		System.out.print("[" + getLetter() + "] : " + getFrequency());
		if(hasLeftChild()) {
			System.out.println();
			for(int i=0; i<=depth; i++) {
				System.out.print("--");
			}
			System.out.print("[LEFT] ");
			this.leftNode.displayNodeInfoWithDepth(depth+1);
		}
		if(hasRightChild()) {
			System.out.println();
			for(int i=0; i<=depth; i++) {
				System.out.print("--");
			}
			System.out.print("[RIGHT] ");
			this.rightNode.displayNodeInfoWithDepth(depth+1);
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

