package lazersmoke.botanicalworkshop.common.block.subtile.functional;

// This class is all Lazersmoke
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import lazersmoke.botanicalworkshop.api.flowers.TonalFunctionalFlower;
import lazersmoke.botanicalworkshop.common.lexicon.LexiconData;
import lazersmoke.botanicalworkshop.common.lib.LibMisc;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidHandler;
import vazkii.botania.api.item.IPetalApothecary;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.subtile.RadiusDescriptor;

public class SubTileExAquainas extends TonalFunctionalFlower{

	private static final int COST = 35;
	private static final int maxMana = 1000;
	private static final int[][] OFFSETS = {
	        {
	                0, 1
	        }, {
	                0, -1
	        }, {
	                1, 0
	        }, {
	                -1, 0
	        }, {
	                -1, 1
	        }, {
	                -1, -1
	        }, {
	                1, 1
	        }, {
	                1, -1
	        }
	};

	private boolean firstTick = true;

	@Override
	public void onUpdate(){
		if(firstTick){
			pitches = new float[][] {
				{
				        0.9F, 1.2F, 1.4F, 1.32F, 1.2F, 0.95F, 0.9F, 0.8F, 0.7F,
				        0.67F, 0.9F, 0.9F, 1.2F, 1.4F, 1.32F, 1.2F, 0.95F,
				        0.9F, 0.8F, 0.7F, 1.1F, 0.9F, 0.9F, 0.8F, 0.7F, 1.05F,
				        0.7F, 0.8F, 0.9F, 0.95F, 0.9F, 0.8F, 0.7F, 0.67F, 0.6F,
				        0.67F, 0.7F, 0.95F, 0.9F, 1.1F, 1.32F, 1.9F, 1.8F
				}
			};
			times = new int[][] {
				{
				        4, 6, 1, 1, 2, 4, 2, 6, 1, 1, 4, 4, 6, 1, 1, 2, 4, 2,
				        6, 1, 1, 4, 4, 6, 1, 1, 4, 4, 6, 1, 1, 4, 4, 1, 1, 1,
				        1, 4, 4, 4, 4, 4, 4
				}
			};
			tempo = 2;
			soundEffect = new String[1][pitches[0].length];
			Arrays.fill(soundEffect[0], "note.harp");
			volumes = new float[1][pitches[0].length];
			Arrays.fill(volumes[0], 0.3F);
		}
		super.onUpdate();
		firstTick = false;

		TileEntity activeTile = supertile.getWorldObj().getTileEntity(
		        supertile.xCoord, supertile.yCoord + 1, supertile.zCoord);
		if(supertile.getWorldObj().getTileEntity(supertile.xCoord,
		        supertile.yCoord - 1, supertile.zCoord) instanceof IFluidHandler)
			activeTile = supertile.getWorldObj().getTileEntity(
			        supertile.xCoord, supertile.yCoord - 1, supertile.zCoord);

		boolean hasBucket = false;
		List<EntityItem> items = supertile.getWorldObj().getEntitiesWithinAABB(
		        EntityItem.class,
		        AxisAlignedBB.getBoundingBox(supertile.xCoord - 1,
		                supertile.yCoord, supertile.zCoord - 1,
		                supertile.xCoord + 2, supertile.yCoord + 1,
		                supertile.zCoord + 2));
		for(EntityItem item : items)
			if(item.getEntityItem().getItem() == Items.bucket)
				hasBucket = true;

		if( ( ( activeTile instanceof IPetalApothecary && ( (IPetalApothecary) activeTile )
		        .hasWater() == false )
		        || ( activeTile instanceof IFluidHandler && ( ( (IFluidHandler) activeTile ) )
		                .canFill(ForgeDirection.DOWN, FluidRegistry.WATER) )
		        || hasBucket || redstoneSignal > 0 )
		        && mana >= COST
		        && !supertile.getWorldObj().isRemote
		        && ticksExisted % 10 == 1){
			List<int[]> offsets = Arrays.asList(OFFSETS);
			Collections.shuffle(offsets);

			for(int[] offsetArray : offsets){
				int[] positions = {
				        supertile.xCoord + offsetArray[0],
				        supertile.zCoord + offsetArray[1]
				};
				Block search = Blocks.water;
				if(supertile.getWorldObj().getBlock(positions[0],
				        supertile.yCoord, positions[1]) == search
				        && supertile.getWorldObj().getBlockMetadata(
				                positions[0], supertile.yCoord, positions[1]) == 0){
					int waterAround = 0;
					for(ForgeDirection dir : LibMisc.CARDINAL_DIRECTIONS)
						if(supertile.getWorldObj().getBlock(
						        positions[0] + dir.offsetX, supertile.yCoord,
						        positions[1] + dir.offsetZ) == search)
							waterAround++;

					if(waterAround < 2)
						supertile.getWorldObj().setBlockToAir(positions[0],
						        supertile.yCoord, positions[1]);
					// Add Water
					if(activeTile instanceof IPetalApothecary
					        && ( (IPetalApothecary) activeTile ).hasWater() == false)
						( (IPetalApothecary) activeTile ).setWater(true);
					else if(activeTile instanceof IFluidHandler
					        && ( (IFluidHandler) activeTile ).canFill(
					                ForgeDirection.DOWN, FluidRegistry.WATER))
						( (IFluidHandler) activeTile ).fill(
						        ForgeDirection.DOWN, new FluidStack(
						                FluidRegistry.WATER, 1000), true);
					// End Add Water
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

	private void playSound(String sound, float volume, float pitch){
		supertile.getWorldObj().playSoundEffect(supertile.xCoord,
		        supertile.yCoord, supertile.zCoord, sound, volume, pitch);
	}

	@Override
	public int getColor(){
		return 0x532FE0; // Same as Hydroangaes
	}

	@Override
	public LexiconEntry getEntry(){
		return LexiconData.exAquainas;
	}

	@Override
	public int getMaxMana(){
		return maxMana;
	}

	@Override
	public RadiusDescriptor getRadius(){
		return new RadiusDescriptor.Square(toChunkCoordinates(), 1);
	}
}