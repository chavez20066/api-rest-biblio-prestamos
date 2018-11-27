package com.biblioteca.service;

import java.util.List;

import com.biblioteca.model.PrestamosBiblioteca;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface PrestamosService {

	public String findAllPrestamosByCodigoAlumno(String codigoAlumno) throws JsonProcessingException;
}
