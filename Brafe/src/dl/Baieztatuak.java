package dl;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Baieztatuak database table.
 * 
 */
@Entity
@NamedQuery(name="Baieztatuak.findAll", query="SELECT b FROM Baieztatuak b")
public class Baieztatuak implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="BaieztatuakID")
	private int baieztatuakID;

	//bi-directional many-to-one association to Erabiltzaileak
	@ManyToOne
	@JoinColumn(name="ErabiltzaileakID")
	private Erabiltzaileak erabiltzaileak;

	//bi-directional many-to-one association to Geldialdiak
	@ManyToOne
	@JoinColumn(name="Geldialdiak_GeldialdiID")
	private Geldialdiak geldialdiak;

	public Baieztatuak() {
	}

	public int getBaieztatuakID() {
		return this.baieztatuakID;
	}

	public void setBaieztatuakID(int baieztatuakID) {
		this.baieztatuakID = baieztatuakID;
	}

	public Erabiltzaileak getErabiltzaileak() {
		return this.erabiltzaileak;
	}

	public void setErabiltzaileak(Erabiltzaileak erabiltzaileak) {
		this.erabiltzaileak = erabiltzaileak;
	}

	public Geldialdiak getGeldialdiak() {
		return this.geldialdiak;
	}

	public void setGeldialdiak(Geldialdiak geldialdiak) {
		this.geldialdiak = geldialdiak;
	}

}