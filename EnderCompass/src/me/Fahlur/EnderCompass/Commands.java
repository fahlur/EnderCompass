package me.Fahlur.EnderCompass;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginDescriptionFile;

public class Commands {
	EnderCompassPlugin plugin;
	public PluginDescriptionFile pdfFile;
	
	public void command(EnderCompassPlugin plugin, CommandSender sender, Command cmd, String label, String[] args){
		this.pdfFile = plugin.getDescription();
		
		/*
		 * EnderCompass Base Command
		 */
		if (cmd.getName().equalsIgnoreCase("endercompass")){
			sender.sendMessage(ChatColor.GREEN + "EnderCompass");
			sender.sendMessage(ChatColor.AQUA + "Version: " + ChatColor.GOLD + pdfFile.getVersion());
			sender.sendMessage(ChatColor.AQUA + "Author(s): " + ChatColor.GOLD + pdfFile.getAuthors());

		}
		

		
	}

}
