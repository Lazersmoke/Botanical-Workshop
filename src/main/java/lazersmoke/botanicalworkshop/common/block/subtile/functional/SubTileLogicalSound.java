package lazersmoke.botanicalworkshop.common.block.subtile.functional;

import lazersmoke.botanicalworkshop.common.lexicon.LexiconData;
import lazersmoke.botanicalworkshop.common.lib.LibConfigs;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.subtile.SubTileFunctional;

public class SubTileLogicalSound extends SubTileFunctional{
	private static int maxMana = 1000;
	
	//Track A
	private static final float[] pitchesa = {
		1.05F, 0.7F, 0.7F, 0.95F, 1.05F, 0.7F, 0.7F, 0.9F, 0.8F,
		1.05F, 0.63F, 0.63F, 0.85F, 0.95F, 0.63F, 0.85F, 0.8F, 0.7F
	};
	private static final int[] timesa = {
		24, 6, 6, 1, 1, 4, 4, 1, 1, 24,
		6, 6, 1, 1, 4, 4, 1, 1
	};//last -> first
	private short notea = 0;
	private int lastTicksa = 0;
	
	private static final float[] pitchesb = {
		0.7F, 0.85F, 0.95F, 1.05F,
		0.7F, 0.85F, 0.95F, 1.05F,
		0.7F, 0.85F, 0.95F, 1.05F,
		0.7F, 0.85F, 0.95F, 1.05F,
		0.53F, 0.63F, 0.7F, 0.8F,
		0.53F, 0.63F, 0.7F, 0.8F,
		0.53F, 0.63F, 0.7F, 0.8F,
		0.53F, 0.63F, 0.7F, 0.8F,
		0.63F, 0.8F, 0.85F, 0.95F,
		0.63F, 0.8F, 0.85F, 0.95F,
		0.63F, 0.8F, 0.85F, 0.95F,
		0.63F, 0.8F, 0.85F, 0.95F,
		0.47F, 0.8F, 0.85F, 0.95F,
		0.47F, 0.8F, 0.85F, 0.95F,
		0.47F, 0.8F, 0.85F, 0.95F,
		0.47F, 0.8F, 0.85F, 0.95F
	};
	private static final int[] timesb = {
		2, 2, 1, 1, 2, 2, 1, 1, 2, 2, 1, 1, 2, 2, 1, 1, 2, 
		2, 1, 1, 2, 2, 1, 1, 2, 2, 1, 1, 2, 2, 1, 1, 2, 
		2, 1, 1, 2, 2, 1, 1, 2, 2, 1, 1, 2, 2, 1, 1, 2, 
		2, 1, 1, 2, 2, 1, 1, 2, 2, 1, 1, 2, 2, 1, 1
	};//last -> first

	private short noteb = 0;
	private int lastTicksb = 0;
	private boolean firstTick = true;

	private static int tempo = 3;//inverse
	
	public SubTileLogicalSound(){
		super();
	}
	
	@Override
	public void onUpdate() {
		super.onUpdate();
		if(supertile.getWorldObj().isRemote)
			return;
		//Track A
		if(ticksExisted-lastTicksa == (firstTick ? ticksExisted-lastTicksa : timesa[notea] * tempo)){
			lastTicksa = ticksExisted;
			if(LibConfigs.TONAL_FLORA)
				playSound("botanicalworkshop:quack", 0.2F, pitchesa[notea]);
			notea++;
			if(notea > (timesa.length - 1))
				notea = 0;
		}
		//Track B
		if(ticksExisted-lastTicksb == (firstTick ? ticksExisted-lastTicksb : timesb[noteb] * tempo)){
			lastTicksb = ticksExisted;
			if(LibConfigs.TONAL_FLORA)
				playSound("note.harp", 0.15F, pitchesb[noteb]);
			noteb++;
			if(noteb > (timesb.length - 1))
				noteb = 0;
		}
		firstTick = false;
	}
	
	private void playSound(String sound, float volume, float pitch) {
		supertile.getWorldObj().playSoundEffect(supertile.xCoord, supertile.yCoord, supertile.zCoord, sound, volume, pitch);
	}
	
	@Override
	public boolean acceptsRedstone(){
		return true;
	}
	
	@Override
	public int getColor(){
		return 0x532FE0; //Same as Hydroangaes
	}
	
	@Override
	public LexiconEntry getEntry() {
		return LexiconData.logicalSound;
	}

	@Override
	public int getMaxMana() {
		return maxMana;
	}
}