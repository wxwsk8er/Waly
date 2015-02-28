/***************************************************************************************************************/
/** Copyright 2015 BiggerOnTheInside (development), all rights reserved.                                       */
/** Released under the Binder License (https://github.com/BiggerOnTheInside/Licenses/blob/master/Binder.txt)   */
/***************************************************************************************************************/

package me.wxwsk8er.Waly.Commands;

import me.wxwsk8er.Waly.Messages;
import me.wxwsk8er.Waly.PlayerMeta;
import me.wxwsk8er.Waly.Waly;
import me.wxwsk8er.Waly.Util.KitUtils;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ClassCommand implements CommandExecutor{
	
	/**
	 * @param sender Issuer of command.
	 * @param cmd Command issued.
	 * @param label Label of command issued.
	 * @param args Arguments of command.
	 * @return Did command run normally.
	 */
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("class") || cmd.getName().equalsIgnoreCase("kit")){
			if(!(sender instanceof Player)){
				sender.sendMessage(Messages.ONLY_PLAYERS_USE_COMMAND.getMessage());
				
				return true;
			}
			
			Player p = (Player) sender;
			
			if(p.hasPermission("test.t")){
				p.sendMessage("You have permissions!");
				Waly.getPermissionManager_().addPermission(p, "test.t");
			}
			else{
				Waly.getPermissionManager_().takePermission(p, "test.t");
			}
			
			if(!p.hasPermission("Waly.Admin.ClassSelector") && !PlayerMeta.getMeta(p).isInLobby()){
				p.sendMessage(Messages.NOT_ENOUGH_PERMISSIONS.getMessage());
				
				return true;
			}
			
			p.sendMessage(Messages.OPENING_CLASS_SELECTOR.getMessage());
			KitUtils.showKitSelector(p);
			
			return true;
		}
		
		return false;
	}
}
