package lazersmoke.botanical.common.block;

import lazersmoke.botanical.common.block.mana.BlockElvenPool;
import net.minecraft.block.Block;

public final class ModBlocks {
	public static Block elvenPool;
	public static void init(){
		elvenPool = new BlockElvenPool();
	}
}
