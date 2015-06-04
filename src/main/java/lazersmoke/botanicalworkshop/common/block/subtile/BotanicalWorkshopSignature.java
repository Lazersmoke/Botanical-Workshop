package lazersmoke.botanicalworkshop.common.block.subtile;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import lazersmoke.botanicalworkshop.client.lib.LibResources;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.subtile.signature.SubTileSignature;
import vazkii.botania.common.block.BlockSpecialFlower;

public class BotanicalWorkshopSignature extends SubTileSignature{

	final String name;

	public BotanicalWorkshopSignature(String name){
		this.name = name;
	}

	@Override
	public void registerIcons(IIconRegister register){
		IIcon normal = register.registerIcon(LibResources.PREFIX_SUBTILE
				+ name.replaceAll("tile\\.", "").replaceAll("subtile", ""));
		BlockSpecialFlower.icons.put(name, normal);
	}

	@Override
	public IIcon getIconForStack(ItemStack stack){
		return BotaniaAPI.internalHandler.getSubTileIconForName(name);
	}

	@Override
	public String getUnlocalizedNameForStack(ItemStack stack){
		return unlocalizedName("");
	}

	@Override
	public String getUnlocalizedLoreTextForStack(ItemStack stack){
		return unlocalizedName(".reference");
	}

	public String getName(){
		return name;
	}

	private String unlocalizedName(String end){
		return "tile.botanicalworkshop:"
				+ SubTileSignature.SPECIAL_FLOWER_PREFIX + name + end;
	}
}