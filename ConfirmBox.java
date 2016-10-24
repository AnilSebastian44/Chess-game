

//Every time we want to restart the game, a confirm box window will come up.
//I have added this action in the restart button to ensure that a player really wants to restart the game or not. 
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.geometry.*;

//CLASS DEFINITION
	public class ConfirmBox{
		static boolean answer;
		
		public static boolean display(String title, String message){
			Stage window = new Stage();
			
			//I am using init modality to avoid any processes apart from pressing yes or not when the confirm window comes up.
			//In this case, we cannot do anything while we do not press yes or not in the confirm box window.
			window.initModality(Modality.APPLICATION_MODAL);
			window.setTitle(title);
			window.setMinWidth(280);
			window.setMinHeight(150);
			
			Label label = new Label();
			label.setText(message);
			
		//CREATE TWO BUTTONS
			//Here we create these two buttons for Yes or No in the confirm window.
			Button btn1 = new Button("YES");
			Button btn2 = new Button("NO");

			//If yes is pressed, then the confirm window will be closed.
			//And as the answer is true the game will be started.
			btn1.setOnAction(e -> {
				answer = true;
				window.close();
			});
			
			//If no is pressed, then the action will be ignored and the confirm window will be closed
			//and the player will be back in the game.
			btn2.setOnAction(e -> {
				answer = false;
				window.close();
			});

			//Here we use the vbox layout to put the buttons vertically.
			VBox vb = new VBox();
			vb.getChildren().addAll(label, btn1, btn2);
			vb.setAlignment(Pos.CENTER);
			vb.setSpacing(10.0);
			
			//Finally we use the show and wait method to show the stage and waits for it to close.
			Scene scene = new Scene(vb);
			window.setScene(scene);
			window.showAndWait();
			
			return answer;
		}
		
	}
