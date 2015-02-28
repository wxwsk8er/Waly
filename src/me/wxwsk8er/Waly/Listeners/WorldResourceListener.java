/***************************************************************************************************************/
/** Copyright 2015 BiggerOnTheInside (development), all rights reserved.                                       */
/** Released under the Binder License (https://github.com/BiggerOnTheInside/Licenses/blob/master/Binder.txt)   */
/***************************************************************************************************************/

package me.wxwsk8er.Waly.Listeners;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

import me.wxwsk8er.Waly.Kit;
import me.wxwsk8er.Waly.PlayerMeta;
import me.wxwsk8er.Waly.Waly;
import net.md_5.bungee.api.ChatColor;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

public class WorldResourceListener implements Listener{
	public class Resource{
		private Material rec;
		private int time;
		private int xp;
		private ItemStack i;
		
		public Resource(Material mat, int time, int xp){
			this.rec = mat;
			this.time = time;
			this.xp = xp;
		}
		
		public Resource(ItemStack i, int time, int xp){
			this.i = i;
			this.time = time;
			this.xp = xp;
		}
	}
	
	private Waly main;
	private HashMap<Material, Resource> resources = new HashMap<Material, Resource>();
	private HashSet<Location> locationsToDo = new HashSet<Location>();
	private HashMap<Material, Location> onTimeResources = new HashMap<Material, Location>();
	private Random randomHelper = new Random();
	
	public WorldResourceListener(Waly main){
		this.main = main;
		
		addResource(Material.COAL_ORE, 10, 8);
		addResource(Material.IRON_ORE, 20, 10);
		addResource(Material.GOLD_ORE, 20, 10);
		addResource(Material.REDSTONE_ORE, 20, 10);
		addResource(Material.GLOWING_REDSTONE_ORE, 20, 10);
		addResource(Material.DIAMOND_ORE, 30, 10);
		addResource(Material.EMERALD_ORE, 18, 40);
		addResource(Material.LOG, 10, 2);
		addResource(Material.MELON_BLOCK, 10, 0);
		addResource(Material.PUMPKIN, 10, 0);
		addResource(Material.STONE, 10, 8);
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onResourceBreak(BlockBreakEvent event){
		if(resources.containsKey(event.getBlock().getType())){
			event.setCancelled(true);
			breakResource(event.getPlayer(), event.getBlock());
			
			event.getBlock().getWorld().playEffect(event.getBlock().getLocation(), Effect.STEP_SOUND, event.getBlock().getTypeId());
		}
		else if(locationsToDo.contains(event.getBlock().getLocation())){
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onResourcePlace(BlockPlaceEvent event){
		if(resources.containsKey(event.getBlock().getType())){
			event.setCancelled(true);
		}
	}
	
	private void addResource(Material mat, int time, int xp){
		resources.put(mat, new Resource(getDropMaterial(mat), time, xp));
	}
	
	private void spawnResource(){
		
	}
	
	private void breakResource(Player p, Block b){
		Material mat = b.getType();
		Resource r = resources.get(b.getType());
		Kit k = PlayerMeta.getMeta(p).getKit();
		
		if(k.getName().toLowerCase().contains("wood age")){
			if(mat.name().contains("ORE") && !(mat == Material.COAL_ORE)){
				p.sendMessage(ChatColor.RED + "Your not advanced enough to mine that!");
				
				return;
			}
		}
		
		ItemStack it = new ItemStack(r.rec, getDropAmount(mat));
		ItemMeta im = it.getItemMeta();
		
		if(mat == Material.STONE){
			im.setDisplayName("Small rock");
		}
		
		it.setItemMeta(im);
		p.getInventory().addItem(it);
		p.giveExp(r.xp);
		p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1.0f, (randomHelper.nextFloat() * .2f) + .9f);
		
		queryRespawn(b);
	}
	
	@SuppressWarnings("deprecation")
	private void queryRespawn(final Block b){
		final Material mat = b.getType();
		
		if(b.getType() != Material.STONE){
			b.setType(getRespawnMaterial(mat));
		}
		else{
			b.setType(Material.AIR);
		}
		
		locationsToDo.add(b.getLocation());
		
		main.getServer().getScheduler().runTaskLater(main, new BukkitRunnable(){
			public void run(){
				b.setType(mat);
				locationsToDo.remove(b.getLocation());
				b.getWorld().playEffect(b.getLocation(), Effect.STEP_SOUND, b.getTypeId());
			}
		}, resources.get(mat).time * 20);
	}
	
	public int getDropAmount(Material mat){
		switch(mat){
			case MELON_BLOCK:
			case PUMPKIN:
				return 2 + randomHelper.nextInt(5);
			case REDSTONE_ORE:
			case GLOWING_REDSTONE_ORE:
				return 3;
			case STONE:
				return 2;
			default:
				return 1;
		}
	}
	
	public Material getRespawnMaterial(Material mat){
		switch(mat){
			case LOG:
			case MELON_BLOCK:
			case PUMPKIN:
				return Material.AIR;
			default:
				return Material.COBBLESTONE;
		}
	}
	
	public Material getDropMaterial(Material mat){
		switch(mat){
			case MELON_BLOCK:
				return Material.MELON;
			case COAL_ORE:
				return Material.COAL;
			case DIAMOND_ORE:
				return Material.DIAMOND;
			case REDSTONE_ORE:
			case GLOWING_REDSTONE_ORE:
				return Material.REDSTONE;
			case GLOWSTONE:
				return Material.GLOWSTONE_DUST;
			case EMERALD:
				return Material.EMERALD_ORE;
			case LOG:
				return Material.STICK;
			case STONE:
				return Material.CLAY_BALL;
			default:
				return mat;
		}
	}
}
