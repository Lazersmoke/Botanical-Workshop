package lazersmoke.botanicalworkshop.common.item;

import lazersmoke.botanicalworkshop.api.BotanicalWorkshopAPI;
import lazersmoke.botanicalworkshop.api.shifted.IShiftedArmorUpgrade;
import lazersmoke.botanicalworkshop.common.item.catalyst.ItemEmptyCatalyst;
import lazersmoke.botanicalworkshop.common.item.catalyst.ItemTransferCatalyst;
import lazersmoke.botanicalworkshop.common.item.catalyst.ItemSimpleCatalyst;
import lazersmoke.botanicalworkshop.common.item.catalyst.ItemThaumicCatalyst;
import lazersmoke.botanicalworkshop.common.item.equipment.armor.shifted.ItemShiftedBoots;
import lazersmoke.botanicalworkshop.common.item.equipment.armor.shifted.ItemShiftedChestplate;
import lazersmoke.botanicalworkshop.common.item.equipment.armor.shifted.ItemShiftedHelmet;
import lazersmoke.botanicalworkshop.common.item.equipment.armor.shifted.ItemShiftedLeggings;
import lazersmoke.botanicalworkshop.common.item.shifted.ItemShiftedHopsUpgrade;
import lazersmoke.botanicalworkshop.common.item.shifted.ItemShiftedMatter;
import lazersmoke.botanicalworkshop.common.item.shifted.ItemShiftedPhaseUpgrade;
import lazersmoke.botanicalworkshop.common.item.shifted.ItemShiftedRevealingUpgrade;
import net.minecraft.item.Item;

public final class ModItems{

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
	public static Item shiftedPhaseUpgrade;
	public static Item shiftedRevealingUpgrade;
	public static Item shiftedHopsUpgrade;
	public static Item shiftedMatter;

	public static void init(){

		bindingCrystal = new ItemBindingCrystal();
		simpleCatalyst = new ItemSimpleCatalyst();
		emptyCatalyst = new ItemEmptyCatalyst();
		manaCatalyst = new ItemTransferCatalyst();
		shiftedHelmet = new ItemShiftedHelmet();
		shiftedChestplate = new ItemShiftedChestplate();
		shiftedLeggings = new ItemShiftedLeggings();
		shiftedBoots = new ItemShiftedBoots();
		thaumicCatalyst = new ItemThaumicCatalyst();
		botanicalResource = new ItemBotanicalResource();
		thaumicResource = new ItemThaumicResource();
		shiftedPhaseUpgrade = new ItemShiftedPhaseUpgrade();
		shiftedRevealingUpgrade = new ItemShiftedRevealingUpgrade();
		shiftedHopsUpgrade = new ItemShiftedHopsUpgrade();
		shiftedMatter = new ItemShiftedMatter();

		BotanicalWorkshopAPI.registerShiftedArmorUpgrade((IShiftedArmorUpgrade) shiftedPhaseUpgrade);
		BotanicalWorkshopAPI.registerShiftedArmorUpgrade((IShiftedArmorUpgrade) shiftedRevealingUpgrade);
		BotanicalWorkshopAPI.registerShiftedArmorUpgrade((IShiftedArmorUpgrade) shiftedHopsUpgrade);
	}
}
