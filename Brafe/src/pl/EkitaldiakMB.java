package pl;

import java.io.Serializable;
import java.util.Date;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class EkitaldiakMB implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int idEkitaldiak;
	private Date ekitaldiData;
	private String ekitaldiIzena;
	private String ekitaldiZonaldea;
	private int partaideKopurua;
	private String sortzailea;
	private String iragazitakoa;
	public EkitaldiakMB() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public int getIdEkitaldiak() {
		return idEkitaldiak;
	}
	public void setIdEkitaldiak(int idEkitaldiak) {
		this.idEkitaldiak = idEkitaldiak;
	}
	public Date getEkitaldiData() {
		return ekitaldiData;
	}
	public void setEkitaldiData(Date ekitaldiData) {
		this.ekitaldiData = ekitaldiData;
	}
	public String getEkitaldiIzena() {
		return ekitaldiIzena;
	}
	public void setEkitaldiIzena(String ekitaldiIzena) {
		this.ekitaldiIzena = ekitaldiIzena;
	}
	public String getEkitaldiZonaldea() {
		return ekitaldiZonaldea;
	}
	public void setEkitaldiZonaldea(String ekitaldiZonaldea) {
		this.ekitaldiZonaldea = ekitaldiZonaldea;
	}
	public int getPartaideKopurua() {
		return partaideKopurua;
	}
	public void setPartaideKopurua(int partaideKopurua) {
		this.partaideKopurua = partaideKopurua;
	}
	public String getSortzailea() {
		return sortzailea;
	}
	public void setSortzailea(String sortzailea) {
		this.sortzailea = sortzailea;
	}
	public String getIragazitakoa() {
		return iragazitakoa;
	}
	public void setIragazitakoa(String iragazitakoa) {
		this.iragazitakoa = iragazitakoa;
	}
	
}
