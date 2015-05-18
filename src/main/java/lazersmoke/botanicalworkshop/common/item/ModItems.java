package lazersmoke.botanicalworkshop.common.item;

import net.minecraft.item.Item;

public final class ModItems {
	
	public static Item bindingCrystal;
	public static Item simpleCatalyst;
	public static Item emptyCatalyst;
	
	public static void init(){
		
		bindingCrystal = new ItemBindingCrystal();
		simpleCatalyst = new ItemSimpleCatalyst();
		emptyCatalyst = new ItemEmptyCatalyst();
		
	}
}
