package lazersmoke.botanicalworkshop.common.core.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

import org.apache.logging.log4j.Level;

import lazersmoke.botanicalworkshop.api.internal.ILightningNetwork;
import lazersmoke.botanicalworkshop.api.mana.LightningNetworkEvent;
import lazersmoke.botanicalworkshop.api.mana.LightningNetworkEvent.Action;
import lazersmoke.botanicalworkshop.api.mana.TileSignature;
import lazersmoke.botanicalworkshop.api.mana.lightning.IBotanicalLightningBlock;
import lazersmoke.botanicalworkshop.common.BotanicalWorkshop;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import vazkii.botania.common.core.helper.MathHelper;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class LightningNetworkHandler implements ILightningNetwork{
	
	public static final LightningNetworkHandler instance = new LightningNetworkHandler();
	
	private static final int LIGHTNING_RANGE = 7;
	private static final int MAX_LIGHTNING_TRANSFER_RATE = 10;
	
	public WeakHashMap<World, List<TileSignature>> lightningBlocks = new WeakHashMap<World, List<TileSignature>>();
	
	@SubscribeEvent
	public void onNetworkEvent(LightningNetworkEvent event) {//TODO make LR work
		if(event.action == Action.ADD) 			add(lightningBlocks, event.tile);
		else if(event.action == Action.REMOVE) 	remove(lightningBlocks, event.tile);
		else if(event.action == Action.TICK){
			IBotanicalLightningBlock theBlock = (IBotanicalLightningBlock) event.tile;
			List<IBotanicalLightningBlock> blocksAround = getClosestXLightningBlocks(theBlock.getPos(), event.tile.getWorldObj(), LIGHTNING_RANGE, 4);
			for(IBotanicalLightningBlock currBlock : blocksAround)
				if(currBlock != theBlock && currBlock.getConductivity() >= theBlock.getConductivity())
						if(theBlock.blindAddLightning(-currBlock.blindAddLightning(Math.min(MAX_LIGHTNING_TRANSFER_RATE, theBlock.getCurrentLightning()))) != 0)
							makeFancies(event.tile.getWorldObj(), theBlock.getPos(), currBlock.getPos());
		}
	}
	private void makeFancies(World world, ChunkCoordinates start, ChunkCoordinates end){//TODO make fancier with lightning and sound
		BotanicalWorkshop.logger.log(Level.INFO, "Making Fancies!");
		if (!world.isRemote){
		world.spawnParticle( "happyVillager", 
		          start.posX, 
		          start.posY, 
		          start.posZ, 
		          end.posX - start.posX, 
		          end.posY - start.posY, 
		          end.posZ - start.posZ);
		}
	}
	private List<IBotanicalLightningBlock> getClosestXLightningBlocks(ChunkCoordinates pos, World world, int limit, int count){
		return getClosestX(lightningBlocks.get(world), pos, world.isRemote, limit, count);
	}
	@Override
	public IBotanicalLightningBlock getClosestLightningBlock(ChunkCoordinates pos, World world, int limit) {
		if(lightningBlocks.containsKey(world))
			return getClosestX(lightningBlocks.get(world), pos, world.isRemote, limit, 1).get(0);
		return null;
	}
	private synchronized List<IBotanicalLightningBlock> getClosestX(List<TileSignature> tiles, ChunkCoordinates pos, boolean remoteCheck, int limit, int count) {
		List<IBotanicalLightningBlock> outputTiles = new ArrayList<IBotanicalLightningBlock>();
		List<TileSignature> outputSigs = new ArrayList<TileSignature>();
		for(int x = 0; x < count; x++){
			float closest = Float.MAX_VALUE;
			
			for(TileSignature sig : tiles) {
				if(sig.remoteWorld != remoteCheck)
					continue;
	
				TileEntity tile = sig.tile;
				if(tile.isInvalid())
					continue;
				float distance = MathHelper.pointDistanceSpace(tile.xCoord, tile.yCoord, tile.zCoord, pos.posX, pos.posY, pos.posZ);
				if(distance > limit)
					continue;
	
				if(distance < closest && !outputSigs.contains(sig)) {
					outputTiles.add((IBotanicalLightningBlock) tile);
					outputSigs.add(sig);
					break;
				}
			}
		}
		
		return outputTiles;
	}
	private synchronized void remove(Map<World, List<TileSignature>> map, TileEntity tile) {
		World world = tile.getWorldObj();

		if(!map.containsKey(world))
			return;

		List<TileSignature> sigs = map.get(world);
		for(TileSignature sig : sigs)
			if(sig.tile.equals(tile)) {
				sigs.remove(sig);
				break;
			}
	}
	private synchronized void add(Map<World, List<TileSignature>> map, TileEntity tile) {
		World world = tile.getWorldObj();

		List<TileSignature> tiles;
		if(!map.containsKey(world))
			map.put(world, new ArrayList<TileSignature>());

		tiles = map.get(world);

		if(!tiles.contains(tile))
			tiles.add(new TileSignature(tile, tile.getWorldObj().isRemote));
	}
	@Override
	public List<TileSignature> getAllLightningBlocksInWorld(World world) {
		return getAllInWorld(lightningBlocks, world);
	}
	private List<TileSignature> getAllInWorld(Map<World, List<TileSignature>> map, World world) {
		if(!map.containsKey(world))
			return new ArrayList<TileSignature>();
		return map.get(world);
	}
	public boolean isBlockIn(TileEntity tile) {
		List<TileSignature> list = lightningBlocks.get(tile.getWorldObj());
		if(list == null)
			return false;

		for(TileSignature sig : list)
			if(sig.tile == tile)
				return true;

		return false;
	}
}

