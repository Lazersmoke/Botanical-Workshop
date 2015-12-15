package lazersmoke.botanicalworkshop.common.item.equipment.armor.shifted;

import java.util.List;

import lazersmoke.botanicalworkshop.api.BotanicalWorkshopAPI;
import lazersmoke.botanicalworkshop.api.mana.IGatewayBindingItem;
import lazersmoke.botanicalworkshop.client.lib.LibResources;
import lazersmoke.botanicalworkshop.common.BotanicalWorkshop;
import lazersmoke.botanicalworkshop.common.block.tile.TileGatewayCore;
import lazersmoke.botanicalworkshop.common.crafting.recipe.ShiftedArmorUpgradeRecipe;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;
import vazkii.botania.api.item.IPhantomInkable;
import vazkii.botania.common.core.helper.ItemNBTHelper;
import vazkii.botania.common.item.ItemTemperanceStone;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemShiftedArmor extends ItemArmor implements ISpecialArmor, IPhantomInkable, IGatewayBindingItem{

	public static int dmgRdc;

	public ItemShiftedArmor(int type, String name){
		this(type, name, BotanicalWorkshopAPI.shiftedArmorMaterial);
	}

	public ItemShiftedArmor(int type, String name, ArmorMaterial mat){
		super(mat, 0, type);
		setCreativeTab(BotanicalWorkshop.creativeTab);
		this.setUnlocalizedName(name);
		GameRegistry.addRecipe(new ShiftedArmorUpgradeRecipe());
	}

	@Override
	public Item setUnlocalizedName(String par1Str){
		GameRegistry.registerItem(this, par1Str);
		return super.setUnlocalizedName(par1Str);
	}

	@Override
	public String getUnlocalizedNameInefficiently(ItemStack par1ItemStack){
		return super.getUnlocalizedNameInefficiently(par1ItemStack).replaceAll("item.", "item." + LibResources.PREFIX_MOD);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister){
		itemIcon = par1IconRegister.registerIcon(LibResources.PREFIX_MOD + getUnlocalizedName().replaceAll("item\\.", ""));
	}

	@Override
	public final String getArmorTexture(ItemStack stack, Entity entity, int slot, String type){
		return hasPhantomInk(stack) ? vazkii.botania.client.lib.LibResources.MODEL_INVISIBLE_ARMOR : getArmorTextureAfterInk(stack, slot);
	}

	public String getArmorTextureAfterInk(ItemStack stack, int slot){
		return slot == 2 ? LibResources.MODEL_SHIFTED_1 : LibResources.MODEL_SHIFTED_0;
	}

	private static final String TAG_PHANTOM_INK = "phantomInk";
	public static final String TAG_UPGRADE_BASE = "shiftedUpgrade";

	@Override
	public boolean hasPhantomInk(ItemStack stack){
		return ItemNBTHelper.getBoolean(stack, TAG_PHANTOM_INK, false);
	}

	@Override
	public void setPhantomInk(ItemStack stack, boolean ink){
		ItemNBTHelper.setBoolean(stack, TAG_PHANTOM_INK, true);
	}

	@Override
	public ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage, int slot){
		if(source.isUnblockable())
			return new ArmorProperties(0, 0, 0);
		return new ArmorProperties(0, damageReduceAmount / 25D, armor.getMaxDamage() + 1 - armor.getItemDamage());
	}

	@Override
	public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot){
		return damageReduceAmount;
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity player, int par4, boolean par5){
		if(player instanceof EntityPlayer)
			onArmorTick(world, (EntityPlayer) player, stack);
	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack stack){
		boolean isInArmorSlot = false;
		for(final ItemStack testStack : player.inventory.armorInventory)
			if(testStack != null && testStack.equals(stack))
				isInArmorSlot = true;
		if(isInArmorSlot){
			for(final String key : BotanicalWorkshopAPI.shiftedUpgrades.keySet())
				if(ItemNBTHelper.getBoolean(stack, TAG_UPGRADE_BASE + key, false) && !ItemTemperanceStone.hasTemperanceActive(player))
					BotanicalWorkshopAPI.shiftedUpgrades.get(key).onArmorTick(world, player, stack);

			if(getCore(stack, world) != null)
				if(getCore(stack, world).getCurrentMana() >= 1000 && stack.getItemDamage() > 0){
					stack.setItemDamage(stack.getItemDamage() - 1);
					getCore(stack, world).recieveMana(-1000);
				}
		}
	}

	@Override
	public void damageArmor(EntityLivingBase entity, ItemStack stack, DamageSource source, int damage, int slot){
		stack.damageItem(damage, entity);
	}

	public static TileGatewayCore getCore(ItemStack stack, World worldObj){
		return (TileGatewayCore) worldObj.getTileEntity(ItemNBTHelper.getInt(stack, "boundGatewayX", 0), ItemNBTHelper.getInt(stack, "boundGatewayY", -1), ItemNBTHelper.getInt(stack, "boundGatewayZ", 0));
	}

	@SuppressWarnings("unchecked")
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, @SuppressWarnings("rawtypes") List loreLineList, boolean par4){
		if(ItemTemperanceStone.hasTemperanceActive(player))
			loreLineList.add("Temperance Stone Active!");

		if(ItemNBTHelper.getInt(stack, "boundGatewayY", -1) != -1)
			loreLineList.add("Gateway: [" + ItemNBTHelper.getInt(stack, "boundGatewayX", -1) + ", " + ItemNBTHelper.getInt(stack, "boundGatewayY", -1) + ", " + ItemNBTHelper.getInt(stack, "boundGatewayZ", -1) + "]");

		for(final String key : BotanicalWorkshopAPI.shiftedUpgrades.keySet())
			if(ItemNBTHelper.getBoolean(stack, TAG_UPGRADE_BASE + key, false))
				loreLineList.add(BotanicalWorkshopAPI.shiftedUpgrades.get(key).getDisplayName());
	}
}