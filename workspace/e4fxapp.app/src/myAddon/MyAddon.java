package myAddon;

import javax.inject.Inject;

import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.ui.model.application.MAddon;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.osgi.service.event.Event;



public class MyAddon {
	
	private final EModelService modelService;
	
	@Inject
	MyAddon(IEventBroker eventBroker, EModelService modelService, MAddon addon){
//		Map<String, String> persist = addon.getPersistedState();
		
		this.modelService = modelService;
//		System.out.println(this.modelService.toString());  // do this to get rid of warning

		eventBroker.subscribe("TEST", this::handleWidget);

		final class MyAddonThread extends Thread{
			public void run() {
				int i = 0;
				while (true)
				{
					eventBroker.post("DATA", i++);
					try {
						Thread.sleep(10000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		(new MyAddonThread()).start();
		}

 		
	void handleWidget(Event event){
		System.out.println(event);
	}
 
}
