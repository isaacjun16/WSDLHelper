package com.imt.token.dto;

public class Yytoken {
	public int tokenIndex;
	public String tokenNombre;
	public String tokenValor;
	public int tokenLinea;
	public int tokenColumna;
	
	public Yytoken(int index, String nombre, String valor, int linea, int columna) {
		this.tokenIndex = index;
		this.tokenNombre = nombre;
		this.tokenValor = valor;
		this.tokenLinea = linea;
		this.tokenColumna = columna;
	}
	
	public String toString(){
		return "Token: "+tokenValor+" se identifica por: "+tokenNombre+", posicion > x:"+tokenLinea+", y: "+tokenColumna;
	}
	
	public int getTokenIndex() {
		return tokenIndex;
	}
	public void setTokenIndex(int tokenIndex) {
		this.tokenIndex = tokenIndex;
	}
	public String getTokenNombre() {
		return tokenNombre;
	}
	public void setTokenNombre(String tokenNombre) {
		this.tokenNombre = tokenNombre;
	}
	public String getTokenValor() {
		return tokenValor;
	}
	public void setTokenValor(String tokenValor) {
		this.tokenValor = tokenValor;
	}
	public int getTokenLinea() {
		return tokenLinea;
	}
	public void setTokenLinea(int tokenLinea) {
		this.tokenLinea = tokenLinea;
	}
	public int getTokenColumna() {
		return tokenColumna;
	}
	public void setTokenColumna(int tokenColumna) {
		this.tokenColumna = tokenColumna;
	}
}
