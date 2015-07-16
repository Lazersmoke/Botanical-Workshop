package lazersmoke.botanicalworkshop.common.lib;

import net.minecraftforge.common.util.ForgeDirection;

public final class LibMisc {
	
	//Mod Constants
	public static final String MOD_ID = "BotanicalWorkshop";
	public static final String MOD_NAME = MOD_ID;
	public static final String BUILD = "GRADLE:BUILD";
	public static final String VERSION = "GRADLE:VERSION" + BUILD;
	public static final String DEPENDENCIES = "required-after:Botania;required-after:PlayerAPI";
	
	// Network Constants
	public static final String NETWORK_CHANNEL = MOD_ID;

	// Proxy Constants
	public static final String PROXY_COMMON = "lazersmoke.botanicalworkshop.common.core.proxy.CommonProxy";
	public static final String PROXY_CLIENT = "lazersmoke.botanicalworkshop.client.core.proxy.ClientProxy";
	
	//Misc Constants
	public static final ForgeDirection[] CARDINAL_DIRECTIONS = new ForgeDirection[] {
		ForgeDirection.NORTH, ForgeDirection.SOUTH, ForgeDirection.EAST, ForgeDirection.WEST
	};
	
	public static final ForgeDirection[] ALL_DIRECTIONS = new ForgeDirection[] {
		ForgeDirection.NORTH, ForgeDirection.SOUTH, ForgeDirection.EAST, ForgeDirection.WEST, ForgeDirection.UP, ForgeDirection.DOWN
	};
}
