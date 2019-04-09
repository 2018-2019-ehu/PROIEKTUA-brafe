package bl;

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
	
}
