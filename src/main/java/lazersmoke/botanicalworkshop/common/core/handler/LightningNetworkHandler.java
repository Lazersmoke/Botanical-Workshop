package lazersmoke.botanicalworkshop.common.core.handler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

import lazersmoke.botanicalworkshop.api.internal.ILightningNetwork;
import lazersmoke.botanicalworkshop.api.mana.LightningNetworkEvent;
import lazersmoke.botanicalworkshop.api.mana.LightningNetworkEvent.Action;
import lazersmoke.botanicalworkshop.api.mana.TileSignature;
import lazersmoke.botanicalworkshop.api.mana.lightning.IBotanicalLightningBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import vazkii.botania.common.core.helper.MathHelper;
import vazkii.botania.common.core.helper.Vector3;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class LightningNetworkHandler implements ILightningNetwork{

	public static final LightningNetworkHandler instance = new LightningNetworkHandler();

	private static final int MAX_LIGHTNING_TRANSFER_RATE = 10;

	public WeakHashMap<World, List<TileSignature>> lightningBlocks = new WeakHashMap<World, List<TileSignature>>();

	@SubscribeEvent
	public void onNetworkEvent(LightningNetworkEvent event){
		if(event.action == Action.ADD)
			add(lightningBlocks, event.tile);
		else if(event.action == Action.REMOVE)
			remove(lightningBlocks, event.tile);
		else if(event.action == Action.TICK){

			if(event.tile.getWorldObj().isRemote)
				return;

			final IBotanicalLightningBlock theBlock = (IBotanicalLightningBlock) event.tile;

			if(theBlock.getCurrentLightning() > theBlock.getOverflowThreshold())
				theBlock.overflow();

			final List<IBotanicalLightningBlock> blocksAround = getClosestLightningBlocks(theBlock.getPos(), event.tile.getWorldObj(), theBlock.getLightningPushRange());
			Collections.shuffle(blocksAround);// To ensure no shenanigans!
			//@formatter:off
			for(final IBotanicalLightningBlock currBlock : blocksAround)
				if(currBlock != theBlock && currBlock.getConductivity() > theBlock.getConductivity() && currBlock.getConductivity() != -1){// -1 is special case for generators which should never take in lightning, even from other generators.
					final int amountExchanged =
					theBlock.blindAddLightning(// subtract from source
						1 - currBlock.blindAddLightning(// add to target
							Math.min(// The actual amount to move
								MAX_LIGHTNING_TRANSFER_RATE,
								theBlock.getCurrentLightning()
							) - 1
						)
					);
					if(Math.abs(amountExchanged) > MAX_LIGHTNING_TRANSFER_RATE - 1)
						makeFancies((TileEntity) theBlock, (TileEntity) currBlock);
				}
			//@formatter:on
		}
	}

	private void makeFancies(TileEntity start, TileEntity end){
		start.getWorldObj().playSoundEffect(start.xCoord + ((IBotanicalLightningBlock) start).getLightningRenderOffset().x, start.yCoord + ((IBotanicalLightningBlock) start).getLightningRenderOffset().y, start.zCoord + ((IBotanicalLightningBlock) start).getLightningRenderOffset().z, "ambient.weather.thunder", 0.2F, (float) ((Math.random() / 2) + 0.25F));
		final Vector3 tileStartVec = Vector3.fromTileEntity(start).add(((IBotanicalLightningBlock) start).getLightningRenderOffset());
		final Vector3 tileEndVec = Vector3.fromTileEntity(end).add(((IBotanicalLightningBlock) end).getLightningRenderOffset());
		vazkii.botania.client.core.handler.LightningHandler.spawnLightningBolt(start.getWorldObj(), tileStartVec, tileEndVec, 1F / 7F, start.getWorldObj().rand.nextLong(), 0x4400799c, 0x4400C6FF);
	}

	private List<IBotanicalLightningBlock> getClosestLightningBlocks(ChunkCoordinates pos, World world, int limit){
		return getClosest(lightningBlocks.get(world), pos, world.isRemote, limit);
	}

	@Override
	public IBotanicalLightningBlock getClosestLightningBlock(ChunkCoordinates pos, World world, int limit){
		if(lightningBlocks.containsKey(world))
			return getClosest(lightningBlocks.get(world), pos, world.isRemote, limit).get(0);
		return null;
	}

	private synchronized List<IBotanicalLightningBlock> getClosest(List<TileSignature> tiles, ChunkCoordinates pos, boolean remoteCheck, int limit){
		final List<IBotanicalLightningBlock> outputTiles = new ArrayList<IBotanicalLightningBlock>();
		final List<TileSignature> outputSigs = new ArrayList<TileSignature>();
		while(true){
			final float closest = Float.MAX_VALUE;
			final List<TileSignature> lastOutputSigs = new ArrayList<TileSignature>(outputSigs);

			for(final TileSignature sig : tiles){
				if(sig.remoteWorld != remoteCheck)
					continue;// the for loop idk what this is for xD

				final TileEntity tile = sig.tile;
				if(tile.isInvalid())
					continue;// the for loop if the tile is invalid
				final float distance = MathHelper.pointDistanceSpace(tile.xCoord, tile.yCoord, tile.zCoord, pos.posX, pos.posY, pos.posZ);

				if(distance > limit)
					continue;// the for loop if it is out of our range

				if(distance < closest && !outputSigs.contains(sig)){
					outputTiles.add((IBotanicalLightningBlock) tile);
					outputSigs.add(sig);
					break;// the for loop if we found the next closest one
				}
			}
			if(outputSigs.size() == lastOutputSigs.size())// There was not a new one added
				break; // the while loop
		}

		return outputTiles;
	}

	private synchronized void remove(Map<World, List<TileSignature>> map, TileEntity tile){
		final World world = tile.getWorldObj();

		if(!map.containsKey(world))
			return;

		final List<TileSignature> sigs = map.get(world);
		for(final TileSignature sig : sigs)
			if(sig.tile.equals(tile)){
				sigs.remove(sig);
				break;
			}
	}

	private synchronized void add(Map<World, List<TileSignature>> map, TileEntity tile){
		final World world = tile.getWorldObj();

		List<TileSignature> tiles;
		if(!map.containsKey(world))
			map.put(world, new ArrayList<TileSignature>());

		tiles = map.get(world);

		if(!tiles.contains(tile))
			tiles.add(new TileSignature(tile, tile.getWorldObj().isRemote));
	}

	@Override
	public List<TileSignature> getAllLightningBlocksInWorld(World world){
		return getAllInWorld(lightningBlocks, world);
	}

	private List<TileSignature> getAllInWorld(Map<World, List<TileSignature>> map, World world){
		if(!map.containsKey(world))
			return new ArrayList<TileSignature>();
		return map.get(world);
	}

	public boolean isBlockIn(TileEntity tile){
		final List<TileSignature> list = lightningBlocks.get(tile.getWorldObj());
		if(list == null)
			return false;

		for(final TileSignature sig : list)
			if(sig.tile == tile)
				return true;

		return false;
	}
}
