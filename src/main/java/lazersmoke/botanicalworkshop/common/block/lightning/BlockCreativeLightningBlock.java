package lazersmoke.botanicalworkshop.common.block.lightning;

import lazersmoke.botanicalworkshop.client.lib.LibResources;
import lazersmoke.botanicalworkshop.common.BotanicalWorkshop;
import lazersmoke.botanicalworkshop.common.block.tile.lightning.TileCreativeLightningBlock;
import lazersmoke.botanicalworkshop.common.item.block.lightning.ItemBlockCreativeLightningBlock;
import lazersmoke.botanicalworkshop.common.lib.LibBlockNames;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import vazkii.botania.api.wand.IWandable;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockCreativeLightningBlock extends BlockContainer implements IWandable{

	public static IIcon iconNorth, iconSouth, iconEast, iconWest, iconUp, iconDown;

	public BlockCreativeLightningBlock(){
		super(Material.rock);
		setHardness(2.0F);
		setResistance(10.0F);
		setStepSound(soundTypeMetal);
		setBlockName(LibBlockNames.LIGHTNING_CREATIVE);
		setCreativeTab(BotanicalWorkshop.creativeTab);
		setBlockBounds(0F, 0F, 0F, 1F, 1F, 1F);
	}

	@Override
	public Block setBlockName(String name){
		GameRegistry.registerBlock(this, ItemBlockCreativeLightningBlock.class, name);
		return super.setBlockName(name);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister){
		iconNorth = iconRegister.registerIcon(LibResources.PREFIX_MOD + getUnlocalizedName().replaceAll("tile\\.", "") + "North");
		iconSouth = iconRegister.registerIcon(LibResources.PREFIX_MOD + getUnlocalizedName().replaceAll("tile\\.", "") + "South");
		iconEast = iconRegister.registerIcon(LibResources.PREFIX_MOD + getUnlocalizedName().replaceAll("tile\\.", "") + "East");
		iconWest = iconRegister.registerIcon(LibResources.PREFIX_MOD + getUnlocalizedName().replaceAll("tile\\.", "") + "West");
		iconUp = iconRegister.registerIcon(LibResources.PREFIX_MOD + getUnlocalizedName().replaceAll("tile\\.", "") + "Up");
		iconDown = iconRegister.registerIcon(LibResources.PREFIX_MOD + getUnlocalizedName().replaceAll("tile\\.", "") + "Down");
	}

	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int side, int meta){
		switch(side){
			case 0:
				return iconDown;
			case 1:
				return iconUp;
			case 2:
				return iconNorth;
			case 3:
				return iconSouth;
			case 4:
				return iconWest;
			case 5:
				return iconEast;
		}
		return iconUp;
	}

	@Override
	public TileEntity createNewTileEntity(World arg0, int arg1){
		return new TileCreativeLightningBlock();
	}

	@Override
	public boolean onUsedByWand(EntityPlayer player, ItemStack wand, World world, int x, int y, int z, int side){
		((TileCreativeLightningBlock) world.getTileEntity(x, y, z)).interpretClick(ForgeDirection.getOrientation(side));
		return true;
	}
}