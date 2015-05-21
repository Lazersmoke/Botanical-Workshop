package lazersmoke.botanicalworkshop.common.lexicon;

import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.lexicon.ITwoNamedPage;
import vazkii.botania.api.lexicon.LexiconCategory;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.lexicon.LexiconPage;

public class WLexiconEntry extends LexiconEntry {

	public WLexiconEntry(String unlocalizedName, LexiconCategory category) {
		super(unlocalizedName, category);
		BotaniaAPI.addEntry(this, category);
	}

	@Override
	public LexiconEntry setLexiconPages(LexiconPage... pages) {
		for(LexiconPage page : pages) {
			page.unlocalizedName = "botanicalworkshop.page." + getLazyUnlocalizedName() + page.unlocalizedName;
			if(page instanceof ITwoNamedPage) {
				ITwoNamedPage dou = (ITwoNamedPage) page;
				dou.setSecondUnlocalizedName("botanicalworkshop.page." + getLazyUnlocalizedName() + dou.getSecondUnlocalizedName());
			}
		}

		return super.setLexiconPages(pages);
	}

	@Override
	public String getUnlocalizedName() {
		return "botanicalworkshop.entry." + super.getUnlocalizedName();
	}

	public String getLazyUnlocalizedName() {
		return super.getUnlocalizedName();
	}

	@Override
	public String getWebLink() {
		return null;//TODO Github Wiki!
	}

}