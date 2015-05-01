package com.imt.token.dto;

import java.util.ArrayList;
import java.util.List;

public class ErrorToken {
	
	public List<Yytoken> errores = new ArrayList<Yytoken>();
	
	public ErrorToken(int indx, String name, String val, int x, int y){
		Yytoken temp = new Yytoken(indx, name, val, x, y);
		errores.add(temp);
	}
	
	public List<Yytoken> getModos(){
		return this.errores;
	}
	
	public void clearModos(){
		errores.clear();
	}
}	
