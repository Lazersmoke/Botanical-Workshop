package lazersmoke.botanicalworkshop.common.quakemovement;

import vazkii.botania.common.core.helper.ItemNBTHelper;
import api.player.server.ServerPlayerAPI;
import api.player.server.ServerPlayerBase;

public class QuakeServerPlayer extends ServerPlayerBase
{

	private boolean wasVelocityChangedBeforeFall = false;

	public QuakeServerPlayer(ServerPlayerAPI playerapi)
	{
		super(playerapi);
	}

	@Override
	public void fall(float fallDistance)
	{
		wasVelocityChangedBeforeFall = this.playerAPI.getVelocityChangedField() || this.player.velocityChanged;

		if (QuakeConfig.INCREASED_FALL_DISTANCE != 0.0D && ItemNBTHelper.getBoolean(this.player.getEquipmentInSlot(0), "shiftedUpgradeHopsUpgrade", false))
		{
			fallDistance -= QuakeConfig.INCREASED_FALL_DISTANCE;
		}
		super.fall(fallDistance);

		this.playerAPI.setVelocityChangedField(wasVelocityChangedBeforeFall && ItemNBTHelper.getBoolean(this.player.getEquipmentInSlot(0), "shiftedUpgradeHopsUpgrade", false));
		this.player.velocityChanged = wasVelocityChangedBeforeFall && ItemNBTHelper.getBoolean(this.player.getEquipmentInSlot(0), "shiftedUpgradeHopsUpgrade", false);
	}
}
