package com.biblioteca.service;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseBody;

import com.biblioteca.model.PrestamosBiblioteca;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface PrestamosService {

	public String findAllPrestamosByCodigoAlumno(String codigoAlumno) throws JsonProcessingException;
	
	public String findAllPrestamosActivosDetalleByCodigoAlumno(String codigoAlumno) throws JsonProcessingException, JSONException;	
	
	public String findAllPrestamosDetalle(String codigoAlumno) throws JsonProcessingException;
}
