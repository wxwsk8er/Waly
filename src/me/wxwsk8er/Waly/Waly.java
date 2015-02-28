/***************************************************************************************************************/
/** Copyright 2015 BiggerOnTheInside (development), all rights reserved.                                       */
/** Released under the Binder License (https://github.com/BiggerOnTheInside/Licenses/blob/master/Binder.txt)   */
/***************************************************************************************************************/

package me.wxwsk8er.Waly;

import java.util.HashMap;

import me.wxwsk8er.Waly.ConfigManager.ConfigPath;
import me.wxwsk8er.Waly.Commands.ClassCommand;
import me.wxwsk8er.Waly.Commands.ClearCommand;
import me.wxwsk8er.Waly.Commands.HelpCommand;
import me.wxwsk8er.Waly.Commands.LobbyCommand;
import me.wxwsk8er.Waly.Commands.SetLobbyCommand;
import me.wxwsk8er.Waly.Listeners.PlayerClassAbilityListener;
import me.wxwsk8er.Waly.Listeners.PlayerCraftListener;
import me.wxwsk8er.Waly.Listeners.PlayerDeathListener;
import me.wxwsk8er.Waly.Listeners.PlayerInventoryListener;
import me.wxwsk8er.Waly.Listeners.PlayerJoinListener;
import me.wxwsk8er.Waly.Listeners.PlayerMoveListener;
import me.wxwsk8er.Waly.Listeners.PlayerPingListener;
import me.wxwsk8er.Waly.Listeners.WorldResourceListener;
import me.wxwsk8er.Waly.Permissions.PermissionManager;
import me.wxwsk8er.Waly.Recipes.CobblestoneRecipe;
import me.wxwsk8er.Waly.Recipes.GenericRecipe;
import me.wxwsk8er.Waly.Recipes.PlankRecipe;
import me.wxwsk8er.Waly.Recipes.SharpenedStickRecipe;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("unused")
public class Waly extends JavaPlugin{
	private Location lobbySpawn;
	private static ConfigManager config;
	private static PermissionManager permissionManager;
	private HashMap<GenericRecipe, ItemStack> recipes = new HashMap<GenericRecipe, ItemStack>();
	
	public GenericRecipe plankRecipe = new PlankRecipe();
	public GenericRecipe cobblestoneRecipe = new CobblestoneRecipe();
	public GenericRecipe sharpenedStickRecipe = new SharpenedStickRecipe();
	
	/* Prevent initialization. */
	public Waly(){
		recipes.put(plankRecipe, new ItemStack(Material.WOOD));
		recipes.put(cobblestoneRecipe, new ItemStack(Material.COBBLESTONE));
		recipes.put(sharpenedStickRecipe, new ItemStack(Material.BLAZE_ROD));
	}
	
	/**
	 * @return Set's up Waly.
	 */
	@SuppressWarnings("static-access")
	public void onEnable(){
		Bukkit.getLogger().info("[Waly] Hello! I'm Waly by wxwsk8er, enjoy!");
				
		config = new ConfigManager(this);
		config.loadConfigFiles(new ConfigPath("Maps.yml", "Maps.yml", "Maps.yml"), new ConfigPath("Config.yml", "Config.yml", "Config.yml"), new ConfigPath("Permissions.yml", "Permissions.yml", "Permissions.yml"));

		for(GenericRecipe recipe : recipes.keySet()){
			recipe.init(this, recipes.get(recipe));
		}
		
		getServer().getPluginManager().registerEvents(new PlayerJoinListener(this), this);
		getServer().getPluginManager().registerEvents(new PlayerDeathListener(this), this);
		getServer().getPluginManager().registerEvents(new PlayerMoveListener(), this);
		getServer().getPluginManager().registerEvents(new PlayerInventoryListener(), this);
		getServer().getPluginManager().registerEvents(new PlayerClassAbilityListener(this), this);
		getServer().getPluginManager().registerEvents(new PlayerCraftListener(this), this);
		getServer().getPluginManager().registerEvents(new PlayerPingListener(), this);
		getServer().getPluginManager().registerEvents(new WorldResourceListener(this), this);
		
		getCommand("setLobby").setExecutor(new SetLobbyCommand(this));
		getCommand("lobby").setExecutor(new LobbyCommand(this));
		getCommand("help").setExecutor(new HelpCommand());
		getCommand("clear").setExecutor(new ClearCommand());
		getCommand("class").setExecutor(new ClassCommand());
		getCommand("kit").setExecutor(new ClassCommand());
		
		permissionManager.getInstance().init(this);

		lobbySpawn = config.getLobbyLocation();
	}
	
	/**
	 * @return De-sets-up Waly.
	 */
	public void onDisable(){
		getLogger().info("Bye bye! See you next time!");
	}

	/**
	 * @param entity Player to kill.
	 */
	public void killPlayer(Player entity) {
		
	}
	
	/**
	 * @return Configuration manager object.
	 */
	public ConfigManager getConfigManager(){
		return config;
	}

	/**
	 * @return Permission manager object.
	 */
	public PermissionManager getPermissionManager(){
		return permissionManager;
	}
	
	/**
	 * @return Configuration manager object.
	 * @deprecated
	 */
	public static ConfigManager getConfigManager_(){
		return config;
	}
	
	/**
	 * @return Permission manager object.
	 */
	public static PermissionManager getPermissionManager_(){
		return permissionManager;
	}
}
