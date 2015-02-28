/***************************************************************************************************************/
/** Copyright 2015 BiggerOnTheInside (development), all rights reserved.                                       */
/** Released under the Binder License (https://github.com/BiggerOnTheInside/Licenses/blob/master/Binder.txt)   */
/***************************************************************************************************************/

package me.wxwsk8er.Waly.Recipes;

import me.wxwsk8er.Waly.Waly;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

public abstract class GenericRecipe {
	public abstract void init(Waly main, ItemStack drop);
	public abstract ItemStack getItemStackReturn();
	public abstract ItemMeta getItemMetaReturn();
	public abstract ShapedRecipe getRecipe();
}
