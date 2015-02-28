/***************************************************************************************************************/
/** Copyright 2015 BiggerOnTheInside (development), all rights reserved.                                       */
/** Released under the Binder License (https://github.com/BiggerOnTheInside/Licenses/blob/master/Binder.txt)   */
/***************************************************************************************************************/

package me.wxwsk8er.Waly.API;

import me.wxwsk8er.Waly.Waly;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

@SuppressWarnings("deprecation")
public class PlayerGotoLobbyEvent extends Event implements Cancellable{
	private Player p;
	private static final HandlerList handlers = new HandlerList();
	private boolean cancelled;
	
	public PlayerGotoLobbyEvent(Player p){
		this.p = p;
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	@Override
	public boolean isCancelled() {
		return cancelled;
	}

	@Override
	public void setCancelled(boolean arg0) {
		this.cancelled = arg0;
	}
	
	public static HandlerList getHandlerList(){
		return handlers;
	}
	
	public Player getPlayer(){
		return p;
	}
	
	public Location getLobbyLocation(){
		return Waly.getConfigManager_().getLobbyLocation();
	}
}