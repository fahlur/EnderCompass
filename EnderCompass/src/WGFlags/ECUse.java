package WGFlags;

import com.sk89q.worldguard.session.Session;
import com.sk89q.worldguard.session.handler.Handler;
import org.bukkit.event.Listener;

public class ECUse extends Handler implements Listener {


	  public static final Factory FACTORY = new Factory();
	  
	  public static class Factory
	    extends Handler.Factory<ECUse>
	  {
		@Override
	    public ECUse create(Session session)
	    {
	      return new ECUse(session);
	    }
	  }
	  
	  protected ECUse(Session session)
	  {
	    super(session);
	  }

}
