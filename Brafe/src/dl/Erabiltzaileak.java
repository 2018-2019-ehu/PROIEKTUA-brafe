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
@NamedQueries({
	@NamedQuery(name="Erabiltzaileak.findAll", query="SELECT e FROM Erabiltzaileak e"),
	@NamedQuery(name="Erabiltzaileak.findErabiltzailea", query="SELECT e FROM Erabiltzaileak e WHERE e.izena = :izena")
})
public class Erabiltzaileak implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idErabiltzailea;

	private float balorazioa;

	private String helbidea;

	private String izena;

	@Temporal(TemporalType.DATE)
	private Date jaioteData;

	private String pasahitza;

	private String telefonoZenbakia;

	//bi-directional many-to-one association to Baieztatuak
	@OneToMany(mappedBy="erabiltzaileak")
	private List<Baieztatuak> baieztatuaks;

	//bi-directional many-to-one association to Baloratuak
	@OneToMany(mappedBy="erabiltzaileak")
	private List<Baloratuak> baloratuaks;

	public Erabiltzaileak() {
	}

	public int getIdErabiltzailea() {
		return this.idErabiltzailea;
	}

	public void setIdErabiltzailea(int idErabiltzailea) {
		this.idErabiltzailea = idErabiltzailea;
	}

	public float getBalorazioa() {
		return this.balorazioa;
	}

	public void setBalorazioa(float balorazioa) {
		this.balorazioa = balorazioa;
	}

	public String getHelbidea() {
		return this.helbidea;
	}

	public void setHelbidea(String helbidea) {
		this.helbidea = helbidea;
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

	public List<Baloratuak> getBaloratuaks() {
		return this.baloratuaks;
	}

	public void setBaloratuaks(List<Baloratuak> baloratuaks) {
		this.baloratuaks = baloratuaks;
	}

	public Baloratuak addBaloratuak(Baloratuak baloratuak) {
		getBaloratuaks().add(baloratuak);
		baloratuak.setErabiltzaileak(this);

		return baloratuak;
	}

	public Baloratuak removeBaloratuak(Baloratuak baloratuak) {
		getBaloratuaks().remove(baloratuak);
		baloratuak.setErabiltzaileak(null);

		return baloratuak;
	}

}