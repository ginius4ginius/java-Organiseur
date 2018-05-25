package agenda.modele;
import agenda.LocalDateAdapter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

public class RendezVous {
	private LocalDate Date ;
	private String Heure ;
	private String Contact ;
	private String Objet ;
	private String Telephone ;
	private boolean Confirmer ;
	private boolean Transport ;
	public RendezVous(LocalDate date, String heure, String contact, String objet,
	String telephone, boolean confirmer, boolean transport) {
	Date = date ;
	Heure = heure ;
	Contact = contact ;
	Objet = objet ;
	Telephone = telephone ;
	Confirmer = confirmer ;
	Transport = transport ;
	}
	public RendezVous() {
	}
	
	@XmlJavaTypeAdapter(LocalDateAdapter.class)
	public LocalDate getDate() {
	return Date;
	}
	
	public void setDate(LocalDate Date) {
	this.Date = Date;
	}
	public String getHeure() {
	return Heure;
	}
	public void setHeure(String Heure) {
	this.Heure = Heure;
	}
	public String getContact() {
	return Contact;
	}
	public void setContact(String Contact) {
	this.Contact = Contact;
	}
	public String getObjet() {
	return Objet;
	}
	public void setObjet (String Objet) {
	this.Objet = Objet;
	}
	public String getTelephone() {
	return Telephone;
	}
	public void setTelephone (String Telephone) {
	this.Telephone = Telephone;
	}
	public boolean getConfirmer() {
	return Confirmer;
	}
	public void setConfirmer (boolean Confirmer) {
	this.Confirmer = Confirmer;
	}
	public boolean getTransport() {
	return Transport;
	}
	public void setTransport (boolean Transport) {
	this.Transport = Transport;
	}
	public String toString() {
	String confirm="";
	if (Confirmer) confirm="� confirmer";
	String transport="";
	if (Transport) transport="pr�voir transport";
	return "Le "+Date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))+
	" � "+Heure+" avec "+Contact +" "+ Telephone+" : "+
	Objet+", "+confirm +"," + transport;
	}
	}

