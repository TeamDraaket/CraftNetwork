package com.craftnetwork;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandCN implements CommandExecutor {

	public CommandCN()
	{
		
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if(sender instanceof Player)
		{
			if(args.length == 2)
			{
				if(args[0].equalsIgnoreCase("add"))
				{
					Player p = (Player) sender;
					Player addFriend = Bukkit.getPlayer(args[1]);
					
					boolean onlineFriend;
					
					try
					{
						onlineFriend = addFriend.isOnline();
					} catch (NullPointerException e) {
						p.sendMessage(ChatColor.RED + "[Social] That player is not online!");
						return true;
					}
					
					if(onlineFriend)
					{
						DataPool.addFriendRequest(p.getName(), addFriend.getName());
						
						p.sendMessage(ChatColor.GREEN + "[Social] You have sent a friend request to " + addFriend.getName());
						addFriend.sendMessage(ChatColor.GREEN + "[Social] " + p.getName() + " has sent you a friend request. To accept, type /cn accept " + p.getName());
					} else {
						p.sendMessage(ChatColor.RED + "[Social] That player is not online!");
					}
				} else if(args[0].equalsIgnoreCase("accept"))
				{
					Player p = (Player) sender;
					Player acceptFriend = Bukkit.getPlayer(args[1]);
					
					boolean onlineFriend;
					
					try
					{
						onlineFriend = acceptFriend.isOnline();
					} catch (NullPointerException e) {
						p.sendMessage(ChatColor.RED + "[Social] That player is not online!");
						return true;
					}
					
					if(onlineFriend)
					{
						if(DataPool.getFriendRequestConfig().getList("friendrequest." + p.getName()).contains(acceptFriend.getName()))
						{
							DataPool.addFriend(p.getName(), acceptFriend.getName());
							DataPool.removeFriendRequest(p.getName(), acceptFriend.getName());
							
							p.sendMessage(ChatColor.GREEN + "[Social] You have accepted " + acceptFriend.getName() + "'s friend request!");
							acceptFriend.sendMessage(ChatColor.GREEN + "[Social] " + p.getName() + " accepted your friend request!");
						}
					} else {
						p.sendMessage(ChatColor.RED + "[Social] That player is not online!");
					}
				}
			}
		}
		
		return true;
	}
}