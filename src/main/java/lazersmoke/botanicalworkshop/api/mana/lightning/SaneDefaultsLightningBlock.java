package lazersmoke.botanicalworkshop.api.mana.lightning;

import lazersmoke.botanicalworkshop.api.mana.LightningNetworkEvent;
import lazersmoke.botanicalworkshop.client.core.handler.HUDHandler;
import lazersmoke.botanicalworkshop.client.lib.LibResources;
import lazersmoke.botanicalworkshop.common.block.tile.TileMod;
import lazersmoke.botanicalworkshop.common.core.handler.LightningNetworkHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.StatCollector;
import vazkii.botania.common.core.helper.Vector3;

/**
 * This class contains a sane default implementation of an IBotanicalLightningBlock. See classes in Botanical Workshop that extend "TileModLightning" to see an implementation of this.<br>
 * <br>
 * NOTE: this is an abstract class, so you are left to fill in the blanks per block. However, the blanks you need to fill are usually different for each block.
 *
 * @author Lazersmoke
 */
public abstract class SaneDefaultsLightningBlock extends TileMod implements IBotanicalLightningBlock{
	protected static final String TAG_LIGHTNING = "botanicalLightning";
	protected static final String TAG_STATE = "botanicalState";

	private int lightning = 0;

	private boolean state;

	protected boolean getState(){// Lazy state retrival
		if(!state && getCurrentLightning() >= getBufferThreshold())// If we are off but can turn on, turn on
			state = true;
		if(state && getCurrentLightning() < getPowerThreshold())// If we are on but can't stay on, turn off
			state = false;
		return state;
	}

	public void renderHUD(Minecraft mc, ScaledResolution res){
		HUDHandler.drawSimpleLightningHUD(0xFF00AE, getCurrentLightning(), getPowerThreshold(), getBufferThreshold(), getOverflowThreshold(), StatCollector.translateToLocal(worldObj.getBlock(xCoord, yCoord, zCoord).getUnlocalizedName().replaceAll("tile.", "tile." + LibResources.PREFIX_MOD) + ".name"), res);
	}

	@Override
	public void updateEntity(){
		super.updateEntity();
		if(worldObj.isRemote)
			return;
		if(!LightningNetworkHandler.instance.isBlockIn(this) && !isInvalid())// Brute force add to the network
			LightningNetworkEvent.addBlock(this);
		LightningNetworkEvent.tickBlock(this);// Every tick, tick this in the lightning network VERY IMPORTANT
	}

	@Override
	public void invalidate(){
		super.invalidate();
		LightningNetworkEvent.removeBlock(this);// Get out of network if invalid
	}

	@Override
	public void onChunkUnload(){
		super.onChunkUnload();
		invalidate();// This also removes from the network
	}

	@Override
	public int getCurrentLightning(){
		return lightning;// simple
	}

	@Override
	public boolean addLightning(int amount){
		if(-amount <= getCurrentLightning())// -amount is big if we remove a lot of lightning
			return blindAddLightning(amount) == amount;// This always returns true because of the check we just did, so return true
		else
			return false;
	}

	// Returns acutal change
	@Override
	public int blindAddLightning(int amount){
		if(lightning >= -amount){
			lightning += amount;
			// These functions fixed a really weird desync bug, but I don't know what they do
			worldObj.func_147453_f(xCoord, yCoord, zCoord, worldObj.getBlock(xCoord, yCoord, zCoord));
			worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
			markDirty();
			return amount;
		}else{
			final int change = -lightning;// Used all of it
			lightning = 0;
			// These functions fixed a really weird desync bug, but I don't know what they do
			worldObj.func_147453_f(xCoord, yCoord, zCoord, worldObj.getBlock(xCoord, yCoord, zCoord));
			worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
			markDirty();
			return change;
		}
	}

	@Override
	public void writeCustomNBT(NBTTagCompound cmp){// Save to disk
		super.writeCustomNBT(cmp);
		cmp.setInteger(TAG_LIGHTNING, lightning);
		cmp.setBoolean(TAG_STATE, state);
	}

	@Override
	public void readCustomNBT(NBTTagCompound cmp){// Load from disk
		super.readCustomNBT(cmp);
		lightning = cmp.getInteger(TAG_LIGHTNING);
		state = cmp.getBoolean(TAG_STATE);
	}

	@Override
	public ChunkCoordinates getPos(){// simple
		return new ChunkCoordinates(xCoord, yCoord, zCoord);
	}

	@Override
	public Vector3 getLightningRenderOffset(){
		return new Vector3(0.5F, 1.16F, 0.5F);// Defaults to 1/16th of a block above the center of the top face; override to change
	}

	@Override
	public int getLightningPushRange(){
		return 7;// This is the standard default range
	}

	@Override
	public void overflow(){// Blow ourselves up, but nothing else
		worldObj.spawnParticle("hugeexplosion", xCoord + 0.5F, yCoord + 0.5F, zCoord + 0.5F, 1.0D, 0.0D, 0.0D);
		worldObj.playSoundEffect(xCoord + 0.5F, yCoord + 0.5F, zCoord + 0.5F, "random.explode", 1F, 0.7F);
		worldObj.setBlockToAir(xCoord, yCoord, zCoord);
	}
}
