package bl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

//import com.sun.glass.ui.Size;

import dl.*;

//import dl.ErabiltzaileakEskaini;

@Stateless
@LocalBean
public class ZerbitzuaEJB {

	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public int erabiltzaileaErregistratuDB(Erabiltzaileak erab){
		List<Erabiltzaileak> erabiltzaileak=em.createNamedQuery("Erabiltzaileak.findAll").getResultList();
		boolean egoera=false;
		int kodea=0;
		
		for(int i=0;i<erabiltzaileak.size();i++) {
			
			if(erabiltzaileak.get(i).getIzena().equals(erab.getIzena())) {
				egoera=true;
				kodea=1;
			}
		}
		if(egoera==false) {
			em.merge(erab);
		}
		
		return kodea;
	}
	
	public int erabiltzaileaLogeatu(String izena,String pasahitza) {
		int kasua=3;
		boolean bilaketa=false;
		Erabiltzaileak erabiltzailea= new Erabiltzaileak();
		
		try {
			erabiltzailea=(Erabiltzaileak)em.createNamedQuery("Erabiltzaileak.findErabiltzailea").setParameter("izena", izena).getSingleResult();
		}catch(javax.persistence.NoResultException exception) {
			bilaketa=true;
		}
		
		if(bilaketa==true) {
			kasua=1;
		}
		else {
			if(!erabiltzailea.getPasahitza().equals(pasahitza)) {
				kasua=2;
			}
		}
		System.out.println(kasua);
		return kasua;
	}
	
	public Erabiltzaileak loginDatuakLortu(String izena) {
		Erabiltzaileak erab=new Erabiltzaileak();
		try {
			erab=(Erabiltzaileak)em.createNamedQuery("Erabiltzaileak.findErabiltzailea").setParameter("izena", izena).getSingleResult();
		}
		catch(javax.persistence.NoResultException exception){

		}
		return erab;
	}
	
	@SuppressWarnings("unchecked")
	public List<Ekitaldiak> ekitaldiGuztiakLortu(){
		List<Ekitaldiak> ekitaldiak=em.createNamedQuery("Ekitaldiak.findAll").getResultList();
		Date data=new Date();
		for(int i=0;i<ekitaldiak.size();i++) {
			if(ekitaldiak.get(i).getAmaieraData().before(data)) {
				em.remove(ekitaldiak.get(i));
				ekitaldiak.remove(i);
			}
		}
		return ekitaldiak;
	}
	
	@SuppressWarnings("unchecked")
	public List<Azpiekitaldiak> azpiekitaldiGuztiakLortu(Ekitaldiak ekitaldia){
		return (List<Azpiekitaldiak>)em.createNamedQuery("Azpiekitaldiak.findMenpekoak").setParameter("ekitaldiak", ekitaldia).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Geldialdiak> geldialdiGuztiakLortu(Azpiekitaldiak azpiekitaldia){
		List<Geldialdiak> geldialdiak=(List<Geldialdiak>)em.createNamedQuery("Geldialdiak.findMenpekoak").setParameter("azpiekitaldiak", azpiekitaldia).getResultList();
		Date data=new Date();
		for(int i=0;i<geldialdiak.size();i++) {
			if(geldialdiak.get(i).getIraungiteData().before(data)) {
				em.remove(geldialdiak.get(i));
				geldialdiak.remove(i);
			}
		}
		return geldialdiak;
	}
	
	@SuppressWarnings("unchecked")
	public List<Baieztatuak> baieztatuakLortuDB(Geldialdiak geldialdia){
		return (List<Baieztatuak>)em.createNamedQuery("Baieztatuak.findGeldialdi").setParameter("geldialdiak", geldialdia).getResultList();
	}
	public Ekitaldiak ekitaldiaLortu(int idEkitaldia) {
		return em.find(Ekitaldiak.class, idEkitaldia);
	}
	
	public Azpiekitaldiak azpiekitaldiaLortu(int idAzpiekitaldia) {
		return em.find(Azpiekitaldiak.class, idAzpiekitaldia);
	}
	
	public Geldialdiak geldialdiaLortu(int idGeldialdia) {
		return em.find(Geldialdiak.class, idGeldialdia);
	}
	
	@SuppressWarnings("unchecked")
	public int geldialdianSartuDB(Erabiltzaileak erabiltzailea,Baieztatuak baieztatua, Geldialdiak geldialdia) {
		Geldialdiak geldialdi=em.find(Geldialdiak.class, geldialdia.getIdGeldialdiak());
		List<Baieztatuak> baieztatuak=(List<Baieztatuak>)em.createNamedQuery("Baieztatuak.findMenpekoak").setParameter("erabiltzaileak", erabiltzailea).getResultList();
		int kodea=0;
		if(baieztatuak.isEmpty()) {
			float batazbestekoa=((geldialdi.getBatazbestekoBalorazioa()*geldialdi.getPartehartzaileak())+baieztatua.getErabiltzaileak().getBalorazioa())/(geldialdi.getPartehartzaileak()+1);
			geldialdi.setPartehartzaileak(geldialdi.getPartehartzaileak()+1);
			geldialdi.setBatazbestekoBalorazioa(batazbestekoa);
			em.persist(geldialdi);
			em.merge(baieztatua);
		}
		else{
			kodea=2;
		}
		return kodea;
	}
	
	@SuppressWarnings("unchecked")
	public void geldialditikAteraDB(Erabiltzaileak erabiltzailea, int geldialdiaId) {
		List<Baieztatuak> baieztatuak=new ArrayList<Baieztatuak>();
		baieztatuak=em.createNamedQuery("Baieztatuak.findMenpekoak").setParameter("erabiltzaileak",erabiltzailea).getResultList();
		for(int i=0;i<baieztatuak.size();i++) {
			if(baieztatuak.get(i).getErabiltzaileak().getIzena().equals(erabiltzailea.getIzena()) && baieztatuak.get(i).getGeldialdiak().getIdGeldialdiak()==geldialdiaId) {
				Geldialdiak geldialdi=(Geldialdiak)em.find(Geldialdiak.class, geldialdiaId);
				float batazbestekoa;
				if((geldialdi.getPartehartzaileak()-1)==0) {
					batazbestekoa=0;
				}
				else {
					batazbestekoa=((geldialdi.getBatazbestekoBalorazioa()*geldialdi.getPartehartzaileak())-baieztatuak.get(i).getErabiltzaileak().getBalorazioa())/(geldialdi.getPartehartzaileak()-1);
				}
				System.out.println(batazbestekoa);
				geldialdi.setPartehartzaileak(geldialdi.getPartehartzaileak()-1);
				geldialdi.setBatazbestekoBalorazioa(batazbestekoa);
				em.persist(geldialdi);
				em.remove(baieztatuak.get(i));
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public void ekitaldiaSortu(Ekitaldiak ekitaldia) {
		List<Ekitaldiak> ekitaldiGuztiak= em.createNamedQuery("Ekitaldiak.findAll").getResultList();
		boolean egoera=false;
		
		for(int i=0;i<ekitaldiGuztiak.size();i++) {
			if(ekitaldiGuztiak.get(i).getEkitaldiIzena().equals(ekitaldia.getEkitaldiIzena())) {
				egoera=true;
			}
		}
		if(egoera==false) {
			em.merge(ekitaldia);
		}
	}
	
	@SuppressWarnings("unchecked")
	public void azpiekitaldiaSortu(Azpiekitaldiak azpiekitaldia,Ekitaldiak ekitaldia) {
		List<Azpiekitaldiak> azpiekitaldiGuztiak= em.createNamedQuery("Azpiekitaldiak.findMenpekoak").setParameter("ekitaldiak", ekitaldia).getResultList();
		boolean egoera=false;
		
		for(int i=0;i<azpiekitaldiGuztiak.size();i++) {
			if(azpiekitaldiGuztiak.get(i).getBueltatzekoLekua().equals(azpiekitaldia.getBueltatzekoLekua())) {
				egoera=true;
			}
		}
		if(egoera==false) {
			em.merge(azpiekitaldia);
		}
	}
	
	@SuppressWarnings("unchecked")
	public void geldialdiaSortu(Geldialdiak geldialdia,Azpiekitaldiak azpiekitaldia) {
		List<Geldialdiak> geldialdiGuztiak= em.createNamedQuery("Geldialdiak.findMenpekoak").setParameter("azpiekitaldiak", azpiekitaldia).getResultList();
		boolean egoera=false;
		
		for(int i=0;i<geldialdiGuztiak.size();i++) {
			if(geldialdiGuztiak.get(i).getGeldialdiIzena().equals(geldialdia.getGeldialdiIzena())) {
				egoera=true;
			}
		}
		if(egoera==false) {
			em.merge(geldialdia);
		}
	}
	
	public int ekitaldiaEzabatuDB(int idEkitaldia) {
		int kodea=0;
		Ekitaldiak ekitaldia=em.find(Ekitaldiak.class, idEkitaldia);
		if(ekitaldia!=null) {
			List<Azpiekitaldiak> azpiekitaldiak=ekitaldia.getAzpiekitaldiaks();
			if(azpiekitaldiak.isEmpty()) {
				em.remove(ekitaldia);		
			}
			else {
				kodea=1;
			}
		}
		return kodea;
	}
	
	public int azpiekitaldiaEzabatuDB(int idAzpiekitaldia) {
		int kodea=0;
		Azpiekitaldiak azpiekitaldia=em.find(Azpiekitaldiak.class, idAzpiekitaldia);
		if(azpiekitaldia!=null) {
			List<Geldialdiak> geldialdiak=azpiekitaldia.getGeldialdiaks();
			if(geldialdiak.isEmpty()) {
				em.remove(azpiekitaldia);		
			}
			else {
				kodea=1;
			}
		}
		return kodea;
	}
	
	public int geldialdiaEzabatuDB(int idGeldialdia) {
		int kodea=0;
		Geldialdiak geldialdia=em.find(Geldialdiak.class, idGeldialdia);
		if(geldialdia!=null) {
			List<Baieztatuak> baieztatuak=geldialdia.getBaieztatuaks();
			if(baieztatuak.isEmpty()) {
				em.remove(geldialdia);		
			}
			else {
				kodea=1;
			}
		}
		return kodea;
	}
	
	public void pasahitzaAldatuDB(String pasahitza, int erabiltzaileID) {
		Erabiltzaileak erab=em.find(Erabiltzaileak.class, erabiltzaileID);
		erab.setPasahitza(pasahitza);
		em.persist(erab);
	}
	public void helbideaAldatuDB(String helbidea, int erabiltzaileID) {
		Erabiltzaileak erab=em.find(Erabiltzaileak.class, erabiltzaileID);
		erab.setHelbidea(helbidea);
		em.persist(erab);
	}
	public void zenbakiaAldatuDB(String zenbakia, int erabiltzaileID) {
		Erabiltzaileak erab=em.find(Erabiltzaileak.class, erabiltzaileID);
		erab.setTelefonoZenbakia(zenbakia);
		em.persist(erab);
	}
	
	public Erabiltzaileak profilaLortuDB(String izena) {
		Erabiltzaileak erabiltzailea=(Erabiltzaileak) em.createNamedQuery("Erabiltzaileak.findErabiltzailea").setParameter("izena", izena).getSingleResult();
		return(erabiltzailea);
	}
	
	public void erabiltzaileaBaloratu(Erabiltzaileak erabiltzailea, int idProfila, float balorazioa) {
		int kopurua=0;
		float BBbalorazioa=0.0f;
		Baloratuak baloratuak=new Baloratuak();
		Erabiltzaileak profila=em.find(Erabiltzaileak.class, idProfila);
		float zaharra=0.0f;
		
		zaharra=profila.getBalorazioa();
		kopurua=((Long) em.createNamedQuery("Baloratuak.kopuruaZenbatu").setParameter("baloratua", idProfila).getSingleResult()).intValue();
		BBbalorazioa=((profila.getBalorazioa()*(kopurua+1))+balorazioa)/(kopurua+2);
		profila.setBalorazioa(BBbalorazioa);
		baloratuak.setErabiltzaileak(erabiltzailea);
		baloratuak.setBaloratua(idProfila);
		balorazioaBerrituGeldialdiak(profila, zaharra);
		em.persist(profila);
		em.merge(baloratuak);
	}
	
	@SuppressWarnings("unchecked")
	public int balorazioKodeaLortuDB(Erabiltzaileak erabiltzailea,int idProfila) {
		int kodea=0;
		List<Baloratuak> zerrenda=(List<Baloratuak>) em.createNamedQuery("Baloratuak.findMenpekoak").setParameter("erabiltzaileak", erabiltzailea).getResultList();
		if(zerrenda.isEmpty()==false) {
			for(int i=0;i<zerrenda.size();i++) {
				if(zerrenda.get(i).getBaloratua()==idProfila) {
					kodea=1;
				}
			}
		}
		return kodea;
	}
	
	@SuppressWarnings("unchecked")
	public void balorazioaBerrituGeldialdiak(Erabiltzaileak erabiltzailea,float balorazioZaharra) {
		float balorazioa;
		int kopurua;
		List<Baieztatuak> baieztatuak=em.createNamedQuery("Baieztatuak.findMenpekoak").setParameter("erabiltzaileak", erabiltzailea).getResultList();
		for(int i=0;i<baieztatuak.size();i++) {
			balorazioa=baieztatuak.get(i).getGeldialdiak().getBatazbestekoBalorazioa();
			kopurua=baieztatuak.get(i).getGeldialdiak().getPartehartzaileak();
			balorazioa=((balorazioa*kopurua)-balorazioZaharra+erabiltzailea.getBalorazioa())/kopurua;
			baieztatuak.get(i).getGeldialdiak().setBatazbestekoBalorazioa(balorazioa);
			em.persist(baieztatuak.get(i));
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Mapa> puntuakLortuDB(){
		return em.createNamedQuery("Mapa.findAll").getResultList();
	}
	
	public void kontuaEzabatuDB(Erabiltzaileak erabiltzailea) {
		Erabiltzaileak erab=em.find(Erabiltzaileak.class, erabiltzailea.getIdErabiltzailea());
		em.remove(erab);
	}
}
