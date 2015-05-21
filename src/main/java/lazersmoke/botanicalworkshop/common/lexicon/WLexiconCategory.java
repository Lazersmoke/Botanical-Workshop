package lazersmoke.botanicalworkshop.common.lexicon;

import net.minecraft.util.ResourceLocation;
import lazersmoke.botanicalworkshop.client.lib.LibResources;
import lazersmoke.botanicalworkshop.common.lib.LibLexicon;
import vazkii.botania.api.lexicon.LexiconCategory;

public class WLexiconCategory extends LexiconCategory{
	public WLexiconCategory(String unlocalizedName, int priority) {
		super(LibLexicon.CATEGORY_PREFIX + unlocalizedName);
		setIcon(new ResourceLocation(LibResources.PREFIX_CATEGORIES + unlocalizedName + ".png"));
		setPriority(priority);
	}
}