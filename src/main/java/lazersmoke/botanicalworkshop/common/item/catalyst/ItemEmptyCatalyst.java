package lazersmoke.botanicalworkshop.common.item.catalyst;

import java.util.ArrayList;
import java.util.List;

import lazersmoke.botanicalworkshop.common.block.tile.TileGatewayCore;
import lazersmoke.botanicalworkshop.common.lib.LibItemNames;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

public class ItemEmptyCatalyst extends ItemModCatalyst{
	
	public ItemEmptyCatalyst(){
		super();
		setUnlocalizedName(LibItemNames.EMPTY_CATALYST);
	}
	
	@Override
	public void onGatewayUpdate(TileGatewayCore gateway, EntityItem catalyst){
		boolean toKill = false;
		if(gateway.currentInventory.size() > 0)
			for(ItemStack currStack : gateway.currentInventory){
				toKill = true;
				gateway.summonItem(currStack);
			}
		if(toKill)
			gateway.currentInventory = new ArrayList<ItemStack>();
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List loreLineList, boolean par4){
		for(int i = 0; i < 2; i++)
			loreLineList.add(StatCollector.translateToLocal("botanicalworkshopmisc.emptyCatalyst" + i));
	}
}