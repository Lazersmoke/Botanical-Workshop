package lazersmoke.botanicalworkshop.common.block.lightning;

import lazersmoke.botanicalworkshop.client.lib.LibResources;
import lazersmoke.botanicalworkshop.common.BotanicalWorkshop;
import lazersmoke.botanicalworkshop.common.block.tile.lightning.TileLightningFurnace;
import lazersmoke.botanicalworkshop.common.item.block.lightning.ItemBlockLightningFurnace;
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
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

// TODO lang file this
public class BlockLightningFurnace extends BlockContainer implements IWandHUD, ILexiconable{

	public static IIcon iconOff, iconOn, iconVert;

	public BlockLightningFurnace(){
		super(Material.rock);
		setHardness(2.0F);
		setResistance(10.0F);
		setStepSound(soundTypeStone);
		setBlockName(LibBlockNames.LIGHTNING_FURNACE);
		setCreativeTab(BotanicalWorkshop.creativeTab);
		setBlockBounds(0F, 0F, 0F, 1F, 1F, 1F);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister){
		iconOff = iconRegister.registerIcon(LibResources.PREFIX_MOD + getUnlocalizedName().replaceAll("tile\\.", "") + "Off");
		iconOn = iconRegister.registerIcon(LibResources.PREFIX_MOD + getUnlocalizedName().replaceAll("tile\\.", "") + "On");
		iconVert = iconRegister.registerIcon(LibResources.PREFIX_MOD + getUnlocalizedName().replaceAll("tile\\.", "") + "Vert");
	}

	@Override
	public IIcon getIcon(int side, int meta){
		return side > 1 ? (meta == 0 ? iconOff : iconOn) : iconVert;// Side > 1 means its a horizontal side
	}

	@Override
	public Block setBlockName(String name){
		GameRegistry.registerBlock(this, ItemBlockLightningFurnace.class, name);
		return super.setBlockName(name);
	}

	@Override
	public TileEntity createNewTileEntity(World arg0, int arg1){
		return new TileLightningFurnace();
	}

	@Override
	public void renderHUD(Minecraft mc, ScaledResolution res, World world, int x, int y, int z){
		((TileLightningFurnace) world.getTileEntity(x, y, z)).renderHUD(mc, res);
	}

	@Override
	public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon){
		return LexiconData.lightningFurnace;
	}

}