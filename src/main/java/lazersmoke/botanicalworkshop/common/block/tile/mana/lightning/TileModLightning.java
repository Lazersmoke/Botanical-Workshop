package lazersmoke.botanicalworkshop.common.block.tile.mana.lightning;

import vazkii.botania.common.core.helper.Vector3;
import lazersmoke.botanicalworkshop.api.mana.LightningNetworkEvent;
import lazersmoke.botanicalworkshop.api.mana.lightning.IBotanicalLightningBlock;
import lazersmoke.botanicalworkshop.client.core.handler.HUDHandler;
import lazersmoke.botanicalworkshop.common.block.tile.TileMod;
import lazersmoke.botanicalworkshop.common.core.handler.LightningNetworkHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChunkCoordinates;

public abstract class TileModLightning extends TileMod implements IBotanicalLightningBlock{
	protected static final String TAG_LIGHTNING = "botanicalLightning";
	
	private int lightning = 0;
	
	protected boolean state;
	protected boolean getState(){
		if(!state && getCurrentLightning() >= getBufferThreshold())
			state = true;
		if(state && getCurrentLightning() < getPowerThreshold())
			state = false;
		return state;
	}
	public void renderHUD(Minecraft mc, ScaledResolution res) {
		HUDHandler.drawSimpleLightningHUD(0xFF00AE, getCurrentLightning(), getPowerThreshold(), getBufferThreshold(), getOverflowThreshold(), "meow", res);
	}
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
	public int getCurrentLightning() {
		return lightning;
	}
	@Override
	public boolean addLightning(int amount) {
		if(-amount <= getCurrentLightning()) return blindAddLightning(amount) == amount;
		else return false;
	}
	
	//Returns acutal change
	@Override
	public int blindAddLightning(int amount) {
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
	public Vector3 getLightningRenderOffset(){
		return new Vector3(0.5F, 1.16F, 0.5F);
	}
}
