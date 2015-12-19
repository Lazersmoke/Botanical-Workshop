package lazersmoke.botanicalworkshop.common.item.catalyst;

import java.util.ArrayList;
import java.util.List;

import lazersmoke.botanicalworkshop.common.block.tile.TileGatewayCore;
import lazersmoke.botanicalworkshop.common.lexicon.LexiconData;
import lazersmoke.botanicalworkshop.common.lib.LibItemNames;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;

public class ItemEmptyCatalyst extends ItemActiveCatalyst implements ILexiconable{

	public ItemEmptyCatalyst(){
		super(LibItemNames.EMPTY_CATALYST);
	}

	@Override
	public void onGatewayUpdate(TileGatewayCore gateway, EntityItem catalyst){
		boolean toKill = false;
		if(gateway.currentInventory.size() > 0)
			for(final ItemStack currStack : gateway.currentInventory){
				toKill = true;
				gateway.summonItem(currStack);
			}
		if(toKill)
			gateway.currentInventory = new ArrayList<ItemStack>();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, @SuppressWarnings("rawtypes") List loreLineList, boolean par4){
		for(int i = 0; i < 3; i++)
			loreLineList.add(StatCollector.translateToLocal("botanicalworkshopmisc.emptyCatalyst" + i));
	}

	@Override
	public LexiconEntry getEntry(World arg0, int arg1, int arg2, int arg3, EntityPlayer arg4, ItemStack arg5){
		return LexiconData.basicCatalyst;
	}
}