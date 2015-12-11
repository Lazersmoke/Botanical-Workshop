package lazersmoke.botanicalworkshop.common.block;

import lazersmoke.botanicalworkshop.client.lib.LibResources;
import lazersmoke.botanicalworkshop.common.BotanicalWorkshop;
import lazersmoke.botanicalworkshop.common.block.tile.mana.lightning.TileLightningCore;
import lazersmoke.botanicalworkshop.common.item.block.ItemBlockThaumicCore;
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
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

// This class is all Lazersmoke
/**Metadata guide:
 * 0 = disabled
 * 1 = consuming
 * 2 = generating 
 */
public class BlockLightningCore extends BlockContainer implements ILexiconable{

	public static IIcon iconGenerating, iconConsuming, iconDisabled;

	public BlockLightningCore(){
		super(Material.rock);
		setHardness(2.0F);
		setResistance(10.0F);
		setStepSound(soundTypeStone);
		setBlockName(LibBlockNames.LIGHTNING_CORE);
		setCreativeTab(BotanicalWorkshop.creativeTab);
		setBlockBounds(0F, 0F, 0F, 1F, 1F, 1F);
	}

	@Override
	public Block setBlockName(String name){
		GameRegistry.registerBlock(this, ItemBlockThaumicCore.class, name);
		return super.setBlockName(name);
	}

	@Override
	public TileEntity createNewTileEntity(World arg0, int arg1){
		return new TileLightningCore();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister){
		iconGenerating = par1IconRegister.registerIcon(LibResources.PREFIX_MOD + getUnlocalizedName().replaceAll("tile\\.", "") + "Generating");
		iconConsuming = par1IconRegister.registerIcon(LibResources.PREFIX_MOD + getUnlocalizedName().replaceAll("tile\\.", "") + "Consuming");
		iconDisabled = par1IconRegister.registerIcon(LibResources.PREFIX_MOD + getUnlocalizedName().replaceAll("tile\\.", "") + "Disabled");
	}
	@Override
	public IIcon getIcon(int side, int meta){
		return meta == 0 ? iconDisabled : meta == 1 ? iconConsuming : meta == 2 ? iconGenerating : iconDisabled;
	}

	@Override
	public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon){
		return LexiconData.lightningCore;
	}
}