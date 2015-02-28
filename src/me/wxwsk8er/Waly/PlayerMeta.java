/***************************************************************************************************************/
/** Copyright 2015 BiggerOnTheInside (development), all rights reserved.                                       */
/** Released under the Binder License (https://github.com/BiggerOnTheInside/Licenses/blob/master/Binder.txt)   */
/***************************************************************************************************************/

package me.wxwsk8er.Waly;

import java.util.HashMap;

import org.bukkit.entity.Player;

public class PlayerMeta {
	private static HashMap<String, PlayerMeta> meta = new HashMap<String, PlayerMeta>();
	
	private boolean isHidden;
	private boolean inLobby;
	private Kit kit;
	
	/**
	 * @deprecated
	 */
	public PlayerMeta(){
		isHidden = false;
		inLobby = true;
		kit = Kit.WOOD_AGE_STARTER;
	}
	
	/**
	 * @param p Player to get meta from.
	 * @return Player's metadata.
	 */
	public static PlayerMeta getMeta(Player p){
		return getMeta(p.getUniqueId().toString());
	}
	
	/**
	 * @deprecated
	 * @param s String to get player by.
	 * @return Player's metadata.
	 */
	public static PlayerMeta getMeta(String s){
		if(!meta.containsKey(s)){
			meta.put(s, new PlayerMeta());
		}
		
		return meta.get(s);
	}
	
	/**
	 * @return Clears the meta table.
	 */
	public static void clear(){
		meta.clear();
	}
	
	/**
	 * @return Is player hidden from view.
	 */
	public boolean isHidden(){
		return isHidden;
	}
	
	/**
	 * @return Is player in lobby.
	 */
	public boolean isInLobby(){
		return inLobby;
	}
	
	/**
	 * @return Kit player is currently using.
	 */
	public Kit getKit(){
		return this.kit;
	}
	
	/**
	 * @param hidden Boolean to set if player is hidden.
	 */
	public void setIsHidden(boolean hidden){
		this.isHidden = hidden;
	}
	
	/**
	 * @param lobby Boolean to set if player is in the lobby.
	 */
	public void setInLobby(boolean lobby){
		this.inLobby = lobby;
	}
	
	/**
	 * @param k Set players current kit.
	 */
	public void setKit(Kit k){
		this.kit = k;
	}
}
