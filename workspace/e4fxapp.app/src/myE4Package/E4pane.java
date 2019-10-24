package myE4Package;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import myE4Package.Pane.InternalPipe;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;

public class E4pane {
	
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
	
	VBox vboxCol[];
	VBox vboxRow[];
	
	// only call processAnchors once at startup 
	boolean starting = true;

	
	@PostConstruct
	void initUI(BorderPane pane) {
		
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
		
		root.prefHeightProperty().bind(pane.heightProperty());
		root.prefWidthProperty().bind(pane.widthProperty());

		pane.widthProperty().addListener((obs, oldVal, newVal) -> {
			System.out.println("oldVal = " + oldVal + " newVal = " + newVal);
		});
		
		root.setGridLinesVisible(true);
		pane.setCenter(root);
		// create a VBox for each cell in the first row and first column
		int col = root.getColumnConstraints().size();
		int row = root.getRowConstraints().size();
		vboxCol = new VBox[col];
		vboxRow = new VBox[row];
		
		VBox vboxShared = new VBox();
		GridPane.setColumnIndex(vboxShared, 0);
		GridPane.setRowIndex(vboxShared, 0);
		root.getChildren().add(vboxShared);
		vboxShared.setStyle("-fx-background-color: transparent;");
		vboxShared.toBack();
		vboxCol[0] = vboxShared;
		vboxRow[0] = vboxShared;
		
		for (int i = 1; i < col; i++) {
			VBox vbox = new VBox();
			vbox.setStyle("-fx-background-color: transparent;");
			GridPane.setColumnIndex(vbox, i);
			root.getChildren().add(vbox);
			vbox.toBack();
			vboxCol[i] = vbox;
		}
		for (int i = 1; i < row; i++) {
			VBox vbox = new VBox();
			vbox.setStyle("-fx-background-color: transparent;");
			GridPane.setRowIndex(vbox, i);
			root.getChildren().add(vbox);
			vbox.toBack();
			vboxRow[i] = vbox;
		}

		// create all of the anchors
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
		else if (child instanceof FlowPane) {
			vboxRow[GridPane.getRowIndex(child)].setId("flow");
		}
		});
		
		for (Map.Entry<String, Line> entry : anchorLines.entrySet()) {
			root.getChildren().add(entry.getValue());
			entry.getValue().layoutXProperty().addListener(c -> {
				processAnchors();
			});
			entry.getValue().layoutYProperty().addListener(c -> {
				processAnchors();
			});
		}
		
//		for (Map.Entry<String, Line> entry : anchorLines.entrySet()) {
//			root.getChildren().add(entry.getValue());
//		}
//
//		root.layoutXProperty().addListener(c -> {
//			processAnchors();
//		});
//		root.layoutYProperty().addListener(c -> {
//			processAnchors();
//		});
		
		}

		// adjust pipes 
		void processAnchors(){
//			System.out.println("In processAnchors");
			
			for (int i = 0; i < pipeIndex; i++) {
				Pipe p = pipeArray[i].p;
				Line head = pipeArray[i].head;
				Line tail = pipeArray[i].tail;
				
		        Bounds sceneTail = tail.localToScene(tail.getBoundsInLocal());
				Bounds sceneHead = head.localToScene(head.getBoundsInLocal());
				// Tooltip only works for pipe1 and pipe5 - don't know why
				Tooltip t = new Tooltip(p.getId());
				Tooltip.install(p, t);
//				p.setLayoutX(sceneTail.getMinX());
//				p.setLayoutY(sceneTail.getMinY());
				Double y = sceneHead.getMinY() - sceneTail.getMinY();
				Double x = sceneHead.getMinX() - sceneTail.getMinX();
				Double angle = Math.atan2(y, x);
				p.setWidth(Math.sqrt(x*x + y*y));

				// Scene Builder doesn't explicitly set the Row and Column index that are set to 0, so...
				if (GridPane.getRowIndex(p) == null) GridPane.setRowIndex(p, 0);
				if (GridPane.getColumnIndex(p) == null) GridPane.setColumnIndex(p, 0);
				Double h = p.getPercentWidth()/100 * vboxRow[GridPane.getRowIndex(p)].getHeight();
				Double w = p.getPercentWidth()/100 * vboxCol[GridPane.getColumnIndex(p)].getWidth();
				p.setHeight(Math.abs(Math.cos(angle)*h) + Math.abs(Math.sin(angle)*w));
			}

	}

}
