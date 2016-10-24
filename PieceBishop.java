
import java.io.File;
import java.util.ArrayList;
import java.util.Map;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PieceBishop extends Piece{
	public PieceBishop(int type) {
		super(type);

		if(type == 1){
			File file = new File("imgs/WhiteBishop.png");
			image = new Image(file.toURI().toString());
			}
			else{
				File file = new File("imgs/BlackBishop.png");
				image = new Image(file.toURI().toString());
			}
		
		 iv = new ImageView();
		 iv.setImage(image);
	}

	protected void findFields() {
		possibleMoveTos = new ArrayList<Map<String, Integer>>();
		checkDiagonal(false);
	}
}
