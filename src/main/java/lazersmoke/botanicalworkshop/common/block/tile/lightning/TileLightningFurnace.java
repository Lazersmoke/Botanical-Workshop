package lazersmoke.botanicalworkshop.common.block.tile.lightning;

import java.util.List;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;

public class TileLightningFurnace extends TileModLightning{

	private int cookingProgress = 0;
	private ItemStack itemIn = null;
	private final String TAG_COOKING_PROGRESS = "botanicalCookingProgress";
	private final String TAG_ITEM_IN = "botanicalItemIn";

	@Override
	public void updateEntity(){
		super.updateEntity();
		if(worldObj.isRemote)
			return;
		// If we are on, have an item, that item is smeltable, we have lightning and our cookingProgress (BTW increment dat shit sometime) is at least 200, do the thing!
		if(getState() && itemIn != null && FurnaceRecipes.smelting().getSmeltingResult(itemIn) != null && addLightning(-2) && worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 1, 3) && /* Update texture */cookingProgress++ >= 200){
			final EntityItem theItem = new EntityItem(worldObj, xCoord + 0.5F, yCoord - 0.5F, zCoord + 0.5F, FurnaceRecipes.smelting().getSmeltingResult(itemIn));
			theItem.delayBeforeCanPickup = 0;
			itemIn = null;
			cookingProgress = 0;
			worldObj.spawnEntityInWorld(theItem);
		}else
			worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 0, 3);
		@SuppressWarnings("unchecked")
		final List<EntityItem> items = worldObj.getEntitiesWithinAABB(EntityItem.class, getActiveAABB());
		if(itemIn == null && !items.isEmpty() && !items.get(0).isDead && items.get(0).getEntityItem() != null && FurnaceRecipes.smelting().getSmeltingResult(items.get(0).getEntityItem()) != null)
			if(items.get(0).getEntityItem().stackSize == 1){
				itemIn = items.get(0).getEntityItem().copy();
				items.get(0).setDead();
			}
	}

	private AxisAlignedBB getActiveAABB(){
		return AxisAlignedBB.getBoundingBox(xCoord, yCoord + 1, zCoord, xCoord + 1, yCoord + 2, zCoord + 1);
	}

	@Override
	public void writeCustomNBT(NBTTagCompound cmp){
		super.writeCustomNBT(cmp);
		cmp.setInteger(TAG_COOKING_PROGRESS, cookingProgress);
		if(itemIn != null){
			final NBTTagCompound stack = new NBTTagCompound();
			itemIn.writeToNBT(stack);
			cmp.setTag(TAG_ITEM_IN, stack);
		}
	}

	@Override
	public void readCustomNBT(NBTTagCompound cmp){
		super.readCustomNBT(cmp);
		cookingProgress = cmp.getInteger(TAG_COOKING_PROGRESS);
		itemIn = ItemStack.loadItemStackFromNBT(cmp.getCompoundTag(TAG_ITEM_IN));
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
		return 10;
	}

	@Override
	public int getBufferThreshold(){
		return 800;
	}

	@Override
	public int getOverflowThreshold(){
		return 1000;
	}
}