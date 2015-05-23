package lazersmoke.botanicalworkshop.common.block.subtile.functional;
//This class is all Lazersmoke
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import lazersmoke.botanicalworkshop.common.BotanicalWorkshop;
import lazersmoke.botanicalworkshop.common.lexicon.LexiconData;
import lazersmoke.botanicalworkshop.common.lib.LibMisc;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import vazkii.botania.api.item.IPetalApothecary;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.subtile.RadiusDescriptor;
import vazkii.botania.api.subtile.SubTileFunctional;

public class SubTileExAquainas extends SubTileFunctional{
	private static final int COST = 35;
	private static final int maxMana = 1000;
	private static final int[][] OFFSETS = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 }, { -1, 1 }, { -1, -1 }, { 1, 1 }, { 1, -1 } };

	@Override
	public void onUpdate(){
		super.onUpdate();
		Block blockAbove = supertile.getWorldObj().getBlock(supertile.xCoord, supertile.yCoord + 1, supertile.zCoord);
		TileEntity tileAbove = supertile.getWorldObj().getTileEntity(supertile.xCoord, supertile.yCoord + 1, supertile.zCoord);
		if((tileAbove instanceof IPetalApothecary && ((IPetalApothecary) tileAbove).hasWater() == false) && mana >= COST && !supertile.getWorldObj().isRemote && ticksExisted % 10 == 1 && redstoneSignal == 0) {
			List<int[]> offsets = Arrays.asList(OFFSETS);
			Collections.shuffle(offsets);

			for(int[] offsetArray : offsets) {
				int[] positions = {
						supertile.xCoord + offsetArray[0],
						supertile.zCoord + offsetArray[1]
				};
				Block search = Blocks.water;
				if(supertile.getWorldObj().getBlock(positions[0], supertile.yCoord, positions[1]) == search && supertile.getWorldObj().getBlockMetadata(positions[0], supertile.yCoord, positions[1]) == 0) {
					int waterAround = 0;
					for(ForgeDirection dir : LibMisc.CARDINAL_DIRECTIONS)
						if(supertile.getWorldObj().getBlock(positions[0] + dir.offsetX, supertile.yCoord, positions[1] + dir.offsetZ) == search)
							waterAround++;

					if(waterAround < 2)
						supertile.getWorldObj().setBlockToAir(positions[0], supertile.yCoord, positions[1]);
					//Add Water
					if(((IPetalApothecary) tileAbove).hasWater() == false)
						((IPetalApothecary) tileAbove).setWater(true);
					//End Add Water
					sync();
					playSound();
					break;
				}
			}
		}
	}
	
	@Override
	public boolean acceptsRedstone(){
		return true;
	}
	
	public void playSound() {
		supertile.getWorldObj().playSoundEffect(supertile.xCoord, supertile.yCoord, supertile.zCoord, "random.drink", 0.02F, 0.5F + (float) Math.random() * 0.5F);
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
		return maxMana;
	}
	
	@Override
	public RadiusDescriptor getRadius() {
		return new RadiusDescriptor.Square(toChunkCoordinates(), 1);
	}
}