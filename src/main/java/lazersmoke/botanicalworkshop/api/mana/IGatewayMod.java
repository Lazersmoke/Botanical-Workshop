package lazersmoke.botanicalworkshop.api.mana;

import net.minecraft.entity.item.EntityItem;
import lazersmoke.botanicalworkshop.common.block.tile.TileGatewayCore;

/**Any class implementing the interface will be considered a
 * Gateway mod catalyst on top of being a regular catalyst
 * @author Lazersmoke
 *
 */
public interface IGatewayMod extends IGatewayCatalyst{
	/**Called every tick that the gateway is open and the catalyst
	 * is inside of it.
	 * @param gateway The Gateway the mod is attached to
	 */
	public void onGatewayUpdate(TileGatewayCore gateway, EntityItem catalyst);
}