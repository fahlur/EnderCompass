package Events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class CompassEvent extends Event implements Cancellable  {
	private static final HandlerList handlers = new HandlerList();
	private Player player;
	public boolean cancelled;
	
	public CompassEvent(Player player) {
		this.player = player;
		// TODO Auto-generated constructor stub
	}
	
	public Player getPlayer(){
		return player;
	}

	public HandlerList getHandlers() {
	    return handlers;
	}

	public static HandlerList getHandlerList() {
	    return handlers;
	}

	@Override
	public boolean isCancelled() {
		// TODO Auto-generated method stub
		return cancelled;
	}

	@Override
	public void setCancelled(boolean arg0) {
		// TODO Auto-generated method stub
		cancelled = arg0;
	}
	
	


}
