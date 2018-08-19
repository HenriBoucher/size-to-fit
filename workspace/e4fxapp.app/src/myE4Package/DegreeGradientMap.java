package myE4Package;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.Adapters;
import org.eclipse.core.runtime.PlatformObject;

import javafx.animation.Timeline;
import javafx.scene.paint.LinearGradient;

public class DegreeGradientMap extends PlatformObject {
	class GradientSet {
		LinearGradient gradient;
		Timeline forward;
		Timeline reverse;
	}
	
	private Map <Double, GradientSet> dg = new HashMap <Double, GradientSet>();
	
	DegreeGradientMap() {
		dg = Adapters.adapt(dg, dg.getClass(), true);
	}
	
}
