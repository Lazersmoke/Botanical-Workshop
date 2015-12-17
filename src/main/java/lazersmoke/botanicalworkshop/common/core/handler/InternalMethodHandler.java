package lazersmoke.botanicalworkshop.common.core.handler;

import lazersmoke.botanicalworkshop.api.internal.IInternalMethodHandler;
import lazersmoke.botanicalworkshop.api.internal.ILightningNetwork;
import lazersmoke.botanicalworkshop.api.recipe.RecipeGatewayTransmutation;
import lazersmoke.botanicalworkshop.client.core.handler.HUDHandler;
import lazersmoke.botanicalworkshop.common.lexicon.page.PageGatewayTransmutationRecipe;
import net.minecraft.client.gui.ScaledResolution;
import vazkii.botania.api.lexicon.LexiconPage;

public class InternalMethodHandler implements IInternalMethodHandler{

	@Override
	public LexiconPage gatewayTransmutationPage(String unlocalizedName, RecipeGatewayTransmutation recipe){
		return new PageGatewayTransmutationRecipe(unlocalizedName, recipe);
	}

	@Override
	public ILightningNetwork getLightningNetworkInstance(){
		return LightningNetworkHandler.instance;
	}

	@Override
	public void drawSimpleLightningHUD(int color, int lightning, int power, int buffer, int overflow, String name, ScaledResolution res){
		HUDHandler.drawSimpleLightningHUD(color, lightning, power, buffer, overflow, name, res);
	}
}
