package dl;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Baloratuak database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name="Baloratuak.findAll", query="SELECT b FROM Baloratuak b"),
	@NamedQuery(name="Baloratuak.findMenpekoak", query="SELECT b FROM Baloratuak b WHERE b.erabiltzaileak = :erabiltzaileak"),
	@NamedQuery(name="Baloratuak.kopuruaZenbatu", query="SELECT Count(b) FROM Baloratuak b WHERE b.baloratua = :baloratua")
})

public class Baloratuak implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idBaloratuak;

	private int baloratua;

	//bi-directional many-to-one association to Erabiltzaileak
	@ManyToOne
	@JoinColumn(name="erabiltzailea")
	private Erabiltzaileak erabiltzaileak;

	public Baloratuak() {
	}

	public int getIdBaloratuak() {
		return this.idBaloratuak;
	}

	public void setIdBaloratuak(int idBaloratuak) {
		this.idBaloratuak = idBaloratuak;
	}

	public int getBaloratua() {
		return this.baloratua;
	}

	public void setBaloratua(int baloratua) {
		this.baloratua = baloratua;
	}

	public Erabiltzaileak getErabiltzaileak() {
		return this.erabiltzaileak;
	}

	public void setErabiltzaileak(Erabiltzaileak erabiltzaileak) {
		this.erabiltzaileak = erabiltzaileak;
	}

}