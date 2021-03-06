package lazersmoke.botanicalworkshop.common.item.block;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

public class ItemBlockGatewayCore extends ItemBlockWithMetadataAndName{

	public ItemBlockGatewayCore(Block par2Block){
		super(par2Block);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, @SuppressWarnings("rawtypes") List loreLineList, boolean par4){
		for(int i = 0; i < 3; i++)
			loreLineList.add(StatCollector.translateToLocal("botanicalworkshopmisc.gatewayCore" + i));
	}

}