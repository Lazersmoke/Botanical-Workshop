package lazersmoke.botanicalworkshop.common.item;

import lazersmoke.botanicalworkshop.api.BotanicalWorkshopAPI;
import lazersmoke.botanicalworkshop.api.shifted.IShiftedArmorUpgrade;
import lazersmoke.botanicalworkshop.common.item.catalyst.ItemCraftingAutomationCatalyst;
import lazersmoke.botanicalworkshop.common.item.catalyst.ItemEmptyCatalyst;
import lazersmoke.botanicalworkshop.common.item.catalyst.ItemLightningConsumingCatalyst;
import lazersmoke.botanicalworkshop.common.item.catalyst.ItemLightningGenerationCatalyst;
import lazersmoke.botanicalworkshop.common.item.catalyst.ItemSimpleCatalyst;
import lazersmoke.botanicalworkshop.common.item.catalyst.ItemThaumicCatalyst;
import lazersmoke.botanicalworkshop.common.item.equipment.armor.shifted.ItemShiftedBoots;
import lazersmoke.botanicalworkshop.common.item.equipment.armor.shifted.ItemShiftedChestplate;
import lazersmoke.botanicalworkshop.common.item.equipment.armor.shifted.ItemShiftedHelmet;
import lazersmoke.botanicalworkshop.common.item.equipment.armor.shifted.ItemShiftedLeggings;
import lazersmoke.botanicalworkshop.common.item.equipment.tool.shifted.ItemShiftedHypervelocitySkewer;
import lazersmoke.botanicalworkshop.common.item.equipment.tool.shifted.ItemShiftedWorldshapersStaff;
import lazersmoke.botanicalworkshop.common.item.shifted.ItemShiftedFireImmunityUpgrade;
import lazersmoke.botanicalworkshop.common.item.shifted.ItemShiftedHopsUpgrade;
import lazersmoke.botanicalworkshop.common.item.shifted.ItemShiftedMatter;
import lazersmoke.botanicalworkshop.common.item.shifted.ItemShiftedPhaseUpgrade;
import lazersmoke.botanicalworkshop.common.item.shifted.ItemShiftedRevealingUpgrade;
import net.minecraft.item.Item;

public final class ModItems{

	public static Item bindingCrystal;
	public static Item simpleCatalyst;
	public static Item emptyCatalyst;
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
	public static Item shiftedFireImmunityUpgrade;
	public static Item shiftedMatter;
	public static Item voltmeter;
	public static Item lightningGenerationCatalyst;
	public static Item lightningConsumingCatalyst;
	public static Item craftingAutomationCatalyst;
	public static Item shiftedHypervelocitySkewer;
	public static Item shiftedWorldshapersStaff;
	public static Item lightningCell;

	public static void init(){

		bindingCrystal = new ItemBindingCrystal();
		simpleCatalyst = new ItemSimpleCatalyst();
		emptyCatalyst = new ItemEmptyCatalyst();
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
		shiftedFireImmunityUpgrade = new ItemShiftedFireImmunityUpgrade();
		shiftedMatter = new ItemShiftedMatter();
		voltmeter = new ItemVoltmeter();
		lightningGenerationCatalyst = new ItemLightningGenerationCatalyst();
		lightningConsumingCatalyst = new ItemLightningConsumingCatalyst();
		craftingAutomationCatalyst = new ItemCraftingAutomationCatalyst();
		shiftedHypervelocitySkewer = new ItemShiftedHypervelocitySkewer();
		shiftedWorldshapersStaff = new ItemShiftedWorldshapersStaff();
		lightningCell = new ItemLightningCell();

		BotanicalWorkshopAPI.registerShiftedArmorUpgrade((IShiftedArmorUpgrade) shiftedPhaseUpgrade);
		BotanicalWorkshopAPI.registerShiftedArmorUpgrade((IShiftedArmorUpgrade) shiftedRevealingUpgrade);
		BotanicalWorkshopAPI.registerShiftedArmorUpgrade((IShiftedArmorUpgrade) shiftedHopsUpgrade);
		BotanicalWorkshopAPI.registerShiftedArmorUpgrade((IShiftedArmorUpgrade) shiftedFireImmunityUpgrade);
	}
}
