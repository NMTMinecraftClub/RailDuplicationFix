package com.m0pt0pmatt.railduplicationfix;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPistonEvent;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.event.block.BlockPistonRetractEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class RailDuplicationFixPlugin extends JavaPlugin implements Listener{

	public void onEnable(){
		Bukkit.getPluginManager().registerEvents(this, this);
	}
	
	@EventHandler
	public void onPistonExtend(BlockPistonExtendEvent event) {
		onPistonEvent(event);
	}
	
	@EventHandler
	public void onPistonExtend(BlockPistonRetractEvent event) {
		onPistonEvent(event);
	}
	
	
	public void onPistonEvent(BlockPistonEvent event){
		if (!event.isSticky()){
			return;
		}
		//if ()
		
		int x = 0;
		int y = 0;
		int z = 0;
		switch(event.getDirection()){
		case NORTH:
			z = -1;
			break;
		case SOUTH:
			z = 1;
			break;
		case EAST:
			x = 1;
			break;
		case WEST:
			x = -1;
			break;
		case UP:
			y = 1;
			break;
		case DOWN:
			y = -1;
			break;
		default:
			break;
		}
		
		if (event instanceof BlockPistonRetractEvent) {
			//because it's already extended, we need to look 2 in the right direction:wq
			
			x *= 2;
			y *= 2;
			z *= 2;
		}
		
		Location location = event.getBlock().getLocation();
		Block rail = event.getBlock().getWorld().getBlockAt(location.getBlockX() + x, location.getBlockY() + y, location.getBlockZ() + z);
		
		if (rail.getType().equals(Material.POWERED_RAIL) 
				|| rail.getType().equals(Material.ACTIVATOR_RAIL) 
				|| rail.getType().equals(Material.DETECTOR_RAIL)){
			event.setCancelled(true);
			
		}
		
	}
	
	
}
