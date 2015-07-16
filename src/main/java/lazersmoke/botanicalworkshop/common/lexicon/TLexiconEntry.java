package lazersmoke.botanicalworkshop.common.lexicon;

import vazkii.botania.api.lexicon.LexiconCategory;

public class TLexiconEntry extends WLexiconEntry{

	public TLexiconEntry(String unlocalizedName, LexiconCategory category){
		super(unlocalizedName, category);
		setKnowledgeType(LexiconData.thaumicKnowledge);
	}
}