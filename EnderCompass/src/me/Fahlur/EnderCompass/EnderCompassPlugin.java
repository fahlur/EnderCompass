package me.Fahlur.EnderCompass;

import Events.BlockBreak;
import Events.Compass;
import Events.CompassEvent;
import Events.PlayerInteract;
import WGFlags.ECUse;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.flags.StateFlag;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Random;


public class EnderCompassPlugin extends JavaPlugin implements Listener {
	public EnderCompassPlugin plugin;

	public CompassEvent ce;
	public PluginDescriptionFile pdfFile = this.getDescription();
	public  final FileConfiguration config = this.getConfig();
	private static WorldGuardPlugin worldGuardPlugin;
	public Commands command = new Commands();
	
	/*
	 * World Guard Flags
	 */
	public static final StateFlag ecuse = new StateFlag("ec-use", false, null);
	

    /*
     * On Load
     */
	public void onLoad()
	{
		worldGuardPlugin = (WorldGuardPlugin)getServer().getPluginManager().getPlugin("WorldGuard");
		worldGuardPlugin.getFlagRegistry().register(ecuse);
		

		
	}
	
	
	// TODO


	
	/*
	 * On Enable
	 */
    public void onEnable() {
    	plugin = this;
    
    
    
    
    	
    	loadConfiguration();
        // register events
        Bukkit.getServer().getPluginManager().registerEvents(this, this);
        //wg reg
        worldGuardPlugin.getSessionManager().registerHandler(ECUse.FACTORY, null);
        /* Events */
		registerEvents(
				this.plugin,
				new BlockBreak(plugin),
				new PlayerInteract(plugin),
				new Compass(plugin)
				);

		
    }
    
    /*
     * Load Configuration
     */
    public void loadConfiguration(){
		config.options().copyDefaults(true);
		this.saveConfig();
	}
    
    /*
     * World Guard Plugin
     */
    public static WorldGuardPlugin getWorldGuard()
    {
      return worldGuardPlugin;
    }
    
    /*
     * On Disable
     */
    public void onDisable() {
    	PluginDescriptionFile pdfFile = this.getDescription();
        System.out.println(pdfFile.getName() + " version " + pdfFile.getVersion() + " stopping...");
    }

    /*
     * Random Integer
     */
    public static int randInt(int min, int max) {
	    Random rand = new Random();
	    int randomNum = rand.nextInt((max - min) + 1) + min;
	    return randomNum;
    }
    
    /*
     * Register Events
     */
    public void registerEvents(org.bukkit.plugin.Plugin plugin, Listener... listeners) {
		for (Listener listener : listeners) {
		 	Bukkit.getServer().getPluginManager().registerEvents(listener, plugin);
		}
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
			command.command(plugin, sender, cmd, label, args);
			return true;
	}
    
}