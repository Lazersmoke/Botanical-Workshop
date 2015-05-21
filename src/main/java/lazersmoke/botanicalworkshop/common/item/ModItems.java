package lazersmoke.botanicalworkshop.common.item;

import lazersmoke.botanicalworkshop.common.item.catalyst.ItemEmptyCatalyst;
import lazersmoke.botanicalworkshop.common.item.catalyst.ItemManaCatalyst;
import lazersmoke.botanicalworkshop.common.item.catalyst.ItemSimpleCatalyst;
import lazersmoke.botanicalworkshop.common.item.catalyst.ItemThaumicCatalyst;
import net.minecraft.item.Item;

public final class ModItems {
	
	public static Item bindingCrystal;
	public static Item simpleCatalyst;
	public static Item emptyCatalyst;
	public static Item manaCatalyst;
	public static Item thaumicCatalyst;
	public static Item botanicalResource;
	public static Item thaumicResource;
	
	public static void init(){
		
		bindingCrystal = new ItemBindingCrystal();
		simpleCatalyst = new ItemSimpleCatalyst();
		emptyCatalyst = new ItemEmptyCatalyst();
		manaCatalyst = new ItemManaCatalyst();
		thaumicCatalyst = new ItemThaumicCatalyst();
		botanicalResource = new ItemBotanicalResource();
		thaumicResource = new ItemThaumicResource();
		
	}
}
