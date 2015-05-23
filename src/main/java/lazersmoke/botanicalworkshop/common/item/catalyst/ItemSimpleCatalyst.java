package lazersmoke.botanicalworkshop.common.item.catalyst;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import lazersmoke.botanicalworkshop.common.lib.LibItemNames;

public class ItemSimpleCatalyst extends ItemCatalyst{
	public ItemSimpleCatalyst(){
		super(LibItemNames.SIMPLE_CATALYST);
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List loreLineList, boolean par4){
		for(int i = 0; i < 3; i++)
			loreLineList.add(StatCollector.translateToLocal("botanicalworkshopmisc.simpleCatalyst" + i));
	}
}