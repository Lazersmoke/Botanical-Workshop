package lazersmoke.botanicalworkshop.common.block.tile;

import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.IEssentiaTransport;
import cpw.mods.fml.common.Optional;

@Optional.InterfaceList({
		@Optional.Interface(modid = "Thaumcraft", iface = "thaumcraft.api.aspects.IEssentiaTransport", striprefs = true),
		@Optional.Interface(modid = "Thaumcraft", iface = "thaumcraft.api.aspects.Aspect", striprefs = true)})
public class TileThaumicCore extends TileMod implements IEssentiaTransport{

	private int essentiaCount = 0;
	private int ticksOn = 0;

	@Override
	public void updateEntity(){
		if(this.blockMetadata == 1)
			ticksOn++;
		else if(this.blockMetadata == 0 || ticksOn > 20)
			ticksOn = 0;

		if(ticksOn == 0)
			this.blockMetadata = 0;
	}

	@Override
	public int addEssentia(Aspect aspect, int amount, ForgeDirection side){
		essentiaCount += amount;
		return amount;
	}

	@Override
	public boolean canInputFrom(ForgeDirection side){
		return false;
	}

	@Override
	public boolean canOutputTo(ForgeDirection side){
		return false;
	}

	@Override
	public int getEssentiaAmount(ForgeDirection side){
		return essentiaCount;
	}

	@Override
	public Aspect getEssentiaType(ForgeDirection side){
		return Aspect.MAGIC;
	}

	@Override
	public int getMinimumSuction(){
		return 0;
	}

	@Override
	public int getSuctionAmount(ForgeDirection side){
		return 0;
	}

	@Override
	public Aspect getSuctionType(ForgeDirection side){
		return null;
	}

	@Override
	public boolean isConnectable(ForgeDirection side){
		return false;
	}

	@Override
	public boolean renderExtendedTube(){
		return false;
	}

	@Override
	public void setSuction(Aspect type, int amount){
		return;
	}

	@Override
	public int takeEssentia(Aspect type, int amount, ForgeDirection side){
		if(amount < this.essentiaCount){
			this.essentiaCount -= amount;
			return amount;
		}
		return 0;
	}

}