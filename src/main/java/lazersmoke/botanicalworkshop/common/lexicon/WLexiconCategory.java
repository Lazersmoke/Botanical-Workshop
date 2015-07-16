package lazersmoke.botanicalworkshop.common.lexicon;

import net.minecraft.util.ResourceLocation;
import vazkii.botania.api.lexicon.LexiconCategory;

public class WLexiconCategory extends LexiconCategory{

	public WLexiconCategory(String unlocalizedName, int priority){
		super(unlocalizedName);
		setIcon(new ResourceLocation(unlocalizedName + ".png"));
		setPriority(priority);
	}
}