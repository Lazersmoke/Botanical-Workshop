package lazersmoke.botanicalworkshop.common.block.tile.lightning;

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

public class TileThaumtanicalTransposer extends TileModLightning implements IWandBindable{
	protected int powerThreshold = 100;
	protected int bufferThreshold = 150;
	protected int overflowThreshold = 300;

	private final AxisAlignedBB[] possibleInputAABBs = new AxisAlignedBB[6];
	private int AABBIndex = 1;

	private int bindX;
	private int bindY;
	private int bindZ;
	private static final String TAG_BIND_X = "bindGatewayX";
	private static final String TAG_BIND_Y = "bindGatewayY";
	private static final String TAG_BIND_Z = "bindGatewayZ";
	private static final String TAG_INPUT_SIDE = "inputSide";

	private boolean firstTick = true;

	@Override
	public void updateEntity(){
		super.updateEntity();
		worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, (AABBIndex << 1) | (getState() ? 1 : 0), 2);
		if(firstTick){
			firstTick = false;// Run once
			bindX = bindX == 0 ? xCoord : bindX;
			bindY = bindY == 0 ? yCoord + 10 : bindY;
			bindZ = bindY == 0 ? yCoord : bindZ;
			possibleInputAABBs[0] = AxisAlignedBB.getBoundingBox(xCoord + 0, yCoord - 1, zCoord + 0, xCoord + 1, yCoord + 0, zCoord + 1);// B
			possibleInputAABBs[1] = AxisAlignedBB.getBoundingBox(xCoord + 0, yCoord + 1, zCoord + 0, xCoord + 1, yCoord + 2, zCoord + 1);// T
			possibleInputAABBs[2] = AxisAlignedBB.getBoundingBox(xCoord + 0, yCoord + 0, zCoord - 1, xCoord + 1, yCoord + 1, zCoord + 0);// N
			possibleInputAABBs[3] = AxisAlignedBB.getBoundingBox(xCoord + 0, yCoord + 0, zCoord + 1, xCoord + 1, yCoord + 1, zCoord + 2);// S
			possibleInputAABBs[4] = AxisAlignedBB.getBoundingBox(xCoord - 1, yCoord + 0, zCoord + 0, xCoord + 0, yCoord + 1, zCoord + 1);// W
			possibleInputAABBs[5] = AxisAlignedBB.getBoundingBox(xCoord + 1, yCoord + 0, zCoord + 0, xCoord + 2, yCoord + 1, zCoord + 1);// E
		}
		if(getState()){
			@SuppressWarnings("unchecked")
			final List<EntityItem> items = worldObj.getEntitiesWithinAABB(EntityItem.class, getActiveAABB());
			// BotanicalWorkshop.logger.log(Level.INFO, "Box is " + getActiveAABB().minX + ", " + getActiveAABB().minY + ", " + getActiveAABB().minZ + " to " + getActiveAABB().maxX + ", " + getActiveAABB().maxY + ", " + getActiveAABB().maxZ);
			// Don't tp if: nothing to tp OR its a IManaItem OR we dont have enough lightning
			if(!items.isEmpty() && (!items.get(0).getEntityItem().hasTagCompound() || (items.get(0).getEntityItem().getTagCompound().hasNoTags())) && items.get(0).getEntityItem().stackSize == 1 && !(items.get(0).getEntityItem().getItem() instanceof IManaItem) && addLightning(-getDistanceToBind())){
				final EntityItem theItem = items.get(0);
				// BotanicalWorkshop.logger.log(Level.INFO, "begin");
				theItem.getEntityItem().func_135074_t();// IDK
				if(theItem.getEntityItem().hasTagCompound())
					theItem.getEntityItem().setTagCompound(null);// Can do because we check for empty above
				theItem.setPosition(bindX + 0.5F, bindY + 1.5F, bindZ + 0.5F);
				theItem.setVelocity(0, 0, 0);
				if(theItem.getEntityItem().hasTagCompound())
					theItem.getEntityItem().setTagCompound(null);// Can do because we check for empty above
				worldObj.playSoundEffect(xCoord + 0.5F, yCoord + 0.5F, zCoord + 0.5F, "mob.endermen.portal", 0.5F, (float) ((Math.random() / 2) + 0.25F));
				worldObj.playSoundEffect(bindX + 0.5F, bindY + 1.5F, bindZ + 0.5F, "mob.endermen.portal", 0.5F, (float) ((Math.random() / 2) + 0.25F));
			}
		}
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
		cmp.setInteger(TAG_INPUT_SIDE, AABBIndex);
	}

	@Override
	public void readCustomNBT(NBTTagCompound cmp){
		super.readCustomNBT(cmp);
		bindX = cmp.getInteger(TAG_BIND_X);
		bindY = cmp.getInteger(TAG_BIND_Y);
		bindZ = cmp.getInteger(TAG_BIND_Z);
		AABBIndex = cmp.getInteger(TAG_INPUT_SIDE);
	}

	public void onWanded(int side){
		AABBIndex = side;
	}

	private AxisAlignedBB getActiveAABB(){
		return possibleInputAABBs[AABBIndex];
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