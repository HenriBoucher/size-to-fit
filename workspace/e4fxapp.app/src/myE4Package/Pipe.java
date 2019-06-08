package myE4Package;

import javax.annotation.PostConstruct;
import java.lang.Override;
import javax.inject.Inject;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.TextField;

public class Pipe extends Rectangle {
	
	@Inject
	Scene scene;
	
    private DoubleProperty percentWidth = new SimpleDoubleProperty();
    public final double getPercentWidth(){return percentWidth.get();}
    public final void setPercentWidth(double value){percentWidth.set(value);}
    public DoubleProperty percentWidthProperty() {return percentWidth;}
    
    private ObjectProperty<VPos> headVPos = new SimpleObjectProperty<VPos>();
    public final VPos getHeadVPos() {return headVPos.getValue();}
    public final void setHeadVPos(VPos value) {headVPos.setValue(value);}
    public ObjectProperty<VPos> headVPosProperty() {return headVPos;}

    private ObjectProperty<HPos> headHPos = new SimpleObjectProperty<HPos>();
    public final HPos getHeadHPos() {return headHPos.getValue();}
    public final void setHeadHPos(HPos value) {headHPos.setValue(value);}
    public ObjectProperty<HPos> headHPosProperty() {return headHPos;}
    
    private ObjectProperty<VPos> tailVPos = new SimpleObjectProperty<VPos>();
    public final VPos getTailVPos() {return tailVPos.getValue();}
    public final void setTailVPos(VPos value) {tailVPos.setValue(value);}
    public ObjectProperty<VPos> tailVPosProperty() {return tailVPos;}

    private ObjectProperty<HPos> tailHPos = new SimpleObjectProperty<HPos>();
    public final HPos getTailHPos() {return tailHPos.getValue();}
    public final void setTailHPos(HPos value) {tailHPos.setValue(value);}
    public ObjectProperty<HPos> tailHPosProperty() {return tailHPos;}
    
    private IntegerProperty headRow = new SimpleIntegerProperty();
    public final int getHeadRow() {return headRow.get();}
    public final void setHeadRow(int value) {headRow.set(value);}
    public IntegerProperty headRowProperty() {return headRow;}
    
    private IntegerProperty headColumn = new SimpleIntegerProperty();
    public final int getHeadColumn() {return headColumn.get();}
    public final void setHeadColumn(int value) {headColumn.set(value);}
    public IntegerProperty headColumnProperty() {return headColumn;}
    
    private IntegerProperty tailRow = new SimpleIntegerProperty();
    public final int getTailRow() {return tailRow.get();}
    public final void setTailRow(int value) {tailRow.set(value);}
    public IntegerProperty tailRowProperty() {return tailRow;}
    
    private IntegerProperty tailColumn = new SimpleIntegerProperty();
    public final int getTailColumn() {return tailColumn.get();}
    public final void setTailColumn(int value) {tailColumn.set(value);}
    public IntegerProperty tailColumnProperty() {return headRow;}
    
	public Pipe() {	
		super();
		
	}
}
