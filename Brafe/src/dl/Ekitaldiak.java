package dl;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the Ekitaldiak database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name="Ekitaldiak.findAll", query="SELECT e FROM Ekitaldiak e"),
	@NamedQuery(name="Ekitaldiak.findEkitaldia", query="SELECT e FROM Ekitaldiak e WHERE e.idEkitaldiak = :idEkitaldiak")
})
public class Ekitaldiak implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idEkitaldiak;

	@Temporal(TemporalType.DATE)
	private Date ekitaldiData;

	private String ekitaldiIzena;

	private String ekitaldiZonaldea;

	private int partaideKopurua;

	private String sortzailea;

	//bi-directional many-to-one association to Azpiekitaldiak
	@OneToMany(mappedBy="ekitaldiak")
	private List<Azpiekitaldiak> azpiekitaldiaks;

	public Ekitaldiak() {
	}

	public int getIdEkitaldiak() {
		return this.idEkitaldiak;
	}

	public void setIdEkitaldiak(int idEkitaldiak) {
		this.idEkitaldiak = idEkitaldiak;
	}

	public Date getEkitaldiData() {
		return this.ekitaldiData;
	}

	public void setEkitaldiData(Date ekitaldiData) {
		this.ekitaldiData = ekitaldiData;
	}

	public String getEkitaldiIzena() {
		return this.ekitaldiIzena;
	}

	public void setEkitaldiIzena(String ekitaldiIzena) {
		this.ekitaldiIzena = ekitaldiIzena;
	}

	public String getEkitaldiZonaldea() {
		return this.ekitaldiZonaldea;
	}

	public void setEkitaldiZonaldea(String ekitaldiZonaldea) {
		this.ekitaldiZonaldea = ekitaldiZonaldea;
	}

	public int getPartaideKopurua() {
		return this.partaideKopurua;
	}

	public void setPartaideKopurua(int partaideKopurua) {
		this.partaideKopurua = partaideKopurua;
	}

	public String getSortzailea() {
		return this.sortzailea;
	}

	public void setSortzailea(String sortzailea) {
		this.sortzailea = sortzailea;
	}

	public List<Azpiekitaldiak> getAzpiekitaldiaks() {
		return this.azpiekitaldiaks;
	}

	public void setAzpiekitaldiaks(List<Azpiekitaldiak> azpiekitaldiaks) {
		this.azpiekitaldiaks = azpiekitaldiaks;
	}

	public Azpiekitaldiak addAzpiekitaldiak(Azpiekitaldiak azpiekitaldiak) {
		getAzpiekitaldiaks().add(azpiekitaldiak);
		azpiekitaldiak.setEkitaldiak(this);

		return azpiekitaldiak;
	}

	public Azpiekitaldiak removeAzpiekitaldiak(Azpiekitaldiak azpiekitaldiak) {
		getAzpiekitaldiaks().remove(azpiekitaldiak);
		azpiekitaldiak.setEkitaldiak(null);

		return azpiekitaldiak;
	}

}