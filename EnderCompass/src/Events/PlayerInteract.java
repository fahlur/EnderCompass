package Events;


import me.Fahlur.EnderCompass.EnderCompassPlugin;
import me.Fahlur.EnderCompass.KickBack;
import me.Fahlur.EnderCompass.MaterialValues;
import me.Fahlur.EnderCompass.SafeLocation;
import net.minecraft.server.v1_12_R1.EntityPlayer;
import net.minecraft.server.v1_12_R1.PacketPlayOutSetCooldown;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.BlockIterator;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;

public class PlayerInteract implements Listener {
	EnderCompassPlugin plugin;
	FileConfiguration config;
	
	
	
	
    /*
     * Class Imports
     */
	SafeLocation sl = new SafeLocation();
	KickBack kb = new KickBack();
	
	/*
     * HashMaps
     */
    public HashMap<String, Long> cooldowns = new HashMap<String, Long>();
    
	 /*
     * Variables
     */
	boolean isFence = false;
	boolean result = false;
	boolean isLiquid = false;
	int cooldownTime;
	
	public PlayerInteract(EnderCompassPlugin plugin){
		this.plugin = plugin;
		this.config = plugin.getConfig();
		this.cooldownTime = config.getInt("Settings.CoolDownInSeconds");
	}
	public final Block getTargetBlock(Player player, int range, Set<Material> ignoredblocks) {
        BlockIterator iter = new BlockIterator(player, range);
        Block lastBlock = iter.next();
        while (iter.hasNext()) {
            lastBlock = iter.next();
            if (lastBlock.getType() == Material.AIR || ignoredblocks.contains(lastBlock.getType())) {
                continue;
            }
            break;
        }
        return lastBlock;
    }
	

	
	/*
	 * Player Interact Event
	 */
	
	
	@EventHandler(priority=EventPriority.HIGHEST)
	public void onPlayerInteract(PlayerInteractEvent event) throws InterruptedException{
    	Player player = event.getPlayer();
    	
    	if (player == null) return;
    	
    	if (!player.hasPermission("ctp.jumpto")) return;
    	
    	Action action = event.getAction();
    	
    	if (action != Action.LEFT_CLICK_AIR && action != Action.LEFT_CLICK_BLOCK) return;
    	EquipmentSlot eq = event.getHand();
    	if (eq.equals(EquipmentSlot.OFF_HAND)){
    		return;
    	}
    	// with compass in hand
    	ItemStack item = event.getPlayer().getItemInHand();
    	if (item == null || item.getType() != Material.COMPASS) return;
    	
    	/*
    	 * Custom Compass Event
    	 */
    	CompassEvent ce = new CompassEvent(player);
		Bukkit.getServer().getPluginManager().callEvent(ce);
    	if (ce.isCancelled()) {
			return;
		}

    	// get block pointing at
    	//Block block = player.getTargetBlock(MaterialValues.IgnoredBlocks, 500);
    	Block block = getTargetBlock(player, config.getInt("Settings.maxDistance"), MaterialValues.IgnoredBlocks);
    	
    	if(cooldowns.containsKey(player.getName())) {
            long secondsLeft = ((cooldowns.get(player.getName())/1000)+cooldownTime) - (System.currentTimeMillis()/1000);
            if(secondsLeft>0) {
                // Still cooling down
                //player.sendMessage(ChatColor.DARK_PURPLE+"You are unable to harness the powers of ender for "+ChatColor.GOLD+ secondsLeft +ChatColor.DARK_PURPLE+" more seconds!");
                return;
            }
        }
    	
    	if (block == null || block.getType() == Material.AIR){
    		player.sendMessage(ChatColor.DARK_PURPLE+"The powers of Ender can not travel such a distance!");
    		return;
    	}
    	
    	if (Arrays.asList(MaterialValues.PUT_ON_TOP).contains(block.getType())){
    		isFence = true;
    	}

    	// filter location - nothing above/below limit (and no 0 position - sky)
    	Location dest = block.getLocation();
    	int y = dest.getBlockY();
    	if (y <= 0 || y > 256) return;
    	
    	// get to the topmost block
    	while (block.getType() != Material.AIR) {
    		block = block.getRelative(BlockFace.UP);
    		if (block == null) return;
    		dest = block.getLocation();
    		if (dest.getBlockY() > 256) return; 
    	}
    	
    	// use player's direction of sight and correct for block offset
    	Location src = player.getLocation();
    	dest.setPitch(src.getPitch());
    	dest.setYaw(src.getYaw());
    	dest.setX(dest.getX()+0.5);
    	dest.setZ(dest.getZ()+0.5);
    	if (isFence){
    		dest.setY(dest.getY()+0.5);
    		isFence = false;
    	}

			
    
    	
    	
   
		// and finally teleport
    	if (ce.isCancelled()) {
			return;
		}
    	
    	if (dest.getBlock().getRelative(BlockFace.DOWN).isLiquid()){
			isLiquid = true;
		}
    	if (MaterialValues.IgnoredBlocks.contains(dest.getBlock().getType())){

			dest.setY(dest.getY()-1);
			if (dest.getBlock().getType() == Material.AIR || dest.getBlock().getType().isSolid()){
				dest.setY(dest.getY()+1);
			}
		
		}
    	
    	
    	
    	while (result == false) {	
	    		if (dest.getBlock().getLocation().getBlockY() > 256){
	    			return;
	    		}
	    		

	    		if (!sl.isSafeLocation((dest), plugin)){
	    			dest.add(0, 1, 0);
	        	}else{
	        		if (isLiquid){
		    			if (dest.getBlock().getRelative(BlockFace.DOWN).isLiquid()){
			    			dest.subtract(0, 1, 0);
			    		}
			    		else{
			    			result = true;
			    		}
		    		}else{

		    			result = true;
		    		}
	        	}
    	}
    	
    
    	
    	player.getWorld().playSound(player.getLocation(), Sound.ENTITY_ENDERMEN_TELEPORT, 1, 1);
    	player.getWorld().playEffect(player.getLocation(), Effect.ENDER_SIGNAL, 9);
    	//player.playEffect(player.getLocation(), Effect.ENDER_SIGNAL, 2);
    	player.teleport(dest);
    	
    	result = false;
    	isLiquid = false;
    	player.getWorld().playEffect(dest, Effect.ENDER_SIGNAL, 9);
    	player.getWorld().playSound(dest, Sound.ENTITY_ENDERMEN_TELEPORT, 1, 1);
    	//player.playEffect(dest, Effect.ENDER_SIGNAL, 2);
    	
    	
    	cooldowns.put(player.getName(), System.currentTimeMillis());
    	EntityPlayer p = ((CraftPlayer)event.getPlayer()).getHandle();
		p.playerConnection.sendPacket(new PacketPlayOutSetCooldown(p.inventory.getItemInHand().getItem(), 20 * cooldownTime));
		
		kb.kickBack(player, config);
	
			
		
		

    }
	
}
