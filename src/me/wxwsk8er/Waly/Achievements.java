/***************************************************************************************************************/
/** Copyright 2015 BiggerOnTheInside (development), all rights reserved.                                       */
/** Released under the Binder License (https://github.com/BiggerOnTheInside/Licenses/blob/master/Binder.txt)   */
/***************************************************************************************************************/

package me.wxwsk8er.Waly;

import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public enum Achievements {
	ACHIEVEMENT_START_PLAYING(ChatColor.GOLD + "Started Playing! " + ChatColor.WHITE + "+5 XP            ", "Waly.Achievement.Start", 5),
	ACHIEVEMENT_HARVEST_WOOD(ChatColor.GOLD + "Harvested Wood! " + ChatColor.WHITE + "+1 XP", "Waly.Achievement.Harvest_Wood", 1),
	ACHIEVEMENT_HARVEST_STONE(ChatColor.GOLD + "Harvested Stone! " + ChatColor.WHITE + "+2 XP", "Waly.Achievement.Harvest_Stone", 2),
	ACHIEVEMENT_HARVEST_IRON(ChatColor.GOLD + "Harvested Iron! " + ChatColor.WHITE + "+8 XP", "Waly.Achievement.Harvest_Iron", 8),
	ACHIEVEMENT_HARVEST_DIAMOND(ChatColor.GOLD + "Harvested Diamond! " + ChatColor.WHITE + "+10 XP", "Waly.Achievement.Harvest_Diamond", 10),
	
	ACHIEVEMENT_WOOD_FIGHTER(ChatColor.GOLD + "Build Sword! " + ChatColor.WHITE + "+1 XP", "Waly.Achievement.Craft_Sword", 1),
	ACHIEVEMENT_WOOD_MINER(ChatColor.GOLD + "Time to mine! " + ChatColor.WHITE + "+2 XP", "Waly.Achievement.Craft_Pickaxe", 2),
	ACHIEVEMENT_WOOD_BUILDER(ChatColor.GOLD + "Built Planks! " + ChatColor.WHITE + "+2 XP", "Waly.Achievement.Craft_Planks", 2);
	
	private String msg, permission;
	private int xp;
	
	Achievements(String msg, String permission, int xp){
		this.msg = msg;
		this.permission = permission;
		this.xp = xp;
	}
	
	public String getPermission(){
		return permission;
	}
	
	public boolean unlockedByPlayer(Player p){
		return p.isOp() || p.hasPermission(permission) || this == ACHIEVEMENT_START_PLAYING;
	}
	
	public void giveToPlayer(Player p){
		p.sendMessage(ChatColor.GOLD + "========== " + ChatColor.WHITE + "Achievement" + ChatColor.GOLD + " ==========");
		p.sendMessage(ChatColor.GOLD + "== " + msg + ChatColor.GOLD + " ==");
	
		// TODO: Give player permission to 'remember' he/she unlocked the achievement.
		// TODO: Add %xp% amount to player's Shotbow XP.
	}
}
