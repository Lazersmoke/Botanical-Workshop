package lazersmoke.botanicalworkshop.common.item.block;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

public class ItemBlockThaumicCore extends ItemBlockWithMetadataAndName{

	public ItemBlockThaumicCore(Block block){
		super(block);
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer player,
	        List loreLineList, boolean par4){
		for(int i = 0; i < 3; i++)
			loreLineList.add(StatCollector
			        .translateToLocal("botanicalworkshopmisc.thaumicCore" + i));
	}

}