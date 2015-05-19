package lazersmoke.botanicalworkshop.common.block.tile;
//This class is %50 Lazersmoke
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import lazersmoke.botanicalworkshop.api.BotanicalWorkshopAPI;
import lazersmoke.botanicalworkshop.api.mana.IGatewayCatalyst;
import lazersmoke.botanicalworkshop.api.mana.IGatewayMod;
import lazersmoke.botanicalworkshop.api.recipe.RecipeGateway;
import lazersmoke.botanicalworkshop.client.lib.LibResources;
import lazersmoke.botanicalworkshop.common.BotanicalWorkshop;
import lazersmoke.botanicalworkshop.common.block.ModBlocks;
import lazersmoke.botanicalworkshop.common.block.tile.mana.TileElvenPool;
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

import org.lwjgl.opengl.GL11;

import vazkii.botania.client.core.handler.HUDHandler;
import vazkii.botania.common.block.tile.TileMod;

public class TileGatewayCore extends TileMod{
		//  W W
		// W   W
		//WS   SW
		//W     G
		//WS   SW
		// W   W 
		//  WCW 
	public List<ItemStack> currentInventory = new ArrayList<ItemStack>();
	public boolean open = false;
	public UUID uuid;
	
	public TileGatewayCore(){
		super();
		uuid = UUID.randomUUID();
	}
	
	private static final String TAG_PORTAL_KEEP = "gatewayKeep";
	private static final int OPENING_MANA_COST = LibConfigs.GATEWAY_OPENING_MANA_COST;
	private static final int COST_PER_TICK = LibConfigs.GATEWAY_TICK_MANA_COST;
	private boolean hasUnloadedParts = false;
	private int ticksOpen = 0;
	private boolean closeNow = false;
	private static final int[][] LIVINGWOOD_POSITIONS = {
		{ -1, 0, 0}, { 1, 0, 0}, { 0, 0, 1}, { 0, 0, -1},
		{ -2, 1, 0}, { 2, 1, 0}, { 0, 1, 2}, { 0, 1, -2},
		{ -3, 2, 0}, { 3, 2, 0}, { 0, 2, 3}, { 0, 2, -3},
		{ -3, 4, 0}, { 3, 4, 0}, { 0, 4, 3}, { 0, 4, -3},
		{ -2, 5, 0}, { 2, 5, 0}, { 0, 5, 2}, { 0, 5, -2},
		{ -1, 6, 0}, { 1, 6, 0}, { 0, 6, 1}, { 0, 6, -1}
		};
	private static final int[][] CHISLED_ELVEN_QUARTZ_POSITIONS = {
		{ -1, 0, -1}, { -1, 0, 1}, { 1, 0, -1}, { 1, 0, 1},
		{ -1, 1, -2}, { -1, 1, 2}, { 1, 1, -2}, { 1, 1, 2}, { -2, 1, -1}, { -2, 1, 1}, { 2, 1, -1}, { 2, 1, 1},
		{ -1, 5, -2}, { -1, 5, 2}, { 1, 5, -2}, { 1, 5, 2}, { -2, 5, -1}, { -2, 5, 1}, { 2, 5, -1}, { 2, 5, 1},	
		{ -1, 6, -1}, { -1, 6, 1}, { 1, 6, -1}, { 1, 6, 1}
	};
	private static final int[][] GLIMMERING_LIVINGWOOD_POSITIONS = {
		{ -3, 3, 0}, { 3, 3, 0}, { 0, 3, 3}, { 0, 3, -3}
	};
	private static final int[][] LIVINGWOOD_STAIRS_UP_POSITIONS = {
		{ -2, 2, 0}, { 2, 2, 0}, { 0, 2, -2}, { 0, 2, 2}
	};
	private static final int[][] LIVINGWOOD_STAIRS_DOWN_POSITIONS = {
		{ -2, 4, 0}, { 2, 4, 0}, { 0, 4, -2}, { 0, 4, 2}
	};
	private static final int[][] AIR_POSITIONS = {
		{ 1, 1, 0}, { 0, 1, 1}, { -1, 1, 0}, { 0, 1, -1}, { 1, 1, -1}, { -1, 1, 1}, { 1, 1, 1}, { -1, 1, -1}, { 0, 1, 0}, 
		{ 1, 2, 0}, { 0, 2, 1}, { -1, 2, 0}, { 0, 2, -1}, { 1, 2, -1}, { -1, 2, 1}, { 1, 2, 1}, { -1, 2, -1}, { 0, 2, 0}, 
		{ 1, 3, 0}, { 0, 3, 1}, { -1, 3, 0}, { 0, 3, -1}, { 1, 3, -1}, { -1, 3, 1}, { 1, 3, 1}, { -1, 3, -1}, { 0, 3, 0}, 
		{ 1, 4, 0}, { 0, 4, 1}, { -1, 4, 0}, { 0, 4, -1}, { 1, 4, -1}, { -1, 4, 1}, { 1, 4, 1}, { -1, 4, -1}, { 0, 4, 0}, 
		{ 1, 5, 0}, { 0, 5, 1}, { -1, 5, 0}, { 0, 5, -1}, { 1, 5, -1}, { -1, 5, 1}, { 1, 5, 1}, { -1, 5, -1}, { 0, 5, 0} 
	};
	private static final int[][] ELVEN_POOL_POSITIONS = {
		{ -1, 2, -2}, { -1, 2, 2}, { 1, 2, -2}, { 1, 2, 2}, { -2, 2, -1}, { -2, 2, 1}, { 2, 2, -1}, { 2, 2, 1}
	};
	@Override
	public void updateEntity(){
		
		int meta = getBlockMetadata();
		
		if(meta==0){//closed
			ticksOpen = 0;
			return;
		}
		int updatedMeta = getUpdatedMetadata();
		
		if(!hasUnloadedParts){
			ticksOpen++;
			AxisAlignedBB aabb = getPortalAABB();
			open = ticksOpen > 60;

			if(open) {
				if(vazkii.botania.common.core.handler.ConfigHandler.elfPortalParticlesEnabled)//import configs
					blockParticle(meta);
			}
			
			List<EntityPlayer> players = worldObj.getEntitiesWithinAABB(EntityPlayer.class, aabb);
			for(EntityPlayer player : players)
				player.addPotionEffect(new PotionEffect(Potion.jump.id, 20, 1));//Allows player to jump out of pit
			
			List<EntityItem> items = worldObj.getEntitiesWithinAABB(EntityItem.class, aabb);
			for(EntityItem item : items){
				if(!(item.getEntityItem().getItem() instanceof IGatewayCatalyst) && !(item.getEntityData().getBoolean(TAG_PORTAL_KEEP))){
					currentInventory.add(item.getEntityItem());
					item.setDead();
				}
				if(item.getEntityItem() != null && item.getEntityItem().getItem() instanceof IGatewayCatalyst){
					resolveRecipes();
				}
			}
		}else closeNow = false;
		

		
		if(closeNow) {
			worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 0, 1 | 2);
			for(int i = 0; i < 36; i++)
				blockParticle(meta);
			closeNow = false;
		} else if(updatedMeta != meta) {
			if(updatedMeta == 0)
				for(int i = 0; i < 36; i++)
					blockParticle(meta);
			worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, updatedMeta, 1 | 2);
		}

		hasUnloadedParts = false;
	}
	
	private void blockParticle(int meta) {
		int i = worldObj.rand.nextInt(AIR_POSITIONS.length);
		double[] pos = new double[] {
				AIR_POSITIONS[i][0] + 0.5F, AIR_POSITIONS[i][1] + 0.5F, AIR_POSITIONS[i][2] + 0.5F
		};
		float motionMul = 0.2F;
		for(i = 0; i < LibConfigs.PARTICLE_DENSITY; i++)
		BotanicalWorkshop.proxy.wispFX(getWorldObj(), xCoord + pos[0], yCoord + pos[1], zCoord + pos[2], (float) (Math.random() * 0.25F), (float) (Math.random() * 0.5F + 0.5F), (float) (Math.random() * 0.25F), (float) (Math.random() * 0.15F + 0.1F), (float) (Math.random() - 0.5F) * motionMul, (float) (Math.random() - 0.5F) * motionMul, (float) (Math.random() - 0.5F) * motionMul);
	}

	private int getUpdatedMetadata() {
		return checkConstructed() ? 1 : 0;
	}
	
	private boolean checkPositions(int[][] positions, Block block, int meta) {
		for(int[] pos : positions) {
			if(!checkPosition(pos, block, meta))
				return false;
		}
		return true;
	}
	
	private boolean checkPosition(int[] pos, Block block, int meta) {
		int x = xCoord + pos[0];
		int y = yCoord + pos[1];
		int z = zCoord + pos[2];
		if(!worldObj.blockExists(x, y, z)) {
			hasUnloadedParts = true;
			return true; // Don't fuck everything up if there's a chunk unload
		}

		Block blockat = worldObj.getBlock(x, y, z);
		if(block == Blocks.air ? blockat.isAir(worldObj, x, y, z) : blockat == block) {
			if(meta == -1)
				return true;

			int metaat = worldObj.getBlockMetadata(x, y, z);
			return meta == metaat;
		}

		return false;
	}
	
	public boolean onWanded() {
		int meta = getBlockMetadata();
		if(meta == 0) {
			int newMeta = getUpdatedMetadata();
			if(newMeta != 0) {
				worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, newMeta, 1 | 2);
				return true;
			}
		}

		return false;
	}
	
	private boolean checkConstructed() {
		if(!checkPositions(LIVINGWOOD_POSITIONS, vazkii.botania.common.block.ModBlocks.livingwood, 0))
			return false;
		if(!checkPositions(CHISLED_ELVEN_QUARTZ_POSITIONS, vazkii.botania.common.block.ModFluffBlocks.elfQuartz, 1))
			return false;
		if(!checkPositions(GLIMMERING_LIVINGWOOD_POSITIONS, vazkii.botania.common.block.ModBlocks.livingwood, 5))
			return false;
		if(!checkPositions(LIVINGWOOD_STAIRS_UP_POSITIONS, vazkii.botania.common.block.ModFluffBlocks.livingwoodStairs, -1))//meh
			return false;
		if(!checkPositions(LIVINGWOOD_STAIRS_DOWN_POSITIONS, vazkii.botania.common.block.ModFluffBlocks.livingwoodStairs, -1))
			return false;
		if(!checkPositions(ELVEN_POOL_POSITIONS, ModBlocks.elvenPool, -1))
			return false;
		if(!checkPositions(AIR_POSITIONS, Blocks.air, -1))
			return false;
		activatePortal();
		return true;
	}
	
	private void activatePortal() {
		if(ticksOpen < 50)//Opening
			return;

		int cost = ticksOpen == 50 ? OPENING_MANA_COST : COST_PER_TICK;//one time cost (one pool, 1/8 from each) : every tick afterward
		if(!addMana(-cost))
			closeNow = true;
	}
	
	public void summonItem(ItemStack stack){
		EntityItem item = new EntityItem(worldObj, xCoord + 0.5, yCoord + 1.5, zCoord + 0.5, stack);
		item.getEntityData().setBoolean(TAG_PORTAL_KEEP, true);
		worldObj.spawnEntityInWorld(item);
	}
	
	private boolean resolveRecipes(){
		List<EntityItem> items = worldObj.getEntitiesWithinAABB(EntityItem.class, getPortalAABB());
		for(EntityItem item : items)
			if(item.getEntityItem().getItem() instanceof IGatewayCatalyst){
				for(RecipeGateway recipe : BotanicalWorkshopAPI.gatewayRecipes)
					if(recipe.getCatalyst().isItemEqual(item.getEntityItem()))
						if(recipe.matches(currentInventory, false) && !worldObj.isRemote){
							recipe.matches(currentInventory, true);						
							summonItem(recipe.getOutput().copy());
							return true;
						}
				if(item.getEntityItem().getItem() instanceof IGatewayMod){
					((IGatewayMod) item.getEntityItem().getItem()).onGatewayUpdate(this, item);
				}
			}
		return false;

	}
	
	public boolean addMana(int amount){
		int totalMana = getCurrentMana();
		if(-amount < (totalMana - 64)){
			for(int[] pos : ELVEN_POOL_POSITIONS) {
				TileEntity tile = worldObj.getTileEntity(xCoord + pos[0], yCoord + pos[1], zCoord + pos[2]);
				if(tile instanceof TileElvenPool) {
					TileElvenPool pool = (TileElvenPool) tile;
					double costRatio = Math.abs(amount) == amount ? ((double) pool.getCurrentMana() * TileElvenPool.MAX_MANA)/ ((double) totalMana * TileElvenPool.MAX_MANA * ELVEN_POOL_POSITIONS.length) : (double) pool.getCurrentMana() / (double) totalMana ; //What percent does a small pool get when adding mana?
					//If cost ratio is positive give small pools more; If negative, take more from large pools
					if(!worldObj.isRemote)
						pool.recieveMana((int)Math.round(amount * costRatio));
				}
			}
			return true;
		}
		return false;
	}
	
	public int getCurrentMana(){
		int totalMana = 0;
		for(int[] pos : ELVEN_POOL_POSITIONS) {
			TileEntity tile = worldObj.getTileEntity(xCoord + pos[0], yCoord + pos[1], zCoord + pos[2]);
			if(tile instanceof TileElvenPool) {
				TileElvenPool pool = (TileElvenPool) tile;
				totalMana+=pool.getCurrentMana();
			}
		}
		return totalMana;
	}
	
	public void renderHUD(Minecraft mc, ScaledResolution res) {
		String name = StatCollector.translateToLocal(new ItemStack(ModBlocks.gatewayCore, 1, getBlockMetadata()).getUnlocalizedName().replaceAll("tile.", "tile." + LibResources.PREFIX_MOD) + ".name");
		int color = 0xB6F2B7; //Offical color of the elves
		HUDHandler.drawSimpleManaHUD(color, getCurrentMana(), TileElvenPool.MAX_MANA * 8, name, res);

		String catalysts = "";
		
		List<EntityItem> items = worldObj.getEntitiesWithinAABB(EntityItem.class, getPortalAABB());
		for(EntityItem item : items)
			if(item.getEntityItem().getItem() instanceof IGatewayCatalyst)
				catalysts = catalysts + ", " + StatCollector.translateToLocal(item.getEntityItem().getUnlocalizedName().replaceAll("item.", "item." + LibResources.PREFIX_MOD) + ".name");
		
		if(catalysts.length() >= 2) //Avoid index out of range on no catalysts
			catalysts = catalysts.substring(2); //Skip Initial ", "
		
		int x = res.getScaledWidth() / 2 - mc.fontRenderer.getStringWidth(catalysts) / 2;
		int y = res.getScaledHeight() / 2 + 30;
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		mc.fontRenderer.drawStringWithShadow(catalysts, x, y, color);
		GL11.glDisable(GL11.GL_BLEND);
	}
	
	AxisAlignedBB getPortalAABB() {
		AxisAlignedBB aabb = AxisAlignedBB.getBoundingBox(xCoord - 1, yCoord + 1, zCoord - 1, xCoord + 2, yCoord + 6, zCoord + 2);
		return aabb;
	}
	
}