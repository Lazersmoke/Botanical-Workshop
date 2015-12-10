package lazersmoke.botanicalworkshop.common.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import lazersmoke.botanicalworkshop.api.mana.lightning.IBotanicalLightningBlock;
import lazersmoke.botanicalworkshop.api.mana.lightning.IBotanicalVoltmeter;
import lazersmoke.botanicalworkshop.common.lib.LibItemNames;
//TODO texture this
public class ItemVoltmeter extends ItemMod implements IBotanicalVoltmeter{
	public ItemVoltmeter() {
		super(LibItemNames.VOLTMETER);
	}
	/**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
    {
		MovingObjectPosition movingobjectposition = getMovingObjectPositionFromPlayer(world, player, true);

		if(movingobjectposition == null)
			return stack;
		else {
			if(movingobjectposition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
				int i = movingobjectposition.blockX;
				int j = movingobjectposition.blockY;
				int k = movingobjectposition.blockZ;
				if(world.getTileEntity(i, j, k) instanceof IBotanicalLightningBlock){
					IBotanicalLightningBlock theBlock = (IBotanicalLightningBlock) world.getTileEntity(i, j, k);
					world.playSoundEffect(i, j, k, "note.harp", 0.3F, 1.0F);
					player.addChatMessage(new ChatComponentText("Current Lightning: " + theBlock.getCurrentLightning()));
					player.addChatMessage(new ChatComponentText("Current Conductivity: " + theBlock.getConductivity()));
				}
			}
		}
		return stack;
	}
}
