package lazersmoke.botanicalworkshop.common.block.subtile.functional;
//This class is all Lazersmoke
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import lazersmoke.botanicalworkshop.api.subtile.SubTileFunctional;
import lazersmoke.botanicalworkshop.common.lexicon.LexiconData;
import lazersmoke.botanicalworkshop.common.lib.LibMisc;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidBlock;
import net.minecraftforge.fluids.IFluidTank;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.subtile.RadiusDescriptor;

public class SubTileExAquainas extends SubTileFunctional{
	private static final int COST = 35;
	private static final int[][] OFFSETS = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 }, { -1, 1 }, { -1, -1 }, { 1, 1 }, { 1, -1 } };

	@Override
	public void onUpdate(){
		super.onUpdate();
		TileEntity tileAbove = supertile.getWorldObj().getTileEntity(supertile.xCoord, supertile.yCoord + 1, supertile.zCoord);
		if(tileAbove instanceof IFluidTank && mana >= COST && !supertile.getWorldObj().isRemote) {
			List<int[]> offsets = Arrays.asList(OFFSETS);
			Collections.shuffle(offsets);

			for(int[] offsetArray : offsets) {
				int[] positions = {
						supertile.xCoord + offsetArray[0],
						supertile.zCoord + offsetArray[1]
				};
				if(
					supertile.getWorldObj().getBlock(positions[0], supertile.yCoord, positions[1]) instanceof IFluidBlock && //Check if Fluid
					(((IFluidBlock) supertile.getWorldObj().getBlock(positions[0], supertile.yCoord, positions[1])).getFluid().getID() == ((IFluidTank) tileAbove).getFluid().fluidID  || //Check if Fluid Matches...
					((IFluidTank) tileAbove).getFluid().amount == 0) && //Or doesn't exist
					supertile.getWorldObj().getBlockMetadata(positions[0], supertile.yCoord, positions[1]) == 0) {	//And still fluid
					
					((IFluidTank) tileAbove).fill(new FluidStack(((IFluidBlock) supertile.getWorldObj().getBlock(positions[0], supertile.yCoord, positions[1])).getFluid(), 1000), true);
					int waterAround = 0;
					for(ForgeDirection dir : LibMisc.CARDINAL_DIRECTIONS)
						if(supertile.getWorldObj().getBlock(positions[0] + dir.offsetX, supertile.yCoord, positions[1] + dir.offsetZ) == Blocks.water)
							waterAround++;

					if(waterAround < 2)
						supertile.getWorldObj().setBlockToAir(positions[0], supertile.yCoord, positions[1]);
					sync();
					break;
				}
			}
		}
	}
	
	@Override
	public boolean acceptsRedstone(){
		return true;
	}
	
	@Override
	public int getColor(){
		return 0x532FE0; //Same as Hydroangaes
	}
	
	@Override
	public LexiconEntry getEntry() {
		return LexiconData.exAquainas;
	}

	@Override
	public int getMaxMana() {
		return COST;
	}
	
	@Override
	public RadiusDescriptor getRadius() {
		return new RadiusDescriptor.Square(toChunkCoordinates(), 1);
	}
}