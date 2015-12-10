package lazersmoke.botanicalworkshop.common.block.tile.mana.lightning;

import lazersmoke.botanicalworkshop.api.mana.LightningNetworkEvent;
import lazersmoke.botanicalworkshop.api.mana.lightning.IBotanicalLightningBlock;
import lazersmoke.botanicalworkshop.common.BotanicalWorkshop;
import lazersmoke.botanicalworkshop.common.block.tile.TileMod;
import lazersmoke.botanicalworkshop.common.core.handler.LightningNetworkHandler;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChunkCoordinates;

import org.apache.logging.log4j.Level;

public class TileModLightning extends TileMod implements IBotanicalLightningBlock{
	private int lightning = 0;
	protected static final String TAG_LIGHTNING = "botanicalLightning";
	
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
		//BotanicalWorkshop.logger.log(Level.INFO, "Adding Lightning: " + amount + " to block at " + this.xCoord + ", " + this.yCoord + ", " + this.zCoord);
		if(-amount <= getCurrentLightning()) return blindAddLightning(amount) == amount;
		else return false;
	}
	
	//Returns acutal change
	@Override
	public int blindAddLightning(int amount) {
		BotanicalWorkshop.logger.log(Level.INFO, "Blindly Adding Lightning: " + amount + " to block at " + this.xCoord + ", " + this.yCoord + ", " + this.zCoord);
		if(lightning >= -amount){
			lightning += amount;
			worldObj.func_147453_f(xCoord, yCoord, zCoord, worldObj.getBlock(xCoord, yCoord, zCoord));
			worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
			markDirty(); 
			return amount;
		}
		else{
			int change = -lightning;
			lightning = 0;
			worldObj.func_147453_f(xCoord, yCoord, zCoord, worldObj.getBlock(xCoord, yCoord, zCoord));
			worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
			markDirty(); 
			return change;
		}
	}
	@Override
	public void writeCustomNBT(NBTTagCompound cmp) {
		super.writeCustomNBT(cmp);
		cmp.setInteger(TAG_LIGHTNING, lightning);
	}
	
	@Override
	public void readCustomNBT(NBTTagCompound cmp) {
		super.readCustomNBT(cmp);
		lightning = cmp.getInteger(TAG_LIGHTNING);
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
