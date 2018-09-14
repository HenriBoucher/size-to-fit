package myE4Package;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.shape.Rectangle;

public class Pipe extends Rectangle {
	
    // Define a variable to store the property
    private DoubleProperty percentWidth = new SimpleDoubleProperty();
 
    // Define a getter for the property's value
    public final double getPercentWidth(){return percentWidth.get();}
 
    // Define a setter for the property's value
    public final void setPercentWidth(double value){percentWidth.set(value);}
 
     // Define a getter for the property itself
    public DoubleProperty percentWidthProperty() {return percentWidth;}
 
	
	public Pipe() {
		super();
	}

}
