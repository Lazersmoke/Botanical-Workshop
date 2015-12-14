package lazersmoke.botanicalworkshop.common.block.tile.mana.lightning;

import java.util.List;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;
import vazkii.botania.api.mana.IManaItem;
import vazkii.botania.api.wand.IWandBindable;
import vazkii.botania.common.core.helper.MathHelper;

public class TileLightningFurnace extends TileModLightning implements IWandBindable{
	protected int powerThreshold = 100;
	protected int bufferThreshold = 150;
	protected int overflowThreshold = 300;

	private int bindX = xCoord;
	private int bindY = yCoord + 10;
	private int bindZ = zCoord;
	private static final String TAG_BIND_X = "bindGatewayX";
	private static final String TAG_BIND_Y = "bindGatewayY";
	private static final String TAG_BIND_Z = "bindGatewayZ";

	@Override
	public void updateEntity(){
		super.updateEntity();
		if(getState()){
			List<EntityItem> items = (List<EntityItem>) worldObj.getEntitiesWithinAABB(EntityItem.class, getActiveAABB());
			// Don't tp if: nothing to tp OR its a IManaItem OR we dont have enough lightning
			if(!items.isEmpty() && !(items.get(0).getEntityItem().getItem() instanceof IManaItem) && addLightning(-getDistanceToBind())){
				items.get(0).setPosition(bindX + 0.5F, bindY + 1.5F, bindZ + 0.5F);
				items.get(0).motionX = 0;
				items.get(0).motionY = 0;
				items.get(0).motionZ = 0;
				worldObj.playSoundEffect(xCoord + 0.5F, yCoord + 0.5F, zCoord + 0.5F, "mob.endermen.portal", 0.5F, (float) ((Math.random() / 2) + 0.25F));
				worldObj.playSoundEffect(bindX + 0.5F, bindY + 1.5F, bindZ + 0.5F, "mob.endermen.portal", 0.5F, (float) ((Math.random() / 2) + 0.25F));
			}
		}
		worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, getState() ? 1 : 0, 1);
	}

	private int getDistanceToBind(){
		return (int) Math.ceil(MathHelper.pointDistanceSpace(xCoord, yCoord, zCoord, bindX, bindY, bindZ));
	}

	@Override
	public void writeCustomNBT(NBTTagCompound cmp){
		super.writeCustomNBT(cmp);
		cmp.setInteger(TAG_BIND_X, bindX);
		cmp.setInteger(TAG_BIND_Y, bindY);
		cmp.setInteger(TAG_BIND_Z, bindZ);
	}

	@Override
	public void readCustomNBT(NBTTagCompound cmp){
		super.readCustomNBT(cmp);
		bindX = cmp.getInteger(TAG_BIND_X);
		bindY = cmp.getInteger(TAG_BIND_Y);
		bindZ = cmp.getInteger(TAG_BIND_Z);
	}

	public boolean onWanded(EntityPlayer player){
		return addLightning(50);
	}

	private AxisAlignedBB getActiveAABB(){
		return AxisAlignedBB.getBoundingBox(xCoord, yCoord + 1, zCoord, xCoord + 1, yCoord + 2, zCoord + 1);
	}

	@Override
	public ChunkCoordinates getBinding(){
		return new ChunkCoordinates(bindX, bindY, bindZ);
	}

	@Override
	public boolean bindTo(EntityPlayer player, ItemStack wand, int x, int y, int z, int s){
		bindX = x;
		bindY = y;
		bindZ = z;
		return true;
	}

	@Override
	public boolean canSelect(EntityPlayer player, ItemStack wand, int x, int y, int z, int s){
		return true;
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
		return 150;
	}

	@Override
	public int getOverflowThreshold(){
		return 300;
	}
}