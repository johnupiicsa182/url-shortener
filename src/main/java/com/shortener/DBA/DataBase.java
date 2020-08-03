package com.shortener.DBA;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.shortener.Entidades.Url;

public class DataBase {

	String url="C:\\sqlite\\data.db";
	File folder=new File(url.substring(0,10));
	Connection conexion;
	Statement statement=null;
	ResultSet resultSet=null;
	PreparedStatement preparedStatement=null;
	public void abrirConexion() {
		try {
			if(!folder.exists())
				folder.mkdir();
			Class.forName("org.sqlite.JDBC");
			conexion=DriverManager.getConnection("jdbc:sqlite:"+url);
			if(conexion!=null) {
				System.out.println("Conectado");
			}
		} catch (Exception e) {
			System.out.println("Error al conectar"+e.getMessage());
		}	
	}
	public void cerrarConexion() {
		try {
			conexion.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	public void createTable() {
		String sql="CREATE TABLE IF NOT EXISTS Url " +
                "(Alias 		TEXT	NOT NULL," +
                " Url           TEXT    NOT NULL, " +
                " Dominio       TEXT    NOT NULL, " +
                " FechaRegistro TEXT    NOT NULL)";
		try {
			this.abrirConexion();
			statement=conexion.createStatement();
			statement.execute(sql);
			statement.close();
			this.cerrarConexion();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public void insertUrl(Url url) {
		String sql = "INSERT INTO Url (Alias,Url,Dominio,FechaRegistro) VALUES (?,?,?,?)";	
		try {
			this.abrirConexion();
			preparedStatement=conexion.prepareStatement(sql);
			preparedStatement.setString(1,url.getAlias());
			preparedStatement.setString(2,url.getUrl());
			preparedStatement.setString(3,url.getDominio());
			preparedStatement.setString(4,url.getHoraPeticion());
			preparedStatement.execute();
			preparedStatement.close();
			this.cerrarConexion();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public Url selectUrl(Url url) {
		String sql=null;
		try {
			this.abrirConexion();
			if(url.getAlias()!=null) {
				sql = "SELECT * FROM Url where Alias=?;";
				preparedStatement=conexion.prepareStatement(sql);
				preparedStatement.setString(1, url.getAlias());
			}else {
				sql = "SELECT * FROM Url where Url=?;";
				preparedStatement=conexion.prepareStatement(sql);
				preparedStatement.setString(1, url.getUrl());
			}
			resultSet=preparedStatement.executeQuery();
			while(resultSet.next()) {
				url.setAlias(resultSet.getString("Alias"));
				url.setUrl(resultSet.getString("Url"));
				url.setDominio(resultSet.getString("Dominio"));
				url.setHoraPeticion(resultSet.getString("FechaRegistro"));
			}
			resultSet.close();
			preparedStatement.close();
			conexion.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return url;	
	}
}
