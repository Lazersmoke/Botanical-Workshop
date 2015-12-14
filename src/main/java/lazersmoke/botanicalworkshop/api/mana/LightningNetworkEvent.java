package lazersmoke.botanicalworkshop.api.mana;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.eventhandler.Event;

public class LightningNetworkEvent extends Event{

	public final TileEntity tile;
	public final Action action;

	public LightningNetworkEvent(TileEntity tile, Action action){
		this.tile = tile;
		this.action = action;
	}

	public static void addBlock(TileEntity tile){
		LightningNetworkEvent event = new LightningNetworkEvent(tile, Action.ADD);
		MinecraftForge.EVENT_BUS.post(event);
	}

	public static void removeBlock(TileEntity tile){
		LightningNetworkEvent event = new LightningNetworkEvent(tile, Action.REMOVE);
		MinecraftForge.EVENT_BUS.post(event);
	}

	public static void tickBlock(TileEntity tile){
		LightningNetworkEvent event = new LightningNetworkEvent(tile, Action.TICK);
		MinecraftForge.EVENT_BUS.post(event);
	}

	public enum Action{
		REMOVE, ADD, TICK
	}
}