package lazersmoke.botanicalworkshop.api.internal;

import java.util.List;

import lazersmoke.botanicalworkshop.api.mana.TileSignature;
import lazersmoke.botanicalworkshop.api.mana.lightning.IBotanicalLightningBlock;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;

/**
 * A basic interface for a world's Lightning Network.
 */
public interface ILightningNetwork{
	/**
	 * Returns a list of all lightning blocks in a given world
	 * 
	 * @param world
	 * The world to search for Lightning Blocks
	 * @return List containing all the Lightning Blocks in the specified world, as TileSignatures
	 */
	List<TileSignature> getAllLightningBlocksInWorld(World world);

	/**
	 * Returns the closest Lightning Block to the given coordinates in the given world in the given range
	 * 
	 * @param pos
	 * Position to search near
	 * @param world
	 * World to search in
	 * @param range
	 * Search radius
	 * @return Nearest Lightning Block as an IBotanicalLightningBlock
	 */
	IBotanicalLightningBlock getClosestLightningBlock(ChunkCoordinates pos, World world, int range);
}