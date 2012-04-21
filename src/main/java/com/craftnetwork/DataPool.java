package com.craftnetwork;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.YamlConfiguration;

public class DataPool {

	private static CraftNetwork cn;
	
	private static File friendsFile;
	private static YamlConfiguration friendsConfig;
	
	public DataPool(CraftNetwork cn)
	{
		DataPool.cn = cn;
		
		setupData();
	}
	
	private void setupData()
	{
		Configuration config = cn.getConfig();
		
		config.addDefault("options.file.friendsFile", "friends.cn");
		
		config.options().copyDefaults(true);
		
		cn.saveConfig();
		
		
		friendsFile = new File(cn.getDataFolder(), "friends.cn");
		
		if(!friendsFile.exists())
		{
			try
			{
				friendsFile.createNewFile();
				friendsConfig = YamlConfiguration.loadConfiguration(friendsFile);
			} catch (IOException e) {}
		} else {
			friendsConfig = YamlConfiguration.loadConfiguration(friendsFile);
		}
		
		try
		{
			friendsConfig.save(friendsFile);
		} catch (IOException e) {}
	}
	
	public static void saveData()
	{
		cn.saveConfig();
		
		try
		{
			friendsConfig.save(friendsFile);
		} catch (IOException e) {}
	}
	
	public static void reloadData()
	{
		cn.saveConfig();
		
		friendsConfig = null;
		
		friendsFile = new File(cn.getDataFolder(), "friends.cn");
		
		if(!friendsFile.exists())
		{
			try
			{
				friendsFile.createNewFile();
				friendsConfig = YamlConfiguration.loadConfiguration(friendsFile);
			} catch (IOException e) {}
		} else {
			friendsConfig = YamlConfiguration.loadConfiguration(friendsFile);
		}
		
		try
		{
			friendsConfig.save(friendsFile);
		} catch (IOException e) {}
	}
	
	public static List<?> getFriendsForPlayer(String name)
	{
		return friendsConfig.getList("friendlist." + name);
	}
	
	@SuppressWarnings("unchecked")
	public static void addFriend(String player, String friend)
	{
		List<String> fList = (List<String>) friendsConfig.getList("friendlist." + player);
		
		if(fList == null)
		{
			String[] fBlank = {};
			
			friendsConfig.set("friendlist." + player, Arrays.asList(fBlank));
			
			saveData();
		}
		
		List<String> rfList = (List<String>) friendsConfig.getList("friendlist." + player);
		
		rfList.add(friend);
		
		saveData();
	}
	
	public static YamlConfiguration getFriendsConfig()
	{
		return friendsConfig;
	}
}