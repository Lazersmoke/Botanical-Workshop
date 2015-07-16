package lazersmoke.botanicalworkshop.common.block.tile.mana;

import java.util.ArrayList;
import java.util.List;

import lazersmoke.botanicalworkshop.client.lib.LibResources;
import lazersmoke.botanicalworkshop.common.block.ModBlocks;
import lazersmoke.botanicalworkshop.common.lib.LibMisc;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.util.ForgeDirection;
import vazkii.botania.api.mana.IManaReceiver;
import vazkii.botania.client.core.handler.HUDHandler;
import vazkii.botania.common.block.tile.TileMod;

public class TileManaBankInterface extends TileMod implements IManaReceiver {

	private List<int[]> poolLocations = new ArrayList<int[]>();

	private void updateLocations() {
		List<int[]> toCheck = new ArrayList<int[]>();
		poolLocations.clear();
		toCheck.add(new int[] { xCoord, yCoord, zCoord });
		boolean a = true;
		List<int[]> addToCheck = new ArrayList<int[]>();// Avoids concurrent
														// modification

		while (a) {
			a = false;
			for (int[] location : toCheck)
				for (ForgeDirection dir : LibMisc.ALL_DIRECTIONS)
					if (!toCheck.contains(new int[] {location[0] + dir.offsetX, location[1] + dir.offsetY, location[2] + dir.offsetZ })
						&& worldObj.getTileEntity(location[0] + dir.offsetX, location[1] + dir.offsetY, location[2] + dir.offsetZ) instanceof TileElvenPool) {
							addToCheck.add(new int[] { 
								location[0] + dir.offsetX,
								location[1] + dir.offsetY,
								location[2] + dir.offsetZ });
							a = true;
					}
			toCheck.addAll(addToCheck);
			addToCheck.clear();
		}
		toCheck.remove(0);
		poolLocations.addAll(toCheck);
	}

	@Override
	public void updateEntity() {
		updateLocations();
	}

	public int getCurrentMana() {
		int totalMana = 0;
		for (int[] pos : poolLocations) {
			TileEntity tile = worldObj.getTileEntity(xCoord + pos[0], yCoord
					+ pos[1], zCoord + pos[2]);
			if (tile instanceof TileElvenPool) {
				TileElvenPool pool = (TileElvenPool) tile;
				totalMana += pool.getCurrentMana();
			}
		}
		return totalMana;
	}

	public boolean canRecieveManaFromBursts() {
		return true;
	}

	public boolean isFull() {
		for (int[] pos : poolLocations) {
			TileEntity tile = worldObj.getTileEntity(xCoord + pos[0], yCoord
					+ pos[1], zCoord + pos[2]);
			if (tile instanceof IManaReceiver
					&& !((IManaReceiver) tile).isFull())
				return false;
		}
		return true;// Made it through all locations without finding not full
					// pool; if no pools, then it is full of 0 mana, max
					// capacity is 0
	}

	public void recieveMana(int amount) {
		int totalMana = getCurrentMana();
		if (-amount < poolLocations.size()) {
			for (int[] pos : poolLocations) {
				TileEntity tile = worldObj.getTileEntity(xCoord + pos[0],
						yCoord + pos[1], zCoord + pos[2]);
				if (tile instanceof TileElvenPool) {
					IManaReceiver pool = (TileElvenPool) tile;
					double costRatio = Math.abs(amount) == amount ? ((double) TileElvenPool.MAX_MANA - (double) pool
							.getCurrentMana()) / ((double) totalMana)
							: (double) pool.getCurrentMana()
									/ (double) totalMana; // What percent does a
															// small pool get
															// when adding mana?
					// If cost ratio is positive give small pools more; If
					// negative, take more from large pools
					if (!worldObj.isRemote)
						pool.recieveMana((int) Math.round(amount * costRatio));
				}
			}
		}
	}

	public void renderHUD(Minecraft mc, ScaledResolution res) {
		String name = StatCollector.translateToLocal(new ItemStack(
				ModBlocks.manaBankInterface, 1, getBlockMetadata())
				.getUnlocalizedName().replaceAll("tile.",
						"tile." + LibResources.PREFIX_MOD)
				+ ".name");
		int color = 0x4444FF;
		HUDHandler.drawSimpleManaHUD(color, this.getCurrentMana(),
				TileElvenPool.MAX_MANA * poolLocations.size(), name, res);
	}

}
