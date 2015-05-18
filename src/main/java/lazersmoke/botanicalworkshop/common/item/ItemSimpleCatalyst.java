package lazersmoke.botanicalworkshop.common.item;

import lazersmoke.botanicalworkshop.api.mana.IGatewayCatalyst;
import lazersmoke.botanicalworkshop.common.lib.LibItemNames;

public class ItemSimpleCatalyst extends ItemCatalyst implements IGatewayCatalyst{
	public ItemSimpleCatalyst(){
		super();
		setUnlocalizedName(LibItemNames.SIMPLE_CATALYST);
	}
}