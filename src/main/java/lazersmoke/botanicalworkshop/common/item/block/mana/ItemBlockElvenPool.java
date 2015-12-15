package lazersmoke.botanicalworkshop.common.item.block.mana;

import java.util.List;

import lazersmoke.botanicalworkshop.common.item.block.ItemBlockWithMetadataAndName;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

public class ItemBlockElvenPool extends ItemBlockWithMetadataAndName{

	public ItemBlockElvenPool(Block par2Block){
		super(par2Block);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, @SuppressWarnings("rawtypes") List loreLineList, boolean par4){
		for(int i = 0; i < 1; i++)
			loreLineList.add(StatCollector.translateToLocal("botanicalworkshopmisc.elvenPool" + i));
	}

}