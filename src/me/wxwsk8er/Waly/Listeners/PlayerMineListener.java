/***************************************************************************************************************/
/** Copyright 2015 BiggerOnTheInside (development), all rights reserved.                                       */
/** Released under the Binder License (https://github.com/BiggerOnTheInside/Licenses/blob/master/Binder.txt)   */
/***************************************************************************************************************/

package me.wxwsk8er.Waly.Listeners;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerMineListener implements Listener{
	
	@EventHandler
	public void onPlayerMine(BlockBreakEvent event){
		Player p = event.getPlayer();
		Block b = event.getBlock();
		
		if(b.getType().name().contains("LOG")){
			event.setCancelled(true);
		}
	}
}
