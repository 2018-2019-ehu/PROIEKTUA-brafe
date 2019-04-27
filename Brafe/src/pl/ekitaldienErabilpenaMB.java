package pl;

import java.io.Serializable;
import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import bl.ZerbitzuaEJB;
import dl.Azpiekitaldiak;
import dl.Baieztatuak;
import dl.Ekitaldiak;
import dl.Erabiltzaileak;
import dl.Geldialdiak;

@Named
@SessionScoped
public class ekitaldienErabilpenaMB implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Ekitaldiak ekitaldia=new Ekitaldiak();
	private List<Ekitaldiak> ekitaldiGuztiak=new ArrayList<Ekitaldiak>();
	private List<Azpiekitaldiak> azpiekitaldiGuztiak=new ArrayList<Azpiekitaldiak>();
	private List<Geldialdiak> geldialdiGuztiak=new ArrayList<Geldialdiak>();
	private Azpiekitaldiak azpiekitaldia= new Azpiekitaldiak();
	private Geldialdiak geldialdia=new Geldialdiak();
	private String iragazitakoa=null;
	private int render=0;
	private String autentikatutakoa;
	private Erabiltzaileak erabiltzailea=new Erabiltzaileak();
	private int kodea=0;

	
	@EJB
	private ZerbitzuaEJB zEJB;
	
	public ekitaldienErabilpenaMB() {
		super();
	}
	
	public void loginIzenaLortu(String login) {
			autentikatutakoa=login;
			erabiltzailea=zEJB.loginDatuakLortu(login);
	}
	
	public List<Ekitaldiak> ekitaldiakIragazi(){
		List<Ekitaldiak> ekitaldiak;
		if(iragazitakoa==null) {
			ekitaldiGuztiak=zEJB.ekitaldiGuztiakLortu();
			ekitaldiak=ekitaldiGuztiak;
		}
		else{
			ekitaldiGuztiak=zEJB.ekitaldiakIragaziDB(iragazitakoa);
			ekitaldiak=ekitaldiGuztiak;
		}
		return(ekitaldiak);
	}
	
	public List<Azpiekitaldiak> azpiekitaldiakIragazi(){
		List<Azpiekitaldiak> azpiekitaldiak;
		if(iragazitakoa==null) {
			azpiekitaldiGuztiak=zEJB.azpiekitaldiGuztiakLortu(ekitaldia);
			azpiekitaldiak=azpiekitaldiGuztiak;
		}
		else{
			azpiekitaldiGuztiak=zEJB.azpiekitaldiakIragaziDB(iragazitakoa,ekitaldia);
			azpiekitaldiak=azpiekitaldiGuztiak;
		}
		return(azpiekitaldiak);
	}
	
	public List<Geldialdiak> geldialdiakIragazi(){
		List<Geldialdiak> geldialdiak;
		if(iragazitakoa==null) {
			geldialdiGuztiak=zEJB.geldialdiGuztiakLortu(azpiekitaldia);
			geldialdiak=geldialdiGuztiak;
		}
		else{
			geldialdiGuztiak=zEJB.geldialdiakIragaziDB(iragazitakoa, azpiekitaldia);
			geldialdiak=geldialdiGuztiak;
		}
		return(geldialdiak);
	}
	
	public void AzpiekitaldiakLortu(int idEkitaldi) {
		Ekitaldiak ekitaldiak=zEJB.ekitaldiaLortu(idEkitaldi);
		azpiekitaldiGuztiak=zEJB.azpiekitaldiGuztiakLortu(ekitaldiak);
		render=0;
		ekitaldia=ekitaldiak;
	}
	
	public void GeldialdiakLortu(int idAzpiekitaldi) {
		Azpiekitaldiak azpiekitaldiak=zEJB.azpiekitaldiaLortu(idAzpiekitaldi);
		geldialdiGuztiak=zEJB.geldialdiGuztiakLortu(azpiekitaldiak);
		render=0;
		azpiekitaldia=azpiekitaldiak;
	}
	
	public void PartaideakLortu(int idGeldialdi) {
		Geldialdiak geldialdi=zEJB.geldialdiaLortu(idGeldialdi);
		geldialdia=geldialdi;
	}
	
	public void GeldialdianSartu() {
		Erabiltzaileak erabiltzailea=zEJB.loginDatuakLortu(autentikatutakoa);
		Baieztatuak baieztatua=new Baieztatuak();
		baieztatua.setErabiltzaileak(erabiltzailea);
		baieztatua.setGeldialdiak(geldialdia);
		zEJB.geldialdianSartuDB(baieztatua, geldialdia);
	}
	
	public List<Baieztatuak> baieztatuakLortu() {
		return zEJB.baieztatuakLortuDB(geldialdia);
	}
	
	public void GeldialditikAtera() {
		zEJB.geldialditikAteraDB(erabiltzailea, geldialdia.getIdGeldialdiak());
	}
	
	public Ekitaldiak getEkitaldia() {
		return ekitaldia;
	}

	public void setEkitaldia(Ekitaldiak ekitaldia) {
		this.ekitaldia = ekitaldia;
	}

	public List<Ekitaldiak> getEkitaldiGuztiak() {
		return ekitaldiGuztiak;
	}

	public void setEkitaldiGuztiak(List<Ekitaldiak> ekitaldiGuztiak) {
		this.ekitaldiGuztiak = ekitaldiGuztiak;
	}

	public String getIragazitakoa() {
		return iragazitakoa;
	}

	public void setIragazitakoa(String iragazitakoa) {
		this.iragazitakoa = iragazitakoa;
	}
	
	public void ekitaldiBerriaSortu(EkitaldiakMB eMB) {
		Ekitaldiak ekitaldia= new Ekitaldiak();
		Date data=new Date();
		Date amaiera=new Date();
		Calendar c= Calendar.getInstance();
		c.setTime(data);
		c.add(Calendar.DATE, 7);
		amaiera=c.getTime();
		ekitaldia.setEkitaldiData(data);
		ekitaldia.setEkitaldiIzena(eMB.getEkitaldiIzena());
		ekitaldia.setEkitaldiZonaldea(eMB.getEkitaldiZonaldea());
		ekitaldia.setPartaideKopurua(2);
		ekitaldia.setSortzailea(autentikatutakoa);
		ekitaldia.setAmaieraData(amaiera);
		zEJB.ekitaldiaSortu(ekitaldia);
		render=0;
	}
	
	public void azpiekitaldiBerriaSortu(AzpiekitaldiakMB aMB) {
		Azpiekitaldiak azpiekitaldiak=new Azpiekitaldiak();
		azpiekitaldiak.setEkitaldiak(ekitaldia);
		azpiekitaldiak.setSortzailea(autentikatutakoa);
		System.out.println(autentikatutakoa);
		azpiekitaldiak.setBueltatzekoLekua(aMB.getBueltatzekoLekua());
		zEJB.azpiekitaldiaSortu(azpiekitaldiak);
		render=0;
	}
	
	public void geldialdiBerriaSortu(GeldialdiakMB gMB) {
		Date data=new Date();
		Date amaiera=new Date();
		Calendar c= Calendar.getInstance();
		c.setTime(data);
		c.add(Calendar.DATE, 1);
		amaiera=c.getTime();
		Geldialdiak geldialdiak=new Geldialdiak();
		geldialdiak.setAzpiekitaldiak(azpiekitaldia);
		geldialdiak.setGeldialdiIzena(gMB.getGeldialdiIzena());
		geldialdiak.setBatazbestekoBalorazioa(0);
		geldialdiak.setIraungiteData(amaiera);
		geldialdiak.setGeralekua(gMB.getGeralekua());
		geldialdiak.setOrdua(gMB.getOrdua());
		geldialdiak.setPartehartzaileak(0);
		geldialdiak.setSortzailea(autentikatutakoa);
		zEJB.geldialdiaSortu(geldialdiak);
		render=0;
	}

	public void ekitaldiaEzabatu(int idEkitaldia) {
		kodea=zEJB.ekitaldiaEzabatuDB(idEkitaldia);
	}
	public int getRender() {
		return render;
	}

	public void setRender(int render) {
		this.render = render;
	}
	
	public void renderHasieratu() {
		render=1;
	}
	
	public void Clean() {
		render=0;
		iragazitakoa=null;
	}

	public String getAutentikatutakoa() {
		return autentikatutakoa;
	}

	public void setAutentikatutakoa(String autentikatutakoa) {
		this.autentikatutakoa = autentikatutakoa;
	}

	public int getKodea() {
		return kodea;
	}

	public void setKodea(int kodea) {
		this.kodea = kodea;
	}
	
}
