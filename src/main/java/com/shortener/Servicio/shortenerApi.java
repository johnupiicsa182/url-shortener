package com.shortener.Servicio;

import javax.websocket.server.PathParam;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shortener.Entidades.Json;
import com.shortener.Entidades.Url;
import com.shortener.Metodos.MetodosUrl;

@RestController
@RequestMapping("urlShortener")
public class shortenerApi {
	
	
	@PostMapping("/createAlias")
	public Json createAlias(@RequestBody Json json) {
		MetodosUrl url=new MetodosUrl();
		if(json.getUrl()==null||json.getUrl().isEmpty()) {
			json.setRespuesta("La peticion esta vacio o no tiene la estructura adecuada");
		}else {
			json=url.postUrl(json);			
		}	
		return json;
	}
	@GetMapping("/getAlias/{alias}")
	public String getAlias(@PathVariable String alias) {
		MetodosUrl url=new MetodosUrl();
		Url url2=new Url();
		String respuesta=null;
		if(alias==null) {
			respuesta="No se encuentra ningun alias";
		}else {
			url2=url.getAlias(alias);
			respuesta=url2.getUrl();
		}
		return respuesta;
	}

}
