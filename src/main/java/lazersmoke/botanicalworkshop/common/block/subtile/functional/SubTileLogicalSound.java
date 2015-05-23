package lazersmoke.botanicalworkshop.common.block.subtile.functional;

import lazersmoke.botanicalworkshop.common.lexicon.LexiconData;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentTranslation;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.subtile.SubTileFunctional;

public class SubTileLogicalSound extends SubTileFunctional{
	private int maxMana = 1000;
	
	public SubTileLogicalSound(){
		super();
	}
	@Override
	public void onUpdate() {
		super.onUpdate();
		if(supertile.getWorldObj().isRemote)
			return;
		//Minecraft.getMinecraft().thePlayer.addChatMessage(
		//		new ChatComponentTranslation("botanicalworkshopmisc.soundLogicChatSpam"));
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