/***************************************************************************************************************/
/** Copyright 2015 BiggerOnTheInside (development), all rights reserved.                                       */
/** Released under the Binder License (https://github.com/BiggerOnTheInside/Licenses/blob/master/Binder.txt)   */
/***************************************************************************************************************/

package me.wxwsk8er.Waly.Permissions;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import me.wxwsk8er.Waly.Waly;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;

/**
 * @author Walt
 * @version 0.1
 */
@SuppressWarnings("unused")
public class PermissionManager {
	private static final PermissionManager instance = new PermissionManager();
	private Waly main;
	private final ConcurrentHashMap<UUID, PermissionAttachment> permissionMap = new ConcurrentHashMap<UUID, PermissionAttachment>();
	
	/* To prevent initialization. */
	private PermissionManager(){}
	
	/**
	 * @return Our permission manager instance.
	 */
	public static PermissionManager getInstance(){
		return instance;
	}
	
	/**
	 * @param main Waly instance to manage permissions API from.
	 */
	public void init(Waly main){
		this.main = main;
		
		for(Player p : Bukkit.getOnlinePlayers()){
			PermissionAttachment pa = p.addAttachment(main);
			
			permissionMap.put(p.getUniqueId(), pa);
		}
	}
	
	/**
	 * @see init
	 */
	public void release(){
		for(PermissionAttachment pa : permissionMap.values()){
			pa.remove();
		}
		
		permissionMap.clear();
	}
	
	/**
	 * @return Permission map.
	 */
	public ConcurrentHashMap<UUID, PermissionAttachment> getPermissionMap(){
		return permissionMap;
	}
	
	/**
	 * @param p Player to set permission too.
	 * @param permission Permission name to set (eg. Waly.Admin.Help)
	 * @param value True (has permission), False (doesn't have permission).
	 */
	public void setPermission(Player p, String permission, boolean value){
		permissionMap.get(p.getUniqueId()).setPermission(permission, value);
	}
	
	/**
	 * @param p Player to add permission too.
	 * @param permission Permission name to add (eg. Waly.Admin.Help)
	 */
	public void addPermission(Player p, String permission){
		permissionMap.get(p.getUniqueId()).setPermission(permission, true);
	}
	
	/**
	 * @param p Player to un-set permission from.
	 * @param permission Permission name to un-set (eg. Waly.Admin.Help).
	 */
	public void unsetPermission(Player p, String permission){
		permissionMap.get(p.getUniqueId()).unsetPermission(permission);
	}
	
	/**
	 * @param p Player to take permission from.
	 * @param permission Permission name to take (eg. Waly.Admin.Help)
	 */
	public void takePermission(Player p, String permission){
		unsetPermission(p, permission);
		permissionMap.get(p.getUniqueId()).setPermission(permission, false);
	}
}
