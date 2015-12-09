package lazersmoke.botanicalworkshop.api.internal;

import java.util.List;

import lazersmoke.botanicalworkshop.api.mana.TileSignature;
import lazersmoke.botanicalworkshop.api.mana.lightning.IBotanicalLightningBlock;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;

/**
 * A basic interface for a world's Lightning Network.
 */
public interface ILightningNetwork {
	List<TileSignature> getAllLightningBlocksInWorld(World world);
	IBotanicalLightningBlock getClosestLightningBlock(ChunkCoordinates pos, World world, int limit);
}