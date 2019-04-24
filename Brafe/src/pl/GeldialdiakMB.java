package pl;

import java.io.Serializable;
import java.util.Date;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;



@Named
@SessionScoped
public class GeldialdiakMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int idGeldialdiak;

	private float batazbestekoBalorazioa;

	private String geldialdiIzena;

	private Date iraungiteData;

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
	
}