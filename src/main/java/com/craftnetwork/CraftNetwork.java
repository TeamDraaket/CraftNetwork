package com.craftnetwork;

import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

public class CraftNetwork extends JavaPlugin {
	
	public void onEnable()
	{
		new DataPool(this);
		
		Logger.getLogger("Minecraft").info("[Social] Using version 1.0.0-B1 Beta. Bugs may be present and features may not function.");
		Logger.getLogger("Minecraft").info("[Social] Services have loaded! Please contact Nijokkin if errors occur!");
		
		getServer().getPluginManager().registerEvents(new EventLogin(), this);
		
		getCommand("cn").setExecutor(new CommandCN());
	}
	
	public void onDisable()
	{
		
	}
}