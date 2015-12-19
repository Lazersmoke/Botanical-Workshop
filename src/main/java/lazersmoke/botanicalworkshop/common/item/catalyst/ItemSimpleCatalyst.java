package lazersmoke.botanicalworkshop.common.item.catalyst;

import java.util.List;

import lazersmoke.botanicalworkshop.common.lexicon.LexiconData;
import lazersmoke.botanicalworkshop.common.lib.LibItemNames;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;

public class ItemSimpleCatalyst extends ItemCatalyst implements ILexiconable{

	public ItemSimpleCatalyst(){
		super(LibItemNames.SIMPLE_CATALYST);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, @SuppressWarnings("rawtypes") List loreLineList, boolean par4){
		for(int i = 0; i < 3; i++)
			loreLineList.add(StatCollector.translateToLocal("botanicalworkshopmisc.simpleCatalyst" + i));
	}

	@Override
	public LexiconEntry getEntry(World arg0, int arg1, int arg2, int arg3, EntityPlayer arg4, ItemStack arg5){
		return LexiconData.basicCatalyst;
	}
}