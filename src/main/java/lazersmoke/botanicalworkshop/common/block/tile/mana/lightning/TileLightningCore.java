package lazersmoke.botanicalworkshop.common.block.tile.mana.lightning;

import lazersmoke.botanicalworkshop.api.mana.lightning.IBotanicalLightningBlock;


public class TileLightningCore extends TileModLightning implements IBotanicalLightningBlock{
	private int ticks = 0;
	@Override
	public void updateEntity(){
		super.updateEntity();
		if(ticks <= 0){
			worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 0, 1);
			ticks = 2;
		}
		ticks --;
	}
	@Override
	public int getConductivity() {
		return -1;//NOTE: -1 reserved for generator-only blocks
	}
	//Stops metadata from reset for another 2 ticks
	public void poke(){
		ticks = 2;
	}
	@Override
	public void overflow(){
		blindAddLightning(-getCurrentLightning());
		ticks = 0;
	}
	@Override
	public int getPowerThreshold() {
		return 0;
	}
	@Override
	public int getBufferThreshold() {
		return 0;
	}
	@Override
	public int getOverflowThreshold() {
		return 1000;
	}
}