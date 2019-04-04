package dl;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the Erabiltzaileak database table.
 * 
 */
@Entity
@NamedQuery(name="Erabiltzaileak.findAll", query="SELECT e FROM Erabiltzaileak e")
@NamedQuery(name="Erabiltzaileak.findErabiltzaile",query="SELECT e FROM Erabiltzaileak e WHERE e.izena = :izena")
public class Erabiltzaileak implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ErabiltzaileakID")
	private int erabiltzaileakID;

	@Column(name="Abizenak")
	private String abizenak;

	@Column(name="Balorazioa")
	private float balorazioa;

	@Column(name="Izena")
	private String izena;

	@Temporal(TemporalType.DATE)
	@Column(name="JaioteData")
	private Date jaioteData;

	@Column(name="Pasahitza")
	private String pasahitza;

	@Column(name="TelefonoZenbakia")
	private String telefonoZenbakia;

	@Column(name="Zonaldea")
	private String zonaldea;

	//bi-directional many-to-one association to Baieztatuak
	@OneToMany(mappedBy="erabiltzaileak")
	private List<Baieztatuak> baieztatuaks;

	public Erabiltzaileak() {
	}

	public int getErabiltzaileakID() {
		return this.erabiltzaileakID;
	}

	public void setErabiltzaileakID(int erabiltzaileakID) {
		this.erabiltzaileakID = erabiltzaileakID;
	}

	public String getAbizenak() {
		return this.abizenak;
	}

	public void setAbizenak(String abizenak) {
		this.abizenak = abizenak;
	}

	public float getBalorazioa() {
		return this.balorazioa;
	}

	public void setBalorazioa(float balorazioa) {
		this.balorazioa = balorazioa;
	}

	public String getIzena() {
		return this.izena;
	}

	public void setIzena(String izena) {
		this.izena = izena;
	}

	public Date getJaioteData() {
		return this.jaioteData;
	}

	public void setJaioteData(Date jaioteData) {
		this.jaioteData = jaioteData;
	}

	public String getPasahitza() {
		return this.pasahitza;
	}

	public void setPasahitza(String pasahitza) {
		this.pasahitza = pasahitza;
	}

	public String getTelefonoZenbakia() {
		return this.telefonoZenbakia;
	}

	public void setTelefonoZenbakia(String telefonoZenbakia) {
		this.telefonoZenbakia = telefonoZenbakia;
	}

	public String getZonaldea() {
		return this.zonaldea;
	}

	public void setZonaldea(String zonaldea) {
		this.zonaldea = zonaldea;
	}

	public List<Baieztatuak> getBaieztatuaks() {
		return this.baieztatuaks;
	}

	public void setBaieztatuaks(List<Baieztatuak> baieztatuaks) {
		this.baieztatuaks = baieztatuaks;
	}

	public Baieztatuak addBaieztatuak(Baieztatuak baieztatuak) {
		getBaieztatuaks().add(baieztatuak);
		baieztatuak.setErabiltzaileak(this);

		return baieztatuak;
	}

	public Baieztatuak removeBaieztatuak(Baieztatuak baieztatuak) {
		getBaieztatuaks().remove(baieztatuak);
		baieztatuak.setErabiltzaileak(null);

		return baieztatuak;
	}

}