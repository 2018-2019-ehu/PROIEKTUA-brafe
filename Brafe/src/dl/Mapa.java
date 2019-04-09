package dl;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Mapa database table.
 * 
 */
@Entity
@NamedQuery(name="Mapa.findAll", query="SELECT m FROM Mapa m")
public class Mapa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idBrafePuntua;

	private float latitudea;

	private float longitudea;

	private String puntuarenIzena;

	public Mapa() {
	}

	public int getIdBrafePuntua() {
		return this.idBrafePuntua;
	}

	public void setIdBrafePuntua(int idBrafePuntua) {
		this.idBrafePuntua = idBrafePuntua;
	}

	public float getLatitudea() {
		return this.latitudea;
	}

	public void setLatitudea(float latitudea) {
		this.latitudea = latitudea;
	}

	public float getLongitudea() {
		return this.longitudea;
	}

	public void setLongitudea(float longitudea) {
		this.longitudea = longitudea;
	}

	public String getPuntuarenIzena() {
		return this.puntuarenIzena;
	}

	public void setPuntuarenIzena(String puntuarenIzena) {
		this.puntuarenIzena = puntuarenIzena;
	}

}