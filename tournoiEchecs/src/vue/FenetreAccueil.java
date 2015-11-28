package vue;

import java.io.File;
import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.FileChooser.ExtensionFilter;

public class FenetreAccueil extends Stage {

	private FXMLLoader leLoader = null;
	
	public FenetreAccueil(Window window) {
		super();
		try {
			leLoader = new FXMLLoader(this.getClass().getResource("/vue/fxml/FenetreSaisie.fxml"));
			AnchorPane root = leLoader.load();
			this.initModality(Modality.WINDOW_MODAL);
			this.initOwner(window);
			Scene scene = new Scene(root);
			this.setScene(scene);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	
	public static File choisirTournoi(Window owner){
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Choisissez un tournoi");
		fileChooser.getExtensionFilters().add(new ExtensionFilter("xml Files", "*.xml"));
		return fileChooser.showOpenDialog(owner);
	}
}
