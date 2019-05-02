package bl;

import java.util.ArrayList;
import java.util.List;

//import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
		return em.createNamedQuery("Ekitaldiak.findAll").getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Ekitaldiak> ekitaldiakIragaziDB(String izena){
		List<Ekitaldiak> ekitaldiak=em.createNamedQuery("Ekitaldiak.findAll").getResultList();
		List<Ekitaldiak> iragaziak=new ArrayList<Ekitaldiak>();
		
		for(int i=0;i<ekitaldiak.size();i++) {
			if(ekitaldiak.get(i).getEkitaldiIzena().contains(izena)) {
				iragaziak.add(ekitaldiak.get(i));
			}
		}
		return iragaziak;
	}
	
	@SuppressWarnings("unchecked")
	public List<Azpiekitaldiak> azpiekitaldiakIragaziDB(String izena,Ekitaldiak ekitaldia){
		List<Azpiekitaldiak> azpiekitaldiak=(List<Azpiekitaldiak>)em.createNamedQuery("Azpiekitaldiak.findMenpekoak").setParameter("ekitaldiak", ekitaldia).getResultList();
		List<Azpiekitaldiak> iragaziak=new ArrayList<Azpiekitaldiak>();
		
		for(int i=0;i<azpiekitaldiak.size();i++) {
			if(azpiekitaldiak.get(i).getBueltatzekoLekua().contains(izena)) {
				iragaziak.add(azpiekitaldiak.get(i));
			}
		}
		return iragaziak;
	}
	
	@SuppressWarnings("unchecked")
	public List<Geldialdiak> geldialdiakIragaziDB(String izena,Azpiekitaldiak azpiekitaldia){
		List<Geldialdiak> geldialdiak=(List<Geldialdiak>)em.createNamedQuery("Geldialdiak.findMenpekoak").setParameter("azpiekitaldiak", azpiekitaldia).getResultList();
		List<Geldialdiak> iragaziak=new ArrayList<Geldialdiak>();
		
		for(int i=0;i<geldialdiak.size();i++) {
			if(geldialdiak.get(i).getGeldialdiIzena().contains(izena)) {
				iragaziak.add(geldialdiak.get(i));
			}
		}
		return iragaziak;
	}
	
	@SuppressWarnings("unchecked")
	public List<Azpiekitaldiak> azpiekitaldiGuztiakLortu(Ekitaldiak ekitaldia){
		return (List<Azpiekitaldiak>)em.createNamedQuery("Azpiekitaldiak.findMenpekoak").setParameter("ekitaldiak", ekitaldia).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Geldialdiak> geldialdiGuztiakLortu(Azpiekitaldiak azpiekitaldia){
		return (List<Geldialdiak>)em.createNamedQuery("Geldialdiak.findMenpekoak").setParameter("azpiekitaldiak", azpiekitaldia).getResultList();
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
	public void geldialdianSartuDB(Baieztatuak baieztatua, Geldialdiak geldialdia) {
		Geldialdiak geldialdi=em.find(Geldialdiak.class, geldialdia.getIdGeldialdiak());
		boolean egoera=false;
		List<Baieztatuak> baieztatuak=(List<Baieztatuak>)em.createNamedQuery("Baieztatuak.findGeldialdi").setParameter("geldialdiak", geldialdia).getResultList();
		for(int i=0;i<baieztatuak.size();i++) {
			if(baieztatuak.get(i).getErabiltzaileak().getIzena().equals(baieztatua.getErabiltzaileak().getIzena())){
				egoera=true;
			}
		}
		if(egoera==false) {
			float batazbestekoa=((geldialdi.getBatazbestekoBalorazioa()*geldialdi.getPartehartzaileak())+baieztatua.getErabiltzaileak().getBalorazioa())/(geldialdi.getPartehartzaileak()+1);
			geldialdi.setPartehartzaileak(geldialdi.getPartehartzaileak()+1);
			geldialdi.setBatazbestekoBalorazioa(batazbestekoa);
			em.persist(geldialdi);
			em.merge(baieztatua);
		}
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
	
	public void azpiekitaldiaSortu(Azpiekitaldiak azpiekitaldia) {
		em.merge(azpiekitaldia);
	}
	
	@SuppressWarnings("unchecked")
	public void geldialdiaSortu(Geldialdiak geldialdia) {
		List<Geldialdiak> geldialdiGuztiak= em.createNamedQuery("Geldialdiak.findAll").getResultList();
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
		
	}
}
