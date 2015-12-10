package lazersmoke.botanicalworkshop.common.item.catalyst;

import lazersmoke.botanicalworkshop.common.block.BlockLightningCore;
import lazersmoke.botanicalworkshop.common.block.tile.TileGatewayCore;
import lazersmoke.botanicalworkshop.common.block.tile.mana.lightning.TileLightningCore;
import lazersmoke.botanicalworkshop.common.lib.LibItemNames;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
//TODO texture this
public class ItemLightningConsumingCatalyst extends ItemActiveCatalyst{

	public ItemLightningConsumingCatalyst(){
		super(LibItemNames.LIGHTNING_GENERATION_CATALYST);
	}

	@Override
	public void onGatewayUpdate(TileGatewayCore gateway, EntityItem catalyst){
		Block powerCore = catalyst.worldObj.getBlock(gateway.xCoord, gateway.yCoord + 6, gateway.zCoord);
		if(powerCore instanceof BlockLightningCore){
			gateway.getWorldObj().setBlockMetadataWithNotify(gateway.xCoord, gateway.yCoord + 6, gateway.zCoord, 1, 1);
			TileLightningCore tileLightningCore = (TileLightningCore) catalyst.worldObj.getTileEntity(gateway.xCoord, gateway.yCoord + 6, gateway.zCoord);
			tileLightningCore.poke();// *POKE*
			if(!gateway.isFull() && gateway.getAvailableSpaceForMana() >= 50000){
				gateway.recieveMana(50000);
				tileLightningCore.addLightning(-50);
			}
		}
	}
}