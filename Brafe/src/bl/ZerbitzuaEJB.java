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


	public void erabiltzaileaErregistratuDB(Erabiltzaileak erab) {
		em.merge(erab);
	}
	
	public int erabiltzaileaLogeatu(String izena,String pasahitza) {
		int kasua=0;
		boolean bilaketa=false;
		Erabiltzaileak erabiltzailea= new Erabiltzaileak();
		
		try {
			erabiltzailea=(Erabiltzaileak)em.createNamedQuery("Erabiltzaileak.findErabiltzailea").setParameter("izena", izena).getSingleResult();
		}catch(javax.persistence.NoResultException exception) {
			bilaketa=true;
		}
		if(bilaketa==true) {
			if(pasahitza!=null) {
				kasua=4;
			}
			else {
				kasua=1;	
			}
		}
		else {
			if(!erabiltzailea.getPasahitza().equals(pasahitza)) {
				kasua=2;
			}
			else {
				kasua=3;
			}
		}
		return kasua;
	}
	
	public Erabiltzaileak loginDatuakLortu(String izena) {
		return (Erabiltzaileak)em.createNamedQuery("Erabiltzaileak.findErabiltzailea").setParameter("izena", izena).getSingleResult();
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
	
	public Ekitaldiak ekitaldiaLortu(int idEkitaldia) {
		return em.find(Ekitaldiak.class, idEkitaldia);
	}
	
	public Azpiekitaldiak azpiekitaldiaLortu(int idAzpiekitaldia) {
		return em.find(Azpiekitaldiak.class, idAzpiekitaldia);
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
	public void azpiekitaldiaSortu(Azpiekitaldiak azpiekitaldia) {
		List<Azpiekitaldiak> azpiekitaldiGuztiak= em.createNamedQuery("Azpiekitaldiak.findAll").getResultList();
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
	
	public void ekitaldiaEzabatuDB(int idEkitaldia) {
		Ekitaldiak ekitaldia=em.find(Ekitaldiak.class, idEkitaldia);
		if(ekitaldia!=null) {
			em.remove(ekitaldia);
		}
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
	public void zenbakiaAldatuDB(int zenbakia, int erabiltzaileID) {
		Erabiltzaileak erab=em.find(Erabiltzaileak.class, erabiltzaileID);
		erab.setTelefonoZenbakia(zenbakia);
		em.persist(erab);
	}
}
