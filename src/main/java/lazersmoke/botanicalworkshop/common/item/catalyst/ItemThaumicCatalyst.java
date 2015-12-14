package lazersmoke.botanicalworkshop.common.item.catalyst;

import cpw.mods.fml.common.Optional;
import lazersmoke.botanicalworkshop.common.BotanicalWorkshop;
import lazersmoke.botanicalworkshop.common.block.BlockThaumicCore;
import lazersmoke.botanicalworkshop.common.block.tile.TileGatewayCore;
import lazersmoke.botanicalworkshop.common.block.tile.TileThaumicCore;
import lazersmoke.botanicalworkshop.common.item.ModItems;
import lazersmoke.botanicalworkshop.common.lib.LibItemNames;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectSourceHelper;

@Optional.InterfaceList({
		@Optional.Interface(modid = "Thaumcraft", iface = "thaumcraft.api.aspects.AspectSourceHelper", striprefs = true),
		@Optional.Interface(modid = "Thaumcraft", iface = "thaumcraft.api.aspects.Aspect", striprefs = true)})
public class ItemThaumicCatalyst extends ItemActiveCatalyst{

	public ItemThaumicCatalyst(){
		super(LibItemNames.THAUMIC_CATALYST);
	}

	@Override
	public void onGatewayUpdate(TileGatewayCore gateway, EntityItem catalyst){
		Block thaumicCore = catalyst.worldObj.getBlock(gateway.xCoord, gateway.yCoord + 6, gateway.zCoord);
		if(thaumicCore instanceof BlockThaumicCore && BotanicalWorkshop.thaumcraftLoaded){
			TileThaumicCore tileThaumicCore = (TileThaumicCore) catalyst.worldObj.getTileEntity(gateway.xCoord, gateway.yCoord + 6, gateway.zCoord);
			if(vazkii.botania.client.core.handler.ClientTickHandler.ticksInGame % 10 == 0)
				if(AspectSourceHelper.drainEssentia(tileThaumicCore, Aspect.MAGIC, ForgeDirection.UNKNOWN, 3)){
					gateway.currentInventory.add(new ItemStack(ModItems.thaumicResource, 1, 0));
					catalyst.worldObj.setBlockMetadataWithNotify(gateway.xCoord, gateway.yCoord + 6, gateway.zCoord, 0, 1);
				}
		}
	}
}