package lazersmoke.botanical.item.block;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import vazkii.botania.common.item.block.ItemBlockWithMetadataAndName;

public class ItemBlockElvenPool extends ItemBlockWithMetadataAndName{

	public ItemBlockElvenPool(Block par2Block) {
		super(par2Block);
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List loreLineList, boolean par4){
		for(int i = 0; i < 2; i++)
			loreLineList.add(StatCollector.translateToLocal("botanicalmisc.elvenPool" + i));
	}
	
}