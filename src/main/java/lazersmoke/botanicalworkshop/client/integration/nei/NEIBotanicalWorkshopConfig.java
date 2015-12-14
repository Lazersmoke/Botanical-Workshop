package lazersmoke.botanicalworkshop.client.integration.nei;

import lazersmoke.botanicalworkshop.common.lib.LibMisc;
import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;

public class NEIBotanicalWorkshopConfig implements IConfigureNEI{

	@Override
	public String getName(){
		return LibMisc.MOD_NAME;
	}

	@Override
	public String getVersion(){
		return LibMisc.VERSION;
	}

	@Override
	public void loadConfig(){
		API.registerRecipeHandler(new RecipeHandlerGatewayTransmutation());
		API.registerUsageHandler(new RecipeHandlerGatewayTransmutation());
	}

}
