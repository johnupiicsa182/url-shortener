package com.shortener.Metodos;

import java.io.File;
import java.util.Date;
import java.util.Random;

import com.shortener.DBA.DataBase;
import com.shortener.Entidades.Json;
import com.shortener.Entidades.Url;

public class MetodosUrl {

	public Json postUrl(Json json) {
		Random random = new Random();
		DataBase dataBase=new DataBase();
		Url url=new Url();
		String cadena=null;
		String[] respuesta=json.getUrl().replace(" ","").split("\\.");	
		url.setUrl(json.getUrl());
		url.setDominio(respuesta[1]);
		url.setHoraPeticion(java.time.LocalDate.now().toString());
		dataBase.createTable();
		url=dataBase.selectUrl(url);
		if(url.getAlias()==null) {
			switch (url.getDominio()) {
			case "google": {
				int longitud=5;
				char[] alias=new char[longitud];
		        cadena = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz"; 
		        for(int n=0;n<longitud;n++) {
		        	alias[n]=cadena.charAt(random.nextInt(alias.length));
		        }
		        url.setAlias(String.valueOf(alias));
				break;
			}
			case "yahoo": {
				int longitud=7;
				char[] alias=new char[longitud];
		        cadena = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ"; 
		        for(int n=0;n<longitud;n++) {
		        	alias[n]=cadena.charAt(random.nextInt(alias.length));
		        }
		        url.setAlias(String.valueOf(alias));
				break;
			}
			default:
				char[] alias=new char[url.getDominio().length()];
				cadena=url.getDominio().replace("a", "").replace("e", "").replace("i", "").replace("o", "").replace("u", "").replace("A", "")
						 .replace("E", "").replace("I", "").replace("O", "").replace("U", "").replace("1", "").replace("2", "").replace("3", "")
						 .replace("4", "").replace("5", "").replace("6", "").replace("7", "").replace("8", "").replace("9", "").replace("0", "")
						 .replace(",", "").replace(":", "").replace(".", "").replace("+", "").replace("-", "").replace(",", "").replace("^", "");
		        for(int n=0;n<url.getDominio().length();n++) {
		        	alias[n]=cadena.charAt(random.nextInt(cadena.length()));
		        }
		        url.setAlias(String.valueOf(alias));
				break;
			}
			dataBase.insertUrl(url);
		}
		
		json.setRespuesta(url.getAlias());
		return json;
	}
	public Url getAlias(String alias) {
		DataBase dataBase=new DataBase();
		Url url=new Url();
		url.setAlias(alias);
		dataBase.createTable();
		url=dataBase.selectUrl(url);
		return url;
	}
}
