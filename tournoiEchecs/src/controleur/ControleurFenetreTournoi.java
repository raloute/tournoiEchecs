package controleur;

import javafx.scene.Node;
import javafx.scene.control.*;

import java.awt.List;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import modele.ModeleDepartage;
import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import vue.AjouterJoueurTournoi;
import metier.Tournoi;
import metier.departage.Departage;
import modele.ModeleTournoi;
import modele.Validation;

public class ControleurFenetreTournoi implements Initializable {

	private ObservableList<Departage> items;
	private ObservableList<Departage> itemsChoisis;

	@FXML
	ListView<Departage> lv_listeDepartages;

	@FXML
	ListView<Departage> lv_listeDepartagesChoisis;

	@FXML
	DatePicker dp_dateDeb;

	@FXML
	Button button_addDepartage;
	
	@FXML
	Button button_removeDepartage;

	@FXML
	DatePicker dp_dateFin;

	@FXML
	TextField tf_nomTournoi;

	@FXML
	TextField tf_lieuTournoi;

	@FXML
	TextField tf_nbRondes;

	@FXML
	TextField tf_arbitre;

	@FXML
    private void actionFenetreJoueurs(Event e) {

		if (check(tf_nomTournoi) && check(tf_lieuTournoi)&& checkDate(dp_dateDeb, dp_dateFin) && check(tf_arbitre) && check(tf_nbRondes)) {
			if (lv_listeDepartagesChoisis.getItems().size()>=3){
				Tournoi tournoi = new Tournoi(tf_nomTournoi.getText(),tf_lieuTournoi.getText(),dp_dateDeb.getValue(),dp_dateFin.getValue(),tf_arbitre.getText(),Integer.valueOf(tf_nbRondes.getText()));
				tournoi.setListeDepartages(new ArrayList<Departage>(itemsChoisis));
				ModeleTournoi.ajouterTournoi(tournoi);	
				
				AjouterJoueurTournoi ajoutjoueur = new AjouterJoueurTournoi(Main.getPrimaryStage());
				ajoutjoueur.show();
				((Node)e.getSource()).getScene().getWindow().hide();
			}
			else {
				lv_listeDepartagesChoisis.setStyle("-fx-control-inner-background : red; ");
			}
		}
	}


	@FXML
	public void actionAnnuler(Event e) {
		//if (showConfirm("Voulez-vous vraiment annuler l'opération ?", Main.getPrimaryStage()))
			((Node)e.getSource()).getScene().getWindow().hide();
	}



	private boolean check(TextField leChampDeSaisie) {
		if (Validation.estVide(leChampDeSaisie)) {
			leChampDeSaisie.setStyle("-fx-control-inner-background : red; ");
			return false;
		} else {
			leChampDeSaisie.setStyle("-fx-control-inner-background : white; ");
			return true;
		}
	}
	
	private boolean checkDate(DatePicker d1,DatePicker d2) {
		if (!Validation.verifDate(d1, d2)) {
			d1.setStyle("-fx-control-inner-background : red; ");
			d2.setStyle("-fx-control-inner-background : red; ");
			return false;
		} else {
			d1.setStyle("-fx-control-inner-background : white; ");
			d2.setStyle("-fx-control-inner-background : white; ");
			return true;
		}
	}

	@FXML
	public void actionRajouterDepartage(){
		Departage dep =  (Departage)lv_listeDepartages.getSelectionModel().getSelectedItem();
		if(dep!=null){
			itemsChoisis.add(dep);
			items.remove(dep);
		}
	}


	@FXML
	public void actionEnleverDepartage(){
		Departage dep=(Departage)lv_listeDepartagesChoisis.getSelectionModel().getSelectedItem();
		if(dep!=null){
		itemsChoisis.remove(dep);
		items.add(dep);
		}
		else {
		}
	}
	
	@FXML
	public void limiteTexte(){
		tf_nomTournoi=Validation.verifLongueurTexte(tf_nomTournoi,30);
		tf_lieuTournoi=Validation.verifLongueurTexte(tf_lieuTournoi,30);
		tf_arbitre=Validation.verifLongueurTexte(tf_arbitre,30);
		tf_nbRondes=Validation.verifLongueurTexte(tf_nbRondes,6);
		
	}

	

	private void chiffresSeulement(Number oldValue, Number newValue, TextField leChampDeSaisie){
		if(newValue.intValue() > oldValue.intValue()){
            char ch = leChampDeSaisie.getText().charAt(newValue.intValue()-1);
            if(!(ch >= '0' && ch <= '9' )){
            	leChampDeSaisie.setText(leChampDeSaisie.getText().substring(0,leChampDeSaisie.getText().length()-1));
            }
       }
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		items =FXCollections.observableArrayList (
				ModeleDepartage.getcollectionDepartages());
		itemsChoisis =FXCollections.observableArrayList ();
		tf_nbRondes.lengthProperty().addListener((observable,oldValue,newValue)->chiffresSeulement(oldValue,newValue,tf_nbRondes));
		
		if(ModeleTournoi.getTournoi()!=null){
			tf_nomTournoi.setText(ModeleTournoi.getTournoi().getNom());
			tf_lieuTournoi.setText(ModeleTournoi.getTournoi().getNom());
			dp_dateDeb.setValue(ModeleTournoi.getTournoi().getDateDeb());
			dp_dateFin.setValue(ModeleTournoi.getTournoi().getDateFin());
			tf_arbitre.setText(ModeleTournoi.getTournoi().getArbitre());
			tf_nbRondes.setText(String.valueOf(ModeleTournoi.getTournoi().getNbRondes()));
			itemsChoisis.addAll(ModeleTournoi.getTournoi().getListeDepartages());
			items.removeAll(itemsChoisis);
		}
		lv_listeDepartages.setItems(items);
		lv_listeDepartagesChoisis.setItems(itemsChoisis);
		
	}

}
