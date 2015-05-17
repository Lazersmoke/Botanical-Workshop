package lazersmoke.botanicalworkshop.common.item.block;
//This class is all Vazkii
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import lazersmoke.botanicalworkshop.client.lib.LibResources;//Need my names :)
import vazkii.botania.common.achievement.ICraftAchievement;
import vazkii.botania.common.achievement.IPickupAchievement;

public class ItemBlockWithMetadataAndName extends ItemBlockWithMetadata implements IPickupAchievement, ICraftAchievement {

	public ItemBlockWithMetadataAndName(Block par2Block) {
		super(par2Block, par2Block);
	}

	@Override
	public String getUnlocalizedNameInefficiently(ItemStack par1ItemStack) {
		return super.getUnlocalizedNameInefficiently(par1ItemStack).replaceAll("tile.", "tile." + LibResources.PREFIX_MOD);
	}

	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack) {
		return super.getUnlocalizedName(par1ItemStack) + par1ItemStack.getItemDamage();
	}

	@Override
	public Achievement getAchievementOnCraft(ItemStack stack, EntityPlayer player, IInventory matrix) {
		return field_150939_a instanceof ICraftAchievement ? ((ICraftAchievement) field_150939_a).getAchievementOnCraft(stack, player, matrix) : null;
	}

	@Override
	public Achievement getAchievementOnPickup(ItemStack stack, EntityPlayer player, EntityItem item) {
		return field_150939_a instanceof IPickupAchievement ? ((IPickupAchievement) field_150939_a).getAchievementOnPickup(stack, player, item) : null;
	}

}