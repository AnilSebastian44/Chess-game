

import javafx.event.EventHandler;
import javafx.scene.control.Control;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;


public class CustomControl extends Control {
	

	public CustomControl() {
		// set a default skin, generate a board and add the board to the control
	//similar to previous custom controls but must handle more
	setSkin(new CustomControlSkin(this));
	chessBoardGame = new ChessBoard();
	getChildren().add(chessBoardGame);
	
	//complex mouse interactions and key interactions
	
	// mouse clicked event handler that will try to place a piece on the board
			setOnMouseClicked(new EventHandler<MouseEvent>(){
				//OVERRIDEN HANDLE METHOD
				@Override
				public void handle(MouseEvent event){
					chessBoardGame.selectPiece(event.getX(), event.getY());
				}
			});
			
			
			//TO ADD IN A KEY LISTENER FOR RESETTING THE GAME
			//this key event will reset the game
			setOnKeyPressed(new EventHandler<KeyEvent>(){
				//OVERRIDEN HANDLE METHOD
				@Override
				public void handle(KeyEvent event){
					if(event.getCode() == KeyCode.SPACE)
						chessBoardGame.resetGame();
				}
			});
}
	
	//override the resize method
	@Override
	public void resize(double width, double height) {
		// call the super class method and update the size of the board
		super.resize(width, height);
		chessBoardGame.resize(width, height);
	}

	public void resetGame() {
		chessBoardGame.resetGame();		
	}
	
	private ChessBoard chessBoardGame;

	}
