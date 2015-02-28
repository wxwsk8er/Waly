/***************************************************************************************************************/
/** Copyright 2015 BiggerOnTheInside (development), all rights reserved.                                       */
/** Released under the Binder License (https://github.com/BiggerOnTheInside/Licenses/blob/master/Binder.txt)   */
/***************************************************************************************************************/

package me.wxwsk8er.Waly.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * @author Walt (wxwsk8er)
 */
public class ClearCommand implements CommandExecutor{
	public ClearCommand(){
		
	}
	
	/**
	 * @param sender The sender of this command
	 * @param cmd The command that was issued.
	 * @param label The label for this command.
	 * @param args The array of arguments.
	 */
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("clear")){
			for(int i = 0; i < 125; i++){
				sender.sendMessage(" ");
			}
		
			return true;
		}
		
		return false;
	}

}
