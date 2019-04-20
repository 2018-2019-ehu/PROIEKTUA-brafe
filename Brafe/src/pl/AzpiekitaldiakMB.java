package pl;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class AzpiekitaldiakMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int idAzpiekitaldiak;

	private int aPartaideKopurua;

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

	public int getaPartaideKopurua() {
		return aPartaideKopurua;
	}

	public void setaPartaideKopurua(int aPartaideKopurua) {
		this.aPartaideKopurua = aPartaideKopurua;
	}

	public String getBueltatzekoLekua() {
		return bueltatzekoLekua;
	}

	public void setBueltatzekoLekua(String bueltatzekoLekua) {
		this.bueltatzekoLekua = bueltatzekoLekua;
	}

}
