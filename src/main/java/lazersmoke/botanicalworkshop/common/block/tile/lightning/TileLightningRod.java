package lazersmoke.botanicalworkshop.common.block.tile.lightning;

import lazersmoke.botanicalworkshop.api.mana.lightning.IBotanicalLightningBlock;
import net.minecraft.nbt.NBTTagCompound;
import vazkii.botania.common.core.helper.Vector3;

public class TileLightningRod extends TileModLightning implements IBotanicalLightningBlock{
	@Override
	public void updateEntity(){
		super.updateEntity();
		worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, grounded ? 0 : 1, 3);
	}

	public void onWanded(){
		grounded = !grounded;
		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		markDirty(); // damn i have to put this everywhere lol
	}

	public boolean isGrounded(){
		return grounded;
	}

	private boolean grounded = false;

	@Override
	public int getConductivity(){
		return getState() ? (grounded ? -1 : 0) : (grounded ? 0 : 1);
	}

	@Override
	public int getPowerThreshold(){
		return 10;
	}

	@Override
	public int getBufferThreshold(){
		return 10;
	}

	@Override
	public int getOverflowThreshold(){
		return 500;
	}

	private final Vector3 renderOffset = new Vector3(0.5F, 1.0F, 0.5F);

	@Override
	public Vector3 getLightningRenderOffset(){
		return renderOffset;
	}

	@Override
	public void writeCustomNBT(NBTTagCompound cmp){
		super.writeCustomNBT(cmp);
		cmp.setBoolean("botanicallyGrounded", grounded);
	}

	@Override
	public void readCustomNBT(NBTTagCompound cmp){
		super.readCustomNBT(cmp);
		grounded = cmp.getBoolean("botanicallyGrounded");
	}
}

// grounded means dont pull from transformer unless no mana