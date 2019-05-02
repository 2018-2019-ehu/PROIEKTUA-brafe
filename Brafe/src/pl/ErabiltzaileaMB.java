package pl;

import java.io.Serializable;
import java.util.Date;

import javax.enterprise.context.RequestScoped;
//import javax.enterprise.context.RequestScoped;
//import javax.enterprise.context.RequestScoped;
//import javax.enterprise.context.SessionScoped;
//import javax.enterprise.context.ApplicationScoped;
//import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
//import javax.persistence.Temporal;
//import javax.persistence.TemporalType;

@Named
@RequestScoped
public class ErabiltzaileaMB implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int idErabiltzailea;
	private float balorazioa;
	private String helbidea;
	private String izena;
	private Date jaioteData;
	private String pasahitza;
	private String telefonoZenbakia;
	public ErabiltzaileaMB() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getIdErabiltzailea() {
		return idErabiltzailea;
	}
	public void setIdErabiltzailea(int idErabiltzailea) {
		this.idErabiltzailea = idErabiltzailea;
	}
	public float getBalorazioa() {
		return balorazioa;
	}
	public void setBalorazioa(float balorazioa) {
		this.balorazioa = balorazioa;
	}
	public String getHelbidea() {
		return helbidea;
	}
	public void setHelbidea(String helbidea) {
		this.helbidea = helbidea;
	}
	public String getIzena() {
		return izena;
	}
	public void setIzena(String izena) {
		this.izena = izena;
	}
	public Date getJaioteData() {
		return jaioteData;
	}
	public void setJaioteData(Date jaioteData) {
		this.jaioteData = jaioteData;
	}
	public String getPasahitza() {
		return pasahitza;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
