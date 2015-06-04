package lazersmoke.botanicalworkshop.common.block.tile.mana;

// This class is %95 Vazkii
import java.awt.Color;
import java.util.List;

import lazersmoke.botanicalworkshop.client.lib.LibResources;
import lazersmoke.botanicalworkshop.common.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.internal.VanillaPacketDispatcher;
import vazkii.botania.api.item.IDyablePool;
import vazkii.botania.api.item.IManaDissolvable;
import vazkii.botania.api.mana.IKeyLocked;
import vazkii.botania.api.mana.IManaItem;
import vazkii.botania.api.mana.IManaPool;
import vazkii.botania.api.mana.IThrottledPacket;
import vazkii.botania.api.mana.ManaNetworkEvent;
import vazkii.botania.api.mana.spark.ISparkAttachable;
import vazkii.botania.api.mana.spark.ISparkEntity;
import vazkii.botania.api.recipe.RecipeManaInfusion;
import vazkii.botania.client.core.handler.HUDHandler; // is final no subclassing allowed
import vazkii.botania.client.core.handler.LightningHandler;
import vazkii.botania.common.Botania;
import vazkii.botania.common.block.tile.TileMod;
import vazkii.botania.common.core.handler.ManaNetworkHandler;
import vazkii.botania.common.core.helper.Vector3;

public class TileElvenPool extends TileMod implements IManaPool, IDyablePool,
		IKeyLocked, ISparkAttachable, IThrottledPacket{

	public static final int MAX_MANA = 1000000;

	private static final String TAG_MANA = "mana";
	private static final String TAG_KNOWN_MANA = "knownMana";
	private static final String TAG_OUTPUTTING = "outputting";
	private static final String TAG_COLOR = "color";
	private static final String TAG_MANA_CAP = "manaCap";
	private static final String TAG_CAN_ACCEPT = "canAccept";
	private static final String TAG_CAN_SPARE = "canSpare";
	private static final String TAG_FRAGILE = "fragile";
	private static final String TAG_INPUT_KEY = "inputKey";
	private static final String TAG_OUTPUT_KEY = "outputKey";

	boolean outputting = false;
	public boolean alchemy = false;
	public boolean conjuration = false;
	boolean catalystsRegistered = false;

	public int color = 0;
	int mana;
	int knownMana = -1;

	public int manaCap = -1;
	int soundTicks = 0;
	boolean canAccept = true;
	boolean canSpare = true;
	public boolean fragile = false;

	String inputKey = "";
	String outputKey = "";

	int ticks = 0;
	boolean sendPacket = false;

	@Override
	public boolean isFull(){
		Block blockBelow = worldObj.getBlock(xCoord, yCoord - 1, zCoord);
		return blockBelow != vazkii.botania.common.block.ModBlocks.manaVoid
				&& getCurrentMana() >= manaCap;
	}

	@Override
	public void recieveMana(int mana){
		boolean full = getCurrentMana() >= manaCap;

		this.mana = Math.max(0, Math.min(getCurrentMana() + mana, manaCap));
		if(!full)
			worldObj.func_147453_f(xCoord, yCoord, zCoord,
					worldObj.getBlock(xCoord, yCoord, zCoord));
	}

	@Override
	public void invalidate(){
		super.invalidate();
		ManaNetworkEvent.removePool(this);
	}

	@Override
	public void onChunkUnload(){
		super.onChunkUnload();
		ManaNetworkEvent.removePool(this);
	}

	public boolean collideEntityItem(EntityItem item){
		if(item.isDead)
			return false;

		boolean didChange = false;
		ItemStack stack = item.getEntityItem();
		if(stack == null)
			return false;

		if(stack.getItem() instanceof IManaDissolvable){
			( (IManaDissolvable) stack.getItem() ).onDissolveTick(this, stack,
					item);
			if(stack.stackSize == 0)
				item.setDead();
		}

		if(item.age > 100 && item.age < 130 || !catalystsRegistered)
			return false;

		for(RecipeManaInfusion recipe : BotaniaAPI.manaInfusionRecipes){
			if(recipe.matches(stack)
					&& ( !recipe.isAlchemy() || alchemy )
					&& ( !recipe.isConjuration() || conjuration )
					&& ( getBlockMetadata() != 2 || recipe.getOutput()
							.getItem() == Item.getItemFromBlock(getBlockType()) )){
				int mana = recipe.getManaToConsume();
				if(getCurrentMana() >= mana){
					recieveMana(-mana);

					if(!worldObj.isRemote){
						stack.stackSize--;
						if(stack.stackSize == 0)
							item.setDead();

						ItemStack output = recipe.getOutput().copy();
						EntityItem outputItem = new EntityItem(worldObj,
								xCoord + 0.5, yCoord + 1.5, zCoord + 0.5,
								output);
						outputItem.age = 105;
						worldObj.spawnEntityInWorld(outputItem);
					}

					craftingFanciness();
					didChange = true;
				}

				break;
			}
		}

		return didChange;
	}

	public void craftingFanciness(){
		if(soundTicks == 0){
			worldObj.playSoundEffect(xCoord, yCoord, zCoord,
					"botania:manaPoolCraft", 0.4F, 4F);
			soundTicks = 6;
		}

		for(int i = 0; i < 25; i++){
			float red = (float) Math.random();
			float green = (float) Math.random();
			float blue = (float) Math.random();
			Botania.proxy.sparkleFX(worldObj, xCoord + 0.5 + Math.random()
					* 0.4 - 0.2, yCoord + 1, zCoord + 0.5 + Math.random() * 0.4
					- 0.2, red, green, blue, (float) Math.random(), 10);
		}
	}

	@Override
	public void updateEntity(){
		if(manaCap == -1)
			manaCap = MAX_MANA;

		if(!ManaNetworkHandler.instance.isPoolIn(this))
			ManaNetworkEvent.addPool(this);

		if(soundTicks > 0)
			soundTicks--;

		if(worldObj.isRemote){
			double particleChance = 1F - (double) getCurrentMana()
					/ (double) manaCap * 0.1;
			Color color = new Color(0x00C6FF);
			if(Math.random() > particleChance)
				Botania.proxy.wispFX(worldObj, xCoord + 0.3 + Math.random()
						* 0.5, yCoord + 0.6 + Math.random() * 0.25, zCoord
						+ Math.random(), color.getRed() / 255F,
						color.getGreen() / 255F, color.getBlue() / 255F,
						(float) Math.random() / 3F,
						(float) -Math.random() / 25F, 2F);
		}

		if(sendPacket && ticks % 10 == 0){
			VanillaPacketDispatcher.dispatchTEToNearbyPlayers(this);
			sendPacket = false;
		}

		alchemy = worldObj.getBlock(xCoord, yCoord - 1, zCoord) == vazkii.botania.common.block.ModBlocks.alchemyCatalyst;
		conjuration = worldObj.getBlock(xCoord, yCoord - 1, zCoord) == vazkii.botania.common.block.ModBlocks.conjurationCatalyst;
		catalystsRegistered = true;

		List<EntityItem> items = worldObj.getEntitiesWithinAABB(
				EntityItem.class, AxisAlignedBB.getBoundingBox(xCoord, yCoord,
						zCoord, xCoord + 1, yCoord + 1, zCoord + 1));
		for(EntityItem item : items){
			if(item.isDead)
				continue;

			ItemStack stack = item.getEntityItem();
			if(stack != null && stack.getItem() instanceof IManaItem){
				IManaItem mana = (IManaItem) stack.getItem();
				if(outputting && mana.canReceiveManaFromPool(stack, this)
						|| !outputting && mana.canExportManaToPool(stack, this)){
					boolean didSomething = false;

					if(outputting){
						if(canSpare){
							if(getCurrentMana() > 0
									&& mana.getMana(stack) < mana
											.getMaxMana(stack))
								didSomething = true;

							int manaVal = Math.min(
									1000,
									Math.min(
											getCurrentMana(),
											mana.getMaxMana(stack)
													- mana.getMana(stack)));
							if(!worldObj.isRemote)
								mana.addMana(stack, manaVal);
							recieveMana(-manaVal);
						}
					}else{
						if(canAccept){
							if(mana.getMana(stack) > 0 && !isFull())
								didSomething = true;

							int manaVal = Math.min(
									1000,
									Math.min(manaCap - getCurrentMana(),
											mana.getMana(stack)));
							if(!worldObj.isRemote)
								mana.addMana(stack, -manaVal);
							recieveMana(manaVal);
						}
					}

					if(didSomething){
						if(worldObj.isRemote
								&& vazkii.botania.common.core.handler.ConfigHandler.chargingAnimationEnabled
								&& worldObj.rand.nextInt(20) == 0){
							Vector3 itemVec = Vector3.fromTileEntity(this).add(
									0.5, 0.5 + Math.random() * 0.3, 0.5);
							Vector3 tileVec = Vector3.fromTileEntity(this).add(
									0.2 + Math.random() * 0.6, 0,
									0.2 + Math.random() * 0.6);
							LightningHandler.spawnLightningBolt(worldObj,
									outputting ? tileVec : itemVec,
									outputting ? itemVec : tileVec, 80,
									worldObj.rand.nextLong(), 0x4400799c,
									0x4400C6FF);
						}
					}
				}
			}
		}

		ticks++;
	}

	@Override
	public void writeCustomNBT(NBTTagCompound cmp){
		cmp.setInteger(TAG_MANA, mana);
		cmp.setBoolean(TAG_OUTPUTTING, outputting);
		cmp.setInteger(TAG_COLOR, color);

		cmp.setInteger(TAG_MANA_CAP, manaCap);
		cmp.setBoolean(TAG_CAN_ACCEPT, canAccept);
		cmp.setBoolean(TAG_CAN_SPARE, canSpare);
		cmp.setBoolean(TAG_FRAGILE, fragile);

		cmp.setString(TAG_INPUT_KEY, inputKey);
		cmp.setString(TAG_OUTPUT_KEY, outputKey);
	}

	@Override
	public void readCustomNBT(NBTTagCompound cmp){
		mana = cmp.getInteger(TAG_MANA);
		outputting = cmp.getBoolean(TAG_OUTPUTTING);
		color = cmp.getInteger(TAG_COLOR);

		if(cmp.hasKey(TAG_MANA_CAP))
			manaCap = cmp.getInteger(TAG_MANA_CAP);
		if(cmp.hasKey(TAG_CAN_ACCEPT))
			canAccept = cmp.getBoolean(TAG_CAN_ACCEPT);
		if(cmp.hasKey(TAG_CAN_SPARE))
			canSpare = cmp.getBoolean(TAG_CAN_SPARE);
		fragile = cmp.getBoolean(TAG_FRAGILE);

		if(cmp.hasKey(TAG_INPUT_KEY))
			inputKey = cmp.getString(TAG_INPUT_KEY);
		if(cmp.hasKey(TAG_OUTPUT_KEY))
			inputKey = cmp.getString(TAG_OUTPUT_KEY);

		if(cmp.hasKey(TAG_KNOWN_MANA))
			knownMana = cmp.getInteger(TAG_KNOWN_MANA);
	}

	public void onWanded(EntityPlayer player, ItemStack wand){
		if(player == null)
			return;

		if(player.isSneaking()){
			outputting = !outputting;
			VanillaPacketDispatcher.dispatchTEToNearbyPlayers(worldObj, xCoord,
					yCoord, zCoord);
		}

		if(!worldObj.isRemote){
			NBTTagCompound nbttagcompound = new NBTTagCompound();
			writeCustomNBT(nbttagcompound);
			nbttagcompound.setInteger(TAG_KNOWN_MANA, getCurrentMana());
			if(player instanceof EntityPlayerMP)
				( (EntityPlayerMP) player ).playerNetServerHandler
						.sendPacket(new S35PacketUpdateTileEntity(xCoord,
								yCoord, zCoord, -999, nbttagcompound));
		}

		worldObj.playSoundAtEntity(player, "botania:ding", 0.11F, 1F);
	}

	public void renderHUD(Minecraft mc, ScaledResolution res){
		String name = StatCollector.translateToLocal(new ItemStack(
				ModBlocks.elvenPool, 1, getBlockMetadata())
				.getUnlocalizedName().replaceAll("tile.",
						"tile." + LibResources.PREFIX_MOD)
				+ ".name");
		int color = 0x4444FF;
		HUDHandler.drawSimpleManaHUD(color, knownMana, manaCap, name, res);

		String power = StatCollector.translateToLocal("botaniamisc."
				+ ( outputting ? "outputtingPower" : "inputtingPower" ));
		int x = res.getScaledWidth() / 2
				- mc.fontRenderer.getStringWidth(power) / 2;
		int y = res.getScaledHeight() / 2 + 30;
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		mc.fontRenderer.drawStringWithShadow(power, x, y, color);
		GL11.glDisable(GL11.GL_BLEND);
	}

	@Override
	public boolean canRecieveManaFromBursts(){
		return true;
	}

	@Override
	public boolean isOutputtingPower(){
		return outputting;
	}

	@Override
	public int getCurrentMana(){
		return mana;
	}

	@Override
	public String getInputKey(){
		return inputKey;
	}

	@Override
	public String getOutputKey(){
		return outputKey;
	}

	@Override
	public boolean canAttachSpark(ItemStack stack){
		return true;
	}

	@Override
	public void attachSpark(ISparkEntity entity){
		// NO-OP
	}

	@Override
	public ISparkEntity getAttachedSpark(){
		List<ISparkEntity> sparks = worldObj
				.getEntitiesWithinAABB(ISparkEntity.class, AxisAlignedBB
						.getBoundingBox(xCoord, yCoord + 1, zCoord, xCoord + 1,
								yCoord + 2, zCoord + 1));
		if(sparks.size() == 1){
			Entity e = (Entity) sparks.get(0);
			return (ISparkEntity) e;
		}

		return null;
	}

	@Override
	public boolean areIncomingTranfersDone(){
		return false;
	}

	@Override
	public int getAvailableSpaceForMana(){
		return Math.max(0, manaCap - getCurrentMana());
	}

	@Override
	public int getColor(){
		return color;
	}

	@Override
	public void setColor(int color){
		this.color = color;
	}

	@Override
	public void markDispatchable(){
		sendPacket = true;
	}
}