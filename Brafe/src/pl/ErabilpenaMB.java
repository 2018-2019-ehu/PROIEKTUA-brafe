package pl;


import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import bl.ZerbitzuaEJB;
import dl.Erabiltzaileak;

@Named
@ApplicationScoped
public class ErabilpenaMB {
	
	//Erabiltzaileak erabiltzaile= new Erabiltzaileak();
	@EJB
	private ZerbitzuaEJB zEJB;
	//private int kodea=0;
	private float balorazioa=0.0f;
	private Erabiltzaileak erabiltzaileDatuak=new Erabiltzaileak();
	private int kodea;
	
	
	public void erregistratu(ErabiltzaileaMB eMB) {
		
		Erabiltzaileak erabiltzaile=new Erabiltzaileak();
		erabiltzaile.setIzena(eMB.getIzena());
		erabiltzaile.setAbizenak(eMB.getAbizenak());
		erabiltzaile.setIdErabiltzailea(0);
		erabiltzaile.setHelbidea(eMB.getHelbidea());
		erabiltzaile.setTelefonoZenbakia(eMB.getTelefonoZenbakia());
		erabiltzaile.setBalorazioa(balorazioa);
		erabiltzaile.setJaioteData(eMB.getJaioteData());
		erabiltzaile.setPasahitza(eMB.getPasahitza());

		zEJB.erabiltzaileaErregistratuDB(erabiltzaile);
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
	
}
