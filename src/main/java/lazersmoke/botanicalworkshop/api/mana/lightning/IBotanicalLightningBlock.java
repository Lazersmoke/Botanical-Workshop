package lazersmoke.botanicalworkshop.api.mana.lightning;

import net.minecraft.util.ChunkCoordinates;


/**
 * Any class implementing this interface will be considered a Gateway Networked block and will automatically connect to the nearest gateway for its mana needs
 * 
 * @author Lazersmoke
 *
 * <b>Implementation Instructions:</b><br>
 * - Override invalidate() and onChunkUnload(), calling <i>LightningNetworkEvent.removeBlock(this);</i> on both.<br>
 * - On the first tick of onUpdate(), call <i>LightningNetworkEvent.addBlock(this);</i>
 */

public interface IBotanicalLightningBlock{
	int getConductivity();
	int getCurrentLightning();
	//Returns true if it had enough
	boolean addLightning(int amount);
	//Returns how much was used
	int blindAddLightning(int amount);
	//Make it go boom (also set lightning to 0 in here)
	void overflow();
	ChunkCoordinates getPos();
}