package lazersmoke.botanicalworkshop.common.item.equipment.tool.shifted;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import lazersmoke.botanicalworkshop.api.BotanicalWorkshopAPI;
import lazersmoke.botanicalworkshop.api.mana.IGatewayBindingItem;
import lazersmoke.botanicalworkshop.client.lib.LibResources;
import lazersmoke.botanicalworkshop.common.BotanicalWorkshop;
import lazersmoke.botanicalworkshop.common.block.tile.TileGatewayCore;
import lazersmoke.botanicalworkshop.common.lexicon.LexiconData;
import lazersmoke.botanicalworkshop.common.lib.LibConfigs;
import lazersmoke.botanicalworkshop.common.lib.LibItemNames;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.common.core.helper.ItemNBTHelper;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemShiftedWorldshapersStaff extends ItemTool implements IGatewayBindingItem, ILexiconable{

	public ItemShiftedWorldshapersStaff(){
		this(BotanicalWorkshopAPI.shiftedToolMaterial);
	}

	public ItemShiftedWorldshapersStaff(ToolMaterial toolMaterial){
		super(0, toolMaterial, new HashSet<Block>(Arrays.asList(new Block[0])));
		this.maxStackSize = 1;
		this.setMaxDamage(toolMaterial.getMaxUses());
		setCreativeTab(BotanicalWorkshop.creativeTab);
		setUnlocalizedName(LibItemNames.SHIFTED_WORLDSHAPERS_STAFF);
		GameRegistry.registerItem(this, LibItemNames.SHIFTED_WORLDSHAPERS_STAFF);
	}

	@Override
	public float getDigSpeed(ItemStack stack, Block block, int meta){
		return block != Blocks.bedrock ? efficiencyOnProperMaterial : 1.0F;
	}

	@Override
	public boolean canHarvestBlock(Block block, ItemStack stack){
		if(block == Blocks.obsidian)
			return toolMaterial.getHarvestLevel() == 3;

		if(block == Blocks.diamond_block || block == Blocks.diamond_ore)
			return toolMaterial.getHarvestLevel() >= 2;

		if(block == Blocks.gold_block || block == Blocks.gold_ore)
			return toolMaterial.getHarvestLevel() >= 2;

		if(block == Blocks.iron_block || block == Blocks.iron_ore)
			return toolMaterial.getHarvestLevel() >= 1;

		if(block == Blocks.lapis_block || block == Blocks.lapis_ore)
			return toolMaterial.getHarvestLevel() >= 1;

		if(block == Blocks.redstone_ore || block == Blocks.lit_redstone_ore)
			return toolMaterial.getHarvestLevel() >= 2;

		if(block == Blocks.anvil)
			return toolMaterial.getHarvestLevel() >= 0;

		if(block == Blocks.snow || block == Blocks.snow_layer)
			return true;

		if(block.getMaterial() == Material.rock)
			return true;

		return block.getMaterial() == Material.iron;
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity playerMaybe, int p_77663_4_, boolean p_77663_5_){
		cooldownTicks = Math.max(cooldownTicks - 1, 0);
		boolean isInHand = false;
		@SuppressWarnings("unchecked")
		final List<EntityPlayer> players = world.playerEntities;
		for(final EntityPlayer currentPlayer : players)
			if(currentPlayer.getHeldItem() == stack){
				isInHand = true;
				break;
			}
		if(isInHand)
			if(getCore(stack, world) != null)
				if(getCore(stack, world).getCurrentMana() >= LibConfigs.SHIFTED_REPAIR_COST && stack.getItemDamage() > 0){
					stack.setItemDamage(stack.getItemDamage() - 1);
					getCore(stack, world).recieveMana(-LibConfigs.SHIFTED_REPAIR_COST);
				}
	}

	private int mode = 0;// 0 means teleposer, 1 means flinger
	private int cooldownTicks = 0;

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player){
		if(cooldownTicks != 0){
			player.addChatMessage(new ChatComponentText("Cooldown is " + cooldownTicks));
			return stack;
		}
		if(world.isRemote)
			return stack;
		if(player.isSneaking()){
			mode = mode + 1 == 2 ? 0 : mode + 1;// Rotate mode
			player.addChatMessage(new ChatComponentText((mode == 0 ? "Teleposer " : "Gateway Seer ") + "Mode"));
		}else if(mode == 0 && cooldownTicks == 0 && getCore(stack, world) != null && getCore(stack, world).getCurrentMana() > LibConfigs.WORLDSHAPER_TELEPOSE_COST){
			getCore(stack, world).recieveMana(-LibConfigs.WORLDSHAPER_TELEPOSE_COST);
			cooldownTicks = 40;
			final MovingObjectPosition mop = getMovingObjectPositionFromPlayer(world, player, true);
			if(mop == null)
				return stack;
			if(mop.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK && world.getBlock(mop.blockX, mop.blockY, mop.blockZ) != Blocks.bedrock){
				final EntityItem itemDropped = new EntityItem(world, getCore(stack, world).xCoord, getCore(stack, world).yCoord + 2.0F, getCore(stack, world).zCoord, new ItemStack(world.getBlock(mop.blockX, mop.blockY, mop.blockZ).getItemDropped(world.getBlock(mop.blockX, mop.blockY, mop.blockZ).damageDropped(0), new Random(), 0)));
				world.spawnEntityInWorld(itemDropped);
				world.setBlockToAir(mop.blockX, mop.blockY, mop.blockZ);
				return stack;
			}
		}else if(mode == 1 && getCore(stack, world) != null)
			player.addChatMessage(new ChatComponentText("Gateway has " + getCore(stack, world).getCurrentMana() + " mana remaining."));
		return stack;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister register){
		final String resourceName = LibResources.PREFIX_MOD + this.getUnlocalizedName().replaceAll("item\\.", "");
		itemIcon = register.registerIcon(resourceName);
	}

	@Override
	public String getUnlocalizedNameInefficiently(ItemStack stack){
		return super.getUnlocalizedNameInefficiently(stack).replaceAll("item\\.", "item." + LibResources.PREFIX_MOD);
	}

	public static TileGatewayCore getCore(ItemStack stack, World worldObj){
		return (TileGatewayCore) worldObj.getTileEntity(ItemNBTHelper.getInt(stack, "boundGatewayX", 0), ItemNBTHelper.getInt(stack, "boundGatewayY", -1), ItemNBTHelper.getInt(stack, "boundGatewayZ", 0));
	}

	@Override
	public LexiconEntry getEntry(World arg0, int arg1, int arg2, int arg3, EntityPlayer arg4, ItemStack arg5){
		return LexiconData.shiftedHypervelocitySkewer;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, @SuppressWarnings("rawtypes") List loreLineList, boolean par4){
		if(ItemNBTHelper.getInt(stack, "boundGatewayY", -1) != -1)
			loreLineList.add("Gateway: [" + ItemNBTHelper.getInt(stack, "boundGatewayX", -1) + ", " + ItemNBTHelper.getInt(stack, "boundGatewayY", -1) + ", " + ItemNBTHelper.getInt(stack, "boundGatewayZ", -1) + "]");
	}
}
