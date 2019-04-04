package dl;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Azpiekitaldiak database table.
 * 
 */
@Entity
@NamedQuery(name="Azpiekitaldiak.findAll", query="SELECT a FROM Azpiekitaldiak a")
public class Azpiekitaldiak implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="AzpiekitaldiID")
	private int azpiekitaldiID;

	@Column(name="AzpiekitaldiIzena")
	private String azpiekitaldiIzena;

	@Column(name="AzpiekitaldiPartaide")
	private int azpiekitaldiPartaide;

	//bi-directional many-to-one association to Ekitaldiak
	@ManyToOne
	@JoinColumn(name="Ekitaldiak_EkitaldiID")
	private Ekitaldiak ekitaldiak;

	//bi-directional many-to-one association to Geldialdiak
	@OneToMany(mappedBy="azpiekitaldiak")
	private List<Geldialdiak> geldialdiaks;

	public Azpiekitaldiak() {
	}

	public int getAzpiekitaldiID() {
		return this.azpiekitaldiID;
	}

	public void setAzpiekitaldiID(int azpiekitaldiID) {
		this.azpiekitaldiID = azpiekitaldiID;
	}

	public String getAzpiekitaldiIzena() {
		return this.azpiekitaldiIzena;
	}

	public void setAzpiekitaldiIzena(String azpiekitaldiIzena) {
		this.azpiekitaldiIzena = azpiekitaldiIzena;
	}

	public int getAzpiekitaldiPartaide() {
		return this.azpiekitaldiPartaide;
	}

	public void setAzpiekitaldiPartaide(int azpiekitaldiPartaide) {
		this.azpiekitaldiPartaide = azpiekitaldiPartaide;
	}

	public Ekitaldiak getEkitaldiak() {
		return this.ekitaldiak;
	}

	public void setEkitaldiak(Ekitaldiak ekitaldiak) {
		this.ekitaldiak = ekitaldiak;
	}

	public List<Geldialdiak> getGeldialdiaks() {
		return this.geldialdiaks;
	}

	public void setGeldialdiaks(List<Geldialdiak> geldialdiaks) {
		this.geldialdiaks = geldialdiaks;
	}

	public Geldialdiak addGeldialdiak(Geldialdiak geldialdiak) {
		getGeldialdiaks().add(geldialdiak);
		geldialdiak.setAzpiekitaldiak(this);

		return geldialdiak;
	}

	public Geldialdiak removeGeldialdiak(Geldialdiak geldialdiak) {
		getGeldialdiaks().remove(geldialdiak);
		geldialdiak.setAzpiekitaldiak(null);

		return geldialdiak;
	}

}