package lazersmoke.botanicalworkshop.common.item.catalyst;

import lazersmoke.botanicalworkshop.common.BotanicalWorkshop;
import lazersmoke.botanicalworkshop.common.block.tile.TileGatewayCore;
import lazersmoke.botanicalworkshop.common.block.tile.TileThaumicCore;
import lazersmoke.botanicalworkshop.common.lib.LibItemNames;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.tileentity.TileEntity;
import vazkii.botania.client.core.handler.ClientTickHandler;
//TODO texture this
//TODO lang file this
//TODO lexicon this

public class ItemCraftingAutomationCatalyst extends ItemActiveCatalyst{

	public ItemCraftingAutomationCatalyst(){
		super(LibItemNames.CRAFTING_AUTOMATION_CATALYST);
	}

	@Override
	public void onGatewayUpdate(TileGatewayCore gateway, EntityItem catalyst){
		gateway.pokeSummonOffset(new float[] {0.5F, -0.5F, 0.5F});// Make items summon under gateway
		final TileEntity currentCore = catalyst.worldObj.getTileEntity(gateway.xCoord, gateway.yCoord + 6, gateway.zCoord);
		if(BotanicalWorkshop.thaumcraftLoaded && currentCore instanceof TileThaumicCore && ClientTickHandler.ticksInGame % 20 == 0)
			if(catalyst.worldObj.getTileEntity(gateway.xCoord, gateway.yCoord + 7, gateway.zCoord) instanceof thaumcraft.common.tiles.TilePedestal)
				if(catalyst.worldObj.getTileEntity(gateway.xCoord, gateway.yCoord + 9, gateway.zCoord) instanceof thaumcraft.common.tiles.TileInfusionMatrix && !((thaumcraft.common.tiles.TileInfusionMatrix) catalyst.worldObj.getTileEntity(gateway.xCoord, gateway.yCoord + 9, gateway.zCoord)).crafting){
					final thaumcraft.common.tiles.TilePedestal thePedestal = ((thaumcraft.common.tiles.TilePedestal) catalyst.worldObj.getTileEntity(gateway.xCoord, gateway.yCoord + 7, gateway.zCoord));
					if(thePedestal.getStackInSlot(0) != null)
						if(gateway.currentInventory.size() > 0 && gateway.currentInventory.get(0) != null){
							gateway.summonItem(thePedestal.getStackInSlot(0));
							thePedestal.setInventorySlotContents(0, gateway.currentInventory.get(0).copy());
							gateway.currentInventory.remove(0);

							catalyst.worldObj.func_147453_f(thePedestal.xCoord, thePedestal.yCoord, thePedestal.zCoord, catalyst.worldObj.getBlock(thePedestal.xCoord, thePedestal.yCoord, thePedestal.zCoord));
							catalyst.worldObj.markBlockForUpdate(thePedestal.xCoord, thePedestal.yCoord, thePedestal.zCoord);
							thePedestal.markDirty();// Not this shit again *sigh*
							((thaumcraft.common.tiles.TileInfusionMatrix) catalyst.worldObj.getTileEntity(gateway.xCoord, gateway.yCoord + 9, gateway.zCoord)).craftingStart(catalyst.worldObj.getClosestPlayer(gateway.xCoord, gateway.yCoord, gateway.zCoord, -1));
						}
				}
	}
}