package main.HuffmanProcessing;

/**
 * Classe permettant d'obtenir l'arbre de Huffman final (avec attribution des poids correspondant � chaque lettre) � partir d'une arborescence initiale (HuffmanBean)
 * @version 0.2.0
 */
public class HuffmanEncoder {
	public HuffmanEncoder() {
	}
	
	/**
	 * M�thode permettant d'encoder selon la m�thode de Huffmann
	 */
	public void encode(HuffmanBean res) {
		while(res.stillHasNodesToProcess()) {
			res.processingOneMoreStep();
		}
		res.assignWeightToAllLetters();
	}
	
	/**
	 * M�thode permettant d'encoder selon la m�thode de Huffmann (avec affichage de chaque �tape)
	 * @param res 
	 */
	@Deprecated
	public void encodeWithDebugInfo(HuffmanBean res) {
		while(res.stillHasNodesToProcess()) {
			res.processingOneMoreStep();
			res.displayTree(System.out);
		}
		res.assignWeightToAllLetters();
	}
}
