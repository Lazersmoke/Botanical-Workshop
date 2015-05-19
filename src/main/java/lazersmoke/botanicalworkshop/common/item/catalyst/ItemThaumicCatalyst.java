package lazersmoke.botanicalworkshop.common.item.catalyst;

import thaumcraft.api.aspects.Aspect;
import lazersmoke.botanicalworkshop.common.BotanicalWorkshop;
import lazersmoke.botanicalworkshop.common.block.BlockThaumicCore;
import lazersmoke.botanicalworkshop.common.block.tile.TileGatewayCore;
import lazersmoke.botanicalworkshop.common.block.tile.TileThaumicCore;
import lazersmoke.botanicalworkshop.common.lib.LibItemNames;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraftforge.common.util.ForgeDirection;


public class ItemThaumicCatalyst extends ItemModCatalyst{
	
	public ItemThaumicCatalyst(){
		super();
		setUnlocalizedName(LibItemNames.THAUMIC_CATALYST);
	}
	
	@Override
	public void onGatewayUpdate(TileGatewayCore gateway, EntityItem catalyst){
		Block thaumicCore = catalyst.worldObj.getBlock(gateway.xCoord, gateway.yCoord + 6, gateway.zCoord);
		if(thaumicCore instanceof BlockThaumicCore){
			BotanicalWorkshop.logger.info("Block found!");
			TileThaumicCore tileThaumicCore = (TileThaumicCore) catalyst.worldObj.getTileEntity(gateway.xCoord, gateway.yCoord + 6, gateway.zCoord);
			if(tileThaumicCore.getEssentiaAmount(ForgeDirection.UP) > 0){
				BotanicalWorkshop.logger.info("Extracting Aspects!");
				gateway.addMana(tileThaumicCore.takeEssentia(Aspect.MAGIC, tileThaumicCore.getEssentiaAmount(ForgeDirection.UP), ForgeDirection.UP) * 5000);
			}
		}
	}
}