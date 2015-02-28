/***************************************************************************************************************/
/** Copyright 2015 BiggerOnTheInside (development), all rights reserved.                                       */
/** Released under the Binder License (https://github.com/BiggerOnTheInside/Licenses/blob/master/Binder.txt)   */
/***************************************************************************************************************/

package me.wxwsk8er.Waly.Recipes;

import me.wxwsk8er.Waly.Waly;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

@SuppressWarnings("unused")
public class SharpenedStickRecipe extends GenericRecipe{
	private ShapedRecipe recipe;
	private ItemStack itemStack;
	private ItemMeta itemMeta;
	private Waly main;

	@Override
	public void init(Waly main, ItemStack drop) {
		if(drop != null){
			this.itemStack = drop;
		}
		else{
			throw new NullPointerException("Can't have a null itemstack!");
		}
		
		this.main = main;
		
		itemMeta = itemStack.getItemMeta();
		
		itemMeta.setDisplayName("Sharp Stick");
		itemStack.setItemMeta(itemMeta);
		
		recipe = new ShapedRecipe(itemStack);
		
		recipe.shape("*  ", "#  ", "   ");
		recipe.setIngredient('*', Material.STICK);
		recipe.setIngredient('#', Material.CLAY_BALL);
		main.getServer().addRecipe(recipe);
	}

	@Override
	public ItemStack getItemStackReturn() {
		return itemStack;
	}

	@Override
	public ItemMeta getItemMetaReturn() {
		return itemMeta;
	}

	@Override
	public ShapedRecipe getRecipe() {
		return recipe;
	}
}
