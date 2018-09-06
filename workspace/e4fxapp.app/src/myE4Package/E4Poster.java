package myE4Package;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.core.services.events.IEventBroker;

import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Rectangle;
import myE4Package.DegreeGradientMap.GradientSet;

public class E4Poster {
 	@Inject
	private IEventBroker eventBroker;
 	
 	@Inject
 	private DegreeGradientMap dgm;
	
	@PostConstruct
	void initUI(BorderPane pane) {
/* 		for (int i=1; i<10; i++) {
 			eventBroker.post("MYTOPIC", "my data" + i);
 			try {
				Thread.sleep(1000);
				//System.out.println("loop count = " + i);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
 		}
*/
		
	/**	Map<String, String> props = part.getProperties();
		System.out.println(props.get("title"));
		System.out.println(props.get("namespace prefix"));
		System.out.println("y-axis");
		List<String> vars = part.getVariables();
		for (String temp : vars) {
			System.out.println(temp);
			Pressure pres = new Pressure();
			pres.description = "Analog description";
			pres.pressure = 34.56;
			context.set(temp, pres);
		}
		Pressure pres2 = new Pressure();
		pres2.description = "junk desc";
		pres2.pressure = 12.34;
		context.set("junk", pres2);
		Pressure retrieve = (Pressure) context.get("junk");
		System.out.println(retrieve.description + " " + retrieve.pressure);
		*/
 		Button b = new Button("In Poster");
//		pane.setCenter(b);
		
		GradientSet dgmInstance = dgm.gradientSet;
		
		dgmInstance = dgm.getGradientSet(180.0);
		
		Rectangle r1 = new Rectangle();
		Timeline t1 = new Timeline();
		t1 = dgmInstance.timeline;
		r1 = dgmInstance.rect;
		
/*		dgmInstance = dgm.getGradientSet(180.0);
		
		Rectangle r2 = new Rectangle();
		Timeline t2 = new Timeline();
		t2 = dgmInstance.timeline;
		r2 = dgmInstance.rect;
		
		dgmInstance = dgm.getGradientSet(180.0);
		
		Rectangle r3 = new Rectangle();
		Timeline t3 = new Timeline();
		t3 = dgmInstance.timeline;
		r3 = dgmInstance.rect;
		
		
		pane.getChildren().addAll(r1, r2, r3);
		*/
		
		pane.setCenter(r1);
		
//		t1.setRate(-t1.getRate());
//		dgmInstance.timeline.setRate(-dgmInstance.timeline.getRate());
//		dgmInstance.timeline.pause();
//		dgmInstance.forward.pause();
		dgmInstance = dgm.getGradientSet(10.0);
		dgmInstance = dgm.getGradientSet(90.0);
		dgmInstance = dgm.getGradientSet(10.0);
		System.err.println(dgmInstance.toString());
	}

}
