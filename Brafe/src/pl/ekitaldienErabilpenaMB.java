package pl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import bl.ZerbitzuaEJB;
import dl.Ekitaldiak;

@Named
@SessionScoped
public class ekitaldienErabilpenaMB implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Ekitaldiak ekitaldia=new Ekitaldiak();
	private List<Ekitaldiak> ekitaldiGuztiak=new ArrayList<Ekitaldiak>();
	private String iragazitakoa=null;
	
	@EJB
	private ZerbitzuaEJB zEJB;
	
	public ekitaldienErabilpenaMB() {
		super();
	}
	
	public List<Ekitaldiak> ekitaldiakIragazi(){
		List<Ekitaldiak> ekitaldiak;
		if(iragazitakoa==null) {
			ekitaldiGuztiak=zEJB.ekitaldiGuztiakLortu();
			ekitaldiak=ekitaldiGuztiak;
		}
		else{
			ekitaldiGuztiak=zEJB.ekitaldiakIragaziDB(iragazitakoa);
			ekitaldiak=ekitaldiGuztiak;
		}
		return(ekitaldiak);
	}
	
	public Ekitaldiak getEkitaldia() {
		return ekitaldia;
	}

	public void setEkitaldia(Ekitaldiak ekitaldia) {
		this.ekitaldia = ekitaldia;
	}

	public List<Ekitaldiak> getEkitaldiGuztiak() {
		return ekitaldiGuztiak;
	}

	public void setEkitaldiGuztiak(List<Ekitaldiak> ekitaldiGuztiak) {
		this.ekitaldiGuztiak = ekitaldiGuztiak;
	}

	public String getIragazitakoa() {
		return iragazitakoa;
	}

	public void setIragazitakoa(String iragazitakoa) {
		this.iragazitakoa = iragazitakoa;
	}
	
	
	

}
