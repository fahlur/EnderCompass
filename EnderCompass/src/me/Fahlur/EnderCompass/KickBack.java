package me.Fahlur.EnderCompass;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class KickBack {

	public double randDouble(double dbl){
		return Math.random() * dbl;
	}
	
	public void kickBack(Player player, FileConfiguration config){
		
		int kickBackChance = config.getInt("Settings.KickBackChance");
		
		if (config.getBoolean("Settings.EnableKickbackEffect")){
    		if (player.hasPermission("ctp.kickback.ignore") || player.isOp()) return;
    		double rand = randDouble(500);
    		if (rand <= kickBackChance){
    			

    			switch (EnderCompassPlugin.randInt(1,2)){
	    			case 1:
	    				player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_BURP, 35, 1);
	        			player.sendMessage(ChatColor.DARK_PURPLE+"You are feeling drained while using the ender compass.");
	        			player.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 220, 1), true);
	        			player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 220, 1), true);
	    				break;
	    			case 2:
	    				player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_BURP, 35, 1);
	        			player.sendMessage(ChatColor.DARK_PURPLE+"The powers of ender overcame your senses.");
	        			player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 220, 1), true);
	        			player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 220, 1), true);
	        			player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 220, 1), true);
	    				break;
    			}
    			
    	
    			
    			
    		}
    	}
		
	}
	
	
}
