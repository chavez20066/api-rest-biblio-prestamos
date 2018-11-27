package com.biblioteca.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.biblioteca.service.PrestamosService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
//@RequestMapping("/api-rest")
public class PrestamosRestController {
	
	private static final Log LOG = LogFactory.getLog(PrestamosRestController.class);
	
	@Autowired
	private PrestamosService prestamosService;
	
	@GetMapping(value = "/vprestamo")
	public String vprestamo(@RequestParam(name = "codigo", required = true) String codigoLector) throws JsonProcessingException {		
		//return new ObjectMapper().writeValueAsString(expedienteService.findAll());
		LOG.info("METHOD: vprestamo():==>INICIO<==");
		return prestamosService.findAllPrestamosByCodigoAlumno(codigoLector);
		
	}	

}
