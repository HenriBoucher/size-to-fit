package myE4Package;
import java.io.IOException;
import java.net.MalformedURLException;

/*
 * You must adjust the width of the window for the rectangle to appear.
 * After the rectangle is drawn note that adjusting the window width adjusts
 * width of the rectangle.  I've substracted 50 pixels from it's width so that
 * you can distinguish between a correctly size rectangle versus one that
 * extends beyond the right edge of the window.
 * 
 * Although newX (see the commented out line) is correct when the window is
 * maximized the call to getBoundsInLocal() is updated too late.  The rectangle
 * remains at its prior size.  Then when you minimize the rectangle it extends 
 * beyond the right edge of the window.  I don't know why but it takes several
 * width adjustments for the rectangle to be drawen properly.
 * 
 * Of course the same problem exits in the vertical direction (heightProperty())
 */
import javafx.application.Application;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.geometry.Pos;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.geometry.Bounds;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.beans.value.ChangeListener;

public class MyTest extends Application {
	
	Line head = new Line();
	Line tail = new Line();
	Rectangle pipe = new Rectangle();
		
	@Override
	public void start(Stage primaryStage) {
		
		primaryStage.setTitle("MyTest");
//		GridPane root = new GridPane();
		GridPane root = null;
		try {
			root = (GridPane) FXMLLoader.load(getClass().getResource("gridpane.fxml"));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		root.setAlignment(Pos.CENTER);


//		ColumnConstraints column1 = new ColumnConstraints();
//		column1.setHgrow(Priority.SOMETIMES);
//		root.getColumnConstraints().add(column1);
//		
//		RowConstraints row1 = new RowConstraints();
//		row1.setMinHeight(10.0);
//		row1.setPrefHeight(30.0);
//		row1.setVgrow(Priority.SOMETIMES);
//		root.getRowConstraints().add(row1);
		
		System.out.println("col = " + root.getRowConstraints());
		System.out.println("row = " + root.getColumnConstraints());
		
		GridPane.setHalignment(head, HPos.RIGHT);
		GridPane.setValignment(head, VPos.CENTER);
		GridPane.setRowIndex(head, 0);
		GridPane.setColumnIndex(head, 0);
		head.setStrokeWidth(5);
		head.setStroke(Color.RED);

		GridPane.setHalignment(tail, HPos.LEFT);
		GridPane.setValignment(tail, VPos.CENTER);
		GridPane.setRowIndex(tail, 0);
		GridPane.setColumnIndex(tail, 0);
		tail.setStrokeWidth(5);
		tail.setStroke(Color.BLUE);
		
		GridPane.setHalignment(pipe, HPos.LEFT);
		GridPane.setValignment(pipe, VPos.CENTER);
		pipe.setWidth(200);
		pipe.setHeight(50);
		
		root.getChildren().addAll(head, tail, pipe);
		
		root.widthProperty().addListener(new ChangeListener<Number>() {
			@Override public void changed(ObservableValue<? extends Number> 
					observableValue, Number oldX, Number newX) {
				System.out.println("newX = " + newX);
				processAnchors();
			}
		});
	
		primaryStage.setScene(new Scene(root, 300, 250));
		primaryStage.show();
	}
	
	void processAnchors() {
		Bounds bHead = head.localToScene(head.getBoundsInLocal());
		Bounds bTail = tail.localToScene(tail.getBoundsInLocal());
		System.out.println("bHEad = " + bHead + " bTail = " + bTail);
		pipe.setWidth(bHead.getMinX() - bTail.getMinX() - 50);

	}
	public static void main(String []args) {
		launch(args);
	}

}


