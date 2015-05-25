package lazersmoke.botanicalworkshop.common.item.catalyst;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

import lazersmoke.botanicalworkshop.common.block.tile.TileGatewayCore;
import lazersmoke.botanicalworkshop.common.lib.LibItemNames;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;


public class ItemManaTransferCatalyst extends ItemActiveCatalyst{
	
	static List<UUID> UUIDList = new CopyOnWriteArrayList<UUID>();
	static List<TileGatewayCore> gatewayList = new CopyOnWriteArrayList<TileGatewayCore>();
	
	public ItemManaTransferCatalyst(){
		super(LibItemNames.MANA_TRANSFER_CATALYST);
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
			else if(currManaMod.getCurrentMana() < gateway.getCurrentMana() && gateway.getCurrentMana() > 10000){
				currManaMod.addMana(10000);
				gateway.addMana(-10000);
			}
		}
		
	}
	
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player,  List loreLineList, boolean par4){
		for(int i = 0; i < 4; i++)
			loreLineList.add(StatCollector.translateToLocal("botanicalworkshopmisc.manaTransferCatalyst" + i));
	}
}