

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ChessBoard extends Pane {

	// constructor for the class
	public ChessBoard() {
		
		// initalize the board: background, data structures, initial layout of
		// pieces
		background = new Rectangle();
		background.setFill(Color.WHITE);
		this.getChildren().add(background);

		board = new int[8][8];
		pieces = new Piece[8][8];
		windows = new Window[8][8];

		for (int i = 0, z = 0; i < 8; i++, z++) {
			for (int j = 0; j < 8; j++) {
				board[i][j] = EMPTY;
				if (z % 2 == 0) {
					windows[i][j] = new Window(1);
				} else {
					windows[i][j] = new Window(2);
				}
				z++;
				getChildren().add(windows[i][j]);
			}
			current_player = PlayerWhite;
		}
		addPieces();
	}
	
	public void addPieces(){
		// TO ADD THE WHITE PIECES
				// Rooks
				board[0][0] = 2;
				pieces[0][0] = new PieceRook(1);
				getChildren().add(pieces[0][0].iv);
				board[7][0] = 2;
				pieces[7][0] = new PieceRook(1);
				getChildren().add(pieces[7][0].iv);

				// Knights
				board[1][0] = 3;
				pieces[1][0] = new PieceKnight(1);
				getChildren().add(pieces[1][0].iv);
				board[6][0] = 3;
				pieces[6][0] = new PieceKnight(1);
				getChildren().add(pieces[6][0].iv);

				// Bishops
				board[2][0] = 4;
				pieces[2][0] = new PieceBishop(1);
				getChildren().add(pieces[2][0].iv);
				board[5][0] = 4;
				pieces[5][0] = new PieceBishop(1);
				getChildren().add(pieces[5][0].iv);

				// Queen
				board[3][0] = 5;
				pieces[3][0] = new PieceQueen(1);
				getChildren().add(pieces[3][0].iv);

				// King
				board[4][0] = 6;
				pieces[4][0] = new PieceKing(1);
				getChildren().add(pieces[4][0].iv);

				// Pawn
				for (int i = 0; i < 8; i++) {
					board[i][1] = 1;
					pieces[i][1] = new PiecePawn(1);
					getChildren().add(pieces[i][1].iv);
				}
				
		// TO ADD THE BLACK PIECES
		// Rooks
		board[0][7] = -2;
		pieces[0][7] = new PieceRook(2);
		getChildren().add(pieces[0][7].iv);
		board[7][7] = -2;
		pieces[7][7] = new PieceRook(2);
		getChildren().add(pieces[7][7].iv);

		// Knights
		board[1][7] = -3;
		pieces[1][7] = new PieceKnight(2);
		getChildren().add(pieces[1][7].iv);
		board[6][7] = -3;
		pieces[6][7] = new PieceKnight(2);
		getChildren().add(pieces[6][7].iv);

		// Bishops
		board[2][7] = -4;
		pieces[2][7] = new PieceBishop(2);
		getChildren().add(pieces[2][7].iv);

		board[5][7] = -4;
		pieces[5][7] = new PieceBishop(2);
		getChildren().add(pieces[5][7].iv);

		// Queen
		board[3][7] = -5;
		pieces[3][7] = new PieceQueen(2);
		getChildren().add(pieces[3][7].iv);

		// King
		board[4][7] = -6;
		pieces[4][7] = new PieceKing(2);
		getChildren().add(pieces[4][7].iv);

		// Pawn
		for (int i = 0; i < 8; i++) {
			board[i][6] = -1;
			pieces[i][6] = new PiecePawn(2);
			getChildren().add(pieces[i][6].iv);
		}
	}


	// WE HAVE TO OVERRIDE RESIZING BEHAVIOUR TO MAKE OUT VIEW APPEAR PROPERLY
	@Override
	public void resize(double width, double height) {
		// CALL THE SUPERCLASS METHOD FIRST
		super.resize(width, height);

		// FIGUTR OUT THE WIDTH AND HEIGHT OF A CELL
		cell_width = width / 8.0;
		cell_height = height / 8.0;

		// RESIZE THE RECTANGLE TO TAKE THE FULL SIZE OF THE WIDGET
		background.setWidth(width);
		background.setHeight(height);

		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++) {
				windows[i][j].resize(cell_width, cell_height);
				windows[i][j].relocate(i * cell_width, j * cell_height);
			}

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (board[i][j] != 0) {
					pieces[i][j].iv.setFitWidth(cell_width);
					pieces[i][j].iv.setFitHeight(cell_height);
					pieces[i][j].iv.relocate(i * cell_width, j * cell_height);
				}
			}
		}
	}

	// PUBLIC METHOD FOR RESETTING THE GAME
	public void resetGame() {
		// TO RESET THE GAME
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
					if(board[i][j] != 0){
					board[i][j] = 0;
					getChildren().remove(pieces[i][j].iv);
					pieces[i][j] = null;
					}
			}
		}
		addPieces();
	}

	public boolean highlihtPiece = false;
	public int clickx;
	public int clicky;
	
	// select piece method
	// Sending the piece and its x and y coordinates on the board
	public void selectPiece(double x, double y) {
		int indexx = (int) (x / cell_width);
		int indexy = (int) (y / cell_height);

		boolean movedPiece = false;
		if (highlihtPiece) {	
			dehighlihtPiece();
			movedPiece = movePiece(indexx, indexy);
		}
		if (gameStarted && board[indexx][indexy] != EMPTY && ((board[indexx][indexy]) > 0 && current_player == PlayerWhite)){
		if (board[indexx][indexy] != 0 && !movedPiece) {			
			clickx = indexx;
			clicky = indexy;
			
			pieces[indexx][indexy].setCBoard(board);
			pieces[indexx][indexy].setXY(indexx, indexy);
			pieces[indexx][indexy].findFields();
			
			highlihtPiece();
		}
	
		else{
			clickx = -1;
			clicky = -1;
		}
		}
		
		if (board[indexx][indexy] != EMPTY && ((board[indexx][indexy]) < 0 && current_player == PlayerBlack)){
			if (board[indexx][indexy] != 0 && !movedPiece) {			
				clickx = indexx;
				clicky = indexy;
				
				pieces[indexx][indexy].setCBoard(board);
				pieces[indexx][indexy].setXY(indexx, indexy);
				pieces[indexx][indexy].findFields();
				
				highlihtPiece();
			}
		
			else{
				clickx = -1;
				clicky = -1;
			}
			}
	}

	public void highlihtPiece() {
		highlihtPiece = true;
		ArrayList<Map<String, Integer>> fields = pieces[clickx][clicky].possibleMoveTos;
		Iterator<Map<String, Integer>> fIterator = fields.iterator();
		while (fIterator.hasNext()) {
			Map<String, Integer> map = fIterator.next();
			windows[map.get("x")][map.get("y")].highlihtPiece();
		}	
	}

	public void dehighlihtPiece() {
		ArrayList<Map<String, Integer>> fields = pieces[clickx][clicky].possibleMoveTos;
		Iterator<Map<String, Integer>> fIterator = fields.iterator();
		while (fIterator.hasNext()) {
			Map<String, Integer> map = fIterator.next();
			windows[map.get("x")][map.get("y")].dehighlihtPiece();
		}
		highlihtPiece = false;
	}

	 public void checkPlayer(){
	        if(current_player==PlayerWhite){
	            current_player = PlayerBlack;
	            
	        }
	        else{
	            current_player = PlayerWhite;
	            
	        }
	        ChessApplication.check(current_player);
	    }
	 
	    public static void getStart(){
	        if(gameStarted)
	        	gameStarted=false;
	        else 
	        	gameStarted = true;
	    }
	
	// move piece method
		public boolean movePiece(int secondx, int secondy) {
			boolean movedPiece = false;

			if (clickx != -1 && ((board[clickx][clicky]) > 0 && current_player == PlayerWhite
					|| (board[clickx][clicky]) < 0 && current_player == PlayerBlack)) {
				// pieces[clickx][clicky].findFields();

				ArrayList<Map<String, Integer>> possibleMoveTos = pieces[clickx][clicky].possibleMoveTos;

				Iterator<Map<String, Integer>> fIterator = possibleMoveTos.iterator();
				while (!movedPiece && fIterator.hasNext()) {
					Map<String, Integer> map = fIterator.next();

					// if the clicked fields is one of the possible move to fields
					// move the piece to that field
					if (map.get("x") == secondx && map.get("y") == secondy) {
						movedPiece = true;
						System.out.println("Moved piece");

						if (board[secondx][secondy] != 0 && current_player==PlayerWhite) {
							// capture piece here
							// To remove the piece from the first clicked
							getChildren().remove(pieces[secondx][secondy].iv);
							ChessApplication.checkPiece(current_player);
						}
						else if(board[secondx][secondy] != 0 && current_player==PlayerBlack){
							
								// capture piece here
								// To remove the piece from the first clicked
								getChildren().remove(pieces[secondx][secondy].iv);
								ChessApplication.checkPiece(current_player);
							
						}
						board[secondx][secondy] = board[clickx][clicky];
						board[clickx][clicky] = EMPTY;

						// To put the piece in the new place
						pieces[secondx][secondy] = pieces[clickx][clicky];
						pieces[clickx][clicky] = null;
						resize(cell_width, cell_height);
						checkPlayer();
					}
				}
			}
			return movedPiece;
		}

		// private fields
		private Rectangle background;
		private int[][] board;
		private Piece[][] pieces;
		private Window[][] windows;
		private double cell_width, cell_height;
		private int current_player;
		private final int EMPTY = 0;
		private final int PlayerWhite = 1;
		private final int PlayerBlack = 2;
		private static boolean gameStarted = false;
	}