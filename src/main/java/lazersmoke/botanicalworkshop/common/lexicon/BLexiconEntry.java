package lazersmoke.botanicalworkshop.common.lexicon;

import vazkii.botania.api.lexicon.LexiconCategory;

public class BLexiconEntry extends WLexiconEntry{

	public BLexiconEntry(String unlocalizedName, LexiconCategory category){
		super(unlocalizedName, category);
		setKnowledgeType(LexiconData.bloodKnowledge);
	}
}