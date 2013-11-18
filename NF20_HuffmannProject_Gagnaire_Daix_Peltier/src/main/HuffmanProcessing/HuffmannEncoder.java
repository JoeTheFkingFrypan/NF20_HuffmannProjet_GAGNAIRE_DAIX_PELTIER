package main.HuffmanProcessing;

import java.util.Map;

public class HuffmannEncoder {
	private HuffmannBean bean;
	
	public HuffmannEncoder(Map<String,Integer> originalValues) {
		this.bean = new HuffmannBean(originalValues);
	}
	
	/**
	 * M�thode permettant d'encoder selon la m�thode de Huffmann
	 */
	public void encode() {
		while(bean.stillHasNodesToProcess()) {
			bean.processingOneMoreStep();
		}
	}
	
	/**
	 * M�thode permettant d'encoder selon la m�thode de Huffmann (avec affichage de chaque �tape)
	 */
	@Deprecated
	public void encodeWithDebugInfo() {
		while(bean.stillHasNodesToProcess()) {
			bean.processingOneMoreStep();
			bean.displayContent();
		}
	}
	
}
