package com.shortener.Entidades;



public class Url {

	private String url;
	private String alias;
	private String dominio;
	private String horaPeticion;
	public String getDominio() {
		return dominio;
	}
	public void setDominio(String dominio) {
		this.dominio = dominio;
	}
	public String getHoraPeticion() {
		return horaPeticion;
	}
	public void setHoraPeticion(String horaPeticion) {
		this.horaPeticion = horaPeticion;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	
}
