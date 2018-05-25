package agenda.modele;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="agenda")

public class AgendaWrapper {
	
	private List<RendezVous> agenda ;
	
	@XmlElement (name="rendez-vous")
	
	public List<RendezVous> getAgenda() {
	return agenda ;
	}
	
	public void setAgenda(List<RendezVous> agenda) {
	this.agenda = agenda ;
	}
	
}