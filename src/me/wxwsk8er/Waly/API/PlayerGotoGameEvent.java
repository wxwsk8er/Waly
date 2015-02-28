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
public class PlayerGotoGameEvent extends Event implements Cancellable{
	private Player p;
	private boolean isCancelled = false;
	private static final HandlerList handlers = new HandlerList();
	
	public PlayerGotoGameEvent(Player p){
		this.p = p;
	}
	
	@Override
	public boolean isCancelled() {
		return isCancelled;
	}

	@Override
	public void setCancelled(boolean arg0) {
		this.isCancelled = arg0;
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
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
