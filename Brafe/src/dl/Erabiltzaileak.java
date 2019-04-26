package dl;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
//import java.util.List;


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

	private String abizenak;

	private float balorazioa;

	private String helbidea;

	private String izena;

	@Temporal(TemporalType.DATE)
	private Date jaioteData;

	private String pasahitza;

	private String telefonoZenbakia;


	public Erabiltzaileak() {
	}

	public int getIdErabiltzailea() {
		return this.idErabiltzailea;
	}

	public void setIdErabiltzailea(int idErabiltzailea) {
		this.idErabiltzailea = idErabiltzailea;
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
		return telefonoZenbakia;
	}

	public void setTelefonoZenbakia(String telefonoZenbakia) {
		this.telefonoZenbakia = telefonoZenbakia;
	}
}