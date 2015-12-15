package lazersmoke.botanicalworkshop.client.integration.nei;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import lazersmoke.botanicalworkshop.api.BotanicalWorkshopAPI;
import lazersmoke.botanicalworkshop.api.recipe.RecipeGatewayTransmutation;
import lazersmoke.botanicalworkshop.client.lib.LibResources;
import lazersmoke.botanicalworkshop.common.BotanicalWorkshop;
import lazersmoke.botanicalworkshop.common.block.BlockGatewayCore;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraftforge.oredict.OreDictionary;

import org.apache.logging.log4j.Level;
import org.lwjgl.opengl.GL11;

import codechicken.lib.gui.GuiDraw;
import codechicken.nei.NEIServerUtils;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;

public class RecipeHandlerGatewayTransmutation extends TemplateRecipeHandler{

	public class CachedElvenTradeRecipe extends CachedRecipe{

		public List<PositionedStack> inputs = new ArrayList<PositionedStack>();
		public PositionedStack output;
		public PositionedStack catalyst;

		public CachedElvenTradeRecipe(RecipeGatewayTransmutation recipe){
			if(recipe == null)
				return;
			BotanicalWorkshop.logger.log(Level.INFO, recipe.getInputs().toString());
			setIngredients(recipe.getInputs());
			output = new PositionedStack(recipe.getOutput(), 107, 46);
			catalyst = new PositionedStack(recipe.getCatalyst(), 60, 46);
		}

		public void setIngredients(List<ItemStack> list){
			int i = 0;
			for(final Object o : list){
				if(o instanceof String)
					this.inputs.add(new PositionedStack(OreDictionary.getOres((String) o), 60 + i * 18, 6));
				else
					this.inputs.add(new PositionedStack(o, 60 + i * 18, 6));

				i++;
			}
		}

		@Override
		public List<PositionedStack> getIngredients(){
			return getCycledIngredients(cycleticks / 20, inputs);
		}

		@Override
		public PositionedStack getResult(){
			return output;
		}

	}

	@Override
	public String getRecipeName(){
		return StatCollector.translateToLocal("botanicalworkshop.nei.gateway");
	}

	@Override
	public String getGuiTexture(){
		return LibResources.GUI_NEI_BLANK;
	}

	@Override
	public void loadTransferRects(){
		transferRects.add(new RecipeTransferRect(new Rectangle(35, 30, 48, 48), "botanicalworkshop.gatewayTransmutation"));
	}

	@Override
	public int recipiesPerPage(){
		return 1;
	}

	@Override
	public void drawBackground(int recipe){
		super.drawBackground(recipe);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.7F);
		GuiDraw.changeTexture(LibResources.GUI_GATEWAY_TRANSMUTATION_OVERLAY);
		GuiDraw.drawTexturedModalRect(30, 10, 17, 17, 100, 80);
		GL11.glDisable(GL11.GL_BLEND);
		GuiDraw.changeTexture(TextureMap.locationBlocksTexture);
		RenderItem.getInstance().renderIcon(35, 29, BlockGatewayCore.portalTex, 48, 48);
	}

	@Override
	public void loadCraftingRecipes(String outputId, Object... results){
		if(outputId.equals("botanicalworkshop.gatewayTransmutation"))
			for(final RecipeGatewayTransmutation recipe : BotanicalWorkshopAPI.gatewayRecipes){
				if(recipe == null)
					continue;

				arecipes.add(new CachedElvenTradeRecipe(recipe));
			}
		else
			super.loadCraftingRecipes(outputId, results);
	}

	@Override
	public void loadCraftingRecipes(ItemStack result){
		for(final RecipeGatewayTransmutation recipe : BotanicalWorkshopAPI.gatewayRecipes){
			if(recipe == null)
				continue;

			if(NEIServerUtils.areStacksSameTypeCrafting(recipe.getOutput(), result))
				arecipes.add(new CachedElvenTradeRecipe(recipe));
		}
	}

	@Override
	public void loadUsageRecipes(ItemStack ingredient){
		for(final RecipeGatewayTransmutation recipe : BotanicalWorkshopAPI.gatewayRecipes){
			if(recipe == null)
				continue;

			final CachedElvenTradeRecipe crecipe = new CachedElvenTradeRecipe(recipe);
			if(crecipe.contains(crecipe.inputs, ingredient) || NEIServerUtils.areStacksSameTypeCrafting(recipe.getCatalyst(), ingredient))
				arecipes.add(crecipe);
		}
	}

}