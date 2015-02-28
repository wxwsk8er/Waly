/***************************************************************************************************************/
/** Copyright 2015 BiggerOnTheInside (development), all rights reserved.                                       */
/** Released under the Binder License (https://github.com/BiggerOnTheInside/Licenses/blob/master/Binder.txt)   */
/***************************************************************************************************************/

package me.wxwsk8er.Waly;

import net.md_5.bungee.api.ChatColor;

public enum Messages {
	NOT_ADVANCED_ENOUGH(ChatColor.RED + "Not advanced enough!"),
	NOT_ENOUGH_PERMISSIONS(ChatColor.RED + "You don't have the required permissions!"),
	ONLY_PLAYERS_USE_COMMAND(ChatColor.RED + "Only players can use that command!"),
	ONLY_CONSOLE_USE_COMMAND(ChatColor.RED + "Only the console can use that command!"),
	OPENING_CLASS_SELECTOR(ChatColor.AQUA + "Opening class selector...");
	
	/* Header: ChatColor.GOLD + "========== " + ChatColor.WHITE + "Achievement" + ChatColor.GOLD + " ==========" */
	
	
	
	/**
	 * {@value} Message string.
	 */
	private String msg;
	
	/**
	 * @param mes Message string.
	 */
	Messages(String mes){
		this.msg = mes;
	}
	
	/**
	 * @return Message string for this message.
	 */
	public String getMessage(){
		return msg;
	}
}
