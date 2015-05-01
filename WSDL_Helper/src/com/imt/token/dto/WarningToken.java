package com.imt.token.dto;

import java.util.ArrayList;
import java.util.List;

public class WarningToken {
	
	public List<Yytoken> warnings = new ArrayList<Yytoken>();
	
	public WarningToken(int indx, String name, String val, int x, int y){
		Yytoken temp = new Yytoken(indx, name, val, x, y);
		warnings.add(temp);
	}
	
	public List<Yytoken> getModos(){
		return this.warnings;
	}
	
	public void clearModos(){
		warnings.clear();
	}
}
