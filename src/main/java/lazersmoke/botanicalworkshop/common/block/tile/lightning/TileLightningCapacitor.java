package lazersmoke.botanicalworkshop.common.block.tile.lightning;

import lazersmoke.botanicalworkshop.api.mana.lightning.IBotanicalLightningCellAcceptor;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;

public class TileLightningCapacitor extends TileModLightning implements IBotanicalLightningCellAcceptor{
	private int buffer = 500;
	private final String TAG_BUFFER = "botanicalCapacitance";

	@Override
	public void updateEntity(){
		super.updateEntity();
		worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, getState() ? 0 : 1, 2);
	}

	@Override
	public int getConductivity(){
		return getState() ? 0 : 1;
	}

	@Override
	public void overflow(){
		blindAddLightning(-getCurrentLightning());
	}

	@Override
	public void writeCustomNBT(NBTTagCompound cmp){
		super.writeCustomNBT(cmp);
		cmp.setInteger(TAG_BUFFER, buffer);
	}

	@Override
	public void readCustomNBT(NBTTagCompound cmp){
		super.readCustomNBT(cmp);
		buffer = cmp.getInteger(TAG_BUFFER);
	}

	public void interpretClick(ForgeDirection side){
		if(side == ForgeDirection.UP)
			buffer = Math.min(buffer + 10, 10000);
		if(side == ForgeDirection.DOWN)
			buffer = Math.max(buffer - 10, 0);
		worldObj.playSoundEffect(xCoord, yCoord, zCoord, "note.harp", 0.3F, 1.0F);
	}

	@Override
	public int getPowerThreshold(){
		return 10;
	}

	@Override
	public int getBufferThreshold(){
		return buffer;
	}

	@Override
	public int getOverflowThreshold(){
		return buffer + 100;
	}
}