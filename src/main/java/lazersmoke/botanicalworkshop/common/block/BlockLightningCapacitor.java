package lazersmoke.botanicalworkshop.common.block;

import lazersmoke.botanicalworkshop.client.lib.LibResources;
import lazersmoke.botanicalworkshop.common.BotanicalWorkshop;
import lazersmoke.botanicalworkshop.common.block.tile.mana.lightning.TileLightningCapacitor;
import lazersmoke.botanicalworkshop.common.item.block.ItemBlockLightningCapacitor;
import lazersmoke.botanicalworkshop.common.lexicon.LexiconData;
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
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.wand.IWandable;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockLightningCapacitor extends BlockContainer implements ILexiconable, IWandable{
	public static IIcon iconCharged, iconUncharged;
	
	public BlockLightningCapacitor(){
		super(Material.rock);
		setHardness(2.0F);
		setResistance(10.0F);
		setStepSound(soundTypeMetal);
		setBlockName(LibBlockNames.LIGHTNING_CAPACITOR);
		setCreativeTab(BotanicalWorkshop.creativeTab);
		setBlockBounds(0F, 0F, 0F, 1F, 1F, 1F);
	}
	@Override
	public Block setBlockName(String name){
		GameRegistry.registerBlock(this, ItemBlockLightningCapacitor.class, name);
		return super.setBlockName(name);
	}

	@Override
	public TileEntity createNewTileEntity(World arg0, int arg1){
		return new TileLightningCapacitor();
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister){
		iconCharged = iconRegister.registerIcon(LibResources.PREFIX_MOD + getUnlocalizedName().replaceAll("tile\\.", "") + "Charged");
		iconUncharged = iconRegister.registerIcon(LibResources.PREFIX_MOD + getUnlocalizedName().replaceAll("tile\\.", "") + "Uncharged");
	}
	
	@SideOnly(Side.CLIENT) 
	@Override
	public IIcon getIcon(int side, int meta){
		switch (meta) {
			case 0:	return iconCharged;
			case 1: return iconUncharged;
	    }
		return iconUncharged;
	}

	@Override
	public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon){
		return LexiconData.lightningCapacitor;
	}
	@Override
	public boolean onUsedByWand(EntityPlayer player, ItemStack wand, World world, int x, int y, int z, int side) {
		((TileLightningCapacitor)world.getTileEntity(x, y, z)).interpretClick(ForgeDirection.getOrientation(side));
		return true;
	}
}