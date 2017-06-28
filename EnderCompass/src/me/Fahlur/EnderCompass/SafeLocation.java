package me.Fahlur.EnderCompass;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

public class SafeLocation {

	EnderCompassPlugin plugin;
	
	 public boolean isSafeLocation(Location location, EnderCompassPlugin p) {
	        Block feet = location.getBlock();
	        Block ground = feet.getRelative(BlockFace.DOWN);
	        Block head = feet.getRelative(BlockFace.UP);
	        this.plugin = p;
	        
	        /* 
	         * Is Nether 
	         */
	        if ((ground.getType().equals(Material.BEDROCK) || ground.getRelative(BlockFace.DOWN).getType().equals(Material.BEDROCK)) && ground.getBiome().equals(Biome.HELL)){
	        	return false;
	        }
	        
	        /*
	         * Is Liquid
	         */
	        if ((ground.isLiquid() || feet.isLiquid() || feet.getRelative(BlockFace.UP).isLiquid())) {
	        	if (feet.getType().equals(Material.LAVA) || feet.getType().equals(Material.STATIONARY_LAVA)){
	        		return false;
	        	}
	        	if (ground.getType().equals(Material.LAVA) || ground.getType().equals(Material.STATIONARY_LAVA)){
	        		return false;
	        	}
	        	if (head.getType().equals(Material.LAVA) || head.getType().equals(Material.STATIONARY_LAVA)){
	        		return false;
	        	}
	        	return true;
	        }
	        
	        /*
	         * is Ground Solid
	         */
	        if (!ground.getType().isSolid()) {
	            return false; // not solid
	        }

	        
	        /*
	         * is Feet Transparent
	         */
	        if (!(feet.getType().isTransparent()) && !(feet.getType() != Material.AIR)) {
	            return false; // not transparent (will suffocate)
	        }
	        
	        /*
	         * is Head Transparent
	         */
	        if (!head.getType().isTransparent()) {
	            return false; // not transparent (will suffocate)
	        }
  
	        
	        return true;
	        
	    }
	    
}
