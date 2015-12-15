package lazersmoke.botanicalworkshop.common.item.catalyst;

import java.util.List;

import lazersmoke.botanicalworkshop.common.lib.LibItemNames;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

public class ItemSimpleCatalyst extends ItemCatalyst{

	public ItemSimpleCatalyst(){
		super(LibItemNames.SIMPLE_CATALYST);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, @SuppressWarnings("rawtypes") List loreLineList, boolean par4){
		for(int i = 0; i < 3; i++)
			loreLineList.add(StatCollector.translateToLocal("botanicalworkshopmisc.simpleCatalyst" + i));
	}
}