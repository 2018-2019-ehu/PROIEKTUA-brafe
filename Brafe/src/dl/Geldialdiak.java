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
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idGeldialdiak;

	private float batazbestekoBalorazioa;

	private String geldialdiIzena;

	@Temporal(TemporalType.DATE)
	@Column(name="IraungiteData")
	private Date iraungiteData;

	//bi-directional many-to-one association to Baieztatuak
	@OneToMany(mappedBy="geldialdiak")
	private List<Baieztatuak> baieztatuaks;

	//bi-directional many-to-one association to Azpiekitaldiak
	@ManyToOne
	@JoinColumn(name="Azpiekitaldiak_idAzpiekitaldiak")
	private Azpiekitaldiak azpiekitaldiak;

	public Geldialdiak() {
	}

	public int getIdGeldialdiak() {
		return this.idGeldialdiak;
	}

	public void setIdGeldialdiak(int idGeldialdiak) {
		this.idGeldialdiak = idGeldialdiak;
	}

	public float getBatazbestekoBalorazioa() {
		return this.batazbestekoBalorazioa;
	}

	public void setBatazbestekoBalorazioa(float batazbestekoBalorazioa) {
		this.batazbestekoBalorazioa = batazbestekoBalorazioa;
	}

	public String getGeldialdiIzena() {
		return this.geldialdiIzena;
	}

	public void setGeldialdiIzena(String geldialdiIzena) {
		this.geldialdiIzena = geldialdiIzena;
	}

	public Date getIraungiteData() {
		return this.iraungiteData;
	}

	public void setIraungiteData(Date iraungiteData) {
		this.iraungiteData = iraungiteData;
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