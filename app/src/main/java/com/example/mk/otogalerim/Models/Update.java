package com.example.mk.otogalerim.Models;

public class Update{
	private boolean tf;

	public void setTf(boolean tf){
		this.tf = tf;
	}

	public boolean isTf(){
		return tf;
	}

	@Override
 	public String toString(){
		return 
			"Update{" + 
			"tf = '" + tf + '\'' + 
			"}";
		}
}
