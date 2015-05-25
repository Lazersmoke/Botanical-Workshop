package lazersmoke.botanicalworkshop.common.item.equipment.armor.shifted;

import java.util.UUID;

import lazersmoke.botanicalworkshop.api.BotanicalWorkshopAPI;
import lazersmoke.botanicalworkshop.client.lib.LibResources;
import lazersmoke.botanicalworkshop.common.BotanicalWorkshop;
import lazersmoke.botanicalworkshop.common.block.tile.TileGatewayCore;
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
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemShiftedArmor extends ItemArmor implements ISpecialArmor, IPhantomInkable{
	
	//Binds armor uuids to catalyst ItemStacks
	public static int dmgRdc;	
	public ItemShiftedArmor(int type, String name) {
		this(type, name, BotanicalWorkshopAPI.shiftedArmorMaterial);
	}
	
	public ItemShiftedArmor(int type, String name, ArmorMaterial mat) {
		super(mat, 0, type);
		setCreativeTab(BotanicalWorkshop.creativeTab);
		this.setUnlocalizedName(name);
	}
	
	@Override
	public Item setUnlocalizedName(String par1Str) {
		GameRegistry.registerItem(this, par1Str);
		return super.setUnlocalizedName(par1Str);
	}

	@Override
	public String getUnlocalizedNameInefficiently(ItemStack par1ItemStack) {
		return super.getUnlocalizedNameInefficiently(par1ItemStack).replaceAll("item.", "item." + LibResources.PREFIX_MOD);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister) {
		itemIcon = par1IconRegister.registerIcon(LibResources.PREFIX_MOD + getUnlocalizedName().replaceAll("item\\.", ""));
	}
	
	private static final String TAG_PHANTOM_INK = "phantomInk";
	private static final String TAG_BOUND_UUID = "boundGatewayUUID";
	
	@Override
	public boolean hasPhantomInk(ItemStack stack) {
		return ItemNBTHelper.getBoolean(stack, TAG_PHANTOM_INK, false);
	}

	@Override
	public void setPhantomInk(ItemStack stack, boolean ink) {
		ItemNBTHelper.setBoolean(stack, TAG_PHANTOM_INK, true);
	}
	
	@Override
	public ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage, int slot) {
		if(source.isUnblockable())
			return new ArmorProperties(0, 0, 0);
		return new ArmorProperties(0, damageReduceAmount / 25D, armor.getMaxDamage() + 1 - armor.getItemDamage());
	}

	@Override
	public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot) {
		return damageReduceAmount;
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity player, int par4, boolean par5) {
		if(player instanceof EntityPlayer)
			onArmorTick(world, (EntityPlayer) player, stack);
	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
		TileGatewayCore.UUIDMap.get(getUUID(stack)).addMana(-1000);
	}

	@Override
	public void damageArmor(EntityLivingBase entity, ItemStack stack, DamageSource source, int damage, int slot) {
		TileGatewayCore.UUIDMap.get(getUUID(stack)).addMana(-1000 * damage);
	}
	
	public static void setUUID(ItemStack stack, UUID uuid){
		ItemNBTHelper.setString(stack, TAG_BOUND_UUID, uuid.toString());
	}
	
	public static UUID getUUID(ItemStack stack){
		return UUID.fromString(ItemNBTHelper.getString(stack, TAG_BOUND_UUID, ""));
	}
}