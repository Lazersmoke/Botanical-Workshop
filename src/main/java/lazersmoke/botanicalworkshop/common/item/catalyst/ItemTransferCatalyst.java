package lazersmoke.botanicalworkshop.common.item.catalyst;

import java.util.List;

import lazersmoke.botanicalworkshop.common.block.tile.TileGatewayCore;
import lazersmoke.botanicalworkshop.common.lib.LibItemNames;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import vazkii.botania.common.core.helper.ItemNBTHelper;

public class ItemTransferCatalyst extends ItemActiveCatalyst {

	public ItemTransferCatalyst() {
		super(LibItemNames.MANA_TRANSFER_CATALYST);
	}

	@Override
	public void onGatewayUpdate(TileGatewayCore gateway, EntityItem catalyst) {
		TileGatewayCore boundGateway = ((TileGatewayCore) gateway.getWorldObj().getTileEntity(ItemNBTHelper.getInt(catalyst.getEntityItem(), "boundGatewayX", 0), ItemNBTHelper.getInt(catalyst.getEntityItem(), "boundGatewayY", -1), ItemNBTHelper.getInt(catalyst.getEntityItem(), "boundGatewayZ", 0)));
		if(gateway.uuid != boundGateway.uuid && gateway.getAvailableSpaceForMana() >= 1000 && boundGateway.getCurrentMana() > 11000){
			gateway.recieveMana(1000);
			boundGateway.recieveMana(-1000);
		}
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List loreLineList, boolean par4) {
		for (int i = 0; i < 4; i++)
			loreLineList.add(StatCollector.translateToLocal("botanicalworkshopmisc.manaTransferCatalyst" + i));
	}
}