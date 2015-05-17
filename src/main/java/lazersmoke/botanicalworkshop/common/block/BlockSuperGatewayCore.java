package lazersmoke.botanicalworkshop.common.block;

import cpw.mods.fml.common.registry.GameRegistry;
import vazkii.botania.api.wand.IWandable;
import vazkii.botania.common.block.BlockModContainer;
import lazersmoke.botanicalworkshop.common.block.tile.TileSuperGatewayCore;
import lazersmoke.botanicalworkshop.common.item.block.ItemBlockSuperGatewayCore;
import lazersmoke.botanicalworkshop.common.lib.LibBlockNames;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

//This class is all Lazersmoke
public class BlockSuperGatewayCore extends BlockModContainer implements IWandable{
	public BlockSuperGatewayCore() {
		super(Material.rock);
		setHardness(2.0F);
		setResistance(10.0F);
		setStepSound(soundTypeStone);
		setBlockName(LibBlockNames.SUPER_GATEWAY_CORE);
		setBlockBounds(0F, 0F, 0F, 1F, 1F, 1F);
	}

	@Override
	public Block setBlockName(String name){
		GameRegistry.registerBlock(this, ItemBlockSuperGatewayCore.class, name);
		return super.setBlockName(name);
	}
	
	@Override
	protected boolean shouldRegisterInNameSet() {
		return false;
	}
	
	@Override
	public TileEntity createNewTileEntity(World arg0, int arg1) {
		return new TileSuperGatewayCore();
	}

	@Override
	public boolean onUsedByWand(EntityPlayer player, ItemStack stack, World world, int x, int y, int z, int side) {
		boolean did = ((TileSuperGatewayCore) world.getTileEntity(x, y, z)).onWanded();
		return did;
	}
	
}