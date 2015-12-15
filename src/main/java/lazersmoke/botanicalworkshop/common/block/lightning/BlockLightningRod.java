package lazersmoke.botanicalworkshop.common.block.lightning;

import lazersmoke.botanicalworkshop.client.lib.LibRenderIDs;
import lazersmoke.botanicalworkshop.common.BotanicalWorkshop;
import lazersmoke.botanicalworkshop.common.block.tile.lightning.TileLightningRod;
import lazersmoke.botanicalworkshop.common.item.block.lightning.ItemBlockLightningRod;
import lazersmoke.botanicalworkshop.common.lexicon.LexiconData;
import lazersmoke.botanicalworkshop.common.lib.LibBlockNames;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.wand.IWandable;
import cpw.mods.fml.common.registry.GameRegistry;

public class BlockLightningRod extends BlockContainer implements ILexiconable, IWandable{
	public BlockLightningRod(){
		super(Material.rock);
		setHardness(2.0F);
		setResistance(10.0F);
		setStepSound(soundTypeMetal);
		setBlockName(LibBlockNames.LIGHTNING_ROD);
		setCreativeTab(BotanicalWorkshop.creativeTab);

		float f = 1F / 16F * 4F;
		setBlockBounds(f, 0F, f, 1F - f, 1F, 1F - f);
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
	public int getRenderType(){
		return LibRenderIDs.idRod;
	}

	@Override
	public Block setBlockName(String name){
		GameRegistry.registerBlock(this, ItemBlockLightningRod.class, name);
		return super.setBlockName(name);
	}

	@Override
	public TileEntity createNewTileEntity(World arg0, int arg1){
		return new TileLightningRod();
	}

	@Override
	public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon){
		return LexiconData.lightningRod;
	}

	@Override
	public boolean onUsedByWand(EntityPlayer player, ItemStack wand, World world, int x, int y, int z, int side){
		((TileLightningRod) world.getTileEntity(x, y, z)).onWanded();
		return true;
	}
}