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
@NamedQuery(name="Ekitaldiak.findAll", query="SELECT e FROM Ekitaldiak e")
public class Ekitaldiak implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="EkitaldiID")
	private int ekitaldiID;

	@Temporal(TemporalType.DATE)
	@Column(name="EkitaldiData")
	private Date ekitaldiData;

	@Column(name="EkitaldiIzena")
	private String ekitaldiIzena;

	@Column(name="EkitaldiSortzailea")
	private String ekitaldiSortzailea;

	@Column(name="Kokapena")
	private String kokapena;

	@Column(name="Partaideak")
	private int partaideak;

	//bi-directional many-to-one association to Azpiekitaldiak
	@OneToMany(mappedBy="ekitaldiak")
	private List<Azpiekitaldiak> azpiekitaldiaks;

	public Ekitaldiak() {
	}

	public int getEkitaldiID() {
		return this.ekitaldiID;
	}

	public void setEkitaldiID(int ekitaldiID) {
		this.ekitaldiID = ekitaldiID;
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

	public String getEkitaldiSortzailea() {
		return this.ekitaldiSortzailea;
	}

	public void setEkitaldiSortzailea(String ekitaldiSortzailea) {
		this.ekitaldiSortzailea = ekitaldiSortzailea;
	}

	public String getKokapena() {
		return this.kokapena;
	}

	public void setKokapena(String kokapena) {
		this.kokapena = kokapena;
	}

	public int getPartaideak() {
		return this.partaideak;
	}

	public void setPartaideak(int partaideak) {
		this.partaideak = partaideak;
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