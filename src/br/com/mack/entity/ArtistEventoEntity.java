package br.com.mack.entity;

import java.io.Serializable;

public class ArtistEventoEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -825474885473291579L;
	
	private String nomeEventos;
	private String cidade;
	private String pais;
	private String rua;
	private String cep;
	private String geoLatitude;
	private String geoLongitude;
	private String timeZone;
	private String comecaDia;
	private String imageMedium;
	private String siteEvento;
	
	public String getNomeEventos() {
		return nomeEventos;
	}
	public void setNomeEventos(String nomeEventos) {
		this.nomeEventos = nomeEventos;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getGeoLatitude() {
		return geoLatitude;
	}
	public void setGeoLatitude(String geoLatitude) {
		this.geoLatitude = geoLatitude;
	}
	public String getGeoLongitude() {
		return geoLongitude;
	}
	public void setGeoLongitude(String geoLongitude) {
		this.geoLongitude = geoLongitude;
	}
	public String getTimeZone() {
		return timeZone;
	}
	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}
	public String getComecaDia() {
		return comecaDia;
	}
	public void setComecaDia(String comecaDia) {
		this.comecaDia = comecaDia;
	}
	public String getImageMedium() {
		return imageMedium;
	}
	public void setImageMedium(String imageMedium) {
		this.imageMedium = imageMedium;
	}
	public String getSiteEvento() {
		return siteEvento;
	}
	public void setSiteEvento(String siteEvento) {
		this.siteEvento = siteEvento;
	}
	
	
}
