package lazersmoke.botanicalworkshop.common.block.tile.mana.lightning;

import net.minecraftforge.common.util.ForgeDirection;

public class TileCreativeLightningBlock extends TileModLightning{
	private int power = 0;
	private int buffer = 0;
	private int overflow = 100;
	@Override
	public int getConductivity(){
		return getState() ? 0 : 1;
	}
	@Override
	public void overflow(){
		blindAddLightning(-getCurrentLightning());
	}
	public void interpretClick(ForgeDirection side){
		if(side == ForgeDirection.UP)
			buffer += 10;
		if(side == ForgeDirection.DOWN)
			buffer -= 10;
		if(side == ForgeDirection.NORTH)
			power += 10;
		if(side == ForgeDirection.SOUTH)
			power -= 10;
		if(side == ForgeDirection.EAST)
			overflow += 10;
		if(side == ForgeDirection.WEST)
			overflow -= 10;
	}
	@Override
	public int getPowerThreshold() {
		return power;
	}
	@Override
	public int getBufferThreshold() {
		return buffer;
	}
	@Override
	public int getOverflowThreshold() {
		return overflow;
	}
}