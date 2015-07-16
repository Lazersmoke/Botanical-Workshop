package lazersmoke.botanicalworkshop.common.block.subtile.generating;

import lazersmoke.botanicalworkshop.api.flowers.TonalGeneratingFlower;

public class SubTileCheaty extends TonalGeneratingFlower{	
	@Override
	public void onUpdate(){
		mana = getMaxMana();
	}
}