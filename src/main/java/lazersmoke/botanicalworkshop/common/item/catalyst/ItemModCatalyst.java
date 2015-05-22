package lazersmoke.botanicalworkshop.common.item.catalyst;

import net.minecraft.entity.item.EntityItem;
import lazersmoke.botanicalworkshop.api.mana.IGatewayMod;
import lazersmoke.botanicalworkshop.common.block.tile.TileGatewayCore;

public class ItemModCatalyst extends ItemCatalyst implements IGatewayMod{

	public ItemModCatalyst(String name) {
		super(name);
	}

	@Override
	public void onGatewayUpdate(TileGatewayCore gateway, EntityItem catalyst) {
		//NO-OP
	}

}