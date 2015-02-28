/***************************************************************************************************************/
/** Copyright 2015 BiggerOnTheInside (development), all rights reserved.                                       */
/** Released under the Binder License (https://github.com/BiggerOnTheInside/Licenses/blob/master/Binder.txt)   */
/***************************************************************************************************************/

package me.wxwsk8er.Waly.Listeners;

import me.wxwsk8er.Waly.Kit;
import me.wxwsk8er.Waly.PlayerMeta;
import me.wxwsk8er.Waly.Util.KitUtils;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

public class PlayerInventoryListener implements Listener{
	
	@EventHandler
	public void onInventoryEdit(InventoryClickEvent event){
		Inventory inventory = event.getInventory();
		Player p = (Player)event.getWhoClicked();
		
		if(inventory.getTitle().startsWith(p.getName())){
			if(event.getCurrentItem().getType() == Material.AIR){
				return;
			}
			
			p.closeInventory();
			event.setCancelled(true);
			
			String name = event.getCurrentItem().getItemMeta().getDisplayName();
			PlayerMeta meta = PlayerMeta.getMeta(p);
			
			if(!Kit.valueOf(ChatColor.stripColor(name).toUpperCase().replace(' ', '_')).isOwnedBy(p)){
				p.sendMessage(ChatColor.RED + "You don't own this class!");
				
				return;
			}
			
			p.sendMessage(ChatColor.GREEN + "You will get this class when you die!");
			meta.setKit(Kit.getKit(ChatColor.stripColor(name)));
			p.sendMessage(ChatColor.AQUA + "Selected class: " + ChatColor.stripColor(name));
		}
		
	}
	
	@EventHandler
	public void onClassSelectorOpen(PlayerInteractEvent event){
		Player p = event.getPlayer();
		PlayerMeta meta = PlayerMeta.getMeta(p);
		
		if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK){
			if(p.getItemInHand().getType() == Material.EYE_OF_ENDER && meta.isInLobby()){
				KitUtils.showKitSelector(p);
			}
		}
		
		event.setCancelled(true);
	}
}
