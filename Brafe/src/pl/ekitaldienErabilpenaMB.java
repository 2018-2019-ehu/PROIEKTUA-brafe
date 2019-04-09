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

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Ekitaldiak ekitaldia=new Ekitaldiak();
	private List<Ekitaldiak> ekitaldiGuztiak=new ArrayList<Ekitaldiak>();
	
	@EJB
	private ZerbitzuaEJB zEJB;
	
	public ekitaldienErabilpenaMB() {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<Ekitaldiak> ekitadiakLortu(){
		return zEJB.ekitaldiGuztiakLortu();
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
	
	
	

}
