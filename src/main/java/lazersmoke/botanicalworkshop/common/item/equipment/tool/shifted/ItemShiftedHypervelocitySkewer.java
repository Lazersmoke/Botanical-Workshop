package lazersmoke.botanicalworkshop.common.item.equipment.tool.shifted;

import java.util.List;

import lazersmoke.botanicalworkshop.api.BotanicalWorkshopAPI;
import lazersmoke.botanicalworkshop.api.mana.IGatewayBindingItem;
import lazersmoke.botanicalworkshop.client.lib.LibResources;
import lazersmoke.botanicalworkshop.common.BotanicalWorkshop;
import lazersmoke.botanicalworkshop.common.block.tile.TileGatewayCore;
import lazersmoke.botanicalworkshop.common.lexicon.LexiconData;
import lazersmoke.botanicalworkshop.common.lib.LibItemNames;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.common.core.helper.ItemNBTHelper;
import vazkii.botania.common.core.helper.Vector3;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemShiftedHypervelocitySkewer extends Item implements IGatewayBindingItem, ILexiconable{

	public ItemShiftedHypervelocitySkewer(){
		this(BotanicalWorkshopAPI.shiftedToolMaterial, LibItemNames.SHIFTED_HYPERVELOCITY_SKEWER);
	}

	public ItemShiftedHypervelocitySkewer(ToolMaterial mat, String name){
		this.maxStackSize = 1;
		this.setMaxDamage(mat.getMaxUses());
		setCreativeTab(BotanicalWorkshop.creativeTab);
		setUnlocalizedName(name);
		GameRegistry.registerItem(this, name);
	}

	public float func_150931_i(){// just in case
		return 0;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister register){
		final String resourceName = LibResources.PREFIX_MOD + this.getUnlocalizedName().replaceAll("item\\.", "");
		itemIcon = register.registerIcon(resourceName);
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity playerMaybe, int p_77663_4_, boolean p_77663_5_){
		boolean isInHand = false;
		holdingPlayerSpeed = 0;
		@SuppressWarnings("unchecked")
		final List<EntityPlayer> players = world.playerEntities;
		for(final EntityPlayer currentPlayer : players)
			if(currentPlayer.getHeldItem() == stack){
				isInHand = true;
				final EntityPlayer t = currentPlayer;
				holdingPlayerSpeed = Math.sqrt(t.motionX * t.motionX + t.motionY * t.motionY + t.motionZ * t.motionZ);// BPS
				holdingPlayerVec = new Vector3(t.motionX, t.motionY, t.motionZ);
			}
		if(isInHand)
			if(getCore(stack, world) != null)
				if(getCore(stack, world).getCurrentMana() >= 1000 && stack.getItemDamage() > 0){
					stack.setItemDamage(stack.getItemDamage() - 1);
					getCore(stack, world).recieveMana(-1000);
				}
	}

	public static TileGatewayCore getCore(ItemStack stack, World worldObj){
		return (TileGatewayCore) worldObj.getTileEntity(ItemNBTHelper.getInt(stack, "boundGatewayX", 0), ItemNBTHelper.getInt(stack, "boundGatewayY", -1), ItemNBTHelper.getInt(stack, "boundGatewayZ", 0));
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker){
		stack.damageItem(1, attacker);
		target.attackEntityFrom(new DamageSource("A fast skewerer"), getProperDamageIncrease());
		target.addVelocity(holdingPlayerVec.x, holdingPlayerVec.y, holdingPlayerVec.z);
		return true;
	}

	private Vector3 holdingPlayerVec = new Vector3(0, 0, 0);
	private double holdingPlayerSpeed = 0;// Blocks per second

	private float getProperDamageIncrease(){
		return (float) (holdingPlayerSpeed * 10.0D);
	}

	@Override
	public LexiconEntry getEntry(World arg0, int arg1, int arg2, int arg3, EntityPlayer arg4, ItemStack arg5){
		return LexiconData.shiftedHypervelocitySkewer;
	}
}
