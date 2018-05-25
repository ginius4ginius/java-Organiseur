package agenda.vue;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import agenda.Main;
import agenda.modele.AgendaWrapper;
import agenda.modele.RendezVous;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class VueControleur {

	@FXML
	private DatePicker choixDate ;
	@FXML
	private ComboBox<String> heure ;
	@FXML
	private ComboBox<String> minutes ;
	@FXML
	private TextField contact ;
	@FXML
	private TextField telephone ;
	@FXML
	private CheckBox confirmer ;
	@FXML
	private CheckBox transport ;
	@FXML
	private TextField objet ;
	@FXML
	private ListView<RendezVous> liste ;
	private ObservableList<RendezVous> lesrendezvous ;
	//private ObservableList<RendezVous> lesrendezvous =
	//FXCollections.observableArrayList();
	
	public void setMainApp(Main lemain){
		lesrendezvous = lemain.getLesrendezvous();
		liste.setItems(lesrendezvous);
		
		File fichier = new File ("agenda.xml");
		if (fichier.exists()) {
		JAXBContext context;
		try {
		context = JAXBContext.newInstance(AgendaWrapper.class);
		Unmarshaller um = context.createUnmarshaller();
		AgendaWrapper agenda = (AgendaWrapper) um.unmarshal(fichier);
		List<RendezVous> lesrv = agenda.getAgenda();
		lesrendezvous.addAll(lesrv);
		} catch (JAXBException e) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Erreur grave");
		alert.setHeaderText("Le fichier agenda ne peut pas être lu");
		alert.showAndWait();
		}
		}
		}
	
	@FXML
	private void sauverAgenda() {
	JAXBContext context;
	try {
	context = JAXBContext.newInstance(AgendaWrapper.class);
	Marshaller m = context.createMarshaller();
	m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	AgendaWrapper wrapper = new AgendaWrapper() ;
	wrapper.setAgenda(lesrendezvous);
	//m.marshal(wrapper, System.out);
	m.marshal(wrapper, new File("agenda.xml"));
	} catch (JAXBException e1) {
	// TODO Auto-generated catch block
	e1.printStackTrace();
	}
	}
	
	
	
	@FXML
	private void initialize() {
	ObservableList<String> lesheures =
	FXCollections.observableArrayList("08","09","10","11","12","13","14","15","16",
	"17","18","19","20");
	heure.setItems(lesheures);
	heure.setValue("08");
	ObservableList<String> lesminutes =
	FXCollections.observableArrayList("00","10","20","30","40","50");
	minutes.setItems(lesminutes);
	minutes.setValue("00");
	choixDate.setValue(LocalDate.now());
	//La ListView liste visualisera la ObservableList lesrendezvous.
	//liste.setItems(lesrendezvous);
	}
	
	private boolean pasErreur() {
		String Contact = contact.getText() ;
		String tel = telephone.getText() ;
		if (Contact.isEmpty() || tel.isEmpty()) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Informations incomplètes");
		alert.setHeaderText("Compléter les champs indiqués");
		if (Contact.isEmpty())
		contact.setPromptText("à compléter");
		if (tel.isEmpty())
		telephone.setPromptText("à compléter");
		alert.showAndWait();
		return false ;
		}
		else return true;
	}
			
			
	@FXML
	private void handleAjouterRV() {
	boolean res = pasErreur();
		if (res) {
	String Heure = heure.getValue() + ":" + minutes.getValue();
	RendezVous rv = new RendezVous(choixDate.getValue(), Heure, contact.getText(),
	objet.getText(),telephone.getText(),confirmer.isSelected(),
	transport.isSelected());
	lesrendezvous.add(rv);
		}
	}
	@FXML
	private void handleSupprimerRV() {
		if(liste.getSelectionModel().isEmpty()==false) {
		lesrendezvous.remove(liste.getSelectionModel().getSelectedIndex());
		}
	}
	
	@FXML
	private void nettoyage() {
		choixDate.setValue(LocalDate.now());
		heure.setValue("08");
		minutes.setValue("00");
		contact.clear();
		telephone.clear();
		objet.clear();
		
	}
	
	@FXML
	private void trie() {
		
		lesrendezvous.sort(new Comparator<RendezVous>() {
			
			@Override
			public int compare(RendezVous a, RendezVous b) {
				
				LocalDateTime dateA= LocalDateTime.of(a.getDate(), LocalTime.parse(a.getHeure()));
				LocalDateTime dateB= LocalDateTime.of(b.getDate(), LocalTime.parse(b.getHeure()));
				
				int r = dateA.compareTo(dateB);
				
				return r;
				
			}
			
		});
		
		/*
		ObservableList<RendezVous> lesrendezvousTrie = lesrendezvous.sorted(new Comparator<RendezVous>() {

			@Override
			public int compare(RendezVous a, RendezVous b) {
				
				
				int valx =a.getDate().compareTo(b.getDate());
				
				if(valx==0) {
				String []tabHeurea = a.getHeure().split(":");
				int heureA = Integer.parseInt(tabHeurea[0]);
				int minuteA = Integer.parseInt(tabHeurea[1]);
				
				String []tabHeureb = b.getHeure().split(":");
				int heureB = Integer.parseInt(tabHeureb[0]);
				int minuteB = Integer.parseInt(tabHeureb[1]);
				
				if(heureA<heureB) {
					return -1;
				}
				else if(heureA==heureB) {
					
					if(minuteA<minuteB) {
						return -1;
					}
					if(minuteA==minuteB) {
						return 0;
					}
					if(minuteA>minuteB) {
						return 1;
					}
					
					
				}else return 1;
				
				}
					
				return valx;
			}
			
		});*/
		//liste.setItems(lesrendezvousTrie);
		
	}
	}
