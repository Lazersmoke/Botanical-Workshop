package lazersmoke.botanicalworkshop.common.block.tile.mana.lightning;

import java.util.List;

import org.apache.logging.log4j.Level;

import lazersmoke.botanicalworkshop.api.mana.lightning.IBotanicalLightningBlockWithThresholds;
import lazersmoke.botanicalworkshop.client.core.handler.HUDHandler;
import lazersmoke.botanicalworkshop.common.BotanicalWorkshop;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;
import vazkii.botania.api.wand.IWandBindable;

public class TileThaumtanicalTransposer extends TileModLightning implements IBotanicalLightningBlockWithThresholds, IWandBindable{
	private int powerThreshold = 100;
	private int bufferThreshold = 150;
	private int overflowThreshold = 300;
	
	private int bindX, bindY, bindZ;
	private static final String TAG_BIND_X = "bindGatewayX";
	private static final String TAG_BIND_Y = "bindGatewayY";
	private static final String TAG_BIND_Z = "bindGatewayZ";
	
	@Override
	public void updateEntity(){
		super.updateEntity();
		BotanicalWorkshop.logger.log(Level.INFO, "getState is " + getState());
		BotanicalWorkshop.logger.log(Level.INFO, "getCurrentLightning is " + getCurrentLightning());
		if(getState() && !worldObj.isRemote){
			List<EntityItem> items = (List<EntityItem>) worldObj.getEntitiesWithinAABB(EntityItem.class, getActiveAABB());
			BotanicalWorkshop.logger.log(Level.INFO, "items.size() is " + items.size());
			if(!items.isEmpty() && addLightning(-50)){
				BotanicalWorkshop.logger.log(Level.INFO, "TP Item");
				items.get(0).setPosition(bindX + 0.5F, bindY + 1.5F, bindZ + 0.5F);
				items.get(0).motionX = 0;
				items.get(0).motionY = 0;
				items.get(0).motionZ = 0;
			}
		}
		if(getCurrentLightning() > getOverflowThreshold())
			overflow();
	}
	private boolean state;
	private boolean getState(){
		if(!state && getCurrentLightning() >= getBufferThreshold())
			state = true;
		if(state && getCurrentLightning() < getPowerThreshold())
			state = false;
		return state;
	}
	@Override
	public void writeCustomNBT(NBTTagCompound cmp) {
		super.writeCustomNBT(cmp);
		cmp.setInteger(TAG_BIND_X, bindX);
		cmp.setInteger(TAG_BIND_Y, bindY);
		cmp.setInteger(TAG_BIND_Z, bindZ);
	}
	
	@Override
	public void readCustomNBT(NBTTagCompound cmp) {
		super.readCustomNBT(cmp);
		bindX = cmp.getInteger(TAG_BIND_X);
		bindY = cmp.getInteger(TAG_BIND_Y);
		bindZ = cmp.getInteger(TAG_BIND_Z);
	}
	
	public void renderHUD(Minecraft mc, ScaledResolution res) {
		HUDHandler.drawSimpleLightningHUD(0xFF00AE, getCurrentLightning(), powerThreshold, bufferThreshold, overflowThreshold, "meow", res);
	}
	public boolean onWanded(){
		return addLightning(50);
	}
	private AxisAlignedBB getActiveAABB() {
		return AxisAlignedBB.getBoundingBox(xCoord, yCoord + 1, zCoord, xCoord + 1, yCoord + 2, zCoord + 1);
	}
	@Override
	public int getPowerThreshold() {
		return powerThreshold;
	}
	@Override
	public int getBufferThreshold() {
		return bufferThreshold;
	}
	@Override
	public int getOverflowThreshold() {
		return overflowThreshold;
	}
	@Override
	public ChunkCoordinates getBinding() {
		return new ChunkCoordinates(bindX, bindY, bindZ);
	}
	@Override
	public boolean bindTo(EntityPlayer player, ItemStack wand, int x, int y, int z, int s) {
		bindX = x;
		bindY = y;
		bindZ = z;
		return true;
	}
	@Override
	public boolean canSelect(EntityPlayer player, ItemStack wand, int x, int y, int z, int s) {
		return true;
	}
	@Override
	public int getConductivity(){
		return getState() ? 0 : 1;
	}
	@Override
	public void overflow(){
		blindAddLightning(-9999);
	}
}