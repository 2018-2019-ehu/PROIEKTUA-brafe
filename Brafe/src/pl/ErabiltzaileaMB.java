package pl;

import java.io.Serializable;
import java.util.Date;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class ErabiltzaileaMB implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private int erabiltzaileakID;
	private String abizenak;
	private float balorazioa;
	private byte erabiltzaileEgoera;
	private String izena;
	private Date jaioteData;
	private String pasahitza;
	private String telefonoZenbakia;
	private String zonaldea;
	
	public ErabiltzaileaMB() {
		super();
	}
	
	public int getErabiltzaileakID() {
		return erabiltzaileakID;
	}
	public void setErabiltzaileakID(int erabiltzaileakID) {
		this.erabiltzaileakID = erabiltzaileakID;
	}
	public String getAbizenak() {
		return abizenak;
	}
	public void setAbizenak(String abizenak) {
		this.abizenak = abizenak;
	}
	public float getBalorazioa() {
		return balorazioa;
	}
	public void setBalorazioa(float balorazioa) {
		this.balorazioa = balorazioa;
	}
	public byte getErabiltzaileEgoera() {
		return erabiltzaileEgoera;
	}
	public void setErabiltzaileEgoera(byte erabiltzaileEgoera) {
		this.erabiltzaileEgoera = erabiltzaileEgoera;
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
	public String getZonaldea() {
		return zonaldea;
	}
	public void setZonaldea(String zonaldea) {
		this.zonaldea = zonaldea;
	}
	
}
