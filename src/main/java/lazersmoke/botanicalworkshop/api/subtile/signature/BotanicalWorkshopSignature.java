package lazersmoke.botanicalworkshop.api.subtile.signature;

import lazersmoke.botanicalworkshop.api.BotanicalWorkshopAPI;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import vazkii.botania.api.subtile.signature.SubTileSignature;

public class BotanicalWorkshopSignature extends SubTileSignature{
	final String name;

	public BotanicalWorkshopSignature(String name) {
		this.name = name;
	}

	@Override
	public void registerIcons(IIconRegister register) {
		BotanicalWorkshopAPI.internalHandler.registerBasicSignatureIcons(name, register);
	}

	@Override
	public IIcon getIconForStack(ItemStack stack) {
		return BotanicalWorkshopAPI.internalHandler.getSubTileIconForName(name);
	}

	@Override
	public String getUnlocalizedNameForStack(ItemStack stack) {
		return unlocalizedName("");
	}

	@Override
	public String getUnlocalizedLoreTextForStack(ItemStack stack) {
		return unlocalizedName(".reference");
	}

	public String getName() {
		return name;
	}

	private String unlocalizedName(String end) {
		return "tile.botania:" + SubTileSignature.SPECIAL_FLOWER_PREFIX + name + end;
	}
}