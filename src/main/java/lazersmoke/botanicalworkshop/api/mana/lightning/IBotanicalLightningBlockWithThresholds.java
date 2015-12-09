package lazersmoke.botanicalworkshop.api.mana.lightning;

/**
 * Any class implementing this interface will be considered a Gateway Networked block and will automatically connect to the nearest gateway for its mana needs
 * 
 * @author Lazersmoke
 *
 */

public interface IBotanicalLightningBlockWithThresholds extends IBotanicalLightningBlock{
	int getPowerThreshold();
	int getBufferThreshold();
	int getOverflowThreshold();
}