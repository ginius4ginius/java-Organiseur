package agenda;


import java.util.Optional;

import agenda.modele.RendezVous;
import agenda.vue.VueControleur;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {
	
	private ObservableList<RendezVous> lesrendezvous = FXCollections.observableArrayList();
	
	public ObservableList<RendezVous> getLesrendezvous(){
		return lesrendezvous ;
		}
	

	@Override
	public void start(Stage primaryStage) {
		try {
			primaryStage.setOnCloseRequest ( (ev)-> {
				try{
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Attention, fermeture de l'application agenda");
				alert.setHeaderText("Voulez-vous vraiment fermer votre agenda ?");
				alert.setContentText("L'agenda n'est peut-�tre pas sauvegard�!");
				ButtonType ok = new ButtonType("OK");
				ButtonType annule = new ButtonType("ANNULER");
				alert.getButtonTypes().setAll(ok,annule);
				Optional<ButtonType> result = alert.showAndWait();
				if (result.get()==annule) {
				ev.consume() ;
				}
				} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				}
				});
				
			// Cr�e un chargeur pour le fichier fxml.
			FXMLLoader loader = new FXMLLoader();
			
			
			//Main.class.getResource("../vue.VueRendezVous.fxml") retourne l'URL du fichier fxml � charger
			//Indique ainsi au chargeur l'emplacement du fichier � charger
			loader.setLocation(Main.class.getResource("vue/VueRendezVous.fxml"));
			//Charge l'objet correspondant au fichier comme objet racine
			GridPane rootLayout = (GridPane) loader.load();
			Scene scene = new Scene(rootLayout) ;
			primaryStage.setScene(scene);
			primaryStage.setTitle("Agenda personnel") ;
			
			VueControleur lecontroleur = loader.getController() ;
			lecontroleur.setMainApp(this);
			
			//Insertion d'une image � gauche dans la barre de titre de la fen�tre
			primaryStage.getIcons().add(new Image("file:resources/images/agenda2.png"));
			primaryStage.show();
			
			
			} catch(Exception e) {
			e.printStackTrace();
			}
		
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
