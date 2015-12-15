package lazersmoke.botanicalworkshop.common.block.tile.lightning;

import lazersmoke.botanicalworkshop.api.mana.lightning.IBotanicalLightningBlock;
import vazkii.botania.common.core.helper.Vector3;

public class TileLightningTransformer extends TileModLightning implements IBotanicalLightningBlock{
	@Override
	public int getConductivity(){
		return getState() ? -1 : 0;
	}

	@Override
	public void overflow(){
		this.worldObj.spawnParticle("hugeexplosion", xCoord + 0.5F, yCoord + 0.5F, zCoord + 0.5F, 1.0D, 0.0D, 0.0D);
		worldObj.setBlockToAir(xCoord, yCoord, zCoord);
	}

	@Override
	public int getPowerThreshold(){
		return 10;
	}

	@Override
	public int getBufferThreshold(){
		return 1000;
	}

	@Override
	public int getOverflowThreshold(){
		return 1500;
	}

	private Vector3 renderOffset = new Vector3(0.5F, 1.0F, 0.5F);

	@Override
	public Vector3 getLightningRenderOffset(){
		return renderOffset;
	}

	@Override
	public int getLightningPushRange(){
		return ((worldObj.getTileEntity(xCoord, yCoord + 1, zCoord) instanceof IBotanicalLightningBlock) && (((IBotanicalLightningBlock) worldObj.getTileEntity(xCoord, yCoord + 1, zCoord)).getConductivity() > 0)) ? 1 : 0;// If the block above me is at least 1 conductive, then push to it
	}
}
