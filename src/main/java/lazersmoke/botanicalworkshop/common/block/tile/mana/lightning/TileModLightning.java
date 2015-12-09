package lazersmoke.botanicalworkshop.common.block.tile.mana.lightning;

import org.apache.logging.log4j.Level;

import lazersmoke.botanicalworkshop.api.mana.LightningNetworkEvent;
import lazersmoke.botanicalworkshop.api.mana.lightning.IBotanicalLightningBlock;
import lazersmoke.botanicalworkshop.common.BotanicalWorkshop;
import lazersmoke.botanicalworkshop.common.block.tile.TileMod;
import lazersmoke.botanicalworkshop.common.core.handler.LightningNetworkHandler;
import net.minecraft.util.ChunkCoordinates;

public class TileModLightning extends TileMod implements IBotanicalLightningBlock{
	private int lightning = 0;
	
	@Override
	public void updateEntity(){
		super.updateEntity();
		if(!LightningNetworkHandler.instance.isBlockIn(this) && !isInvalid())
			LightningNetworkEvent.addBlock(this);
		LightningNetworkEvent.tickBlock(this);
	}
	@Override
	public void invalidate(){
		super.invalidate();
		LightningNetworkEvent.removeBlock(this);
	}
	@Override
	public void onChunkUnload(){
		super.onChunkUnload();
		invalidate();
	}
	@Override
	public int getConductivity() {
		return 1;
	}
	@Override
	public int getCurrentLightning() {
		return lightning;
	}
	@Override
	public boolean addLightning(int amount) {
		BotanicalWorkshop.logger.log(Level.INFO, "Adding Lightning: " + amount + " to block at " + this.xCoord + ", " + this.yCoord + ", " + this.zCoord);
		if(-amount <= getCurrentLightning()) return blindAddLightning(amount) == amount;
		else return false;
	}
	
	//Returns acutal change
	@Override
	public int blindAddLightning(int amount) {
		BotanicalWorkshop.logger.log(Level.INFO, "Blindly Adding Lightning: " + amount + " to block at " + this.xCoord + ", " + this.yCoord + ", " + this.zCoord);
		if(lightning >= -amount){
			lightning += amount;
			return amount;
		}
		else{
			int change = -lightning;
			lightning = 0;
			return change;
		}
	}
	@Override
	public ChunkCoordinates getPos() {
		return new ChunkCoordinates(xCoord, yCoord, zCoord);
	}
	@Override
	public void overflow() {
		// NO-OP
	}

}
