package lazersmoke.botanicalworkshop.common.block.tile;

import cpw.mods.fml.common.FMLLog;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.IEssentiaTransport;
import vazkii.botania.common.block.tile.TileMod;

public class TileThaumicCore extends TileMod implements IEssentiaTransport{

	private int essentiaCount = 0;
	private int suction = 64;
	
	@Override
	public int addEssentia(Aspect aspect, int amount, ForgeDirection side) {
		essentiaCount += amount;
		FMLLog.info("Added %d Essentia, now have: %d", amount, essentiaCount);
		return amount;
	}

	@Override
	public boolean canInputFrom(ForgeDirection side) {
		return true;
	}

	@Override
	public boolean canOutputTo(ForgeDirection side) {
		return false;
	}

	@Override
	public int getEssentiaAmount(ForgeDirection side) {
		return essentiaCount;
	}

	@Override
	public Aspect getEssentiaType(ForgeDirection side) {
		return Aspect.MAGIC;
	}

	@Override
	public int getMinimumSuction() {
		return 0;
	}

	@Override
	public int getSuctionAmount(ForgeDirection side) {
		return suction;
	}

	@Override
	public Aspect getSuctionType(ForgeDirection side) {
		return null;
	}

	@Override
	public boolean isConnectable(ForgeDirection side) {
		return true;
	}

	@Override
	public boolean renderExtendedTube() {
		return false;
	}

	@Override
	public void setSuction(Aspect type, int amount) {
		this.suction = amount;
	}

	@Override
	public int takeEssentia(Aspect type, int amount, ForgeDirection side) {
		if(amount < this.essentiaCount){
			this.essentiaCount -= amount;
			return amount;
		}
		return 0;
	}
	
}