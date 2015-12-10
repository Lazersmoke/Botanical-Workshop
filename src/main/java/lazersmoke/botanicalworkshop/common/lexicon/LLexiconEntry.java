package lazersmoke.botanicalworkshop.common.lexicon;

import vazkii.botania.api.lexicon.LexiconCategory;

public class LLexiconEntry extends WLexiconEntry{

	public LLexiconEntry(String unlocalizedName, LexiconCategory category){
		super(unlocalizedName, category);
		setKnowledgeType(LexiconData.lightningKnowledge);
	}
}