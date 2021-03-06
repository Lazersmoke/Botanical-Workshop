package lazersmoke.botanicalworkshop.common.item.catalyst;

import lazersmoke.botanicalworkshop.common.block.lightning.BlockLightningCore;
import lazersmoke.botanicalworkshop.common.block.tile.TileGatewayCore;
import lazersmoke.botanicalworkshop.common.block.tile.lightning.TileLightningCore;
import lazersmoke.botanicalworkshop.common.lib.LibConfigs;
import lazersmoke.botanicalworkshop.common.lib.LibItemNames;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;

public class ItemLightningConsumingCatalyst extends ItemActiveCatalyst{

	public ItemLightningConsumingCatalyst(){
		super(LibItemNames.LIGHTNING_CONSUMING_CATALYST);
	}

	@Override
	public void onGatewayUpdate(TileGatewayCore gateway, EntityItem catalyst){
		final Block powerCore = catalyst.worldObj.getBlock(gateway.xCoord, gateway.yCoord + 6, gateway.zCoord);
		if(powerCore instanceof BlockLightningCore){
			gateway.getWorldObj().setBlockMetadataWithNotify(gateway.xCoord, gateway.yCoord + 6, gateway.zCoord, 1, 3);
			final TileLightningCore tileLightningCore = (TileLightningCore) catalyst.worldObj.getTileEntity(gateway.xCoord, gateway.yCoord + 6, gateway.zCoord);
			tileLightningCore.poke();// *POKE*
			if(!gateway.isFull() && gateway.getAvailableSpaceForMana() >= 50 * LibConfigs.LIGHTNING_MANA_RATE && tileLightningCore.addLightning(-50))
				gateway.recieveMana(50 * LibConfigs.LIGHTNING_MANA_RATE);
		}
	}
}