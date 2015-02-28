/***************************************************************************************************************/
/** Copyright 2015 BiggerOnTheInside (development), all rights reserved.                                       */
/** Released under the Binder License (https://github.com/BiggerOnTheInside/Licenses/blob/master/Binder.txt)   */
/***************************************************************************************************************/

package me.wxwsk8er.Waly.Commands;

import java.io.IOException;

import me.wxwsk8er.Waly.Waly;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetLobbyCommand implements CommandExecutor{
	private Waly main;
	
	public SetLobbyCommand(Waly main){
		this.main = main;
	}
	
	/**
	 * @param sender Issuer of command.
	 * @param cmd Command issued.
	 * @param label Name of command.
	 * @param args Arguments of command.
	 * @return
	 */
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("setlobby")){
			if(!(sender instanceof Player)){
				sender.sendMessage(ChatColor.RED + "Only players can use the lobby command!");
				
				return true;
			}
			
			Player p = (Player) sender;
			
			if(!p.hasPermission("TheHidden.Command.Lobby") && !p.isOp()){
				p.sendMessage(ChatColor.RED + "You don't have permission to use the lobby command!");
				
				return true;
			}
			
			Location loc = p.getLocation();
			
			main.getConfigManager().getConfig("Maps.yml").getConfig().set("lobby.x", loc.getBlockX());
			main.getConfigManager().getConfig("Maps.yml").getConfig().set("lobby.y", loc.getBlockY());
			main.getConfigManager().getConfig("Maps.yml").getConfig().set("lobby.z", loc.getBlockZ());
			
			try {
				main.getConfigManager().getConfig("Maps.yml").getConfig().save("Maps.yml");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			p.sendMessage(ChatColor.AQUA + "Saved new location :)");
			
			return true;
		}
		
		return false;
	}
}
