/***************************************************************************************************************/
/** Copyright 2015 BiggerOnTheInside (development), all rights reserved.                                       */
/** Released under the Binder License (https://github.com/BiggerOnTheInside/Licenses/blob/master/Binder.txt)   */
/***************************************************************************************************************/

package me.wxwsk8er.Waly.Commands;

import me.wxwsk8er.Waly.Messages;
import me.wxwsk8er.Waly.Waly;
import me.wxwsk8er.Waly.API.PlayerGotoLobbyEvent;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LobbyCommand implements CommandExecutor{
private Waly main;
	
	public LobbyCommand(Waly main){
		this.main = main;
	}
	
	/**
	 * @param sender Issuer of command.
	 * @param cmd Command issued.
	 * @param label Label of command.
	 * @param args Arguments of command.
	 * @return Did command run normally.
	 */
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("lobby")){
			PlayerGotoLobbyEvent event;
			
			if(args.length <= 0){
				if(!(sender instanceof Player)){
					sender.sendMessage(Messages.ONLY_PLAYERS_USE_COMMAND.getMessage());
				
					return true;
				}
				
				Player p = (Player) sender;
				event = new PlayerGotoLobbyEvent(p);
				
				if(p.hasPermission("Waly.Admin.Lobby")){
					p.sendMessage(ChatColor.AQUA + "Sending you to the lobby");
					p.teleport(main.getConfigManager().getLobbyLocation());
				
					return true;
				}
				
				p.sendMessage(Messages.NOT_ENOUGH_PERMISSIONS.getMessage());
				
				return true;
			}
			else{
				if(sender instanceof Player){
					Player p = (Player) sender;
					event = new PlayerGotoLobbyEvent(p);
					
					if(p.hasPermission("Waly.Admin.Lobby")){
						Bukkit.getServer().getPluginManager().callEvent(event);
						
						if(!event.isCancelled()){
							p.sendMessage(ChatColor.AQUA + "Sending you to the lobby.");
							p.teleport(main.getConfigManager().getLobbyLocation());
						}
						else{
							p.sendMessage(ChatColor.RED + "Lobby teleport cancelled.");
						}
						
						return true;
					}
					
					p.sendMessage(Messages.NOT_ENOUGH_PERMISSIONS.getMessage());
					
					return true;
				}
				
				if(args.length >= 1){
					Player p = Bukkit.getServer().getPlayer(args[0]);
					event = new PlayerGotoLobbyEvent(p);
						
					if(p != null){
						if(args[1] != null){
							if(!event.isCancelled()){
								p.sendMessage(ChatColor.AQUA + "Sending you to lobby. Reason: " + ChatColor.WHITE + args[1]);
								p.teleport(main.getConfigManager().getLobbyLocation());
							}
							else{
								p.sendMessage(ChatColor.RED + "Lobby teleport cancelled.");
							}
						}
						else{
							if(!event.isCancelled()){
								p.sendMessage(ChatColor.AQUA + "Sending you to lobby.");
								p.teleport(main.getConfigManager().getLobbyLocation());
							}
							else{
								p.sendMessage(ChatColor.RED + "Lobby teleport cancelled.");
							}
						}
					}
					else{
						sender.sendMessage(ChatColor.RED + "Player not found!");
					}
					
					return true;
				}
				else{
					sender.sendMessage(ChatColor.RED + "Only one argument required!");
					
					return true;
				}
			}
		}
		
		return false;
	}
}

