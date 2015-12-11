package lazersmoke.botanicalworkshop.common.block.tile.mana.lightning;

import lazersmoke.botanicalworkshop.api.mana.lightning.IBotanicalLightningBlock;

public class TileLightningRod extends TileModLightning implements IBotanicalLightningBlock{
	@Override
	public void updateEntity(){
		super.updateEntity();
	}
	@Override
	public int getConductivity(){
		return 0;
	}
	@Override
	public void overflow() {
		blindAddLightning(-getCurrentLightning());
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
		return 100;
	}
}
