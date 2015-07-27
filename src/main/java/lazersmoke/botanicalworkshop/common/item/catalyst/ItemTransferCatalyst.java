package lazersmoke.botanicalworkshop.common.item.catalyst;

import java.util.List;

import lazersmoke.botanicalworkshop.common.block.tile.TileGatewayCore;
import lazersmoke.botanicalworkshop.common.lib.LibItemNames;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import vazkii.botania.common.core.helper.ItemNBTHelper;

public class ItemTransferCatalyst extends ItemActiveCatalyst {

	public ItemTransferCatalyst() {
		super(LibItemNames.MANA_TRANSFER_CATALYST);
	}

	@Override
	public void onGatewayUpdate(TileGatewayCore gateway, EntityItem catalyst) {
		TileGatewayCore boundGateway = ((TileGatewayCore) gateway.getWorldObj().getTileEntity(ItemNBTHelper.getInt(catalyst.getEntityItem(), "boundGatewayX", 0),
				ItemNBTHelper.getInt(catalyst.getEntityItem(), "boundGatewayY", -1), ItemNBTHelper.getInt(catalyst.getEntityItem(), "boundGatewayZ", 0)));
		if (gateway != null && boundGateway !=null && gateway.uuid != boundGateway.uuid && gateway.getAvailableSpaceForMana() >= 5000 && boundGateway.getCurrentMana() > 15000) {
			gateway.recieveMana(5000);
			boundGateway.recieveMana(-5000);
		}
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int par7, float par8, float par9, float par10) {
		TileEntity tile = world.getTileEntity(x, y, z);
		if(tile != null && tile instanceof TileGatewayCore){
			ItemNBTHelper.setInt(stack, "boundGatewayX", x);
			ItemNBTHelper.setInt(stack, "boundGatewayY", y);
			ItemNBTHelper.setInt(stack, "boundGatewayZ", z);
			return true;
		}
		return false;
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List loreLineList, boolean par4) {
		for (int i = 0; i < 4; i++)
			loreLineList.add(StatCollector.translateToLocal("botanicalworkshopmisc.manaTransferCatalyst" + i));

		if (ItemNBTHelper.getInt(stack, "boundGatewayY", -1) != -1)
			loreLineList.add("Gateway: [" + ItemNBTHelper.getInt(stack, "boundGatewayX", -1) + ", " + ItemNBTHelper.getInt(stack, "boundGatewayY", -1) + ", "
					+ ItemNBTHelper.getInt(stack, "boundGatewayZ", -1) + "]");
	}
}