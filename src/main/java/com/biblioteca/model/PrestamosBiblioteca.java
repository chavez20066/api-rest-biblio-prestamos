package com.biblioteca.model;


public class PrestamosBiblioteca {
	

	private String Z36_MATERIAL;
	
	
	private String Z30_CALL_NO;
	
	
	private String Z13_AUTHOR;
	

	private String Z13_TITLE;

	public PrestamosBiblioteca() {

	}

	public String getZ36_MATERIAL() {
		return Z36_MATERIAL;
	}

	public void setZ36_MATERIAL(String z36_MATERIAL) {
		Z36_MATERIAL = z36_MATERIAL;
	}

	public String getZ30_CALL_NO() {
		return Z30_CALL_NO;
	}

	public void setZ30_CALL_NO(String z30_CALL_NO) {
		Z30_CALL_NO = z30_CALL_NO;
	}

	public String getZ13_AUTHOR() {
		return Z13_AUTHOR;
	}

	public void setZ13_AUTHOR(String z13_AUTHOR) {
		Z13_AUTHOR = z13_AUTHOR;
	}

	public String getZ13_TITLE() {
		return Z13_TITLE;
	}

	public void setZ13_TITLE(String z13_TITLE) {
		Z13_TITLE = z13_TITLE;
	}

	@Override
	public String toString() {
		return "PrestamosBiblioteca [Z36_MATERIAL=" + Z36_MATERIAL + ", Z30_CALL_NO=" + Z30_CALL_NO + ", Z13_AUTHOR="
				+ Z13_AUTHOR + ", Z13_TITLE=" + Z13_TITLE + "]";
	}	

}
