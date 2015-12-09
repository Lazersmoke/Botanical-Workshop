package lazersmoke.botanicalworkshop.api.mana;

import java.util.List;

import lazersmoke.botanicalworkshop.common.block.tile.TileGatewayCore;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public final class GatewayHelper {
		public static final int GATEWAY_SCAN_RANGE = 12;

		public static List<TileGatewayCore> getGatewaysAround(World world, double x, double y, double z) {
			return GatewayHelper.getTEsAround(TileGatewayCore.class, world, x, y, z);
		}

		public static <T> List<T> getTEsAround(Class<? extends TileEntity> clazz, World world, double x, double y, double z) {
			int r = GATEWAY_SCAN_RANGE;
			List<T> entities = world.getEntitiesWithinAABB(clazz, AxisAlignedBB.getBoundingBox(x - r, y - r, z - r, x + r, y + r, z + r));
			return entities;
		}
}
