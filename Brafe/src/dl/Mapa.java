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
	private int idMapa;

	@Column(name="EstablezimenduIzena")
	private String establezimenduIzena;

	@Column(name="Latitudea")
	private float latitudea;

	@Column(name="Logitudea")
	private float logitudea;

	public Mapa() {
	}

	public int getIdMapa() {
		return this.idMapa;
	}

	public void setIdMapa(int idMapa) {
		this.idMapa = idMapa;
	}

	public String getEstablezimenduIzena() {
		return this.establezimenduIzena;
	}

	public void setEstablezimenduIzena(String establezimenduIzena) {
		this.establezimenduIzena = establezimenduIzena;
	}

	public float getLatitudea() {
		return this.latitudea;
	}

	public void setLatitudea(float latitudea) {
		this.latitudea = latitudea;
	}

	public float getLogitudea() {
		return this.logitudea;
	}

	public void setLogitudea(float logitudea) {
		this.logitudea = logitudea;
	}

}