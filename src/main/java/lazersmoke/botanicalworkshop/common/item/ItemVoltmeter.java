package lazersmoke.botanicalworkshop.common.item;

import lazersmoke.botanicalworkshop.api.mana.lightning.IBotanicalLightningBlock;
import lazersmoke.botanicalworkshop.common.lib.LibItemNames;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class ItemVoltmeter extends ItemMod{
	public ItemVoltmeter(){
		super(LibItemNames.VOLTMETER);
	}

	/**
	 * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
	 */
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player){
		MovingObjectPosition movingobjectposition = getMovingObjectPositionFromPlayer(world, player, true);

		if(movingobjectposition == null)
			return stack;
		else{
			if(movingobjectposition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK){
				int i = movingobjectposition.blockX;
				int j = movingobjectposition.blockY;
				int k = movingobjectposition.blockZ;
				if(world.getTileEntity(i, j, k) instanceof IBotanicalLightningBlock){
					IBotanicalLightningBlock theBlock = (IBotanicalLightningBlock) world.getTileEntity(i, j, k);
					world.playSoundEffect(i, j, k, "note.harp", 0.3F, 1.0F);
					if(world.isRemote){
						player.addChatMessage(new ChatComponentText("Client says:"));
						player.addChatMessage(new ChatComponentText(" " + theBlock.getPowerThreshold() + "/" + theBlock.getBufferThreshold() + "/" + theBlock.getOverflowThreshold()));
						player.addChatMessage(new ChatComponentText(" Current Push Radius: " + theBlock.getLightningPushRange()));
						player.addChatMessage(new ChatComponentText(" Current Lightning: " + theBlock.getCurrentLightning()));
						player.addChatMessage(new ChatComponentText(" Current Conductivity: " + theBlock.getConductivity()));
					}
					if(!world.isRemote){
						player.addChatMessage(new ChatComponentText("Server says:"));
						player.addChatMessage(new ChatComponentText(" " + theBlock.getPowerThreshold() + "/" + theBlock.getBufferThreshold() + "/" + theBlock.getOverflowThreshold()));
						player.addChatMessage(new ChatComponentText(" Current Push Radius: " + theBlock.getLightningPushRange()));
						player.addChatMessage(new ChatComponentText(" Current Lightning: " + theBlock.getCurrentLightning()));
						player.addChatMessage(new ChatComponentText(" Current Conductivity: " + theBlock.getConductivity()));
					}
				}
			}
		}
		return stack;
	}
}
