package lazersmoke.botanicalworkshop.common.item.catalyst;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

import lazersmoke.botanicalworkshop.common.block.tile.TileGatewayCore;
import lazersmoke.botanicalworkshop.common.lib.LibItemNames;
import net.minecraft.entity.item.EntityItem;


public class ItemManaCatalyst extends ItemModCatalyst{
	
	static List<UUID> UUIDList = new CopyOnWriteArrayList<UUID>();
	static List<TileGatewayCore> gatewayList = new CopyOnWriteArrayList<TileGatewayCore>();
	
	public ItemManaCatalyst(){
		super();
		setUnlocalizedName(LibItemNames.MANA_CATALYST);
	}
	
	@Override
	public void onGatewayUpdate(TileGatewayCore gateway, EntityItem catalyst){
		
		if(!UUIDList.contains(gateway.uuid)){
			gatewayList.add(gateway);
			UUIDList.add(gateway.uuid);
		}
			
		gateway.addMana(-100);
		
		for(TileGatewayCore currManaMod : gatewayList){			
			if(currManaMod.uuid == gateway.uuid)
				continue;
			else if(currManaMod.getCurrentMana() < gateway.getCurrentMana()){
				currManaMod.addMana(1000);
				gateway.addMana(-1000);
			}
		}
		
	}
}