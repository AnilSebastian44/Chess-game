
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

//class declaration - abstract because we will not want to create a Piece object but we would
//like to specify the private fields that all pieces should have in addition to their behaviours
public abstract class Piece extends Group {

	// piece can be either white (1) or black (2)
	private int type;
	// image used to display the piece
	protected Image image;
	protected ImageView iv;

	protected int xOnBoard;
	protected int yOnBoard;

	// Giving the piece its x and y coordinates on the board
	public void setXY(int x, int y) {
		xOnBoard = x;
		yOnBoard = y;
	}

	protected int[][] cBoard;

	// To put the board into the pieces
	public void setCBoard(int[][] board) {
		cBoard = board;
	}

	protected ArrayList<Map<String, Integer>> possibleMoveTos = new ArrayList<Map<String, Integer>>();

	public Piece(int type) {
		this.type = type;
	}

	protected abstract void findFields();

	// move method
    public void checkVerticalAndHorizontal(boolean isKing) {

        String whatsOnField = "empty";

        // TO check the vertical fields above clicked field
        int i = yOnBoard - 1;
        while (i >= 0 && (!isKing || (isKing && i > yOnBoard - 2)) && whatsOnField.equals("empty")) {

            System.out.println("up");
            whatsOnField = checkField(xOnBoard, i);
            saveField(whatsOnField, xOnBoard, i);

            i--;
        }

        // TO check the vertical fields beneath clicked field
        i = yOnBoard + 1;
        whatsOnField = "empty";
        while (i <= 7 && (!isKing || (isKing && i < yOnBoard + 2)) && whatsOnField.equals("empty")) {

            System.out.println("down");
            whatsOnField = checkField(xOnBoard, i);
            saveField(whatsOnField, xOnBoard, i);

            i++;
        }

        // TO check the horizontal fields to left of clicked field
        i = xOnBoard - 1;
        whatsOnField = "empty";
        while (i >= 0 && (!isKing || (isKing && i > xOnBoard - 2)) && whatsOnField.equals("empty")) {

            System.out.println("left");
            whatsOnField = checkField(i, yOnBoard);
            saveField(whatsOnField, i, yOnBoard);

            i--;
        }

        // TO check the horizontal fields to right of clicked field
        i = xOnBoard + 1;
        whatsOnField = "empty";
        while (i <= 7 && (!isKing || (isKing && i < xOnBoard + 2)) && whatsOnField.equals("empty")) {

            System.out.println("right");
            whatsOnField = checkField(i, yOnBoard);
            saveField(whatsOnField, i, yOnBoard);

            i++;
        }
    }
    
    public void checkDiagonal(boolean isKing) {
        String whatsOnField = "empty";

        // TO check the diagonal fields top left of clicked field
        int x = xOnBoard - 1;
        int y = yOnBoard - 1;
        while (x >= 0 && y >= 0 && (!isKing || (isKing && y > yOnBoard - 2)) && whatsOnField.equals("empty")) {

            System.out.println("top left");
            whatsOnField = checkField(x, y);
            saveField(whatsOnField, x, y);

            x--;
            y--;
        }

        // TO check the diagonal fields top right of clicked field
        x = xOnBoard + 1;
        y = yOnBoard - 1;
        whatsOnField = "empty";
        while (x <= 7 && y >= 0 && (!isKing || (isKing && x < xOnBoard + 2)) && whatsOnField.equals("empty")) {

            System.out.println("top right");
            whatsOnField = checkField(x, y);
            saveField(whatsOnField, x, y);

            x++;
            y--;
        }

        // TO check the diagonal fields to bottom left of clicked field
        x = xOnBoard - 1;
        y = yOnBoard + 1;
        whatsOnField = "empty";
        while (x >= 0 && y <= 7 && (!isKing || (isKing && x > xOnBoard - 2)) && whatsOnField.equals("empty")) {

            System.out.println(" bottom left");
            whatsOnField = checkField(x, y);
            saveField(whatsOnField, x, y);

            x--;
            y++;
        }

        // TO check the diagonal fields to bottom right of clicked field
        x = xOnBoard + 1;
        y = yOnBoard + 1;
        whatsOnField = "empty";
        
        if(isKing){
            System.out.print((isKing && x < xOnBoard + 2));
        }
        
        while (x <= 7 && y <= 7 && (!isKing || (isKing && x < xOnBoard + 2)) && whatsOnField.equals("empty")) {

            System.out.println(" bottom right");
            whatsOnField = checkField(x, y);
            saveField(whatsOnField, x, y);

            x++;
            y++;
        }

        /*
         * // TO check the horizontal fields to right of clicked field i =
         * xOnBoard + 1; whatsOnField = "empty"; while (i <= 7 && (!isKing ||
         * (isKing && i < xOnBoard + 2)) && whatsOnField.equals("empty")) {
         * 
         * System.out.println("right"); whatsOnField = checkField(i, yOnBoard);
         * saveField(whatsOnField, i, yOnBoard);
         * 
         * i++; }
         */
    }

	public void checkPawn() {
		String whatsOnField = "";

		// To check the possible movements down for all the white pawns.
		if (type == 1) {
			int x = xOnBoard;
			int y = yOnBoard + 1;

			if (yOnBoard == 1) {
				while (y >= 0 && y < yOnBoard + 3 && checkField(x, y).equals("empty")) {

					System.out.println("OneDown or TwoDown");
					whatsOnField = checkField(xOnBoard, y);
					saveField(whatsOnField, xOnBoard, y);

					y++;
				}
			}

			else {
				while (y >= 0 && y < yOnBoard + 2 && checkField(x, y).equals("empty")) {

					System.out.println("One down");
					whatsOnField = checkField(xOnBoard, y);
					saveField(whatsOnField, xOnBoard, y);

					y++;
				}
			}
		}

		// To check the possible movements up for all the black pawns.
		else if (type == 2) {
			int x1 = xOnBoard;
			int y1 = yOnBoard - 1;

			// move two places up
			if (yOnBoard == 6) {
				while (y1 >= 0 && y1 > yOnBoard - 3 && checkField(x1, y1).equals("empty")) {

					System.out.println("OneUp or TwoUp");
					whatsOnField = checkField(xOnBoard, y1);
					saveField(whatsOnField, xOnBoard, y1);

					y1--;
				}
			}

			// move only one place up
			else {
				while (y1 >= 0 && y1 > yOnBoard - 2 && checkField(x1, y1).equals("empty")) {

					System.out.println("OneUp");
					whatsOnField = checkField(xOnBoard, y1);
					saveField(whatsOnField, xOnBoard, y1);

					y1--;
				}
			}
		}
	}

	public void checkPawnDiagonal() {

		String whatsOnField = "";
		// TO check the diagonal fields to bottom left of clicked field
		int x = xOnBoard - 1; // to move it one space to the left
		int y = yOnBoard + 1; // to move it one space down

		// TO check the diagonal fields to bottom right of clicked field
		int x1 = xOnBoard + 1; // to move it one space to the right
		int y1 = yOnBoard + 1; // to move it one space down
		// whatsOnField = "opponentsPiece";
		if (type == 1) {
			if (x >= 0 && x <= 7 && y >= 0 && y <= 7 && checkField(x, y).equals("opponentsPiece")) {

				System.out.println(" bottom left");
				whatsOnField = checkField(x, y);
				saveField(whatsOnField, x, y);

				x--;
				y++;
			}

			if (x1 >= 0 && x1 <= 7 && y1 >= 0 && y1 <= 7 && checkField(x1, y1).equals("opponentsPiece")) {

				System.out.println(" bottom right");
				whatsOnField = checkField(x1, y1);
				saveField(whatsOnField, x1, y1);

				x++;
				y++;
			}
		}

		else if (type == 2) {

			// To check the diagonal fields top left of clicked field
			int x2 = xOnBoard - 1; // to move it one space to the left
			int y2 = yOnBoard - 1; // to move it one space up

			// TO check the diagonal fields top right of clicked field
			int x3 = xOnBoard + 1; // to move it one space to the right
			int y3 = yOnBoard - 1; // to move it one space up

			if (x2 >= 0 && x2 <= 7 && y2 >= 0 && y2 <= 7 && checkField(x2, y2).equals("opponentsPiece")) {

				System.out.println("top left");
				whatsOnField = checkField(x2, y2);
				saveField(whatsOnField, x2, y2);

				x--;
				y--;
			}

			if (x3 >= 0 && x3 <= 7 && y3 >= 0 && y3 <= 7 && checkField(x3, y3).equals("opponentsPiece")) {

				System.out.println("top right");
				whatsOnField = checkField(x3, y3);
				saveField(whatsOnField, x3, y3);

				x++;
				y--;
			}
		}
	}

	public void checkKnight() {
		String whatsOnField = "";

		///////////// ========type 1 && type 2========///////////////
		int y = yOnBoard - 2; // to move it two spaces up
		int x = xOnBoard + 1; // to move it one space to the right

		int y1 = yOnBoard - 2; // to move it two spaces up
		int x1 = xOnBoard - 1; // to move it one space to the left

		int y2 = yOnBoard + 2; // to move it two spaces down
		int x2 = xOnBoard - 1; // to move it one space to the left

		int y3 = yOnBoard + 2; // to move it two spaces down
		int x3 = xOnBoard + 1; // to move it one space to the right

		int y4 = yOnBoard - 1; // to move it one space up
		int x4 = xOnBoard - 2; // to move it space to the left

		int y5 = yOnBoard - 1; // to move it one space up
		int x5 = xOnBoard + 2; // to move it two spaces to the right

		int y6 = yOnBoard + 1; // to move it one down
		int x6 = xOnBoard + 2; // to move it two spaces to the right

		int y7 = yOnBoard + 1; // to move it one down
		int x7 = xOnBoard - 2; // to move it two spaces to the right

		if (type == 1 || type == 2) {

			if (x >= 0 && x <= 7 && y >= 0 && y <= 7
					&& (checkField(x, y).equals("empty") || checkField(x, y).equals("opponentsPiece"))) {
				System.out.println("2Up and 1Right");
				whatsOnField = checkField(x, y);
				saveField(whatsOnField, x, y);

				x++;
				y--;
			}

			if (x1 >= 0 && x1 <= 7 && y1 >= 0 && y1 <= 7
					&& (checkField(x1, y1).equals("empty") || checkField(x1, y1).equals("opponentsPiece"))) {
				System.out.println("2Up and 1Left");
				whatsOnField = checkField(x1, y1);
				saveField(whatsOnField, x1, y1);

				x1++;
				y1--;
			}

			if (x2 >= 0 && x2 <= 7 && y2 >= 0 && y2 <= 7
					&& (checkField(x2, y2).equals("empty") || checkField(x2, y2).equals("opponentsPiece"))) {
				System.out.println("2Down and 1Left");
				whatsOnField = checkField(x2, y2);
				saveField(whatsOnField, x2, y2);

				x2--;
				y2++;
			}

			if (x3 >= 0 && x3 <= 7 && y3 >= 0 && y3 <= 7
					&& (checkField(x3, y3).equals("empty") || checkField(x3, y3).equals("opponentsPiece"))) {
				System.out.println("2Down and 1Right");
				whatsOnField = checkField(x3, y3);
				saveField(whatsOnField, x3, y3);

				x3++;
				y3++;
			}

			if (x4 >= 0 && x4 <= 7 && y4 >= 0 && y4 <= 7
					&& (checkField(x4, y4).equals("empty") || checkField(x4, y4).equals("opponentsPiece"))) {
				System.out.println("1Up and 2Left");
				whatsOnField = checkField(x4, y4);
				saveField(whatsOnField, x4, y4);

				x4--;
				y4--;
			}

			if (x5 >= 0 && x5 <= 7 && y5 >= 0 && y5 <= 7
					&& (checkField(x5, y5).equals("empty") || checkField(x5, y5).equals("opponentsPiece"))) {
				System.out.println("1Up and 2Right");
				whatsOnField = checkField(x5, y5);
				saveField(whatsOnField, x5, y5);

				x5++;
				y5--;
			}

			if (x6 >= 0 && x6 <= 7 && y6 >= 0 && y6 <= 7
					&& (checkField(x6, y6).equals("empty") || checkField(x6, y6).equals("opponentsPiece"))) {
				System.out.println("1Down and 2Right");
				whatsOnField = checkField(x6, y6);
				saveField(whatsOnField, x6, y6);

				x6++;
				y6--;
			}

			if (x7 >= 0 && x7 <= 7 && y7 >= 0 && y7 <= 7
					&& (checkField(x7, y7).equals("empty") || checkField(x7, y7).equals("opponentsPiece"))) {
				System.out.println("1Down and 2Left");

				/*
				 * Window windows[][] = new Window[x7][y7]; windows[x7][y7] =
				 * new Window(3); getChildren().add(windows[x7][y7]);
				 */

				whatsOnField = checkField(x7, y7);
				saveField(whatsOnField, x7, y7);
				x7--;
				y7++;
			}
		}
	}

	/*
	 * public void checkKnight(){ String whatsOnField = "";
	 * 
	 * int knightMovesx [] ={-2, -1, 1, 2 ,2, 1, -1, -2}; int knightMovesy []
	 * ={1, 2, 2 ,1, -1, -2, -2, -1}; int kx =xOnBoard ; int ky =yOnBoard;
	 * 
	 * 
	 * for(int i=0; i<8; i++){ kx = xOnBoard+knightMovesx[i]; ky =
	 * yOnBoard+knightMovesy[i]; if(kx >= 0 && kx <= 7 && ky >= 0 && ky <= 7){
	 * whatsOnField = checkField(kx, ky); saveField(whatsOnField, kx, ky); }
	 * 
	 * 
	 * } }
	 */

	// To check the field of x and y
	protected String checkField(int x, int y) {

		String whatsOnField = "";

		// First check if it is empty
		if (cBoard[x][y] == 0) {
			whatsOnField = "empty";
		}
		// piece of same type
		else if ((cBoard[x][y] > 0 && type == 1) || (cBoard[x][y] < 0 && type == 2)) {
			whatsOnField = "sameType";
		}
		// piece of opponent
		else {
			whatsOnField = "opponentsPiece";
			System.out.println(whatsOnField);
		}

		return whatsOnField;

	}

	protected void saveField(String whatsOnField, int x, int y) {

		
		if (whatsOnField == "empty" || whatsOnField == "opponentsPiece") {
			// To save the possible moves

			Map<String, Integer> map = new HashMap<String, Integer>();
			map.put("x", x);
			map.put("y", y);

			System.out.println("x: " + x);
			System.out.println("y: " + y);
			possibleMoveTos.add(map);
		}

	}
	// capture method

}
