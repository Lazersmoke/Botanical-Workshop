package lazersmoke.botanicalworkshop.common.item;

import lazersmoke.botanicalworkshop.api.mana.lightning.IBotanicalLightningCellAcceptor;
import lazersmoke.botanicalworkshop.client.lib.LibResources;
import lazersmoke.botanicalworkshop.common.lib.LibItemNames;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import vazkii.botania.common.core.helper.ItemNBTHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemLightningCell extends ItemMod{
	public ItemLightningCell(){
		super(LibItemNames.LIGHTNING_CELL);
		this.maxStackSize = 1;
	}

	private IIcon iconEmpty, iconEjecting, iconAccepting;
	private final int MAX_LIGHTNING_AMOUNT = 1000;
	private final int MAX_LIGHTNING_TRANSFER_RATE = 100;

	private final String TAG_LIGHTNING = "botanicalLightning";
	private final String TAG_EJECTING = "botanicalEjectingLightning";

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconIndex(ItemStack stack){
		return getIcon(stack, 0);
	}

	@Override
	public IIcon getIcon(ItemStack stack, int pass){
		return ItemNBTHelper.getInt(stack, TAG_LIGHTNING, 0) == 0 ? iconEmpty : ItemNBTHelper.getBoolean(stack, TAG_EJECTING, false) ? iconEjecting : iconAccepting;
	}

	@Override
	public void registerIcons(IIconRegister reg){
		itemIcon = reg.registerIcon(LibResources.PREFIX_MOD + getUnlocalizedName().replaceAll("item\\.", "") + "Input");// Default
		iconEmpty = reg.registerIcon(LibResources.PREFIX_MOD + getUnlocalizedName().replaceAll("item\\.", "") + "Empty");
		iconEjecting = reg.registerIcon(LibResources.PREFIX_MOD + getUnlocalizedName().replaceAll("item\\.", "") + "Output");
		iconAccepting = reg.registerIcon(LibResources.PREFIX_MOD + getUnlocalizedName().replaceAll("item\\.", "") + "Input");
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player){
		final MovingObjectPosition daMOP = getMovingObjectPositionFromPlayer(world, player, true);

		if(player.isSneaking()){
			ItemNBTHelper.setBoolean(stack, TAG_EJECTING, !ItemNBTHelper.getBoolean(stack, TAG_EJECTING, false));
			if(world.isRemote)
				player.addChatMessage(new ChatComponentText("You are now " + (ItemNBTHelper.getBoolean(stack, TAG_EJECTING, false) ? "dispensing" : "accepting") + " lightning."));
			return stack;
		}else if(daMOP != null && daMOP.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK){
			final int hitX = daMOP.blockX;
			final int hitY = daMOP.blockY;
			final int hitZ = daMOP.blockZ;
			if(world.getTileEntity(hitX, hitY, hitZ) instanceof IBotanicalLightningCellAcceptor){
				final IBotanicalLightningCellAcceptor theBlock = (IBotanicalLightningCellAcceptor) world.getTileEntity(hitX, hitY, hitZ);
				world.playSoundEffect(hitX, hitY, hitZ, "note.harp", 0.3F, 1.0F);
				if(ItemNBTHelper.getBoolean(stack, TAG_EJECTING, false))
					ItemNBTHelper.setInt(stack, TAG_LIGHTNING, ItemNBTHelper.getInt(stack, TAG_LIGHTNING, 0) - theBlock.blindAddLightning(Math.min(MAX_LIGHTNING_TRANSFER_RATE, ItemNBTHelper.getInt(stack, TAG_LIGHTNING, 0))));
				else if(!ItemNBTHelper.getBoolean(stack, TAG_EJECTING, false) && ItemNBTHelper.getInt(stack, TAG_LIGHTNING, 0) < MAX_LIGHTNING_AMOUNT)
					ItemNBTHelper.setInt(stack, TAG_LIGHTNING, ItemNBTHelper.getInt(stack, TAG_LIGHTNING, 0) - theBlock.blindAddLightning(-Math.min(MAX_LIGHTNING_AMOUNT - ItemNBTHelper.getInt(stack, TAG_LIGHTNING, 0), MAX_LIGHTNING_TRANSFER_RATE)));
			}
		}
		if(world.isRemote)
			player.addChatMessage(new ChatComponentText("You now have " + ItemNBTHelper.getInt(stack, TAG_LIGHTNING, 0) + " lightning."));
		return stack;
	}
}
