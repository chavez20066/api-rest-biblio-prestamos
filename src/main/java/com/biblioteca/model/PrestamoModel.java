package com.biblioteca.model;

public class PrestamoModel {
	
	String codigo;
	String titulo;
	String autor;
	String fecha;
	
	String fechaDevolucion;
	
	public PrestamoModel() {
		
	}
	
	
	public String getFechaDevolucion() {
		return fechaDevolucion;
	}


	public void setFechaDevolucion(String fechaDevolucion) {
		this.fechaDevolucion = fechaDevolucion;
	}


	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	

}
