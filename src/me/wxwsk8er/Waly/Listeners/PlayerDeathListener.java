/***************************************************************************************************************/
/** Copyright 2015 BiggerOnTheInside (development), all rights reserved.                                       */
/** Released under the Binder License (https://github.com/BiggerOnTheInside/Licenses/blob/master/Binder.txt)   */
/***************************************************************************************************************/

package me.wxwsk8er.Waly.Listeners;

import me.wxwsk8er.Waly.Waly;
import me.wxwsk8er.Waly.PlayerMeta;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

/**
 * @author Walt
 * @version 0.2
 * @category Listener
 */
public class PlayerDeathListener implements Listener{
	private Waly main;
	
	/**
	 * @param main Waly instance
	 */
	public PlayerDeathListener(Waly main){
		this.main = main;
	}
	
	/**
	 * @param event Event fired when player hurts player.
	 */
	@EventHandler
	public void onPlayerHurt(EntityDamageByEntityEvent event){
		if(event.getEntity() instanceof Player){
			Player p = (Player) event.getEntity();
			
			if(PlayerMeta.getMeta(p).isInLobby() == true){
				event.setCancelled(true);
			}
		}
		
		return;
	}
	
	/**
	 * @param event Event fired when player respawns.
	 */
	@EventHandler
	public void onPlayerDeath(PlayerRespawnEvent event){
		if(PlayerMeta.getMeta(event.getPlayer()).isInLobby() == true){
			event.setRespawnLocation(main.getConfigManager().getLobbyLocation());
		}
		
		main.killPlayer(event.getPlayer());
	}
}
