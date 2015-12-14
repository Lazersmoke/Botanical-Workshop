package lazersmoke.botanicalworkshop.common.block.tile;

public class TileModToggleable extends TileMod{
	private boolean isOn = false;

	public boolean getState(){
		return isOn;
	}

	public boolean toggle(){
		if(isOn)
			isOn = false;
		else
			isOn = true;
		return isOn;
	}

	public boolean setState(boolean newState){
		isOn = newState;
		return isOn;
	}
}
