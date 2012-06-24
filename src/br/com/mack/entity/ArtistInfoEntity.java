package br.com.mack.entity;

import java.io.Serializable;

public class ArtistInfoEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7448714271500449229L;
	
	private String name = null;
	private String summary = null;
	private String url = null;
	private String imageMedium = null;
	private StringBuffer content = null;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSummary() {
		return summary;
	}
	
	public void setSummary(String summary) {
		this.summary = summary;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getImageMedium() {
		return imageMedium;
	}
	
	public void setImageMedium(String imageMedium) {
		this.imageMedium = imageMedium;
	}
	
	public StringBuffer getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = new StringBuffer(content);
	}
}
