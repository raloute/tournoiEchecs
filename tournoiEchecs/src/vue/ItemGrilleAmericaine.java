package vue;

import metier.Joueur;
import metier.Partie;
import metier.Ronde;
import modele.ModeleTournoi;

import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;

public class ItemGrilleAmericaine extends ListCell<Joueur>{

	private final GridPane gridPane = new GridPane();

	private final Label PI = new Label();
	private final Label titre = new Label();
    private final Label joueur = new Label();
    private final Label elo = new Label();
    private final Label categorie = new Label();
    private final Label federation = new Label();
    private final Label ligue = new Label();
    private final ArrayList<Label> rondes = new ArrayList<>();
    private final Label score = new Label();
    private final Label cumulatif = new Label();
    private final Label trs = new Label();
    private final Label perfs = new Label();

    private final AnchorPane content = new AnchorPane();

    private final Map<String,String> mapTitre = new HashMap<String,String>();
    private final Map<String,String> mapSexe = new HashMap<String,String>();
    private final Map<String,String> mapCategorie = new HashMap<String,String>();
    private final Map<String,String> mapTypeElo = new HashMap<String,String>();
    
    private int numRonde;


	public ItemGrilleAmericaine(){
			numRonde=ModeleTournoi.getTournoi().getNumRondeActuelle();
			if(numRonde==-1){
				numRonde=ModeleTournoi.getTournoi().getNbRondes();
			}
		
	        GridPane.setConstraints(PI, 0, 0);
	        GridPane.setConstraints(titre, 1, 0);
	        GridPane.setConstraints(joueur, 2, 0);
	        GridPane.setConstraints(elo, 3, 0);
	        GridPane.setConstraints(categorie, 4, 0);
	        GridPane.setConstraints(federation, 5, 0);
	        GridPane.setConstraints(ligue, 6, 0);
	        int i=7;
	        for (;i<ModeleTournoi.getTournoi().getNbRondes()+7;i++) {
	        	rondes.add(new Label());
	        	GridPane.setConstraints(rondes.get(i-7), i, 0);
			}
	        GridPane.setConstraints(score, i, 0);
	        GridPane.setConstraints(cumulatif, i+1, 0);
	        GridPane.setConstraints(trs, i+2, 0);
	        GridPane.setConstraints(perfs,i+3, 0);

	        gridPane.getColumnConstraints().add(new ColumnConstraints(20, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, HPos.LEFT, true));
	        gridPane.getColumnConstraints().add(new ColumnConstraints(5, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, HPos.LEFT, true));
	        gridPane.getColumnConstraints().add(new ColumnConstraints(200, Region.USE_COMPUTED_SIZE, 200, Priority.ALWAYS, HPos.LEFT, true));
	        gridPane.getColumnConstraints().add(new ColumnConstraints(40, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.ALWAYS, HPos.LEFT, true));
	        gridPane.getColumnConstraints().add(new ColumnConstraints(40, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.ALWAYS, HPos.LEFT, true));
	        gridPane.getColumnConstraints().add(new ColumnConstraints(20, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.ALWAYS, HPos.LEFT, true));
	        gridPane.getColumnConstraints().add(new ColumnConstraints(40, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.ALWAYS, HPos.LEFT, true));
	        for (int j=0;j<ModeleTournoi.getTournoi().getNbRondes();j++) {
	        	gridPane.getColumnConstraints().add(new ColumnConstraints(30, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.ALWAYS, HPos.LEFT, true));
			}
	        gridPane.getColumnConstraints().add(new ColumnConstraints(20, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, HPos.LEFT, true));
	        gridPane.getColumnConstraints().add(new ColumnConstraints(20, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, HPos.LEFT, true));
	        gridPane.getColumnConstraints().add(new ColumnConstraints(20, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, HPos.LEFT, true));
	        gridPane.getColumnConstraints().add(new ColumnConstraints(30, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, HPos.RIGHT, true));


	        gridPane.getRowConstraints().add(new RowConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, VPos.CENTER, true));

	        gridPane.setHgap(2);
	        gridPane.setVgap(8);
	        gridPane.getChildren().setAll(PI,titre,joueur, elo, categorie,federation,ligue,score,cumulatif,trs,perfs);
	        for (int j =0;j<numRonde;j++) {
	        	gridPane.getChildren().add(rondes.get(j));
			}
	        AnchorPane.setTopAnchor(gridPane, 0d);
	        AnchorPane.setLeftAnchor(gridPane, 0d);
	        AnchorPane.setBottomAnchor(gridPane, 0d);
	        AnchorPane.setRightAnchor(gridPane, 0d);
	        content.getChildren().add(gridPane);

	        mapTitre.put("Ma�tre FIDE Masculin", "f");
	        mapTitre.put("Ma�tre FIDE F�minin", "f");
	        mapTitre.put("Ma�tre International Masculin", "m");
	        mapTitre.put("Ma�tre International F�minin", "m");
	        mapTitre.put("Grand Ma�tre International Masculin", "g");
	        mapTitre.put("Grand Ma�tre International F�minin", "g");
	        mapTitre.put("Candidat Ma�tre Masculin", " ");
	        mapTitre.put("Candidat Ma�tre F�minin", " ");
	        mapTitre.put("Aucun titre", " ");
	        
	        mapCategorie.put("V�t�ran", "Vet");
	        mapCategorie.put("S�nior", "Sen");
	        mapCategorie.put("Junior", "Jun");
	        mapCategorie.put("Cadet", "Cad");
	        mapCategorie.put("Minime", "Min");
	        mapCategorie.put("Benjamin", "Ben");
	        mapCategorie.put("Pupille", "Pup");
	        mapCategorie.put("Poussin", "Pou");
	        mapCategorie.put("Petit Poussin", "Pet");
	        
	        mapTypeElo.put("FIDE", "F");
	        mapTypeElo.put("National", "N");
	        mapTypeElo.put("Nouveau", "E");
	        
	        mapSexe.put("Homme", "M");
	        mapSexe.put("Femme", "F");
	}

	@Override
    protected void updateItem(Joueur item, boolean empty) {
        super.updateItem(item, empty);
        setGraphic(null);
        setText(null);
        setContentDisplay(ContentDisplay.LEFT);
        if (!empty && item != null) {
        	PI.setText(String.valueOf(item.getClassement()));
        	titre.setText(mapTitre.get(item.getTitre()));
        	joueur.setText(item.getNomJoueur()+" "+item.getPrenomJoueur());
        	elo.setText(String.valueOf(item.getElo())+mapTypeElo.get(item.getTypeElo()));
        	categorie.setText(mapCategorie.get(item.getCategorie())+mapSexe.get(item.getSexe()));
        	federation.setText(item.getFederation());
        	ligue.setText(item.getLigue());
        	//rondes
	        for (int i=0;i<numRonde;i++) {
	        	for(Partie partie :ModeleTournoi.getTournoi().getRonde(i).getListePartie()){
	        		if(partie.joueurEstDansPartie(item)){
	        			rondes.get(i).setText(partie.getAffichageGa(item,i));
	        		}
	        	}
			}
        	score.setText(String.valueOf(item.getScore()));
        	cumulatif.setText(String.valueOf(item.getPointsDepartage("Cumulatif")));
        	trs.setText(String.valueOf(item.getPointsDepartage("Buchholz")));
        	perfs.setText(String.valueOf((int)item.getPointsDepartage("PerfElo")));

            setText(null);
            setGraphic(content);
            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        }
    }



}
