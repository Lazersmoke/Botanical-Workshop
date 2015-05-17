package lazersmoke.botanicalworkshop.common.block.tile;
//This class is %50 Lazersmoke
import lazersmoke.botanicalworkshop.common.BotanicalWorkshop;
import lazersmoke.botanicalworkshop.common.block.ModBlocks;
import lazersmoke.botanicalworkshop.common.block.tile.mana.TileElvenPool;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

import vazkii.botania.common.block.tile.TileMod;

public class TileSuperGatewayCore extends TileMod{
		//  EDE
		// Q   Q
		//E     E
		//D     D
		//E     E
		// Q   Q 
		//  ECE 
	public static final int MAX_MANA = 10000;
	public int mana = 0;
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
		int updatedMeta = getValidMetadata();
		
		if(!hasUnloadedParts){
			ticksOpen++;
			AxisAlignedBB aabb = getPortalAABB();
			boolean open = ticksOpen > 60;

			if(ticksOpen > 60) {
				if(vazkii.botania.common.core.handler.ConfigHandler.elfPortalParticlesEnabled)
					blockParticle(meta);
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
		BotanicalWorkshop.proxy.wispFX(getWorldObj(), xCoord + pos[0], yCoord + pos[1], zCoord + pos[2], (float) (Math.random() * 0.25F), (float) (Math.random() * 0.5F + 0.5F), (float) (Math.random() * 0.25F), (float) (Math.random() * 0.15F + 0.1F), (float) (Math.random() - 0.5F) * motionMul, (float) (Math.random() - 0.5F) * motionMul, (float) (Math.random() - 0.5F) * motionMul);
	}

	private int getValidMetadata() {
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
			int newMeta = getValidMetadata();
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

		int cost = ticksOpen == 50 ? 1000000 : 1000;//one time cost (one pool, 1/8 from each) : every tick afterward
		int totalMana = 0;
		
		for(int[] pos : ELVEN_POOL_POSITIONS) {
			TileEntity tile = worldObj.getTileEntity(xCoord + pos[0], yCoord + pos[1], zCoord + pos[2]);
			if(tile instanceof TileElvenPool) {
				TileElvenPool pool = (TileElvenPool) tile;
				totalMana+=pool.getCurrentMana();
			}
		}
		if(cost < (totalMana - 64)){
			for(int[] pos : ELVEN_POOL_POSITIONS) {
				TileEntity tile = worldObj.getTileEntity(xCoord + pos[0], yCoord + pos[1], zCoord + pos[2]);
				if(tile instanceof TileElvenPool) {
					TileElvenPool pool = (TileElvenPool) tile;
					double costRatio = (double) pool.getCurrentMana() / (double) totalMana;
					pool.recieveMana((int)Math.round(-cost * costRatio));
				}
			}
		}else closeNow = true;
	}
	
	AxisAlignedBB getPortalAABB() {
		AxisAlignedBB aabb = AxisAlignedBB.getBoundingBox(xCoord - 1, yCoord + 1, zCoord - 1, xCoord + 1, yCoord + 6, zCoord + 1);
		return aabb;
	}
	
}