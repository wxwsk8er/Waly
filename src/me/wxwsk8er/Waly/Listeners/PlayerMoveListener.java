/***************************************************************************************************************/
/** Copyright 2015 BiggerOnTheInside (development), all rights reserved.                                       */
/** Released under the Binder License (https://github.com/BiggerOnTheInside/Licenses/blob/master/Binder.txt)   */
/***************************************************************************************************************/

package me.wxwsk8er.Waly.Listeners;

import me.wxwsk8er.Waly.PlayerMeta;
import me.wxwsk8er.Waly.Util.ParticleEffect;
import me.wxwsk8er.Waly.Util.ParticleEffect.OrdinaryColor;
import me.wxwsk8er.Waly.Util.ParticleEffect.ParticlePacket;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMoveListener implements Listener{
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event){
		Player p = event.getPlayer();
		
		if(PlayerMeta.getMeta(p).isHidden()){
			for(Player player : Bukkit.getOnlinePlayers()){
				if(player.getUniqueId() != p.getUniqueId()){
					
				}
			}
		}

		ParticlePacket packet = new ParticlePacket(ParticleEffect.SMOKE_LARGE, new OrdinaryColor(0, 0, 0), false);
	
		
		if(!ParticlePacket.isInitialized()){
			ParticlePacket.initialize();
		}
		
		for(Player player : Bukkit.getOnlinePlayers()){
			packet.sendTo(p.getLocation(), player);
		}
	}
}
