package lazersmoke.botanicalworkshop.common.item;

import lazersmoke.botanicalworkshop.api.mana.ISuperGatewayCatalyst;
import lazersmoke.botanicalworkshop.common.lib.LibItemNames;

public class ItemSimpleCatalyst extends ModCatalyst implements ISuperGatewayCatalyst{
	public ItemSimpleCatalyst(){
		super();
		setUnlocalizedName(LibItemNames.SIMPLE_CATALYST);
	}
}