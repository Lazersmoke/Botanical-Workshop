package lazersmoke.botanicalworkshop.common.block;

import lazersmoke.botanicalworkshop.common.item.block.ItemBlockWeakGatewayCore;
import lazersmoke.botanicalworkshop.common.lexicon.LexiconData;
import lazersmoke.botanicalworkshop.common.lib.LibBlockNames;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import cpw.mods.fml.common.registry.GameRegistry;

//This class is all Lazersmoke
public class BlockWeakGatewayCore extends Block implements ILexiconable{
	
	IIcon iconOff, iconOn;
	
	public BlockWeakGatewayCore() {
		super(Material.wood);
		setHardness(2.0F);
		setResistance(10.0F);
		setStepSound(soundTypeWood);
		setBlockName(LibBlockNames.WEAK_GATEWAY_CORE);
		setBlockBounds(0F, 0F, 0F, 1F, 1F, 1F);
	}

	@Override
	public Block setBlockName(String name){
		GameRegistry.registerBlock(this, ItemBlockWeakGatewayCore.class, name);
		return super.setBlockName(name);
	}
	
	@Override
	public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
		return LexiconData.thaumicCore;
	}
}