package lazersmoke.botanicalworkshop.api.internal;

import lazersmoke.botanicalworkshop.api.recipe.RecipeGatewayTransmutation;
import net.minecraft.client.gui.ScaledResolution;
import vazkii.botania.api.lexicon.LexiconPage;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public interface IInternalMethodHandler{

	public LexiconPage gatewayTransmutationPage(String unlocalizedName, RecipeGatewayTransmutation recipe);

	public ILightningNetwork getLightningNetworkInstance();

	@SideOnly(Side.CLIENT)
	public void drawSimpleLightningHUD(int color, int lightning, int power, int buffer, int overflow, String name, ScaledResolution res);

}