
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

class Window extends Group {

	// constructor for the class
	public Window(int type) {

		this.type = type;

		if (type == 1) {

			e = new Rectangle();
			e.setFill(Color.CADETBLUE);
			e.setStroke(Color.BLACK);

			e.setHeight(300);
			e.setWidth(300);

			getChildren().add(e);
		}

		else if (type == 2) {

			e = new Rectangle();
			e.setFill(Color.DARKGREY);
			e.setStroke(Color.BLACK);

			e.setHeight(300);
			e.setWidth(300);

			getChildren().add(e);
		}

		e.setOnMouseEntered(new EventHandler<MouseEvent>() {
			// OVERRIDEN HANDLE METHOD
			@Override
			public void handle(MouseEvent event) {
				e.setStyle("-fx-effect: innershadow(gaussian, RED, 5, 1.0, 0, 0);");
			}
		});

		e.setOnMouseExited(new EventHandler<MouseEvent>() {
			// OVERRIDEN HANDLE METHOD
			@Override
			public void handle(MouseEvent event) {
				e.setStyle("-fx-effect: none;");
			}
		});
	}

	public void highlihtPiece() {
		if (type == 1) {
			//e.setFill(Color.rgb(2,2,2,0.2));
			e.setFill(Color.AQUA);
			e.setStroke(Color.BLACK);
		}
		if (type == 2) {
			e.setFill(Color.AQUAMARINE);
			//e.setFill(Color.rgb(0,0,0,0.5));
			e.setStroke(Color.BLACK);
		}
	}

	public void dehighlihtPiece() {
		if (type == 1) {
			e.setFill(Color.CADETBLUE);
			e.setStroke(Color.BLACK);
		}
		if (type == 2) {
			e.setFill(Color.DARKGREY);
			e.setStroke(Color.BLACK);
		}
	}

	// overridden version of the resize method
	@Override
	public void resize(double width, double height) {
		// call the super class method and update the center and radius of the
		// ellipse representing the window
		super.resize(width, height);
		if (type == 1 || type == 2) {
			e.setHeight(height);
			e.setWidth(width);
		}
	}

	// overridden version of the relocate method
	@Override
	public void relocate(double x, double y) {
		// call the superclass method and update the relevant transform
		super.relocate(x, y);
		// pos.setX(x); pos.setY(y);

	}

	// private fields of the class
	private Rectangle e; // rectangle for rendering this window
	private int type;
}

/*
 * e.setOnMouseEntered(new EventHandler<MouseEvent>(){ //OVERRIDEN HANDLE METHOD
 * 
 * @Override public void handle(MouseEvent event){ e.setFill(Color.RED);
 * //e.setStyle("-fx-effect: innershadow(gaussian, RED, 2, 1.0, 0, 0);"); } });
 * 
 * e.setOnMouseExited(new EventHandler<MouseEvent> (){ //OVERRIDEN HANDLE METHOD
 * 
 * @Override public void handle(MouseEvent event){ if(type == 1){
 * e.setFill(Color.CADETBLUE); } else{ e.setFill(Color.DARKGREY); }
 * //e.setStyle("-fx-effect: innershadow(gaussian, RED, 2, 1.0, 0, 0);"); } });
 */
