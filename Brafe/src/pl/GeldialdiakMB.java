package pl;

import java.io.Serializable;
import java.util.Date;

import javax.enterprise.context.RequestScoped;
//import javax.enterprise.context.SessionScoped;
import javax.inject.Named;



@Named
@RequestScoped
public class GeldialdiakMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int idGeldialdiak;

	private float batazbestekoBalorazioa;

	private String geldialdiIzena;

	private Date iraungiteData;
	
	private Date hasieraData;
	
	private int partehartzaileak;
	
	private String ordua;
	
	private String geralekua;

	public GeldialdiakMB() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getIdGeldialdiak() {
		return idGeldialdiak;
	}

	public void setIdGeldialdiak(int idGeldialdiak) {
		this.idGeldialdiak = idGeldialdiak;
	}

	public float getBatazbestekoBalorazioa() {
		return batazbestekoBalorazioa;
	}

	public void setBatazbestekoBalorazioa(float batazbestekoBalorazioa) {
		this.batazbestekoBalorazioa = batazbestekoBalorazioa;
	}

	public String getGeldialdiIzena() {
		return geldialdiIzena;
	}

	public void setGeldialdiIzena(String geldialdiIzena) {
		this.geldialdiIzena = geldialdiIzena;
	}

	public Date getIraungiteData() {
		return iraungiteData;
	}

	public void setIraungiteData(Date iraungiteData) {
		this.iraungiteData = iraungiteData;
	}

	public int getPartehartzaileak() {
		return partehartzaileak;
	}

	public void setPartehartzaileak(int partehartzaileak) {
		this.partehartzaileak = partehartzaileak;
	}

	public String getOrdua() {
		return ordua;
	}

	public void setOrdua(String ordua) {
		this.ordua = ordua;
	}

	public String getGeralekua() {
		return geralekua;
	}

	public void setGeralekua(String geralekua) {
		this.geralekua = geralekua;
	}

	public Date getHasieraData() {
		return hasieraData;
	}

	public void setHasieraData(Date hasieraData) {
		this.hasieraData = hasieraData;
	}
}
