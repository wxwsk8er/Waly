/***************************************************************************************************************/
/** Copyright 2015 BiggerOnTheInside (development), all rights reserved.                                       */
/** Released under the Binder License (https://github.com/BiggerOnTheInside/Licenses/blob/master/Binder.txt)   */
/***************************************************************************************************************/

package me.wxwsk8er.Waly.Listeners;

import me.wxwsk8er.Waly.Achievements;
import me.wxwsk8er.Waly.Waly;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener{
	private Waly main;
	
	public PlayerJoinListener(Waly main){
		this.main = main;
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event){
		Bukkit.broadcastMessage(ChatColor.AQUA + "Welcome " + event.getPlayer().getName() + " to the game!");
		
		event.getPlayer().teleport(main.getConfigManager().getLobbyLocation());
		
		Achievements.ACHIEVEMENT_START_PLAYING.giveToPlayer(event.getPlayer());
	}
}
