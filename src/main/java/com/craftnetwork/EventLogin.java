package com.craftnetwork;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import com.craftnetwork.friends.CraftPlayer;

public class EventLogin implements Listener {

	public EventLogin()
	{
		
	}
	
	@EventHandler
	public void playerLogin(PlayerLoginEvent event)
	{
		Player p = event.getPlayer();
		CraftPlayer cp = new CraftPlayer(p);
		
		cp.setupPlayer();
	}
}