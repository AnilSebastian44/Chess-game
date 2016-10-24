//imports


//imports
import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
//class definition
public class ChessApplication extends Application {
	
	private static Integer STARTTIME = 14;
	private static Integer STARTTIME1 = 59;
	private Timeline timeline, timeline1;
	private TextField textfield = new TextField();
	private TextField textfield1 = new TextField();
	private IntegerProperty timeMinutes = new SimpleIntegerProperty(STARTTIME);
	private Label label = new Label(":");
	private IntegerProperty timeSeconds = new SimpleIntegerProperty(STARTTIME1);
	
	// overridden init method
	@Override
	public void init() {
		// initialize the layout, create a CustomControl and it to the layout
		sp_mainlayout = new StackPane();
		cc_custom = new CustomControl();
		sp_mainlayout.getChildren().add(cc_custom);
		 //Here we create the button restart, and when this one is pressed, the method restartProgram is called.
		button2 = new Button("Restart Game");		
		button2.setId("button");
		button2.setPrefWidth(1000);
		button2.setOnAction(e -> restartGame());
	}
	// overridden start method
	@Override
	public void start(Stage primaryStage) {
		// set the title and scene, and show the stage
		window = primaryStage;
		primaryStage.setTitle("Chess CesarVelasco 2861816, Anil Sebastian 2852760");
		bp = new BorderPane();
		primaryStage.setScene(new Scene(bp, 550, 550));
		
		//This is to confirm that a player wants to exit the game.
		//Here we call the closeProgram method, if the player presses yes then the game will be closed,
		//if the player presses no then the action will be ignored and the player will be back in the game.
		//The consume method stops its further propagation.
		primaryStage.setOnCloseRequest(e -> {
			e.consume();
			closeGame();
			});
		
		// Bind the timerLabel text property to the timeSeconds property
		textfield.textProperty().bind(timeMinutes.asString());
		textfield1.textProperty().bind(timeSeconds.asString());
		textfield.setPrefWidth(100);
		textfield1.setPrefWidth(100);


		button = new Button();
		button.setId("button");
		button.setText("Start Game");
		button.setPrefWidth(1000);
		
		button.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				ChessBoard.getStart();
				if(timeline1 == null){
				timeMinutes.set(STARTTIME);
				timeline = new Timeline();

				timeline.getKeyFrames().addAll(new KeyFrame(Duration.minutes(STARTTIME*1.5), 
						new KeyValue(timeMinutes, 0)));
				timeline.playFromStart();
			}
				timeSeconds.set(STARTTIME1);
				timeline1 = new Timeline();
				timeline1.getKeyFrames().addAll(new KeyFrame(Duration.seconds(STARTTIME1*1.5), 
						new KeyValue(timeSeconds, 0)));
				timeline1.setCycleCount(15);
				timeline1.playFromStart();
			}
		});
        
		HBox statusBar = new HBox(20);
		statusBar.setAlignment(Pos.CENTER); // center the components within VBox
	    statusBar.setStyle("-fx-background-color: DARKGREY");
	    statusText = new Text("Player's Turn: ");
		statusText.setId("text");
	    status = new Text();
		status.setId("text");
	    check(current_player);
	    
	    statusBar.getChildren().addAll(statusText, status);
	    
	    VBox statusTop = new VBox();
	    
	 // TextField's to show how many pieces does each player have
	 		W_tf = new TextField();
	 		W_tf.setEditable(false);
	 		W_tf.setPrefWidth(255);
	 		

	 		B_tf = new TextField();
	 		B_tf.setEditable(false);
	 		B_tf.setPrefWidth(255);
	 		W_tf.setText("There are 16 black pieces");
			W_tf.setId("text");
	 		B_tf.setText("There are 16 white pieces");
			B_tf.setId("text");
	 		statusTop.getChildren().addAll(button, textfield, label, textfield1, button2, W_tf, B_tf);

		bp.setCenter(sp_mainlayout);
		bp.setBottom(statusBar);
		bp.setTop(statusTop);

		//LINK TO THE CSS FILE
		bp.getStylesheets().add("velasco_cesar_2861816_Project/chessStyle.css");
		primaryStage.minWidthProperty().bind(bp.heightProperty().multiply(1));
	      //primaryStage.setMinWidth(300);
		  //primaryStage.minHeightProperty().bind(scene.widthProperty().divide(1));
		  primaryStage.setMinHeight(300);
	    
		primaryStage.show();
	}
	 

	 public static void check(int player){
	    	current_player = player;
			status.setText("White");
    if(current_player == 1){
		status.setText("White");
	}
	else if(current_player == 2){
		status.setText("Black");
	}
	}
	 
	 public static void checkPiece(int player) {
			current_player = player;

			if (current_player == 1) {
				countWhite = countWhite - 1;
				W_tf.setText("There are " + countWhite + " black pieces left");
			} else if (current_player == 2) {
				countBlack = countBlack - 1;
				B_tf.setText("There are " + countBlack + " white pieces left");
			}
		}
		
	
	// overridden stop method
	@Override
	public void stop() {
	}
	
	// entry point into our program to launch our JavaFX application
	public static void main(String[] args) {
		launch(args);
	}
	
	//Method when a player wants to close the game.
		public void closeGame(){
		Boolean answer = ConfirmBox.display("Exit", "Are you sure you want to exit the game?");
		if(answer)
		window.close();
		}

		//Method when a player wants to restart a game.
		public void restartGame(){
		Boolean answer = ConfirmBox.display("Restart", "Are you sure you want to restart the game?");
		if(answer)
			cc_custom.resetGame();
		}
		
	// private fields for this class
	private StackPane sp_mainlayout;	//layout which allows items to be positioned on top of each other
	private CustomControl cc_custom;	//control which has a board and detects mouse and keyboard events
	private Button button, button2;
	private Stage window;
	private BorderPane  bp;
    private static Text statusText, status;
    private static int current_player;
    
	private static TextField W_tf; // TextField to show how many white pieces left in
	// the board
	private static TextField B_tf; // TextField to show how many black pieces left in
	// the board
	private static int countWhite = 16; // total number of white pieces
	private static int countBlack = 16; // total number of black pieces
    
}

