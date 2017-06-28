package Events;

import com.sk89q.worldguard.bukkit.WGBukkit;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import me.Fahlur.EnderCompass.EnderCompassPlugin;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.util.BlockIterator;

public class Compass implements Listener {
	EnderCompassPlugin plugin;
	FileConfiguration config;
	
	public Compass(EnderCompassPlugin plugin){
		this.plugin = plugin;
		this.config = plugin.getConfig();
	}
	
	
	public final Block getTargetBlock(Player player, int range) {
        BlockIterator iter = new BlockIterator(player, range);
        Block lastBlock = iter.next();
        while (iter.hasNext()) {
            lastBlock = iter.next();
            if (lastBlock.getType() == Material.AIR) {
                continue;
            }
            break;
        }
        return lastBlock;
    }
	
	 @EventHandler (priority = EventPriority.MONITOR)
		public void EnderCompass(CompassEvent e){
			Player player = e.getPlayer();
			//Block block = player.getTargetBlock((Set<Material>) null, 256);
			Block block = getTargetBlock(player, config.getInt("Settings.maxDistance"));
		
			
			Location bLoc = block.getLocation();
			Boolean prot = false;

		
			

			if (player.getLocation().getBlock().getBiome().equals(Biome.HELL)){
				if (!player.hasPermission("ctp.jumpto.nether")){
					player.sendMessage(ChatColor.DARK_RED+"The powers of ender do not work in such a world.");
					e.setCancelled(true);
				}
			}
			
			if (player.getLocation().getBlock().getBiome().equals(Biome.SKY)){
				if (!player.hasPermission("ctp.jumpto.end")){
					player.sendMessage(ChatColor.DARK_RED+"The powers of ender do not work in such a world.");
					e.setCancelled(true);
				}
			}
			
			
			/*
			 * World Guard Stuff
			 */
			if (config.getBoolean("WorldGuard.UseCustomFlags")){
				for(ProtectedRegion r : WGBukkit.getRegionManager(player.getLocation().getWorld()).getApplicableRegions(player.getLocation())) {
					if (r.getFlag(EnderCompassPlugin.ecuse) != null){
						if(r.getFlag(EnderCompassPlugin.ecuse).toString() == "DENY"){
							prot = true;
						}
					}
		         }

				for(ProtectedRegion r : WGBukkit.getRegionManager(player.getLocation().getWorld()).getApplicableRegions(bLoc)) {
					if (r.getFlag(EnderCompassPlugin.ecuse) != null){
						if(r.getFlag(EnderCompassPlugin.ecuse).toString() == "DENY"){
							prot = true;
						}
					}
		         }
				
				if (prot){
					if (player.hasPermission("ctp.flag.override") || player.isOp()) return;
					player.sendMessage(ChatColor.DARK_RED+"You're not allowed to go there.");
					e.setCancelled(true);
				}
			}
			
			
			/*
			 *  End Event
			 */
		}
	
	
	
}
