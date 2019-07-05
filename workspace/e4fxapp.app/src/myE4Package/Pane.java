package myE4Package;

import java.io.IOException;
import java.net.MalformedURLException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.geometry.Bounds;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.ObjectBinding;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import myE4Package.Pipe;
import javafx.scene.shape.Rectangle;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.ChangeListener;
import javafx.scene.Parent;
import javafx.geometry.Bounds;

public class Pane extends Application {

	class InternalPipe {
		Pipe p;
		Line head;
		Line tail;
	}
	
	HashMap <String, Line> anchorLines = new HashMap<String, Line>();
	
	int pipeArraySize = 100;
	final int arrayIncrement = 50;
	InternalPipe[] pipeArray = new InternalPipe[pipeArraySize];
	
	int pipeIndex = 0;
	
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
	
	final Scene scene = new Scene(root, 600, 600);
	primaryStage.setScene(scene);
	primaryStage.show();
	
	root.getChildren().forEach(child -> {
		if (child instanceof Pipe) {
			Pipe p = (Pipe) child;
			pipeArray[pipeIndex] = new InternalPipe();
			pipeArray[pipeIndex].p = p;
			
			String headId = "-pipe-" + p.getHeadColumn() + "-" + p.getHeadHPos() + "-" + p.getHeadRow() + "-" + p.getHeadVPos();
			Line head = anchorLines.get(headId);
			if (head == null) {
				head = new Line();
				head.setId(headId);
				GridPane.setColumnIndex(head, p.getHeadColumn());
				GridPane.setHalignment(head, p.getHeadHPos());
				GridPane.setRowIndex(head, p.getHeadRow());
				GridPane.setValignment(head, p.getHeadVPos());
				head.setStroke(Color.TRANSPARENT);
				anchorLines.put(headId, head);
			} 
			pipeArray[pipeIndex].head = head;

			String tailId = "-pipe-" + p.getTailColumn() + "-" + p.getTailHPos() + "-" + p.getTailRow() + "-" + p.getTailVPos();
			Line tail = anchorLines.get(tailId);
			if (tail == null) {
				tail = new Line();
				tail.setId(tailId );
				GridPane.setColumnIndex(tail, p.getTailColumn());
				GridPane.setHalignment(tail, p.getTailHPos());
				GridPane.setRowIndex(tail, p.getTailRow());
				GridPane.setValignment(tail, p.getTailVPos());
				tail.setStroke(Color.TRANSPARENT);
				anchorLines.put(tailId, tail);
 			}
			pipeArray[pipeIndex].tail = tail;
			
			//handle array resize
			pipeIndex++;
			if (pipeIndex == pipeArraySize) {
				InternalPipe[] tempPipeArray = new InternalPipe[pipeArraySize + arrayIncrement];
				System.arraycopy(pipeArray, 0, tempPipeArray, 0, pipeArraySize);
				pipeArray = tempPipeArray;
				pipeArraySize = pipeArray.length;
			}
	}
	});

	for (Map.Entry<String, Line> entry : anchorLines.entrySet()) {
		root.getChildren().add(entry.getValue());
	}
	
	// position line() in the bottom right corner for layout property changes
	Line bottomRight = new Line();
	GridPane.setColumnIndex(bottomRight, root.getColumnConstraints().size() - 1);
	GridPane.setHalignment(bottomRight, HPos.RIGHT);
	GridPane.setRowIndex(bottomRight, root.getRowConstraints().size() - 1);
	GridPane.setValignment(bottomRight, VPos.BOTTOM);
	bottomRight.setStroke(Color.TRANSPARENT);
	root.getChildren().add(bottomRight);

	bottomRight.layoutXProperty().addListener(c -> {
		processAnchors();
	});
	bottomRight.layoutYProperty().addListener(c -> {
		processAnchors();
	});
	
	
	}

	void processAnchors(){
		for (int i = 0; i < pipeIndex; i++) {
	        Bounds boundsInSceneTail = pipeArray[i].tail.localToScene(pipeArray[i].tail.getBoundsInLocal());
			Bounds boundsInSceneHead = pipeArray[i].head.localToScene(pipeArray[i].head.getBoundsInLocal());
			pipeArray[i].p.setWidth(boundsInSceneHead.getMinX() - boundsInSceneTail.getMinX());
		}
}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
