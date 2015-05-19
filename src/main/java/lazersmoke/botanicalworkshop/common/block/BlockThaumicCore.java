package lazersmoke.botanicalworkshop.common.block;

import lazersmoke.botanicalworkshop.common.block.tile.TileThaumicCore;
import lazersmoke.botanicalworkshop.common.item.block.ItemBlockThaumicCore;
import lazersmoke.botanicalworkshop.common.lib.LibBlockNames;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import vazkii.botania.common.block.BlockModContainer;
import cpw.mods.fml.common.registry.GameRegistry;

//This class is all Lazersmoke
public class BlockThaumicCore extends BlockModContainer{
	
	IIcon iconOff, iconOn;
	
	public BlockThaumicCore() {
		super(Material.wood);
		setHardness(2.0F);
		setResistance(10.0F);
		setStepSound(soundTypeWood);
		setBlockName(LibBlockNames.THAUMIC_CORE);
		setBlockBounds(0F, 0F, 0F, 1F, 1F, 1F);
	}

	@Override
	public Block setBlockName(String name){
		GameRegistry.registerBlock(this, ItemBlockThaumicCore.class, name);
		return super.setBlockName(name);
	}
	
	@Override
	protected boolean shouldRegisterInNameSet() {
		return false;
	}

	@Override
	public TileEntity createNewTileEntity(World arg0, int arg1) {
		return new TileThaumicCore();
	}
}