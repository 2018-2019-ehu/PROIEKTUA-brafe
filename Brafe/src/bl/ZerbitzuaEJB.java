package bl;

import java.util.List;

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
	public List<Erabiltzaileak> erabiltzaileakLortuDB()
	{
		List<Erabiltzaileak> erabGuztiak;
		erabGuztiak=(List<Erabiltzaileak>)em.createNamedQuery("Erabiltzaileak.findAll").getResultList();
		return erabGuztiak;
	}

	//@SuppressWarnings("unchecked")
	public Erabiltzaileak erabiltzaileBakarraLortuDB(String erabIzena)
	{
		Erabiltzaileak erabiltzaile;
		erabiltzaile=(Erabiltzaileak)em.createNamedQuery("Erabiltzaileak.findErabiltzaile").setParameter("izena", erabIzena).getSingleResult();
		return erabiltzaile;
	}
	
	public boolean logeatu(String erabIzena, String pasahitza) {
		boolean baimena=false;
		Erabiltzaileak erabiltzaile;
		erabiltzaile=(Erabiltzaileak)em.createNamedQuery("Erabiltzaileak.findErabiltzaile").setParameter("izena", erabIzena).getSingleResult();
		if(pasahitza.equals(erabiltzaile.getPasahitza())) {
			baimena=true;
		}
		return baimena;
	}
	
	public void erabiltzaileaErregistratuDB(Erabiltzaileak erab) {
		em.merge(erab);
	}
	
	/*
	@SuppressWarnings("unchecked")
	public List<Ekitaldiak> ekitaldiakBilatu(String ekitaldiIzena){
		List<Ekitaldiak> ekitaldiak;
		List<Ekitaldiak> iragazita=new List<Ekitaldiak>();
		ekitaldiak=(List<Ekitaldiak>)em.createNamedQuery("Ekitaldiak.findAll").getResultList();
		for(int i=0;i<ekitaldiak.size();i++) {
			if(ekitaldiak.get(i).getEkitaldiIzena().contains(ekitaldiIzena)) {
				iragazita.add(ekitaldiak.get(i));
			}
		}
		return iragazita;
	}
	
	@SuppressWarnings("unchecked")
	public List<Azpiekitaldiak> azpiEkitaldiakBilatu(String azpiekitaldiIzena){
		List<Azpiekitaldiak> azpiekitaldiak;
		List<Azpiekitaldiak> iragazita=null;
		azpiekitaldiak=(List<Azpiekitaldiak>)em.createNamedQuery("Ekitaldiak.findAll").getResultList();
		for(int i=0;i<azpiekitaldiak.size();i++) {
			if(azpiekitaldiak.get(i).getAzpiekitaldiIzena().contains(azpiekitaldiIzena)) {
				iragazita.add(azpiekitaldiak.get(i));
			}
		}
		return iragazita;
	}
	*/
}
