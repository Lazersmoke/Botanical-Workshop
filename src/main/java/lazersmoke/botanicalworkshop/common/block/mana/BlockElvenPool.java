package lazersmoke.botanicalworkshop.common.block.mana;

// This class is mostly Vazkii
import java.util.ArrayList;
import java.util.List;

import lazersmoke.botanicalworkshop.client.lib.LibRenderIDs;
import lazersmoke.botanicalworkshop.common.BotanicalWorkshop;
import lazersmoke.botanicalworkshop.common.block.tile.mana.TileElvenPool;
import lazersmoke.botanicalworkshop.common.item.block.mana.ItemBlockElvenPool;
import lazersmoke.botanicalworkshop.common.lexicon.LexiconData;
import lazersmoke.botanicalworkshop.common.lib.LibBlockNames;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import vazkii.botania.api.internal.VanillaPacketDispatcher;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.wand.IWandHUD;
import vazkii.botania.api.wand.IWandable;
import cpw.mods.fml.common.registry.GameRegistry;

public class BlockElvenPool extends BlockContainer implements IWandHUD, IWandable, ILexiconable{

	boolean lastFragile = false;

	public BlockElvenPool(){
		super(Material.rock);
		setHardness(2.0F);
		setResistance(10.0F);
		setStepSound(soundTypeStone);
		setBlockName(LibBlockNames.ELVEN_POOL);
		setCreativeTab(BotanicalWorkshop.creativeTab);
		setBlockBounds(0F, 0F, 0F, 1F, 0.5F, 1F);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta){
		return new TileElvenPool();
	}

	@Override
	public Block setBlockName(String name){
		GameRegistry.registerBlock(this, ItemBlockElvenPool.class, name);
		return super.setBlockName(name);
	}

	@Override
	public void registerBlockIcons(IIconRegister IconRegister){
		// NO-OP
	}

	@Override
	public int damageDropped(int meta){
		return meta;
	}

	@Override
	public void breakBlock(World par1World, int par2, int par3, int par4, Block par5, int par6){
		final TileElvenPool pool = (TileElvenPool) par1World.getTileEntity(par2, par3, par4);
		lastFragile = pool.fragile;
		super.breakBlock(par1World, par2, par3, par4, par5, par6);
	}

	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune){
		final ArrayList<ItemStack> drops = new ArrayList<ItemStack>();

		if(!lastFragile)
			drops.add(new ItemStack(this, 1, metadata));

		return drops;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void getSubBlocks(Item item, CreativeTabs creativeTab, @SuppressWarnings("rawtypes") List subblocks){
		subblocks.add(new ItemStack(item, 1, 0));
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity){
		if(entity instanceof EntityItem){
			final TileElvenPool tile = (TileElvenPool) world.getTileEntity(x, y, z);
			if(tile.collideEntityItem((EntityItem) entity))
				VanillaPacketDispatcher.dispatchTEToNearbyPlayers(world, x, y, z);
		}
	}

	@Override
	public boolean isOpaqueCube(){
		return false;
	}

	@Override
	public boolean renderAsNormalBlock(){
		return false;
	}

	@Override
	public IIcon getIcon(int par1, int par2){
		return vazkii.botania.common.block.ModFluffBlocks.elfQuartz.getIcon(par1, 0);
	}

	@Override
	public int getRenderType(){
		return LibRenderIDs.idPool;
	}

	@Override
	public boolean hasComparatorInputOverride(){
		return true;
	}

	@Override
	public int getComparatorInputOverride(World par1World, int par2, int par3, int par4, int par5){
		final TileElvenPool pool = (TileElvenPool) par1World.getTileEntity(par2, par3, par4);
		int val = (int) ((double) pool.getCurrentMana() / (double) pool.manaCap * 15.0);
		if(pool.getCurrentMana() > 0)
			val = Math.max(val, 1);

		return val;
	}

	@Override
	public void renderHUD(Minecraft mc, ScaledResolution res, World world, int x, int y, int z){
		((TileElvenPool) world.getTileEntity(x, y, z)).renderHUD(mc, res);
	}

	@Override
	public boolean onUsedByWand(EntityPlayer player, ItemStack stack, World world, int x, int y, int z, int side){
		((TileElvenPool) world.getTileEntity(x, y, z)).onWanded(player, stack);
		return true;
	}

	@Override
	public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon){
		return LexiconData.elvenPool;
	}
}
