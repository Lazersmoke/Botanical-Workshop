package lazersmoke.botanicalworkshop.api.mana;

import net.minecraft.entity.item.EntityItem;
import lazersmoke.botanicalworkshop.common.block.tile.TileGatewayCore;

/**
 * Any class implementing the interface will be considered an Active Gateway Catalyst on top of being a regular catalyst, and will have its onGatewayUpdate function called on every gateway update.
 * 
 * @author Lazersmoke
 */
public interface IGatewayMod extends IGatewayCatalyst{

	/**
	 * Called every tick that the gateway is open and the catalyst is inside of it.
	 * 
	 * @param gateway
	 * The gateway the catalyst is attached to
	 * @param catalyst
	 * The catalyst item entity that is being updated
	 */
	public void onGatewayUpdate(TileGatewayCore gateway, EntityItem catalyst);
}