package com.mcgrapeseed.tpa;

import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TPACommand implements CommandExecutor {

	private TPA plugin;
	private HashMap<Player,Player> requests = new HashMap<Player,Player>();

	public TPACommand(TPA tpa) {
		this.plugin = tpa;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if(!(sender instanceof Player)){
			sender.sendMessage("Console is not allowed to tpa");
		}
		Player player = (Player)sender;
		String main = args[0];
		if (main.equalsIgnoreCase("accept")){
			if (requests.containsKey(player)){
			if (player.hasPermission("tpa.tpa")){
				Player teleportee = requests.get(player);
				teleportee.sendMessage(ChatColor.GREEN + "TPA Accepted, Teleporting..");
				player.sendMessage(ChatColor.GREEN +"TPA Accepted, "+ teleportee.getName() + " Has been teleported to you!");
				teleportee.teleport(player);
				requests.remove(player);
			}else{
				player.sendMessage(ChatColor.RED +"You do not have permission!");
			}
			}else{
				player.sendMessage(ChatColor.RED +"There is no request to accept!");
			}
		
			return true;
		}
		if(main.equalsIgnoreCase("decline")){
			if (requests.containsKey(player)){
if (player.hasPermission("tpa.tpa")){
	Player teleportee = requests.get(player);
	teleportee.sendMessage(ChatColor.RED +"TPA Declined");
	player.sendMessage(ChatColor.RED +"TPA Declined.");
			}else{
				player.sendMessage(ChatColor.RED +"You do not have permission!");
			}
			}else{
				player.sendMessage(ChatColor.RED +"No request to decline!");
			}
			
			return true;
		}
		if (!player.hasPermission("tpa.tp")){
			player.sendMessage(ChatColor.RED +"You do not have permission!");
		}
		Player recipient = plugin.getServer().getPlayer(main);
		if ( recipient == null){
			sender.sendMessage("Invalid Player name.");
			return true;
		}
		
		requests.put(recipient, player);
		recipient.sendMessage(ChatColor.RED +player.getName()+ " want's to tp to you.");
		recipient.sendMessage(ChatColor.RED +"Use /tpa accept or /tpa decline to accept or decline this request.");
		
		return true;
	}

}
