package lazersmoke.botanicalworkshop.common.item.catalyst;

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


public class ItemThaumicCatalyst extends ItemModCatalyst{
	
	public ItemThaumicCatalyst(){
		super();
		setUnlocalizedName(LibItemNames.THAUMIC_CATALYST);
	}
	
	@Override
	public void onGatewayUpdate(TileGatewayCore gateway, EntityItem catalyst){
		Block thaumicCore = catalyst.worldObj.getBlock(gateway.xCoord, gateway.yCoord + 6, gateway.zCoord);
		if(thaumicCore instanceof BlockThaumicCore){
			TileThaumicCore tileThaumicCore = (TileThaumicCore) catalyst.worldObj.getTileEntity(gateway.xCoord, gateway.yCoord + 6, gateway.zCoord);
			if(vazkii.botania.client.core.handler.ClientTickHandler.ticksInGame % 10 == 0)
				if(AspectSourceHelper.drainEssentia(tileThaumicCore, Aspect.MAGIC, ForgeDirection.UNKNOWN, 3))
					gateway.currentInventory.add(new ItemStack(ModItems.thaumicResource, 1, 0));
		}
	}
}