package lazersmoke.botanicalworkshop.common.lexicon.page;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import lazersmoke.botanicalworkshop.api.recipe.RecipeGatewayTransmutation;
import lazersmoke.botanicalworkshop.client.lib.LibResources;
import lazersmoke.botanicalworkshop.common.block.BlockGatewayCore;
import vazkii.botania.api.internal.IGuiLexiconEntry;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.lexicon.LexiconRecipeMappings;
import vazkii.botania.common.lexicon.page.PageRecipe;

public class PageGatewayTransmutationRecipe extends PageRecipe {

	private static final ResourceLocation gatewayRecipeOverlay = new ResourceLocation(LibResources.GUI_GATEWAY_TRANSMUTATION_OVERLAY);

	List<RecipeGatewayTransmutation> recipes = new ArrayList<RecipeGatewayTransmutation>();
	int ticksElapsed = 0;
	int recipeAt = 0;

	public PageGatewayTransmutationRecipe(String unlocalizedName, List<RecipeGatewayTransmutation> recipes) {
		super(unlocalizedName);
		this.recipes = recipes;
	}

	public PageGatewayTransmutationRecipe(String unlocalizedName, RecipeGatewayTransmutation recipe) {
		this(unlocalizedName, Arrays.asList(recipe));
	}

	@Override
	public void onPageAdded(LexiconEntry entry, int index) {
		for (RecipeGatewayTransmutation recipe : recipes)
			LexiconRecipeMappings.map(recipe.getOutput(), entry, index);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void renderRecipe(IGuiLexiconEntry gui, int mx, int my) {
		RecipeGatewayTransmutation recipe = recipes.get(recipeAt);
		TextureManager render = Minecraft.getMinecraft().renderEngine;
		render.bindTexture(gatewayRecipeOverlay);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glColor4f(1F, 1F, 1F, 1F);
		((GuiScreen) gui).drawTexturedModalRect(gui.getLeft(), gui.getTop(), 0, 0, gui.getWidth(), gui.getHeight());
		GL11.glDisable(GL11.GL_BLEND);

		renderItem(gui, gui.getLeft() + 38, gui.getTop() + 52, recipe.getCatalyst(), false);

		renderItemAtGridPos(gui, 3, 1, recipe.getOutput(), false);

		List<ItemStack> inputs = recipe.getInputs();
		int i = 0;
		for (Object obj : inputs) {
			Object input = obj;

			renderItemAtInputPos(gui, i, (ItemStack) input);
			i++;
		}

		IIcon portalIcon = BlockGatewayCore.portalTex;
		Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.locationBlocksTexture);
		RenderItem.getInstance().renderIcon(gui.getLeft() + 22, gui.getTop() + 36, portalIcon, 48, 48);
	}

	@SideOnly(Side.CLIENT)
	public void renderItemAtInputPos(IGuiLexiconEntry gui, int x, ItemStack stack) {
		if (stack == null || stack.getItem() == null)
			return;
		stack = stack.copy();

		if (stack.getItemDamage() == Short.MAX_VALUE)
			stack.setItemDamage(0);

		int xPos = gui.getLeft() + x * 20 + 45;
		int yPos = gui.getTop() + 14;
		ItemStack stack1 = stack.copy();
		if (stack1.getItemDamage() == -1)
			stack1.setItemDamage(0);

		renderItem(gui, xPos, yPos, stack1, false);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void updateScreen() {
		if (ticksElapsed % 20 == 0) {
			recipeAt++;

			if (recipeAt == recipes.size())
				recipeAt = 0;
		}
		++ticksElapsed;
	}
}