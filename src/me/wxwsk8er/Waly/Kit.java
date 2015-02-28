/*******************************************************************************
 * Copyright 2014 stuntguy3000 (Luke Anderson) and coasterman10.
 *  
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301, USA.
 ******************************************************************************/
package me.wxwsk8er.Waly;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * @author Walt
 * @version 0.2
 */
public enum Kit {
	WOOD_AGE_STARTER(Material.STICK){
		{
			lore.add("You are the beginner.");
			lore.add("");
			lore.add("You resieve only the bare minimum");
			lore.add("required to survive, and are forced");
			lore.add("to find your way to advancement if");
			lore.add("you are the survive the deadly world!");
			lore.add("");
			lore.add("Comes with food, and a stick");
			
			spawnItems.add(new ItemStack(Material.MELON, 4));
			spawnItems.add(new ItemStack(Material.STICK));
		}
	},
	
	WOOD_AGE_BUILDER(Material.WOOD){
		{
			lore.add("You are the first builder.");
			lore.add("");
			lore.add("You have to tools minimum tools");
			lore.add("required to build, but combined");
			lore.add("with your talent, you can survive");
			lore.add("and advance easyily!");
			lore.add("");
			lore.add("Comes with food, and planks");
			
			spawnItems.add(new ItemStack(Material.MELON, 5));
			spawnItems.add(new ItemStack(Material.WOOD, 15));
		}
	},
	
	WOOD_AGE_FIGHTER(Material.WOOD_SWORD){
		{
			lore.add("You are the new warrior.");
			lore.add("");
		}
	},
	
	/*WOOD_AGE_MINER(Material.WOOD_PICKAXE){
		{
			
		}
	},
	
	WOOD_AGE_TINKERER(Material.WOOD_PLATE){
		{
			
		}
	},
*/
	SPACER_0(Material.BEDROCK){
		{
			lore.add("");
		}
	},
	
	SPACER_1(Material.BEDROCK){
		{
			lore.add("");
		}
	},
	
	SPACER_2(Material.BEDROCK){
		{
			lore.add("");
		}
	},
	
	SPACER_3(Material.BEDROCK){
		{
			lore.add("");
		}
	},
	
	SPACER_4(Material.BEDROCK){
		{
			lore.add("");
		}
	},
	
	SPACER_5(Material.BEDROCK){
		{
			lore.add("");
		}
	},
	STONE_AGE_STARTER(Material.CLAY_BALL){
		{
			lore.add("You aren't the dumbest thing around!");
			lore.add("");
			lore.add("You can now mine stone to get");
			lore.add("little rocks, which you can");
			lore.add("combine to make cobblesone and");
			lore.add("craft tools and weapons with!");
			lore.add("");
			lore.add("Comes with food, and a few");
			lore.add("clay balls");
		}
	};
	
	/*STONE_AGE_BUILDER(Material.COBBLESTONE){
		{
			
		}
	},
	
	STONE_AGE_FIGHTER(Material.STONE_SWORD){
		{
			
		}
	},
	
	STONE_AGE_MINER(Material.STONE_PICKAXE){
		{
			
		}
	},
	
	STONE_AGE_TINKERER(Material.STONE_PLATE){
		{
			
		}
	},
	
	STONE_AGE_SMITH(Material.FURNACE){
		{
			
		}
	},
	
	MASTER_JUMPER(Material.FEATHER){
		{
		
		}
	};
	*/
	
    static {
        for (Kit kit : values())
            kit.init();
	};

    private ItemStack icon;
    List<String> lore = new ArrayList<String>();
    List<ItemStack> spawnItems = new ArrayList<ItemStack>();
    ItemStack[] spawnArmor = new ItemStack[] {
            new ItemStack(Material.LEATHER_BOOTS),
            new ItemStack(Material.LEATHER_LEGGINGS),
            new ItemStack(Material.LEATHER_CHESTPLATE),
            new ItemStack(Material.LEATHER_HELMET) };

    /**
     * @param m Material to use as icon.
     */
    Kit(Material m) {
        icon = new ItemStack(m);
        ItemMeta meta = icon.getItemMeta();
        meta.setDisplayName(getName());
        icon.setItemMeta(meta);
    }

    /**
     * @return Sets-up the kit object.
     */
    private void init() {
        for (int i = 0; i < lore.size(); i++) {
            String s = lore.get(i);
            s = ChatColor.AQUA + s;
            lore.set(i, s);
        }
        ItemMeta meta = icon.getItemMeta();
        meta.setLore(lore);
        icon.setItemMeta(meta);
    }

    /**
     * @param name Name of kit to return (eg. 'wood_age_starter')
     * @return Kit with that name.
     */
    public static Kit getKit(String name) {
        for (Kit type : values()) {
            if (type.name().equalsIgnoreCase(name))
                return type;
        }
        return null;
    }

    /**
     * @param recipient Player to give kit to.
     * @return Player gets kit.
     */
    public void give(Player recipient) {
        PlayerInventory inv = recipient.getInventory();
        inv.clear();

        for (ItemStack item : spawnItems) {
            ItemStack toGive = item.clone();
            inv.addItem(toGive);
        }

        recipient.removePotionEffect(PotionEffectType.SPEED);

        inv.setArmorContents(spawnArmor);
        colorizeArmor(inv, Color.RED);

        if (this == WOOD_AGE_STARTER)
            addFlashParticles(recipient);

            recipient.setMaxHealth(20.0);
    }

    /**
     * @param inv Players inventory to add armor to.
     * @param color Color to dye leather armor.
     */
    private void colorizeArmor(PlayerInventory inv, Color color) {
        for (ItemStack item : inv.getArmorContents()) {
            if (item.getItemMeta() instanceof LeatherArmorMeta) {
                LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
                meta.setColor(color);
                item.setItemMeta(meta);
            }
        }
    }

    /**
     * @return Name of kit in pretty form (eg. 'Wood age starter')
     */
    public String getName() {
        return (name().substring(0, 1) + name().substring(1).toLowerCase()).replace('_', ' ');
    }

    /**
     * @param p Player to check
     * @return Does player of the kit.
     */
    public boolean isOwnedBy(Player p) {
        return p.isOp() || (this.name().contains("WOOD") && this.name().contains("STARTER")) || p.hasPermission("Waly.Class." + this.name().toLowerCase());
    }

    /**
     * @param p Player to add speed effect to.
     */
    public void addFlashParticles(Player p) {
        if (this != WOOD_AGE_STARTER)
            return;
        p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED,
                Integer.MAX_VALUE, 0, true), true);
    }

    /**
     * @return Icon for this kit.
     */
    public ItemStack getIcon() {
        return icon;
    }
}
