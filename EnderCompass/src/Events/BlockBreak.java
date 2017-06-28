package Events;

import me.Fahlur.EnderCompass.EnderCompassPlugin;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreak implements Listener {
	EnderCompassPlugin plugin;
	
	public BlockBreak(EnderCompassPlugin plugin){
		this.plugin = plugin;
	}
	
	@EventHandler (priority = EventPriority.HIGHEST)
    public void blockBreak(BlockBreakEvent e){
    	Player player = e.getPlayer();
    	
    	if (!player.hasPermission("ctp.jumpto")) return;
    	
    	if (player.getInventory().getItemInMainHand().getType().equals(Material.COMPASS)){
    		e.setCancelled(true);
    	}
    }
	
}
