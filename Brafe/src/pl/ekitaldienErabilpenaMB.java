package pl;

import java.io.IOException;
//import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
//import javax.faces.context.FacesContext;
import javax.inject.Named;

import bl.ZerbitzuaEJB;
import dl.Azpiekitaldiak;
import dl.Baieztatuak;
import dl.Ekitaldiak;
import dl.Erabiltzaileak;
import dl.Geldialdiak;
import dl.Mapa;

@Named
@SessionScoped
public class ekitaldienErabilpenaMB implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Ekitaldiak ekitaldia=new Ekitaldiak();
	private List<Ekitaldiak> ekitaldiGuztiak=new ArrayList<Ekitaldiak>();
	private List<Azpiekitaldiak> azpiekitaldiGuztiak=new ArrayList<Azpiekitaldiak>();
	private List<Geldialdiak> geldialdiGuztiak=new ArrayList<Geldialdiak>();
	private List<Baieztatuak> baieztatuGuztiak=new ArrayList<Baieztatuak>();
	private Azpiekitaldiak azpiekitaldia= new Azpiekitaldiak();
	private Geldialdiak geldialdia=new Geldialdiak();
	private String iragazitakoa=null;
	private int render=0;
	private String autentikatutakoa;
	private Erabiltzaileak erabiltzailea=new Erabiltzaileak();
	private int kodea=0;
	private Erabiltzaileak profila=new Erabiltzaileak();
	private float balorazioa;
	private int popup;

	
	@EJB
	private ZerbitzuaEJB zEJB;
	
	public ekitaldienErabilpenaMB() {
		super();
	}
	
	public void hasieraPrestatu(String login) {
			autentikatutakoa=login;
			erabiltzailea=zEJB.loginDatuakLortu(login);
			ekitaldiGuztiak=zEJB.ekitaldiGuztiakLortu();
	}
	
	public void ekitaldiakIragazi(){
		List<Ekitaldiak> ekitaldiak=new ArrayList<Ekitaldiak>();
		ekitaldiGuztiak=zEJB.ekitaldiGuztiakLortu();
		if(iragazitakoa!=null && !iragazitakoa.equals("")){
			for(int i=0;i<ekitaldiGuztiak.size();i++) {
				if(ekitaldiGuztiak.get(i).getEkitaldiIzena().contains(iragazitakoa)) {
					ekitaldiak.add(ekitaldiGuztiak.get(i));
				}
			}
			ekitaldiGuztiak.clear();
			ekitaldiGuztiak.addAll(ekitaldiak);
		}
	}
	
	public void azpiekitaldiakIragazi(){
		List<Azpiekitaldiak> azpiekitaldiak=new ArrayList<Azpiekitaldiak>();
		azpiekitaldiGuztiak=zEJB.azpiekitaldiGuztiakLortu(ekitaldia);
		if(iragazitakoa!=null && !iragazitakoa.equals("")){
			for(int i=0;i<azpiekitaldiGuztiak.size();i++) {
				if(azpiekitaldiGuztiak.get(i).getBueltatzekoLekua().contains(iragazitakoa)) {
					azpiekitaldiak.add(azpiekitaldiGuztiak.get(i));
				}
			}
			azpiekitaldiGuztiak.clear();
			azpiekitaldiGuztiak.addAll(azpiekitaldiak);
		}
	}
	
	public void geldialdiakIragazi(){
		geldialdiGuztiak=zEJB.geldialdiGuztiakLortu(azpiekitaldia);
		List<Geldialdiak> geldialdiak=new ArrayList<Geldialdiak>();
		if(iragazitakoa!=null && !iragazitakoa.equals("")){
			for(int i=0;i<geldialdiGuztiak.size();i++) {
				if(geldialdiGuztiak.get(i).getGeldialdiIzena().contains(iragazitakoa)) {
					geldialdiak.add(geldialdiGuztiak.get(i));
				}
			}
			geldialdiGuztiak.clear();
			geldialdiGuztiak.addAll(geldialdiak);
		}
	}
	
	public void AzpiekitaldiakLortu(int idEkitaldi) {
		Ekitaldiak ekitaldiak=zEJB.ekitaldiaLortu(idEkitaldi);
		azpiekitaldiGuztiak=zEJB.azpiekitaldiGuztiakLortu(ekitaldiak);
		ekitaldia=ekitaldiak;
		Clean();
	}
	
	public void GeldialdiakLortu(int idAzpiekitaldi) {
		Azpiekitaldiak azpiekitaldiak=zEJB.azpiekitaldiaLortu(idAzpiekitaldi);
		geldialdiGuztiak=zEJB.geldialdiGuztiakLortu(azpiekitaldiak);
		azpiekitaldia=azpiekitaldiak;
		Clean();
	}
	
	public void PartaideakLortu(int idGeldialdi) {
		Geldialdiak geldialdi=zEJB.geldialdiaLortu(idGeldialdi);
		geldialdia=geldialdi;
		baieztatuGuztiak=zEJB.baieztatuakLortuDB(geldialdia);
		Clean();
	}
	
	public void GeldialdianSartu() {
		Erabiltzaileak erabiltzailea=zEJB.loginDatuakLortu(autentikatutakoa);
		Baieztatuak baieztatua=new Baieztatuak();
		baieztatua.setErabiltzaileak(erabiltzailea);
		baieztatua.setGeldialdiak(geldialdia);
		popup=zEJB.geldialdianSartuDB(erabiltzailea,baieztatua, geldialdia);
		baieztatuakLortu();
	}
	
	public void baieztatuakLortu() {
		baieztatuGuztiak=zEJB.baieztatuakLortuDB(geldialdia);
	}
	
	public void GeldialditikAtera() {
		zEJB.geldialditikAteraDB(erabiltzailea, geldialdia.getIdGeldialdiak());
		baieztatuakLortu();
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
		Date amaiera=dataKalkulatu(7, data);
		ekitaldia.setEkitaldiData(data);
		ekitaldia.setEkitaldiIzena(eMB.getEkitaldiIzena());
		ekitaldia.setEkitaldiZonaldea(eMB.getEkitaldiZonaldea());
		ekitaldia.setPartaideKopurua(2);
		ekitaldia.setSortzailea(autentikatutakoa);
		ekitaldia.setAmaieraData(amaiera);
		zEJB.ekitaldiaSortu(ekitaldia);
	}
	
	public void azpiekitaldiBerriaSortu(AzpiekitaldiakMB aMB) {
		Azpiekitaldiak azpiekitaldiak=new Azpiekitaldiak();
		azpiekitaldiak.setEkitaldiak(ekitaldia);
		azpiekitaldiak.setSortzailea(autentikatutakoa);
		azpiekitaldiak.setBueltatzekoLekua(aMB.getBueltatzekoLekua());
		zEJB.azpiekitaldiaSortu(azpiekitaldiak,ekitaldia);
		render=0;
	}
	
	public void geldialdiBerriaSortu(GeldialdiakMB gMB) {
		Date amaiera=dataKalkulatu(2, gMB.getHasieraData());
		Geldialdiak geldialdiak=new Geldialdiak();
		
		geldialdiak.setHasieraData(gMB.getHasieraData());
		geldialdiak.setAzpiekitaldiak(azpiekitaldia);
		geldialdiak.setGeldialdiIzena(gMB.getGeldialdiIzena());
		geldialdiak.setBatazbestekoBalorazioa(0);
		geldialdiak.setIraungiteData(amaiera);
		geldialdiak.setGeralekua(gMB.getGeralekua());
		geldialdiak.setOrdua(gMB.getOrdua());
		geldialdiak.setPartehartzaileak(0);
		geldialdiak.setSortzailea(autentikatutakoa);
		zEJB.geldialdiaSortu(geldialdiak,azpiekitaldia);
		render=0;
	}

	public void ekitaldiaEzabatu(int idEkitaldia) {
		kodea=zEJB.ekitaldiaEzabatuDB(idEkitaldia);
		if(kodea==1) {
			popup=1;
		}
	}
	public void azpiekitaldiaEzabatu(int idAzpiekitaldia) {
		kodea=zEJB.azpiekitaldiaEzabatuDB(idAzpiekitaldia);
		if(kodea==1) {
			popup=1;
		}
	}
	public void geldialdiaEzabatu(int idGeldialdia) {
		kodea=zEJB.geldialdiaEzabatuDB(idGeldialdia);
		if(kodea==1) {
			popup=1;
		}
	}
	public int getRender() {
		return render;
	}

	public void setRender(int render) {
		this.render = render;
	}
	
	public void renderHasieratu() {
		render=1;
		System.out.println("Render hasieratu: "+render);
	}
	
	public void Clean() {
		System.out.println("No debo entrar aqui");
		render=0;
		kodea=0;
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
	
	public Erabiltzaileak getProfila() {
		return profila;
	}

	public void setProfila(Erabiltzaileak profila) {
		this.profila = profila;
	}

	public Date dataKalkulatu(int kop,Date data){
		Calendar c= Calendar.getInstance();
		c.setTime(data);
		c.add(Calendar.DATE, kop);
		return c.getTime();
	}
	
	public String profilaIkusi(String izena) {
		String orria="erabiltzaileProfila.xhtml";
		if(izena.equals(autentikatutakoa)) {
			orria="profilaAldatu.xhtml";
		}
		else {
			profila=zEJB.profilaLortuDB(izena);
			kodea=zEJB.balorazioKodeaLortuDB(erabiltzailea,profila.getIdErabiltzailea());
		}
		return orria;
	}
	
	public void balorazioaAldatu() {
		zEJB.erabiltzaileaBaloratu(erabiltzailea, profila.getIdErabiltzailea(), balorazioa);
		profilaIkusi(profila.getIzena());
	}
	
	public void ekitaldiakErrefreskatu() {
		ekitaldiGuztiak=zEJB.ekitaldiGuztiakLortu();
		Clean();
	}

	public Erabiltzaileak getErabiltzailea() {
		return erabiltzailea;
	}

	public void setErabiltzailea(Erabiltzaileak erabiltzailea) {
		this.erabiltzailea = erabiltzailea;
	}

	public float getBalorazioa() {
		return balorazioa;
	}

	public void setBalorazioa(float balorazioa) {
		this.balorazioa = balorazioa;
	}

	public Azpiekitaldiak getAzpiekitaldia() {
		return azpiekitaldia;
	}

	public void setAzpiekitaldia(Azpiekitaldiak azpiekitaldia) {
		this.azpiekitaldia = azpiekitaldia;
	}

	public Geldialdiak getGeldialdia() {
		return geldialdia;
	}

	public void setGeldialdia(Geldialdiak geldialdia) {
		this.geldialdia = geldialdia;
	}

	public List<Baieztatuak> getBaieztatuGuztiak() {
		return baieztatuGuztiak;
	}

	public void setBaieztatuGuztiak(List<Baieztatuak> baieztatuGuztiak) {
		this.baieztatuGuztiak = baieztatuGuztiak;
	}
	public List<Mapa> puntuakLortu(){
		return zEJB.puntuakLortuDB();
	}

	public List<Azpiekitaldiak> getAzpiekitaldiGuztiak() {
		return azpiekitaldiGuztiak;
	}

	public void setAzpiekitaldiGuztiak(List<Azpiekitaldiak> azpiekitaldiGuztiak) {
		this.azpiekitaldiGuztiak = azpiekitaldiGuztiak;
	}

	public List<Geldialdiak> getGeldialdiGuztiak() {
		return geldialdiGuztiak;
	}

	public void setGeldialdiGuztiak(List<Geldialdiak> geldialdiGuztiak) {
		this.geldialdiGuztiak = geldialdiGuztiak;
	}

	public int getPopup() {
		return popup;
	}

	public void setPopup(int popup) {
		this.popup = popup;
	}
	
	public void popupItxi() {
		popup=0;
	}
	
	public void saioaAmaitu() throws IOException {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		FacesContext.getCurrentInstance().getExternalContext().redirect("Login.xhtml");
	}
	public String ekitaldietaraBueltatu() {
		ekitaldiGuztiak=zEJB.ekitaldiGuztiakLortu();
		Clean();
		return "hasiera.xhtml";
	}
	
	public String azpiekitaldietaraBueltatu() {
		azpiekitaldiGuztiak=zEJB.azpiekitaldiGuztiakLortu(ekitaldia);
		Clean();
		return "azpiekitaldiak.xhtml";
	}
	
	public String geldialdietaraBueltatu() {
		geldialdiGuztiak=zEJB.geldialdiGuztiakLortu(azpiekitaldia);
		Clean();
		return "geldialdiak.xhtml";
	}
	
	
}
