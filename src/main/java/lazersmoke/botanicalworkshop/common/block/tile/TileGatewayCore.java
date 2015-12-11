package lazersmoke.botanicalworkshop.common.block.tile;

// This class is %50 Lazersmoke
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import lazersmoke.botanicalworkshop.api.BotanicalWorkshopAPI;
import lazersmoke.botanicalworkshop.api.mana.IGatewayBindingItem;
import lazersmoke.botanicalworkshop.api.mana.IGatewayCatalyst;
import lazersmoke.botanicalworkshop.api.mana.IGatewayMod;
import lazersmoke.botanicalworkshop.api.recipe.RecipeGatewayTransmutation;
import lazersmoke.botanicalworkshop.client.lib.LibResources;
import lazersmoke.botanicalworkshop.common.BotanicalWorkshop;
import lazersmoke.botanicalworkshop.common.block.ModBlocks;
import lazersmoke.botanicalworkshop.common.block.tile.mana.TileElvenPool;
import lazersmoke.botanicalworkshop.common.item.ModItems;
import lazersmoke.botanicalworkshop.common.lexicon.LexiconData;
import lazersmoke.botanicalworkshop.common.lib.LibConfigs;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.StatCollector;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.logging.log4j.Level;
import org.lwjgl.opengl.GL11;

import vazkii.botania.api.lexicon.ILexicon;
import vazkii.botania.api.mana.spark.ISparkAttachable;
import vazkii.botania.api.mana.spark.ISparkEntity;
import vazkii.botania.client.core.handler.HUDHandler;
import vazkii.botania.common.Botania;
import vazkii.botania.common.core.helper.ItemNBTHelper;
public class TileGatewayCore extends TileMod implements ISparkAttachable/* implements IManaReceiver */{
	//   W W
	//  W   W
	// WS   SW
	// G     G
	// WS   SW
	//  W   W
	//   WCW
	public List<ItemStack> currentInventory = new ArrayList<ItemStack>();
	public boolean open = false;
	public UUID uuid;
	public static HashMap<UUID, TileGatewayCore> UUIDMap = new HashMap<UUID, TileGatewayCore>();

	public TileGatewayCore() {
		super();
		UUID freshUuid = UUID.randomUUID();
		UUIDMap.put(freshUuid, this);
		this.uuid = freshUuid;
		if(BotanicalWorkshop.bloodMagicLoaded){
			acceptableAlternativeMaterialsQuartz.add(WayofTime.alchemicalWizardry.ModBlocks.bloodRune);
			acceptableAlternativeMaterialsLivingwood.add(WayofTime.alchemicalWizardry.ModBlocks.bloodRune);
		}
	}

	private static final String TAG_PORTAL_KEEP = "gatewayKeep";
	private static final int OPENING_MANA_COST = LibConfigs.GATEWAY_OPENING_MANA_COST;
	private static final int COST_PER_TICK = LibConfigs.GATEWAY_TICK_MANA_COST;
	private boolean hasUnloadedParts = false;
	private boolean additionalPools = false;
	private int ticksOpen = 0;
	private boolean closeNow = false;
	//@formatter:off
	private static List<Block> acceptableAlternativeMaterialsQuartz = new ArrayList<Block>(Arrays.asList(
			vazkii.botania.common.block.ModFluffBlocks.elfQuartz,
			vazkii.botania.common.block.ModFluffBlocks.elfQuartzSlab,
			vazkii.botania.common.block.ModFluffBlocks.elfQuartzStairs,
			vazkii.botania.common.block.ModFluffBlocks.manaQuartz,
			vazkii.botania.common.block.ModFluffBlocks.manaQuartzSlab,
			vazkii.botania.common.block.ModFluffBlocks.manaQuartzStairs
			));
	private static List<Block> acceptableAlternativeMaterialsLivingwood = new ArrayList<Block>(Arrays.asList(
			vazkii.botania.common.block.ModBlocks.livingwood,
			vazkii.botania.common.block.ModFluffBlocks.livingwoodPlankSlab,
			vazkii.botania.common.block.ModFluffBlocks.livingwoodPlankStairs,
			vazkii.botania.common.block.ModFluffBlocks.livingwoodSlab,
			vazkii.botania.common.block.ModFluffBlocks.livingwoodStairs,
			vazkii.botania.common.block.ModBlocks.dreamwood,
			vazkii.botania.common.block.ModFluffBlocks.dreamwoodPlankSlab,
			vazkii.botania.common.block.ModFluffBlocks.dreamwoodPlankStairs,
			vazkii.botania.common.block.ModFluffBlocks.dreamwoodSlab,
			vazkii.botania.common.block.ModFluffBlocks.dreamwoodStairs
			));
	private static List<Block> acceptableAlternativeMaterialsLivingwoodStairs = new ArrayList<Block>(Arrays.asList(
			vazkii.botania.common.block.ModFluffBlocks.livingwoodStairs,
			vazkii.botania.common.block.ModFluffBlocks.livingwoodPlankStairs,
			vazkii.botania.common.block.ModFluffBlocks.dreamwoodStairs,
			vazkii.botania.common.block.ModFluffBlocks.dreamwoodPlankStairs
			));
	private static List<Block> acceptableAlternativeMaterialsGlimmeringLivingwood = new ArrayList<Block>(Arrays.asList(
			vazkii.botania.common.block.ModBlocks.livingwood,
			Blocks.glowstone
			));
	private static final int[][] LIVINGWOOD_POSITIONS = { 
		{ -1, 0, 0 }, { 1, 0, 0 },
		{ 0, 0, 1 }, { 0, 0, -1 },
		{ -2, 1, 0 }, { 2, 1, 0 },
		{ 0, 1, 2 }, { 0, 1, -2 },
		{ -3, 2, 0 }, { 3, 2, 0 },
		{ 0, 2, 3 }, { 0, 2, -3 },
		{ -3, 4, 0 }, { 3, 4, 0 },
		{ 0, 4, 3 }, { 0, 4, -3 },
		{ -2, 5, 0 }, { 2, 5, 0 },
		{ 0, 5, 2 }, { 0, 5, -2 },
		{ -1, 6, 0 }, { 1, 6, 0 },
		{ 0, 6, 1 }, { 0, 6, -1 }};
	private static final int[][] CHISLED_ELVEN_QUARTZ_POSITIONS = { 
		{ -1, 0, -1 }, { -1, 0, 1 },
		{ 1, 0, -1 }, { 1, 0, 1 },
		{ -1, 1, -2 }, { -1, 1, 2 },
		{ 1, 1, -2 }, { 1, 1, 2 },
		{ -2, 1, -1 }, { -2, 1, 1 },
		{ 2, 1, -1 }, { 2, 1, 1 },
		{ -1, 5, -2 }, { -1, 5, 2 },
		{ 1, 5, -2 }, { 1, 5, 2 },
		{ -2, 5, -1 }, { -2, 5, 1 },
		{ 2, 5, -1 }, { 2, 5, 1 },
		{ -1, 6, -1 }, { -1, 6, 1 },
		{ 1, 6, -1 }, { 1, 6, 1 }};
	private static final int[][] GLIMMERING_LIVINGWOOD_POSITIONS = {{ -3, 3, 0 }, { 3, 3, 0 }, { 0, 3, 3 }, { 0, 3, -3 }};
	private static final int[][] LIVINGWOOD_STAIRS_UP_POSITIONS = {{ -2, 2, 0 }, { 2, 2, 0 }, { 0, 2, -2 }, { 0, 2, 2 }};
	private static final int[][] LIVINGWOOD_STAIRS_DOWN_POSITIONS = {{ -2, 4, 0 }, { 2, 4, 0 }, { 0, 4, -2 }, { 0, 4, 2 }};
	private static final int[][] AIR_POSITIONS = {
		{ 1, 1, 0 }, { 0, 1, 1 }, { -1, 1, 0 }, { 0, 1, -1 }, { 1, 1, -1 }, { -1, 1, 1 }, { 1, 1, 1 }, { -1, 1, -1 }, { 0, 1, 0 },
		{ 1, 2, 0 }, { 0, 2, 1 }, { -1, 2, 0 }, { 0, 2, -1 }, { 1, 2, -1 }, { -1, 2, 1 }, { 1, 2, 1 }, { -1, 2, -1 }, { 0, 2, 0 }, 
		{ 1, 3, 0 }, { 0, 3, 1 }, { -1, 3, 0 }, { 0, 3, -1 }, { 1, 3, -1 }, { -1, 3, 1 }, { 1, 3, 1 }, { -1, 3, -1 }, { 0, 3, 0 },
		{ 1, 4, 0 }, { 0, 4, 1 }, { -1, 4, 0 }, { 0, 4, -1 }, { 1, 4, -1 }, { -1, 4, 1 }, { 1, 4, 1 }, { -1, 4, -1 }, { 0, 4, 0 },
		{ 1, 5, 0 }, { 0, 5, 1 }, { -1, 5, 0 }, { 0, 5, -1 }, { 1, 5, -1 }, { -1, 5, 1 }, { 1, 5, 1 }, { -1, 5, -1 }, { 0, 5, 0 }};
	private static final int[][] ELVEN_POOL_POSITIONS = { { -1, 2, -2 }, { -1, 2, 2 }, { 1, 2, -2 }, { 1, 2, 2 }, { -2, 2, -1 }, { -2, 2, 1 }, { 2, 2, -1 }, { 2, 2, 1 } };
	private static final int[][] ADDITIONAL_ELVEN_POOL_POSITIONS = { { 3, 3, 1 }, { 3, 3, -1 }, { -3, 3, 1 }, { -3, 3, -1 }, { 1, 3, 3 }, { -1, 3, 3 }, { 1, 3, -3 }, { -1, 3, -3 }, { 2, 3, 2 },
			{ 2, 3, -2 }, { -2, 3, 2 }, { -2, 3, -2 }, };
	private static final int[][] ADDITIONAL_CHISLED_ELVEN_QUARTZ_POSITIONS = { 
		{ 3, 2, 1 }, { 3, 2, -1 },
		{ -3, 2, 1 }, { -3, 2, -1 },
		{ 1, 2, 3 }, { -1, 2, 3 },
		{ 1, 2, -3 }, { -1, 2, -3 },
		{ 2, 2, 2 }, { 2, 2, -2 },
		{ -2, 2, 2 }, { -2, 2, -2 }};
	//@formatter:on
	public boolean isOpen() {
		return worldObj.getBlockMetadata(xCoord, yCoord, zCoord) == 1;
	}

	@Override
	public void updateEntity() {

		int meta = getBlockMetadata();

		if (meta == 0) {// closed
			ticksOpen = 0;
			return;
		}
		int updatedMeta = getUpdatedMetadata();

		if (!hasUnloadedParts) {
			ticksOpen++;
			AxisAlignedBB aabb = getPortalAABB();
			open = ticksOpen > 60;

			if (open) {
				if (vazkii.botania.common.core.handler.ConfigHandler.elfPortalParticlesEnabled)// import configs
					blockParticle(meta);
			}

			List<EntityPlayer> players = worldObj.getEntitiesWithinAABB(EntityPlayer.class, aabb);
			for (EntityPlayer player : players)
				player.addPotionEffect(new PotionEffect(Potion.jump.id, 10, 5, true));// Allows player to jump out of pit

			List<EntityItem> items = worldObj.getEntitiesWithinAABB(EntityItem.class, aabb);
			for (EntityItem item : items) {
				if (!(item.getEntityItem().getItem() instanceof IGatewayCatalyst) && !(item.getEntityData().getBoolean(TAG_PORTAL_KEEP))) {
					currentInventory.add(item.getEntityItem());
					item.setDead();
				}
				if (item.getEntityItem() != null && item.getEntityItem().getItem() instanceof IGatewayCatalyst) {
					resolveRecipes();
				}
			}
		} else
			closeNow = false;

		if (closeNow) {
			worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 0, 1 | 2);
			for (int i = 0; i < 36; i++)
				blockParticle(meta);
			closeNow = false;
		} else if (updatedMeta != meta) {
			if (updatedMeta == 0)
				for (int i = 0; i < 36; i++)
					blockParticle(meta);
			worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, updatedMeta, 1 | 2);
		}

		hasUnloadedParts = false;
	}

	private void blockParticle(int meta) {
		int i = worldObj.rand.nextInt(AIR_POSITIONS.length);
		double[] pos = new double[] { AIR_POSITIONS[i][0] + 0.5F, AIR_POSITIONS[i][1] + 0.5F, AIR_POSITIONS[i][2] + 0.5F };
		float motionMul = 0.2F;
		for (i = 0; i < LibConfigs.PARTICLE_DENSITY * 10; i++)
			Botania.proxy.wispFX(getWorldObj(), xCoord + pos[0], yCoord + pos[1], zCoord + pos[2], (float) (Math.random() * 0.25F), (float) (Math.random() * 0.5F + 0.5F),
					(float) (Math.random() * 0.25F), (float) (Math.random() * 0.15F + 0.1F), (float) (Math.random() - 0.5F) * motionMul, (float) (Math.random() - 0.5F) * motionMul,
					(float) (Math.random() - 0.5F) * motionMul);
	}

	private int getUpdatedMetadata() {
		return checkConstructed() ? 1 : 0;
	}

	private boolean checkPositions(int[][] positions, List<Block> blocks) {
		for (int[] pos : positions)
			if (!checkPosition(pos, blocks))
				return false;
		return true;
	}

	private boolean checkPosition(int[] pos, List<Block> blocks) {
		int x = xCoord + pos[0];
		int y = yCoord + pos[1];
		int z = zCoord + pos[2];
		if (!worldObj.blockExists(x, y, z)) {
			hasUnloadedParts = true;
			return true; // Don't fuck everything up if there's a chunk unload
		}

		Block blockat = worldObj.getBlock(x, y, z);
		for(Block block : blocks)
			if (block == Blocks.air ? blockat.isAir(worldObj, x, y, z) : blockat == block)
				return true;
		return false;
	}

	public boolean onWanded() {
		int meta = getBlockMetadata();
		if (meta == 0) {
			int newMeta = getUpdatedMetadata();
			if (newMeta != 0) {
				worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, newMeta, 1 | 2);
				return true;
			}
		}

		return false;
	}

	private boolean checkConstructed() {
		if (!checkPositions(LIVINGWOOD_POSITIONS, acceptableAlternativeMaterialsLivingwood)){
			BotanicalWorkshop.logger.log(Level.INFO, "Failed 1");
			return false;}
		if (!checkPositions(CHISLED_ELVEN_QUARTZ_POSITIONS, acceptableAlternativeMaterialsQuartz)){
			BotanicalWorkshop.logger.log(Level.INFO, "Failed 2");
			return false;}
		if (!checkPositions(GLIMMERING_LIVINGWOOD_POSITIONS, acceptableAlternativeMaterialsGlimmeringLivingwood)){
			BotanicalWorkshop.logger.log(Level.INFO, "Failed 3");
			return false;}
		if (!checkPositions(LIVINGWOOD_STAIRS_UP_POSITIONS, acceptableAlternativeMaterialsLivingwoodStairs)){// meh
			BotanicalWorkshop.logger.log(Level.INFO, "Failed 4");
			return false;}
		if (!checkPositions(LIVINGWOOD_STAIRS_DOWN_POSITIONS, acceptableAlternativeMaterialsLivingwoodStairs)){
			BotanicalWorkshop.logger.log(Level.INFO, "Failed 5");
			return false;}
		if (!checkPositions(ELVEN_POOL_POSITIONS, Arrays.asList(ModBlocks.elvenPool))){
			BotanicalWorkshop.logger.log(Level.INFO, "Failed 6");
			return false;}
		if (!checkPositions(AIR_POSITIONS, Arrays.asList(Blocks.air))){
			BotanicalWorkshop.logger.log(Level.INFO, "Failed 7");
			return false;}
		if (checkPositions(ADDITIONAL_CHISLED_ELVEN_QUARTZ_POSITIONS, acceptableAlternativeMaterialsQuartz) && checkPositions(ADDITIONAL_ELVEN_POOL_POSITIONS, Arrays.asList(ModBlocks.elvenPool)))
			additionalPools = true;
		activatePortal();
		return true;
	}

	private void activatePortal() {
		if (ticksOpen < 50)// Opening
			return;
		int cost = ticksOpen == 50 ? OPENING_MANA_COST : COST_PER_TICK;// one time cost (one pool, 1/8 from each) : every tick afterward
		if (cost > getCurrentMana())
			closeNow = true;
		else
			recieveMana(-cost);
	}

	public void summonItem(ItemStack stack) {
		if (!worldObj.isRemote) {
			EntityItem item = new EntityItem(worldObj, xCoord + 0.5, yCoord + 1.5, zCoord + 0.5, stack);
			item.getEntityData().setBoolean(TAG_PORTAL_KEEP, true);
			if(item.getEntityItem().getItem() instanceof IGatewayBindingItem){
				ItemNBTHelper.setInt(item.getEntityItem(), "boundGatewayX", xCoord);
				ItemNBTHelper.setInt(item.getEntityItem(), "boundGatewayY", yCoord);
				ItemNBTHelper.setInt(item.getEntityItem(), "boundGatewayZ", zCoord);
			}
			worldObj.spawnEntityInWorld(item);
		}
	}

	private boolean resolveRecipes() {
		List<EntityItem> items = worldObj.getEntitiesWithinAABB(EntityItem.class, getPortalAABB());
		for (EntityItem item : items)
			if (item.getEntityItem().getItem() instanceof IGatewayCatalyst) {
				for (RecipeGatewayTransmutation recipe : BotanicalWorkshopAPI.gatewayRecipes)
					if (recipe.getCatalyst().isItemEqual(item.getEntityItem()))
						if (recipe.matches(currentInventory, false) && !worldObj.isRemote) {
							recipe.matches(currentInventory, true);
							summonItem(recipe.getOutput().copy());
							return true;
						}
				if (item.getEntityItem().getItem() instanceof IGatewayMod && !(item.getEntityData().getBoolean(TAG_PORTAL_KEEP))) {
					((IGatewayMod) item.getEntityItem().getItem()).onGatewayUpdate(this, item);
				}

				for (ItemStack possibleLexicon : currentInventory)
					if (possibleLexicon.getItem() instanceof ILexicon) {
						if (item.getEntityItem() == new ItemStack(ModItems.bindingCrystal, 1, 1))
							((ILexicon) possibleLexicon.getItem()).unlockKnowledge(possibleLexicon, LexiconData.thaumicKnowledge);
						if (item.getEntityItem() == new ItemStack(ModItems.bindingCrystal, 1, 2))
							((ILexicon) possibleLexicon.getItem()).unlockKnowledge(possibleLexicon, LexiconData.bloodKnowledge);
						if (item.getEntityItem() == new ItemStack(ModItems.bindingCrystal, 1, 3))
							((ILexicon) possibleLexicon.getItem()).unlockKnowledge(possibleLexicon, LexiconData.lightningKnowledge);
					}
			}
		return false;

	}

	public void recieveMana(int amount) {
		int totalMana = getCurrentMana();
		int[][] positions = additionalPools ? ArrayUtils.addAll(ELVEN_POOL_POSITIONS, ADDITIONAL_ELVEN_POOL_POSITIONS) : ELVEN_POOL_POSITIONS;
		if (-amount < (totalMana - 64)) {
			for (int[] pos : positions) {
				TileEntity tile = worldObj.getTileEntity(xCoord + pos[0], yCoord + pos[1], zCoord + pos[2]);
				if (tile instanceof TileElvenPool) {
					TileElvenPool pool = (TileElvenPool) tile;
					double costRatio = Math.abs(amount) == amount ? ((double) TileElvenPool.MAX_MANA - (double) pool.getCurrentMana()) / ((double) totalMana) : (double) pool.getCurrentMana()
							/ (double) totalMana; // What percent does a small pool get when adding mana?
					// If cost ratio is positive give small pools more; If negative, take more from large pools
					if (!worldObj.isRemote)
						pool.recieveMana((int) Math.round(amount * costRatio));
				}
			}
		}
	}

	public int getCurrentMana() {
		int totalMana = 0;
		int[][] positions = additionalPools ? ArrayUtils.addAll(ELVEN_POOL_POSITIONS, ADDITIONAL_ELVEN_POOL_POSITIONS) : ELVEN_POOL_POSITIONS;
		for (int[] pos : positions) {
			TileEntity tile = worldObj.getTileEntity(xCoord + pos[0], yCoord + pos[1], zCoord + pos[2]);
			if (tile instanceof TileElvenPool) {
				TileElvenPool pool = (TileElvenPool) tile;
				totalMana += pool.getCurrentMana();
			}
		}
		return totalMana;
	}

	public void renderHUD(Minecraft mc, ScaledResolution res) {
		String name = StatCollector.translateToLocal(new ItemStack(ModBlocks.gatewayCore, 1, getBlockMetadata()).getUnlocalizedName().replaceAll("tile.", "tile." + LibResources.PREFIX_MOD) + ".name");
		int color = 0xB6F2B7; // Offical color of the elves
		HUDHandler.drawSimpleManaHUD(color, getCurrentMana(), TileElvenPool.MAX_MANA
				* (additionalPools ? ELVEN_POOL_POSITIONS.length + ADDITIONAL_ELVEN_POOL_POSITIONS.length : ELVEN_POOL_POSITIONS.length), name, res);
		String catalysts = "";

		List<EntityItem> items = worldObj.getEntitiesWithinAABB(EntityItem.class, getPortalAABB());
		for (EntityItem item : items)
			if (item.getEntityItem().getItem() instanceof IGatewayCatalyst)
				catalysts = catalysts + ", " + StatCollector.translateToLocal(item.getEntityItem().getUnlocalizedName().replaceAll("item.", "item." + LibResources.PREFIX_MOD) + ".name");

		if (catalysts.length() >= 2) // Avoid index out of range on no catalysts
			catalysts = catalysts.substring(2); // Skip Initial ", "

		int x = res.getScaledWidth() / 2 - mc.fontRenderer.getStringWidth(catalysts) / 2;
		int y = res.getScaledHeight() / 2 + 30;
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		mc.fontRenderer.drawStringWithShadow(catalysts, x, y, color);
		GL11.glDisable(GL11.GL_BLEND);
	}

	public AxisAlignedBB getPortalAABB() {
		AxisAlignedBB aabb = AxisAlignedBB.getBoundingBox(xCoord - 1, yCoord + 1, zCoord - 1, xCoord + 2, yCoord + 6, zCoord + 2);
		return aabb;
	}

	@Override
	public boolean canRecieveManaFromBursts() {
		return true;
	}

	@Override
	public boolean isFull() {
		int[][] positions = additionalPools ? ArrayUtils.addAll(ELVEN_POOL_POSITIONS, ADDITIONAL_ELVEN_POOL_POSITIONS) : ELVEN_POOL_POSITIONS;
		for (int[] pos : positions) {
			TileEntity tile = worldObj.getTileEntity(xCoord + pos[0], yCoord + pos[1], zCoord + pos[2]);
			if (tile instanceof TileElvenPool && !((TileElvenPool) tile).isFull())
				return false;
		}
		return true;// Made it through all locations without finding not full pool; if no pools, then it is full of 0 mana, max capacity is 0
	}

	@Override
	public boolean canAttachSpark(ItemStack stack) {
		return true;
	}

	@Override
	public void attachSpark(ISparkEntity entity) {
		// NO-OP
	}

	@Override
	public ISparkEntity getAttachedSpark() {
		List<ISparkEntity> sparks = worldObj.getEntitiesWithinAABB(ISparkEntity.class, AxisAlignedBB.getBoundingBox(xCoord, yCoord + 1, zCoord, xCoord + 1, yCoord + 2, zCoord + 1));
		if (sparks.size() == 1) {
			Entity e = (Entity) sparks.get(0);
			return (ISparkEntity) e;
		}

		return null;
	}

	@Override
	public boolean areIncomingTranfersDone() {
		return !isFull();
	}

	@Override
	public int getAvailableSpaceForMana() {
		return Math.max(0, TileElvenPool.MAX_MANA * (additionalPools ? ELVEN_POOL_POSITIONS.length + ADDITIONAL_ELVEN_POOL_POSITIONS.length : ELVEN_POOL_POSITIONS.length) - getCurrentMana());
	}
}