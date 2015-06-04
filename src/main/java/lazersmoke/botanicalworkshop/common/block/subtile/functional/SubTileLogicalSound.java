package lazersmoke.botanicalworkshop.common.block.subtile.functional;

import java.util.Arrays;

import lazersmoke.botanicalworkshop.api.flowers.TonalFlower;
import lazersmoke.botanicalworkshop.common.lexicon.LexiconData;
import vazkii.botania.api.lexicon.LexiconEntry;

public class SubTileLogicalSound extends TonalFlower{

	private boolean firstTick = true;

	public SubTileLogicalSound(){
		super();
	}

	@Override
	public void onUpdate(){
		if(firstTick){
			pitches = new float[][] {
			        {
			                1.05F, 0.7F, 0.7F, 0.95F, 1.05F, 0.7F, 0.7F, 0.9F,
			                0.8F, 1.05F, 0.63F, 0.63F, 0.85F, 0.95F, 0.63F,
			                0.85F, 0.8F, 0.7F
			        },

			        {
			                0.7F, 0.85F, 0.95F, 1.05F, 0.7F, 0.85F, 0.95F,
			                1.05F, 0.7F, 0.85F, 0.95F, 1.05F, 0.7F, 0.85F,
			                0.95F, 1.05F, 0.53F, 0.63F, 0.7F, 0.8F, 0.53F,
			                0.63F, 0.7F, 0.8F, 0.53F, 0.63F, 0.7F, 0.8F, 0.53F,
			                0.63F, 0.7F, 0.8F, 0.63F, 0.8F, 0.85F, 0.95F,
			                0.63F, 0.8F, 0.85F, 0.95F, 0.63F, 0.8F, 0.85F,
			                0.95F, 0.63F, 0.8F, 0.85F, 0.95F, 0.47F, 0.8F,
			                0.85F, 0.95F, 0.47F, 0.8F, 0.85F, 0.95F, 0.47F,
			                0.8F, 0.85F, 0.95F, 0.47F, 0.8F, 0.85F, 0.95F
			        }
			};
			times = new int[][] {
			        {
			                24, 6, 6, 1, 1, 4, 4, 1, 1, 24, 6, 6, 1, 1, 4, 4,
			                1, 1
			        },

			        {
			                2, 2, 1, 1, 2, 2, 1, 1, 2, 2, 1, 1, 2, 2, 1, 1, 2,
			                2, 1, 1, 2, 2, 1, 1, 2, 2, 1, 1, 2, 2, 1, 1, 2, 2,
			                1, 1, 2, 2, 1, 1, 2, 2, 1, 1, 2, 2, 1, 1, 2, 2, 1,
			                1, 2, 2, 1, 1, 2, 2, 1, 1, 2, 2, 1, 1
			        }
			};
			tempo = 2;
			soundEffect = new String[2][Math.max(pitches[0].length,
			        pitches[1].length)];
			Arrays.fill(soundEffect[0], "botanicalworkshop:quack");
			Arrays.fill(soundEffect[1], "note.harp");
			volumes = new float[2][Math.max(pitches[0].length,
			        pitches[1].length)];
			Arrays.fill(volumes[0], 0.3F);
			Arrays.fill(volumes[1], 0.2F);
		}
		super.onUpdate();
		firstTick = false;
	}

	@Override
	public LexiconEntry getEntry(){
		return LexiconData.logicalSound;
	}
}