

import java.io.File;
import java.util.ArrayList;
import java.util.Map;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PiecePawn extends Piece{

	
	public PiecePawn(int type) {
		super(type);

		if(type == 1){
			File file = new File("imgs/WhitePawn.png");
			image = new Image(file.toURI().toString());
			}
		
			else{
			File file = new File("imgs/BlackPawn.png");
			image = new Image(file.toURI().toString());

			}
		
		 iv = new ImageView();
		 iv.setImage(image);
	}
	
	protected void findFields() {
		possibleMoveTos = new ArrayList<Map<String, Integer>>();
		checkPawn();
		checkPawnDiagonal();
	}
	
}
