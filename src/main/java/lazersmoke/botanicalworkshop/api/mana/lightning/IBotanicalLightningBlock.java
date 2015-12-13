package lazersmoke.botanicalworkshop.api.mana.lightning;

import net.minecraft.util.ChunkCoordinates;
import vazkii.botania.common.core.helper.Vector3;


/**
 * Any class implementing this interface will be considered a Lightning Block and will be updated and connected to the lightning network. Lightning Blocks with less conductivity give lightning to Lightning Blocks with more conductivity.
 *
 * <b>Implementation Instructions:</b><br>
 * - Override invalidate() and onChunkUnload(), calling <i>LightningNetworkEvent.removeBlock(this);</i> on both.<br>
 * - On the first tick of onUpdate(), call <i>LightningNetworkEvent.addBlock(this);</i>
 * - On every tick of onUpdate(), call <i>LightningNetworkEvent.tickBlock(this);</i> in order to make this block work with the network (Don't *not* call it ever)
 * 
 * @author Lazersmoke
 */
public interface IBotanicalLightningBlock{
	/**
	 * Returns current conductivity. Ususally this is a function of current lightning and thresholds. Usually small (1-10)ish.
	 * @return
	 * Current conductivity
	 */
	int getConductivity();
	/**
	 * Returns current lightning
	 * @return
	 * Current lightning
	 */
	int getCurrentLightning();
	/**
	 * Adds lightning to the block. Given negative values, will remove that much if possible. If it can't it returns false.
	 * 
	 * Example implementation:
	 * 	boolean addLightning(int amount){
	 * 		return blindAddLightning(amount) == amount;
	 * 	}
	 * 
	 * @param amount
	 * Amount to add
	 * @return
	 * If the lightning was successfully added/removed
	 */
	boolean addLightning(int amount);
	/**
	 * Blindly adds lightning to the block. Given negative values, will remove that much, and returns acutual net change.
	 * Do something like this in here after updating lightning:
	 *	worldObj.func_147453_f(xCoord, yCoord, zCoord, worldObj.getBlock(xCoord, yCoord, zCoord));
	 *	worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
	 *	markDirty(); 
	 * @param amount
	 * Amount to add
	 * @return
	 * Actual net change
	 */
	int blindAddLightning(int amount);
	/**
	 * Makes the block "overflow" somehow. Make sure you set lightning to 0.
	 */
	void overflow();
	/**
	 * Returns this blocks chunk coordinates
	 * @return
	 * This blocks chunk coordinates
	 */
	ChunkCoordinates getPos();
	/**
	 * Gets the minumum amount of lightning needed to stay powered (on).
	 * @return
	 * Power Threshold
	 */
	int getPowerThreshold();
	/**
	 * Gets the minumum amount of lightning needed to become powered (on).
	 * @return
	 * Buffer Threshold
	 */
	int getBufferThreshold();
	/**
	 * Gets the amount of lightning needed to overflow (go boom!).
	 * @return
	 * Overflow Threshold
	 */
	int getOverflowThreshold();
	/**
	 * Gets the offset of the point to render lightning effects at.
	 * @return
	 * lightning render offset as a 3 element float array
	 */
	Vector3 getLightningRenderOffset();
	/**
	 * Returns maximum range to disperse lightning to.
	 * When in doubt, use 7
	 * @return
	 * Lightning Push Range
	 */
	int getLightningPushRange();
}