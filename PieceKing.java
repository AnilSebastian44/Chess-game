

import java.io.File;
import java.util.ArrayList;
import java.util.Map;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class PieceKing extends Piece {
	public PieceKing(int type) {
		super(type);

		if (type == 1) {
			File file = new File("imgs/WhiteKing.png");
			image = new Image(file.toURI().toString());
		} else {
			File file = new File("imgs/BlackKing.png");
			image = new Image(file.toURI().toString());
		}

		iv = new ImageView();
		//setupGestureSource(iv);
		iv.setImage(image);
	}
	
	/*
	public void setupGestureSource(final ImageView source) {

		source.setOnDragDetected(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

				// allow any transfer mode
				Dragboard db = source.startDragAndDrop(TransferMode.MOVE);

				// put a image on dragboard
				ClipboardContent content = new ClipboardContent();

				Image sourceImage = source.getImage();
				content.putImage(sourceImage);
				db.setContent(content);

				iv = source;

				event.consume();
			}
		});

		source.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				source.setCursor(Cursor.HAND);
				int curseur = (int) e.getSceneX();
				curseur = (int) e.getSceneY();
			}
		});

	}

	int curseur = 0;
	int nombreLeft = 10;
	int nombreRight = 0;
	*/
	
	protected void findFields() {
		possibleMoveTos = new ArrayList<Map<String, Integer>>();
		//when onky set it to true when the piece is a king
		checkVerticalAndHorizontal(true);
		checkDiagonal(true);
	}

}
