package myE4Package;

import java.io.IOException;
import java.net.MalformedURLException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.geometry.Bounds;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.ObjectBinding;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import myE4Package.Pipe;
import javafx.scene.shape.Rectangle;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.ChangeListener;


public class TryRegion extends Application {

	@Override
	public void start(Stage primaryStage) {
	GridPane root = null;
	try {
		root = (GridPane) FXMLLoader.load(getClass().getResource("test.fxml"));
	} catch (MalformedURLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	System.out.println("In TryRegion.");
	
	Region myRegion = new Region();
	myRegion.setMinWidth(100);
	myRegion.setMinHeight(50);
	Pipe myPipe = new Pipe();
	myPipe.setWidth(400);
	myPipe.setHeight(200);
	myPipe.setFill(Color.RED);
	myPipe.setPercentWidth(10);
	System.out.println("--- " + myPipe);
	Rectangle myRect = new Rectangle();
	myRect.setWidth(400);
	myRect.setHeight(200);
	myRect.setFill(Color.GREEN);
	myRegion.setShape(myRect);
	GridPane.setRowIndex(myRegion, 1);
	GridPane.setColumnIndex(myRegion, 1);
	System.out.println("width " + myRegion.getWidth());
	System.out.println("height " + myRegion.getHeight());
//	root.getChildren().add(myRegion);
	
	Scene scene = new Scene(root, 600, 600);
	primaryStage.setScene(scene);
	primaryStage.show();
	
	
	}
	public static void main(String[] args) {
		launch(args);
	}

}
