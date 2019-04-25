package pl;


import java.io.Serializable;

import javax.ejb.EJB;
//import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import bl.ZerbitzuaEJB;
import dl.Erabiltzaileak;

@Named
@SessionScoped
public class ErabilpenaMB implements Serializable {
	
	private static final long serialVersionUID = 1L;
	//Erabiltzaileak erabiltzaile= new Erabiltzaileak();
	@EJB
	private ZerbitzuaEJB zEJB;
	//private int kodea=0;
	private Erabiltzaileak erabiltzaileDatuak=new Erabiltzaileak();
	private int kodea;
	
	
	public void erregistratu(ErabiltzaileaMB eMB) {
		
		Erabiltzaileak erabiltzaile=new Erabiltzaileak();
		erabiltzaile.setIzena(eMB.getIzena());
		erabiltzaile.setAbizenak(eMB.getAbizenak());
		erabiltzaile.setIdErabiltzailea(0);
		erabiltzaile.setHelbidea(eMB.getHelbidea());
		erabiltzaile.setTelefonoZenbakia(eMB.getTelefonoZenbakia());
		erabiltzaile.setBalorazioa(3.5f);
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
			System.out.println(erabiltzaileDatuak.getAbizenak());
		}
		return orria;
	}
	
	public void pasahitzaAldatu(String pasahitza) {
		zEJB.pasahitzaAldatuDB(pasahitza, erabiltzaileDatuak.getIdErabiltzailea());
		erabiltzaileDatuak=zEJB.loginDatuakLortu(erabiltzaileDatuak.getIzena());
		System.out.println(erabiltzaileDatuak.getPasahitza());
	}
	
	public void helbideaAldatu(String helbidea) {
		zEJB.helbideaAldatuDB(helbidea, erabiltzaileDatuak.getIdErabiltzailea());
		erabiltzaileDatuak=zEJB.loginDatuakLortu(erabiltzaileDatuak.getIzena());
	}
	
	public void zenbakiaAldatu(int zenbakia) {
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
	
}
