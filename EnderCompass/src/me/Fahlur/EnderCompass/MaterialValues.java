package me.Fahlur.EnderCompass;

import org.bukkit.Material;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MaterialValues {

	/*
	 * Ignored Blocks Set Values
	 */
	public static final Material[] SET_VALUES = new Material[] {
	    	Material.AIR, 
	    	Material.STATIONARY_LAVA, 
	    	Material.LAVA, 
	    	Material.STATIONARY_WATER, 
	    	Material.WATER, 
	    	Material.FIRE,
	    	Material.SIGN_POST, 
	    	Material.WALL_SIGN, 
	    	Material.STONE_BUTTON, 
	    	Material.WOOD_BUTTON, 
	    	Material.LEVER, 
	    	Material.GOLD_PLATE, 
	    	Material.IRON_PLATE, 
	    	Material.STONE_PLATE, 
	    	Material.WOOD_PLATE,
	    	Material.RAILS, 
	    	Material.ACTIVATOR_RAIL, 
	    	Material.POWERED_RAIL, 
	    	Material.DETECTOR_RAIL,
	    	Material.DEAD_BUSH, 
	    	Material.LONG_GRASS, 
	    	Material.YELLOW_FLOWER, 
	    	Material.BROWN_MUSHROOM,
	    	Material.RED_MUSHROOM, 
	    	Material.RED_ROSE,
	    	Material.SUGAR_CANE,
	    	Material.SUGAR_CANE_BLOCK, 
	    	Material.TORCH, 
	    	Material.WHEAT, 
	    	Material.SKULL,
	    	Material.SNOW, 
	    	Material.CARPET, 
	    	Material.TRIPWIRE, 
	    	Material.REDSTONE_WIRE,
	    	Material.REDSTONE_TORCH_OFF,
	    	Material.REDSTONE_TORCH_ON, 
	    	Material.REDSTONE_COMPARATOR_OFF, 
	    	Material.REDSTONE_COMPARATOR_ON, 
	    	Material.DIODE_BLOCK_OFF, 
	    	Material.DIODE_BLOCK_ON,
	    	Material.SAPLING, 
	    	Material.VINE, 
	    	Material.LADDER, 
	    	Material.DOUBLE_PLANT
	    };
	
	/*
	 * Set Values as Array List
	 */
	public static final Set<Material> IgnoredBlocks = new HashSet<Material>(Arrays.asList(SET_VALUES));
	

	/*
	 * Put on Top Set Values
	 */
	
	public static final Material[] PUT_ON_TOP = new Material[] {
    		Material.FENCE, 
    		Material.ACACIA_FENCE, 
    		Material.BIRCH_FENCE, 
    		Material.DARK_OAK_FENCE,
    		Material.JUNGLE_FENCE, 
    		Material.NETHER_FENCE, 
    		Material.SPRUCE_FENCE,
    		Material.FENCE_GATE, 
    		Material.ACACIA_FENCE_GATE, 
    		Material.BIRCH_FENCE_GATE, 
    		Material.DARK_OAK_FENCE_GATE,
    		Material.JUNGLE_FENCE_GATE, 
    		Material.SPRUCE_FENCE_GATE,
    		Material.COBBLE_WALL
    };
	
	
	
	
}
