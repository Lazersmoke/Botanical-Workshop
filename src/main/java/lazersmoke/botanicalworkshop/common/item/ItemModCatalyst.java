package lazersmoke.botanicalworkshop.common.item;

import lazersmoke.botanicalworkshop.api.mana.IGatewayMod;
import lazersmoke.botanicalworkshop.common.block.tile.TileGatewayCore;

public class ItemModCatalyst extends ItemCatalyst implements IGatewayMod{

	@Override
	public void onGatewayUpdate(TileGatewayCore gateway) {
		//NO-OP
	}

}