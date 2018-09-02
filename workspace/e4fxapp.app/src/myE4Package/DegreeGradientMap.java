package myE4Package;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PreDestroy;
import javax.inject.Singleton;

import org.eclipse.e4.core.di.annotations.Creatable;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;


@Creatable
@Singleton
public class DegreeGradientMap {
	public class GradientSet {
		Rectangle rect;
		Timeline stopped;
		Timeline forward;
		Timeline reverse;
	}
	
	public Map <Double, GradientSet> dgm = new HashMap <Double, GradientSet>();
	
	DegreeGradientMap() {
		System.err.println("In DegreeGradientMap constructor.");
		System.err.println(this.toString());
	}
	
	public GradientSet getGradientSet ( Double gradientSlopeDegree ) {
		
		GradientSet gradientSet;
		System.out.println("value of this " + this + " and this.dgm " + this.dgm);
		System.out.println("in getGradientSet " + this.toString());
		gradientSet = this.dgm.get(gradientSlopeDegree);
		System.out.println("after dg.get " + gradientSet.toString());
		
		if (gradientSet == null) {
			Rectangle rect = new Rectangle();
			Timeline timeline = new Timeline();
			
			double widthOfOneGradientCycle = 20.0;
			double xStartStatic = 100.0;
			double yStartStatic = 100.0;
			double xEndStatic = xStartStatic + (widthOfOneGradientCycle * Math.cos(Math.toRadians(gradientSlopeDegree)));
			double yEndStatic = yStartStatic + (widthOfOneGradientCycle * Math.sin(Math.toRadians(gradientSlopeDegree)));

			for (int i = 0; i < 10; i++) {
				int innerIterator = i;
				KeyFrame kf = new KeyFrame(Duration.millis(30 * innerIterator), new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent ae) {
						
						double runningRadius = innerIterator * (widthOfOneGradientCycle / 10);
						double xStartDynamic = xStartStatic + (runningRadius * Math.cos(Math.toRadians(gradientSlopeDegree)));
						double yStartDynamic = yStartStatic + (runningRadius * Math.sin(Math.toRadians(gradientSlopeDegree)));
						double xEndDynamic = xEndStatic + (runningRadius * Math.cos(Math.toRadians(gradientSlopeDegree)));
						double yEndDynamic = yEndStatic + (runningRadius * Math.sin(Math.toRadians(gradientSlopeDegree)));
						
						LinearGradient gradient = new LinearGradient(xStartDynamic, yStartDynamic, xEndDynamic, yEndDynamic, 
								false, CycleMethod.REPEAT, new Stop[] {
									new Stop(0.0, Color.WHITE),
									new Stop(0.5, Color.BLACK),
									new Stop(1.0, Color.WHITE)
						});
						rect.setFill(gradient);
					}
				});
				timeline.getKeyFrames().add(kf);
			}
			timeline.setCycleCount(Timeline.INDEFINITE);
			gradientSet.rect = rect;
			gradientSet.forward = timeline;
		}
	
		return gradientSet;
	}
	
	@PreDestroy
	public void cleanup()
	{
		System.err.println("In PreDestroy");
	}
	
}
