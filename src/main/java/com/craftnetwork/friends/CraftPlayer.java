package com.craftnetwork.friends;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.craftnetwork.DataPool;

public class CraftPlayer {

	private Player p;
	
	public CraftPlayer(Player p)
	{
		this.p = p;
	}
	
	public void broadcastToFriends(String msg)
	{
		String pName = p.getName();
		
		List<?> friendsList = DataPool.getFriendsForPlayer(pName);
		
		for(int i = 0; i < friendsList.size(); i++)
		{
			String friend = (String) friendsList.get(i);
			Player pFriend = Bukkit.getPlayer(friend);
			
			pFriend.sendMessage(ChatColor.GREEN + "[Social] <" + pName + "> " + msg);
		}
	}
	
	public void printFriendList()
	{
		String pName = p.getName();
		
		List<?> friendsList = DataPool.getFriendsForPlayer(pName);
		
		p.sendMessage(ChatColor.GREEN + "[Social] Current friends: ");
		
		for(int i = 0; i < friendsList.size(); i++)
		{
			String friend = (String) friendsList.get(i);
			
			p.sendMessage(ChatColor.GREEN + "[Social] " + friend);
		}
	}
	
	@SuppressWarnings("unchecked")
	public void setupPlayer()
	{
		List<String> fList = (List<String>) DataPool.getFriendsConfig().getList("friendlist." + p.getName());
		
		if(fList == null)
		{
			String[] fBlank = {};
			
			DataPool.getFriendsConfig().set("friendlist." + p.getName(), Arrays.asList(fBlank));
			
			DataPool.saveData();
			DataPool.reloadData();
		}
	}
}