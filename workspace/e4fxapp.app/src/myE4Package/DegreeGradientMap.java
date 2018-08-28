package myE4Package;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Singleton;

import org.eclipse.e4.core.di.annotations.Creatable;

import javafx.animation.Timeline;
import javafx.scene.paint.LinearGradient;

@Creatable
@Singleton
public class DegreeGradientMap {
	class GradientSet {
		LinearGradient gradient;
		Timeline forward;
		Timeline reverse;
	}
	
	private Map <Double, GradientSet> dg = new HashMap <Double, GradientSet>();
	
	DegreeGradientMap() {
		System.err.println("In DegreeGradientMap constructor.");
		System.err.println(this.toString());
	}
	
}
