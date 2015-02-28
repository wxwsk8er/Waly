/***************************************************************************************************************/
/** Copyright 2015 BiggerOnTheInside (development), all rights reserved.                                       */
/** Released under the Binder License (https://github.com/BiggerOnTheInside/Licenses/blob/master/Binder.txt)   */
/***************************************************************************************************************/

package me.wxwsk8er.Waly.Listeners;

import me.wxwsk8er.Waly.PlayerMeta;
import me.wxwsk8er.Waly.Waly;
import net.md_5.bungee.api.ChatColor;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.ItemStack;

/**
 * @author Walt
 * @version 0.1
 * @category Listener
 */
public class PlayerCraftListener implements Listener{
	private Waly main;
	
	/**
	 * @param main Waly instance
	 */
	public PlayerCraftListener(Waly main){
		this.main = main;
	}
	
	/**
	 * @param event Event fired when player crafts cobblestone
	 */
	@EventHandler
	public void onPlayerCraftCobbleStone(CraftItemEvent event){
		Player p = (Player)event.getWhoClicked();
		CraftingInventory inv = event.getInventory();
		ItemStack i = main.cobblestoneRecipe.getItemStackReturn();

		if(inv.getRecipe() == main.cobblestoneRecipe.getRecipe() && !PlayerMeta.getMeta(p).getKit().name().contains("WOOD_AGE")){
			event.getInventory().setResult(i);
		}
		
		if(inv.getRecipe() == main.cobblestoneRecipe.getRecipe() && PlayerMeta.getMeta(p).getKit().name().contains("WOOD_AGE")){
			p.sendMessage(ChatColor.RED + "Your not advanced enough!");
			
			event.setCancelled(true);
		}
	}
	
	/**
	 * 
	 * @param event Event fired when player crafts planks.
	 */
	@SuppressWarnings("unused")
	@EventHandler
	public void onPlayerCraftPlank(CraftItemEvent event){
		Player p = (Player)event.getWhoClicked();
		CraftingInventory inv = event.getInventory();
		ItemStack i = main.plankRecipe.getItemStackReturn();

		if(inv.getRecipe() == main.plankRecipe.getRecipe()){
			event.getInventory().setResult(i);
		}
	}
}
