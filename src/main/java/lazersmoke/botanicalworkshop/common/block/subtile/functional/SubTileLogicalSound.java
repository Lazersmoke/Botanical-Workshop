package lazersmoke.botanicalworkshop.common.block.subtile.functional;

import com.mojang.authlib.GameProfile;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.common.util.FakePlayer;
import vazkii.botania.api.subtile.SubTileFunctional;

public class SubTileLogicalSound extends SubTileFunctional{
	@Override
	public void onUpdate() {
		super.onUpdate();
		if(supertile.getWorldObj().isRemote)
			return;
		Minecraft mc = Minecraft.getMinecraft();
		EntityPlayer fakeplayer = new FakePlayer(MinecraftServer.getServer().worldServerForDimension(mc.thePlayer.dimension), new GameProfile(null, "SoundLogical"));
		fakeplayer.addChatMessage(new ChatComponentTranslation("botanicalworkshop.soundLogicChatSpam").setChatStyle(new ChatStyle().setColor(EnumChatFormatting.RED)));
	}
}