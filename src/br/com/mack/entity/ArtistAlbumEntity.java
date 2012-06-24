package br.com.mack.entity;

import java.io.Serializable;

public class ArtistAlbumEntity implements Serializable{

	private String name;
	private String url;
	private String cantorNome;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getCantorNome() {
		return cantorNome;
	}
	public void setCantorNome(String cantorNome) {
		this.cantorNome = cantorNome;
	}
	
	
	
}
