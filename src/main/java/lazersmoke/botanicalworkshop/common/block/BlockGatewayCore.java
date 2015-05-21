package lazersmoke.botanicalworkshop.common.block;

import lazersmoke.botanicalworkshop.client.core.helper.IconHelper;
import lazersmoke.botanicalworkshop.common.block.tile.TileGatewayCore;
import lazersmoke.botanicalworkshop.common.item.block.ItemBlockGatewayCore;
import lazersmoke.botanicalworkshop.common.lexicon.LexiconData;
import lazersmoke.botanicalworkshop.common.lib.LibBlockNames;
import net.minecraft.block.Block;
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

//This class is all Lazersmoke
public class BlockGatewayCore extends BlockModContainer implements IWandable, IWandHUD, ILexiconable{
	
	IIcon iconOff, iconOn;
	
	public BlockGatewayCore() {
		super(Material.rock);
		setHardness(2.0F);
		setResistance(10.0F);
		setStepSound(soundTypeStone);
		setBlockName(LibBlockNames.GATEWAY_CORE);
		setBlockBounds(0F, 0F, 0F, 1F, 1F, 1F);
	}

	@Override
	public Block setBlockName(String name){
		GameRegistry.registerBlock(this, ItemBlockGatewayCore.class, name);
		return super.setBlockName(name);
	}
	
	@Override
	protected boolean shouldRegisterInNameSet() {
		return false;
	}
	
	@Override
	public void registerBlockIcons(IIconRegister register) {
		iconOff = IconHelper.forBlock(register, this, 0);
		iconOn = IconHelper.forBlock(register, this, 1);
	}

	@Override
	public IIcon getIcon(int side, int meta) {
		return meta == 0 ? iconOff : iconOn;
	}
	
	@Override
	public TileEntity createNewTileEntity(World arg0, int arg1) {
		return new TileGatewayCore();
	}
	
	@Override
	public void renderHUD(Minecraft mc, ScaledResolution res, World world, int x, int y, int z) {
		((TileGatewayCore) world.getTileEntity(x, y, z)).renderHUD(mc, res);
	}

	@Override
	public boolean onUsedByWand(EntityPlayer player, ItemStack stack, World world, int x, int y, int z, int side) {
		boolean did = ((TileGatewayCore) world.getTileEntity(x, y, z)).onWanded();
		return did;
	}
	
	@Override
	public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
		return LexiconData.gatewayCore;
	}
	
}