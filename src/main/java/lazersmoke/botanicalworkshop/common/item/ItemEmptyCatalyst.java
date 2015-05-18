package lazersmoke.botanicalworkshop.common.item;

import java.util.ArrayList;

import lazersmoke.botanicalworkshop.common.block.tile.TileGatewayCore;
import lazersmoke.botanicalworkshop.common.lib.LibItemNames;
import net.minecraft.item.ItemStack;

public class ItemEmptyCatalyst extends ItemModCatalyst{
	public ItemEmptyCatalyst(){
		super();
		setUnlocalizedName(LibItemNames.EMPTY_CATALYST);
	}
	@Override
	public void onGatewayUpdate(TileGatewayCore gateway){
		boolean toKill = false;
		if(gateway.currentInventory.size() > 0)
			for(ItemStack currStack : gateway.currentInventory){
				toKill = true;
				gateway.summonItem(currStack);
			}
		if(toKill)
			gateway.currentInventory = new ArrayList<ItemStack>();
	}
}