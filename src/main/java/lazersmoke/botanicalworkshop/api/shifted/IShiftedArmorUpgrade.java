package lazersmoke.botanicalworkshop.api.shifted;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface IShiftedArmorUpgrade{

	/**
	 * Returns the key for this upgrade, for it to be found in the map in the API. This should ALWAYS return the same result. Looks like "PhaseUpgrade" start
	 * with caps
	 */
	public String getKey();

	/**
	 * Returns the display name for the hover over tooltip on armor that this upgrade is attached to. Can be different each time.
	 */
	public String getDisplayName();

	/**
	 * Just like ItemShiftedArmor.onArmorTick, but mana cost has already been accounted for.
	 * 
	 * @param stack
	 *            The Stack of the armor piece
	 * @param player
	 *            player wearing armor
	 * @param world
	 *            world
	 */
	public void onArmorTick(World world, EntityPlayer player, ItemStack stack);
}
