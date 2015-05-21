package lazersmoke.botanicalworkshop.api.subtile.signature;

import net.minecraft.item.ItemStack;
import vazkii.botania.api.subtile.signature.BasicSignature;
import vazkii.botania.api.subtile.signature.SubTileSignature;

public class BotanicalWorkshopSignature extends BasicSignature{

	final String name;
	
	public BotanicalWorkshopSignature(String name) {
		super(name);
		this.name = name;
	}
	
	@Override
	public String getUnlocalizedNameForStack(ItemStack stack) {
		return unlocalizedName("");
	}

	@Override
	public String getUnlocalizedLoreTextForStack(ItemStack stack) {
		return unlocalizedName(".reference");
	}
	
	private String unlocalizedName(String end) {
		return "tile.botanicalworkshop:" + SubTileSignature.SPECIAL_FLOWER_PREFIX + name + end;
	}
}