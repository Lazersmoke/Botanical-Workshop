package lazersmoke.botanicalworkshop.common.lexicon;

import vazkii.botania.api.lexicon.LexiconCategory;

public class MLexiconEntry extends WLexiconEntry{

	public MLexiconEntry(String unlocalizedName, LexiconCategory category){
		super(unlocalizedName, category);
		setKnowledgeType(LexiconData.mechanicalKnowledge);
	}
}