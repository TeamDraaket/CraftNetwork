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
	private static File friendRequestFile;
	private static YamlConfiguration friendsConfig;
	private static YamlConfiguration friendRequestConfig;
	
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
		
		friendRequestFile = new File(cn.getDataFolder(), "friendrequest.cn");
		
		if(!friendRequestFile.exists())
		{
			try
			{
				friendRequestFile.createNewFile();
				friendRequestConfig = YamlConfiguration.loadConfiguration(friendRequestFile);
			} catch (IOException e) {}
		} else {
			friendRequestConfig = YamlConfiguration.loadConfiguration(friendRequestFile);
		}
		
		try
		{
			friendRequestConfig.save(friendRequestFile);
		} catch (IOException e) {}
	}
	
	public static void saveData()
	{
		cn.saveConfig();
		
		try
		{
			friendsConfig.save(friendsFile);
		} catch (IOException e) {}
		try
		{
			friendRequestConfig.save(friendRequestFile);
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
		
		friendRequestFile = new File(cn.getDataFolder(), "friendrequest.cn");
		
		if(!friendRequestFile.exists())
		{
			try
			{
				friendRequestFile.createNewFile();
				friendRequestConfig = YamlConfiguration.loadConfiguration(friendRequestFile);
			} catch (IOException e) {}
		} else {
			friendRequestConfig = YamlConfiguration.loadConfiguration(friendRequestFile);
		}
		
		try
		{
			friendRequestConfig.save(friendRequestFile);
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
	
	@SuppressWarnings("unchecked")
	public static void addFriendRequest(String player, String friend)
	{
		List<String> frList = (List<String>) friendRequestConfig.getList("friendrequest." + player);
		
		if(frList == null)
		{
			String[] frBlank = {};
			
			friendRequestConfig.set("friendrequest." + player, Arrays.asList(frBlank));
			
			saveData();
		}
		
		List<String> rfList = (List<String>) friendRequestConfig.getList("friendrequest." + player);
		
		rfList.add(friend);
		
		saveData();
	}
	
	@SuppressWarnings("unchecked")
	public static void removeFriendRequest(String player, String friend)
	{
		List<String> frList = (List<String>) friendRequestConfig.getList("friendrequest." + player);
		
		if(frList == null)
		{
			String[] frBlank = {};
			
			friendRequestConfig.set("friendrequest." + player, Arrays.asList(frBlank));
			
			saveData();
		}
		
		List<String> rfList = (List<String>) friendRequestConfig.getList("friendrequest." + player);
		
		rfList.remove(friend);
		
		saveData();
	}
	
	public static YamlConfiguration getFriendsConfig()
	{
		return friendsConfig;
	}
	
	public static YamlConfiguration getFriendRequestConfig()
	{
		return friendRequestConfig;
	}
}