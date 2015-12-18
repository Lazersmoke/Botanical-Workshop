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
import org.lwjgl.opengl.GL11;

import vazkii.botania.api.lexicon.ILexicon;
import vazkii.botania.client.core.handler.HUDHandler;
import vazkii.botania.common.Botania;
import vazkii.botania.common.core.helper.ItemNBTHelper;

public class TileGatewayCore extends TileMod{
	/**
	 * List of items in gateway
	 */
	public List<ItemStack> currentInventory = new ArrayList<ItemStack>();
	private boolean open = false;
	/**
	 * This gateway's UUID
	 */
	public UUID uuid;
	/**
	 * HashMap of all gateway UUID's to Gateway tiles
	 */
	public static HashMap<UUID, TileGatewayCore> UUIDMap = new HashMap<UUID, TileGatewayCore>();

	public TileGatewayCore(){
		super();
		final UUID freshUuid = UUID.randomUUID();
		UUIDMap.put(freshUuid, this);
		this.uuid = freshUuid;
		if(BotanicalWorkshop.bloodMagicLoaded){
			acceptableAlternativeMaterialsQuartz.add((Block) Block.blockRegistry.getObject("AWWayofTime:AlchemicalWizardrybloodRune"));
			acceptableAlternativeMaterialsLivingwood.add((Block) Block.blockRegistry.getObject("AWWayofTime:AlchemicalWizardrybloodRune"));
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
	/**
	 * Is the gateway open?
	 *
	 * @return true = yes, false = no
	 */
	public boolean isOpen(){
		return worldObj.getBlockMetadata(xCoord, yCoord, zCoord) == 1;
	}

	@Override
	public void updateEntity(){

		final int meta = getBlockMetadata();

		if(meta == 0){// closed
			ticksOpen = 0;
			return;
		}
		final int updatedMeta = getUpdatedMetadata();

		if(!hasUnloadedParts){
			ticksOpen++;
			final AxisAlignedBB aabb = getPortalAABB();
			open = ticksOpen > 60;

			if(open)
				if(vazkii.botania.common.core.handler.ConfigHandler.elfPortalParticlesEnabled)// import configs
					blockParticle(meta);

			@SuppressWarnings("unchecked")
			final List<EntityPlayer> players = worldObj.getEntitiesWithinAABB(EntityPlayer.class, aabb);
			for(final EntityPlayer player : players)
				player.addPotionEffect(new PotionEffect(Potion.jump.id, 10, 5, true));// Allows player to jump out of pit

			@SuppressWarnings("unchecked")
			final List<EntityItem> items = worldObj.getEntitiesWithinAABB(EntityItem.class, aabb);
			for(final EntityItem item : items){
				if(!(item.getEntityItem().getItem() instanceof IGatewayCatalyst) && !(item.getEntityData().getBoolean(TAG_PORTAL_KEEP))){
					currentInventory.add(item.getEntityItem());
					item.setDead();
				}
				if(item.getEntityItem() != null && item.getEntityItem().getItem() instanceof IGatewayCatalyst)
					resolveRecipes();
			}
		}else
			closeNow = false;

		if(closeNow){
			worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 0, 1 | 2);
			for(int i = 0; i < 36; i++)
				blockParticle(meta);
			closeNow = false;
		}else if(updatedMeta != meta){
			if(updatedMeta == 0)
				for(int i = 0; i < 36; i++)
					blockParticle(meta);
			worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, updatedMeta, 1 | 2);
		}

		hasUnloadedParts = false;
	}

	//@formatter:off
/*
	@Override
	public void readCustomNBT(NBTTagCompound cmp){
		super.readCustomNBT(cmp);
		final NBTTagList nbttaglist = cmp.getTagList("Items", 10);

		currentInventory.clear();
		for(int i = 0; i < nbttaglist.tagCount(); ++i){
			final NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
			final int j = nbttagcompound1.getByte("Slot") & 255;

			if(j >= 0 && j < currentInventory.size())
				currentInventory.add(ItemStack.loadItemStackFromNBT(nbttagcompound1));
		}
	}

	@Override
	public void writeCustomNBT(NBTTagCompound cmp){
		super.writeCustomNBT(cmp);
		final NBTTagList nbttaglist = new NBTTagList();

		for(int i = 0; i < currentInventory.size(); ++i)
			if(currentInventory.get(i) != null){
				final NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte) i);
				currentInventory.get(i).writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}

		cmp.setTag("Items", nbttaglist);
	}
*/
	//@formatter:on
	private void blockParticle(int meta){
		int i = worldObj.rand.nextInt(AIR_POSITIONS.length);
		final double[] pos = new double[] {AIR_POSITIONS[i][0] + 0.5F, AIR_POSITIONS[i][1] + 0.5F, AIR_POSITIONS[i][2] + 0.5F};
		final float motionMul = 0.2F;
		for(i = 0; i < LibConfigs.PARTICLE_DENSITY * 10; i++)
			Botania.proxy.wispFX(getWorldObj(), xCoord + pos[0], yCoord + pos[1], zCoord + pos[2], (float) (Math.random() * 0.25F), (float) (Math.random() * 0.5F + 0.5F), (float) (Math.random() * 0.25F), (float) (Math.random() * 0.15F + 0.1F), (float) (Math.random() - 0.5F) * motionMul, (float) (Math.random() - 0.5F) * motionMul, (float) (Math.random() - 0.5F) * motionMul);
	}

	private int getUpdatedMetadata(){
		return checkConstructed() ? 1 : 0;
	}

	private boolean checkPositions(int[][] positions, List<Block> blocks){
		for(final int[] pos : positions)
			if(!checkPosition(pos, blocks))
				return false;
		return true;
	}

	private boolean checkPosition(int[] pos, List<Block> blocks){
		final int x = xCoord + pos[0];
		final int y = yCoord + pos[1];
		final int z = zCoord + pos[2];
		if(!worldObj.blockExists(x, y, z)){
			hasUnloadedParts = true;
			return true; // Don't fuck everything up if there's a chunk unload
		}

		final Block blockat = worldObj.getBlock(x, y, z);
		for(final Block block : blocks)
			if(block == Blocks.air ? blockat.isAir(worldObj, x, y, z) : blockat == block)
				return true;
		return false;
	}

	public boolean onWanded(){
		final int meta = getBlockMetadata();
		if(meta == 0){
			final int newMeta = getUpdatedMetadata();
			if(newMeta != 0){
				worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, newMeta, 1 | 2);
				return true;
			}
		}

		return false;
	}

	private boolean checkConstructed(){
		if(!checkPositions(LIVINGWOOD_POSITIONS, acceptableAlternativeMaterialsLivingwood))
			return false;
		if(!checkPositions(CHISLED_ELVEN_QUARTZ_POSITIONS, acceptableAlternativeMaterialsQuartz))
			return false;
		if(!checkPositions(GLIMMERING_LIVINGWOOD_POSITIONS, acceptableAlternativeMaterialsGlimmeringLivingwood))
			return false;
		if(!checkPositions(LIVINGWOOD_STAIRS_UP_POSITIONS, acceptableAlternativeMaterialsLivingwoodStairs))
			return false;
		if(!checkPositions(LIVINGWOOD_STAIRS_DOWN_POSITIONS, acceptableAlternativeMaterialsLivingwoodStairs))
			return false;
		if(!checkPositions(ELVEN_POOL_POSITIONS, Arrays.asList(ModBlocks.elvenPool)))
			return false;
		if(!checkPositions(AIR_POSITIONS, Arrays.asList(Blocks.air)))
			return false;
		if(checkPositions(ADDITIONAL_CHISLED_ELVEN_QUARTZ_POSITIONS, acceptableAlternativeMaterialsQuartz) && checkPositions(ADDITIONAL_ELVEN_POOL_POSITIONS, Arrays.asList(ModBlocks.elvenPool)))
			additionalPools = true;
		activatePortal();
		return true;
	}

	private void activatePortal(){
		if(ticksOpen < 50)// Opening
			return;
		final int cost = ticksOpen == 50 ? OPENING_MANA_COST : COST_PER_TICK;// one time cost (one pool, 1/8 from each) : every tick afterward
		if(cost > getCurrentMana())
			closeNow = true;
		else
			recieveMana(-cost);
	}

	private float[] summonOffset = {0.5F, 1.5F, 0.5F};
	private int summonOffsetResetTicks = 2;

	/**
	 * Changes the offset of where the items are summoned
	 *
	 * @param newSummonOffset
	 * New offset in form {x,y,z}
	 */
	public void pokeSummonOffset(float[] newSummonOffset){
		summonOffset = newSummonOffset;
		summonOffsetResetTicks = 2;
	}// call every tick to keep summon changed

	/**
	 * Summons an item in the gateway
	 *
	 * @param stack
	 * ItemStack to summon an EntityItem for
	 */
	public void summonItem(ItemStack stack){
		if(summonOffsetResetTicks < 1)
			summonOffset = new float[] {0.5F, 1.5F, 0.5F};
		summonOffsetResetTicks--;
		if(!worldObj.isRemote){
			final EntityItem item = new EntityItem(worldObj, xCoord + summonOffset[0], yCoord + summonOffset[1], zCoord + summonOffset[2], stack);
			item.getEntityData().setBoolean(TAG_PORTAL_KEEP, true);
			if(item.getEntityItem().getItem() instanceof IGatewayBindingItem){
				ItemNBTHelper.setInt(item.getEntityItem(), "boundGatewayX", xCoord);
				ItemNBTHelper.setInt(item.getEntityItem(), "boundGatewayY", yCoord);
				ItemNBTHelper.setInt(item.getEntityItem(), "boundGatewayZ", zCoord);
			}
			worldObj.spawnEntityInWorld(item);
		}
	}

	private boolean resolveRecipes(){
		@SuppressWarnings("unchecked")
		final List<EntityItem> items = worldObj.getEntitiesWithinAABB(EntityItem.class, getPortalAABB());
		for(final EntityItem item : items)
			if(item.getEntityItem().getItem() instanceof IGatewayCatalyst){
				RecipeGatewayTransmutation bestRecipe = null;
				if(!currentInventory.isEmpty() && !worldObj.isRemote){// Optimization; dont check if we literally cant craft anything
					for(final RecipeGatewayTransmutation recipe : BotanicalWorkshopAPI.gatewayRecipes)
						if(recipe.getCatalyst().isItemEqual(item.getEntityItem())){
							// BotanicalWorkshop.logger.log(Level.INFO, "Checking recipe: " + recipe.getOutput().toString() + " for " + recipe.getInputs().toString() + " with catalyst " + recipe.getCatalyst().toString());
							if(recipe.matches(currentInventory, false) == 1){
								// BotanicalWorkshop.logger.log(Level.INFO, "Checking recipe: " + recipe.getOutput().toString() + " for " + recipe.getInputs().toString() + " with catalyst " + recipe.getCatalyst().toString());
								// BotanicalWorkshop.logger.log(Level.INFO, "Partial Match!");
								bestRecipe = recipe;
								continue;
							}
							if(recipe.matches(currentInventory, false) == 2){
								// BotanicalWorkshop.logger.log(Level.INFO, "Checking recipe: " + recipe.getOutput().toString() + " for " + recipe.getInputs().toString() + " with catalyst " + recipe.getCatalyst().toString());
								// BotanicalWorkshop.logger.log(Level.INFO, "Exact Match!");
								bestRecipe = recipe;
								break;
							}
						}
					if(bestRecipe != null){
						// BotanicalWorkshop.logger.log(Level.INFO, "Found a recipe, using " + bestRecipe.getOutput().toString() + " for " + bestRecipe.getInputs().toString() + " with catalyst " + bestRecipe.getCatalyst().toString());
						bestRecipe.matches(currentInventory, true);// Consume items
						summonItem(bestRecipe.getOutput().copy());
						return true;
					}
				}
				if(item.getEntityItem().getItem() instanceof IGatewayMod && !(item.getEntityData().getBoolean(TAG_PORTAL_KEEP)))
					((IGatewayMod) item.getEntityItem().getItem()).onGatewayUpdate(this, item);

				for(final ItemStack possibleLexicon : currentInventory)
					if(possibleLexicon.getItem() instanceof ILexicon){
						if(item.getEntityItem() == new ItemStack(ModItems.bindingCrystal, 1, 1))
							((ILexicon) possibleLexicon.getItem()).unlockKnowledge(possibleLexicon, LexiconData.thaumicKnowledge);
						if(item.getEntityItem() == new ItemStack(ModItems.bindingCrystal, 1, 2))
							((ILexicon) possibleLexicon.getItem()).unlockKnowledge(possibleLexicon, LexiconData.bloodKnowledge);
						if(item.getEntityItem() == new ItemStack(ModItems.bindingCrystal, 1, 3))
							((ILexicon) possibleLexicon.getItem()).unlockKnowledge(possibleLexicon, LexiconData.lightningKnowledge);
					}
			}
		return false;

	}

	/**
	 * Gives the gateway some mana, also accepts negative mana
	 *
	 * @param amount
	 * Amount to add
	 */
	public void recieveMana(int amount){
		final int totalMana = getCurrentMana();
		final int[][] positions = additionalPools ? ArrayUtils.addAll(ELVEN_POOL_POSITIONS, ADDITIONAL_ELVEN_POOL_POSITIONS) : ELVEN_POOL_POSITIONS;
		if(-amount < (totalMana - 64))
			for(final int[] pos : positions){
				final TileEntity tile = worldObj.getTileEntity(xCoord + pos[0], yCoord + pos[1], zCoord + pos[2]);
				if(tile instanceof TileElvenPool){
					final TileElvenPool pool = (TileElvenPool) tile;
					final double costRatio = Math.abs(amount) == amount ? ((double) TileElvenPool.MAX_MANA - (double) pool.getCurrentMana()) / (totalMana) : (double) pool.getCurrentMana() / (double) totalMana; // What percent does a small pool get when adding mana?
					// If cost ratio is positive give small pools more; If negative, take more from large pools
					if(!worldObj.isRemote)
						pool.recieveMana((int) Math.round(amount * costRatio));
				}
			}
	}

	/**
	 * How much mana is in the gateway? (All pools combined)
	 *
	 * @return Amount of mana in the gateway
	 */
	public int getCurrentMana(){
		int totalMana = 0;
		final int[][] positions = additionalPools ? ArrayUtils.addAll(ELVEN_POOL_POSITIONS, ADDITIONAL_ELVEN_POOL_POSITIONS) : ELVEN_POOL_POSITIONS;
		for(final int[] pos : positions){
			final TileEntity tile = worldObj.getTileEntity(xCoord + pos[0], yCoord + pos[1], zCoord + pos[2]);
			if(tile instanceof TileElvenPool){
				final TileElvenPool pool = (TileElvenPool) tile;
				totalMana += pool.getCurrentMana();
			}
		}
		return totalMana;
	}

	/**
	 * Renders a HUD for the gateway when you are holding a wand
	 *
	 * @param mc
	 * @param res
	 */
	public void renderHUD(Minecraft mc, ScaledResolution res){
		final String name = StatCollector.translateToLocal(new ItemStack(ModBlocks.gatewayCore, 1, getBlockMetadata()).getUnlocalizedName().replaceAll("tile.", "tile." + LibResources.PREFIX_MOD) + ".name");
		final int color = 0xB6F2B7; // Offical color of the elves
		HUDHandler.drawSimpleManaHUD(color, getCurrentMana(), TileElvenPool.MAX_MANA * (additionalPools ? ELVEN_POOL_POSITIONS.length + ADDITIONAL_ELVEN_POOL_POSITIONS.length : ELVEN_POOL_POSITIONS.length), name, res);
		String catalysts = "";

		@SuppressWarnings("unchecked")
		final List<EntityItem> items = worldObj.getEntitiesWithinAABB(EntityItem.class, getPortalAABB());
		for(final EntityItem item : items)
			if(item.getEntityItem().getItem() instanceof IGatewayCatalyst)
				catalysts = catalysts + ", " + StatCollector.translateToLocal(item.getEntityItem().getUnlocalizedName().replaceAll("item.", "item." + LibResources.PREFIX_MOD) + ".name");

		if(catalysts.length() >= 2) // Avoid index out of range on no catalysts
			catalysts = catalysts.substring(2); // Skip Initial ", "

		final int x = res.getScaledWidth() / 2 - mc.fontRenderer.getStringWidth(catalysts) / 2;
		final int y = res.getScaledHeight() / 2 + 30;
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		mc.fontRenderer.drawStringWithShadow(catalysts, x, y, color);
		GL11.glDisable(GL11.GL_BLEND);
	}

	/**
	 * Gets the AABB where items are taken in
	 *
	 * @return Portal AABB
	 */
	public AxisAlignedBB getPortalAABB(){
		final AxisAlignedBB aabb = AxisAlignedBB.getBoundingBox(xCoord - 1, yCoord + 1, zCoord - 1, xCoord + 2, yCoord + 6, zCoord + 2);
		return aabb;
	}

	/**
	 * Is the gateway full of mana?
	 *
	 * @return true = yes, false = no
	 */
	public boolean isFull(){
		final int[][] positions = additionalPools ? ArrayUtils.addAll(ELVEN_POOL_POSITIONS, ADDITIONAL_ELVEN_POOL_POSITIONS) : ELVEN_POOL_POSITIONS;
		for(final int[] pos : positions){
			final TileEntity tile = worldObj.getTileEntity(xCoord + pos[0], yCoord + pos[1], zCoord + pos[2]);
			if(tile instanceof TileElvenPool && !((TileElvenPool) tile).isFull())
				return false;
		}
		return true;// Made it through all locations without finding not full pool; if no pools, then it is full of 0 mana, max capacity is 0
	}

	/**
	 * How much space for mana is remaining
	 *
	 * @return How much more mana can be stored
	 */
	public int getAvailableSpaceForMana(){
		return Math.max(0, TileElvenPool.MAX_MANA * (additionalPools ? ELVEN_POOL_POSITIONS.length + ADDITIONAL_ELVEN_POOL_POSITIONS.length : ELVEN_POOL_POSITIONS.length) - getCurrentMana());
	}
}