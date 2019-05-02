package pl;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
//import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class AzpiekitaldiakMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int idAzpiekitaldiak;
	
	private String sortzailea;

	private String bueltatzekoLekua;

	public AzpiekitaldiakMB() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getIdAzpiekitaldiak() {
		return idAzpiekitaldiak;
	}

	public void setIdAzpiekitaldiak(int idAzpiekitaldiak) {
		this.idAzpiekitaldiak = idAzpiekitaldiak;
	}

	public String getBueltatzekoLekua() {
		return bueltatzekoLekua;
	}

	public void setBueltatzekoLekua(String bueltatzekoLekua) {
		this.bueltatzekoLekua = bueltatzekoLekua;
	}

	public String getSortzailea() {
		return sortzailea;
	}

	public void setSortzailea(String sortzailea) {
		this.sortzailea = sortzailea;
	}

}
