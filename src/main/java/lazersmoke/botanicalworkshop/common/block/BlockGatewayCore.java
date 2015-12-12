package lazersmoke.botanicalworkshop.common.block;

import lazersmoke.botanicalworkshop.client.lib.LibResources;
import lazersmoke.botanicalworkshop.common.BotanicalWorkshop;
import lazersmoke.botanicalworkshop.common.block.tile.TileGatewayCore;
import lazersmoke.botanicalworkshop.common.item.block.ItemBlockGatewayCore;
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
import net.minecraft.util.ChatComponentText;
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
public class BlockGatewayCore extends BlockContainer implements IWandable, IWandHUD, ILexiconable{

	public static IIcon iconOff, iconOn, portalTex;

	public BlockGatewayCore(){
		super(Material.wood);
		setHardness(2.0F);
		setResistance(10.0F);
		setStepSound(soundTypeStone);
		setBlockName(LibBlockNames.GATEWAY_CORE);
		setCreativeTab(BotanicalWorkshop.creativeTab);
		setBlockBounds(0F, 0F, 0F, 1F, 1F, 1F);
	}

	@Override
	public Block setBlockName(String name){
		GameRegistry.registerBlock(this, ItemBlockGatewayCore.class, name);
		return super.setBlockName(name);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister){
		iconOff = par1IconRegister.registerIcon(LibResources.PREFIX_MOD
				+ getUnlocalizedName().replaceAll("tile\\.", "") + "0");
		iconOn = par1IconRegister.registerIcon(LibResources.PREFIX_MOD
				+ getUnlocalizedName().replaceAll("tile\\.", "") + "1");
		portalTex = par1IconRegister.registerIcon(LibResources.PREFIX_MOD
				+ getUnlocalizedName().replaceAll("tile\\.", "") + "Portal");
	}

	@Override
	public IIcon getIcon(int side, int meta){
		return meta == 0 ? iconOff : iconOn;
	}

	@Override
	public TileEntity createNewTileEntity(World arg0, int arg1){
		return new TileGatewayCore();
	}

	@Override
	public void renderHUD(Minecraft mc, ScaledResolution res, World world,
			int x, int y, int z){
		( (TileGatewayCore) world.getTileEntity(x, y, z) ).renderHUD(mc, res);
	}

	@Override
	public boolean onUsedByWand(EntityPlayer player, ItemStack stack,World world, int x, int y, int z, int side){
		player.addChatMessage(new ChatComponentText("You wanded it!"));
		boolean did = ( (TileGatewayCore) world.getTileEntity(x, y, z) ).onWanded();
		return did;
	}

	@Override
	public LexiconEntry getEntry(World world, int x, int y, int z,
			EntityPlayer player, ItemStack lexicon){
		return LexiconData.gatewayCore;
	}

}