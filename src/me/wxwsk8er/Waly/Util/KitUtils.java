/***************************************************************************************************************/
/** Copyright 2015 BiggerOnTheInside (development), all rights reserved.                                       */
/** Released under the Binder License (https://github.com/BiggerOnTheInside/Licenses/blob/master/Binder.txt)   */
/***************************************************************************************************************/

package me.wxwsk8er.Waly.Util;

import java.util.List;

import me.wxwsk8er.Waly.Kit;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class KitUtils {
	public static void showKitSelector(Player p) {		
        int size = ((Kit.values().length + 8) / 9) * 9;
        Inventory inv = Bukkit.createInventory(p, size, p.getName() + " - Select Class");
        for (Kit kit : Kit.values()) {
            ItemStack i = kit.getIcon().clone();
            
            ItemMeta im = i.getItemMeta();
            List<String> lore = im.getLore();
            
            if(kit.name().contains("SPACER")){
            	im.setDisplayName(" ");
                
                inv.addItem(i);
            	
                continue;
            }
            
            lore.add(ChatColor.GRAY + "---------------");
            
            if (kit.isOwnedBy(p)) {
                lore.add(ChatColor.GREEN + "Unlocked, click to use!");
            } else {
                lore.add(ChatColor.RED + "Locked, advance to unlock!");
            }
            
            im.setLore(lore);
            i.setItemMeta(im);
            inv.addItem(i);
        }
        p.openInventory(inv);
    }
}
