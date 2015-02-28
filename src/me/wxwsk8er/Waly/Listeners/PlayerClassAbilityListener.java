/***************************************************************************************************************/
/** Copyright 2015 BiggerOnTheInside (development), all rights reserved.                                       */
/** Released under the Binder License (https://github.com/BiggerOnTheInside/Licenses/blob/master/Binder.txt)   */
/***************************************************************************************************************/

package me.wxwsk8er.Waly.Listeners;

import me.wxwsk8er.Waly.Kit;
import me.wxwsk8er.Waly.Waly;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

/**
 * @author Walt
 * @version 0.1
 * @category Listener
 */
public class PlayerClassAbilityListener implements Listener {
	private Waly main;
	private int timesJumped = 0;
	
	/**
	 * @param main Waly instance
	 */
	public PlayerClassAbilityListener(Waly main){
		this.main = main;
	}
	
	/**
	 * @param event Event to catch when player double-jumps.
	 * @see PlayerToggleFlightEvent
	 * @see BukkitScheduler
	 */
	@SuppressWarnings("deprecation")
	@EventHandler
    public void setFlyOnJump(PlayerToggleFlightEvent event) {
        Player player = event.getPlayer();
        Vector jump = player.getLocation().getDirection().multiply(0.5).setY(.3);
        Location loc = player.getLocation();
        Block block = loc.add(0, -1, 0).getBlock();
       
        if(event.isFlying() && event.getPlayer().getGameMode() != GameMode.CREATIVE && Kit.WOOD_AGE_STARTER.isOwnedBy(player)) {
           
            if(timesJumped != 2) {
                player.setFlying(false);
                player.setVelocity(player.getVelocity().add(jump));
                timesJumped++;
            }
           
            else if(timesJumped == 2) {
                if(block.getType() != Material.AIR) {
                    player.setAllowFlight(true);
                    timesJumped = 0;
                } else {
                    player.setFlying(false);
                    player.setAllowFlight(true);
                }
            }
           
            player.playSound(player.getLocation(), Sound.IRONGOLEM_THROW, 1.0f, 3f);
            event.setCancelled(true);
        }
        
        main.getServer().getScheduler().runTaskLater(main, new BukkitRunnable(){
        	public void run(){
        		timesJumped = 0;
        	}
        }, 10 * 20);
    }
	
	/**
	 * @param event Event to catch when player moves.
	 */
	@EventHandler
	public void onPlayerJump(PlayerMoveEvent event){
		Player p = event.getPlayer();
		
		if(p.getVelocity().getY() > 0 && Kit.WOOD_AGE_STARTER.isOwnedBy(p)){
			p.setAllowFlight(true);
		}
	}
}
