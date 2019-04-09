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
}
