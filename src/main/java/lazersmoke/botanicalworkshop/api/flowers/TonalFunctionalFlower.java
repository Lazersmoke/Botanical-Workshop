package lazersmoke.botanicalworkshop.api.flowers;

import java.util.Arrays;

import vazkii.botania.api.subtile.SubTileFunctional;
import lazersmoke.botanicalworkshop.common.lib.LibConfigs;

/**
 * Functioning Tonal Flower MAKE SURE TO INITIALIZE PITCHES, TIMES, VOLUMES BEFORE CALLING `super.onUpdate();`!!! If acceptsRedstone is specified, redstone is required to stop music
 * 
 * @author Sam
 */
public class TonalFunctionalFlower extends SubTileFunctional{

	protected static float[][] pitches;
	protected static int[][] times;// Your last time should be moved to the first spot in the array and everything else moved forward by one
	protected static float[][] volumes;
	protected int[] note;
	protected int[] lastTicks;
	protected static int tempo;
	protected boolean firstTick = true;
	protected static String[][] soundEffect;

	@Override
	public void onUpdate(){
		if(firstTick){
			note = new int[pitches.length];
			Arrays.fill(note, 0);
			lastTicks = new int[pitches.length];
			Arrays.fill(lastTicks, 0);
		}
		for(int i = 0; i < pitches.length; i++)
			if(ticksExisted - lastTicks[i] == (firstTick ? ticksExisted - lastTicks[i] : times[i][note[i]] * tempo)){
				lastTicks[i] = ticksExisted;
				if((acceptsRedstone() ? redstoneSignal == 0 : true) && LibConfigs.TONAL_FLORA)
					playSound(soundEffect[i][note[i]], volumes[i][note[i]], pitches[i][note[i]]);
				note[i]++;
				if(note[i] > (times[i].length - 1))
					note[i] = 0;
			}
		firstTick = false;
		super.onUpdate();
	}

	private void playSound(String sound, float volume, float pitch){
		supertile.getWorldObj().playSoundEffect(supertile.xCoord, supertile.yCoord, supertile.zCoord, sound, volume, pitch);
	}
}