/***************************************************************************************************************/
/** Copyright 2015 BiggerOnTheInside (development), all rights reserved.                                       */
/** Released under the Binder License (https://github.com/BiggerOnTheInside/Licenses/blob/master/Binder.txt)   */
/***************************************************************************************************************/

package me.wxwsk8er.Waly.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HelpCommand implements CommandExecutor{
	
	public HelpCommand(){
		
	}

	/**
	 * @param sender Issuer of command.
	 * @param cmd Command that was issued.
	 * @param label Label of command.
	 * @param args Arguments of command.
	 * @return Did command run normally.
	 */
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("help")){
			if(sender instanceof Player){
				((Player)sender).performCommand("/clear");
			}
			
			if(args.length == 0){
				if(sender.hasPermission("Waly.Admin.Help")){
					sender.sendMessage(ChatColor.AQUA + "=================   Waly Help   =================");
					sender.sendMessage(ChatColor.AQUA + "/setlobby       - Set lobby location where you are");
					sender.sendMessage(ChatColor.AQUA + "/help           - Show this information.");
					sender.sendMessage(ChatColor.AQUA + "/lobby [player] - Send player or self to lobby.");
					sender.sendMessage(ChatColor.AQUA + "/class, /kit    - Show class selector to self.");
					sender.sendMessage(ChatColor.AQUA + "=================  Thats all  :) =================");
				}
				else{
					sender.sendMessage(ChatColor.AQUA + "How to play, Rules");
				}
			}
			else{
				if(args[0].equalsIgnoreCase("how")){
					sender.sendMessage(ChatColor.AQUA + "Progress through the game by collecting resources and helping friends!");
				}
				else if(args[0].equalsIgnoreCase("rules")){
					sender.sendMessage(ChatColor.AQUA + "1. No griefing");
					sender.sendMessage(ChatColor.AQUA + "2. No hacking");
					sender.sendMessage(ChatColor.AQUA + "3. No swearing");
					sender.sendMessage(ChatColor.AQUA + "4. No spamming");
				}
				else{
					sender.sendMessage(ChatColor.AQUA + "How to play, Rules");
				}
			}
			
			return true;
		}
		
		return false;
	}

}
