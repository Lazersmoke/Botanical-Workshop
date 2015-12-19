package lazersmoke.botanicalworkshop.common.block.lightning;

import lazersmoke.botanicalworkshop.client.lib.LibResources;
import lazersmoke.botanicalworkshop.common.BotanicalWorkshop;
import lazersmoke.botanicalworkshop.common.block.tile.lightning.TileThaumtanicalTransposer;
import lazersmoke.botanicalworkshop.common.item.block.lightning.ItemBlockThaumtanicalTransposer;
import lazersmoke.botanicalworkshop.common.lexicon.LexiconData;
import lazersmoke.botanicalworkshop.common.lib.LibBlockNames;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.wand.IWandHUD;
import vazkii.botania.api.wand.IWandable;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

// This class is all Lazersmoke
public class BlockThaumtanicalTransposer extends BlockContainer implements IWandable, IWandHUD, ILexiconable{

	public static IIcon iconOff, iconOn, iconOffInput, iconOnInput;

	public BlockThaumtanicalTransposer(){
		super(Material.rock);
		setHardness(2.0F);
		setResistance(10.0F);
		setStepSound(soundTypeStone);
		setBlockName(LibBlockNames.THAUMTANICAL_TRANSPOSER);
		setCreativeTab(BotanicalWorkshop.creativeTab);
		setBlockBounds(0F, 0F, 0F, 1F, 1F, 1F);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister){
		iconOff = iconRegister.registerIcon(LibResources.PREFIX_MOD + getUnlocalizedName().replaceAll("tile\\.", "") + "Off");
		iconOn = iconRegister.registerIcon(LibResources.PREFIX_MOD + getUnlocalizedName().replaceAll("tile\\.", "") + "On");

		iconOffInput = iconRegister.registerIcon(LibResources.PREFIX_MOD + getUnlocalizedName().replaceAll("tile\\.", "") + "OffInput");
		iconOnInput = iconRegister.registerIcon(LibResources.PREFIX_MOD + getUnlocalizedName().replaceAll("tile\\.", "") + "OnInput");
	}

	@Override
	public IIcon getIcon(int side, int meta){
		return (meta >> 1) == side ? ((meta & 1) == 1 ? iconOnInput : iconOffInput) : ((meta & 1) == 1 ? iconOn : iconOff);
	}

	@Override
	public Block setBlockName(String name){
		GameRegistry.registerBlock(this, ItemBlockThaumtanicalTransposer.class, name);
		return super.setBlockName(name);
	}

	@Override
	public TileEntity createNewTileEntity(World arg0, int arg1){
		return new TileThaumtanicalTransposer();
	}

	@Override
	public void renderHUD(Minecraft mc, ScaledResolution res, World world, int x, int y, int z){
		((TileThaumtanicalTransposer) world.getTileEntity(x, y, z)).renderHUD(mc, res);
	}

	@Override
	public boolean onUsedByWand(EntityPlayer player, ItemStack stack, World world, int x, int y, int z, int side){
		((TileThaumtanicalTransposer) world.getTileEntity(x, y, z)).onWanded(side);
		world.playSoundEffect(x, y, z, "note.harp", 0.3F, 1.0F);
		return true;
	}

	@Override
	public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon){
		return LexiconData.thaumtanicalTransposer;
	}

}