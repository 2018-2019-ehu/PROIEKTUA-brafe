package dl;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the Geldialdiak database table.
 * 
 */
@Entity
@NamedQuery(name="Geldialdiak.findAll", query="SELECT g FROM Geldialdiak g")
public class Geldialdiak implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="GeldialdiID")
	private int geldialdiID;

	@Column(name="BatazbestekoBalorazioa")
	private int batazbestekoBalorazioa;

	@Temporal(TemporalType.DATE)
	@Column(name="GeldialdiIraungiteData")
	private Date geldialdiIraungiteData;

	@Column(name="GeldialdiIzena")
	private String geldialdiIzena;

	@Column(name="Geralekua")
	private String geralekua;

	//bi-directional many-to-one association to Baieztatuak
	@OneToMany(mappedBy="geldialdiak")
	private List<Baieztatuak> baieztatuaks;

	//bi-directional many-to-one association to Azpiekitaldiak
	@ManyToOne
	@JoinColumn(name="Azpiekitaldiak_AzpiekitaldiID")
	private Azpiekitaldiak azpiekitaldiak;

	public Geldialdiak() {
	}

	public int getGeldialdiID() {
		return this.geldialdiID;
	}

	public void setGeldialdiID(int geldialdiID) {
		this.geldialdiID = geldialdiID;
	}

	public int getBatazbestekoBalorazioa() {
		return this.batazbestekoBalorazioa;
	}

	public void setBatazbestekoBalorazioa(int batazbestekoBalorazioa) {
		this.batazbestekoBalorazioa = batazbestekoBalorazioa;
	}

	public Date getGeldialdiIraungiteData() {
		return this.geldialdiIraungiteData;
	}

	public void setGeldialdiIraungiteData(Date geldialdiIraungiteData) {
		this.geldialdiIraungiteData = geldialdiIraungiteData;
	}

	public String getGeldialdiIzena() {
		return this.geldialdiIzena;
	}

	public void setGeldialdiIzena(String geldialdiIzena) {
		this.geldialdiIzena = geldialdiIzena;
	}

	public String getGeralekua() {
		return this.geralekua;
	}

	public void setGeralekua(String geralekua) {
		this.geralekua = geralekua;
	}

	public List<Baieztatuak> getBaieztatuaks() {
		return this.baieztatuaks;
	}

	public void setBaieztatuaks(List<Baieztatuak> baieztatuaks) {
		this.baieztatuaks = baieztatuaks;
	}

	public Baieztatuak addBaieztatuak(Baieztatuak baieztatuak) {
		getBaieztatuaks().add(baieztatuak);
		baieztatuak.setGeldialdiak(this);

		return baieztatuak;
	}

	public Baieztatuak removeBaieztatuak(Baieztatuak baieztatuak) {
		getBaieztatuaks().remove(baieztatuak);
		baieztatuak.setGeldialdiak(null);

		return baieztatuak;
	}

	public Azpiekitaldiak getAzpiekitaldiak() {
		return this.azpiekitaldiak;
	}

	public void setAzpiekitaldiak(Azpiekitaldiak azpiekitaldiak) {
		this.azpiekitaldiak = azpiekitaldiak;
	}

}