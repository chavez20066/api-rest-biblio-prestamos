package com.biblioteca.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.biblioteca.service.PrestamosService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@CrossOrigin(origins= {"http://10.0.113.96:8080"})
@RestController
//@RequestMapping("/api-rest")
public class PrestamosRestController {
	
	private static final Log LOG = LogFactory.getLog(PrestamosRestController.class);
	
	@Autowired
	private PrestamosService prestamosService;
	
	@GetMapping(value = "/vprestamo")
	public ResponseEntity<String> vprestamo(@RequestParam(name = "codigo", required = true) String codigoLector,
			Authentication authentication,
			HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {		
		//return new ObjectMapper().writeValueAsString(expedienteService.findAll());
		
		LOG.info("METHOD: vprestamo():==>INICIO<=="+ request.getRemoteAddr());
		
		//if(request.getRemoteAddr().equals("10.0.113.44")) {
			//return prestamosService.findAllPrestamosByCodigoAlumno(codigoLector);
			return new ResponseEntity<String>(prestamosService.findAllPrestamosByCodigoAlumno(codigoLector), HttpStatus.OK);
		//}	
		
		//return "IP no permitida";
		
		//return new ResponseEntity<String>(HttpStatus.FORBIDDEN);
	}
	@GetMapping(value = "/vprestamo-detalle")
	public ResponseEntity<String> vprestamoDetalle(@RequestParam(name = "codigo", required = true) String codigoLector,
			Authentication authentication,
			HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException, JSONException {		
		//return new ObjectMapper().writeValueAsString(expedienteService.findAll());
		
		LOG.info("METHOD: vprestamoDetalle():==>INICIO<=="+ request.getRemoteAddr());
		
		if(request.getRemoteAddr().equals("10.0.7.159") || request.getRemoteAddr().equals("10.0.113.44")
				|| request.getRemoteAddr().equals("10.0.113.49") || request.getRemoteAddr().equals("0:0:0:0:0:0:0:1")
				|| request.getRemoteAddr().equals("127.0.0.1")|| request.getRemoteAddr().equals("10.11.103.18")) {
			return new ResponseEntity<String>(prestamosService.findAllPrestamosActivosDetalleByCodigoAlumno(codigoLector), HttpStatus.OK);
		}	
		
		//return "IP no permitida";
		
		return new ResponseEntity<String>(HttpStatus.FORBIDDEN);
	}
	@GetMapping(value = "/vprestamo-all")
	public ResponseEntity<String> vprestamAll(@RequestParam(name = "codigo", required = true) String codigoLector,
			Authentication authentication,
			HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException, JSONException {		
		//return new ObjectMapper().writeValueAsString(expedienteService.findAll());
		
		LOG.info("METHOD: vprestamAll():==>INICIO<=="+ request.getRemoteAddr());
		
		if(request.getRemoteAddr().equals("10.0.7.159") || request.getRemoteAddr().equals("10.0.113.44")
				|| request.getRemoteAddr().equals("10.0.113.49") || request.getRemoteAddr().equals("0:0:0:0:0:0:0:1")
				|| request.getRemoteAddr().equals("127.0.0.1")|| request.getRemoteAddr().equals("10.11.103.18")) {
			return new ResponseEntity<String>(prestamosService.findAllPrestamosDetalle(codigoLector), HttpStatus.OK);
		}	
		
		//return "IP no permitida";
		
		return new ResponseEntity<String>(HttpStatus.FORBIDDEN);
	}
	
}
