package lazersmoke.botanicalworkshop.common.block.tile.lightning;

import net.minecraft.init.Blocks;
import vazkii.botania.client.core.handler.ClientTickHandler;

public class TileLightningLavaFabricator extends TileModLightning{

	@Override
	public void updateEntity(){
		super.updateEntity();
		if(worldObj.isRemote)
			return;
		if(getState() && worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord) && ClientTickHandler.ticksInGame % 80 == 0 && addLightning(-10))
			worldObj.setBlock(xCoord, yCoord + 1, zCoord, Blocks.lava);
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
	public int getPowerThreshold(){
		return 100;
	}

	@Override
	public int getBufferThreshold(){
		return 500;
	}

	@Override
	public int getOverflowThreshold(){
		return 600;
	}
}