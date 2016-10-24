

import java.io.File;
import java.util.ArrayList;
import java.util.Map;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PieceQueen extends Piece {
	public PieceQueen(int type) {
		super(type);

		if(type == 1){
		File file = new File("imgs/WhiteQueen.png");
		image = new Image(file.toURI().toString());
		}
		else{
			File file = new File("imgs/BlackQueen.png");
			image = new Image(file.toURI().toString());
		}
		 iv = new ImageView();
		 iv.setImage(image);
	}
	
	protected void findFields(){
		possibleMoveTos = new ArrayList<Map<String, Integer>>();
		checkVerticalAndHorizontal(false);
		checkDiagonal(false);
	}
}
