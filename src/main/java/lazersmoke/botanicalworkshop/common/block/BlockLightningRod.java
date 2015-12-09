package lazersmoke.botanicalworkshop.common.block;

import lazersmoke.botanicalworkshop.common.BotanicalWorkshop;
import lazersmoke.botanicalworkshop.common.block.tile.mana.lightning.TileLightningRod;
import lazersmoke.botanicalworkshop.common.item.block.ItemBlockLightningRod;
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
import cpw.mods.fml.common.registry.GameRegistry;

// This class is all Lazersmoke
public class BlockLightningRod extends BlockContainer implements ILexiconable{
	public BlockLightningRod(){
		super(Material.rock);
		setHardness(2.0F);
		setResistance(10.0F);
		setStepSound(soundTypeMetal);
		setBlockName(LibBlockNames.LIGHTNING_ROD);
		setCreativeTab(BotanicalWorkshop.creativeTab);
		setBlockBounds(0F, 0F, 0F, 1F, 1F, 1F);
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
		return LexiconData.gatewayCore;
	}
}