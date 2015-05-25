package lazersmoke.botanicalworkshop.common.item;

import lazersmoke.botanicalworkshop.common.item.catalyst.ItemEmptyCatalyst;
import lazersmoke.botanicalworkshop.common.item.catalyst.ItemManaTransferCatalyst;
import lazersmoke.botanicalworkshop.common.item.catalyst.ItemSimpleCatalyst;
import lazersmoke.botanicalworkshop.common.item.catalyst.ItemThaumicCatalyst;
import lazersmoke.botanicalworkshop.common.item.equipment.armor.shifted.ItemShiftedBoots;
import lazersmoke.botanicalworkshop.common.item.equipment.armor.shifted.ItemShiftedChestplate;
import lazersmoke.botanicalworkshop.common.item.equipment.armor.shifted.ItemShiftedHelmet;
import lazersmoke.botanicalworkshop.common.item.equipment.armor.shifted.ItemShiftedLeggings;
import net.minecraft.item.Item;

public final class ModItems {
	
	public static Item bindingCrystal;
	public static Item simpleCatalyst;
	public static Item emptyCatalyst;
	public static Item manaCatalyst;
	public static Item shiftedHelmet;
	public static Item shiftedChestplate;
	public static Item shiftedLeggings;
	public static Item shiftedBoots;
	public static Item thaumicCatalyst;
	public static Item botanicalResource;
	public static Item thaumicResource;
	
	public static void init(){
		
		bindingCrystal = new ItemBindingCrystal();
		simpleCatalyst = new ItemSimpleCatalyst();
		emptyCatalyst = new ItemEmptyCatalyst();
		manaCatalyst = new ItemManaTransferCatalyst();
		shiftedHelmet = new ItemShiftedHelmet();
		shiftedChestplate = new ItemShiftedChestplate();
		shiftedLeggings = new ItemShiftedLeggings();
		shiftedBoots = new ItemShiftedBoots();
		thaumicCatalyst = new ItemThaumicCatalyst();
		botanicalResource = new ItemBotanicalResource();
		thaumicResource = new ItemThaumicResource();
		
	}
}
