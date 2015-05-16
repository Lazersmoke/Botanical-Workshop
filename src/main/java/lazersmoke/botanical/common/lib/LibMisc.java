package lazersmoke.botanical.common.lib;

public final class LibMisc {
	
	//Mod Constants
	public static final String MOD_ID = "BotanicalWorkshop";
	public static final String MOD_NAME = MOD_ID;
	public static final String BUILD = "GRADLE:BUILD";
	public static final String VERSION = "GRADLE:VERSION" + BUILD;
	public static final String DEPENDENCIES = vazkii.botania.common.lib.LibMisc.DEPENDENCIES + ";required-after:Botania";
	
	// Network Constants
	public static final String NETWORK_CHANNEL = MOD_ID;

	// Proxy Constants
	public static final String PROXY_COMMON = "lazersmoke.botanical.common.core.proxy.CommonProxy";
	public static final String PROXY_CLIENT = "lazersmoke.botanical.client.core.proxy.ClientProxy";
	public static final String GUI_FACTORY = "lazersmoke.botanical.client.core.proxy.GuiFactory";
}
