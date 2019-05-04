package pl;


import java.io.IOException;
import java.io.Serializable;

import javax.ejb.EJB;
//import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import bl.ZerbitzuaEJB;
import dl.Erabiltzaileak;

@Named
@SessionScoped
public class ErabilpenaMB implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@EJB
	private ZerbitzuaEJB zEJB;
	private Erabiltzaileak erabiltzaileDatuak=new Erabiltzaileak();
	private int kodea;
	
	
	public String erregistratu(ErabiltzaileaMB eMB) {
		
		String orria=null;
		Erabiltzaileak erabiltzaile=new Erabiltzaileak();
		erabiltzaile.setIzena(eMB.getIzena());
		erabiltzaile.setIdErabiltzailea(0);
		erabiltzaile.setHelbidea(eMB.getHelbidea());
		erabiltzaile.setTelefonoZenbakia(eMB.getTelefonoZenbakia());
		erabiltzaile.setBalorazioa(3.5f);
		erabiltzaile.setJaioteData(eMB.getJaioteData());
		erabiltzaile.setPasahitza(eMB.getPasahitza());
		
		if(atributuakKonprobatu(erabiltzaile)) {
			kodea=2;
		}
		else {
			kodea=zEJB.erabiltzaileaErregistratuDB(erabiltzaile);
			if(kodea==0) {
				orria="ondoErregistroa.xhtml";
			}
		}
		return orria;
	}
	
	public String logeatu(ErabiltzaileaMB eMB) {
		String orria=null;
		kodea=zEJB.erabiltzaileaLogeatu(eMB.getIzena(),eMB.getPasahitza());

		if(kodea==3) {
			erabiltzaileDatuak=zEJB.loginDatuakLortu(eMB.getIzena());
			orria="hasiera.xhtml";
		}
		return orria;
	}
	
	public void pasahitzaAldatu(String pasahitza) {
		zEJB.pasahitzaAldatuDB(pasahitza, erabiltzaileDatuak.getIdErabiltzailea());
		erabiltzaileDatuak=zEJB.loginDatuakLortu(erabiltzaileDatuak.getIzena());
		kodea=5;
	}
	
	public void helbideaAldatu(String helbidea) {
		zEJB.helbideaAldatuDB(helbidea, erabiltzaileDatuak.getIdErabiltzailea());
		erabiltzaileDatuak=zEJB.loginDatuakLortu(erabiltzaileDatuak.getIzena());
	}
	
	public void zenbakiaAldatu(String zenbakia) {
		zEJB.zenbakiaAldatuDB(zenbakia, erabiltzaileDatuak.getIdErabiltzailea());
		erabiltzaileDatuak=zEJB.loginDatuakLortu(erabiltzaileDatuak.getIzena());
	}
	
	public void kodeaGarbitu() {
		kodea=0;
	}
	public Erabiltzaileak getErabiltzaileDatuak() {
		return erabiltzaileDatuak;
	}

	public void setErabiltzaileDatuak(Erabiltzaileak erabiltzaileDatuak) {
		this.erabiltzaileDatuak = erabiltzaileDatuak;
	}

	public int getKodea() {
		return kodea;
	}

	public void setKodea(int kodea) {
		this.kodea = kodea;
	}
	
	public boolean atributuakKonprobatu(Erabiltzaileak erabiltzailea) {
		boolean egoera=false;
		
		if(erabiltzailea.getIzena().equals(""))egoera=true; 
		if(erabiltzailea.getHelbidea().equals("")) egoera=true;
		if(erabiltzailea.getJaioteData()==null) egoera=true;
		if(erabiltzailea.getPasahitza().equals("")) egoera=true;
		if(erabiltzailea.getTelefonoZenbakia().equals("")) egoera=true;
		System.out.println(erabiltzailea.getJaioteData());
		return egoera;
	}
	
	public void kontuaEzabatu() throws IOException {
		zEJB.kontuaEzabatuDB(erabiltzaileDatuak);
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		FacesContext.getCurrentInstance().getExternalContext().redirect("Login.xhtml");
	}
	
	public String logeatuOrria() {
		kodeaGarbitu();
		return "Login.xhtml";
	}
	
	public String erregistratu() {
		kodeaGarbitu();
		return "erregistroa.xhtml";
	}
}
