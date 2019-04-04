package pl;


import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import bl.ZerbitzuaEJB;
import dl.Erabiltzaileak;

@Named
@ApplicationScoped
public class ErabilpenaMB {
	
	//Erabiltzaileak erabiltzaile= new Erabiltzaileak();
	private ZerbitzuaEJB zEJB;
	//private int kodea=0;
	
	public String abizenakBueltatu(ErabiltzaileaMB eMB) {
		Erabiltzaileak erabiltzaile;
		erabiltzaile=zEJB.erabiltzaileBakarraLortuDB(eMB.getIzena());
		return erabiltzaile.getIzena();
	}
	
	public void erregistratu(ErabiltzaileaMB eMB) {
		
		Erabiltzaileak erabiltzaile=new Erabiltzaileak();
		erabiltzaile.setAbizenak(eMB.getAbizenak());
		erabiltzaile.setBalorazioa(0);
		erabiltzaile.setErabiltzaileakID(2);
		erabiltzaile.setIzena(eMB.getIzena());
		erabiltzaile.setJaioteData(eMB.getJaioteData());
		erabiltzaile.setPasahitza(eMB.getPasahitza());
		erabiltzaile.setTelefonoZenbakia(eMB.getTelefonoZenbakia());
		erabiltzaile.setZonaldea(eMB.getZonaldea());
		
		zEJB.erabiltzaileaErregistratuDB(erabiltzaile);
	}
}
