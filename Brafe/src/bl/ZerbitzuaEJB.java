package bl;

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
}
